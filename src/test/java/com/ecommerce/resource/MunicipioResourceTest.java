package com.ecommerce.resource;

import com.ecommerce.dto.MunicipioRequestDTO;
import com.ecommerce.service.MunicipioService;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import jakarta.inject.Inject;
import org.junit.jupiter.api.*;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

@QuarkusTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class MunicipioResourceTest {

    @Inject
    MunicipioService municipioService;

    static Long municipioId;

    static final Long ESTADO_ID_FIXO = 1L;

    @Test
    @Order(1)
    void testCriarMunicipio() {
        MunicipioRequestDTO municipio = new MunicipioRequestDTO(
            "São Paulo",
            ESTADO_ID_FIXO
        );

        municipioId = given()
            .contentType(ContentType.JSON)
            .body(municipio)
            .when().post("/municipios")
            .then()
            .statusCode(201)
            .extract()
            .jsonPath()
            .getLong("id");
    }

    @Test
    @Order(2)
    void testAtualizarMunicipio() {
        MunicipioRequestDTO atualizado = new MunicipioRequestDTO(
            "Campinas",
            ESTADO_ID_FIXO
        );

        given()
            .contentType(ContentType.JSON)
            .body(atualizado)
            .pathParam("id", municipioId)
            .when().put("/municipios/{id}")
            .then()
            .statusCode(204); // Atualização sem corpo
    }

    @Test
    @Order(3)
    void testBuscarPorId() {
        given()
            .pathParam("id", 4)
            .when().get("/municipios/{id}")
            .then()
            .statusCode(200)
            .contentType(ContentType.JSON)
            .body("id", equalTo(4))
            .body("nome", equalTo("Belo Horizonte"));
    }

    @Test
    @Order(4)
    void testListarTodosMunicipios() {
        given()
            .when().get("/municipios")
            .then()
            .statusCode(200)
            .body("size()", greaterThan(0));
    }

    @Test
    @Order(5)
    void testDeletarMunicipio() {
        given()
            .pathParam("id", municipioId)
            .when().delete("/municipios/{id}")
            .then()
            .statusCode(204);
    }
}
