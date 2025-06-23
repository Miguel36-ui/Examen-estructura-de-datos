// Clase para representar una calificación con estudiante, materia y valor
public class Calificacion implements Comparable<Calificacion> {
    private String nombreEstudiante;
    private String nombreMateria;
    private double calificacion;

    public Calificacion(String nombreEstudiante, String nombreMateria, double calificacion) {
        this.nombreEstudiante = nombreEstudiante;
        this.nombreMateria = nombreMateria;
        this.calificacion = calificacion;
    }

    // Getters
    public String getNombreEstudiante() {
        return nombreEstudiante;
    }

    public String getNombreMateria() {
        return nombreMateria;
    }

    public double getCalificacion() {
        return calificacion;
    }

    / Para ordenar en el árbol, podemos ordenar primero por nombre de estudiante,
    // y si son iguales, por nombre de materia
    @Override
    public int compareTo(Calificacion otra) {
        int cmp = this.nombreEstudiante.compareToIgnoreCase(otra.nombreEstudiante);
        if (cmp == 0) {
            return this.nombreMateria.compareToIgnoreCase(otra.nombreMateria);
        }
        return cmp;
    }

    @Override
    public String toString() {
        return nombreEstudiante + " - " + nombreMateria + " : " + calificacion;
    }
}
