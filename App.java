import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JOptionPane;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class App extends JFrame {
    //Items
    Container container, container2, container3;
    JLabel nameTrainer1, nameTrainer2, pokeEscogido1, pokeEscogido2, mensaje1, mensaje2, versus, pokeatak1, pokeatak2;
    JTextField campoName1, campoName2, campoEscogido1, campoEscogido2, campoAtaque1, campoAtaque2;
    JButton confirm, confirm2, start;
    JComboBox<Ataque> ataque1, ataque2;
    JComboBox<String> pokeopcion1, pokeopcion2;
    Entrenador entrenador1, entrenador2;
    GeneradorAleatorio equip1, equip2;
    Pokemon ataquesPoke1, ataquesPoke2;

public void VentanaAleatorio() {
    // Crear ventana
    JFrame nuevaVentana = new JFrame("Batalla Pokémon");
    nuevaVentana.setLayout(new FlowLayout());
    nuevaVentana.setSize(750, 600);
    nuevaVentana.setLocationRelativeTo(null);
    nuevaVentana.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    ImageIcon icono = new ImageIcon(getClass().getResource("/Images/icon.svg.png"));
    nuevaVentana.setIconImage(icono.getImage());

    // Panel principal
    container2 = nuevaVentana.getContentPane();
    container2.setLayout(new BoxLayout(container2, BoxLayout.Y_AXIS));

    // Elementos de texto
    JLabel infoBatalla = new JLabel();
    JLabel estadoPoke1 = new JLabel();
    JLabel estadoPoke2 = new JLabel();

    // ComboBox y botón
    JComboBox<Ataque> comboAtaques = new JComboBox<>();
    JButton botonAtacar = new JButton("¡Atacar!");

    // Control del combate
    final boolean[] turnoJugador1 = {true}; // Alterna turnos
    final Pokemon[] poke1 = {entrenador1.elegirPokemon()};
    final Pokemon[] poke2 = {entrenador2.elegirPokemon()};

    // Método para actualizar la UI según el turno
    Runnable actualizarUI = () -> {
        if (poke1[0] == null || poke2[0] == null) {
            String ganador = (poke1[0] != null) ? entrenador1.getNombre() : entrenador2.getNombre();
            JOptionPane.showMessageDialog(nuevaVentana, "¡" + ganador + " gana la batalla!");
            nuevaVentana.dispose();
            return;
        }

        infoBatalla.setText("Turno de: " + (turnoJugador1[0] ? entrenador1.getNombre() : entrenador2.getNombre()));
        estadoPoke1.setText(poke1[0].getNombre() + " - HP: " + poke1[0].getHp());
        estadoPoke2.setText(poke2[0].getNombre() + " - HP: " + poke2[0].getHp());

        // Cargar ataques del Pokémon que va a atacar
        Pokemon atacante = turnoJugador1[0] ? poke1[0] : poke2[0];
        comboAtaques.removeAllItems();
        for (Ataque atk : atacante.getAtaques()) {
            comboAtaques.addItem(atk);
        }
    };

    // Acción del botón de ataque
    botonAtacar.addActionListener(e -> {
        Pokemon atacante = turnoJugador1[0] ? poke1[0] : poke2[0];
        Pokemon defensor = turnoJugador1[0] ? poke2[0] : poke1[0];

        Ataque ataqueSeleccionado = (Ataque) comboAtaques.getSelectedItem();
        if (ataqueSeleccionado != null) {
            atacante.atacar(defensor, ataqueSeleccionado);
            JOptionPane.showMessageDialog(nuevaVentana,
                atacante.getNombre() + " usó " + ataqueSeleccionado.getNombre() +
                " contra " + defensor.getNombre());

            // Verificar si el defensor fue derrotado
            if (!defensor.estaVivo()) {
                JOptionPane.showMessageDialog(nuevaVentana, defensor.getNombre() + " ha sido derrotado.");
                if (turnoJugador1[0]) {
                    poke2[0] = entrenador2.obtenerSiguientePokemon();
                } else {
                    poke1[0] = entrenador1.obtenerSiguientePokemon();
                }
            }

            // Cambiar turno
            turnoJugador1[0] = !turnoJugador1[0];
            actualizarUI.run();
        }
    });

    // Agregar elementos al panel
    container2.add(infoBatalla);
    container2.add(estadoPoke1);
    container2.add(estadoPoke2);
    container2.add(new JLabel("Selecciona un ataque:"));
    container2.add(comboAtaques);
    container2.add(botonAtacar);

    // Mostrar
    nuevaVentana.setVisible(true);
    actualizarUI.run(); // Inicializar
}

    public void guardarInfoAlea() {
        //Obtenemos nombres
        String nombre1 = campoName1.getText();
        String nombre2 = campoName2.getText();
        entrenador1.setNombre(nombre1);
        entrenador2.setNombre(nombre2);
        //Generamos equipo random
        List<Pokemon> equipo1 = equip1.generarEquipoAleatorio();
        List <Pokemon> equipo2 = equip2.generarEquipoAleatorio();
        entrenador1.setEquipo(equipo1);
        entrenador2.setEquipo(equipo2);
        //Organizar cadena de texto
        StringBuilder equipoTexto1 = new StringBuilder(); //Se usa StringBuilder para ahorrar recursos
        for (Pokemon p : entrenador1.getEquipo()) {
            equipoTexto1.append(p.toString()).append("\n");
        }

        StringBuilder equipoTexto2 = new StringBuilder();
        for (Pokemon p : entrenador2.getEquipo()) {
            equipoTexto2.append(p.toString()).append("\n");
        }
        String t1 = "Entrenador 1: " +entrenador1.getNombre() +"\n" +equipoTexto1;
        String t2 = "Entrenador 2: " +entrenador2.getNombre() + "\n" +equipoTexto2;
        JOptionPane.showMessageDialog(container, t1);
        JOptionPane.showMessageDialog(container, t2);




    }

    public void ObtenerPokes(){
        JOptionPane.showMessageDialog(container, "Funciona");
    }

    public App() {
        //Diseño del container
        container = getContentPane(); //Obtenemos panel principal
        container.setLayout(new FlowLayout()); //Posicion de items, izq o drc
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Cerrar programa
        setSize(750,600);

        setLocationRelativeTo(null); //Centrar container
        setTitle("¡Batalla Pokémon!");

        ImageIcon icono = new ImageIcon(getClass().getResource("/Images/icon.svg.png")); //Cambio de icono para wue se vea más pro
        setIconImage(icono.getImage());

        //Toma de datos
        entrenador1 = new Entrenador(" ",null);
        equip1 = new GeneradorAleatorio();
        nameTrainer1 = new JLabel("Entrenador 1: ");
        campoName1 = new JTextField(10);
        container.add(nameTrainer1);
        container.add(campoName1);

        entrenador2 = new Entrenador(" ",null);
        equip2 = new GeneradorAleatorio();
        nameTrainer2 = new JLabel("Entrenador 2: ");
        campoName2 = new JTextField(10);
        container.add(nameTrainer2);
        container.add(campoName2);

        confirm = new JButton("Empezar Batalla");
        confirm.addActionListener(e -> {
            guardarInfoAlea(); // si quieres seguir ejecutando esto
            VentanaAleatorio(); // método que abre la nueva ventana
            
        });
        container.add(confirm);


        setVisible(true);
    }



    public static void main(String[] args){
        App app = new App();
    }
}

