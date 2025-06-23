import java.util.LinkedList;
import java.util.Queue;

// Clase que gestiona la atención en ventanilla usando una cola (FIFO)
public class ColaAtencion {
    private Queue<Estudiante> cola;

    // Constructor que inicializa la cola usando LinkedList
    public ColaAtencion() {
        cola = new LinkedList<>();
    }

    // Agrega un estudiante al final de la cola
    public void agregarEstudiante(Estudiante estudiante) {
        cola.add(estudiante);
    }

    // Atiende (elimina y retorna) al primer estudiante de la cola
    public Estudiante atenderEstudiante() {
        return cola.poll(); // retorna null si está vacía
    }

    // Verifica si la cola está vacía
    public boolean estaVacia() {
        return cola.isEmpty();
    }

    // Muestra la lista de estudiantes en espera
    public void mostrarCola() {
        if (cola.isEmpty()) {
            System.out.println("No hay estudiantes en espera.");
        } else {
            System.out.println("Estudiantes en la cola:");
            for (Estudiante e : cola) {
                System.out.println(e);
            }
        }
    }
}
