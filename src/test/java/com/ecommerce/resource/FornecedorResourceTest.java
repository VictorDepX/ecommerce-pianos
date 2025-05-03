package com.ecommerce.resource;

import com.ecommerce.dto.FornecedorRequestDTO;
import com.ecommerce.service.FornecedorService;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import jakarta.inject.Inject;
import org.junit.jupiter.api.*;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

@QuarkusTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class FornecedorResourceTest {

    @Inject
    FornecedorService fornecedorService;

    static Long fornecedorId;

    @Test
    public void testCriarFornecedor() {
        
        List<Long> marcasIds = List.of(1L, 2L);

        FornecedorRequestDTO dto = new FornecedorRequestDTO("Fornecedor Teste", "12345678000199", "11999999999", marcasIds);

        fornecedorId = given()
            .contentType(ContentType.JSON)
            .body(dto)
            .when().post("/fornecedores")
            .then()
            .statusCode(201)
            .extract()
            .jsonPath()
            .getLong("id"); 
    }

    @Test
    @Order(2)
    void testAtualizarFornecedor() {

        List<Long> marcasIds = List.of(1L, 2L);

        FornecedorRequestDTO dto = new FornecedorRequestDTO("Fornecedor Teste", "12345678000199", "11999999999", marcasIds);

        fornecedorId = given()
        .contentType(ContentType.JSON)
        .body(dto)
    .when()
        .post("/fornecedores")
    .then()
        .statusCode(201)
        .extract()
        .jsonPath()
        .getLong("id");

        FornecedorRequestDTO atualizado = new FornecedorRequestDTO(
            "Fornecedor Atualizado",
            "98765432000199",
            "11888888888",
            marcasIds
        );

        given()
            .contentType(ContentType.JSON)
            .body(atualizado)
            .pathParam("id", fornecedorId)
            .when().put("/fornecedores/{id}")
            .then()
            .statusCode(204);
    }

    @Test
    @Order(3)
    void testBuscarPorId() {

        List<Long> marcasIds = List.of(1L, 2L);

        FornecedorRequestDTO atualizado = new FornecedorRequestDTO(
            "Fornecedor Atualizado",
            "98765432000199",
            "11888888888",
            marcasIds
        );

        fornecedorId = given()
        .contentType(ContentType.JSON)
        .body(atualizado)
    .when()
        .post("/fornecedores")
    .then()
        .statusCode(201)
        .extract()
        .jsonPath()
        .getLong("id");



        given()
            .when().get("/fornecedores/" + fornecedorId)
            .then()
            .statusCode(200)
            .body("entity.nome", equalTo("Fornecedor Atualizado"))
            .body("entity.cnpj", equalTo("98765432000199"))
            .body("entity.telefone", equalTo("11888888888"));
    }

    @Test
    @Order(4)
    void testBuscarPorNome() {
        given()
            .queryParam("nome", "Fornecedor Atualizado")
            .when().get("/fornecedores/buscar/nome")
            .then()
            .statusCode(200)
            .body("entity.nome", equalTo("Fornecedor Atualizado"));
    }

    @Test
    @Order(5)
    void testBuscarPorCnpj() {
        given()
            .queryParam("value", "98765432000199")
            .when().get("/fornecedores/buscar/cnpj")
            .then()
            .statusCode(200)
            .body("entity.cnpj", equalTo("98765432000199"));
    }

    @Test
    @Order(6)
    void testBuscarPorTelefone() {
        given()
            .queryParam("value", "11999999999")
            .when().get("/fornecedores/buscar/telefone")
            .then()
            .statusCode(200)
            .body("entity.telefone", equalTo("11999999999"));
    }

    @Test
    @Order(7)
    void testDeletarFornecedor() {
        given()
            .pathParam("id", fornecedorId)
            .when().delete("/fornecedores/{id}")
            .then()
            .statusCode(204);
    }
}
