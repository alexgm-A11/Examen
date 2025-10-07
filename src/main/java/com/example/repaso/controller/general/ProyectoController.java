package com.example.repaso.controller.general;

import com.example.repaso.dto.ProyectoDTO;
import com.example.repaso.service.service.ProyectoService;
import org.hibernate.service.spi.ServiceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/proyectos")
public class ProyectoController {
    private final ProyectoService proyectoService;

    public ProyectoController(ProyectoService proyectoService){
        this.proyectoService = proyectoService;
    }
    @GetMapping
    public ResponseEntity<List<ProyectoDTO>> listarProyectos() {
        List<ProyectoDTO> proyectos = proyectoService.findAll();
        return ResponseEntity.ok(proyectos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProyectoDTO> obtenerProyecto(@PathVariable Long id) {
        ProyectoDTO proyecto = proyectoService.findById(id);
        return ResponseEntity.ok(proyecto);
    }

    @PostMapping
    public ResponseEntity<ProyectoDTO> crearProyecto(@RequestBody ProyectoDTO proyectoDTO) {
        ProyectoDTO creado = proyectoService.create(proyectoDTO);
        return ResponseEntity.ok(creado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProyectoDTO> actualizarProyecto(@PathVariable Long id,
                                                          @RequestBody ProyectoDTO proyectoDTO) {
        ProyectoDTO actualizado = proyectoService.update(id, proyectoDTO);
        return ResponseEntity.ok(actualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarProyecto(@PathVariable Long id) {
        proyectoService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
