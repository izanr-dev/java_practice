import java.time.Year;
import java.util.List;

/**
 * Clase de prueba para verificar la funcionalidad de la Biblioteca.
 * Comprueba filtrado por género, fecha y correcta impresión.
 * @author Izan Robles
 * @author Javier Ruiz
 * @version 1.0
 * Nombre de fichero: PruebaBiblioteca.java
 */
public class PruebaBiblioteca {
    
    /**
     * Punto de entrada para testar biblioteca
     */
    public static void main(String[] args) {
        System.out.println("Creando biblioteca...");
        Biblioteca biblio = new Biblioteca("Biblioteca UAM");

        // Metemos datos (algunos con género y año, otros sin ello para probar compatibilidad)
        Libro l1 = new Libro("111", "Dune", "Frank Herbert", 5, Year.of(1965), "Ciencia Ficción");
        Libro l2 = new Libro("222", "El Hobbit", "J.R.R. Tolkien", 3, Year.of(1937), "Fantasía");
        Libro l3 = new Libro("333", "Fahrenheit 451", "Ray Bradbury", 2, Year.of(1953), "Ciencia Ficción");
        Libro l4 = new Libro("444", "La Biblia", "Varios Autores", 1);

        biblio.anadirLibro(l1);
        biblio.anadirLibro(l2);
        biblio.anadirLibro(l3);
        biblio.anadirLibro(l4); 

        //Imprimir biblioteca entera
        System.out.println("\n--- ESTADO ACTUAL DE LA BIBLIOTECA ---");
        System.out.println(biblio); //Llama a toString de Biblioteca

        //Probamos filtrando por un genero existente
        System.out.println("--- LIBROS DE CIENCIA FICCIÓN ---");
        List<Libro> librosEncontradosCF = biblio.librosPorGenero("Ciencia Ficción");
        for (Libro libro : librosEncontradosCF) {
            System.out.println("Encontrado: " + libro);
        }

        //Probamos a filtrar por un genero en el que no tenemos libros
        System.out.println("\n--- LIBROS DE TERROR ---");
        List<Libro> librosEncontradosTR = biblio.librosPorGenero("Terror");
        if (librosEncontradosTR.isEmpty()) {
            System.out.println("Correcto: No hay libros de terror.");
        } else {
            System.out.println("ERROR: Ha devuelto algo que no debía.");
        }

        //Probamos filtrando por año
        System.out.println("\n--- LIBROS POSTERIORES A 1960 ---");
        List<Libro> librosPorPublicacion = biblio.librosPosterioresA(1960);
        for (Libro libro : librosPorPublicacion) {
            System.out.println("Posterior a 1960: " + libro);
        }
    }
}