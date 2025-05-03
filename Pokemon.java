import java.util.*;

public class Pokemon {
    private String nombre;
    private TipoPokemon tipo;
    private int hp;
    private List<Ataque> ataques;
    private int ataque;
    private int defensa;
    private int ataqueEspecial;
    private int defensaEspecial;
    private int velocidad;

    public Pokemon(String nombre, TipoPokemon tipo, int hp, int ataque, int defensa,
                   int ataqueEspecial, int defensaEspecial, int velocidad, List<Ataque> ataques) {
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

    public void atacar(Pokemon objetivo, Ataque ataque) {
        double ventaja = this.tipo.calcularVentajaContra(objetivo.getTipo());
        int statAtaque = ataque.getTipoDanio().equalsIgnoreCase("Físico") ? this.ataque : this.ataqueEspecial;
        int statDefensa = ataque.getTipoDanio().equalsIgnoreCase("Físico") ? objetivo.getDefensa() : objetivo.getDefensaEspecial();

        int danio = (int)(((double) ataque.getPotencia() * statAtaque / statDefensa) * ventaja);
        objetivo.recibirDanio(danio);

        System.out.println(this.nombre + " usa " + ataque.getNombre() + " contra " + objetivo.getNombre()
                + " causando " + danio + " de daño (Ventaja: " + ventaja + ")");
    }

    public void recibirDanio(int cantidad) {
        hp -= cantidad;
        if (hp < 0) hp = 0;
    }

    public boolean estaVivo() {
        return hp > 0;
    }

    public String getNombre() {
        return nombre;
    }

    public TipoPokemon getTipo() {
        return tipo;
    }

    public int getHp() {
        return hp;
    }

    public List<Ataque> getAtaques() {
        return ataques;
    }

    public int getAtaque() {
        return ataque;
    }
    public int getDefensa() { return defensa; }
    public int getAtaqueEspecial() { return ataqueEspecial; }
    public int getDefensaEspecial() { return defensaEspecial; }
    public int getVelocidad() { return velocidad; }
    @Override
    public String toString() {
        return nombre + " (" + tipo + ", HP: " + hp + ")";
    }
}
