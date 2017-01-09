package com.asura.fui.page.view.script.chart;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import com.asura.fui.page.data.FrontData;
import com.asura.fui.util.ParameterUtil;
import com.asura.tools.util.StringUtil;

public class ChartData {

	public static final String RECORD_SPLITER = "||";
	public static final String FIELD_SPLITER = ",";
	private HashMap<String, List<String>> mmap;
	private List<String> labels;
	private String dataVar;
	private String keyName;
	private String valueNames;
	private String xLabels;


	public ChartData() {
		this.mmap = new LinkedHashMap<>();
		this.labels = new ArrayList<>();
	}

	public String[] getLabels(FrontData paras) {
		List<String> list = new ArrayList<>();
		for (String l : this.labels) {
			list.add(ParameterUtil.getValue(l, paras));
		}
		return list.toArray(new String[0]);
	}

	public String getxLabels(FrontData paras) {
		return this.xLabels;
	}
	
	public void setxLabels(String xLabels) {
		this.xLabels = xLabels;
	}
	
	public String getKeyName(FrontData paras) {
		if (StringUtil.isNullOrEmpty(this.keyName)) {
			this.keyName = "x1";
		}

		return ParameterUtil.getValue(this.keyName, paras);
	}

	public void setKeyName(String keyName) {
		this.keyName = keyName;
	}

	public void setValueNames(String valueNames) {
		this.valueNames = valueNames;
	}

	public String getDataVar() {
		return this.dataVar;
	}

	public void setDataVar(String dataVar) {
		this.dataVar = dataVar;
	}

	public boolean isNoData() {
		return (this.mmap.size() == 0);
	}

	public String toData() {
		if (isNoData()) {
			return this.dataVar;
		}
		String s = "[\n";

		List<String> list = new ArrayList<>();

		for (String key : getKeys()) {
			String[] vs = getValues(key);
			List<String> subs = new ArrayList<>();
			for (int i = 0; i < vs.length; ++i) {
				subs.add("y" + (i + 1) + ": " + vs[i]);
			}
			list.add("    { x1: '" + key + "', " + StringUtil.getStringFromStrings(subs, ", ") + "}");
		}

		s = s + StringUtil.getStringFromStrings(list, ",\n") + "\n    ]";

		return s;
	}

	public String[] getValueNames(FrontData paras) {
		List<String> list = new ArrayList<>();

		if (StringUtil.isNullOrEmpty(this.valueNames)) {
			for (int i = 0; i < this.labels.size(); ++i)
				list.add("y" + (i + 1));
		} else {
			for (String s : StringUtil.split(this.valueNames, ",")) {
				list.add(ParameterUtil.getValue(s.trim(), paras));
			}
		}

		return ((String[]) list.toArray(new String[0]));
	}

	public String[] getKeys() {
		return ((String[]) this.mmap.keySet().toArray(new String[0]));
	}

	public String[] getValues(String key) {
		return this.mmap.get(key).toArray(new String[0]);
	}

	public int getValueLength() {
		if (this.mmap.size() > 0) {
			return this.mmap.values().iterator().next().size();
		}

		return 0;
	}

	public static ChartData fromString(String key, String value, String label) {
		ChartData data = new ChartData();

		if ((!(StringUtil.isNullOrEmpty(key))) && (!(StringUtil.isNullOrEmpty(value)))) {
			String[] keys = StringUtil.split(key, ",");
			String[] arrayOfString2;
			int ii = (arrayOfString2 = StringUtil.split(value, "||")).length;
			for (int str1 = 0; str1 < ii; ++str1) {
				String r = arrayOfString2[str1];
				String[] vs = StringUtil.split(r.trim(), ",");
				if (vs.length == keys.length)
					for (int i = 0; i < keys.length; ++i) {
						String k = keys[i].trim();
						if (!(data.mmap.containsKey(k))) {
							data.mmap.put(k, new ArrayList<String>());
						}
						data.mmap.get(k).add(vs[i].trim());
					}
			}
		}
		String[] arrayOfString1;
		int str1 = (arrayOfString1 = StringUtil.split(label, ",")).length;
		for (int r = 0; r < str1; ++r) {
			String l = arrayOfString1[r];
			data.labels.add(l.trim());
		}

		return data;
	}
}
