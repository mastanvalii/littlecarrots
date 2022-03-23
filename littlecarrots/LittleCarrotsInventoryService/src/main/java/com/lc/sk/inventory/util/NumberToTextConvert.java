package com.lc.sk.inventory.util;

import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.regex.Pattern;

public class NumberToTextConvert {

	private static String[] texts= {"zero", "one", "two", "three", "four", "five", "six", "seven","eight", "nine", "ten", "eleven", "twelve", "thirteen", "fourteen", "fifteen","sixteen", "seventeen", "eighteen", "nineteen", "twenty", "twenty one", "twenty two", "twenty three","twenty four"};
	
	public static String checkNumbers(String value) {
		String result="";
		for(int i=0; i<texts.length; i++) {
			value = value.replaceAll(""+i+"", texts[i]);
		}
		result = value;
		return result;
	}
	
}
