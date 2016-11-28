package com.asura.fui.page.view.script.jquery;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.asura.fui.page.data.FrontData;
import com.asura.fui.page.view.script.IUIScript;
import com.asura.fui.page.view.script.ScriptFunction;
import com.asura.fui.util.ParameterUtil;
import com.asura.tools.util.StringUtil;

public class JQueryGet implements IUIScript {

	private static final Logger logger = LoggerFactory.getLogger(JQueryGet.class);

	private String url;
	private String urlVar;
	private String consts;
	private String vars;
	private ScriptFunction callback;
	private HashMap<String, String> conMap;
	private HashMap<String, String> varMap;

	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUrlVar() {
		return this.urlVar;
	}

	public void setUrlVar(String urlVar) {
		this.urlVar = urlVar;
	}

	public String getConsts() {
		return this.consts;
	}

	public void setConsts(String consts) {
		this.consts = consts;
	}

	public String getVars() {
		return this.vars;
	}

	public void setVars(String vars) {
		this.vars = vars;
	}

	public ScriptFunction getCallback() {
		return this.callback;
	}

	public void setCallback(ScriptFunction callback) {
		this.callback = callback;
	}

	@Override
	public String toScript(FrontData paras) {
		initial();

		String s = "";

		if (!(StringUtil.isNullOrEmpty(this.url))) {
			this.url = ParameterUtil.getValue(this.url, paras);
		}

		if (!(StringUtil.isNullOrEmpty(this.url)))
			s = "$.get(\"" + this.url + "\"";
		else if (!(StringUtil.isNullOrEmpty(this.urlVar))) {
			s = "$.get(" + this.urlVar;
		}

		if ((this.conMap.size() > 0) || (this.varMap.size() > 0)) {
			s = s + " + \"?\"";
		}

		List<String> clist = new ArrayList<>();
		for (Iterator<String> iterator = conMap.keySet().iterator(); iterator.hasNext();) {
			String c = (String) iterator.next();
			try {
				if (clist.size() == 0) {
					clist.add((new StringBuilder("\"")).append(c).append("=")
							.append(URLEncoder.encode((String) conMap.get(c), "utf8")).append("\"").toString());
				} else {
					clist.add((new StringBuilder("\"&")).append(c).append("=")
							.append(URLEncoder.encode((String) conMap.get(c), "utf8")).append("\"").toString());
				}
			} catch (UnsupportedEncodingException e) {
				logger.error("parse jquery script function failed, detail:" + conMap, e);
			}
		}

		if (clist.size() > 0) {
			s = s + " + " + StringUtil.getStringFromStrings(clist, " + ");
		}

		List<String> vlist = new ArrayList<>();
		for (String c : this.varMap.keySet()) {
			if ((vlist.size() == 0) && (clist.size() == 0))
				vlist.add("\"" + c + "=\" + encodeURI(" + ((String) this.varMap.get(c)) + ")");
			else {
				vlist.add("\"&" + c + "=\" + encodeURI(" + ((String) this.varMap.get(c)) + ")");
			}
		}

		if (vlist.size() > 0) {
			s = s + " + " + StringUtil.getStringFromStrings(vlist, " + ");
		}

		this.callback.setCallback(true);
		s = s + ", " + this.callback.toScript(paras);

		s = s + ");";

		return s;
	}

	private synchronized void initial() {
		if (this.conMap == null) {
			this.conMap = new LinkedHashMap<>();
			if (!(StringUtil.isNullOrEmpty(this.consts))) {
				String[] cs = StringUtil.split(this.consts, ",");
				for (String c : cs) {
					String[] vs = StringUtil.split(c.trim(), "=");
					if (vs.length == 2) {
						this.conMap.put(vs[0].trim(), vs[1].trim());
					}
				}
			}

			this.varMap = new LinkedHashMap<>();
			if (!(StringUtil.isNullOrEmpty(this.vars))) {
				String[] cs = StringUtil.split(this.vars, ",");
				for (String c : cs) {
					String[] vs = StringUtil.split(c.trim(), "=");
					if (vs.length == 2)
						this.varMap.put(vs[0].trim(), vs[1].trim());
				}
			}
		}
	}

}
