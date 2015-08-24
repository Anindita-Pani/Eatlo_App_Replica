package com.app.eatlo;

/* Show location list*/
import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.eatlo.util.DeliveryBean;
import com.app.eatlo.util.AdapterDeliveryLocation;
import com.app.eatlo.util.AdapterDeliveryLocation.LocItemClick;

public class DeliveryLocFragment extends Fragment {

	RecyclerView rvDeliveryLocList;
	LocItemClick itemClickListner;

	public DeliveryLocFragment(LocItemClick itemClickListner) {
		this.itemClickListner = itemClickListner;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View rootView = inflater.inflate(R.layout.delivery_loc, container,
				false);
		rvDeliveryLocList = (RecyclerView) rootView.findViewById(R.id.rvDelLoc);
		rvDeliveryLocList.setLayoutManager(new LinearLayoutManager(
				getActivity()));
		// set adapter
		rvDeliveryLocList.setAdapter(new AdapterDeliveryLocation(LocList(),
				getActivity(), itemClickListner));
		return rootView;
	}

	// get all the delivery locations and thier status(open/closed)
	public List<DeliveryBean> LocList() {
		DeliveryBean delObj;
		List<DeliveryBean> deliveryList = new ArrayList<DeliveryBean>();
		delObj = new DeliveryBean("HSR Layout", true);
		deliveryList.add(delObj);
		delObj = new DeliveryBean("Koramangala", false);
		deliveryList.add(delObj);
		delObj = new DeliveryBean("Indiranagar", false);
		deliveryList.add(delObj);
		delObj = new DeliveryBean("BTM Layout", true);
		deliveryList.add(delObj);
		delObj = new DeliveryBean("AKR Tech Park", false);
		deliveryList.add(delObj);
		delObj = new DeliveryBean("Sarjapur Road", true);
		deliveryList.add(delObj);
		delObj = new DeliveryBean("Dolmur", true);
		deliveryList.add(delObj);
		delObj = new DeliveryBean("Bellandur", true);
		deliveryList.add(delObj);
		delObj = new DeliveryBean("Outer Ring Road - Kadubeesanahalli", false);
		deliveryList.add(delObj);
		delObj = new DeliveryBean("MG Road", true);
		deliveryList.add(delObj);
		delObj = new DeliveryBean("Bannerghatta Road", false);
		deliveryList.add(delObj);
		delObj = new DeliveryBean("Old Airport Road-Kodihalli", false);
		deliveryList.add(delObj);
		delObj = new DeliveryBean("ITPL - Brookefield", true);
		deliveryList.add(delObj);
		delObj = new DeliveryBean("Bagmane Tech Park - C.V. Raman Nagar", false);
		deliveryList.add(delObj);
		return deliveryList;

	}
}
