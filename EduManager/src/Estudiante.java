// Clase que representa a un estudiante con sus datos básicos
public class Estudiante {
    private String nombre;
    private String id;
    private String grado;
    private String grupo;

    // Constructor para inicializar los atributos del estudiante
    public Estudiante(String nombre, String id, String grado, String grupo) {
        this.nombre = nombre;
        this.id = id;
        this.grado = grado;
        this.grupo = grupo;
    }

      // Métodos getter para acceder a los atributos
    public String getNombre() {
        return nombre;
    }

    public String getId() {
        return id;
    }

    public String getGrado() {
        return grado;
    }

    public String getGrupo() {
        return grupo;
    }

    // Método toString para mostrar la información del estudiante
    @Override
    public String toString() {
        return "Estudiante{" +
                "nombre='" + nombre + '\'' +
                ", id='" + id + '\'' +
                ", grado='" + grado + '\'' +
                ", grupo='" + grupo + '\'' +
                '}';
    }
}

