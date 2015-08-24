package com.app.eatlo.util;

/*Adapter for displaying all the locations */
import java.util.List;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.app.eatlo.R;
import com.app.eatlo.util.AdapterDeliveryLocation.DeliveryLocViewHolder;

public class AdapterDeliveryLocation extends
		RecyclerView.Adapter<DeliveryLocViewHolder> {

	private List<DeliveryBean> deliveryList;
	private Context context;
	DeliveryLocViewHolder holder;
	LocItemClick itemClickedListner;// listener to find if selected location is
									// closed/opened.

	public AdapterDeliveryLocation(List<DeliveryBean> deliveryList,
			Context context, LocItemClick itemClickedListner) {
		this.itemClickedListner = itemClickedListner;
		this.context = context;
		this.deliveryList = deliveryList;
	}

	@Override
	public int getItemCount() {
		// TODO Auto-generated method stub
		return deliveryList.size();
	}

	@Override
	public void onBindViewHolder(DeliveryLocViewHolder holder, int i) {

		// TODO Auto-generated method stub

		// set the location name
		holder.tvLoc.setText(deliveryList.get(i).getDeliveryLoc());

	}

	@Override
	public DeliveryLocViewHolder onCreateViewHolder(ViewGroup viewGroup,
			int arg1) {
		// TODO Auto-generated method stub
		View v = LayoutInflater.from(viewGroup.getContext()).inflate(
				R.layout.list_item_delivery_loc, null);
		RecyclerView.LayoutParams lp = new RecyclerView.LayoutParams(
				ViewGroup.LayoutParams.MATCH_PARENT, // width
				ViewGroup.LayoutParams.WRAP_CONTENT);
		v.setLayoutParams(lp);
		holder = new DeliveryLocViewHolder(v);

		return holder;
	}

	// View Holder for each location item
	public class DeliveryLocViewHolder extends RecyclerView.ViewHolder {
		TextView tvLoc;
		LinearLayout llLoc;

		public DeliveryLocViewHolder(View itemView) {
			super(itemView);
			tvLoc = (TextView) itemView.findViewById(R.id.tvDeliveryLoc);
			llLoc = (LinearLayout) itemView.findViewById(R.id.llLoc);
			llLoc.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					// pass the open/closed status to the Main Activity
					itemClickedListner.LocationClicked(deliveryList.get(
							getPosition()).getStoreOpen());
				}
			});

		}

	}

	// interface - if location is opened/closed
	public interface LocItemClick {
		public void LocationClicked(Boolean open);
	}
}
