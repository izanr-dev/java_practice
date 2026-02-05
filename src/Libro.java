//Por funcionalidad creímos conveniente añadir esta librería para el año de publicación
import java.time.Year;

/**
 * Clase Libro con información relevante del ejemplar.
 * @author Izan Robles
 * @author Javier Ruiz
 * @version 1.2
 * Nombre del fichero: Libro.java
 * */
public class Libro {
    private String isbn;
    private String titulo;
    private String autor;
    private int ejemplaresDisponibles;
    private Year publicacion;
    private String genero;

    /**
     * Constructor estándar para Libro.
     * @param isbn Indica el identificador único ISBN de cada Libro.
     * @param titulo Nombre del libro.
     * @param autor Nombre y apellidos del autor del libro.
     * @param ejemplaresDisponibles Número de ejemplares actualmente en la biblioteca.
     */
    public Libro(String isbn, String titulo, String autor, int ejemplaresDisponibles) {
        this.isbn = isbn;
        this.titulo = titulo;
        this.autor = autor;
        this.ejemplaresDisponibles = ejemplaresDisponibles;
        this.publicacion = null;
        this.genero = null;
    }

    /**
     * Constructor ampliado para Libro.
     * @param isbn Indica el identificador único ISBN de cada Libro.
     * @param titulo Nombre del libro.
     * @param autor Nombre y apellidos del autor del libro.
     * @param ejemplaresDisponibles Número de ejemplares actualmente en la biblioteca.
     * @param publicacion Año de publicacion del libro
     * @param genero Clasificación de género para el libro según su contenido
     */
    public Libro(String isbn, String titulo, String autor, int ejemplaresDisponibles, Year publicacion, String genero) {
        this.isbn = isbn;
        this.titulo = titulo;
        this.autor = autor;
        this.ejemplaresDisponibles = ejemplaresDisponibles;
        this.publicacion = publicacion;
        this.genero = genero;
    }

    /**
     * Método para obtener el año de publicación de un libro.
     * @return Dato tipo Year con el año de publicación del libro. 
     */
    public Year obtenerAñoPublicacion() {
        return this.publicacion;
    }

    /**
     * Método para obtener el genero de un libro
     * @return String con el genero de llibro. 
     */
    public String obtenerGenero() {
        return this.genero;
    }

    /**
     * Método para verificar si el libro está disponible.
     * @return 'true' si hay al menos un ejemplar disponible. En caso contrario 'false'. 
     */
    public boolean estaDisponible() {
        return this.ejemplaresDisponibles > 0;
    }

    /**
     * Método para prestar el libro.
     * @return 'true' si se ha efectuado el préstamo, 'false' si no había ejemplares disponibles. 
     */
    public boolean prestar() {
        if (estaDisponible()) {
            this.ejemplaresDisponibles--;
            return true;
        }
        return false;
    }

    /**
     * Método para devoler el libro a la biblioteca. 
     */
    public void devolver() {
        this.ejemplaresDisponibles++;
    }

    /**
     * Método para obtener la descripción del libro
     * @return String con información organizada básica sobre el libro y su disponibilidad.
     */
    private String descripcion() {
        String estado = this.estaDisponible() ? "Disponible" : "No disponible";
        return "'" + this.titulo + "' de " + this.autor + " [" + estado + "] ";
    }

    /**
     * Método para sintetizar información del libro
     * @return String con información amplida y controlada sobre el libro y su disponibilidad. 
     */
    @Override
    public String toString() {
        if (this.genero != null && this.publicacion != null) {
            return "ISBN: " + this.isbn + ". " + this.descripcion() + "Año de publicacion: " + this.publicacion + " Género: " + this.genero + " (" + this.ejemplaresDisponibles + " ejemplares disponibles)";
        }
        return "ISBN: " + this.isbn + ". " + this.descripcion() + " (" + this.ejemplaresDisponibles + " ejemplares disponibles)";
    }

}


