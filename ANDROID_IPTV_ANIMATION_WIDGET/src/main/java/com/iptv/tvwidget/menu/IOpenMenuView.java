package com.iptv.tvwidget.menu;

public interface IOpenMenuView {
	public interface ItemView {
		public void initialize(IOpenMenuItem itemData, int menuType);
	}
}
