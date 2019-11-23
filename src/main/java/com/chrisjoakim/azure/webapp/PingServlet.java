package com.chrisjoakim.azure.webapp;

import java.io.IOException;

import java.io.*;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.text.*;
import java.util.*;

import com.fasterxml.jackson.databind.ObjectMapper;

public class PingServlet extends javax.servlet.http.HttpServlet {

    protected void doGet(
        javax.servlet.http.HttpServletRequest request,
        javax.servlet.http.HttpServletResponse response) 
        throws javax.servlet.ServletException, IOException {

        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        long epoch = date.getTime();
    
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("date", dateFormat.format(date));
        map.put("epoch", epoch);
        map.put("build_user", getResourceText("build_user.txt"));
        map.put("build_date", getResourceText("build_date.txt"));
        map.put("redis_host", "" + System.getenv("AZURE_REDISCACHE_HOST"));

        ObjectMapper mapper = new ObjectMapper();
        response.setContentType("application/json");
		PrintWriter out = response.getWriter();
		out.print(mapper.writeValueAsString(map));
    }

    protected void doPost(
        javax.servlet.http.HttpServletRequest request, 
        javax.servlet.http.HttpServletResponse response) 
        throws javax.servlet.ServletException, IOException {

        this.doGet(request, response);
    }

	private String getResourceText(String resourceName) {
		
		try {
            InputStream is = this.getClass().getClassLoader().getResourceAsStream(resourceName);
			InputStreamReader sr = new InputStreamReader(is, "UTF-8");
			BufferedReader reader = new BufferedReader(sr);
			String line = "";
			StringBuffer sb = new StringBuffer();
			while (line != null) {
				line = reader.readLine();
				if (line != null) {
					sb.append(line);
				}
			}
			return sb.toString().trim();
		}
		catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}
}
