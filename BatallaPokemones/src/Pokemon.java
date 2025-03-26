public class Pokemon {
    //Atributos
    private String nombre;
    private String tipo;
    private short puntosSalud;

    //Metodos
    public void atacar(){
        System.out.println("Nada por el momento");
    }
    public void recibirDaño(){
        System.out.println("Nada por el momento");
    }
    
    //Constructor 
    public Pokemon(String nombre, String tipo, short puntosSalud) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.puntosSalud = puntosSalud;
    }

    //Setters
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    public void setPuntosSalud(short puntosSalud) {
        this.puntosSalud = puntosSalud;
    }

    //Getters
    public String getNombre() {
        return nombre;
    }
    public String getTipo() {
        return tipo;
    }
    public short getPuntosSalud() {
        return puntosSalud;
    }
}
