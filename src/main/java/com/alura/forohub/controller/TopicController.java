package com.alura.forohub.controller;

import com.alura.forohub.domain.topico.*;
import com.alura.forohub.domain.user.User;
import com.alura.forohub.domain.user.UserRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/topicos")
public class TopicController {

    @Autowired
    private TopicRepository topicRepository;

    @Autowired
    private UserRepository userRepository;

    @PostMapping
    public ResponseEntity<DTORespuestaTopico> saveTopic(@RequestBody @Valid DTORegistroTopico dtoRegisterTopic,
                                                        UriComponentsBuilder uriComponentsBuilder){

        User autor = userRepository.findById(dtoRegisterTopic.autor())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        Topico topico = topicRepository.save(new Topico(dtoRegisterTopic, autor));

        DTORespuestaTopico dtoResponseTopic = new DTORespuestaTopico(topico.getId(), topico.getTitulo(), topico.getMensaje(), topico.getCurso(), topico.getFechaCreacion());

        URI url = uriComponentsBuilder.path("/topic/{id}").buildAndExpand(topico.getId()).toUri();

        return ResponseEntity.created(url).body(dtoResponseTopic);

    }

    @GetMapping
    public ResponseEntity<Page<DTOListadoTopico>> listadoTopico(@PageableDefault(size = 5) Pageable paginacion){
        return ResponseEntity.ok(topicRepository.findByStatusTrue(paginacion).map(DTOListadoTopico::new));
    }

    @PutMapping
    @Transactional
    public ResponseEntity actualizarTopico(@RequestBody @Valid DTOActualizarTopico dtoActualizarTopico){
        Topico topico = topicRepository.getReferenceById(dtoActualizarTopico.id());

        topico.actualizarDatos(dtoActualizarTopico);

        return ResponseEntity.ok(new DTORespuestaTopico(topico.getId(),topico.getTitulo(),topico.getMensaje(), topico.getCurso(),topico.getFechaCreacion()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DTORespuestaTopico> detalleDeTopico(@PathVariable Long id) {
        Topico topico = topicRepository.getReferenceById(id);
        var dtoRespuestaTopico = new DTORespuestaTopico(topico.getId(),topico.getTitulo(),topico.getMensaje(), topico.getCurso(),topico.getFechaCreacion());
        return ResponseEntity.ok(dtoRespuestaTopico);
    }

    // DELETE LOGICO
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity eliminarTopico(@PathVariable Long id) {
        Topico topico = topicRepository.getReferenceById(id);
        topico.desactivarMedico();
        return ResponseEntity.noContent().build();
    }

}
