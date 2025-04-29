import java.util.List;

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

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setEquipo(List<Pokemon> equipo) {
        this.equipo = equipo;
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
