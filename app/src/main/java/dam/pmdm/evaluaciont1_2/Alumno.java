package dam.pmdm.evaluaciont1_2;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;

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

    public static ArrayList<Alumno> cargaInicialAlumnosTxt() {
        return new ArrayList<>(Arrays.asList(
                // Alumnos de "DI"
                new Alumno("Bernardino Font García", "DI", 0, 0, 0),
                new Alumno("Alvaro García Guimaraens", "DI", 0, 0, 0),
                new Alumno("Mario Gómez Pérez", "DI", 0, 0, 0),
                new Alumno("Carlos Hernández Borja", "DI", 0, 0, 0),
                new Alumno("Axel León Arroyo", "DI", 0, 0, 0),
                new Alumno("Camilo Armando Maita Bracamonte", "DI", 0, 0, 0),
                new Alumno("Oscar Martín García", "DI", 0, 0, 0),
                new Alumno("Guillermo Martín Muñoz", "DI", 0, 0, 0),
                new Alumno("Clara Nuevo Mota", "DI", 0, 0, 0),
                new Alumno("Alejandro Martínez Bravo", "DI", 0, 0, 0),
                new Alumno("Adrian Ignacio Ocaña de Frutos", "DI", 0, 0, 0),
                new Alumno("Lucas René Oliveira Urrutia", "DI", 0, 0, 0),
                new Alumno("Alejandro Ramírez Gómez", "DI", 0, 0, 0),
                new Alumno("Roberto Ruíz", "DI", 0, 0, 0),
                new Alumno("Angel Santana Aguilera", "DI", 0, 0, 0),
                new Alumno("Cindy Vanessa Taboada Guerra", "DI", 0, 0, 0),

                // Alumnos de "SGE"
                new Alumno("Víctor Anguita Martínez de Velasco", "SGE", 0, 0, 0),
                new Alumno("Miguel Cañizares Chichon", "SGE", 0, 0, 0),
                new Alumno("Nander Antonio Cueva Machuca", "SGE", 0, 0, 0),
                new Alumno("Bony Pablo Diez Ateca", "SGE", 0, 0, 0),
                new Alumno("Ana Coral Fernández Arteta", "SGE", 0, 0, 0),
                new Alumno("Ignacio Fernández Periáñez", "SGE", 0, 0, 0),
                new Alumno("Bernardino Font García", "SGE", 0, 0, 0),
                new Alumno("Alvaro García Guimaraens", "SGE", 0, 0, 0),
                new Alumno("Mario Gómez Pérez", "SGE", 0, 0, 0),
                new Alumno("Carlos Hernández Borja", "SGE", 0, 0, 0),
                new Alumno("Axel León Arroyo", "SGE", 0, 0, 0),
                new Alumno("Camilo Armando Maita Bracamonte", "SGE", 0, 0, 0),
                new Alumno("Oscar Martín García", "SGE", 0, 0, 0),
                new Alumno("Guillermo Martín Muñoz", "SGE", 0, 0, 0),
                new Alumno("Clara Nuevo Mota", "SGE", 0, 0, 0),
                new Alumno("Alejandro Martínez Bravo", "SGE", 0, 0, 0),
                new Alumno("Adrian Ignacio Ocaña de Frutos", "SGE", 0, 0, 0),
                new Alumno("Lucas René Oliveira Urrutia", "SGE", 0, 0, 0),
                new Alumno("Alejandro Ramírez Gómez", "SGE", 0, 0, 0),
                new Alumno("Roberto Ruíz", "SGE", 0, 0, 0),
                new Alumno("Angel Santana Aguilera", "SGE", 0, 0, 0),
                new Alumno("Cindy Vanessa Taboada Guerra", "SGE", 0, 0, 0),

                // Alumnos de "IACC"
                new Alumno("Víctor Anguita Martínez de Velasco", "IACC", 0, 0, 0),
                new Alumno("Miguel Cañizares Chichon", "IACC", 0, 0, 0),
                new Alumno("Nander Antonio Cueva Machuca", "IACC", 0, 0, 0),
                new Alumno("Bony Pablo Diez Ateca", "IACC", 0, 0, 0),
                new Alumno("Ana Coral Fernández Arteta", "IACC", 0, 0, 0),
                new Alumno("Ignacio Fernández Periáñez", "IACC", 0, 0, 0),
                new Alumno("Bernardino Font García", "IACC", 0, 0, 0),
                new Alumno("Alvaro García Guimaraens", "IACC", 0, 0, 0),
                new Alumno("Mario Gómez Pérez", "IACC", 0, 0, 0),
                new Alumno("Carlos Hernández Borja", "IACC", 0, 0, 0),
                new Alumno("Axel León Arroyo", "IACC", 0, 0, 0),
                new Alumno("Camilo Armando Maita Bracamonte", "IACC", 0, 0, 0),
                new Alumno("Oscar Martín García", "IACC", 0, 0, 0),
                new Alumno("Guillermo Martín Muñoz", "IACC", 0, 0, 0),
                new Alumno("Clara Nuevo Mota", "IACC", 0, 0, 0),
                new Alumno("Alejandro Martínez Bravo", "IACC", 0, 0, 0),
                new Alumno("Adrian Ignacio Ocaña de Frutos", "IACC", 0, 0, 0),
                new Alumno("Lucas René Oliveira Urrutia", "IACC", 0, 0, 0),
                new Alumno("Alejandro Ramírez Gómez", "IACC", 0, 0, 0),
                new Alumno("Roberto Ruíz", "IACC", 0, 0, 0),
                new Alumno("Angel Santana Aguilera", "IACC", 0, 0, 0),
                new Alumno("Cindy Vanessa Taboada Guerra", "IACC", 0, 0, 0),

                // Alumnos de "IOS"
                new Alumno("Víctor Anguita Martínez de Velasco", "IOS", 0, 0, 0),
                new Alumno("Miguel Cañizares Chichon", "IOS", 0, 0, 0),
                new Alumno("Nander Antonio Cueva Machuca", "IOS", 0, 0, 0),
                new Alumno("Bony Pablo Diez Ateca", "IOS", 0, 0, 0),
                new Alumno("Ana Coral Fernández Arteta", "IOS", 0, 0, 0),
                new Alumno("Ignacio Fernández Periáñez", "IOS", 0, 0, 0),
                new Alumno("Bernardino Font García", "IOS", 0, 0, 0),
                new Alumno("Alvaro García Guimaraens", "IOS", 0, 0, 0),
                new Alumno("Mario Gómez Pérez", "IOS", 0, 0, 0),
                new Alumno("Carlos Hernández Borja", "IOS", 0, 0, 0),
                new Alumno("Axel León Arroyo", "IOS", 0, 0, 0),
                new Alumno("Camilo Armando Maita Bracamonte", "IOS", 0, 0, 0),
                new Alumno("Oscar Martín García", "IOS", 0, 0, 0),
                new Alumno("Guillermo Martín Muñoz", "IOS", 0, 0, 0),
                new Alumno("Clara Nuevo Mota", "IOS", 0, 0, 0),
                new Alumno("Alejandro Martínez Bravo", "IOS", 0, 0, 0),
                new Alumno("Adrian Ignacio Ocaña de Frutos", "IOS", 0, 0, 0),
                new Alumno("Lucas René Oliveira Urrutia", "IOS", 0, 0, 0),
                new Alumno("Alejandro Ramírez Gómez", "IOS", 0, 0, 0),
                new Alumno("Roberto Ruíz", "IOS", 0, 0, 0),
                new Alumno("Angel Santana Aguilera", "IOS", 0, 0, 0),
                new Alumno("Cindy Vanessa Taboada Guerra", "IOS", 0, 0, 0)
        ));
    }
}