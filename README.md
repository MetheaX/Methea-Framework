# Methea-Framework
Methea is a framework providing authentication and access management for your `Spring Boot Application`
with minimum configuration. No need to deal with users and permission stuffs.

Methea also provide UI components which is build on top of [FreeMarker](https://freemarker.apache.org/) to increase your development speed.

Methea build on top of following frameworks:
1. [Spring Boot](https://spring.io/projects/spring-boot)
2. [JWT](https://jwt.io/)
3. [FreeMarker](https://freemarker.apache.org/)
4. [CoreUI](https://coreui.io/)

# Building from sources

Pre-requires
 * Java 11 JDK as JAVA_HOME
 * Maven as its build tool.

How to build
 * Simply by checkout source code from our repository and using maven command `mvn clean install` to build framework.

Support with `Spring Boot 2.2.2.Release` and later version of Spring Boot.

# Usages

### I. Integration as web application

1. Adding dependency to spring boot application

	    <dependency>
            <groupId>io.methea</groupId>
            <artifactId>web-ui</artifactId>
            <version>0.0.1-SNAPSHOT</version>
        </dependency>

2. Adding spring-boot components scan

    	 @SpringBootApplication(scanBasePackages = {"io.methea", "your-reverse-domain"})
         @EnableJpaRepositories(basePackages = {"io.methea", "your-reverse-domain"})
         @EntityScan(basePackages = {"io.methea", "your-reverse-domain"})
         public class MetheaHostApplication {
            public static void main(String[] args) {
                SpringApplication.run(MetheaHostApplication.class, args);
            }
         }
 3. In resources config environment properties
    - create file `environment.properties` with the configuration
    
    		methea.client.freemarker.templates=your-template-path
    		
 4. Config datasource of your choice
    * Please note that currently, we testing only with [PostgreSQL](https://www.postgresql.org/)
 5. Sample project
    * Still have doubt here is our [sample project](https://github.com/MetheaX/Methea-Sample-Project)
### II. Integration as webservice application
1.  Adding dependency to your spring boot application

	    <dependency>
            <groupId>io.methea</groupId>
            <artifactId>api</artifactId>
            <version>0.0.1-SNAPSHOT</version>
        </dependency>
        
2. Adding spring-boot components scan

    	 @SpringBootApplication(scanBasePackages = {"io.methea", "your-reverse-domain"})
         @EnableJpaRepositories(basePackages = {"io.methea", "your-reverse-domain"})
         @EntityScan(basePackages = {"io.methea", "your-reverse-domain"})
         public class MetheaHostApplication {
            public static void main(String[] args) {
                SpringApplication.run(WebServiceApplication.class, args);
            }
         }
3. Deployment methea web management (Optional)
   
   Methea web management allow you to manage users, permission, roles, groups, URI... in webservice application.
