package com.app.eatlo;

/* Container of both screens - DeliveryLocation and Choose Menu Item*/
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;

import com.app.eatlo.util.DeliveryBean;
import com.app.eatlo.util.DialogMessage;
import com.app.eatlo.util.AdapterDeliveryLocation.LocItemClick;

public class MainActivity extends ActionBarActivity {

	FrameLayout container;
	DialogMessage dialogLocInactive;
	FragmentManager fragMgr;
	FragmentTransaction trans;
	DeliveryLocFragment deliveryFrag;
	ImageView imgBack;
	DialogMessage dialogLeavCart;
	ChooseDishesFragment chooseDishFrag;
	int backPressedCount = 1; // for toast to press back again

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		container = (FrameLayout) findViewById(R.id.frame);
		imgBack = (ImageView) findViewById(R.id.imgBack); // image back on top
															// right corner
		deliveryFrag = new DeliveryLocFragment(itemClickListner);
		fragMgr = getSupportFragmentManager();
		trans = fragMgr.beginTransaction();
		trans.add(R.id.frame, deliveryFrag).commit(); // add the fragment
														// Delivery Location
		imgBack.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				onBackPressed();
			}
		});
		// dialog for closed locations
		dialogLocInactive = new DialogMessage(MainActivity.this,
				new OnClickListener() {

					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						dialogLocInactive.dismiss();
					}
				}, null, getResources().getString(R.string.msgLocClosed),
				getResources().getString(android.R.string.ok), true);
	}

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub

		int count = fragMgr.getBackStackEntryCount(); // total no of
														// transactions in back
														// stack
		if (count == 0) {
			if (backPressedCount > 0) {
				// show message to press back again
				Toast.makeText(MainActivity.this,
						getResources().getString(R.string.msgExit),
						Toast.LENGTH_SHORT).show();
				backPressedCount--;
			}

			else
				super.onBackPressed();

		} else {

			// for choose dish fragment
			// show dialog if displayed fragment is Choose Dish and items are
			// added to cart
			if (chooseDishFrag.isVisible() && chooseDishFrag.ItemPresent()) {

				dialogLeavCart = new DialogMessage(MainActivity.this,
						new OnClickListener() {

							@Override
							public void onClick(View v) {
								// TODO Auto-generated method stub
								imgBack.setVisibility(View.GONE);
								fragMgr.popBackStack();
								dialogLeavCart.dismiss();
								imgBack.setVisibility(View.GONE);
							}
						}, new OnClickListener() {

							@Override
							public void onClick(View v) {
								// TODO Auto-generated method stub

								dialogLeavCart.dismiss();
							}
						}, getResources().getString(R.string.msgClearCart),
						getResources().getString(R.string.strYes), false);

				dialogLeavCart.show(getSupportFragmentManager(), "");
				dialogLeavCart.setCancelable(true);

			} else {
				// for other fragments and if no items in cart
				super.onBackPressed();
				imgBack.setVisibility(View.GONE);
			}
		}

	}

	// location item click listener to find if location is opened or closed
	LocItemClick itemClickListner = new LocItemClick() {

		@Override
		public void LocationClicked(Boolean open) {

			// TODO Auto-generated method stub
			if (open) {

				// show choose item fragment
				imgBack.setVisibility(View.VISIBLE);
				trans = fragMgr.beginTransaction();
				trans.setCustomAnimations(android.R.anim.slide_in_left,
						android.R.anim.slide_out_right,
						android.R.anim.slide_in_left,
						android.R.anim.slide_out_right);
				trans.remove(deliveryFrag);
				chooseDishFrag = new ChooseDishesFragment();
				trans.add(R.id.frame, chooseDishFrag);
				trans.addToBackStack(null);
				trans.commit();

			} else {
				dialogLocInactive.show(getSupportFragmentManager(), "");
			}

		}
	};

}
