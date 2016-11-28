package com.asura.fui.service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.asura.fui.page.data.DataConverter;
import com.asura.fui.post.IPostHandler;
import com.asura.fui.post.PostHandlerFactory;

public class PostService extends HttpServlet {
	private static final long serialVersionUID = -5164872685520450152L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");

		request.getSession().setAttribute("aaa", "bbb");

		System.out.println("post service last:" + request.getHeader("Referer"));

		String postId = request.getParameter("post_id");

		for (IPostHandler handler : PostHandlerFactory.instance().getPostHandlers()) {
			if (handler.canHandle(postId)) {
				System.out.println("post id:" + postId);
				System.out.println("post handler:" + handler);
				System.out.println("post paras:" + DataConverter.fromParameter(request));

				String re = handler.redirect(request, response);

				response.sendRedirect(re);
			}
		}
	}
}
