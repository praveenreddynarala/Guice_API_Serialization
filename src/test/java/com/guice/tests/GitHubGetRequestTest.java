package com.guice.tests;

import com.google.gson.reflect.TypeToken;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.gson.pojo.GitHubRepos;
import com.restapi.guicemodule.PropertiesModule;
import com.restapi.guicemodule.RequestModule;
import com.restapi.guicemodule.SerializationModule;
import com.restapi.properties.RequestProperties;
import com.restapi.requests.GitHubGetRequest;
import com.restapi.serialization.Deserialize;
import io.restassured.response.Response;
import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.List;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class GitHubGetRequestTest {

    private Injector injector;
    private RequestProperties requestProperties;
    private GitHubGetRequest request;
    private Deserialize deserialize;

    @BeforeAll
    void loadDI(){
        requestProperties = new RequestProperties();
        injector = Guice.createInjector(new RequestModule(), new PropertiesModule(), new SerializationModule());
        request = injector.getInstance(GitHubGetRequest.class);

    }

    @BeforeEach
    void setProperties(){
        requestProperties.setBaseUrl(request.getBaseUrl());
        requestProperties.setReqHeaders(request.getContentType() + "," + request.getToken());
        deserialize = injector.getInstance(Deserialize.class);
    }

    @Test
    public void getAllGitHubRepos() {
        requestProperties.setUrlEndPoint("user/repos");
        Response response = request.getRequest(requestProperties);
        System.out.println(response.getStatusCode());
        System.out.println(response.body().prettyPrint());
        Assertions.assertTrue(response.getStatusCode()==200, "Response Status Code :: "+response.getStatusCode());

        List<GitHubRepos> listGitHubRepos = deserialize.tokenTypeDeserialization(
                response.getBody().asString(),
                new TypeToken<ArrayList<GitHubRepos>>() {
                },
                true,
                false
        );

        listGitHubRepos.stream().forEach(
                c -> System.out.println("ID :: "+c.getId()+"Name :: "+c.getName()+"Repo Url :: "+c.getHtml_url())
        );
    }

    @Test
    public void getGitHubRepo(){

        GitHubRepos repos = new GitHubRepos();
        repos = deserialize.parseJSON(GitHubRepos.class, "GitHub_Repo_Root_List_Response.json");

        requestProperties.setUrlEndPoint("repos/praveenreddynarala/Hello-World");
        Response response = request.getRequest(requestProperties);
        System.out.println(response.getStatusCode());
        System.out.println(response.body().prettyPrint());
        Assertions.assertTrue(response.getStatusCode()==200, "Response Status Code :: "+response.getStatusCode());


        GitHubRepos githubDR = deserialize.deserializeResponse(
                response.getBody().asString(),
                GitHubRepos.class
        );

        Assertions.assertTrue(repos.getFull_name().equals(githubDR.getFull_name()));
    }

}
