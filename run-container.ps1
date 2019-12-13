
# Execute the Docker container containing the web application.
# Chris Joakim, Microsoft, 2019/12/13

echo 'docker ps before ...'
docker ps

echo $env:AZURE_REDISCACHE_HOST
echo $env:AZURE_REDISCACHE_KEY

echo 'running container ...'
docker run -d |
    -e AZURE_REDISCACHE_HOST=$env:AZURE_REDISCACHE_HOST |
    -e AZURE_REDISCACHE_KEY=$env:AZURE_REDISCACHE_KEY |
    -p 3000:8080 |
    cjoakim/azure-webapp-wildfly:latest

echo 'docker ps after ...'
docker ps
