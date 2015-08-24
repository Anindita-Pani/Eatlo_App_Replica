package com.app.eatlo.util;

/* Class to hold each location details*/
public class DeliveryBean {
 String deliveryLoc;
 Boolean storeOpen;
 
 public DeliveryBean(String deliveryLoc,Boolean storeOpen)
 {
	 this.deliveryLoc=deliveryLoc;
	 this.storeOpen=storeOpen;
 }
public String getDeliveryLoc() {
	return deliveryLoc;
}
public void setDeliveryLoc(String deliveryLoc) {
	this.deliveryLoc = deliveryLoc;
}
public Boolean getStoreOpen() {
	return storeOpen;
}
public void setStoreOpen(Boolean storeOpen) {
	this.storeOpen = storeOpen;
}
 
}
