package com.ecommerce.resource;

import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

import com.ecommerce.dto.EstadoRequestDTO;
import com.ecommerce.dto.EstadoResponseDTO;
import com.ecommerce.service.EstadoServiceImpl;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import jakarta.inject.Inject;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

@QuarkusTest
public class EstadoResourceTest {

    @Inject
    EstadoServiceImpl estadoService;

    @Test
    void testBuscarTodos() {
        given()
            .when().get("/estados")
            .then()
                .statusCode(200);
    }


    @Test
    void testIncluirEstado() {
        EstadoRequestDTO estado = new EstadoRequestDTO(
            "Rio Grande", 
            "RG");
        given()
            .contentType(ContentType.JSON)
            .body(estado)
            .when().post("/estados")
            .then()
                .statusCode(201)
                .body(
                    "id", notNullValue(),
                "nome", is("Rio Grande"),
                "sigla", is("RG")
                );
    }

    static Long id; 

    @Test
    void testAlterarEstado() {
        EstadoRequestDTO estado = new EstadoRequestDTO(
            "Rio Grande do Sul 3", 
            "RS");
       
        id =  estadoService.salvar(estado).id();

        EstadoRequestDTO estadoAlterado = new EstadoRequestDTO(
            "Rio Grande do Sul - Alterado", 
            "RR");

        given()
            .contentType(ContentType.JSON)
            .body(estadoAlterado)
            .when().put("/estados/" + id)
            .then()
                .statusCode(204);

        EstadoResponseDTO response = estadoService.buscarPorId(id);
        assertThat(response.nome(), is("Rio Grande do Sul - Alterado"));
        assertThat(response.sigla(), is("RR"));
    }



    @Test
    void testApagarEstado() {
        given()
        .when().delete("/estados/" + id)
        .then()
            .statusCode(204);

        EstadoResponseDTO responseDTO = estadoService.buscarPorId(id);
        assertNull(responseDTO); 
    }
}
