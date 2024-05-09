package es.comepiedras.chat;

import es.comepiedras.chat.exception.NotSameSalaException;
import es.comepiedras.chat.exception.SalaNotFoundException;
import es.comepiedras.util.LogUtil;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Usuario {

    private Logger loggerUser;
    private static final Logger logger = Logger.getLogger(Usuario.class);

    private String nombre;
    private Sala sala;

    private List<Mensaje> mensajesEmitidos;
    private List<Mensaje> mensajesEmitidosIndirecto;
    private List<Mensaje> mensajesRecibidos;
    private List<Mensaje> mensajesRecibidosIndirecto;

    public Usuario(String nombre) {
        this.nombre = nombre;
        mensajesEmitidos = new ArrayList<>();
        mensajesEmitidosIndirecto = new ArrayList<>();
        mensajesRecibidos = new ArrayList<>();
        mensajesRecibidosIndirecto = new ArrayList<>();
        try {
            loggerUser = LogUtil.createLoggerUser(nombre);
        } catch (IOException e) {
            System.err.println("Error creando logger en la creación del usuario. " + e.getMessage());
            e.printStackTrace();
            loggerUser = Logger.getLogger(Usuario.class);
        }
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String addMensaje(String mensaje, Usuario destinatario)  throws SalaNotFoundException {
        return addMensaje(mensaje, destinatario, 0);
    }
    public String addMensaje(String mensaje, Usuario destinatario, int ms)  throws SalaNotFoundException {
        String mensajeRetorno = this.nombre + " dice: ";
        if (this.sala == null) {
            loggerUser.error("No tiene sala asociada. Se queda sin decir: - " + mensaje);
            throw new SalaNotFoundException("El usuario " + this.nombre + " no tiene sala asignada");
        }
        if (destinatario == null) {
            loggerUser.error("El destinatario no existe. Se queda sin decir: - " + mensaje + " -");
            throw new NotSameSalaException("El destinatario no existe. Se queda sin decir: - " + mensaje + " -");
        }
        if (destinatario.getSala() != null && !this.sala.getNombre().equals(destinatario.getSala().getNombre())){
            loggerUser.error("El destinatario no está en la misma sala asociada. Se queda sin decir: - " + mensaje + " -");
            throw new NotSameSalaException("El destinatario no está en la misma sala " + this.sala.getNombre() + " != " + destinatario.getSala().getNombre() + ". Se queda sin decir: - " + mensaje + " -");
        }
        if(mensaje != null) {
            loggerUser.info("Mensaje " + (mensajesEmitidos.size() + 1) + " " + mensaje + " para " + destinatario.getNombre());
            logger.info("Mensaje " + (mensajesEmitidos.size() + 1) + " " + mensaje + " para " + destinatario.getNombre());
            Mensaje myMensaje = new Mensaje(mensaje, this, destinatario, this.sala);
            mensajesEmitidos.add(myMensaje);
            destinatario.addMensajeRecibido(myMensaje);
            mensajeRetorno += "- " + mensaje + " - a " + destinatario.getNombre();
        } else {
            mensajeRetorno += "No hay mensaje";
        }
        return mensajeRetorno;
    }

    public String addMensaje(String mensaje) throws SalaNotFoundException {
        return addMensaje(mensaje, 0);
    }
    public String addMensaje(String mensaje, int ms) throws SalaNotFoundException {

        if (ms > 0 ) {
            try {
                Thread.sleep(ms); // sleep microsecond
            } catch (InterruptedException e) {
                loggerUser.error("error al dormir " + e.getMessage());
            }
        }

        String mensajeRetorno = this.nombre + " dice: ";
        if (this.sala == null) {
            loggerUser.error("No tiene sala asociada. Se queda sin decir: - " + mensaje);
            throw new SalaNotFoundException("El usuario " + this.nombre + " no tiene sala asignada. No puede decir: - " + mensaje);
        }
        if(mensaje != null) {
            Mensaje myMensaje = new Mensaje(mensaje, this, this.sala);
            mensajesEmitidosIndirecto.add(myMensaje);
            this.sala.getUsuarios().stream().filter(it->it != this).forEach(it->it.addMensajeRecibidoIndirecto(new Mensaje(mensaje, this, it, this.sala)));
            this.sala.agregarMensaje(myMensaje);
            mensajeRetorno += "- " + mensaje + " - en la sala: " + this.sala.getNombre();
        } else {
            mensajeRetorno += "No hay mensaje";
        }
        return mensajeRetorno;
    }

    public void addMensajeRecibido(Mensaje mensaje) {
        if(mensaje != null){
            mensajesRecibidos.add(mensaje);
        }
        loggerUser.info("Mensaje " + (mensajesEmitidos.size() + 1) + " " + mensaje.getTexto() + " de " + mensaje.getRemitente().getNombre());

    }

    public void addMensajeRecibidoIndirecto(Mensaje mensaje) {
        if(mensaje != null) {
            mensajesRecibidosIndirecto.add(mensaje);
        }
    }


    public List<Mensaje> getMensajesEmitido() {
        return mensajesEmitidos;
    }

//    public void setMensajesEmitido(List<Mensaje> mensajes) {
//        this.mensajesEmitidos = mensajes;
//    }

    public List<Mensaje> getMensajeEmitidosIndirecto() {
        return mensajesEmitidosIndirecto;
    }

    public void setMensajeEmitidosIndirecto(List<Mensaje> mensajeEmitidosIndirecto) {
        this.mensajesEmitidosIndirecto = mensajeEmitidosIndirecto;
    }

    public List<Mensaje> getMensajesRecibidos() {
        return mensajesRecibidos;
    }

//    public void setMensajesRecibidos(List<Mensaje> mensajesRecibidos) {
//        this.mensajesRecibidos = mensajesRecibidos;
//    }

    public List<Mensaje> getMensajesRecibidosIndirecto() {
        return mensajesRecibidosIndirecto;
    }

    public Sala getSala() {
        return sala;
    }

    public void setSala(Sala sala) {
        this.sala = sala;
    }

    public void unsetSala() {
        this.sala = null;
    }

//    public void setMensajeRecibidosIndirecto(List<Mensaje> mensajeRecibidosIndirecto) {
//        this.mensajeRecibidosIndirecto = mensajeRecibidosIndirecto;
//    }

    @Override
    public String toString() {
        return "Usuario{" +
                "nombre='" + this.nombre + '\'' +
                ", sala=" + (sala!=null?sala.getNombre():"") +
                ", mensajesEmitidos=" + mensajesEmitidos.size() +
                ", mensajesEmitidosIndirecto=" + mensajesEmitidosIndirecto.size() +
                ", mensajesRecibidos=" + mensajesRecibidos.size() +
                ", mensajesRecibidosIndirecto=" + mensajesRecibidosIndirecto.size() +
                '}';
    }
}
