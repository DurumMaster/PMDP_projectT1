package dam.pmdm.evaluaciont1_2;

import java.io.Serializable;

public class Alumno implements Serializable {
    private static final long serialID = 1L;

    private String nombre;
    private String asignatura;
    private double notaExamenes;
    private double notaActividades;
    private double notaFinal;

    public Alumno(String nombre, String asignatura, double notaExamenes, double notaActividades, double notaFinal) {
        this.nombre = nombre;
        this.asignatura = asignatura;
        this.notaExamenes = notaExamenes;
        this.notaActividades = notaActividades;
        this.notaFinal = notaFinal;
    }

    public double getNotaFinal() {
        return notaFinal;
    }

    public double getNotaActividades() {
        return notaActividades;
    }

    public double getNotaExamenes() {
        return notaExamenes;
    }

    public String getAsignatura() {
        return asignatura;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNotaExamenes(double notaExamenes) {
        this.notaExamenes = notaExamenes;
    }

    public void setNotaActividades(double notaActividades) {
        this.notaActividades = notaActividades;
    }

    public void setNotaFinal(double notaFinal) {
        this.notaFinal = notaFinal;
    }
}