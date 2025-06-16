package com.ecommerce.resource;

import com.ecommerce.dto.*;
import com.ecommerce.enumerator.StatusPedido;
import com.ecommerce.model.ItemPedido;
import com.ecommerce.service.PedidoService;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import jakarta.inject.Inject;
import org.junit.jupiter.api.*;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class PedidoResourceTest {

    @Inject
    PedidoService pedidoService;

    static Long pedidoId;

    @Test
    @Order(1)
    void testCriarPedido() {
        PedidoRequestDTO dto = new PedidoRequestDTO(
            StatusPedido.PROCESSANDO_PAGAMENTO,
            1, // clienteId existente
            1, // enderecoClienteId existente
            List.of(new ItemPedidoRequestDTO(1, 2)) // pianoId existente
        );

        PedidoResponseDTO response = given()
            .contentType(ContentType.JSON)
            .body(dto)
            .when().post("/pedidos")
            .then()
                .statusCode(201)
                .body("id", notNullValue())
                .extract().as(PedidoResponseDTO.class);

        pedidoId = response.id();
        assertEquals(StatusPedido.PROCESSANDO_PAGAMENTO, response.status());
    }

    @Test
    @Order(2)
    void testBuscarTodos() {
        given()
            .when().get("/pedidos")
            .then()
                .statusCode(200)
                .body("size()", greaterThan(0));
    }

    @Test
    @Order(3)
    void testBuscarPorId() {
        given()
            .when().get("/pedidos/" + pedidoId)
            .then()
                .statusCode(200)
                .body("id", equalTo(pedidoId.intValue()));
    }

    @Test
    @Order(4)
    void testPagarComPix() {
        given()
            .when().post("/pedidos/" + pedidoId + "/pagar/pix")
            .then()
                .statusCode(200)
                .body(containsString("Chave PIX gerada"));
    }

    @Test
    @Order(5)
    void testCancelarPedido() {
        given()
            .when().post("/pedidos/" + pedidoId + "/cancelar")
            .then()
                .statusCode(200)
                .body(containsString("Pedido cancelado"));
    }

    @Test
    @Order(6)
    void testPagamentoCartaoDebito() {
        // recriar pedido
        PedidoRequestDTO dto = new PedidoRequestDTO(
            StatusPedido.PROCESSANDO_PAGAMENTO,
            1L, 1L,
            List.of(new ItemPedidoRequestDTO(1L, 1))
        );
        PedidoResponseDTO response = pedidoService.salvar(dto);
        Long novoPedidoId = response.id();

        DadosCartaoDTO cartao = new DadosCartaoDTO(
            "Maria Teste",
            "1234123412341234",
            "123",
            "12/2027"
        );

        given()
            .contentType(ContentType.JSON)
            .body(cartao)
            .when().post("/pedidos/" + novoPedidoId + "/pagar/debito")
            .then()
                .statusCode(200)
                .body(containsString("sucesso"));
    }

    @Test
    @Order(7)
    void testApagar() {
        given()
            .when().delete("/pedidos/" + pedidoId)
            .then()
                .statusCode(204);
    }
}
