package es.comepiedras.chat.exception;

public class SalaNotFoundException extends NullPointerException {
    public SalaNotFoundException(){
        super();
    }

    public SalaNotFoundException(String msg){
        super(msg);
    }
}
