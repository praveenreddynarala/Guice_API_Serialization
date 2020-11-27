package com.gson.tests;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.gson.pojo.GitHubRepos;
import io.restassured.RestAssured;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class TypeTokenTests {

    public static void main(String[] arg) {
        deserialization();
    }

    private static void deserialization() {
        //GitHubRepos[] githubRepos = new Gson().fromJson(getResponse(), GitHubRepos[].class);
        Type githubRepo = new TypeToken<ArrayList<GitHubRepos>>(){}.getType(); //If the root element is List [], should use TypeToken
        List<GitHubRepos> listGitHubRepos = new Gson().fromJson(getResponse(), githubRepo);
        listGitHubRepos.stream().forEach(
                c -> System.out.println("ID :: "+c.getId()+"Name :: "+c.getName()+"Repo Url :: "+c.getHtml_url())
        );
    }

    private static String getResponse() {
        return RestAssured.given()
                .contentType("application/json")
                .auth()
                .oauth2("72cb4c2b5160a3a925b596f26bcba561a6fae987")
                .get("https://api.github.com/user/repos")
                .then()
                .extract()
                .response().body().asString();
    }

}
