public class NodoArbol {
    Calificacion calificacion;
    NodoArbol izquierdo, derecho;

    public NodoArbol(Calificacion calificacion) {
        this.calificacion = calificacion;
        this.izquierdo = null;
        this.derecho = null;
    }
}
