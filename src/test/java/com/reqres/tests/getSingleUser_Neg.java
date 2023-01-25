package com.reqres.tests;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.FileWriter;

public class getSingleUser_Neg extends baseClass{
    @Test
    public void getSingleNeg(){
        try{
            logger = report.createTest("Request to get user not found error");
            RequestSpecification httpReq = RestAssured.given();

            //GET request
            Response res = httpReq.request(Method.GET,"/users/0");
            String responseBody = res.getBody().asString();
            //JSON Response Files
            FileWriter myWriter = new FileWriter(System.getProperty("user.dir")+"/JSON Response Files/getSingleUser404Response_"+randomFilename+".json");
            myWriter.write(responseBody);
            myWriter.close();
            logger.pass("Request successful with 404 code");
            Assert.assertEquals(res.getStatusCode(),404);
            Assert.assertEquals(res.getStatusLine(), "HTTP/1.1 404 Not Found");
        } catch (Exception e){
            System.out.println("Write to the file failed: " +e.getMessage());
        }
    }
}
