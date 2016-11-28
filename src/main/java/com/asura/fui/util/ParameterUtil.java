package com.asura.fui.util;

import java.util.HashMap;
import java.util.LinkedHashMap;

import com.asura.fui.page.data.FrontData;
import com.asura.tools.util.StringUtil;

public class ParameterUtil {

	private static HashMap<String, HashMap<String, String>> cache = new HashMap<>();

	public static synchronized HashMap<String, String> convert(String ec) {
		if (ec == null) {
			ec = "";
		}
		if (!(cache.containsKey(ec))) {
			HashMap<String, String> map = new LinkedHashMap<>();
			String[] ss = StringUtil.split(ec.trim(), ",");
			for (String s : ss) {
				int index = s.indexOf("=");
				if (index > 0) {
					map.put(replaceSpecial(s.substring(0, index).trim()),
							replaceSpecial(s.substring(index + 1).trim()));
				}

			}

			cache.put(ec, map);
		}

		return cache.get(ec);
	}

	public static String format(String value) {
		value = value.replace("=", "&e;");
		value = value.replace(",", "&c;");

		return value;
	}

	private static String replaceSpecial(String v) {
		v = v.replace("&e;", "=");
		v = v.replace("&c;", ",");

		return v;
	}

	public static String getValue(String value, FrontData paras) {
		if (StringUtil.isNullOrEmpty(value)) {
			return "";
		}
		String t = value;
		for (String p : paras.getAllFields()) {
			t = t.replace("$" + p + "$", paras.getValueString(p));
		}

		if ((t.trim().startsWith("$")) && (t.trim().endsWith("$")) && (t.length() < 20)) {
			t = "";
		}
		return t;
	}

	public static String getPagePath(String suffix) {
		if (!suffix.contains(".fui")) {
			return null;
		} else {
			if (suffix.startsWith("/")) {
				suffix = suffix.substring(1, suffix.indexOf(".fui"));
			} else {
				suffix = suffix.substring(0, suffix.indexOf(".fui"));
			}

		}

		return suffix + ".xml";

	}

	public static void main(String[] args) {
		System.out.println(getPagePath("/shendiao/aaa.fui"));
		System.out.println(convert(
				"type=text,value=Search this site,id=s,onblur=if (this.value == ''){this.value = 'Search this site';},onfocus=if (this.value == 'Search this site'){this.value = '';},style="));
	}
}
