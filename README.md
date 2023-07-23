# Methea-Framework
Methea is a framework providing authentication and access management for your `Spring Boot Application`
with minimum configuration, specifically for `RESTful Webservice` with `JWE`. No need to deal with users and permission stuffs.

Methea also provide [UI Management Tool](https://github.com/MetheaX/methea-management) which is allows you to control & configure your system. (Under Developement)

Methea build on top of following frameworks:
1. [Spring Boot](https://spring.io/projects/spring-boot)
2. [JWT](https://jwt.io/)
3. [JWE]()

# Building from sources

Pre-requires
 * Java 17 or above JDK as JAVA_HOME
 * Maven as its build tool.
 * Keystore generator

How to build
 * Simply by checkout source code from our repository and using maven command `mvn clean install` to build framework.

Support with `Spring Boot 3.1.0` and later version of Spring Boot.

# Overview Structure
* Account - refer to company  or organization
* Group - refer to department/team in organization
* JWTConfig - use for config JWT properties, such as expire datetime
* Permission - Bind with roles & resources
* Public Permission - Public resources must map into this table
* Resource - All endpoint in this systems
* Role - User role
* User - User information
# Usages
### I. Integration into webservice application
1.  Adding dependency to your spring boot application

	    <dependency>
            <groupId>io.github.metheax</groupId>
            <artifactId>auth</artifactId>
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
4. Load pre-define data into your database

    ```sql
    INSERT INTO public.core_account (account_id, created_date_time, created_user, status, updated_date_time, updated_user, account_address, account_code, account_email, account_name, account_name_oth) VALUES ('68bcf443-1b0c-49ff-877e-8650477383e8', '2021-06-13 10:57:36.711763', 'System', 'A', '2021-07-10 15:18:42.808053', 'admin', 'Phnom Penh, Cambodia.', 'METHEA', 'methea@localhost.com', 'Methea LLC.', 'មេធា');
    INSERT INTO public.core_group (group_id, created_date_time, created_user, status, updated_date_time, updated_user, group_code, group_name, group_name_oth, remarks, account_id) VALUES ('366a7028-b623-49b0-8988-d711647051a5', '2021-06-13 10:57:36.729797', 'System', 'A', '2021-06-13 10:57:36.729797', 'System', 'M_SYS_ADMIN', 'System Admin', 'អេតមីនប្រព័ន្ធ', null, '68bcf443-1b0c-49ff-877e-8650477383e8');
    INSERT INTO public.core_resource (resource_id, created_date_time, created_user, status, updated_date_time, updated_user, resource_name) VALUES ('ad5ea55c-547a-4537-8797-e3714c64d8a3', '2021-06-13 10:57:36.748761', 'System', 'A', '2021-06-13 10:57:36.748761', 'System', '/**');
    INSERT INTO public.core_role (role_id, created_date_time, created_user, status, updated_date_time, updated_user, role_code, name, name_oth) VALUES ('33525a14-0ebc-4a3e-ada5-bd3ef94c9495', '2021-06-13 10:57:36.742761', 'System', 'A', '2021-06-13 10:57:37.016415', 'System', 'ROLE_ADMIN', 'Admin', 'អេតមីន');
    INSERT INTO public.core_user (user_id, created_date_time, created_user, status, updated_date_time, updated_user, email, first_name, first_name_oth, frc_usr_rst_pwd, identity_code, last_name, last_name_oth, password, phone, username, group_id) VALUES ('c6eff227-d496-486c-a72c-db2f92e06faa', '2021-06-13 10:57:37.015455', 'System', 'A', '2021-06-13 10:57:37.015455', 'System', 'admin@localhost.com', 'Admin', 'អេតមីន', 'N', '639691', 'Admin', 'អេតមីន', 'Gp/lBVv1VTKk3DLGiENtX4Ow1xLEUqIDRBpa+zuTJWT3IIqfC4m0SB1tpVDo1+BQHFnY90XmvbJE1JAMDfRSmA$vkDevdjMM9v+/BonOi6HX/+v3Syh5mrSMXssz5707LSlOrCiIj3O7Q50bg7mLeCl6GYKhad7GGQi7CKvP1KqnbvtS2eZMFevVHsYHsQD2UeMQqiP7nAQ1z12ZHMNc5QckXZgUPMqetTzhJMzDduJ9+nOrC3HlqSo43VoMg0k+EA', '+85569639691', 'admin', '366a7028-b623-49b0-8988-d711647051a5');
    INSERT INTO public.core_user_roles (user_id, role_id) VALUES ('c6eff227-d496-486c-a72c-db2f92e06faa', '33525a14-0ebc-4a3e-ada5-bd3ef94c9495');
    INSERT INTO public.core_permission (permission_id, created_date_time, created_user, status, updated_date_time, updated_user, resource_id, role_id) VALUES ('bdee9930-cbde-4e62-96d8-91df821a698b', '2021-06-13 10:57:36.763762', 'System', 'A', '2021-06-13 10:57:36.763762', 'System', 'ad5ea55c-547a-4537-8797-e3714c64d8a3', '33525a14-0ebc-4a3e-ada5-bd3ef94c9495');
    ```
5. Generate keystore for access token and refresh token
    * We recommend using [KeyStore Exploer](https://keystore-explorer.org/) to generate key store
    * Place keystore under `resources` directory as `resources/keystore`
    * Use below configuration in your `application.properties` or `application.yml`
    ```yaml
   keystore-token-file= classpath:keystore/test.pfx
    keystore-token-password= 12345
    keystore-token-alias= test
    keystore-key-token-password= 12345
    
    keystore-refresh-token-file= classpath:keystore/test.pfx
    keystore-refresh-token-password= 12345
    keystore-refresh-token-alias= test
    keystore-key-refresh-token-password= 12345
    ```

6. Sample project
    * Still have doubt on how to integrate, here is our [sample project](https://github.com/MetheaX/Methea-Sample-Webservice)