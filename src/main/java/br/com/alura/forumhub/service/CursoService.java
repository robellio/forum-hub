package br.com.alura.forumhub.service;

import br.com.alura.forumhub.model.Curso;
import br.com.alura.forumhub.repository.CursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CursoService {
    @Autowired
    private CursoRepository cursoRepository;

    public List<Curso> listar() {
        return cursoRepository.findAll();
    }

    public Curso buscarPorId(Long id) {
        return cursoRepository.findById(id).orElseThrow(() -> new RuntimeException("Curso não encontrado"));
    }

    public Curso cadastrar(Curso curso) {
        return cursoRepository.save(curso);
    }

    public Curso atualizar(Long id, Curso cursoAtualizado) {
        Curso curso = cursoRepository.findById(id).orElseThrow(() -> new RuntimeException("Curso não encontrado"));
        curso.setNome(cursoAtualizado.getNome());
        curso.setCategoria(cursoAtualizado.getCategoria());
        return cursoRepository.save(curso);
    }

    public void deletar(Long id) {
        cursoRepository.deleteById(id);
    }
}

