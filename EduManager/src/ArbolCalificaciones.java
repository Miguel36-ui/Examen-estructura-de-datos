// Nodo del árbol binario para almacenar calificaciones
class NodoCalificacion {
    Calificacion dato;
    NodoCalificacion izquierdo, derecho;

    public NodoCalificacion(Calificacion dato) {
        this.dato = dato;
        izquierdo = derecho = null;
    }
}

// Árbol binario de búsqueda para calificaciones
public class ArbolCalificaciones {
    private NodoCalificacion raiz;

    public ArbolCalificaciones() {
        raiz = null;
    }

    // Método para insertar calificaciones
    public void insertar(Calificacion calif) {
        raiz = insertarRec(raiz, calif);
    }

    private NodoCalificacion insertarRec(NodoCalificacion nodo, Calificacion calif) {
        if (nodo == null) {
            return new NodoCalificacion(calif);
        }

        if (calif.compareTo(nodo.dato) < 0) {
            nodo.izquierdo = insertarRec(nodo.izquierdo, calif);
        } else if (calif.compareTo(nodo.dato) > 0) {
            nodo.derecho = insertarRec(nodo.derecho, calif);
        } else {
            // Si la calificación ya existe para ese estudiante y materia, puedes actualizarla o ignorar
            // Aquí por simplicidad, ignoramos duplicados
        }
        return nodo;
    }

    // Método para mostrar el recorrido inorden (ordenado)
    public void mostrarInorden() {
        mostrarInordenRec(raiz);
    }

    private void mostrarInordenRec(NodoCalificacion nodo) {
        if (nodo != null) {
            mostrarInordenRec(nodo.izquierdo);
            System.out.println(nodo.dato);
            mostrarInordenRec(nodo.derecho);
        }
    }

    // Verifica si el árbol está vacío
    public boolean estaVacio() {
        return raiz == null;
    }

    // Verifica si un estudiante tiene al menos una calificación registrada
    public boolean tieneCalificaciones(String nombreEstudiante) {
        return tieneCalificacionesRec(raiz, nombreEstudiante);
    }

    private boolean tieneCalificacionesRec(NodoCalificacion nodo, String nombreEstudiante) {
        if (nodo == null) return false;
        if (nodo.dato.getNombreEstudiante().equalsIgnoreCase(nombreEstudiante)) return true;
        return tieneCalificacionesRec(nodo.izquierdo, nombreEstudiante) || tieneCalificacionesRec(nodo.derecho, nombreEstudiante);
    }

    // Muestra todas las calificaciones de un estudiante
    public void mostrarCalificacionesDe(String nombreEstudiante) {
        mostrarCalificacionesDeRec(raiz, nombreEstudiante);
    }

    private void mostrarCalificacionesDeRec(NodoCalificacion nodo, String nombreEstudiante) {
        if (nodo == null) return;
        mostrarCalificacionesDeRec(nodo.izquierdo, nombreEstudiante);
        if (nodo.dato.getNombreEstudiante().equalsIgnoreCase(nombreEstudiante)) {
            System.out.println(nodo.dato);
        }
        mostrarCalificacionesDeRec(nodo.derecho, nombreEstudiante);
    }
}
