/*
 * Clase que representa un mensaje que se propaga por la red social.
 * @author Izan Robles
 * @author Javier Ruiz
 */
public class Mensaje {

    private Usuario autor;
    private int alcance;
    private Usuario usuarioActual;
    private String texto;

    /*
     * Constructor para la clase Mensaje.
     * @param texto String con el contenido del mensaje.
     * @param alcance entero con el valor inicial de alcance disponible.
     * @param autor objeto Usuario que representa al creador del mensaje.
     */
    public Mensaje(String texto, int alcance, Usuario autor) {
        this.texto = texto;
        this.alcance = alcance;
        this.autor = autor;
        this.usuarioActual = autor;
    }

    /*
     * Getter estándar para el autor del mensaje.
     * @return objeto Usuario que creó el mensaje.
     */
    public Usuario getAutor() {
        return autor;
    }

    /*
     * Getter estándar para el alcance actual.
     * @return entero con el alcance disponible del mensaje.
     */
    public int getAlcance() {
        return alcance;
    }

    /*
     * Getter estándar para el usuario donde se encuentra el mensaje.
     * @return objeto Usuario que tiene el mensaje actualmente.
     */
    public Usuario getUsuarioActual() {
        return usuarioActual;
    }

    /*
     * Comprueba si el mensaje tiene alcance suficiente para atravesar un enlace.
     * @param enlace objeto Enlace a evaluar.
     * @return true si el alcance es mayor o igual al coste real del enlace.
     */
    public boolean puedeDifundirPor(Enlace enlace) {
        if (enlace == null) {
            return false;
        }
        return this.alcance >= enlace.costeReal();
    }

    /*
     * Comprueba si el usuario destino puede aceptar el mensaje.
     * @param u objeto Usuario destino a evaluar.
     * @return true siempre.
     */
    public boolean aceptadoPor(Usuario u) {
        return true;
    }

    /*
     * Intenta difundir el mensaje a través del enlace indicado.
     * @param enlace objeto Enlace por el que se intentará transmitir el mensaje.
     * @return true si la difusión es exitosa, false en caso contrario.
     */
    public boolean difunde(Enlace enlace) {
        if (enlace == null) {
            return false;
        }

        Enlace enlaceValido = this.usuarioActual.getEnlace(enlace.getUsuarioDestino());

        if (enlaceValido == enlace && puedeDifundirPor(enlace) && aceptadoPor(enlace.getUsuarioDestino())) {
            this.usuarioActual = enlace.getUsuarioDestino();
            this.alcance -= enlace.costeReal();
            this.alcance += this.usuarioActual.getCapacidadAmplificacion();
            return true;
        }

        return false;
    }

    /*
     * Intenta difundir el mensaje a través de una secuencia de usuarios especificada.
     * @param destinos secuencia variable de objetos Usuario que representan la ruta prevista.
     * @return true si logra transmitirse a todos sin saltarse ninguno, false en caso contrario.
     */
    public boolean difunde(Usuario... destinos) {
        boolean exitoTotal = true;

        for (Usuario siguienteDestino : destinos) {
            Enlace enlaceAUsar = this.usuarioActual.getEnlace(siguienteDestino);

            if (enlaceAUsar != null && this.difunde(enlaceAUsar)) {
            } else {
                exitoTotal = false;
            }
        }

        return exitoTotal;
    }

    /*
     * Genera una representación textual del mensaje.
     * @return cadena de caracteres con un formato útil.
     */
    @Override
    public String toString() {
        return "Mensaje(" + this.texto + ":" + this.alcance + ") en @" + this.usuarioActual.getNombre();
    }
}