package br.com.alura.forumhub.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "topicos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Topico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;
    private String mensagem;
    private LocalDateTime dataCriacao = LocalDateTime.now();

    @Enumerated(EnumType.STRING)
    private StatusTopico status = StatusTopico.NAO_RESPONDIDO;

    @ManyToOne
    private Usuario usuario;

    @ManyToOne
    private Curso curso;

    @OneToMany(mappedBy = "topico")
    private List<Resposta> respostas;

    public Topico(String titulo, String mensagem, Usuario usuario, Curso curso) {
        this.titulo = titulo;
        this.mensagem = mensagem;
        this.usuario = usuario;
        this.curso = curso;
        this.status = StatusTopico.NAO_RESPONDIDO;
        this.dataCriacao = LocalDateTime.now();
    }
}
