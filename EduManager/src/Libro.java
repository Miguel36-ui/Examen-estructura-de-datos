// Clase que representa un libro con título y autor
public class Libro {
    private String titulo;
    private String autor;

    // Constructor para inicializar un libro
    public Libro(String titulo, String autor) {
        this.titulo = titulo;
        this.autor = autor;
    }

     // Métodos getter para acceder a los atributos
    public String getTitulo() {
        return titulo;
    }

    public String getAutor() {
        return autor;
    }
public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Libro)) return false;
        Libro otro = (Libro) obj;
        return this.titulo.equalsIgnoreCase(otro.titulo); // Comparación por título
    }

    @Override
    public int hashCode() {
        return titulo.toLowerCase().hashCode();
    }

    @Override
    public String toString() {
        return "Título: " + titulo + ", Autor: " + autor ;
    }
}