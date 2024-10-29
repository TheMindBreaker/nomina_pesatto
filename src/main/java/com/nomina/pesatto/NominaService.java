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

    public String registrarEmpleado(EmpleadoDTO empleadoDTO) {
        Empleado empleado = new Empleado(empleadoDTO.getId(), empleadoDTO.getNombre(), empleadoDTO.getSalario());
        empleados.add(empleado);
        return "Empleado registrado con éxito.";
    }

    public String calcularIndividual(String id, int dias) {
        Empleado empleado = empleados.stream()
                .filter(emp -> emp.getId().equals(id))
                .findFirst()
                .orElse(null);

        if (empleado == null) {
            return "Empleado no encontrado.";
        }

        double salarioTotal = empleado.getSalario() * dias;
        return "La nómina de " + empleado.getNombre() + " para " + dias + " días es: $" + salarioTotal;
    }

    public String calcularGrupo(int dias) {
        StringBuilder resultado = new StringBuilder("Nómina de todos los empleados para " + dias + " días:\n");

        for (Empleado empleado : empleados) {
            double salarioTotal = empleado.getSalario() * dias;
            resultado.append("Empleado: ").append(empleado.getNombre())
                     .append(", Nómina: $").append(salarioTotal).append("\n");
        }

        return resultado.toString();
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
