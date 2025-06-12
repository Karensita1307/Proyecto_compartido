package controlador;

import modelo.Entrenador;
import vista.VistaPokemon;
import vista.VistaPokemonConsola;
import vista.VistaPokemonGUI;

public class ControladorPokemon{
    Entrenador entrenador1;
    Entrenador entrenador2;
    VistaPokemon vista;
    private boolean esGui;

    public ControladorPokemon(VistaPokemon vista, boolean esGui) {
        this.vista = vista;
        this.vista.setControlador(this);
        this.esGui = esGui;
    }
    public void setVista(VistaPokemon vista) {
        this.vista = vista;
    }

    public VistaPokemon getVista() {
        return vista;
    }

    public void inicializar(){
        vista.iniciar(this);
    }

    public void cambiarVista() {
        if (esGui) {
            vista = new VistaPokemonConsola();
        } else {
            vista = new VistaPokemonGUI();
        }
        esGui = !esGui;
        vista.setControlador(this);
        vista.Menu();
    }
}