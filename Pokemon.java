import java.util.*;

public class Pokemon {
    private String nombre;
    private TipoPokemon tipo;
    private int hp;
    private List<Ataque> ataques;

    public Pokemon(String nombre, TipoPokemon tipo, int hp, List<Ataque> ataques) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.hp = hp;
        this.ataques = ataques;
    }

    public void atacar(Pokemon objetivo, Ataque ataque) {
        double ventaja = this.tipo.calcularVentajaContra(objetivo.getTipo());
        int danio = (int)(ataque.getPotencia() * ventaja);
        objetivo.recibirDanio(danio);

        System.out.println(this.nombre + " usa " + ataque.getNombre() + " contra " + objetivo.getNombre() +
            " causando " + danio + " de daño. (Ventaja: " + ventaja + ")");
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
}
