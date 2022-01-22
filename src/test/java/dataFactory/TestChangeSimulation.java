package dataFactory;


import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class TestChangeSimulation {

    private static String corpoRequestsNew = "{\n" +
            "  \"id\": 13,\n" +
            "  \"nome\": \"Gustavo Henrique de Castilho\",\n" +
            "  \"cpf\": \"97093236014\",\n" +
            "  \"email\": \"gutohc_@email.com\",\n" +
            "  \"valor\": 2000,\n" +
            "  \"parcelas\": 20,\n" +
            "  \"seguro\": true\n" +
            "}";


    @BeforeAll
    public static void setup() {
        RestAssured.baseURI = "http://localhost:8080/api/v1";
    }

    @Test
    @DisplayName("Dado como entrada um CPF, a partir desse atributo fazer a alteração no body da requisição")
    public void testPutNewAddUsers() {
        Response response = given()
                .header("Content-type", "application/json")
                .and()
                .body(corpoRequestsNew)
                .when()
                .put("/simulacoes/97093236014")
                .then()
                .log().all()
                .extract().response();

        Assertions.assertEquals(200, response.statusCode());
        Assertions.assertEquals("13",response.jsonPath().getString("id"));
        Assertions.assertEquals("Gustavo Henrique de Castilho", response.jsonPath().getString("nome"));
    }

    @Test
    @DisplayName("Dado a entrada um atribuito inexistente, o retorno deve ser 404")
    public void testPutNewAddUsersNotExist() {
        Response response = given()
                .header("Content-type", "application/json")
                .and()
                .body(corpoRequestsNew)
                .when()
                .put("/simulacoes/01001001001")
                .then()
                .log().all()
                .extract().response();

        Assertions.assertEquals(404, response.statusCode());
        Assertions.assertEquals("CPF 01001001001 não encontrado", response.jsonPath().getString("mensagem"));

    }

}
