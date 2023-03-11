package com.juaracoding.moviedb;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;

public class TestMovieNowPlaying {
    String baseURL = "https://api.themoviedb.org/3/movie";
    String apiKey = "api_key=e1ed789751204e965fb311a3197fae17";
    String apiKeyV4 = "eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJlMWVkNzg5NzUxMjA0ZTk2NWZiMzExYTMxOTdmYWUxNyIsInN1YiI6IjY0MDVmNTUxZTYxZTZkMDA5NjNkMDEzNSIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.q7bcEPM64vs7EWq-9zS1RzzOVvq7K9ffLUFWLEm52VM";

   @Test
   public void GetMovie () {
       String getmovieURL = "https://api.themoviedb.org/3/movie/now_playing?api_key=e1ed789751204e965fb311a3197fae17&language=en-US&page=1";
       Response response = RestAssured.get(getmovieURL);
       System.out.println(response.getBody().asString());
       System.out.println(response.getStatusCode());
       System.out.println(response.getTime());
       System.out.println(response.header("content-type"));
       Assert.assertEquals(response.getStatusCode(), 200);
       System.out.println("Status code : Sesuai ");
   }


       @Test
       public void moviePopular(){
           String moviepopularURL = "https://api.themoviedb.org/3/movie/popular?api_key=e1ed789751204e965fb311a3197fae17&language=en-US&page=1";
           given().get(moviepopularURL).then().statusCode(200).body("results.popularity[0]",equalTo(2550.267F));
}


    @Test
    public void TestMovieRating () {
        String urlMovieRating =baseURL+"/842942/rating?"+apiKey;
        JSONObject requestBody = new JSONObject();
        requestBody.put("value", 8.5);
        System.out.println(requestBody.toJSONString());
        given()
                .header("Authorization", apiKeyV4)
                .header("Content-Type", "application/json")
                .accept(ContentType.JSON)
                .body(requestBody.toJSONString())
                .when()
                .post(urlMovieRating)
                .then()
                .statusCode(401)
                .log().all();
        System.out.println("gagal post");
    }


}




