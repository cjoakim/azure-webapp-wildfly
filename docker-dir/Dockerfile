FROM jboss/wildfly
# FROM jboss/wildfly:10.1.0.Final
# both of the above "FROM" base-images work with this app - cjoakim 2020/01/03

COPY deployments/root.war /opt/jboss/wildfly/standalone/deployments/root.war

# docker build -t cjoakim/azure-webapp-wildfly .
# docker run -d -e xxx=yyy -p 8080:8080 cjoakim/azure-webapp-wildfly:latest
# Invoke http://localhost:8080/
