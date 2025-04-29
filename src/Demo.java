import javax.swing.*;
import java.awt.*;
import java.util.List;

public class Demo extends JFrame {
    //Items
    Container container;
    JLabel nameTrainer1, nameTrainer2, pokeEscogido1, pokeEscogido2;
    JTextField campoName1, campoName2, campoEscogido1, campoEscogido2, campoAtaque1, campoAtaque2;
    JButton confirm, start;
    JComboBox<Ataque> ataque1, ataque2;
    Entrenador entrenador1, entrenador2;
    GeneradorAleatorio equip1, equip2;
    Pokemon ataquesPoke1, ataquesPoke2;

    public Demo() {
        //Diseño del container
        container = getContentPane(); //Obtenemos panel principal
        container.setLayout(new FlowLayout()); //Posicion de items, izq o drc
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Cerrar programa
        setSize(750,600);

        setLocationRelativeTo(null); //Centrar container
        setTitle("¡Batalla Pokémon!");

        ImageIcon icono = new ImageIcon(getClass().getResource("/Images/icon.svg.png")); //Cambio de icono
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

        confirm = new JButton("Confimar");
        confirm.addActionListener(e -> guardarInfo());
        container.add(confirm);

        //Escoger Pokémon para la batalla - Trainer 1
        pokeEscogido1 = new JLabel("Escoje un Pokémon: ");
        campoEscogido1 = new JTextField(10);
        container.add(pokeEscogido1);
        container.add(campoEscogido1);

        //Trainer 2
        pokeEscogido2 = new JLabel("Escoje un Pokémon: ");
        campoEscogido2 = new JTextField(10);
        container.add(pokeEscogido2);
        container.add(campoEscogido2);

        start = new JButton("Comenzar");
        start.addActionListener(e -> ObtenerPokes());
        container.add(start);

        //Obtener ataques
        /*
        List<Ataque> listaAtaques1 = ataquesPoke1.getAtaques(); // o como los obtengas
        ataque1 = new JComboBox<>(listaAtaques1.toArray(new Ataque[0]));
        Ataque ataqueSeleccionado1 = (Ataque) ataque1.getSelectedItem();
        container.add(ataque1);

        List<Ataque> listaAtaques2 = ataquesPoke2.getAtaques(); // o como los obtengas
        ataque2 = new JComboBox<>(listaAtaques2.toArray(new Ataque[0]));
        Ataque ataqueSeleccionado2 = (Ataque) ataque2.getSelectedItem();
        container.add(ataque2);
        */

        setVisible(true);
    }
    public static void main(String[] args){
        Demo demo = new Demo();
    }
    public void guardarInfo() {
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
}
