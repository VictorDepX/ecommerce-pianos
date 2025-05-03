package com.ecommerce.resource;

import com.ecommerce.dto.MarcaRequestDTO;
import com.ecommerce.service.MarcaService;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import jakarta.inject.Inject;
import org.junit.jupiter.api.*;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

@QuarkusTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class MarcaResourceTest {

    @Inject
    MarcaService marcaService;

    static Long marcaId;

    @Test
    @Order(1)
    void testCriarMarca() {
        MarcaRequestDTO dto = new MarcaRequestDTO(
            "Marca Teste",
            "11111111000191"
        );

        // Criação sem retorno, buscar ID depois
        given()
            .contentType(ContentType.JSON)
            .body(dto)
            .when().post("/marcas")
            .then()
            .statusCode(204); // Criação sem corpo

        marcaId = given()
            .when().get("/marcas")
            .then()
            .statusCode(200)
            .extract().jsonPath().getLong("find { it.nome == 'Marca Teste' }.id");
    }

    @Test
    @Order(2)
    void testAtualizarMarca() {
        MarcaRequestDTO atualizada = new MarcaRequestDTO(
            "Marca Atualizada",
            "22222222000192"
        );

        given()
            .contentType(ContentType.JSON)
            .body(atualizada)
            .pathParam("id", marcaId)
            .when().put("/marcas/{id}")
            .then()
            .statusCode(204); // Atualização sem corpo
    }

    @Test
    @Order(3)
    void testBuscarPorId() {
        given()
            .pathParam("id", marcaId)
            .when().get("/marcas/{id}")
            .then()
            .statusCode(200)
            .contentType(ContentType.JSON)
            .body("nome", equalTo("Marca Atualizada"))
            .body("cnpj", equalTo("22222222000192"));
    }

    @Test
    @Order(4)
    void testDeletarMarca() {
        given()
            .pathParam("id", marcaId)
            .when().delete("/marcas/{id}")
            .then()
            .statusCode(204); // Exclusão sem corpo
    }
}
