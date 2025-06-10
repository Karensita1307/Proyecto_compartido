package modelo.excepciones;

public class AtaqueNoDisponibleException extends Exception {
    public AtaqueNoDisponibleException(String mensaje) {
        super(mensaje);
    }
}