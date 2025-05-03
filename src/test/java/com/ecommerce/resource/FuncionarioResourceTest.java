package com.ecommerce.resource;

import com.ecommerce.dto.FuncionarioRequestDTO;
import com.ecommerce.service.FuncionarioService;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import jakarta.inject.Inject;
import org.junit.jupiter.api.*;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

@QuarkusTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class FuncionarioResourceTest {

    @Inject
    FuncionarioService funcionarioService;

    static Long funcionarioId;

    @Test
    @Order(1)
    void testCriarFuncionario() {
        FuncionarioRequestDTO funcionario = new FuncionarioRequestDTO(
            "João Teste",
            "11999999999",
            "12345678900",
            "joao@empresa.com",
            "senha123",
            4500.0,
            "TI"
        );

        funcionarioId = given()
            .contentType(ContentType.JSON)
            .body(funcionario)
            .when().post("/funcionarios")
            .then()
            .statusCode(201)
            .extract()
            .jsonPath()
            .getLong("id");
    }

    @Test
    @Order(2)
    void testAtualizarFuncionario() {
        FuncionarioRequestDTO atualizado = new FuncionarioRequestDTO(
            "João Atualizado",
            "11888888888",
            "98765432100",
            "joaoatualizado@empresa.com",
            "novasenha456",
            5200.0,
            "Financeiro"
        );

        given()
            .contentType(ContentType.JSON)
            .body(atualizado)
            .pathParam("id", funcionarioId)
            .when().put("/funcionarios/{id}")
            .then()
            .statusCode(204); // Atualização sem corpo
    }

    @Test
    @Order(3)
    void testBuscarPorId() {
        given()
            .pathParam("id", funcionarioId)
            .when().get("/funcionarios/{id}")
            .then()
            .statusCode(200)
            .contentType(ContentType.JSON)
            .body("nome", equalTo("João Atualizado"))
            .body("cpf", equalTo("98765432100"))
            .body("departamento", equalTo("Financeiro"));
    }

    @Test
    @Order(4)
    void testDeletarFuncionario() {
        given()
            .pathParam("id", funcionarioId)
            .when().delete("/funcionarios/{id}")
            .then()
            .statusCode(204); // Exclusão sem corpo
    }
}
