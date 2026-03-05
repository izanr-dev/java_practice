/*
* Clase para enlaces entre usuarios.
* @author Izan Robles
* @author Javier Ruiz
* */
public class Enlace {
    private static int costeAcumulado;
    private Usuario usuarioOrigen;
    private Usuario usuarioDestino;
    private int coste;

    /*
    * Constructor para Enlace
    * @param usuarioOrigen dato tipo Usuario que almacena el emisor del mensaje.
    * @param usuarioDestino dato tipo Usuario que almacena el receptor del mensaje.
    * @param coste dato tipo entero que almacena el coste de la transmisión del mensaje.
    * */
    public Enlace (Usuario usuarioOrigen, Usuario usuarioDestino, int coste) {
        this.usuarioOrigen = usuarioOrigen;
        this.usuarioDestino = usuarioDestino;
        setCoste(coste);
        costeAcumulado += this.coste;
    }


    /*
    * Constructor para enlace sin coste (se pone a 1 automáticamente)
    * @param usuarioOrigen dato tipo Usuario que almacena el emisor del mensaje.
    * @param usuarioDestino dato tipo Usuario que almacena el receptor del mensaje.
    * */
    public Enlace (Usuario usuarioOrigen, Usuario usuarioDestino) {
        this(usuarioOrigen, usuarioDestino, 1);
    }

    /*
    * Método para cambiar el usuario de destino del enlace y, en consecuencia,
    * el coste de la transmisión del mensaje. Recalcula automáticamente el coste acumulado.
    * @param nuevoUsuarioDestino tipo Usuario con el nuevo receptor del mensaje
    * @param nuevoCoste tipo entero con el nuevo coste de la transmisión.
    * */
    public void cambiarDestino(Usuario nuevoUsuarioDestino, int nuevoCoste) {

        this.usuarioDestino = nuevoUsuarioDestino;
        int costeAntiguo = this.coste;
        setCoste(nuevoCoste);
        costeAcumulado += (this.coste - costeAntiguo);
    }

    /*
    * Método para obtener el coste especial de un enlace
    * @return  entero con el coste especial del enlace.
    * */
    public int costeEspecial() {
        return 0;
    }

    /*
    * Método para calcular el coste real de un enlace teniend en cuenta
    * el coste especial que pueden tener.
    * @return entero con el coste real del enlace.
    */
    public int costeReal() {
        return this.coste + this.costeEspecial();
    }

    /*
    * Getter estándar estático para obtener el coste acumulado
    * */
    public static int getCosteAcumulado() {
        return costeAcumulado;
    }

    /*
    * Getter estándar para el usuario emisor.
    * */
    public Usuario getUsuarioOrigen() {
        return usuarioOrigen;
    }

    /*
    * Getter estándar para el usuario receptor.
    * */
    public Usuario getUsuarioDestino() {
        return usuarioDestino;
    }

    /*
    * Getter estándar para el coste asignado al enlace.
    * */
    public int getCoste() {
        return coste;
    }

    /*
    * Método set privado para el coste. Permite reciclar la comprobación.
    * @param coste dato tipo entero con el coste a guardar.
    * */
    private void setCoste (int coste) {

        if (coste <= 0) {
            this.coste = 1;
        }
        else {
            this.coste = coste;
        }
    }

    /*
    * Método para generar una representación textual del enlace.
    * @return String que contiene el usuario emisor, el coste y el usuario receptor respectivamente.
    * */
    @Override
    public String toString () {
        return "(@" + this.usuarioOrigen + "--" + this.coste + "-->@" + this.usuarioDestino + ")";
    }
}

