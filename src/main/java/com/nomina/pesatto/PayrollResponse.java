package com.nomina.pesatto;

public class PayrollResponse {
    private String id;
    private String nombre;
    private int diasACalcular;
    private double salarioDiario;
    private double retencionISR;
    private double totalAPagar;

    // Constructor
    public PayrollResponse(String id, String nombre, int diasACalcular, double salarioDiario, double retencionISR, double totalAPagar) {
        this.id = id;
        this.nombre = nombre;
        this.diasACalcular = diasACalcular;
        this.salarioDiario = salarioDiario;
        this.retencionISR = retencionISR;
        this.totalAPagar = totalAPagar;
    }

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getDiasACalcular() {
        return diasACalcular;
    }

    public void setDiasACalcular(int diasACalcular) {
        this.diasACalcular = diasACalcular;
    }

    public double getSalarioDiario() {
        return salarioDiario;
    }

    public void setSalarioDiario(double salarioDiario) {
        this.salarioDiario = salarioDiario;
    }

    public double getRetencionISR() {
        return retencionISR;
    }

    public void setRetencionISR(double retencionISR) {
        this.retencionISR = retencionISR;
    }

    public double getTotalAPagar() {
        return totalAPagar;
    }

    public void setTotalAPagar(double totalAPagar) {
        this.totalAPagar = totalAPagar;
    }
}
