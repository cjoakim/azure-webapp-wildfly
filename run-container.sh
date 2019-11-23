#!/bin/bash

# Execute the Docker container containing the web application.
# Chris Joakim, Microsoft, 2019/11/23

echo 'docker ps before ...'
docker ps

echo 'running container ...'
docker run -d -e xxx=yyy -p 3000:8080 cjoakim/azure-webapp-wildfly:latest

echo 'docker ps after ...'
docker ps
