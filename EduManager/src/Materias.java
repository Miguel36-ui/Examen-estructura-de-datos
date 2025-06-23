import java.util.HashMap;
import java.util.Map;

public class Materias {
    private HashMap<String, String> mapaMaterias;

    public Materias() {
        mapaMaterias = new HashMap<>();
    }

    // Verifica si el mapa de materias está vacío
    public boolean estaVacio() {
        return mapaMaterias.isEmpty();
    }

    // Agrega una materia con código y nombre
    public void agregarMateria(String codigo, String nombre) {
        mapaMaterias.put(codigo, nombre);
    }

    // Obtiene el nombre de la materia a partir de su código
    public String obtenerMateria(String codigo) {
        return mapaMaterias.get(codigo);
    }

    // Método para mostrar todas las materias
    public void mostrarMaterias() {
        for (Map.Entry<String, String> entry : mapaMaterias.entrySet()) {
            System.out.println(entry.getKey() + " - " + entry.getValue());
        }
    }

public void registrarMateria(String codigo, String nombre) {
    if(codigo == null || codigo.isEmpty() || nombre == null || nombre.isEmpty()) {
        System.out.println("El código y el nombre de la materia no pueden estar vacíos.");
        return;
    }
    mapaMaterias.put(codigo, nombre);
}

}
