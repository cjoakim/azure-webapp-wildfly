
# Create the Docker container which contains the root.war file.
# Chris Joakim, Microsoft, 2019/12/13

cd docker-dir

echo 'building ...'
docker build -t cjoakim/azure-webapp-wildfly .

echo 'listing images ...'
docker images

cd ..
