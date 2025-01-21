package com.alura.forohub.domain.topico;

import java.time.LocalDateTime;

public record DTOListadoTopico(
        Long id,
        String titulo,
        String mensaje,
        LocalDateTime fechaCreacion,
        String autor
) {
    public DTOListadoTopico(Topico topico) {
        this(topico.getId(), topico.getTitulo(), topico.getMensaje(), topico.getFechaCreacion(), topico.getAutor().toString());
    }
}
