package com.iuh.rencar_project.utils;

import java.text.Normalizer;
import java.util.regex.Pattern;

public class StringUtils {
	public static String unAccent(String s) {
		String temp = Normalizer.normalize(s, Normalizer.Form.NFD);
		Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
		temp = pattern.matcher(temp).replaceAll("").replaceAll(" ", "-").replaceAll("Đ", "D").replaceAll("đ", "d");
		return temp.toLowerCase();
	}
}
