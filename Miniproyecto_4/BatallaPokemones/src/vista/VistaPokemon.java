package vista;

import controlador.ControladorPokemon;

public interface VistaPokemon {
    public void Menu();
    public void setControlador(ControladorPokemon controlador);
    public void iniciar(ControladorPokemon controladorPokemon);

}

