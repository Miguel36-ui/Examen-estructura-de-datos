import java.util.Scanner;
import java.util.ArrayList;
import java.util.HashMap;

public class SistemaEscolarApp {
    private static Scanner scanner = new Scanner(System.in);

    // Estructuras de datos principales
    private static ArrayList<Estudiante> estudiantes = new ArrayList<>();
    private static ArrayList<Libro> libros = new ArrayList<>();
    private static ColaAtencion colaAtencion = new ColaAtencion();
    private static PilaDevoluciones pilaDevoluciones = new PilaDevoluciones();
    private static ArbolCalificaciones arbolCalificaciones = new ArbolCalificaciones();
    private static Materias materias = new Materias();
    private static MapaSalones mapaSalones = new MapaSalones();
    private static HashMap<Libro, Estudiante> prestamos = new HashMap<>(); // Mapa de libros prestados

    public static void main(String[] args) {
        int opcion;
        do {
            mostrarMenu();
            opcion = leerEntero("Seleccione una opción: ");
            System.out.println();

            switch (opcion) {
                case 1 -> registrarEstudiante();
                case 2 -> mostrarEstudiantes();
                case 3 -> agregarLibro();
                case 4 -> mostrarLibros();
                case 5 -> registrarAtencion();
                case 6 -> atenderEstudiante();
                case 7 -> prestarLibro(); // NUEVA OPCIÓN
                case 8 -> registrarDevolucion(); // Ajustada
                case 9 -> mostrarPilaDevoluciones();
                case 10 -> insertarCalificacion();
                case 11 -> mostrarCalificacionesOrdenadas();
                case 12 -> registrarMateria();
                case 13 -> mostrarMaterias();
                case 14 -> conectarSalones();
                case 15 -> mostrarConexionesSalones();
                case 16 -> System.out.println("Saliendo del sistema. ¡Hasta luego!");
                default -> System.out.println("Opción inválida. Intente de nuevo.");
            }
            System.out.println();
        } while (opcion != 16);
    }
    // Muestra el menú principal
    private static void mostrarMenu() {
        System.out.println("----- Menú Sistema Escolar -----");
        System.out.println("1. Registrar estudiante");
        System.out.println("2. Ver lista de estudiantes");
        System.out.println("3. Agregar libro nuevo");
        System.out.println("4. Ver libros nuevos");
        System.out.println("5. Registrar atención en ventanilla");
        System.out.println("6. Atender estudiante");
        System.out.println("7. Prestar libro"); 
        System.out.println("8. Registrar devolución de libro");
        System.out.println("9. Ver pila de devoluciones");
        System.out.println("10. Insertar calificación");
        System.out.println("11. Mostrar calificaciones ordenadas (inorden)");
        System.out.println("12. Registrar materia");
        System.out.println("13. Ver materias registradas");
        System.out.println("14. Conectar salones");
        System.out.println("15. Ver conexiones de un salón");
        System.out.println("16. Salir");
    }

        private static void prestarLibro() {
        if (libros.isEmpty()) {
            System.out.println("No hay libros disponibles.");
            return;
        }

        String titulo = leerCadena("Título del libro a prestar: ");
        Libro libro = buscarLibroPorTitulo(titulo);

        if (libro == null) {
            System.out.println("Libro no encontrado.");
            return;
        }

        if (prestamos.containsKey(libro)) {
            System.out.println("Ese libro ya está prestado actualmente.");
            return;
        }

        if (estudiantes.isEmpty()) {
            System.out.println("No hay estudiantes registrados para prestar el libro.");
            return;
        }

        String idEstudiante = leerCadena("ID del estudiante que recibe el préstamo: ");
        Estudiante estudiante = buscarEstudiantePorID(idEstudiante);

        if (estudiante == null) {
            System.out.println("Estudiante no encontrado.");
        } else {
            prestamos.put(libro, estudiante);
            System.out.println("Libro prestado correctamente a " + estudiante.getNombre());
        }
    }

    // Función auxiliar para leer un entero con mensaje
    private static int leerEntero(String mensaje) {
        System.out.print(mensaje);
        while (!scanner.hasNextInt()) {
            System.out.println("Entrada inválida. Intente de nuevo.");
            System.out.print(mensaje);
            scanner.next();
        }
        int valor = scanner.nextInt();
        scanner.nextLine(); // limpiar buffer
        return valor;
    }

    // Función auxiliar para leer cadena
    private static String leerCadena(String mensaje) {
        System.out.print(mensaje);
        return scanner.nextLine();
    }

    // 1. Registrar estudiante
    private static void registrarEstudiante() {
        System.out.println("Registrar estudiante:");
        String nombre = leerCadena("Nombre: ");
        String id = leerCadena("ID: ");
        String grado = leerCadena("Grado: ");
        String grupo = leerCadena("Grupo: ");

        estudiantes.add(new Estudiante(nombre, id, grado, grupo));
        System.out.println("Estudiante registrado correctamente.");
    }

    // 2. Mostrar estudiantes
    private static void mostrarEstudiantes() {
        if (estudiantes.isEmpty()) {
            System.out.println("No hay estudiantes registrados.");
        } else {
            System.out.println("Lista de estudiantes:");
            for (Estudiante e : estudiantes) {
                System.out.println(e);
            }
        }
    }

    // 3. Agregar libro nuevo
    private static void agregarLibro() {
        System.out.println("Agregar libro nuevo:");
        String titulo = leerCadena("Título: ");
        String autor = leerCadena("Autor: ");

        libros.add(new Libro(titulo, autor));
        System.out.println("Libro agregado correctamente.");
    }

    // 4. Mostrar libros
    private static void mostrarLibros() {
        if (libros.isEmpty()) {
            System.out.println("No hay libros registrados.");
        } else {
            System.out.println("Lista de libros:");
            for (Libro l : libros) {
                System.out.println(l);
            }
        }
    }

    // 5. Registrar atención en ventanilla
    private static void registrarAtencion() {
        System.out.println("Registrar atención en ventanilla:");
        if (estudiantes.isEmpty()) {
            System.out.println("No hay estudiantes registrados para agregar a la cola.");
            return;
        }
        String id = leerCadena("Ingrese ID del estudiante para atender: ");

        Estudiante estudiante = buscarEstudiantePorID(id);
        if (estudiante != null) {
            colaAtencion.agregarEstudiante(estudiante);
            System.out.println("Estudiante agregado a la cola de atención.");
        } else {
            System.out.println("Estudiante no encontrado.");
        }
    }

    // 6. Atender estudiante de la cola
    private static void atenderEstudiante() {
        System.out.println("Atender estudiante:");
        if (colaAtencion.estaVacia()) {
            System.out.println("No hay estudiantes en la cola.");
        } else {
            Estudiante atendido = colaAtencion.atenderEstudiante();
            System.out.println("Estudiante atendido: " + atendido);
        }
    }

    // 7. Registrar devolución de libro
    private static void registrarDevolucion() {
    System.out.println("Registrar devolución de libro:");
    if (libros.isEmpty()) {
        System.out.println("No hay libros registrados.");
        return;
    }
    String titulo = leerCadena("Ingrese título del libro devuelto: ");

    Libro libro = buscarLibroPorTitulo(titulo);
    if (libro != null) {
        // Verifica si el libro realmente está prestado
        if (prestamos.containsKey(libro)) {
            prestamos.remove(libro);  // Quita el préstamo
            pilaDevoluciones.devolverLibro(libro);  // Agrega a la pila de devoluciones
            System.out.println("Libro devuelto correctamente.");
        } else {
            System.out.println("Ese libro no está actualmente prestado.");
        }
    } else {
        System.out.println("Libro no encontrado.");
    }
}


    // 8. Mostrar pila de devoluciones
    private static void mostrarPilaDevoluciones() {
        pilaDevoluciones.mostrarPila();
    }

    // 9. Insertar calificación en el árbol
    private static void insertarCalificacion() {
    if (materias.estaVacio()) {
        System.out.println("No hay materias registradas.");
        System.out.println("Registre materias antes de subir calificaciones (opción 11).");
        return;
    }

    System.out.println("Materias registradas:");
    materias.mostrarMaterias();

    String codigoMateria = leerCadena("Ingrese el código de la materia: ");
    String nombreMateria = materias.obtenerMateria(codigoMateria);
    if (nombreMateria == null) {
        System.out.println("Código de materia no válido.");
        return;
    }

    if (estudiantes.isEmpty()) {
        System.out.println("No hay estudiantes registrados.");
        return;
    }

    System.out.println("Estudiantes registrados:");
    for (Estudiante e : estudiantes) {
        System.out.println("ID: " + e.getId() + " | Nombre: " + e.getNombre() + " | Grado: " + e.getGrado() + " | Grupo: " + e.getGrupo());
    }

    String idEstudiante = leerCadena("Ingrese el ID del estudiante: ");
    Estudiante estudiante = buscarEstudiantePorID(idEstudiante);
    if (estudiante == null) {
        System.out.println("Estudiante no encontrado.");
        return;
    }

    try {
        double calificacion = Double.parseDouble(leerCadena("Ingrese la calificación (0 - 10): "));
        if (calificacion < 0 || calificacion > 10) {
            System.out.println("La calificación debe estar entre 0 y 10.");
            return;
        }

        Calificacion nueva = new Calificacion(estudiante.getNombre(), nombreMateria, calificacion);
        arbolCalificaciones.insertar(nueva);
        System.out.println("Calificación registrada para " + estudiante.getNombre() + " en " + nombreMateria);
    } catch (NumberFormatException e) {
        System.out.println("Entrada inválida. Use números con punto decimal si es necesario.");
    }
}


    // 10. Mostrar calificaciones ordenadas (inorden)
  private static void mostrarCalificacionesOrdenadas() {
    if (arbolCalificaciones.estaVacio()) {
        System.out.println("No hay calificaciones registradas.");
        return;
    }

    if (estudiantes.isEmpty()) {
        System.out.println("No hay estudiantes registrados.");
        return;
    }

    System.out.println("Estudiantes registrados:");
    for (Estudiante e : estudiantes) {
        System.out.println("ID: " + e.getId() + " | Nombre: " + e.getNombre());
    }

    String idEstudiante = leerCadena("Ingrese el ID del estudiante para ver sus calificaciones: ");
    Estudiante estudiante = buscarEstudiantePorID(idEstudiante);

    if (estudiante == null) {
        System.out.println("Estudiante no encontrado.");
        return;
    }

    if (!arbolCalificaciones.tieneCalificaciones(estudiante.getNombre())) {
        System.out.println("Aún no se han registrado calificaciones.");
        return;
    }

    System.out.println("Calificaciones de " + estudiante.getNombre() + ":");
    arbolCalificaciones.mostrarCalificacionesDe(estudiante.getNombre());
}


    // 11. Registrar materia
    private static void registrarMateria() {
        String codigo = leerCadena("Código de materia: ");
        String nombre = leerCadena("Nombre de materia: ");
        materias.registrarMateria(codigo, nombre);
    }

    // 12. Mostrar materias registradas
    private static void mostrarMaterias() {
        materias.mostrarMaterias();
    }

    // 13. Conectar salones
   private static void conectarSalones() {
    String salon1 = leerCadena("Ingrese nombre del primer salón: ");
    String salon2 = leerCadena("Ingrese nombre del segundo salón: ");

    if (salon1.equalsIgnoreCase(salon2)) {
        System.out.println("No se puede conectar un salón consigo mismo.");
        return;
    }

    // Agrega los salones si no existen
    mapaSalones.agregarSalonSiNoExiste(salon1);
    mapaSalones.agregarSalonSiNoExiste(salon2);

    // Verifica si ya están conectados
    if (mapaSalones.yaEstanConectados(salon1, salon2)) {
        System.out.println("Los salones ya están conectados.");
    } else {
        mapaSalones.conectarSalones(salon1, salon2);
        System.out.println("Salones conectados correctamente.");
    }
}


    // 14. Mostrar conexiones de un salón
    private static void mostrarConexionesSalones() {
        String salon = leerCadena("Ingrese nombre del salón para mostrar conexiones: ");
        mapaSalones.mostrarConexiones(salon);
    }


    ////////////////////////////////////////////////////////////////

    // Método auxiliar para buscar estudiante por ID
    private static Estudiante buscarEstudiantePorID(String id) {
        for (Estudiante e : estudiantes) {
            if (e.getId().equalsIgnoreCase(id)) {
                return e;
            }
        }
        return null;
    }

    // Método auxiliar para buscar libro por título
    private static Libro buscarLibroPorTitulo(String titulo) {
        for (Libro l : libros) {
            if (l.getTitulo().equalsIgnoreCase(titulo)) {
                return l;
            }
        }
        return null;
    }
}
