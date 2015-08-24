package com.app.eatlo.util;

/* Class to hold each menu item details */
public class MenuItemBean {

	public MenuItemBean(int itemCount, String itemName, String itemDesc,
			String itemPrice, String itemCategory, int itemImg) {
		this.itemName = itemName;
		this.itemDesc = itemDesc;
		this.itemPrice = itemPrice;
		this.itemImg = itemImg;
		this.itemCategory = itemCategory;
		this.itemCount = itemCount;
	}

	int itemCount;

	public int getItemCount() {
		return itemCount;
	}

	public void setItemCount(int itemCount) {
		this.itemCount = itemCount;
	}

	String itemCategory;

	public String getItemCategory() {
		return itemCategory;
	}

	public void setItemCategory(String itemCategory) {
		this.itemCategory = itemCategory;
	}

	String itemName, itemDesc, itemPrice;
	int itemImg;

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getItemDesc() {
		return itemDesc;
	}

	public void setItemDesc(String itemDesc) {
		this.itemDesc = itemDesc;
	}

	public String getItemPrice() {
		return itemPrice;
	}

	public void setItemPrice(String itemPrice) {
		this.itemPrice = itemPrice;
	}

	public int getItemImg() {
		return itemImg;
	}

	public void setItemImg(int itemImg) {
		this.itemImg = itemImg;
	}

}
