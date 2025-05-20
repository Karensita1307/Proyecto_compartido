package modelo;
import java.util.*;

public class Entrenador {
    private String nombre;
    private List<Pokemon> equipo;

    public Entrenador(String nombre, List<Pokemon> equipo) {
        this.nombre = nombre;
        this.equipo = equipo;
    }

    public Pokemon elegirPokemon() {
        return obtenerSiguientePokemon();
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setEquipo(List<Pokemon> equipo) {
        this.equipo = equipo;
    }

    public String getNombre() {
        return nombre;
    }

    public List<Pokemon> getEquipo() {
        return equipo;
    }

    public Pokemon obtenerSiguientePokemon() {
        for (Pokemon p : equipo) {
            if (p.estaVivo()) return p;
        }
        return null;
    }
}