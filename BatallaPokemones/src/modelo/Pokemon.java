package modelo;
import java.util.*;

public class Pokemon {
    //Atributos
    private String nombre;
    private TipoPokemon tipo;
    private int hp;
    private List<Ataque> ataques;
    private int ataque;
    private int defensa;
    private int ataqueEspecial;
    private int defensaEspecial;
    private int velocidad;

    //Contructor
    public Pokemon(String nombre, TipoPokemon tipo, int hp, int ataque, int defensa,
                   int ataqueEspecial, int defensaEspecial, int velocidad, List<Ataque> ataques) {
        //Verificar datos
        if (hp <= 0 || ataques == null || ataques.isEmpty())
            throw new IllegalArgumentException("Parámetros inválidos");
        this.nombre = nombre;
        this.tipo = tipo;
        this.hp = hp;
        this.ataque = ataque;
        this.defensa = defensa;
        this.ataqueEspecial = ataqueEspecial;
        this.defensaEspecial = defensaEspecial;
        this.velocidad = velocidad;
        this.ataques = new ArrayList<>(ataques);
    }

    //Metodo atacar
    public String atacar(Pokemon objetivo, Ataque ataque) {
        double ventaja = this.tipo.calcularVentajaContra(objetivo.getTipo()); //Ventaja de tipos
        //Ataque y Defensa normal/especial, tomando tipo de daño
        int statAtaque = ataque.getTipoDanio().equalsIgnoreCase("Físico") ? this.ataque : this.ataqueEspecial;
        int statDefensa = ataque.getTipoDanio().equalsIgnoreCase("Físico") ? objetivo.getDefensa() : objetivo.getDefensaEspecial();

        //Se calcula daño causado ( Ventaja si aplica )
        int danio = (int)(((double) ataque.getPotencia() * statAtaque / statDefensa) * ventaja);
        objetivo.recibirDanio(danio);

        //Imprimir datos de ataque
        System.out.println("\n------"+ this.nombre + " usa " + ataque.getNombre() + " contra " + objetivo.getNombre()
                + " causando " + danio + " de daño (Ventaja: " + ventaja + ")" + "------\n");
        return null;
    }

    //Metodo recibir daño
    public void recibirDanio(int cantidad) {
        hp -= cantidad; //Hp se le resta el daño realizado
        if (hp < 0) hp = 0; //Si la vida < 0, se ajusta a 0
    }

    //Metodo sigue vivo?
    public boolean estaVivo() {
        return hp > 0;
    }

    //Getters
    public String getNombre() { return nombre; }

    public TipoPokemon getTipo() {
        return tipo;
    }

    public List<Ataque> getAtaques() {
        return ataques;
    }

    public int getDefensa() { return defensa; }

    public int getDefensaEspecial() { return defensaEspecial; }

    public int getVelocidad() { return velocidad; }

    // Convertir a String
    public String toString() {
        return nombre + " (" + tipo + ", HP: " + hp + ", Velocidad: " +velocidad +")";
    }
}