package es.comepiedras.chat;

public class Mensaje {
    private String texto;
    private Usuario remitente;
    private Usuario destinatario;
    private Sala sala;

    public Mensaje(String texto, Usuario remitente, Usuario destinatario, Sala sala) {
        this.texto = texto;
        this.remitente = remitente;
        this.destinatario = destinatario;
        this.sala = sala;
    }

    public Mensaje(String texto, Usuario remitente, Sala sala) {
        this.texto = texto;
        this.remitente = remitente;
        this.destinatario = null;
        this.sala = sala;
    }

    public String getTexto() {
        return texto;
    }

    public Usuario getRemitente() {
        return remitente;
    }

    public Usuario getDestinatario() {
        return destinatario;
    }

    @Override
    public String toString() {
        return "Mensaje{" +
                "texto='" + texto + '\'' +
                ", remitente=" + remitente.getNombre() +
                ", destinatario=" + destinatario.getNombre() +
                ", sala=" + sala.getNombre() +
                '}';
    }
}
