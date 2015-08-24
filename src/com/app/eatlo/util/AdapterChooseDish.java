package com.app.eatlo.util;

/*Adapter for displaying all the menu items */
import java.util.List;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.app.eatlo.util.AdapterChooseDish.MenuViewHolder;
import com.app.eatlo.R;

public class AdapterChooseDish extends RecyclerView.Adapter<MenuViewHolder> {
	private List<MenuItemBean> menuItemList;
	private Context context;
	MenuViewHolder holder;
	MenuAddRemoveClickLisner clickListner; // listener for each item
											// added/removed

	public AdapterChooseDish(List<MenuItemBean> menuItemList, Context context,
			MenuAddRemoveClickLisner clickListner) {

		this.context = context;
		this.menuItemList = menuItemList;
		this.clickListner = clickListner;
	}

	@Override
	public int getItemCount() {
		// TODO Auto-generated method stub
		return menuItemList.size();
	}

	@Override
	public void onBindViewHolder(MenuViewHolder holder, int i) {
		// TODO Auto-generated method stub
		MenuItemBean menuObj = menuItemList.get(i);
		holder.tvItemName.setText(menuObj.getItemName());
		holder.tvItemDesc.setText(menuObj.getItemDesc());
		holder.tvItemPrice.setText(context.getResources()
				.getString(R.string.Rs) + menuObj.getItemPrice());
		holder.imgItem.setImageResource(menuObj.getItemImg());
		holder.tvItemCount.setText("" + menuObj.getItemCount());
		if (menuObj.getItemCount() > 0)
			holder.tvItemCount.setTextColor(context.getResources().getColor(
					android.R.color.black));
		else
			holder.tvItemCount.setTextColor(context.getResources().getColor(
					R.color.dark_grey));
	}

	@Override
	public MenuViewHolder onCreateViewHolder(ViewGroup viewGroup, int arg1) {
		// TODO Auto-generated method stub
		View v = LayoutInflater.from(viewGroup.getContext()).inflate(
				R.layout.list_item_choose_dish, null);
		holder = new MenuViewHolder(v);
		return holder;
	}

	// interface for each item added/removed
	public interface MenuAddRemoveClickLisner {
		public void Clicked(Boolean itemAdded, String price);
	}

	// View holder for list items
	public class MenuViewHolder extends RecyclerView.ViewHolder implements
			OnClickListener {
		TextView tvItemName, tvItemDesc, tvItemPrice, tvItemCount;
		ImageView imgItem, imgAddItem, imgRemoveItem;

		public MenuViewHolder(View itemView) {
			super(itemView);
			tvItemName = (TextView) itemView.findViewById(R.id.tvItemName);
			tvItemDesc = (TextView) itemView.findViewById(R.id.tvItemDesc);
			tvItemPrice = (TextView) itemView.findViewById(R.id.tvItemPrice);
			tvItemCount = (TextView) itemView.findViewById(R.id.menuItemCount);
			imgItem = (ImageView) itemView.findViewById(R.id.imgDishItem);
			imgAddItem = (ImageView) itemView.findViewById(R.id.imgMenuAddItem);
			imgRemoveItem = (ImageView) itemView
					.findViewById(R.id.imgRemoveMenuItem);
			// set click listener for add and remove image
			imgAddItem.setOnClickListener(this);
			imgRemoveItem.setOnClickListener(this);

		}

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			int position = getPosition();
			MenuItemBean menuItemClicked = menuItemList.get(position);
			switch (v.getId()) {
			case R.id.imgMenuAddItem:
				// increase the item count
				menuItemClicked
						.setItemCount(menuItemClicked.getItemCount() + 1);
				// update UI of position-th item in recycler view
				notifyItemChanged(position);
				// send the price of the item to be added
				clickListner.Clicked(true, menuItemClicked.getItemPrice());
				break;
			case R.id.imgRemoveMenuItem:
				// if item count is greater than zero , only then price and
				// count can be reduced
				// else send the price to be added as 0 and handle this
				// condition in choose fragment
				if (menuItemClicked.getItemCount() > 0) {
					// decrease the item count
					menuItemClicked
							.setItemCount(menuItemClicked.getItemCount() - 1);
					// update UI of position-th item in recycler view
					notifyItemChanged(position);
					// send the price of the item to be subtracted
					clickListner.Clicked(false, menuItemClicked.getItemPrice());
				} else
					// send the price as 0 (item count is 0)
					clickListner.Clicked(false, "0");
				break;
			default:
				break;
			}
			menuItemClicked = null;
		}

	}

}
