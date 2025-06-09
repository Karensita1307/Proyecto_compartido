package modelo;
import java.util.ArrayList;
import java.util.List;

public class Entrenador {
    //Atributos
    private String nombre;
    private List<Pokemon> equipo;

    //Constructor
    public Entrenador(String nombre, List<Pokemon> equipo) {
        this.nombre = nombre;
        this.equipo = (equipo != null) ? equipo : new ArrayList<>();
    }

    //Metodo elegir pokemon
    public Pokemon elegirPokemon() {
        return obtenerSiguientePokemon();
    }

    //Getters
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public List<Pokemon> getEquipo() {
        return equipo;
    }

    //Setters
    public void setEquipo(List<Pokemon> equipo) {
        this.equipo = equipo;
    }

    //Metodo obtener siguiente pokemon
    public Pokemon obtenerSiguientePokemon() {
        for (Pokemon p : equipo) {
            if (p.estaVivo()) return p; //Devuelve el primer pokemon que aun esta vivo
        }
        return null; //Si ninguno esta vivo, devuelve null
    }
}