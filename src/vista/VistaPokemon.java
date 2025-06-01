package vista;

import controlador.ControladorPokemon;

public interface VistaPokemon {
    public void Menu();
    public void mostrarEstado(String estado);
    public void mostrarMensaje(String mensaje);
    public void setControlador(ControladorPokemon controlador);
    public void iniciar(ControladorPokemon controladorPokemon);

}

