package controlador;

import modelo.Entrenador;
import modelo.Pokemon;
import modelo.TipoPokemon;
import modelo.Ataque;
import modelo.GeneradorAleatorio;

import vista.VistaPokemon;
import vista.VistaPokemonConsola;
import vista.VistaPokemonGUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControladorPokemon implements ActionListener {
    Entrenador entrenador1;
    Entrenador entrenador2;
    VistaPokemon vista;

    public ControladorPokemon(VistaPokemon vista) {
        this.vista = vista;
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

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
