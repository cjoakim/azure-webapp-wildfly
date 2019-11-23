package com.chrisjoakim.azure.webapp;

import java.io.IOException;

import java.io.*;
import java.text.*;
import java.util.*;

import com.fasterxml.jackson.databind.ObjectMapper;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisShardInfo;

public class RedisServlet extends javax.servlet.http.HttpServlet {

    protected void doGet(
        javax.servlet.http.HttpServletRequest request,
        javax.servlet.http.HttpServletResponse response) 
        throws javax.servlet.ServletException, IOException {

        HashMap<String, Object> map = new HashMap<String, Object>();
        ObjectMapper mapper = new ObjectMapper();
        map.put("operation", "get");

        String key = request.getParameter("key");
        if (key != null) {
            map.put("key", key);
            map.put("operation", "get");
            Jedis jedis = getJedis();
            String value = jedis.get(key);
            map.put("value", value);
        }
        else {
            map.put("error", "no key parameter provided");
        }

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

    private Jedis getJedis() {

        boolean useSsl    = false;  // port 6379 enabled for now
        String  cacheHost = System.getenv("AZURE_REDISCACHE_HOST");
        String  cacheKey  = System.getenv("AZURE_REDISCACHE_KEY");

        JedisShardInfo shardInfo = new JedisShardInfo(cacheHost, 6379, useSsl);
        shardInfo.setPassword(cacheKey);
        return new Jedis(shardInfo); 
    }

}
