package es.comepiedras.chat.action;

import es.comepiedras.chat.Sala;
import es.comepiedras.chat.Usuario;
import es.comepiedras.chat.exception.UserDetinatarioNotFoundException;
import org.apache.log4j.Logger;

import java.util.*;
import java.util.stream.IntStream;


public class RandomChat implements Runnable {

    private static final Random random = new Random();
    private static final Logger logger = Logger.getLogger(RandomChat.class);
    private String suffix;

    public String getSuffix() {
        return suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }

    @Override
    public void run() {
        logger.info("Inicio RandomChat " + suffix);
        List<String> nombreSalas = Arrays.asList("Amarilla", "Roja", "Verde", "Azul");
        List<String>  nombresUsuarios = Arrays.asList(
                "Alice", "Bob", "Charlie", "David", "Emily", "Frank", "George", "Hannah",
                "Isaac", "Julia", "Kevin", "Laura", "Michael", "Nina", "Oliver", "Penelope",
                "Quinn", "Rachel", "Samuel", "Tessa", "Ulysses", "Violet", "Winston", "Xanthe",
                "Yvette", "Zachary", "Abigail", "Benjamin", "Caroline", "Derek", "Elena",
                "Felix", "Gabriella", "Harrison", "Ivy", "Jaxon", "Kayla", "Lucas", "Maeve",
                "Nathan", "Ophelia", "Parker", "Ruby", "Sage", "Theodore", "Uma", "Victor",
                "Wendy", "Xavier", "Yara", "Zara", "Addison", "Bridget", "Cassius",
                "Diana", "Elijah", "Florence", "Gavin", "Haven", "Ida", "Jocelyn",
                "Kaitlyn", "Maisie", "Nolan", "Orla", "Paige", "Quinn", "Riley", "Sage",
                "Tatum", "Una", "Victor", "Waverly", "Xander", "Yvette", "Zayden"
        );
        List<String> convesacion = Arrays.asList("¿Quiere estudiar la Santa Biblia?", "¡Sí! claro", "¿Tienes Biblia?", "Sí, una personal", "Empezariamos por Genesis 1, 1", "Por el principio, estupendo", "Empezamos el próximo lunes", "¿A que hora?","A las 18h", "Me viene perfecto", "Estaremos en la autopista hacia el cielo 707", "Allí nos veremos. Gracias", "Un placer, Que ¡Dios te bendiga?", "Igualmente, con Dios");

        Collections.shuffle(nombresUsuarios);
        Stack<String> pilaNombres = new Stack<>();
        nombresUsuarios.stream().forEach(pilaNombres::push);
        int numSala = random.nextInt(2) + 2; // entre 2 y 4 salas
        HashMap<String, Sala> hmSala = HashMap.newHashMap(numSala);
        for (int i = 0; i < numSala; i++) {
            String nombreSala = "Sala " + nombreSalas.get(i) + "_" + suffix;
            Sala sala = new Sala(nombreSala, random.nextInt(1500) + 500);
            hmSala.put(nombreSala, sala);
            logger.info("creada sala: " + nombreSala);
            int numUsuarios = random.nextInt(7) + 4; // entre 4 y 10 usuarios
            for (int j = 0; j < numUsuarios; j++) {
                String nombreUsuario = pilaNombres.pop() + "_" + suffix;
                Usuario usuario = new Usuario(nombreUsuario);
                logger.info("creado usuario: " + nombreUsuario);
                logger.info(sala.agregarUsuario(usuario));
            }
            sala.getUsuarios().forEach(it -> it.addMensaje("hola soy " + it.getNombre(), random.nextInt(500) + 200));
            do {
                IntStream.range(0, convesacion.size()).forEach(index -> {
                    try {
                        if (sala.getUsuarios().getFirst() != sala.getUsuarios().getLast()) {
                            int ms = random.nextInt(400) + 100;
                            if (index % 2 == 0) {
                                sala.getUsuarios().getFirst().addMensaje(convesacion.get(index), sala.getUsuarios().getLast(), ms);
                            } else {
                                sala.getUsuarios().getLast().addMensaje(convesacion.get(index), sala.getUsuarios().getFirst(), ms);
                            }
                        }
                    }catch (UserDetinatarioNotFoundException e){
                        logger.error(e);
                    }
                });
                sala.eliminarUsuario(sala.getUsuarios().getLast());
            } while (!sala.getUsuarios().isEmpty());
        }
        logger.info("Fin RandomChat " + suffix);
    }
}

