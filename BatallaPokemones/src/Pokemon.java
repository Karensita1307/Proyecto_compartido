

public class Pokemon {
    //Atributos
    private String nombre;
    private short puntosSalud;

    //Metodos
    public void atacar(){
        System.out.println("Nada por el momento");
    }
    public void recibirDaño(){
        System.out.println("Nada por el momento");
    }
    
    //Constructor 
    public Pokemon(String nombre, short puntosSalud) {
        this.nombre = nombre;
        this.puntosSalud = puntosSalud;

    }

    //Setters
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public void setPuntosSalud(short puntosSalud) {
        this.puntosSalud = puntosSalud;
    }
    

    //Getters
    public String getNombre() {
        return nombre;
    }
    public short getPuntosSalud() {
        return puntosSalud;
    }
    


}
