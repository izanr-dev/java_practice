import java.util.*;

/**
 * Clase tipo Biblioteca con nombre y mapa con pares género (String) y libro (Libro).
 * @author Izan Robles
 * @author Javier Ruiz
 * @version 1.0
 * Nombre del fichero Biblioteca.java
 */
public class Biblioteca {
    private String nombre;
    private Map<String, List<Libro>> catalogo;

    /**
     * Constructor de Biblioteca
     * @param nombre String con el nombre deseado para la biblioteca
     */
    public Biblioteca (String nombre) {
        this.nombre = nombre;
        this.catalogo = new HashMap<>();
    }

    /**
     * Añade un libro al catálogo clasificándolo por su género.
     * @param libro El libro que se va a añadir a la biblioteca.
     */
    public void anadirLibro(Libro libro) {
        String genero = libro.obtenerGenero();
        
        if (genero == null) {
            genero = "Sin clasificar";
        }

        if (!this.catalogo.containsKey(genero)) {
            this.catalogo.put(genero, new ArrayList<>());
        }

        List<Libro> listaDeEseGenero = this.catalogo.get(genero);

        listaDeEseGenero.add(libro);
    }

    /**
     * Método para encontrar todos los libros de un género específico
     * @param genero String con el género a buscar
     * @return Lista de libros con dicho género
     */
    public List<Libro> librosPorGenero (String genero) {
        List<Libro> librosEncontrados = this.catalogo.get(genero);

        //Para que no falle al introducir un género nuevo
        if (librosEncontrados == null) {
            return new ArrayList<>();
        }

        return librosEncontrados;
    }

    /**
     * Método para obtener libros posteriores a un año determinado
     * @param publicacion Año sobre el que efectuar la búsqueda
     * @return Lista con los datos Libro posteriores a dicho año
     */
    public List<Libro> librosPosterioresA (int publicacion) {
        List<Libro> librosEncontrados = new ArrayList<Libro>();
        
        //Recorre todos los géneros de cada mapa y devuelve cada lista de libros
        for (List<Libro> librosDeUnGenero : this.catalogo.values()) {

            //Recorre la lista de libros de cada clave
            for (Libro libro : librosDeUnGenero) {
                
                if (libro.obtenerAñoPublicacion() != null && libro.obtenerAñoPublicacion().getValue() > publicacion) {
                    librosEncontrados.add(libro);
                }
            }
        }

        return librosEncontrados;
    }

    /**
     * Método para recopilar toda la información de la biblioteca.
     * @return String con toda la información debidamente ordenada, lista para imprimir.
     */
    @Override
    public String toString () {
        
        StringBuilder toPrint = new StringBuilder();
        
        toPrint.append("Biblioteca: ").append(this.nombre).append("\n");

        //Recorremos lista de claves de mapas para mostrar géneros
        for (String genero : this.catalogo.keySet()) {
            toPrint.append("Género: ").append(genero).append("\n");

            //Obtenemos lista de libros para esa clave y la añadimos
            List<Libro> librosDeUnGenero = this.catalogo.get(genero);

            //Iteramos sobre los libros de un genero y recopilamos información
            for (Libro libro : librosDeUnGenero) {
                toPrint.append(" -").append(libro.toString()).append("\n");
            }
        }

        return toPrint.toString();
    }
}