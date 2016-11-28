package com.asura.fui.page.view.script;

import java.util.HashMap;

import com.asura.fui.util.ParameterUtil;

public class ParaMapping {

	private String from;
	private String to;
	private HashMap<String, String> fromMap;
	private HashMap<String, String> toMap;

	public ParaMapping() {
	}

	public ParaMapping(String from, String to) {
		this.from = from;
		this.to = to;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public HashMap<String, String> getFromMap() {
		initial();
		return this.fromMap;
	}

	public HashMap<String, String> getToMap() {
		initial();
		return this.toMap;
	}

	private synchronized void initial() {
		if (this.fromMap == null) {
			this.fromMap = ParameterUtil.convert(this.from);
			this.toMap = ParameterUtil.convert(this.to);
		}
	}
}
