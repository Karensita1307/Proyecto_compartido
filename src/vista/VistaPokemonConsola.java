package vista;

import controlador.ControladorPokemon;
import java.util.List;
import java.util.Scanner;
import modelo.Ataque;
import modelo.Entrenador;
import modelo.GeneradorAleatorio;
import modelo.Pokemon;

public class VistaPokemonConsola implements VistaPokemon {    
    private Scanner scanner;
    private ControladorPokemon controlador;

    public VistaPokemonConsola(){
        scanner = new Scanner(System.in);
    }

    @Override
    public void Menu() {
        System.out.println("1. Empezar batalla");
        System.out.println("2. Cambiar vista");
        System.out.println("3. Salir");

        int opcion = scanner.nextInt();
        switch(opcion) {
            case 1:
            Scanner sc = new Scanner(System.in);
            System.out.println("_____BATALLA DE POKEMONES_____");
            System.out.print("Ingrese el nombre del primer entrenador: ");
            String nombre1 = sc.nextLine();
            List<Pokemon> equipo1 = GeneradorAleatorio.generarEquipoAleatorio();
            Entrenador entrenador1 = new Entrenador(nombre1, equipo1);
            System.out.print("Ingrese el nombre del segundo entrenador: ");
            String nombre2 = sc.nextLine();
            List<Pokemon> equipo2 = GeneradorAleatorio.generarEquipoAleatorio();
            Entrenador entrenador2 = new Entrenador(nombre2, equipo2);
    
            System.out.println("--------------------------------------------");
            System.out.println("Entrenadores, ¡Preparence!");
            System.out.println("¡¡¡Que Empiece La Batalla!!!");

            break;
            case 2:
            controlador.cambiarVista();
            break;
            case 3:
            System.exit(0);
            break;
           
        }
        Scanner sc = new Scanner(System.in);
        System.out.println("_____BATALLA DE POKEMONES_____");
        System.out.print("Ingrese el nombre del primer entrenador: ");
        String nombre1 = sc.nextLine();
        List<Pokemon> equipo1 = GeneradorAleatorio.generarEquipoAleatorio();
        Entrenador entrenador1 = new Entrenador(nombre1, equipo1);
        System.out.print("Ingrese el nombre del segundo entrenador: ");
        String nombre2 = sc.nextLine();
        List<Pokemon> equipo2 = GeneradorAleatorio.generarEquipoAleatorio();
        Entrenador entrenador2 = new Entrenador(nombre2, equipo2);


    }

    public void batalla(Entrenador entrenador1, Entrenador entrenador2) {
        Pokemon poke1 = entrenador1.elegirPokemon();
        Pokemon poke2 = entrenador2.elegirPokemon();

        Object sc = null;
        while (poke1 != null && poke2 != null) {
            System.out.println("\n" + poke1.getNombre() + " (HP: " + poke1.getHp() + ") VS " + poke2.getNombre() + " (HP: " + poke2.getHp() + ")");

            Pokemon primero = (poke1.getHp() <= poke2.getHp()) ? poke1 : poke2;
            Pokemon segundo = (primero == poke1) ? poke2 : poke1;
            Entrenador entrenadorPrimero = (primero == poke1) ? entrenador1 : entrenador2;
            Entrenador entrenadorSegundo = (segundo == poke1) ? entrenador1 : entrenador2;

            System.out.println("\nTurno de " + primero.getNombre());
            Ataque ataque1 = elegirAtaque(sc, primero);
            primero.atacar(segundo, ataque1);

            if (!segundo.estaVivo()) {
                System.out.println(segundo.getNombre() + " ha sido derrotado.");
                if (segundo == poke1) {
                    poke1 = entrenador1.obtenerSiguientePokemon();
                } else {
                    poke2 = entrenador2.obtenerSiguientePokemon();
                }
                continue;
            }

            System.out.println("\nTurno de " + segundo.getNombre());
            Ataque ataque2 = elegirAtaque(sc, segundo);
            segundo.atacar(primero, ataque2);

            if (!primero.estaVivo()) {
                System.out.println(primero.getNombre() + " ha sido derrotado.");
                if (primero == poke1) {
                    poke1 = entrenador1.obtenerSiguientePokemon();
                } else {
                    poke2 = entrenador2.obtenerSiguientePokemon();
                }
            }
        }

        if (poke1 != null && poke1.estaVivo()) {
            System.out.println("\n¡" + entrenador1.getNombre() + " gana la batalla!");
        } else {
            System.out.println("\n¡" + entrenador2.getNombre() + " gana la batalla!");
        }

        ((Scanner) sc).close();
    }
    




    private Ataque elegirAtaque(Object sc, Pokemon segundo) {
        Scanner scanner = (Scanner) sc;
        System.out.println("Elige un ataque:");
        List<Ataque> ataques = segundo.getAtaques();
        for (int i = 0; i < ataques.size(); i++) {
            System.out.println((i + 1) + ". " + ataques.get(i).toString());
        }
        int opcion = scanner.nextInt();
        return ataques.get(opcion - 1);
    }

    @Override
    public void mostrarMensaje(String mensaje) {
        System.out.println(mensaje);
    }

    @Override
    public void setControlador(ControladorPokemon controlador) {
        this.controlador = controlador;
    }

    @Override
    public void iniciar(ControladorPokemon controladorPokemon) {
        this.controlador = controladorPokemon;
        Menu();
    }



    
}