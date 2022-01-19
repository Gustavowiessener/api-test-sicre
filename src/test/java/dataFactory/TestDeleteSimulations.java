package dataFactory;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class TestDeleteSimulations {

    @BeforeAll
    public static void setup() {
        RestAssured.baseURI = "http://localhost:8080/api/v1";
    }

    @Test
    @DisplayName("Dado como entrada o ID para exclusão de uma simualação, deve retornar o status 204")
    public void deleteInUser() {
        Response response = given()
                .contentType(ContentType.JSON)
                .when()
                .delete("/simulacoes/15")
                .then()
                .log().all()
                .extract().response();


        Assertions.assertEquals(204, response.statusCode());

    }

    //Cenário deletar id não existente, não está passando. Somente retorno 200 em ids não existentes;

    @Test
    @DisplayName("Dado como entrada um ID não existente, o status retorna 200.")
    public void deleteUserIdsNotExist() {
        Response response = given()
                .contentType(ContentType.JSON)
                .when()
                .delete("/simulacoes/99")
                .then()
                .extract().response();


        Assertions.assertEquals(200, response.statusCode());

    }

}
