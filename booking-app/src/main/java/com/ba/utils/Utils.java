package com.ba.utils;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Utils {

	public static String checkNull(String str, String _default) {
		String result = null;
		try {
			if (str != null && str.trim().length() > 0 && !str.trim().equals("null")) {
				result = str.trim();
			} else {
				result = _default;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public static Long getOrderNumber() {
		Random random = new Random();
		int firstRandomVal = random.nextInt(9999);
		int secRandomVal = random.nextInt(9999);
		String val = String.valueOf(firstRandomVal) + String.valueOf(secRandomVal);
		return Long.parseLong(val);
	}

	public static Map<String, String> getSSConfigTypeMap() {
		Map<String, String> map = new HashMap<String, String>();
		map.put("1111", "GST INCENTIVE");
		map.put("1112", "COMPANY INCENTIVE");
		map.put("PR", "PURCHASE REWARD");
		map.put("L1", "LEVEL 1 REWARD");
		map.put("L2", "LEVEL 2 REWARD");
		map.put("L3", "LEVEL 3 REWARD");
		map.put("L4", "LEVEL 4 REWARD");
		map.put("L5", "LEVEL 5 REWARD");
		map.put("L6", "LEVEL 6 REWARD");

		return map;
	}

	public static void main(String[] arg) {
	System.out.println(LocalDate.now());
	}
}
