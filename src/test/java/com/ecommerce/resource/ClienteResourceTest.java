package com.ecommerce.resource;

import com.ecommerce.dto.ClienteRequestDTO;
import com.ecommerce.service.ClienteService;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import jakarta.inject.Inject;
import org.junit.jupiter.api.*;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

@QuarkusTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ClienteResourceTest {

    @Inject
    ClienteService clienteService;

    static Long clienteId;

    @Test
    @Order(1)
    void testCriarCliente() {
        ClienteRequestDTO cliente = new ClienteRequestDTO(
            "Maria Silva",
            "11988887777",
            "12345678901",
            "maria@email.com",
            "senhaSegura123"
        );

        clienteId = given()
            .contentType(ContentType.JSON)
            .body(cliente)
            .when().post("/clientes")
            .then()
            .statusCode(201)
            .extract()
            .jsonPath()
            .getLong("id");
    }

    @Test
    @Order(2)
    void testAtualizarCliente() {
        ClienteRequestDTO atualizado = new ClienteRequestDTO(
            "Maria Oliveira",
            "11999998888",
            "98765432100",
            "maria.oliveira@email.com",
            "novaSenha456"
        );

        given()
            .contentType(ContentType.JSON)
            .body(atualizado)
            .pathParam("id", clienteId)
            .when().put("/clientes/{id}")
            .then()
            .statusCode(204);
    }

    @Test
    @Order(3)
    void testBuscarPorId() {
        given()
            .pathParam("id", clienteId)
            .when().get("/clientes/{id}")
            .then()
            .statusCode(200)
            .contentType(ContentType.JSON)
            .body("nome", equalTo("Maria Oliveira"))
            .body("cpf", equalTo("98765432100"))
            .body("email", equalTo("maria.oliveira@email.com"));
    }

    @Test
    @Order(4)
    void testDeletarCliente() {
        given()
            .pathParam("id", clienteId)
            .when().delete("/clientes/{id}")
            .then()
            .statusCode(204);
    }
}
