# Test Automation Framework - Google Guice, Gson, Rest Assured and Junit 5

## Introduction

This project gives good understanding on how one can use Google Guice library in the test framework in order to implement Dependency Injection pattern.
“The DI principle is a technique that is used to remove internal dependencies from the implementation by enabling these dependencies to be injected externally. It states that when an object is dependent on other objects, such objects should be created using a separate framework or component.”
Also, We will learn the “De-Serialization” concept where we will create Java Object from JSON Objects using the Gson library. And do assertions using Junit 5 framework.

## What is Google Guice?

As per Wikipedia, Google Guice (pronounced "juice") is an open-source software framework for the Java platform released by Google under the Apache License. It provides support for dependency injection using annotations to configure Java objects. Dependency injection is a design pattern whose core principle is to separate behavior from dependency resolution.

Guice allows implementation classes to be bound programmatically to an interface, then injected into constructors, methods or fields using an @Inject annotation. When more than one implementation of the same interface is needed, the user can create custom annotations that identify an implementation, then use that annotation when injecting it.

## What is Gson?

As per Gson official document, Gson is a Java library that can be used to convert Java Objects into their JSON representation. It can also be used to convert a JSON string to an equivalent Java object. Gson can work with arbitrary Java objects including pre-existing objects that you do not have source-code of.

## System Requirements

1. JDK1.8.XYZ
2. Maven
3. Google Guice
4. Gson
5. Junit 5
6. Rest Assured

**Please refer POM.xml** - [GitHub](https://github.com/praveenreddynarala/Guice_API_Serialization/blob/main/pom.xml)

## Steps to implement Google Guice

> Step 1

In this project, I have created one simple Interface to call all different HTTPS methods. 

        @ImplementedBy(RequestBuilder.class)
        public interface Client {

            void setRequestProperties(RequestProperties requestProperties);
            RequestSpecification getResponseSpecification();
            Response getRequest();
            Response postRequest();
        }

> Step 2

Created one Builder implementation class to override all methods from Interface

        public class RequestBuilder implements Client {}
        
**Example Implementation/Builder Class** - [GitHub](https://github.com/praveenreddynarala/Guice_API_Serialization/blob/main/src/main/java/com/restapi/builder/RequestBuilder.java)

> Step 3

Created RequestModule class by extending AbstractModule class from com.google.inject to bind classes

        public class RequestModule extends AbstractModule {

            @Override
            public void configure(){
                bind(String.class)
                        .annotatedWith(Names.named("Server Url"))
                .toInstance("https://reqres.in");
                bind(Client.class).to(RequestBuilder.class);
            }

        }

**Example Implementation/Builder Class** - [GitHub](https://github.com/praveenreddynarala/Guice_API_Serialization/blob/main/src/main/java/com/restapi/guicemodule/RequestModule.java)

> Step 4

Created dependency classes to call bindings using Interface.

        public class GetRequest {

            private Client client;

            @Inject
            public GetRequest(Client client) {
                this.client = client;
            }
        }

**Example Implementation** - [GitHub](https://github.com/praveenreddynarala/Guice_API_Serialization/blob/main/src/main/java/com/restapi/requests/GetRequest.java)

> Step 5

Created one Test Class using Junit 5. And created Injector to create an Object for binded class

        @BeforeEach
        public void beforeTest(){
            requestProperties = new RequestProperties();

*Creating injector for Module classes using Google Guice*    
            Injector injector = Guice.createInjector(new RequestModule());  

*Using Injector, creating object for Binded class*
            request = injector.getInstance(GetRequest.class); 
        }

**Example Implementation** - [GitHub](https://github.com/praveenreddynarala/Guice_API_Serialization/blob/main/src/test/java/com/guice/tests/GetRequestTests.java)


**Reading Data Properties file in Guice**

> Step 1 - Sample Properties file

        base_url=https://reqres.in
        content_type=Content-Type:application/json

**Example properties file** - [GitHub](https://github.com/praveenreddynarala/Guice_API_Serialization/blob/main/src/main/resources/Application.properties)

> Step 2 - Reading data in Google Guice module class

        public class PropertiesModule extends AbstractModule {

            @Override
            protected void configure(){
                Names.bindProperties(binder(), loadProperties("GitHub.properties"));

            }

        }

**Example module class** - [GitHub](https://github.com/praveenreddynarala/Guice_API_Serialization/blob/main/src/main/java/com/restapi/guicemodule/PropertiesModule.java)

> Step 2 - Inject properties in Binded class (@constructor using @Named. This is called Constructor Injection) to load values at run time using Getters like below.

         @Inject
        public PostRequest(
                Client client,
                @Named("base_url") String baseUrl,
                @Named("content_type") String content_type
        ){
            this.client = client;
            this.baseUrl = baseUrl;
            this.contentType = content_type;
        }

        public String getBaseUrl(){return this.baseUrl;}
        public String getContentType(){return this.contentType;}

**Example properties Injection class** - [GitHub](https://github.com/praveenreddynarala/Guice_API_Serialization/blob/main/src/main/java/com/restapi/requests/PostRequest.java)

> Step 3 - Calling these properties in Test files like below

        @BeforeAll
        void loadDI(){
            requestProperties = new RequestProperties();
            Injector injector = Guice.createInjector(new RequestModule(), new PropertiesModule());
            request = injector.getInstance(PostRequest.class);
*Reading values from PostRequest binding class*
            requestProperties.setBaseUrl(request.getBaseUrl());
            requestProperties.setReqHeaders(request.getContentType());
        }

**Field Level Injections**

Injection is a process of injecting dependency into an object. Field injection is used to set value object as dependency to the field of an object. Observe the example given below.

> Step 1 - Created binding for String class to inject "Server Url" as Named binding in Module class.

        @Override
        public void configure(){
*Named Binding*
            bind(String.class)
                    .annotatedWith(Names.named("Server Url"))
            .toInstance("https://reqres.in");
            bind(Client.class).to(RequestBuilder.class);
        }

> Step 2 - Inject Named field

        public class RequestBuilder implements Client {

            @Inject(optional=true) @Named("Server Url")
            private String serverUrl = "";

        }

*Note* - Had mentioned field as Optional. We can read from Module class or set directly
**Example properties Injection class** - [GitHub](https://github.com/praveenreddynarala/Guice_API_Serialization/blob/main/src/main/java/com/restapi/builder/RequestBuilder.java)

## Response Deserialization using Gson

> Step 1 - Create one POJO class to bind expected response

        package com.gson.pojo;

        public class GitHubRepos {

            private Integer id;
            private String node_id;
            private String name;
            private String full_name;
            private String html_url;
            private String description;
            private Integer subscribers_count;

            public Integer getId() {
                return id;
            }

            public void setId(Integer id) {
                this.id = id;
            }
            
        }

**Complete Example** - [GitHub](https://github.com/praveenreddynarala/Guice_API_Serialization/blob/main/src/main/java/com/gson/pojo/GitHubRepos.java)

> Step 2 - Create Gson object

        Gson gsonObj = new GsonBuilder().serializeNulls().create();
*serializeNulls()* - is used to parse null values (if any). Hence, I used GsonBuilder class. Otherwise, we can directly create object for Gson class using Gson gsonObj = new Gson();

> Step 3 - Make a Get Request call to GitHub using Rest Assured and parse the response body using **gsonObj.fromJson(responseBudy, POJO.class)**

    POJOClass pojoClsObj = gsonObj.fromJson(ResponseBodu, POJO.class);

*By using above statement convert JSON string to POJO class objects*

**Example** - [GitHub](https://github.com/praveenreddynarala/Guice_API_Serialization/blob/main/src/test/java/com/gson/tests/GsonArrays.java)

        private static void deserialization(){
            Users usersObj = new Gson().fromJson(getResponse(), Users.class);
            usersObj.getData().stream().forEach((c) -> System.out.println("ID :: "+c.getId()+" Avatar :: "+c.getAvatar()
                    +" Email :: "+c.getEmail()+ " First Name :: "+c.getFirst_name()+"  Last Name :: "+c.getLast_name()));
        }

> Exclude Fields Without Expose Annotation 

        private static void deserialization() {
            String jsonString = "{\"name\":\"Praveen\",\"lastName\":\"Narala\",\"age\":null,\"isDeveloper\":false}";

            ExposeUserProperties users = new GsonBuilder().excludeFieldsWithoutExposeAnnotation()
                    .create().fromJson(jsonString, ExposeUserProperties.class);
            System.out.println(users);
        }
**Example** - [GitHub](https://github.com/praveenreddynarala/Guice_API_Serialization/blob/main/src/test/java/com/gson/tests/ExposeJavaPropertiesTest.java)

*POJO Class*

        /**
        *Expose annotation will help to expose properties in DataSerialization and Deserialization
        * Expose annotation will replace setting properties to null values for hiding
        */
        public class ExposeUserProperties {

*@Expose - will display property value in both Serialization and deserialiazation*
            @Expose()
            private String name;

*Expose lastName property in Serialization but not in deserialization*
            @Expose(serialize = true, deserialize = false)
            private String lastName;

*Does not expose age property in Serialization but exposes in deserialization*
            @Expose(serialize = false, deserialize = true)
            private Integer age;

*Does not expose isDeveloper property in both Serialization and Deserialization*
            @Expose(serialize = false, deserialize = false)
            private Boolean isDevelopr;
 
            public ExposeUserProperties(String name, String lastName, Integer age, Boolean isDevelopr){
                this.name = name;
                this.lastName = lastName;
                this.age = age;
                this.isDevelopr = isDevelopr;
            }
        }
**Example** - [GitHub](https://github.com/praveenreddynarala/Guice_API_Serialization/blob/main/src/main/java/com/expose/pojo/ExposeUserProperties.java)

## Serialization using Gson

> Parsing Null Values

        private static void serialization() {
            Users users = new Users(
                    "Praveen",
                    "Narala",
                    null
            );

            Gson gson = new GsonBuilder().serializeNulls().create();
            String jsonString = gson.toJson(users);
        }

**Example** - [GitHub](https://github.com/praveenreddynarala/Guice_API_Serialization/blob/main/src/test/java/com/gson/tests/ParsingNullValuesInJSON.java)

> Exclude Fields Without Expose Annotation 
*Expose annotation will help to expose properties in DataSerialization and Deserialization. Expose annotation will replace setting properties to null values for hiding*

        ExposeUserProperties users = new ExposeUserProperties(
                "Praveen",
                "Narala",
                35,
                true
        );

        String jsonString = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create().toJson(users);
        System.out.println(jsonString);


**Different Serialization and Deserialization Examples**

> Please refer - [GitHub](https://github.com/praveenreddynarala/Guice_API_Serialization/tree/main/src/test/java/com/gson/tests)


## Sample Project for cloning

> Tests - [GitHub](https://github.com/praveenreddynarala/Guice_API_Serialization/tree/main/src/test/java/com/guice/tests)

> Utilities - [GitHub](https://github.com/praveenreddynarala/Guice_API_Serialization/tree/main/src/main/java/com/restapi)



