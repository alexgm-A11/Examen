package com.example.repaso.controller.general;

import com.example.repaso.dto.AlumnoDTO;
import com.example.repaso.dto.CategoriaDTO;
import com.example.repaso.service.service.AlumnoService;
import org.hibernate.service.spi.ServiceException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/alumnos")
public class AlumnoController {
    private final AlumnoService alumnoService;

    public AlumnoController(AlumnoService alumnoService) {
        this.alumnoService = alumnoService;
    }
    @GetMapping
    public ResponseEntity<List<AlumnoDTO>> listAll() throws ServiceException {
        return ResponseEntity.ok(alumnoService.findAll());
    }
}
