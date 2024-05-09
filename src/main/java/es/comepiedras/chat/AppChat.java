package es.comepiedras.chat;

import es.comepiedras.chat.action.RandomChat;
import org.apache.log4j.Logger;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AppChat {

    private static final Logger logger = Logger.getLogger(AppChat.class);
    private static final Random random = new Random();

    public static void main(String[] args) {
        logger.info("Iniciando aplicación");
        int hilos = random.nextInt(3) + 2; // entre 2 y 5 hilos
        logger.info(String.format("Creando %d hilos", hilos));
        ExecutorService executor = Executors.newFixedThreadPool(hilos);
        logger.info("antes del for RandomChar");
        for (int i = 0; i < hilos; i++) {
            RandomChat randomChat = new RandomChat();
            randomChat.setSuffix("["+i+"]");
            executor.submit(randomChat);
        }
        logger.info("despues del for RandomChar");
        executor.shutdown();

//        String nombreSala1 = "Amarillo";
//        String nombreUsuario1 = "Pedro";
//        String nombreUsuario2 = "Santiago";
//        Sala sala1 = new Sala(nombreSala1);
//        logger.info("creada sala: " + sala1.getNombre());
//        Usuario pedro = new Usuario(nombreUsuario1);
//        logger.info("creado usuario: " + pedro.getNombre());
//        Usuario santiago = new Usuario(nombreUsuario2);
//        logger.info("creado usuario: " + santiago.getNombre());
//
//        logger.info(sala1.agregarUsuario(pedro));
////        logger.info("agregado usuario: " + pedro.getNombre() + " a sala " + sala1.getNombre() );
//        logger.info(sala1.agregarUsuario(santiago));
////        logger.info("agregado usuario: " + santiago.getNombre() + " a sala " + sala1.getNombre() );
//
//        List<String> mensajes = Arrays.asList("Hola a todos", "Hola Santiago", "¡Cuánto tiempo! Pedro", "¿Cómo estás?", "Estoy bien y tú", "Yo también estoy bien", "Llevo prisa, me tengo que ir, ¡Adios!", "¡Adiós!","¡Que Dios te bendiga!");
//
//        logger.info(pedro.addMensaje(mensajes.getFirst()));
//        logger.info(pedro.addMensaje(mensajes.get(1), santiago));
//        logger.info(santiago.addMensaje(mensajes.get(2), pedro));
//        logger.info(pedro.addMensaje(mensajes.get(3), santiago));
//        logger.info(santiago.addMensaje(mensajes.get(4), pedro));
//        logger.info(pedro.addMensaje(mensajes.get(5), santiago));
//        logger.info(santiago.addMensaje(mensajes.get(6), pedro));
//        logger.info(pedro.addMensaje(mensajes.get(7), santiago));
//        logger.info(santiago.addMensaje(mensajes.get(8), pedro));
//        logger.info(pedro.addMensaje(mensajes.get(8), santiago));
//
//        logger.info(sala1.eliminarUsuario(pedro));
//        logger.info(sala1.eliminarUsuario(santiago));
//
//        try {
//            pedro.addMensaje("Adios a todos");
//        } catch (SalaNotFoundException e) {
//            logger.error(e.getMessage());
//        }
//
////        if(santiago.getMensajesRecibidos().size() >0) {
////            System.out.println("Le ha llegado el mensaje a Santiago: " + santiago.getMensajesRecibidos().getLast());
////        }
////
////        if(pedro.getMensajesEmitido().getFirst() != null){
////            System.out.println("Pedro ha emitido el mensaje: " + pedro.getMensajesEmitido().getFirst());
////        }

        logger.info("Finalizada aplicación");
    }

}
