package com.asura.fui.page.view.html;

import com.asura.fui.page.data.FrontData;

public class SimpleHtml extends AbstractHtmlElement {
	private String tag;

	public SimpleHtml(String tag, String content) {
		this.tag = tag;
		setContent(content);
	}

	public SimpleHtml(String tag) {
		this.tag = tag;
	}

	public SimpleHtml(String tag, String content, FrontData paras) {
		super(paras);
		this.tag = tag;
		setContent(content);
	}

	public SimpleHtml(String tag, FrontData paras) {
		super(paras);
		this.tag = tag;
	}

	public String getTag() {
		return this.tag;
	}
}
