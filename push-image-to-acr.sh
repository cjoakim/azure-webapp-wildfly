#!/bin/bash

# Tag and Push the Docker image to Azure Container Registry (ACR)
# Chris Joakim, Microsoft, 2019/11/23

# You may have to login first, with the following command:
# az acr login --name cjoakimacr

echo 'listing current contents of the registry ...'
az acr repository list --name cjoakimacr --output table

echo 'tagging the image ...'
docker tag cjoakim/azure-webapp-wildfly:latest cjoakimacr.azurecr.io/azure-webapp-wildfly:latest

echo 'pushing the image to ACR ...'
docker push cjoakimacr.azurecr.io/azure-webapp-wildfly:latest

echo 'listing current contents of the registry ...'
az acr repository list --name cjoakimacr --output table
