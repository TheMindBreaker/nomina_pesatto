package com.nomina.pesatto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/nomina")
public class NominaController {

    private final NominaService nominaService;

    @Autowired
    public NominaController(NominaService nominaService) {
        this.nominaService = nominaService;
    }

    @PostMapping("/registrarEmpleado")
    public ResponseEntity<?> registrarEmpleado(@RequestBody EmpleadoDTO empleado) {
        return ResponseEntity.ok(nominaService.registrarEmpleado(empleado));
    }
    

    @GetMapping("/calcularIndividual/{id}/{dias}")
    public ResponseEntity<?> calcularIndividual(@PathVariable String id, @PathVariable int dias) {
        return ResponseEntity.ok(nominaService.calcularIndividual(id, dias));
    }

    @GetMapping("/calcularGrupo/{dias}")
    public ResponseEntity<?> calcularGrupo(@PathVariable int dias) {
        return ResponseEntity.ok(nominaService.calcularGrupo(dias));
    }

    @GetMapping("/todos")
    public ResponseEntity<?> listarEmpleados() {
        return ResponseEntity.ok(nominaService.listarEmpleados());
    }

    @GetMapping("/guardar")
    public ResponseEntity<?> guardar() {
        return ResponseEntity.ok(nominaService.guardarJSON());
    }
}
