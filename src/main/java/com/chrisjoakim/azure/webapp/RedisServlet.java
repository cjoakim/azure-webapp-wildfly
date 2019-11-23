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
        populateDateTimeAttributes(map);

        String key = request.getParameter("key");
        if (key != null) {
            map.put("key", key);
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

            HashMap<String, Object> map = new HashMap<String, Object>();
            ObjectMapper mapper = new ObjectMapper();
            map.put("operation", "set");
            populateDateTimeAttributes(map);
    
            String key   = request.getParameter("key");
            String value = request.getParameter("value");
            if ((key != null) && (value != null)) {
                map.put("key", key);
                map.put("value", value);
                Jedis jedis = getJedis();
                String result = jedis.set(key, value);
                map.put("result", result);
            }
            else {
                map.put("error", "no key and/or value parameter provided");
            }
    
            response.setContentType("application/json");
            PrintWriter out = response.getWriter();
            out.print(mapper.writeValueAsString(map));
    }

    private Jedis getJedis() {

        boolean useSsl    = false;  // port 6379 enabled for now
        String  cacheHost = System.getenv("AZURE_REDISCACHE_HOST");
        String  cacheKey  = System.getenv("AZURE_REDISCACHE_KEY");

        JedisShardInfo shardInfo = new JedisShardInfo(cacheHost, 6379, useSsl);
        shardInfo.setPassword(cacheKey);
        return new Jedis(shardInfo); 
    }

    private void populateDateTimeAttributes(HashMap<String, Object> map) {

        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        long epoch = date.getTime();
        map.put("date", dateFormat.format(date));
        map.put("epoch", epoch);  
    }

}

// curl "http://localhost:3000/redis?key=cat" | jq 
// curl -X POST -d 'key=cat&value=elsa' 'http://localhost:3000/redis' | jq
