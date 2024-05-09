package es.comepiedras.chat;

import es.comepiedras.util.LogUtil;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Sala {
    private static final Logger logger = Logger.getLogger(Sala.class);
    private Logger loggerSala;
    private String nombre;

    private List<Usuario> usuarios;
    private List<Mensaje> mensajes;

    public Sala(String nombre) {
        this(nombre, 0);
    }
    public Sala(String nombre, int ms) {
        this.nombre = nombre;
        this.usuarios = new ArrayList<>();
        this.mensajes = new ArrayList<>();
        try {
            if (ms > 0 ) {
                Thread.sleep(ms); // sleep microsecond
            }
            loggerSala = LogUtil.createLoggerSala(nombre);
        } catch (IOException e) {
            String msgError = "Error creando logger en la creación de la sala. " + e.getMessage();
//            System.err.println(msgError);
//            e.printStackTrace();
            loggerSala = Logger.getLogger(Sala.class);
            loggerSala.error(msgError);
        } catch (InterruptedException e) {
            String msgError = "Error durmiendo. " + e.getMessage();
            loggerSala = Logger.getLogger(Sala.class);
            loggerSala.error(msgError);
            throw new RuntimeException(e);
        }
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public String agregarUsuario(Usuario usuario) {
        String mensajeRetorno = "Entra en la sala " + this.nombre + " el usuario " + usuario.getNombre();
        usuario.setSala(this);
        usuarios.add(usuario);
        loggerSala.info("(" + usuarios.size() + ") Entra usuario: " + usuario.getNombre());
        return mensajeRetorno;
    }

    public String eliminarUsuario(Usuario usuario) {
        String mensajeRetorno = "Sale de la sala " + this.nombre + " el usuario " + usuario.getNombre();
        usuario.unsetSala();
        usuarios.remove(usuario);
        loggerSala.info("(" + usuarios.size() + ") Sale usuario: " + usuario.getNombre());
        return mensajeRetorno;
    }

    public String agregarMensaje(Mensaje mensaje) {
        String mensajeRetorno = "Mensaje de " + mensaje.getRemitente().getNombre() + " para todos: " + mensaje.getTexto();
        mensajes.add(mensaje);
        logger.info(mensajeRetorno);
        loggerSala.info(mensajeRetorno);
        return "La sala " + this.nombre + "Recibe: " + mensajeRetorno;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "Sala{" +
                "nombre='" + nombre + '\'' +
                ", usuarios=" + usuarios.size() +
                ", mensajes=" + mensajes.size() +
                '}';
    }
}
