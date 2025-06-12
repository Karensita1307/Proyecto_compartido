package modelo;

public class Ataque {
    //Atributos
    private String nombre;
    private String tipoDanio;
    private int potencia;

    //Contructor
    public Ataque(String nombre, String tipoDanio, int potencia) {
        this.nombre = nombre;
        this.tipoDanio = tipoDanio;
        this.potencia = potencia;
    }

    // Getters
    public String getNombre() {
        return nombre;
    }

    public String getTipoDanio() {
        return tipoDanio;
    }

    public int getPotencia() {
        return potencia;
    }

    // Convertir a String
    public String toString() {
        return nombre + " (" + tipoDanio + ", Potencia: " + potencia + ")";
    }
}