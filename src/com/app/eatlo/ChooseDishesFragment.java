package com.app.eatlo;

/* Display menu items*/
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import com.app.eatlo.util.AdapterChooseDish;
import com.app.eatlo.util.AdapterChooseDish.MenuAddRemoveClickLisner;
import com.app.eatlo.util.MenuItemBean;

public class ChooseDishesFragment extends Fragment implements OnClickListener{

	RecyclerView rvChooseDish;
	TextView tvVeg,tvNonVeg,tvAll,tvPrice,tvItemCount;
	AdapterChooseDish adapterChooseDish;
	List<MenuItemBean> menuItemList;//list to store values of menu items
	public static final String VEG="VEG";
	public static final String NON_VEG="NON_VEG";
	int totalPrice=0,totalItemCount=0;
	Animation upDownAnim; // item count animation above cart image
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View rootView = inflater.inflate(R.layout.choose_dishes, container,
				false);
		rvChooseDish=(RecyclerView)rootView.findViewById(R.id.rvChooseDish);
		rvChooseDish.setLayoutManager(new LinearLayoutManager(getActivity()));
		// get the list of all items
		menuItemList=GetMenuItemList();
		upDownAnim=AnimationUtils.loadAnimation(getActivity(), R.anim.updown);
		//set the adapter of recycler view
		adapterChooseDish=new AdapterChooseDish(menuItemList,getActivity(),menuItemAddRemListner);
		rvChooseDish.setAdapter(adapterChooseDish);
		
		tvItemCount=(TextView)rootView.findViewById(R.id.tvItemCount);
		tvVeg=(TextView)rootView.findViewById(R.id.tvVeg);
		tvNonVeg=(TextView)rootView.findViewById(R.id.tvNonVeg);
		tvAll=(TextView)rootView.findViewById(R.id.tvAll);
		tvPrice=(TextView)rootView.findViewById(R.id.tvPrice);
		tvVeg.setOnClickListener(this);
		tvNonVeg.setOnClickListener(this);
		tvAll.setOnClickListener(this);
		
		return rootView;
	}
	
	// listner for each item added / removed
	MenuAddRemoveClickLisner menuItemAddRemListner=new MenuAddRemoveClickLisner() {
		
		@Override
		public void Clicked(Boolean itemAdded, String price) {
			// TODO Auto-generated method stub
			
			if(itemAdded)
			{
				// item added 
				tvItemCount.startAnimation(upDownAnim);
				totalPrice=totalPrice+Integer.parseInt(price);
				totalItemCount=totalItemCount+1;
			}
			else if(Integer.parseInt(price)>0)
			{
				// item removed 
				tvItemCount.startAnimation(upDownAnim);
				totalPrice=totalPrice-Integer.parseInt(price);
				totalItemCount=totalItemCount-1;
			}
			// set the updated price and count
			tvPrice.setText(""+totalPrice);	
			tvItemCount.setText(""+totalItemCount);
				
		}
	};

	// list of all items(considering all opened locations have same menu)
	public List<MenuItemBean> GetMenuItemList() {
		List<MenuItemBean> menuItemList = new ArrayList<MenuItemBean>();
		MenuItemBean menuItem ;
		menuItem = new MenuItemBean(0,
				"Chicken Kebab",
				"Tossed with malai and spicy tangy flovours,this chicken kebab is awesome.",
				"120", NON_VEG,R.drawable.ic_chicken_kebab);
		menuItemList.add(menuItem);
		menuItem = new MenuItemBean(0,
				"Kheer",
				"A home made kheer made with milk and lots of nuts.",
				"60", VEG, R.drawable.ic_kheer);
		menuItemList.add(menuItem);
		menuItem = new MenuItemBean(0,
				"Chicken Biryani",
				"An authentic dish of chicken biryani slown cooked in dum to infuse the taste of all indian spices together.",
				"160", NON_VEG, R.drawable.ic_biryani);
		menuItemList.add(menuItem);
		menuItem = new MenuItemBean(0,
				"Rooh Afza Lemonade",
				"A refreshing summer cooler prepared with Hamdard ka Rooh Afza and a dash of Lemon juice",
				"30", VEG, R.drawable.ic_rooh_afza);
		menuItemList.add(menuItem);
		
		
		
		return menuItemList;

	}
	//list of non veg items
	public List<MenuItemBean> GetNonVegItemList(List<MenuItemBean> menuItemList) {
		List<MenuItemBean> nonvegItemList = new ArrayList<MenuItemBean>();
		for(int counter=0;counter<menuItemList.size();counter++)
		{
			if(menuItemList.get(counter).getItemCategory().equals(NON_VEG))
				nonvegItemList.add(menuItemList.get(counter));
		}
		
		
		return nonvegItemList;

	}
	//list of veg items
	public List<MenuItemBean> GetVegItemList(List<MenuItemBean> menuItemList) {
		List<MenuItemBean> vegItemList = new ArrayList<MenuItemBean>();
		
		for(int counter=0;counter<menuItemList.size();counter++)
		{
			if(menuItemList.get(counter).getItemCategory().equals(VEG))
				vegItemList.add(menuItemList.get(counter));
		}
		
		
		return vegItemList;

	}

	
	// click listener for veg , non-veg and all selection
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.tvAll:
			adapterChooseDish=new AdapterChooseDish(menuItemList,getActivity(),menuItemAddRemListner);
			rvChooseDish.setAdapter(adapterChooseDish);
			tvAll.setBackgroundColor(getResources().getColor(R.color.theme_color));
			tvVeg.setBackgroundColor(getResources().getColor(R.color.dark_grey));
			tvNonVeg.setBackgroundColor(getResources().getColor(R.color.dark_grey));
			break;
		case R.id.tvVeg:
			
			adapterChooseDish=new AdapterChooseDish(GetVegItemList(menuItemList),getActivity(),menuItemAddRemListner);
			rvChooseDish.setAdapter(adapterChooseDish);
			tvAll.setBackgroundColor(getResources().getColor(R.color.dark_grey));
			tvVeg.setBackgroundColor(getResources().getColor(R.color.theme_color));
			tvNonVeg.setBackgroundColor(getResources().getColor(R.color.dark_grey));
			break;
		case R.id.tvNonVeg:
			adapterChooseDish=new AdapterChooseDish(GetNonVegItemList(menuItemList),getActivity(),menuItemAddRemListner);
			rvChooseDish.setAdapter(adapterChooseDish);
			tvAll.setBackgroundColor(getResources().getColor(R.color.dark_grey));
			tvVeg.setBackgroundColor(getResources().getColor(R.color.dark_grey));
			tvNonVeg.setBackgroundColor(getResources().getColor(R.color.theme_color));
			break;
		default:
			break;
		}
	}
	
	// find if items are added 
	public Boolean ItemPresent()
	{
		if(totalItemCount>0)
			return true;
		else
			return false;
			
	}
}
