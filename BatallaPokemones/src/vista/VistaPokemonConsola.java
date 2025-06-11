package vista;

import controlador.ControladorPokemon;
import java.util.List;
import java.util.Scanner;
import modelo.Ataque;
import modelo.Entrenador;
import modelo.excepciones.AtaqueNoDisponibleException;
import modelo.excepciones.PokemonDebilitadoException;
import modelo.GeneradorAleatorio;
import modelo.Pokemon;

public class VistaPokemonConsola implements VistaPokemon {
    private Scanner scanner;
    private ControladorPokemon controlador;

    public VistaPokemonConsola() {
        scanner = new Scanner(System.in);
    }

    @Override
    public void Menu() {
        System.out.println("BIENVENIDOS A LA BATALLA POKEMON");
        System.out.println("--------------------------------------");
        System.out.println("1. Batalla por consola");
        System.out.println("2. Cambiar vista a GUI");
        System.out.println("3. Salir");

        int opcion = scanner.nextInt();
        scanner.nextLine();

        switch (opcion) {
            case 1:
                iniciarBatalla();
                break;
            case 2:
                controlador.cambiarVista();
                break;
            case 3:
                System.exit(0);
                break;
        }
    }

    private void iniciarBatalla() {
        System.out.print("Ingrese el nombre del primer entrenador: ");
        String nombre1 = scanner.nextLine();
        List<Pokemon> equipo1 = GeneradorAleatorio.generarEquipoAleatorio();
        Entrenador entrenador1 = new Entrenador(nombre1, equipo1);

        System.out.print("Ingrese el nombre del segundo entrenador: ");
        String nombre2 = scanner.nextLine();
        List<Pokemon> equipo2 = GeneradorAleatorio.generarEquipoAleatorio();
        Entrenador entrenador2 = new Entrenador(nombre2, equipo2);

        System.out.println("---------- ¡Prepárense para la batalla! ----------\n");
        System.out.println("---- Equipo de " +entrenador1.getNombre() +" ----");
        System.out.println(equipo1);
        System.out.println("\n---- Equipo de " +entrenador2.getNombre() +" ----");
        System.out.println(equipo2);

        Pokemon poke1 = elegirPokemonInicial(scanner, entrenador1);
        Pokemon poke2 = elegirPokemonInicial(scanner, entrenador2);

        Pokemon pokemonActual1 = poke1;
        Pokemon pokemonActual2 = poke2;

        Pokemon atacante, defensor;
        Entrenador entrenadorAtacante, entrenadorDefensor;

        if (poke1.getVelocidad() >= poke2.getVelocidad()) {
            atacante = poke1;
            defensor = poke2;
            entrenadorAtacante = entrenador1;
            entrenadorDefensor = entrenador2;
        } else {
            atacante = poke2;
            defensor = poke1;
            entrenadorAtacante = entrenador2;
            entrenadorDefensor = entrenador1;
        }

        while (atacante != null && defensor != null) {
            System.out.println("\nTurno de " + atacante.getNombre());
            mostrarAtaques(atacante);

            try {
                Ataque ataque = elegirAtaque(scanner, atacante);
                atacante.atacar(defensor, ataque);
            } catch (AtaqueNoDisponibleException e) {
                System.out.println("Error: " + e.getMessage());
                continue;
            }

            if (!defensor.estaVivo()) {
                System.out.println(defensor.getNombre() + " ha sido derrotado.");
                try {
                    defensor = elegirPokemonVivo(scanner, entrenadorDefensor);
                    if (entrenadorDefensor == entrenador1) {
                        pokemonActual1 = defensor;
                    } else {
                        pokemonActual2 = defensor;
                    }
                } catch (PokemonDebilitadoException e) {
                    System.out.println(e.getMessage());
                    break; // Termina la batalla si no quedan Pokémon
                }
            }

            // Cambio de turno
            Pokemon tempPoke = atacante;
            atacante = defensor;
            defensor = tempPoke;

            Entrenador tempEntrenador = entrenadorAtacante;
            entrenadorAtacante = entrenadorDefensor;
            entrenadorDefensor = tempEntrenador;
        }

        System.out.println("\nFin del combate");
        if (pokemonActual1.estaVivo()) {
            System.out.println("¡" + entrenador1.getNombre() + " gana la batalla!");
        } else if (pokemonActual2.estaVivo()) {
            System.out.println("¡" + entrenador2.getNombre() + " gana la batalla!");
        } else {
            System.out.println("¡Empate! Ambos entrenadores quedaron sin Pokémon.");
        }

        Menu();
    }

    private Pokemon elegirPokemonInicial(Scanner sc, Entrenador entrenador) {
        while (true) {
            System.out.println(entrenador.getNombre() + ", elige tu Pokémon inicial:");
            List<Pokemon> equipo = entrenador.getEquipo();
            for (int i = 0; i < equipo.size(); i++) {
                System.out.println((i + 1) + ". " + equipo.get(i));
            }
            int opcion = sc.nextInt();
            sc.nextLine();
            Pokemon seleccionado = equipo.get(opcion - 1);
            if (seleccionado.estaVivo()) {
                return seleccionado;
            } else {
                System.out.println("Ese Pokémon está debilitado. Elige otro.");
            }
        }
    }

    private Pokemon elegirPokemonVivo(Scanner sc, Entrenador entrenador) throws PokemonDebilitadoException {
        List<Pokemon> equipo = entrenador.getEquipo();
        boolean hayVivos = equipo.stream().anyMatch(Pokemon::estaVivo);
        if (!hayVivos) {
            throw new PokemonDebilitadoException("Todos los Pokémon de " + entrenador.getNombre() + " están debilitados.");
        }

        while (true) {
            System.out.println(entrenador.getNombre() + ", elige un nuevo Pokémon:");
            for (int i = 0; i < equipo.size(); i++) {
                System.out.println((i + 1) + ". " + equipo.get(i));
            }
            int opcion = sc.nextInt();
            sc.nextLine();
            Pokemon seleccionado = equipo.get(opcion - 1);
            if (seleccionado.estaVivo()) {
                return seleccionado;
            } else {
                System.out.println("Ese Pokémon está debilitado. Elige otro.");
            }
        }
    }

    private void mostrarAtaques(Pokemon pokemon) {
        List<Ataque> ataques = pokemon.getAtaques();
        System.out.println("Ataques de " + pokemon.getNombre() + ":");
        for (int i = 0; i < ataques.size(); i++) {
            System.out.println((i + 1) + ". " + ataques.get(i).getNombre());
        }
    }

    private Ataque elegirAtaque(Scanner sc, Pokemon pokemon) throws AtaqueNoDisponibleException {
        List<Ataque> ataques = pokemon.getAtaques();
        int opcion = sc.nextInt();
        sc.nextLine();
        if (opcion < 1 || opcion > ataques.size()) {
            throw new AtaqueNoDisponibleException("El número de ataque no está disponible.");
        }
        return ataques.get(opcion - 1);
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