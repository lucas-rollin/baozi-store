package io.github.lucas_rollin.baozistore.controller;

import io.github.lucas_rollin.baozistore.model.Cliente;
import io.github.lucas_rollin.baozistore.model.Pedido;
import io.github.lucas_rollin.baozistore.model.Produto;
import io.github.lucas_rollin.baozistore.repository.ClienteRepository;
import io.github.lucas_rollin.baozistore.repository.PedidoRepository;
import io.github.lucas_rollin.baozistore.repository.ProdutoRepository;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    private final PedidoRepository pedidos;
    private final ClienteRepository clientes;
    private final ProdutoRepository produtos;

    public PedidoController(PedidoRepository pedidos, ClienteRepository clientes, ProdutoRepository produtos) {
        this.pedidos = pedidos;
        this.clientes = clientes;
        this.produtos = produtos;
    }

    // Body: {"cliente": {"id": 1}, "produto": {"id": 1}, "quantidade": 3}
    @PostMapping
    public ResponseEntity<Pedido> criar(@RequestBody Pedido pedido) {
        if (pedido.getCliente() == null || pedido.getProduto() == null
                || pedido.getQuantidade() == null || pedido.getQuantidade() <= 0) {
            return ResponseEntity.badRequest().build();
        }
        Cliente cliente = clientes.findById(pedido.getCliente().getId()).orElse(null);
        Produto produto = produtos.findById(pedido.getProduto().getId()).orElse(null);
        if (cliente == null || produto == null) {
            return ResponseEntity.badRequest().build();
        }
        pedido.setId(null);
        pedido.setCliente(cliente);   // full objects, so the response shows names
        pedido.setProduto(produto);
        return ResponseEntity.status(HttpStatus.CREATED).body(pedidos.save(pedido));
    }

    @GetMapping
    public List<Pedido> listar() {
        return pedidos.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pedido> buscar(@PathVariable Long id) {
        return pedidos.findById(id).map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> apagar(@PathVariable Long id) {
        if (!pedidos.existsById(id)) return ResponseEntity.notFound().build();
        pedidos.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
