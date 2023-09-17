FROM tomcat:10-jdk17-temurin

COPY /target/root.war /usr/local/tomcat/webapps/
