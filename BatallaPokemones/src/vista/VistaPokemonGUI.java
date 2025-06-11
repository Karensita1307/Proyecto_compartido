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
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import modelo.Ataque;
import modelo.Entrenador;
import modelo.GeneradorAleatorio;
import modelo.Pokemon;
import modelo.ListaTurnos;
import modelo.excepciones.AtaqueNoDisponibleException;
import modelo.excepciones.PokemonDebilitadoException;

import java.util.Stack;

public class VistaPokemonGUI extends JFrame implements VistaPokemon {
    //Items
    Container container;
    JFrame nuevaVentana;
    JLabel tituloBienvenida;
    JTextField campoName1, campoName2;
    JTextArea areaHistorial;
    JScrollPane scrollHistorial;
    JButton confirm, botonAtacar;
    JComboBox<Ataque> comboAtaques;
    JComboBox<Pokemon> comboPokemon1, comboPokemon2;
    Entrenador entrenador1, entrenador2;
    GeneradorAleatorio equip1, equip2;
    private ControladorPokemon controlador;
    private Stack<String> historialMovimientos = new Stack<>();
    ListaTurnos listaTurnos = new ListaTurnos();

    public void VentanaAleatorio() {
        //Ventana de la batalla
        nuevaVentana = new JFrame("¡Batalla Pokémon!"); // Titulo de container
        nuevaVentana.setLayout(new GridBagLayout()); // Organizar items
        nuevaVentana.setSize(750, 600); // Tamaño
        nuevaVentana.setLocationRelativeTo(null); // Centrar la ventana
        nuevaVentana.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        //Icono de container
        ImageIcon icono1 = new ImageIcon(getClass().getResource("/Images/icon.svg.png"));
        nuevaVentana.setIconImage(icono1.getImage());

        //Imagen insana de fondo
        JPanel fondo1 = new JPanel(){
            Image imagen1 = new ImageIcon(getClass().getResource("/Images/fondo1.jpg")).getImage();

            //Adaptar imagen
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(imagen1, 0, 0, getWidth(), getHeight(), this);
            }
        };

        //Panel principal
        nuevaVentana.setContentPane(fondo1); //Cambiamos panel principal
        fondo1.setLayout(new GridBagLayout()); //Organizar items

        //Pila
        areaHistorial = new JTextArea(10, 25);
        areaHistorial.setEditable(false);
        areaHistorial.setLineWrap(true);
        areaHistorial.setWrapStyleWord(true);
        scrollHistorial = new JScrollPane(areaHistorial);

        //ComboBox
        comboAtaques = new JComboBox<>();
        comboPokemon1 = new JComboBox<>();
        comboPokemon2 = new JComboBox<>();

        //Color del comboBox
        comboAtaques.setRenderer(new DefaultListCellRenderer() {
            //Cambia color al ser seleccionado
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

        //Boton
        botonAtacar = new JButton("¡Atacar!");
        botonAtacar.setFocusPainted(false); //Quita borde feo cuando se hace clic
        botonAtacar.setPreferredSize(new Dimension(120, 35)); //Ancho, alto
        botonAtacar.setBackground(new Color(245, 222, 102)); //Color de fondo
        botonAtacar.setForeground(Color.white); //Color del texto
        botonAtacar.setFont(new Font("Arial Black", Font.BOLD, 13)); //Tamaño y tipo
        botonAtacar.setBorder(BorderFactory.createLineBorder(new Color(204, 179, 68), 4, true)); //Delineado
        botonAtacar.setContentAreaFilled(true); //Pinta el fondo
        botonAtacar.setOpaque(true); //No deja que se vea nada debajo

        //Control del combate
        final Pokemon[] poke1 = {entrenador1.elegirPokemon()};
        final Pokemon[] poke2 = {entrenador2.elegirPokemon()};
        final boolean[] esPrimerTurno = { true };
        final boolean[] turnoJugador1 = { true }; //Valor inicial irrelevante, se sobrescribira

        //Metodo para actualizar la UI según el turno
        Runnable actualizarUI = () -> {
            if (poke1[0] == null || poke2[0] == null) { //Detecta si algun entrenador ya no tiene pokemones disponibles para la batalla
                String ganador = (poke1[0] != null) ? entrenador1.getNombre() : entrenador2.getNombre(); //Se obtiene nombre del ganador
                JOptionPane.showMessageDialog(fondo1, "¡" + ganador + " gana la batalla!"); //Mensaje de victoria
                nuevaVentana.dispose();
                return;
            }

            //Se remueven items para agregarlos actualizados cada vez
            comboPokemon1.removeAllItems();
            comboPokemon2.removeAllItems();

            //Se añade equipo de cada entrenador
            for (Pokemon p : entrenador1.getEquipo()) {
                comboPokemon1.addItem(p);
            }

            for (Pokemon p : entrenador2.getEquipo()) {
                comboPokemon2.addItem(p);
            }

            Pokemon atacante = turnoJugador1[0] ? poke1[0] : poke2[0]; //El atacante segun el turno actual
            comboAtaques.removeAllItems(); //Limpiar lista anterior
            if (atacante != null) {
                for (Ataque atk : atacante.getAtaques()) {
                    comboAtaques.addItem(atk); //Cargar ataques del Pokemon en turno
                }
            }
        };

        //Accion del botón de ataque
        botonAtacar.addActionListener(e -> {
            //Guardamos los Pokemon seleccionados como los iniciales
            poke1[0] = (Pokemon) comboPokemon1.getSelectedItem();  //Jugador 1
            poke2[0] = (Pokemon) comboPokemon2.getSelectedItem();  //Jugador 2

            if (esPrimerTurno[0]) {
                turnoJugador1[0] = poke1[0].getVelocidad() >= poke2[0].getVelocidad();
                esPrimerTurno[0] = false; //Ya no es el primer turno
            }

            //Usamos los Pokemon activos del arreglo, no los combos
            Pokemon atacante = turnoJugador1[0] ? poke1[0] : poke2[0];
            Pokemon defensor  = turnoJugador1[0] ? poke2[0] : poke1[0];
            //Obtenemos Ataque seleccionado
            Ataque ataqueSeleccionado = (Ataque) comboAtaques.getSelectedItem();

            //Excepcion si Hp <= 0
            try{
                if (!atacante.estaVivo()) {
                    throw new PokemonDebilitadoException(atacante.getNombre() + " está debilitado, elige otro.");
                }
            } catch (PokemonDebilitadoException ex) {
                JOptionPane.showMessageDialog(fondo1, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
                atacante.atacar(defensor, ataqueSeleccionado); //Realizar ataque
                //Mensaje de ataque seleccionado
                JOptionPane.showMessageDialog(fondo1,
                        atacante.getNombre() + " usó " + ataqueSeleccionado.getNombre() +
                                " contra " + defensor.getNombre());
                //Movimiento ( Para pila )
                String movimiento = atacante.getNombre() + " usó " + ataqueSeleccionado.getNombre();
                historialMovimientos.push(movimiento); //Se le agrega movimiento al historial
                actualizarHistorial(); //Metodo que actualiza el panel que muestra el historial


                //Verificar si el defensor fue derrotado
                if (!defensor.estaVivo()) {
                    JOptionPane.showMessageDialog(fondo1, defensor.getNombre() + " ha sido derrotado.");
                    //Muerte ( Para pila )
                    String muerte = defensor.getNombre() + " ha sido derrotado";
                    historialMovimientos.push(muerte);
                    actualizarHistorial(); //Metodo que actualiza el panel que muestra el historial
                    // Cambiamos al siguiente Pokémon del entrenador correspondiente
                    if (defensor == poke1[0]) {
                        poke1[0] = entrenador1.obtenerSiguientePokemon();
                    } else {
                        poke2[0] = entrenador2.obtenerSiguientePokemon();
                    }
                }

                //Cambiar turno
                turnoJugador1[0] = !turnoJugador1[0];
                actualizarUI.run();
        });

        //Agregar elementos al panel
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(10, 10, 10, 10); // Espaciado entre elementos

        //Decoracion seleccionar ataque
        c.gridx = 0;
        c.gridy = 2;
        c.gridwidth = 1;
        fondo1.add(new JLabel("Selecciona un ataque:"), c);

        //Scroll historial
        c.gridx = 2;
        c.gridy = 0;
        c.gridheight = 3;
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 1.0;
        c.weighty = 1.0;
        fondo1.add(scrollHistorial, c);

        //Reiniciamos cambios para evitar que otros items se expandan
        c.gridheight = 1;
        c.fill = GridBagConstraints.NONE;
        c.weightx = 0;
        c.weighty = 0;

        //Decoracion ComboBox
        c.gridx = 1;
        c.gridy = 2;
        comboAtaques.setPreferredSize(new Dimension(200, 25));
        fondo1.add(comboAtaques, c);

        //Seleccion de Pokémon 1
        c.gridx = 0;
        c.gridy = 4;
        fondo1.add(new JLabel("Escoge tu Pokémon:"), c);
        c.gridx = 0;
        c.gridy = 5;
        fondo1.add(comboPokemon1, c);

        //Seleccion de Pokémon 2
        c.gridx = 1;
        c.gridy = 4;
        fondo1.add(new JLabel("Escoge tu Pokémon:"), c);
        c.gridx = 1;
        c.gridy = 5;
        fondo1.add(comboPokemon2, c);

        //Decoracion boton
        c.gridx = 0;
        c.gridy = 3;
        c.gridwidth = 2;
        c.anchor = GridBagConstraints.CENTER;
        botonAtacar.setPreferredSize(new Dimension(150, 30));
        fondo1.add(botonAtacar, c);

        //Mostrar
        nuevaVentana.setVisible(true);
        actualizarUI.run(); //Inicializar
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
        //Poke con mas velocidad
        for (Pokemon p : entrenador1.getEquipo()) {
            listaTurnos.agregarPorVelocidad(p);
        }
        for (Pokemon p : entrenador2.getEquipo()) {
            listaTurnos.agregarPorVelocidad(p);
        }
        //Mostrar equipo
        String t1 = "Entrenador 1: " +entrenador1.getNombre() +"\n" +equipoTexto1;
        String t2 = "Entrenador 2: " +entrenador2.getNombre() + "\n" +equipoTexto2;
        JOptionPane.showMessageDialog(container, t1);
        JOptionPane.showMessageDialog(container, t2);
    }
    private void actualizarHistorial() {
        StringBuilder texto = new StringBuilder();
        for (String mov : historialMovimientos) {
            texto.append(mov).append("\n"); //Agrega movimientos
        }
        areaHistorial.setText(texto.toString());
    }

    public VistaPokemonGUI() {
        //Imagen de fondo
        JPanel fondo = new JPanel(){
            Image imagen = new ImageIcon(getClass().getResource("/Images/fondo.png")).getImage();

            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(imagen, 0, 0, getWidth(), getHeight(), this);
            }
        };
        setContentPane(fondo); //Cambiamos panel principal
        fondo.setLayout(new GridBagLayout()); //Organizar items
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Cerrar programa
        setSize(750,600);

        setLocationRelativeTo(null); //Centrar container
        setTitle("¡Batalla Pokémon!"); // Titulo de container

        ImageIcon icono = new ImageIcon(getClass().getResource("/Images/icon.svg.png")); //Cambio de icono para que se vea más pro
        setIconImage(icono.getImage());

        //Inicializar GridBag para organizar
        GridBagConstraints c = new GridBagConstraints();

        //Titulo
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 2; //Ocupa 2 columnas
        c.anchor = GridBagConstraints.CENTER; //Centrado
        c.fill = GridBagConstraints.NONE; //Para que no se estire
        c.insets = new Insets(10, 0, 20, 0); //Margen: top, left, bottom, right
        tituloBienvenida = new JLabel("¡Bienvenidos!");
        tituloBienvenida.setFont(new Font("Arial Black", Font.BOLD, 45)); //Tamañno y tipo
        tituloBienvenida.setForeground(new Color(0, 191, 255)); //Color texto
        fondo.add(tituloBienvenida, c);


        //Entrenador 1
        entrenador1 = new Entrenador(" ", null);
        equip1 = new GeneradorAleatorio();

        c.gridx = 0;
        c.gridy = 1;
        c.gridwidth = 1; //Ocupa 1 columna
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
        c.gridwidth = 1; //Ocupa 1 columna
        fondo.add(new JLabel("Entrenador 2:"), c);

        c.gridx = 1;
        c.gridy = 2;
        campoName2 = new JTextField(19);
        campoName2.setPreferredSize(new Dimension(200, 30)); //Ancho, alto
        campoName2.setForeground(Color.BLACK);
        campoName2.setFont(new Font("Arial", Font.PLAIN, 12)); //Tamaño y tipo
        campoName2.setBorder(BorderFactory.createLineBorder(new Color(30, 144, 255), 2, true)); //Delineado
        fondo.add(campoName2, c);

        c.gridx = 0;
        c.gridy = 3;
        c.gridwidth = 2; //Ocupa 2 columnas
        confirm = new JButton("Comenzar");
        confirm.setFocusPainted(false); //Quita borde feo cuando se hace clic
        confirm.setPreferredSize(new Dimension(150, 35)); //Ancho, alto
        confirm.setBackground(new Color(0, 191, 255)); //Color de fondo
        confirm.setForeground(Color.white); //Color del texto
        confirm.setFont(new Font("Arial Black", Font.BOLD, 13));
        confirm.setBorder(BorderFactory.createLineBorder(new Color(30, 144, 255), 4, true)); //Delineado
        confirm.setContentAreaFilled(true);
        confirm.setOpaque(true);
        confirm.addActionListener(e -> {
            guardarInfoAlea(); //Seguir ejecutando esto
            VentanaAleatorio(); //Metodo que abre la nueva ventana

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