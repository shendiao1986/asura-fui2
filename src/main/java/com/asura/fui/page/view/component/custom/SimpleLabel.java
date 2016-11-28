package com.asura.fui.page.view.component.custom;

import com.asura.fui.page.data.FrontData;
import com.asura.fui.page.view.component.AbstractUIComponent;
import com.asura.fui.page.view.component.data.DataLabel;
import com.asura.fui.page.view.component.data.IUIData;
import com.asura.fui.page.view.component.layout.IUILayout;
import com.asura.fui.page.view.html.HtmlDiv;
import com.asura.fui.page.view.html.HtmlSpan;
import com.asura.fui.page.view.html.IHtmlElement;

public class SimpleLabel extends AbstractUIComponent {
	private boolean bold;
	private boolean twoline;

	public IHtmlElement toHtml(IUIData data, IUILayout layout, FrontData paras) {
		DataLabel la = (DataLabel) data;

		HtmlDiv div = new HtmlDiv(paras);

		HtmlSpan span = new HtmlSpan(paras);
		span.setContent(la.getLabel());
		span.addStyle("float", "left");
		span.addStyle("overflow", "hidden");
		span.addStyle("white-space", "nowrap");
		if (this.twoline)
			span.addStyle("width", "100%");
		else {
			span.addStyle("width", "20%");
		}
		if (this.bold) {
			span.addStyle("font-weight", "bold");
		}
		div.addChild(span);

		if (!(this.twoline)) {
			span = new HtmlSpan(paras);
			span.setContent(" : ");
			span.addStyle("float", "left");
			span.addStyle("overflow", "hidden");
			span.addStyle("white-space", "nowrap");
			span.addStyle("margin-left", "2%");
			span.addStyle("margin-right", "1%");
			span.addStyle("width", "3%");
			if (this.bold) {
				span.addStyle("font-weight", "bold");
			}
			div.addChild(span);
		}

		span = new HtmlSpan(paras);
		span.setContent(la.getText());
		span.addStyle("float", "left");
		span.addStyle("overflow", "hidden");
		if (this.twoline)
			span.addStyle("width", "100%");
		else {
			span.addStyle("width", "74%");
		}
		span.addStyle("white-space", "nowrap");
		div.addChild(span);

		return div;
	}
}
