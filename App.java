import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JOptionPane;
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
    JLabel nameTrainer1, nameTrainer2, pokeEscogido1, pokeEscogido2, mensaje1, mensaje2;
    JTextField campoName1, campoName2, campoEscogido1, campoEscogido2, campoAtaque1, campoAtaque2;
    JButton confirm, confirm2, start;
    JComboBox<Ataque> ataque1, ataque2;
    JComboBox<String> pokeopcion1, pokeopcion2;
    Entrenador entrenador1, entrenador2;
    GeneradorAleatorio equip1, equip2;
    Pokemon ataquesPoke1, ataquesPoke2;

    /* 
    public static List<Pokemon> crearEquipo(Scanner sc) {
        List<Pokemon> equipo = new ArrayList<>();
        System.out.print("¿Quieres un equipo aleatorio? (si/no): ");
        String respuesta = sc.nextLine().toLowerCase();

        if (respuesta.equals("si") || respuesta.equals("s")) {
            return GeneradorAleatorio.generarEquipoAleatorio();
        }

        for (int i = 0; i < 3; i++) {
            System.out.println("\nCreando Pokémon #" + (i + 1));

            System.out.print("Nombre: ");
            String nombre = sc.nextLine();

            System.out.println("Tipos disponibles: " + Arrays.toString(TipoPokemon.values()));
            System.out.print("Tipo: ");
            TipoPokemon tipo = TipoPokemon.valueOf(sc.nextLine().toUpperCase());

            System.out.print("HP: ");
            int hp = sc.nextInt();
            sc.nextLine();
            System.out.print("Ataque: ");
            int ataque = sc.nextInt();
            System.out.print("Defensa: ");
            int defensa = sc.nextInt();
            System.out.print("Ataque Especial: ");
            int atEspecial = sc.nextInt();
            System.out.print("Defensa Especial: ");
            int defEspecial = sc.nextInt();
            System.out.print("Velocidad: ");
            int velocidad = sc.nextInt();
            sc.nextLine();

            List<Ataque> ataques = new ArrayList<>();
            for (int j = 0; j < 2; j++) {
                System.out.println("Ataque #" + (j + 1));
                System.out.print("Nombre del ataque: ");
                String nomAtaque = sc.nextLine();
                System.out.print("Tipo de daño (Físico/Especial): ");
                String tipoDanio = sc.nextLine();
                System.out.print("Potencia: ");
                int potencia = sc.nextInt();
                while (potencia < 20 || potencia > 100) {
                    System.out.println("La potencia no puede ser menor a 20 o mayor a 100 ");
                    System.out.print("Potencia: ");
                    potencia = sc.nextInt();
                }
                sc.nextLine();
                ataques.add(new Ataque(nomAtaque, tipoDanio, potencia));

            }

            equipo.add(new Pokemon(nombre, tipo, hp, ataque, defensa, atEspecial, defEspecial, velocidad, ataques));
        }

        return equipo;
    }
*/

    public void VentanaCrearEquipo() {
        // creamos una nueva ventana para hacer la batalla
        JFrame nuevaaVentana = new JFrame();

        //Diseño del container
        container3 = getContentPane(); //Obtenemos panel principal
        container3.setLayout(new FlowLayout()); //Posicion de items, izq o drc
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Cerrar programa
        setSize(750, 600);
    
        //organizamos la estetica
        setLocationRelativeTo(null); // Centrada
        setTitle("¡Batalla Pokémon!");

        ImageIcon icono = new ImageIcon(getClass().getResource("/Images/icon.svg.png")); //Cambio de icono para que se vea más pro
        setIconImage(icono.getImage());

         //Escoger Pokémon para la batalla - Trainer 1
         pokeEscogido1 = new JLabel("Escoje un Pokémon: ");
         pokeopcion1 = new JComboBox<>(new String[]{
                 "Charmander", "Squirtle", "Bulbasaur", "Pikachu", "Sandshrew", "Pidgey",
                 "Growlithe", "Poliwag", "Oddish", "Magnemite", "Diglett", "Zubat"
         });
         container3.add(pokeEscogido1);
         container3.add(pokeopcion1);
 
         //Trainer 2
         pokeEscogido2 = new JLabel("Escoje un Pokémon: ");
         pokeopcion2 = new JComboBox<>(new String[]{
             "Charmander", "Squirtle", "Bulbasaur", "Pikachu", "Sandshrew", "Pidgey",
             "Growlithe", "Poliwag", "Oddish", "Magnemite", "Diglett", "Zubat"
         });
         container3.add(pokeEscogido2);
         container3.add(pokeopcion2);
 
         start = new JButton("Comenzar");
         start.addActionListener(e -> ObtenerPokes());
         container3.add(start);

         setVisible(true);
 
    };


    public void VentanaAleatorio() {
        // creamos una nueva ventana para hacer la batalla
        JFrame nuevaVentana = new JFrame();

        //Diseño del container
        container2 = getContentPane(); //Obtenemos panel principal
        container2.setLayout(new FlowLayout()); //Posicion de items, izq o drc
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Cerrar programa
        setSize(750, 600);
    
        //organizamos la estetica claro que si
        setLocationRelativeTo(null); // Centrada
        setTitle("¡Batalla Pokémon!");

        ImageIcon icono = new ImageIcon(getClass().getResource("/Images/icon.svg.png")); //Cambio de icono para que se vea más pro
        setIconImage(icono.getImage());
 
        start = new JButton("Comenzar");
        start.addActionListener(e -> ObtenerPokes());
        container2.add(start);

        mensaje1 = new JLabel("Entrenadores, ¡Que empiece la batalla!");
        container2.add(mensaje1);

        
        

        
        Pokemon poke1 = entrenador1.elegirPokemon();
        Pokemon poke2 = entrenador2.elegirPokemon();

        

         setVisible(true);
 
    };

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
        Entrenador entrenador1 = new Entrenador(nombre1, equipo1);
        Entrenador entrenador2 = new Entrenador(nombre2, equipo2);


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

        confirm = new JButton("Equipo aleatorio");
        confirm.addActionListener(e -> {
            guardarInfoAlea(); // si quieres seguir ejecutando esto
            VentanaAleatorio(); // método que abre la nueva ventana
            
        });
        container.add(confirm);

        confirm2 = new JButton("Crear mi Equipo");
        confirm2.addActionListener(e -> {
            VentanaCrearEquipo(); // método que abre la nueva ventana
            
        });
        container.add(confirm2);

        setVisible(true);
    }



    public static void main(String[] args){
        App app = new App();
    }
}

