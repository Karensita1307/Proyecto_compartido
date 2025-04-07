import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import utils.Ataques_P;
import utils.Tipos_P;

public class Entrenador {
    private String nombre;
    private String equipo;
    private ArrayList<String> pokemonElegido1 = new ArrayList<>();
    private ArrayList<String> pokemonElegido2 = new ArrayList<>();

    public Entrenador(String nombre, String equipo) {
        this.nombre = nombre;
        this.equipo = equipo;
        pokemonElegido1 = new ArrayList<>();
        pokemonElegido2 = new ArrayList<>();
    }

    public String getNombre() {

        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEquipo() {
        return equipo;
    }

    public void setEquipo(String equipo) {
        this.equipo = equipo;
    }

    public void setPokemonElegido1(ArrayList<String> seleccionado){
        pokemonElegido1 = seleccionado;
        System.out.println("¡Bien! Has escogido a " +pokemonElegido1.get(0));
    }

    public void setPokemonElegido2(ArrayList<String> seleccionado){
        pokemonElegido2 = seleccionado;
        System.out.println("¡Bien! Has escogido a " +pokemonElegido2.get(0));
    }

    public ArrayList<String> getPokemonElegido1() {
        return pokemonElegido1;
    }
    public ArrayList<String> getPokemonElegido2() {
        return pokemonElegido2;
    }
}
