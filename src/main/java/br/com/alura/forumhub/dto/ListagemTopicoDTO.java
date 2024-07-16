package br.com.alura.forumhub.dto;

import br.com.alura.forumhub.model.Resposta;
import br.com.alura.forumhub.model.StatusTopico;
import br.com.alura.forumhub.model.Topico;

import java.time.LocalDateTime;
import java.util.List;

public record ListagemTopicoDTO(
        Long id,
        String titulo,
        String mensagem,
        LocalDateTime dataCriacao,
        String nomeAutor,
        StatusTopico status,
        List<Resposta> respostas
) {
    public ListagemTopicoDTO(Topico topico) {
        this(
                topico.getId(),
                topico.getTitulo(),
                topico.getMensagem(),
                topico.getDataCriacao(),
                topico.getUsuario().getNome(),
                topico.getStatus(),
                topico.getRespostas()
        );
    }
}
