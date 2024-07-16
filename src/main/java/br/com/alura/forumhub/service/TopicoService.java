package br.com.alura.forumhub.service;

import br.com.alura.forumhub.dto.AtualizacaoTopicoDTO;
import br.com.alura.forumhub.dto.CadastroTopicoDTO;
import br.com.alura.forumhub.dto.ListagemTopicoDTO;
import br.com.alura.forumhub.model.Curso;
import br.com.alura.forumhub.model.Topico;
import br.com.alura.forumhub.model.Usuario;
import br.com.alura.forumhub.repository.CursoRepository;
import br.com.alura.forumhub.repository.TopicoRepository;
import br.com.alura.forumhub.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TopicoService {

    @Autowired
    private TopicoRepository topicoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private CursoRepository cursoRepository;

    public List<ListagemTopicoDTO> listar() {
        return topicoRepository.findAll().stream()
                .map(ListagemTopicoDTO::new)
                .collect(Collectors.toList());
    }

    public ListagemTopicoDTO listarPorId(Long id) {
        return topicoRepository.findById(id)
                .map(ListagemTopicoDTO::new)
                .orElseThrow(() -> new RuntimeException("Tópico não encontrado"));
    }

    public ListagemTopicoDTO cadastrar(CadastroTopicoDTO dto, String username) {
        Usuario usuario = usuarioRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        Curso curso = cursoRepository.findByNome(dto.nomeCurso())
                .orElseThrow(() -> new RuntimeException("Curso não encontrado"));
        Topico topico = new Topico(dto.titulo(), dto.mensagem(), usuario, curso);
        topicoRepository.save(topico);
        return new ListagemTopicoDTO(topico);
    }

    public ListagemTopicoDTO atualizar(Long id, AtualizacaoTopicoDTO dto, String username) {
        Topico topico = topicoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tópico não encontrado"));
        if (!topico.getUsuario().getUsername().equals(username)) {
            throw new RuntimeException("Usuário não autorizado a atualizar este tópico");
        }
        topico.setTitulo(dto.titulo());
        topico.setMensagem(dto.mensagem());
        topicoRepository.save(topico);
        return new ListagemTopicoDTO(topico);
    }

    public void deletar(Long id, String username) {
        Topico topico = topicoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tópico não encontrado"));
        if (!topico.getUsuario().getUsername().equals(username)) {
            throw new RuntimeException("Usuário não autorizado a deletar este tópico");
        }
        topicoRepository.delete(topico);
    }
}
