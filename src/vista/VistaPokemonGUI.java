package vista;

import controlador.ControladorPokemon;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.DefaultListCellRenderer;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import modelo.Ataque;
import modelo.Entrenador;
import modelo.GeneradorAleatorio;
import modelo.Pokemon;

public class VistaPokemonGUI extends JFrame implements VistaPokemon {
    //Items
    Container container;
    JFrame nuevaVentana;
    JLabel tituloBienvenida, infoBatalla, estadoPoke1, estadoPoke2;
    JTextField campoName1, campoName2;
    JButton confirm, botonAtacar;
    JComboBox<Ataque> comboAtaques;
    Entrenador entrenador1, entrenador2;
    GeneradorAleatorio equip1, equip2;
    private ControladorPokemon controlador;

public void VentanaAleatorio() {
    // Ventana de la batalla
    nuevaVentana = new JFrame("¡Batalla Pokémon!"); // Titulo de container
    nuevaVentana.setLayout(new GridBagLayout()); // Organizar items
    nuevaVentana.setSize(750, 600); // Tamaño
    nuevaVentana.setLocationRelativeTo(null); // Centrar la ventana
    nuevaVentana.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

    // Icono de container
    ImageIcon icono1 = new ImageIcon(getClass().getResource("/Images/icon.svg.png"));
    nuevaVentana.setIconImage(icono1.getImage());

    // Imagen insana de fondo
    JPanel fondo1 = new JPanel(){
        Image imagen1 = new ImageIcon(getClass().getResource("/Images/fondo1.jpg")).getImage();

        // Adaptar imagen
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(imagen1, 0, 0, getWidth(), getHeight(), this);
        }
    };

    // Panel principal
    nuevaVentana.setContentPane(fondo1); // Cambiamos panel principal
    fondo1.setLayout(new GridBagLayout()); //Organizar items

    // Elementos de texto
    infoBatalla = new JLabel();
    estadoPoke1 = new JLabel();
    estadoPoke2 = new JLabel();

    // ComboBox
    comboAtaques = new JComboBox<>();
    //Color del comboBox
    comboAtaques.setRenderer(new DefaultListCellRenderer() {
        // Cambia color al ser seleccionado
        public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
            Component c = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
            if (isSelected) {
                c.setBackground(new Color(204, 179, 68));
                c.setForeground(Color.WHITE);
            } else {
                c.setBackground(new Color(245, 222, 102));
                c.setForeground(Color.BLACK);
            }
            return c;
        }
    });

    // Boton
    botonAtacar = new JButton("¡Atacar!");
    botonAtacar.setFocusPainted(false); // Quita borde feo cuando se hace clic
    botonAtacar.setPreferredSize(new Dimension(120, 35)); // ancho, alto
    botonAtacar.setBackground(new Color(245, 222, 102)); // Color de fondo
    botonAtacar.setForeground(Color.white); // Color del texto
    botonAtacar.setFont(new Font("Arial Black", Font.BOLD, 13)); // Tamaño y tipo
    botonAtacar.setBorder(BorderFactory.createLineBorder(new Color(204, 179, 68), 4, true)); // Delineado
    botonAtacar.setContentAreaFilled(true); // Pinta el fondo
    botonAtacar.setOpaque(true); // No deja que se vea nada debajo

    // Control del combate
    final Pokemon[] poke1 = {entrenador1.elegirPokemon()};
    final Pokemon[] poke2 = {entrenador2.elegirPokemon()};
    final boolean[] turnoJugador1 = {poke1[0].getVelocidad() >= poke2[0].getVelocidad()}; //Alternar turnos

    // Metodo para actualizar la UI según el turno
    Runnable actualizarUI = () -> {
        if (poke1[0] == null || poke2[0] == null) {
            String ganador = (poke1[0] != null) ? entrenador1.getNombre() : entrenador2.getNombre();
            JOptionPane.showMessageDialog(fondo1, "¡" + ganador + " gana la batalla!");
            nuevaVentana.dispose();
            return;
        }

        infoBatalla.setText("Turno de: " + (turnoJugador1[0] ? entrenador1.getNombre() : entrenador2.getNombre()));
        estadoPoke1.setText(poke1[0].getNombre() + " - HP: " + poke1[0].getHp() +"      VS ");
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
            JOptionPane.showMessageDialog(fondo1,
                atacante.getNombre() + " usó " + ataqueSeleccionado.getNombre() +
                " contra " + defensor.getNombre());

            // Verificar si el defensor fue derrotado
            if (!defensor.estaVivo()) {
                JOptionPane.showMessageDialog(fondo1, defensor.getNombre() + " ha sido derrotado.");
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
    GridBagConstraints c = new GridBagConstraints();
    c.insets = new Insets(10, 10, 10, 10); // Espaciado entre elementos

    // Decoracion infoBatalla
    c.gridx = 0;
    c.gridy = 0;
    c.gridwidth = 2;
    c.fill = GridBagConstraints.HORIZONTAL;
    c.anchor = GridBagConstraints.CENTER;
    infoBatalla.setFont(new Font("Arial", Font.BOLD, 16));
    fondo1.add(infoBatalla, c);

    // Decoracion estadoPoke1
    c.gridx = 0;
    c.gridy = 1;
    c.gridwidth = 1;
    c.fill = GridBagConstraints.HORIZONTAL;
    c.anchor = GridBagConstraints.CENTER;
    estadoPoke1.setFont(new Font("Arial", Font.PLAIN, 14));
    fondo1.add(estadoPoke1, c);

    // Decoracion estadoPoke2
    c.gridx = 1;
    c.gridy = 1;
    c.fill = GridBagConstraints.HORIZONTAL;
    c.anchor = GridBagConstraints.CENTER;
    estadoPoke2.setFont(new Font("Arial", Font.PLAIN, 14));
    fondo1.add(estadoPoke2, c);

    // Decoracion seleccionar ataque
    c.gridx = 0;
    c.gridy = 2;
    c.gridwidth = 1;
    fondo1.add(new JLabel("Selecciona un ataque:"), c);

    // Decoracion ComboBox
    c.gridx = 1;
    c.gridy = 2;
    comboAtaques.setPreferredSize(new Dimension(200, 25));
    fondo1.add(comboAtaques, c);

    // Decoracion boton
    c.gridx = 0;
    c.gridy = 3;
    c.gridwidth = 2;
    c.anchor = GridBagConstraints.CENTER;
    botonAtacar.setPreferredSize(new Dimension(150, 30));
    fondo1.add(botonAtacar, c);

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
        // Mostrar equipo
        String t1 = "Entrenador 1: " +entrenador1.getNombre() +"\n" +equipoTexto1;
        String t2 = "Entrenador 2: " +entrenador2.getNombre() + "\n" +equipoTexto2;
        JOptionPane.showMessageDialog(container, t1);
        JOptionPane.showMessageDialog(container, t2);
    }

    public VistaPokemonGUI() {
        // Imagen de fondo
        JPanel fondo = new JPanel(){
            Image imagen = new ImageIcon(getClass().getResource("/Images/fondo.png")).getImage();

            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(imagen, 0, 0, getWidth(), getHeight(), this);
            }
        };
        setContentPane(fondo); // Cambiamos panel principal
        fondo.setLayout(new GridBagLayout()); //Organizar items
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Cerrar programa
        setSize(750,600);

        setLocationRelativeTo(null); //Centrar container
        setTitle("¡Batalla Pokémon!"); // Titulo de container

        ImageIcon icono = new ImageIcon(getClass().getResource("/Images/icon.svg.png")); //Cambio de icono para que se vea más pro
        setIconImage(icono.getImage());

        // Inicializar GridBag para organizar
        GridBagConstraints c = new GridBagConstraints();

        //Titulo
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 2; // Ocupa 2 columnas
        c.anchor = GridBagConstraints.CENTER; //Centrado
        c.fill = GridBagConstraints.NONE; // Para que no se estire
        c.insets = new Insets(10, 0, 20, 0); // Margen: top, left, bottom, right
        tituloBienvenida = new JLabel("¡Bienvenidos!");
        tituloBienvenida.setFont(new Font("Arial Black", Font.BOLD, 45)); // Tamañno y tipo
        tituloBienvenida.setForeground(new Color(0, 191, 255)); // Color texto
        fondo.add(tituloBienvenida, c);


        //Entrenador 1
        entrenador1 = new Entrenador(" ", null);
        equip1 = new GeneradorAleatorio();

        c.gridx = 0;
        c.gridy = 1;
        c.gridwidth = 1; // Ocupa 1 columna
        fondo.add(new JLabel("Entrenador 1:"), c);

        c.gridx = 1;
        c.gridy = 1;
        campoName1 = new JTextField(19);
        campoName1.setPreferredSize(new Dimension(200, 30)); // ancho, alto
        campoName1.setForeground(Color.BLACK);
        campoName1.setFont(new Font("Arial", Font.PLAIN, 12)); // Tamaño y tipo
        campoName1.setBorder(BorderFactory.createLineBorder(new Color(30, 144, 255), 2, true)); // Delineado
        fondo.add(campoName1, c);

        //Entrenador 2
        entrenador2 = new Entrenador(" ",null);
        equip2 = new GeneradorAleatorio();

        c.gridx = 0;
        c.gridy = 2;
        c.gridwidth = 1; // Ocupa 1 columna
        fondo.add(new JLabel("Entrenador 2:"), c);

        c.gridx = 1;
        c.gridy = 2;
        campoName2 = new JTextField(19);
        campoName2.setPreferredSize(new Dimension(200, 30)); // ancho, alto
        campoName2.setForeground(Color.BLACK);
        campoName2.setFont(new Font("Arial", Font.PLAIN, 12)); // Tamaño y tipo
        campoName2.setBorder(BorderFactory.createLineBorder(new Color(30, 144, 255), 2, true)); // Delineado
        fondo.add(campoName2, c);

        c.gridx = 0;
        c.gridy = 3;
        c.gridwidth = 2; // Ocupa 2 columnas
        confirm = new JButton("Comenzar");
        confirm.setFocusPainted(false); // Quita borde feo cuando se hace clic
        confirm.setPreferredSize(new Dimension(150, 35)); // ancho, alto
        confirm.setBackground(new Color(0, 191, 255)); // Color de fondo
        confirm.setForeground(Color.white); // Color del texto
        confirm.setFont(new Font("Arial Black", Font.BOLD, 13));
        confirm.setBorder(BorderFactory.createLineBorder(new Color(30, 144, 255), 4, true)); // Delineado
        confirm.setContentAreaFilled(true);
        confirm.setOpaque(true);
        confirm.addActionListener(e -> {
            guardarInfoAlea(); // seguir ejecutando esto
            VentanaAleatorio(); // metodo que abre la nueva ventana

        });
        fondo.add(confirm, c);
        setVisible(true);

        c.gridx = 0;
        c.gridy = 5;
        c.gridwidth = 2; // Ocupa 2 columnas
        confirm = new JButton("Cambiar vista");
        confirm.setFocusPainted(false); // Quita borde feo cuando se hace clic
        confirm.setPreferredSize(new Dimension(155, 40)); // ancho, alto
        confirm.setBackground(new Color(0, 191, 255)); // Color de fondo
        confirm.setForeground(Color.white); // Color del texto
        confirm.setFont(new Font("Arial Black", Font.BOLD, 13));
        confirm.setBorder(BorderFactory.createLineBorder(new Color(30, 144, 255), 4, true)); // Delineado
        confirm.setContentAreaFilled(true);
        confirm.setOpaque(true);
        confirm.addActionListener(e -> {
            controlador.cambiarVista(); // Cambia a la vista de consola
        });
        fondo.add(confirm, c);
        setVisible(true);
        
    }

    @Override
    public void Menu() {
        setVisible(true);
    }

    @Override
    public void setControlador(ControladorPokemon controlador) {
        this.controlador = controlador;
    }

    @Override
    public void iniciar(ControladorPokemon controladorPokemon) {
        this.controlador = controladorPokemon;
    }

}