package io.github.luanBenevides.vendas.rest.controller;

import io.github.luanBenevides.vendas.domain.entity.Produto;
import io.github.luanBenevides.vendas.domain.repository.ProdutoRepository;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {

    private ProdutoRepository repository;

    public ProdutoController(ProdutoRepository repository) {
        this.repository = repository;
    }

    @PostMapping("/salvar")
    @ResponseStatus(HttpStatus.CREATED)
    public Produto createProduto(@RequestBody Produto produto) {
        return repository.save(produto);
    }

    @GetMapping("/all")
    public List<Produto> getAllProdutos() {
        return repository.findAll();
    }

    @GetMapping("/filtro")
    public List<Produto> getAllProdutosWithFillter(Produto filtro) {
        ExampleMatcher exampleMatcher = ExampleMatcher
                .matching()
                .withIgnoreCase()
                .withStringMatcher( ExampleMatcher.StringMatcher.CONTAINING);

        Example example = Example.of(filtro, exampleMatcher);

        return repository.findAll(example);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateProduto(@PathVariable("id") Integer id,
                                 @RequestBody Produto produto) {
         repository.findById(id)
                .map(produtoDb -> {
                    produto.setId(produtoDb.getId());
                    repository.save(produto);
                    return produtoDb;
                }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Produto não encontrado"));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProduto(@PathVariable("id") Integer id) {
         repository.findById(id)
                .map(produto -> {
                    repository.delete(produto);
                    return produto;
                }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Produto não encontrado"));
    }
}
