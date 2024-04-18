package com.example.zepto.global;

import java.util.ArrayList;
import java.util.List;

import com.example.zepto.model.product;

public class globalData {
	public static List<product> cart;
	static {
		cart = new ArrayList<product>();
	}

}
