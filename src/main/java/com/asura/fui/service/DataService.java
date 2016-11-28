package com.asura.fui.service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.asura.fui.data.DataProviderFactory;
import com.asura.fui.data.IDataProvider;
import com.asura.fui.page.data.DataConverter;
import com.asura.fui.page.data.FrontData;
import com.asura.fui.util.JsonUtil;
import com.asura.fui.util.ServiceUtil;

public class DataService extends HttpServlet {
	private static final long serialVersionUID = -5164872685520450152L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("utf8");

		FrontData data = DataConverter.fromParameter(request);

		String category = data.getValueString("cat");
		String key = data.getValueString("key");

		System.out.println(data);

		IDataProvider dp = DataProviderFactory.instance().getProvider(category, key);

		if (dp != null)
			ServiceUtil.output(JsonUtil.toJson(dp.build(data)), response);
		else
			ServiceUtil.output("", response);
	}
}
