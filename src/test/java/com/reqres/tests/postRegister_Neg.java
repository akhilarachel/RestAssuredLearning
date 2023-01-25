package com.reqres.tests;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.FileWriter;

public class postRegister_Neg extends baseClass{
    @Test
    public void postRegisterError(){
        try{
            logger = report.createTest("Successful  Request to register a new user");
            RequestSpecification httpReq = RestAssured.given();

            JSONObject reqParameters = new JSONObject();
            reqParameters.put("email","eve.holt@reqres.in");

            httpReq.header("Content-Type","application/json");
            httpReq.body(reqParameters.toJSONString());

            //POST request
            Response res = httpReq.request(Method.POST,"/register");
            String responseBody = res.getBody().asString();
            //JSON Response Files
            FileWriter myWriter = new FileWriter(System.getProperty("user.dir")+"/JSON Response Files/postReg400Response_"+randomFilename+".json");
            myWriter.write(responseBody);
            myWriter.close();
            logger.pass("Request successful with 400 code");
            Assert.assertEquals(res.getStatusCode(),400);
            Assert.assertEquals(res.getStatusLine(), "HTTP/1.1 400 Bad Request");
        } catch (Exception e){
            System.out.println("Write to the file failed: " +e.getMessage());
        }
    }
}
