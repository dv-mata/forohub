package com.alura.forohub.domain.topico;

import java.time.LocalDateTime;

public record DTORespuestaTopico(
        Long id,
        String titulo,
        String mensaje,
        String curso,
        LocalDateTime fechaCreacion
) {
}