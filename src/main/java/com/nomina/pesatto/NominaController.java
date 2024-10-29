package com.nomina.pesatto;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

    @PostMapping("/registrar")
    public ResponseEntity<ApiResponse<?>> registrarEmpleado(@RequestBody EmpleadoDTO empleado) {
        try {
            String message = nominaService.registrarEmpleado(empleado);
            return ResponseEntity.ok(new ApiResponse<>(null, message, null));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse<>(null, null, e.getMessage()));
        }
    }

    @GetMapping("/calcularIndividual/{id}/{dias}")
    public ResponseEntity<ApiResponse<?>> calcularIndividual(@PathVariable String id, @PathVariable int dias) {
        try {
            PayrollResponse payroll = nominaService.calcularIndividual(id, dias);
            return ResponseEntity.ok(new ApiResponse<>(payroll, "Calculation successful", null));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse<>(null, null, e.getMessage()));
        }
    }

    @GetMapping("/calcularGrupo/{dias}")
    public ResponseEntity<ApiResponse<?>> calcularGrupo(@PathVariable int dias) {
        try {
            List<PayrollResponse> payrolls = nominaService.calcularGrupo(dias);
            return ResponseEntity.ok(new ApiResponse<>(payrolls, "Group calculation successful", null));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse<>(null, null, e.getMessage()));
        }
    }


    @GetMapping("/todos")
    public ResponseEntity<ApiResponse<?>> getAllEmployees() {
        try {
            List<Empleado> employees = nominaService.listarEmpleados();
            return ResponseEntity.ok(new ApiResponse<>(employees, "Employees retrieved successfully", null));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse<>(null, null, e.getMessage()));
        }
    }

    @GetMapping("/guardar")
    public ResponseEntity<?> guardar() {
        return ResponseEntity.ok(nominaService.guardarJSON());
    }
}
