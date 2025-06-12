package modelo;

public class NodoPokemon {
    //Atributos
    public Pokemon pokemon;
    public NodoPokemon siguiente;

    //Constructor
    public NodoPokemon(Pokemon pokemon) {
        this.pokemon = pokemon;
        this.siguiente = null;
    }
}
