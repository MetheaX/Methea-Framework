# Methea-Framework
Methea is a framework providing authentication and access management for your `Spring Boot Application`
with minimum configuration. No need to deal with users and permission stuffs.

Methea also provide [UI Management Tool](https://github.com/MetheaX/methea-management) which is allows you to control & configure your system.

Methea build on top of following frameworks:
1. [Spring Boot](https://spring.io/projects/spring-boot)
2. [JWT](https://jwt.io/)

# Building from sources

Pre-requires
 * Java 8 or above JDK as JAVA_HOME
 * Maven as its build tool.

How to build
 * Simply by checkout source code from our repository and using maven command `mvn clean install` to build framework.

Support with `Spring Boot 2.2.2.Release` and later version of Spring Boot.

# Usages
### I. Integration into webservice application
1.  Adding dependency to your spring boot application

	    <dependency>
            <groupId>io.github.metheax</groupId>
            <artifactId>api</artifactId>
            <version>0.0.1-SNAPSHOT</version>
        </dependency>

2. Config datasource of your choice
    * Please note that currently, we're testing only with [PostgreSQL](https://www.postgresql.org/)
        
3. Adding spring-boot components scan

    	 @SpringBootApplication(scanBasePackages = {"io.github.metheax", "your-reverse-domain"})
         @EnableJpaRepositories(basePackages = {"io.github.metheax", "your-reverse-domain"})
         @EntityScan(basePackages = {"io.github.metheax", "your-reverse-domain"})
         public class MetheaHostApplication {
            public static void main(String[] args) {
                SpringApplication.run(WebServiceApplication.class, args);
            }
         }`
   
4. Sample project
    * Still have doubt on how to integrate, here is our [sample project](https://github.com/MetheaX/Methea-Sample-Webservice)