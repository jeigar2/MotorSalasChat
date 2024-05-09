package es.comepiedras.chat.exception;

public class UserDetinatarioNotFoundException extends NullPointerException{
    public UserDetinatarioNotFoundException() {
        super();
    }

    public UserDetinatarioNotFoundException(String s) {
        super(s);
    }
}
