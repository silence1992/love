package com.lhx.service;
import java.io.IOException;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.OutputStream;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

public class PicServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("iamge/jpeg");
		BufferedImage image = new BufferedImage(50,20,BufferedImage.TYPE_INT_RGB);
		Random r = new Random();
		Graphics g = image.getGraphics();
		OutputStream out = response.getOutputStream();
		g.setColor(new Color(r.nextInt(255),r.nextInt(255),r.nextInt(255)));
		g.fillRect(0, 0, 50, 20);
		g.setColor(new Color(0,0,0));
		String number  = String.valueOf(r.nextInt(99999));
		HttpSession session = request.getSession();
		session.setAttribute("checkcode", number);
		g.drawString(number, 5, 15);
		g.setColor(new Color(r.nextInt(255),r.nextInt(255),r.nextInt(255)));
		g.drawLine(r.nextInt(10), r.nextInt(20), r.nextInt(20)+30, r.nextInt(20));
		JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
		encoder.encode(image);		
	}
}
