package com.lc.sk.inventory.security.cache;

import java.util.Comparator;

import com.lc.sk.inventory.security.beans.ProductFullDetailsWithImage;

public class SortProductDetails  implements Comparator<ProductFullDetailsWithImage>{

	@Override
	public int compare(ProductFullDetailsWithImage o1, ProductFullDetailsWithImage o2) {
		// TODO Auto-generated method stub
		return (int) (Long.parseLong(o1.getProductid())-Long.parseLong(o2.getProductid()));
	}

}
