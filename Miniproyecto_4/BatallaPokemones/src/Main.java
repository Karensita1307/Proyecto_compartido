import controlador.ControladorPokemon;
import vista.VistaPokemonGUI;

public class Main {
    public static void main(String[] args) {
        VistaPokemonGUI vista = new VistaPokemonGUI();
        ControladorPokemon controlador = new ControladorPokemon(vista, true);
        controlador.inicializar();
    }
}