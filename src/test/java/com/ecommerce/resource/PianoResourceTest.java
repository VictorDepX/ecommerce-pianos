package com.ecommerce.resource;

import com.ecommerce.dto.PianoDTO;
import com.ecommerce.dto.PianoResponseDTO;
import com.ecommerce.enumerator.TipoPiano;
import com.ecommerce.service.PianoService;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import jakarta.inject.Inject;

import org.junit.jupiter.api.*;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertNull;

@QuarkusTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class PianoResourceTest {

    @Inject
    PianoService pianoService;

    static Long id;

    @Test
    @Order(1)
    void testCriarPiano() {
        PianoDTO dto = new PianoDTO(
            "YDP-145",
            "Yamaha",
            8999.0,
            88,
            true,
            "Madeira",
            TipoPiano.DIGITAL,
            1L,
            1L
        );

        id = given()
            .contentType(ContentType.JSON)
            .body(dto)
            .when().post("/pianos")
            .then()
            .statusCode(201)
            .body("modelo", equalTo("YDP-145"))
            .body("fabricante", equalTo("Yamaha"))
            .body("preco", equalTo(8999.0f))
            .extract()
            .jsonPath()
            .getLong("id");
    }

    @Test
    @Order(2)
    void testAtualizarPiano() {

        PianoDTO atualizado = new PianoDTO(
            "YDP-165",
            "Yamaha Atualizado",
            8999.0,
            88,
            true,
            "Madeira premium",
            TipoPiano.DIGITAL,
            1L,
            1L
        );

        given()
            .contentType(ContentType.JSON)
            .body(atualizado)
            .when().put("/pianos/" + id)
            .then()
            .statusCode(204);

        PianoResponseDTO response = pianoService.buscarPorId(id);
        assertThat(response.modelo(), is("YDP-165"));
        assertThat(response.fabricante(), is("Yamaha Atualizado"));
        assertThat(response.preco(), is(8999.0));
    }

    @Test
    @Order(3)
    void testDeletarPiano() {
        given()
        .pathParam("id", id)
        .when().delete("/pianos/{id}")
        .then()
            .statusCode(204);

        PianoResponseDTO response = pianoService.buscarPorId(id);
        assertNull(response);
    }

    @Test
    @Order(4)
    void testListarTodos() {
        given()
            .when().get("/pianos")
            .then()
            .statusCode(200);
    }
}
