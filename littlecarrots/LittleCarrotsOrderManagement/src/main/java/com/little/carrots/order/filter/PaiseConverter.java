package com.little.carrots.order.filter;

public class PaiseConverter {

	public static int convert(double price) {
		return ((int)(price*100));
	}
}
