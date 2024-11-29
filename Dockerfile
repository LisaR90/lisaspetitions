FROM tomcat:latest

# Clean default webapps
RUN rm -rf /usr/local/tomcat/webapps/*

# Copy WAR file into Tomcat webapps directory
COPY target/lisaspetitions.war /usr/local/tomcat/webapps/lisaspetitions.war

EXPOSE 9090

CMD ["catalina.sh", "run"]