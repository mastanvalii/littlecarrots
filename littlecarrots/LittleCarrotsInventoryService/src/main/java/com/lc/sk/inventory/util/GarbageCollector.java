package com.lc.sk.inventory.util;

import org.springframework.stereotype.Component;

@Component
public class GarbageCollector {

	public static void clearMemory() {
		System.gc();
		Runtime.getRuntime().gc();
	}
}
