package com.reqres.tests;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.FileWriter;

public class postUserCreation_Pos extends baseClass{

    @Test
    public void newUserCreation(){
        try{
            logger = report.createTest("Success Request to create a new user");
            RequestSpecification httpReq = RestAssured.given();

            JSONObject reqParameters = new JSONObject();
            reqParameters.put("name","morpheus");
            reqParameters.put("job","leader");

            httpReq.header("Content-Type","application/json");
            httpReq.body(reqParameters.toJSONString());

            //POST request
            Response res = httpReq.request(Method.POST,"/user");
            String responseBody = res.getBody().asString();
            //JSON Response Files
            FileWriter myWriter = new FileWriter(System.getProperty("user.dir")+"/JSON Response Files/postNewUserResponse_"+randomFilename+".json");
            myWriter.write(responseBody);
            myWriter.close();
            logger.pass("Request successful with 201 code");
            Assert.assertEquals(res.getStatusCode(),201);
            Assert.assertEquals(res.getStatusLine(), "HTTP/1.1 201 Created");
        } catch (Exception e){
            System.out.println("Write to the file failed: " +e.getMessage());
        }
    }
}
