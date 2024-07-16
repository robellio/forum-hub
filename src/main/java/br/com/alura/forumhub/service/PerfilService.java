package br.com.alura.forumhub.service;

import br.com.alura.forumhub.model.Perfil;
import br.com.alura.forumhub.repository.PerfilRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PerfilService {
    @Autowired
    private PerfilRepository perfilRepository;

    public List<Perfil> listar() {
        return perfilRepository.findAll();
    }
}

