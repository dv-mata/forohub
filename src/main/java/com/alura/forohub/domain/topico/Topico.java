package com.alura.forohub.domain.topico;

import com.alura.forohub.domain.user.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity(name = "Topico")
@Table(name = "topicos")
//@Getter
//@AllArgsConstructor
//@NoArgsConstructor
@EqualsAndHashCode( of = "id" )
public class Topico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User autor;

    private String titulo;

    private String mensaje;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDateTime fechaCreacion;

    private Boolean status;

    private String curso;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getAutor() {
        return autor;
    }

    public void setAutor(User autor) {
        this.autor = autor;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public Topico(Long id, User autor, String titulo, String mensaje, LocalDateTime fechaCreacion, Boolean status, String curso) {
        this.id = id;
        this.autor = autor;
        this.titulo = titulo;
        this.mensaje = mensaje;
        this.fechaCreacion = fechaCreacion;
        this.status = status;
        this.curso = curso;
    }

    public Topico() {
    }

    public Topico(DTORegistroTopico dtoRegisterTopic, User autor) {
        this.titulo = dtoRegisterTopic.titulo();
        this.mensaje = dtoRegisterTopic.mensaje();
        this.curso = dtoRegisterTopic.curso();
        this.fechaCreacion = LocalDateTime.now();
        this.status = true;
        this.autor = autor;
    }

    public void actualizarDatos(DTOActualizarTopico dtoActualizarTopico) {
        if (dtoActualizarTopico.titulo() != null) {
            this.titulo = dtoActualizarTopico.titulo();
        }
        if (dtoActualizarTopico.mensaje() != null) {
            this.mensaje = dtoActualizarTopico.mensaje();
        }
    }

    public void desactivarMedico() {
        this.status = false;
    }
}
