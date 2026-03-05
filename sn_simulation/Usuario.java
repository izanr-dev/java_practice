import java.util.ArrayList;
import java.util.List;

/*
 * Clase que representa a un usuario dentro de la red social.
 * @author Izan Robles
 * @author Javier Ruiz
 */
public class Usuario {

    private String nombre;
    private int capacidadAmplificacion;
    private List<Enlace> enlaces;

    /*
     * Constructor completo para la clase Usuario.
     * @param nombre Cadena de caracteres con el nombre del usuario.
     * @param capacidadAmplificacion entero con el número de unidades de influencia.
     */
    public Usuario(String nombre, int capacidadAmplificacion) {
        this.nombre = nombre;
        this.capacidadAmplificacion = capacidadAmplificacion;
        this.enlaces = new ArrayList<>();
    }

    /*
     * Constructor con valor por defecto de 2 para la capacidad de amplificación.
     * @param nombre cadena de caracteres con el nombre del usuario.
     */
    public Usuario(String nombre) {
        this(nombre, 2);
    }

    /*
     * Método para añadir enlaces al usuario.
     * @param e objeto de la clase Enlace.
     * @return true si se añade correctamente, false en caso contrario.
     */
    /*
     * Método para añadir enlaces al usuario.
     * @param e objeto de la clase Enlace.
     * @return true si se añade correctamente, false en caso contrario.
     */
    public boolean addEnlace(Enlace e) {
        if (e == null || e.getUsuarioOrigen() != this) {
            return false; // Filtramos nulos o enlaces que no nacen de este usuario
        }

        if (puedeAnadirEnlaceHacia(e.getUsuarioDestino())) {
            enlaces.add(e);
            return true;
        }
        return false;
    }

    /*
     * Método sobrecargado para añadir enlaces al usuario creando el enlace internamente.
     * @param destino Usuario al que apunta el enlace.
     * @param coste Coste de propagación del enlace.
     * @return true si se añade correctamente, false en caso contrario.
     */
    public boolean addEnlace(Usuario destino, int coste) {
        if (puedeAnadirEnlaceHacia(destino)) {
            Enlace nuevoEnlace = new Enlace(this, destino, coste);
            enlaces.add(nuevoEnlace);
            return true;
        }
        return false;
    }

    /*
     * Getter estándar para el nombre del usuario.
     * @return cadena de caracteres con el nombre.
     */
    public String getNombre() {
        return nombre;
    }

    /*
     * Getter estándar para la capacidad de amplificación.
     * @return entero con la capacidad de amplificación.
     */
    public int getCapacidadAmplificacion() {
        return capacidadAmplificacion;
    }

    /*
     * Accede al i-ésimo enlace de la secuencia de enlaces salientes.
     * @param i entero con el índice del enlace a obtener.
     * @return objeto Enlace en la posición indicada o null si no existe.
     */
    public Enlace getEnlace(int i) {
        if (i >= 0 && i < enlaces.size()) {
            return enlaces.get(i);
        }
        return null;
    }

    /*
     * Obtiene el número total de enlaces salientes.
     * @return entero con la cantidad total de enlaces.
     */
    public int getNumEnlaces() {
        return enlaces.size();
    }

    /*
     * Obtiene el enlace directo desde este usuario hacia otro dado.
     * @param destino objeto Usuario al que apunta el enlace buscado.
     * @return objeto Enlace si existe la conexión, o null en caso contrario.
     */
    public Enlace getEnlace(Usuario destino) {
        for (Enlace e : enlaces) {
            if (e.getUsuarioDestino() == destino) {
                return e;
            }
        }
        return null;
    }

    /*
     * Método auxiliar privado para comprobar si es válido añadir un enlace hacia un destino.
     * Verifica que no haya autorreferencias ni enlaces duplicados al mismo destino.
     * @param destino objeto Usuario a comprobar.
     * @return true si es válido, false si es autorreferencia o ya existe.
     */
    private boolean puedeAnadirEnlaceHacia(Usuario destino) {
        if (this == destino) {
            return false;
        }
        for (Enlace enlaceExistente : enlaces) {
            if (enlaceExistente.getUsuarioDestino() == destino) {
                return false;
            }
        }
        return true;
    }

    /*
     * Genera una representación textual del usuario y sus enlaces.
     * @return String con el formato de usuario y lista de enlaces.
     */
    @Override
    public String toString() {

        String resultado = "@" + this.nombre + "(" + this.capacidadAmplificacion + ")[";

        for (int i = 0; i < enlaces.size(); i++) {
            resultado += enlaces.get(i).toString();

            if (i < enlaces.size() - 1) {
                resultado += ", ";
            }
        }

        resultado += "]";
        return resultado;
    }
}