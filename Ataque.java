public class Ataque {
    private String nombre;
    private String tipoDanio;
    private int potencia;

    public Ataque(String nombre, String tipoDanio, int potencia) {
        this.nombre = nombre;
        this.tipoDanio = tipoDanio;
        this.potencia = potencia;
    }

    public String getNombre() {
        return nombre;
    }

    public String getTipoDanio() {
        return tipoDanio;
    }

    public int getPotencia() {
        return potencia;
    }
}
