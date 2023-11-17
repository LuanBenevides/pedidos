package io.github.luanBenevides.vendas.service;

import io.github.luanBenevides.vendas.domain.repository.ClienteRepository;
import io.github.luanBenevides.vendas.domain.repository.ItemPedidoRepository;
import io.github.luanBenevides.vendas.domain.repository.PedidoRepository;
import io.github.luanBenevides.vendas.domain.repository.ProdutoRepository;
import io.github.luanBenevides.vendas.service.impl.PedidoServiceImpl;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DisplayName("Testes unitários - PedidoServiceImpl")
public class TestPedidoServiceImpl {

    private @Autowired ClienteRepository clienteRepository;
    private @Autowired ProdutoRepository produtoRepository;
    private @Autowired PedidoRepository pedidoRepository;
    private @Autowired ItemPedidoRepository itemPedidoRepository;
    private @Autowired PedidoServiceImpl pedidoService;

    @BeforeEach
    void setUp() {
        pedidoService = new PedidoServiceImpl(
                pedidoRepository,
                clienteRepository,
                produtoRepository,
                itemPedidoRepository);
    }

    @Test
    @Order(1)
    @DisplayName("1 - Teste do método createPedido - Caso o pedido seja criado com sucesso.")
    void testCreatePedidoCasoSucesso() {

    }
}
