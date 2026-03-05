public class PruebaAvanzada {
    public static void main(String[] args) {
        Usuario ana = new Usuario("ana", 1);
        Usuario luis = new Usuario("luis", 5);
        Usuario carmen = new Usuario("carmen");
        Usuario miguel = new Usuario("miguel", 0);
        Usuario bruno = new Usuario("bruno", 2);
        Usuario aleister = new Usuario("aleister", 9);

        Mensaje mssg1 = new Mensaje("Propaganda1", 13, luis);
        Mensaje mssg2 = new Mensaje("Propaganda2", 5, carmen);

        System.out.println("\n---------------- PRUEBAS DE ENLACES ----------------");

        System.out.println("\n> Creando enlace normal Luis->Miguel (10). (El resultado debe ser true).");
        boolean status = luis.addEnlace(miguel, 10);
        System.out.println("> Resultado: " + status);

        System.out.println("> Creando enlace normal Miguel->Bruno (0). (Debe ajustarse a 1 internamente).");
        miguel.addEnlace(new Enlace(miguel, bruno, 0));
        System.out.println("> Coste registrado: " + miguel.getEnlace(0).getCoste());

        System.out.println("> Creando enlace Ana->Ana (5). (Autorreferencia, el resultado debe ser false).");
        status = ana.addEnlace(ana, 5);
        System.out.println("> Resultado: " + status);


        System.out.println("> Creando enlace normal Luis->Miguel (8). (Duplicado, el resultado debe ser false).");
        status = luis.addEnlace(miguel, 8);
        System.out.println("> Resultado: " + status);

        System.out.println("\n---------------- PRUEBAS DE DIFUSIÓN ----------------");

        System.out.println("\n> Estado inicial del mensaje 1:");
        System.out.println("> " + mssg1);
        System.out.println("> Difundiendo mensaje de Luis a Miguel. (El resultado debe ser true)");
        status = mssg1.difunde(luis.getEnlace(miguel));
        System.out.println("> Resultado: " + status);
        System.out.println("> Estado tras salto (Alcance esperado: 13 - 10 + 0 = 3):");
        System.out.println("> " + mssg1);

        System.out.println("\n> Cambiando coste del enlace de Miguel a 20");
        miguel.getEnlace(0).cambiarDestino(bruno, 20);
        System.out.println("> Intentando saltar de Miguel a Bruno. (20>3) (El resultado debe ser false)");
        status = mssg1.difunde(miguel.getEnlace(bruno));
        System.out.println("> Resultado: " + status);
        System.out.println("> El mensaje no debería haberse movido:");
        System.out.println("> " + mssg1);

        System.out.println("\n---------------- PRUEBAS DE DIFUSIÓN MÚLTIPLE ----------------");

        System.out.println("\n> Creando enlaces (carmen->ana) y (ana->aleister)");
        carmen.addEnlace(ana, 2);
        ana.addEnlace(aleister, 2);
        System.out.println("> Estado inicial del mensaje 2:");
        System.out.println("> " + mssg2);
        System.out.println("> Carmen intenta enviar a: Ana, Luis y Aleister.");
        System.out.println("> Resultado total debe ser false pero el mensaje debe haberse movido parcialmente");
        status = mssg2.difunde(ana, luis, aleister);
        System.out.println("> Resultado: " + status);
        System.out.println("> Estado final del mensaje (Debe estar en Aleister con alcance = 11):");
        System.out.println("> " + mssg2);

        System.out.println("\n---------------- COSTE ACUMULADO ----------------");
        System.out.println("> Coste calculado manualmente en el proceso = 10 + 20 + 2 + 2 = 34");
        System.out.println("> Coste acumulado obtenido: " + Enlace.getCosteAcumulado());
    }

}
