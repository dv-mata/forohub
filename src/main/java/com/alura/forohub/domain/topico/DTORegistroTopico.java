package com.alura.forohub.domain.topico;

import jakarta.validation.constraints.NotNull;

public record DTORegistroTopico(

        @NotNull
        String titulo,
        @NotNull
        String mensaje,
        @NotNull
        Long autor,
        @NotNull
        String curso
) {
}
