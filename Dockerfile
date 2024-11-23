# Use latest Tomcat
FROM tomcat:latest

RUN mv /usr/local/tomcat/webapps /usr/local/tomcat/webapps2
RUN mv /usr/local/tomcat/webapps.dist /usr/local/tomcat/webapps

# Copy WAR file into the Tomcat webapps directory
ADD target/*.war /usr/local/tomcat/webapps/

# Expose Tomcat port
EXPOSE 9090

# Start Tomcat server
CMD ["catalina.sh", "run"]
