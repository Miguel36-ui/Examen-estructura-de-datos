import java.util.*;

// Clase que representa un grafo no dirigido de conexiones entre salones
public class MapaSalones {
    // Mapa de adyacencia: cada salón está conectado con una lista de otros salones
    private Map<String, List<String>> grafo;

    // Constructor que inicializa el grafo como un HashMap vacío
    public MapaSalones() {
        grafo = new HashMap<>();
    }

    // Conecta dos salones en ambas direcciones (grafo no dirigido)
    public void conectarSalones(String salon1, String salon2) {
        grafo.putIfAbsent(salon1, new ArrayList<>());
        grafo.putIfAbsent(salon2, new ArrayList<>());

        // Añade la conexión de ambos lados
        grafo.get(salon1).add(salon2);
        grafo.get(salon2).add(salon1);
    }

    // Muestra todas las conexiones de un salón específico
    public void mostrarConexiones(String salon) {
        List<String> conexiones = grafo.get(salon);

        if (conexiones == null || conexiones.isEmpty()) {
            System.out.println("El salón '" + salon + "' no tiene conexiones registradas.");
        } else {
            System.out.println("Salón '" + salon + "' está conectado con:");
            for (String conectado : conexiones) {
                System.out.println(" - " + conectado);
            }
        }
    }

    // Muestra todas las conexiones registradas del grafo
    public void mostrarTodasLasConexiones() {
        if (grafo.isEmpty()) {
            System.out.println("No hay salones conectados aún.");
            return;
        }

        System.out.println("Conexiones entre salones:");
        for (String salon : grafo.keySet()) {
            System.out.print(salon + " -> ");
            System.out.println(grafo.get(salon));
        }
    }

    public void agregarSalonSiNoExiste(String nombre) {
    grafo.putIfAbsent(nombre, new ArrayList<>());
}

public boolean yaEstanConectados(String s1, String s2) {
    return grafo.containsKey(s1) && grafo.get(s1).contains(s2);
}


}
