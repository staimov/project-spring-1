FROM tomcat:10-jdk17-openjdk

COPY /target/root.war /usr/local/tomcat/webapps/
