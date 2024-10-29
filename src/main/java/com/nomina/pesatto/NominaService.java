package com.nomina.pesatto;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class NominaService {

	private final List<Empleado> empleados = new ArrayList<>();
    private static final String FILE_PATH = "empleados.json"; // Define the path for JSON file
    private final ObjectMapper objectMapper = new ObjectMapper(); // Initialize ObjectMapper


    // ISR retention rates (example values)
    private double calculateISR(double salary) {
        if (salary <= 7735.0) return 0.0192;  // 1.92%
        if (salary <= 65651.07) return 0.064; // 6.4%
        if (salary <= 115375.9) return 0.1088; // 10.88%
        if (salary <= 134119.41) return 0.16; // 16%
        return 0.35; // 35% for higher salaries
    }

    public String registrarEmpleado(EmpleadoDTO empleadoDTO) {
        Empleado empleado = new Empleado(empleadoDTO.getId(), empleadoDTO.getNombre(), empleadoDTO.getSalario());
        empleados.add(empleado);
        return "Empleado registrado con éxito.";
    }

    public PayrollResponse calcularIndividual(String id, int dias) {
        Empleado empleado = empleados.stream()
                .filter(emp -> emp.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Empleado no encontrado."));

        double salarioDiario = empleado.getSalario();
        double grossSalary = salarioDiario * dias;
        double isrRetention = grossSalary * calculateISR(salarioDiario);
        double totalAPagar = grossSalary - isrRetention;

        return new PayrollResponse(
                empleado.getId(),
                empleado.getNombre(),
                dias,
                salarioDiario,
                isrRetention,
                totalAPagar
        );
    }

    public List<PayrollResponse> calcularGrupo(int dias) {
        List<PayrollResponse> payrolls = new ArrayList<>();

        for (Empleado empleado : empleados) {
            double salarioDiario = empleado.getSalario();
            double grossSalary = salarioDiario * dias;
            double isrRetention = grossSalary * calculateISR(salarioDiario);
            double totalAPagar = grossSalary - isrRetention;

            payrolls.add(new PayrollResponse(
                    empleado.getId(),
                    empleado.getNombre(),
                    dias,
                    salarioDiario,
                    isrRetention,
                    totalAPagar
            ));
        }

        return payrolls;
    }


    public List<Empleado> listarEmpleados() {
        return empleados;
    }


    
    
    public String guardarJSON() {
        try {
            objectMapper.writeValue(new File(FILE_PATH), empleados);
            return "Datos guardados en empleados.json";
        } catch (IOException e) {
            return "Error al guardar datos: " + e.getMessage();
        }
    }

    public String cargarJSON() {
        try {
            List<Empleado> loadedEmpleados = objectMapper.readValue(new File(FILE_PATH), new TypeReference<List<Empleado>>() {});
            empleados.clear();
            empleados.addAll(loadedEmpleados);
            return "Datos cargados desde empleados.json";
        } catch (IOException e) {
            return "Error al cargar datos: " + e.getMessage();
        }
    }
}
