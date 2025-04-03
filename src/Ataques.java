public class Ataques {
    //Atributos
    private String nombre;
    private String tipoDaño;
    private short potencia;
    
    //Constructor
    public Ataques(String nombre, String tipoDaño, short potencia) {
        this.nombre = nombre;
        this.tipoDaño = tipoDaño;
        this.potencia = potencia;
    }

    //Setters
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public void setTipoDaño(String tipoDaño) {
        this.tipoDaño = tipoDaño;
    }
    public void setPotencia(short potencia) {
        this.potencia = potencia;
    }

    //Getters
    public String getNombre() {
        return nombre;
    }
    public String getTipoDaño() {
        return tipoDaño;
    }
    public short getPotencia() {
        return potencia;
    }   
}
