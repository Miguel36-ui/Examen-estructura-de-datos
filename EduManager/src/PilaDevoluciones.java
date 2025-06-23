import java.util.Stack;

// Clase que gestiona la devolución de libros usando una pila (LIFO)
public class PilaDevoluciones {
    private Stack<Libro> pila;

    // Constructor que inicializa la pila
    public PilaDevoluciones() {
        pila = new Stack<>();
    }

    // Agrega un libro a la pila (devolución de un libro)
    public void devolverLibro(Libro libro) {
        pila.push(libro);
    }

    // Elimina y retorna el último libro devuelto (el más reciente)
    public Libro procesarDevolucion() {
        if (!pila.isEmpty()) {
            return pila.pop();
        } else {
            System.out.println("No hay libros para devolver.");
            return null;
        }
    }

    // Verifica si la pila está vacía
    public boolean estaVacia() {
        return pila.isEmpty();
    }

    // Muestra todos los libros en la pila (orden de devoluciones)
    public void mostrarPila() {
        if (pila.isEmpty()) {
            System.out.println("No hay libros en la pila de devoluciones.");
        } else {
            System.out.println("Pila de devoluciones:");
            for (int i = pila.size() - 1; i >= 0; i--) {
                System.out.println(pila.get(i));
            }
        }
    }
}
