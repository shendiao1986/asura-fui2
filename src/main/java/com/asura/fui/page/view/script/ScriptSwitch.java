package com.asura.fui.page.view.script;

import java.util.ArrayList;
import java.util.List;

import com.asura.fui.page.data.FrontData;
import com.asura.tools.util.StringUtil;

public class ScriptSwitch implements IUIScript {

	private List<ScriptCondition> conditions;

	@Override
	public String toScript(FrontData paras) {
		List<String> list = new ArrayList<>();
		int i = 0;
		for (ScriptCondition con : this.conditions) {
			if (i == 0) {
				list.add("if(" + con.getCon() + "){\n" + con.toScript(paras) + "\n}");
			} else {
				list.add("(" + con.getCon() + "){\n" + con.toScript(paras) + "\n}");
			}
			++i;
		}

		return StringUtil.getStringFromStrings(list, " else if ");
	}

}
