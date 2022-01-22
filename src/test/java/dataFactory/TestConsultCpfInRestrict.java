package dataFactory;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;


public class TestConsultCpfInRestrict {

    @BeforeAll
    public static void setup() {
        RestAssured.baseURI = "http://localhost:8080/api/v1";
    }

    @Test
    @DisplayName("Entrada com um CPF e deve retornar status 200 se tiver um problema/restrição")
    public void testGetCpfInProblemAssert() {
            Response response = given()
                    .contentType(ContentType.JSON)
                    .when()
                    .get("/restricoes/97093236014")
                    .then()
                    .log().all()
                    .extract().response();

        Assertions.assertEquals(200, response.statusCode());
        Assertions.assertEquals("O CPF 97093236014 tem problema", response.jsonPath().getString("mensagem"));
    }

    @Test
    @DisplayName("Novo cenário com outro CPF, deve retornar status 200 e mensagem")
    public void testGetCpfInNotProblemAssert() {
        Response response = given()
                .contentType(ContentType.JSON)
                .when()
                .get("/restricoes/19626829001")
                .then()
                .log().all()
                .extract().response();

        Assertions.assertEquals(200, response.statusCode());
        Assertions.assertEquals("O CPF 19626829001 tem problema", response.jsonPath().getString("mensagem"));

    }
}

