package com.asura.fui.page.view.html;

import com.asura.fui.page.data.FrontData;

public class HtmlSpan extends AbstractHtmlElement {
	public HtmlSpan() {
	}

	public HtmlSpan(FrontData paras) {
		super(paras);
	}

	public HtmlSpan(String text, FrontData paras) {
		super(paras);
		setContent(text);
	}

	public String getTag() {
		return "span";
	}
}
