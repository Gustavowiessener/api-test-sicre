package dataFactory;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class TestCreateSimulations {


    private static String corpoRequests = "{\n" +
            "  \"id\": 13,\n" +
            "  \"nome\": \"Fulano de Tal\",\n" +
            "  \"cpf\": \"97093236014\",\n" +
            "  \"email\": \"email@email.com\",\n" +
            "  \"valor\": 1200,\n" +
            "  \"parcelas\": 3,\n" +
            "  \"seguro\": true\n" +
            "}";

    private static String corpoRequestsInvalid = "{\n" +
            "  \"name\": \"Fulano de Tal\",\n" +
            "  \"cpf\": \"58063164083\",\n" +
            "  \"email\": \"email@live.com\",\n" +
            "  \"valor\": 1500,\n" +
            "  \"parcelas\": 2,\n" +
            "  \"seguro\": bolean \n" +
            "}";

    private static String corpoRequestsExistAtt = "{\n" +
            "             \"id\": 12,\n" +
            "            \"nome\": \"Deltrano\",\n" +
            "            \"cpf\": \"97093236014\",\n" +
            "            \"email\": \"deltrano@gmail.com\",\n" +
            "            \"valor\": 20000,\n" +
            "            \"parcelas\": 5,\n" +
            "            \"seguro\": false\n" +
            "}";


    @BeforeAll
    public static void setup() {
        RestAssured.baseURI = "http://localhost:8080/api/v1";
    }


    @Test
    @DisplayName("Dado um body válido para API, deve retornar o status 201 e os dados inseridos como retorno")
    public void testPostRequest() {
        Response response = given()
                .header("Content-type", "application/json")
                .and()
                .body(corpoRequests)
                .when()
                .post("/simulacoes")
                .then()
                .log().all()
                .extract().response();

        Assertions.assertEquals(201, response.statusCode());
        Assertions.assertEquals("13",response.jsonPath().getString("id"));
        Assertions.assertEquals("Fulano de Tal", response.jsonPath().getString("nome"));
        Assertions.assertEquals("97093236014", response.jsonPath().getString("cpf"));
        Assertions.assertEquals("email@email.com", response.jsonPath().getString("email"));
        Assertions.assertEquals("1200", response.jsonPath().getString("valor"));
        Assertions.assertEquals("3", response.jsonPath().getString("parcelas"));
        Assertions.assertEquals("true", response.jsonPath().getString("seguro"));

    }




    @Test
    @DisplayName("Dado a entrada de um Body invalido resultado deve ser um statusCode de 400")
    public void testPostRequestInvalid() {
        Response response = given()
                .header("Content-type", "application/json")
                .and()
                .body(corpoRequestsInvalid)
                .when()
                .post("/simulacoes")
                .then()
                .log().all()
                .extract().response();

        Assertions.assertEquals(400, response.statusCode());

    }

    @Test
    @DisplayName("Dado como entrada um request já existente, deve retornar statusCode 400 com mensagem CPF duplicado")
    public void testPostRequestExist() {
        Response response = given()
                .header("Content-type", "application/json")
                .and()
                .body(corpoRequestsExistAtt)
                .when()
                .post("/simulacoes/")
                .then()
                .log().all()
                .extract().response();

        Assertions.assertEquals(400, response.statusCode());
        Assertions.assertEquals("CPF duplicado", response.jsonPath().getString("mensagem"));

    }
}


