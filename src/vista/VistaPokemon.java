package vista;

import controlador.ControladorPokemon;
import modelo.Entrenador;

public interface VistaPokemon {
    public void iniciar(ControladorPokemon controlador);
    public String asignarNombre1();
    public String asignarNombre2();
    public void mostrarEquipos(Entrenador t1, Entrenador t2);
}

