package com.reqres.tests;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.FileWriter;

public class getUserList_Pos extends baseClass{

    @Test
    void getAllUserList(){
        try{
            logger = report.createTest("Request to get list of users");
            RequestSpecification httpReq = RestAssured.given();

            //GET request
            Response res = httpReq.request(Method.GET,"/users?1");
            String responseBody = res.getBody().asString();
            //System.out.println("Response is " +responseBody);
            //JSON Response Files
            FileWriter myWriter = new FileWriter(System.getProperty("user.dir")+"/JSON Response Files/getUserListResponse_"+randomFilename+".json");
            myWriter.write(responseBody);
            myWriter.close();
            logger.pass("Request successful");
            Assert.assertEquals(res.getStatusCode(),200);
            Assert.assertEquals(res.getStatusLine(), "HTTP/1.1 200 OK");
        } catch (Exception e){
            System.out.println("Write to the file failed: " +e.getMessage());
        }

    }
}
