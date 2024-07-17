package br.com.alura.forumhub.controller;

import br.com.alura.forumhub.dto.AtualizacaoTopicoDTO;
import br.com.alura.forumhub.dto.CadastroTopicoDTO;
import br.com.alura.forumhub.dto.ListagemTopicoDTO;
import br.com.alura.forumhub.service.TopicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/topicos")
public class TopicoController {

    @Autowired
    private TopicoService topicoService;

    @GetMapping
    public List<ListagemTopicoDTO> listar() {
        return topicoService.listar();
    }

    @GetMapping("/{id}")
    public ListagemTopicoDTO listarPorId(@PathVariable Long id) {
        return topicoService.listarPorId(id);
    }

    @PostMapping
    public ListagemTopicoDTO cadastrar(@RequestBody CadastroTopicoDTO dto, Authentication authentication) {
        String username = authentication.getName();
        return topicoService.cadastrar(dto, username);
    }

    @PutMapping("/{id}")
    public ListagemTopicoDTO atualizar(@PathVariable Long id, @RequestBody AtualizacaoTopicoDTO dto, Authentication authentication) {
        String username = authentication.getName();
        return topicoService.atualizar(id, dto, username);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id, Authentication authentication) {
        String username = authentication.getName();
        topicoService.deletar(id, username);
    }
}

