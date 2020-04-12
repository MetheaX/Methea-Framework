# Methea-Framework
Methea is a framework providing authentication and access management for your `Spring Boot Application`
with minimum configuration. No need to deal with users and permission stuffs.

Methea also provide UI components which is build on top of [FreeMarker](https://freemarker.apache.org/) to increase your development speed.

Methea provide dual intent authentication, which mean you can access from `UI` and `Client Webservice`.

Methea build on top of following frameworks:
1. [Spring Boot](https://spring.io/projects/spring-boot)
2. [JWT](https://jwt.io/)
3. [FreeMarker](https://freemarker.apache.org/)

# Building from sources

Pre-requires
 * Java 11 JDK as JAVA_HOME
 * Maven as its build tool.

How to build
 * Simply by checkout source code from our repository and using maven command `mvn clean install` to build framework.

# Usages

1. Adding dependency to your existing project or new project

	    <dependency>
            <groupId>io.methea</groupId>
            <artifactId>methea-core</artifactId>
            <version>0.0.1-SNAPSHOT</version>
        </dependency>

2. Adding spring-boot components scan

    	 @SpringBootApplication(scanBasePackages = {"io.methea"})
         @EnableJpaRepositories(basePackages = {"io.methea"})
         @EntityScan(basePackages = {"io.methea"})
         @ComponentScan(basePackages = {"io.methea"})
         public class MetheaHostApplication {
            public static void main(String[] args) {
                SpringApplication.run(MetheaHostApplication.class, args);
            }
         }
 3. In resources config environment properties
    - create file `environment.properties` with the configuration
    
    		methea.client.freemarker.templates=your-template-path
            methea.client.security.secret=your-jwt-secret-key
            methea.client.security.token.expiration_time=your-token-expiration
            methea.client.security.token.prefix=Bearer
            methea.client.security.request.header=Authorization
 4. Config datasource of your choice
    * Please note that currently, we testing only with [PostgreSQL](https://www.postgresql.org/)
 5. Sample project
    * Still have doubt here is our [sample project](https://github.com/MetheaX/Methea-Sample-Project)
    * Access token from `client` by using this url `${baseURL}/auth/token` with following payload:
        
          {
            	"username": "admin",
            	"password": "admin"
          }
       
