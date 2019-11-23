package com.chrisjoakim.azure.webapp;

import java.io.IOException;

import java.io.*;
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
}
