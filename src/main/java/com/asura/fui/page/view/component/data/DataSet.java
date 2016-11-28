package com.asura.fui.page.view.component.data;

import java.util.HashMap;
import java.util.List;

import com.asura.fui.page.data.FrontData;
import com.asura.fui.page.view.component.layout.IUILayout;
import com.asura.fui.page.view.html.HtmlDiv;
import com.asura.fui.page.view.html.IHtmlElement;
import com.asura.fui.page.view.html.SimpleHtml;
import com.asura.fui.util.ParameterUtil;
import com.asura.tools.util.StringUtil;

public class DataSet extends AbstractUIData {
	private List<IUIData> set;
	private String tag;
	private String attrs;
	private String content;

	public List<IUIData> getSet() {
		return this.set;
	}

	public void setSet(List<IUIData> set) {
		this.set = set;
	}

	public IHtmlElement toElement(IUILayout layout, FrontData paras) {
		IHtmlElement div = null;
		if (StringUtil.isNullOrEmpty(this.tag)) {
			div = new HtmlDiv(paras);
		} else if (!(StringUtil.isNullOrEmpty(this.content)))
			div = new SimpleHtml(this.tag, this.content, paras);
		else {
			div = new SimpleHtml(this.tag, paras);
		}

		if (!(StringUtil.isNullOrEmpty(this.attrs))) {
			HashMap<String, String> sMap = ParameterUtil.convert(this.attrs);
			for (String key : sMap.keySet()) {
				if ("".equals(sMap.get(key)))
					div.addAttr(key, null);
				else {
					div.addAttr(key, (String) sMap.get(key));
				}
			}

		}

		UIDatas datas = new UIDatas();
		for (IUIData d : this.set) {
			datas.addUIData(d);
		}

		layout.toHtml(div, datas, paras, false);

		return div;
	}

	public String getTag() {
		return this.tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public String getAttrs() {
		return this.attrs;
	}

	public void setAttrs(String attrs) {
		this.attrs = attrs;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}
}
