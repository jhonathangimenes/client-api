package org.oda.resource;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

@QuarkusTest
public class UserResourceTestRepository {

    @Test
    void createSuccessEndpoint() {
        given().when().post("/clients").then().statusCode(201);
    }
}
