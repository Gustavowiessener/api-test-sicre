package dataFactory;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class TestConsultSimulations {

    @BeforeAll
    public static void setup() {
        RestAssured.baseURI = "http://localhost:8080/api/v1";
    }

    @Test
    @DisplayName("Cenário deve pegar toda as simulações cadastradas, e retornar o status 200 com a listagem dos cadastros")
    public void testGetRequestsAll() {
        Response response = given()
                .contentType(ContentType.JSON)
                .when()
                .get("/simulacoes")
                .then()
                .log().all()
                .extract().response();

        Assertions.assertEquals(200, response.statusCode());
    }


    @Test
    @DisplayName("Dado um CPF não existente, retorno previsto statusCode 404")
    public void testGetInUserNotExist(){
        Response response = given()
                .contentType(ContentType.JSON)
                .when()
                .get("/simulacoes/12345678910")
                .then()
                .log().all()
                .extract().response();

        Assertions.assertEquals(404, response.statusCode());
    }



    @Test
    @DisplayName("Dado como entrada o ID como parametro, deve retornar os dados da requesição e statusCode 200")
    public void testGetRequestWithQueryParam() {
        Response response = given()
                .contentType(ContentType.JSON)
                .param("id", "13")
                .when()
                .get("/simulacoes/97093236014")
                .then()
                .log().all()
                .extract().response();

        Assertions.assertEquals(200, response.statusCode());
        Assertions.assertEquals("Gustavo Henrique de Castilho", response.jsonPath().getString("nome"));
    }

    @Test
    @DisplayName("Dado um CPF não valido, após a validação de CPF valido, retorno status 404")
    public void testGetCpfNotExist(){
        Response response = given()
                .contentType(ContentType.JSON)
                .when()
                .get("/simulacoes/22222222222")
                .then()
                .log().all()
                .extract().response();

        Assertions.assertEquals(404, response.statusCode());
        Assertions.assertEquals("CPF 22222222222 não encontrado", response.jsonPath().getString("mensagem"));
    }




}
