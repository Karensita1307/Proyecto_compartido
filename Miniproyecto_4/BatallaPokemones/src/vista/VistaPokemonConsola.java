package vista;

import controlador.ControladorPokemon;

import java.util.*;

import modelo.*;
import modelo.excepciones.AtaqueNoDisponibleException;
import modelo.excepciones.PokemonDebilitadoException;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class VistaPokemonConsola implements VistaPokemon {
    private Scanner scanner;
    private ControladorPokemon controlador;
    private HashMap<String, Pokemon> mapaPokemones; //HashMap

    public VistaPokemonConsola() {
        scanner = new Scanner(System.in);
    }

    @Override
    public void Menu() {
        System.out.println("BIENVENIDOS A LA BATALLA POKEMON");
        System.out.println("--------------------------------------");
        System.out.println("1. Batalla por consola");
        System.out.println("2. Cargar partida desde archivo");
        System.out.println("3. Guardar equipos actuales");
        System.out.println("4. Cambiar vista a GUI");
        System.out.println("5. Salir");

        //Toma de opcion por parte del usuario
        int opcion = scanner.nextInt();
        scanner.nextLine();

        switch (opcion) {
            case 1:
                iniciarBatalla(); //Comenzamos batalla
                break;
            case 2:
                List<Entrenador> entrenadores = cargarEquiposDesdeArchivo(); //Se obtiene equipo del archivo
                if (entrenadores.size() == 2) { //Verifica que sean dos entrenadores
                    iniciarBatallaConEquipos(entrenadores.get(0), entrenadores.get(1)); //Comenzamos batalla con equipo de archivo
                } else {
                    System.out.println("No se pudo cargar la partida. Asegúrate de que el archivo tenga los datos correctos.");
                }
                Menu();
                break;
            case 3:
                //Verifica que haya equipo en archivo
                if (mapaPokemones == null || mapaPokemones.isEmpty()) {
                    System.out.println("No hay equipos para guardar. Primero inicia una batalla.");
                } else {
                    System.out.println("Juega una batalla para guardar.");
                }
                Menu();
                break;
            case 4, 5:
                System.exit(0);
                break;
            default:
                System.out.println("Opción no válida.");
                Menu();
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

        ejecutarBatalla(entrenador1, entrenador2, true);
    }
    private void iniciarBatallaConEquipos(Entrenador entrenador1, Entrenador entrenador2) {
        ejecutarBatalla(entrenador1, entrenador2, false);
    }

    private void ejecutarBatalla(Entrenador entrenador1, Entrenador entrenador2, boolean guardarTrasCadaDerrota) {
        mapaPokemones = new HashMap<>();
        //Se agregan nombres de Poke a HashMap
        for (Pokemon p : entrenador1.getEquipo()) mapaPokemones.put(p.getNombre(), p);
        for (Pokemon p : entrenador2.getEquipo()) mapaPokemones.put(p.getNombre(), p);

        System.out.println("\n---------- ¡Prepárense para la batalla! ----------\n");
        System.out.println("---- Equipo de " + entrenador1.getNombre() + " ----");
        System.out.println(entrenador1.getEquipo());
        System.out.println("\n---- Equipo de " + entrenador2.getNombre() + " ----");
        System.out.println(entrenador2.getEquipo());

        Pokemon poke1 = elegirPokemonInicial(scanner, entrenador1);
        Pokemon poke2 = elegirPokemonInicial(scanner, entrenador2);

        Pokemon pokemonActual1 = poke1;
        Pokemon pokemonActual2 = poke2;

        Pokemon atacante, defensor;
        Entrenador entrenadorAtacante, entrenadorDefensor;

        //Calculo de velocidad ( Quien empieza la batalla )
        if (poke1.getVelocidad() >= poke2.getVelocidad()) {
            atacante = poke1; defensor = poke2;
            entrenadorAtacante = entrenador1; entrenadorDefensor = entrenador2;
        } else {
            atacante = poke2; defensor = poke1;
            entrenadorAtacante = entrenador2; entrenadorDefensor = entrenador1;
        }

        while (true) {
            System.out.println("\nTurno de " + atacante.getNombre());
            mostrarAtaques(atacante);

            try {
                Ataque ataque = elegirAtaque(scanner, atacante);
                atacante.atacar(defensor, ataque);
            } catch (AtaqueNoDisponibleException e) {
                System.out.println(e.getMessage());
                continue;
            }

            if (!defensor.estaVivo()) { //Verifica si sigue vivo
                System.out.println(defensor.getNombre() + " ha sido derrotado.");
                try {
                    defensor = elegirPokemonVivo(scanner, entrenadorDefensor); //Escoge Pokemon
                    if (entrenadorDefensor == entrenador1) {
                        pokemonActual1 = defensor;
                    } else {
                        pokemonActual2 = defensor;
                    }

                    //Pregunta si guardar info en archivo
                    if (guardarTrasCadaDerrota) {
                        System.out.print("¿Deseas guardar la partida tras esta derrota? (s/n): ");
                        String respuesta = scanner.nextLine().trim().toLowerCase();
                        if (respuesta.equals("s")) {
                            guardarEquipos(entrenador1, entrenador2);
                        }
                    } else {
                        System.out.print("¿Deseas guardar la partida? (s/n): ");
                        String respuesta = scanner.nextLine().trim().toLowerCase();
                        if (respuesta.equals("s")) {
                            guardarEquipos(entrenador1, entrenador2);
                        }
                    }
                } catch (PokemonDebilitadoException e) {
                    System.out.println(e.getMessage());
                    break;
                }
            }

            //Cambio de turno
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
        }

        Menu();
    }

    private Pokemon elegirPokemonInicial(Scanner sc, Entrenador entrenador) {
        List<Pokemon> equipo = entrenador.getEquipo();
        Map<String, Pokemon> mapaPorNombre = crearMapaPorNombre(equipo);
        while (true) {
            System.out.println("\n" +entrenador.getNombre() + ", elige tu Pokémon inicial escribiendo su nombre:");
            for (Pokemon p : equipo) {
                System.out.println("- " + p);
            }
            String nombre = sc.nextLine().toLowerCase();
            Pokemon seleccionado = mapaPorNombre.get(nombre);

            try{
                if (seleccionado == null) {
                    System.out.println("Ese Pokémon no existe. Intenta de nuevo.");
                } else if (!seleccionado.estaVivo()) {
                    throw new PokemonDebilitadoException(nombre +" esta debilitado, elige otro.");
                } else {
                    return seleccionado;
                }
            } catch (PokemonDebilitadoException ex) {
                System.out.println("Error: " + ex.getMessage());
            }
        }
    }

    private Pokemon elegirPokemonVivo(Scanner sc, Entrenador entrenador) throws PokemonDebilitadoException {
        List<Pokemon> equipo = entrenador.getEquipo();
        Map<String, Pokemon> mapaPorNombre = crearMapaPorNombre(equipo);
        boolean hayVivos = equipo.stream().anyMatch(Pokemon::estaVivo);
        if (!hayVivos) {
            throw new PokemonDebilitadoException("Todos los Pokémon de " + entrenador.getNombre() + " están debilitados.");
        }

        while (true) {
            System.out.println("\n" + entrenador.getNombre() + ", elige un Pokémon vivo escribiendo su nombre:");
            for (Pokemon p : equipo) {
                System.out.println("- " + p);
            }
            String nombre = sc.nextLine().toLowerCase();
            Pokemon seleccionado = mapaPorNombre.get(nombre);

            try {
                if (seleccionado == null) {
                    System.out.println("Ese Pokémon no existe. Intenta de nuevo.");
                }  else if (!seleccionado.estaVivo()) {
                    throw new PokemonDebilitadoException(nombre + " está debilitado, elige otro.");
                } else {
                    return seleccionado;
                }
            } catch (PokemonDebilitadoException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    private static Map<String, Pokemon> crearMapaPorNombre(List<Pokemon> equipo) {
        Map<String, Pokemon> mapa = new HashMap<>();
        for (Pokemon p : equipo) {
            mapa.put(p.getNombre().toLowerCase(), p);
        }
        return mapa;
    }

    private void guardarEquipos(Entrenador e1, Entrenador e2) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("equipos_guardados.txt"))) {

            //Escribe el nombre del primer entrenador en el archivo
            writer.write("Entrenador: " + e1.getNombre() + "\n");

            //Recorre el equipo del primer entrenador y guarda cada Pokémon en formato texto
            for (Pokemon p : e1.getEquipo()) {
                writer.write(convertirPokemonATexto(p) + "\n"); //Formatea los datos del Pokémon
            }

            //Agrega una línea en blanco y luego escribe el nombre del segundo entrenador
            writer.write("\nEntrenador: " + e2.getNombre() + "\n");

            //Recorre el equipo del segundo entrenador y guarda cada Pokémon en formato texto
            for (Pokemon p : e2.getEquipo()) {
                writer.write(convertirPokemonATexto(p) + "\n");
            }

            //Muestra mensaje de exito si se guarda bien
            System.out.println("Equipos guardados exitosamente.");

//Captura cualquier excepción de entrada/salida
        } catch (IOException e) {
            System.out.println("Error al guardar equipos: " + e.getMessage());
        }
    }

    private List<Entrenador> cargarEquiposDesdeArchivo() {
        //Se crea una lista para almacenar los entrenadores cargados desde el archivo
        List<Entrenador> entrenadores = new ArrayList<>();

//Se usa try-with-resources para asegurarse de que el BufferedReader se cierre solo
        try (BufferedReader reader = new BufferedReader(new FileReader("equipos_guardados.txt"))) {
            String linea;
            Entrenador entrenadorActual = null; //Variable temporal para el entrenador que se esta leyendo
            List<Pokemon> equipo = null;        //Lista temporal para el equipo del entrenador actual

            //Lee el archivo línea por línea
            while ((linea = reader.readLine()) != null) {

                //Si la linea comienza con "Entrenador:", se inicia la carga de un nuevo entrenador
                if (linea.startsWith("Entrenador:")) {
                    //Si ya se estaba leyendo un entrenador anterior, se agrega a la lista
                    if (entrenadorActual != null) {
                        entrenadores.add(entrenadorActual);
                    }

                    //Se extrae el nombre del entrenador de la linea
                    String nombre = linea.substring("Entrenador:".length()).trim();

                    //Se prepara un nuevo equipo para este entrenador
                    equipo = new ArrayList<>();

                    //Se crea un nuevo objeto Entrenador con su nombre y equipo vacio
                    entrenadorActual = new Entrenador(nombre, equipo);

                } else if (!linea.trim().isEmpty()) {
                    //Si la linea no esta vacia, se asume que contiene informacion de un Pokemon

                    //Se divide la linea por comas
                    String[] partes = linea.split(",");

                    //Verifica que tenga el formato esperado
                    if (partes.length < 11) {
                        System.out.println("Línea con formato inválido: " + linea);
                        continue; // Salta esta línea si no está bien formateada
                    }

                    //Se extraen los atributos del Pokemon
                    String nombre = partes[0];
                    TipoPokemon tipo = TipoPokemon.valueOf(partes[1]);
                    int hp = Integer.parseInt(partes[2]);
                    int ataque = Integer.parseInt(partes[3]);
                    int defensa = Integer.parseInt(partes[4]);
                    int ataqueEsp = Integer.parseInt(partes[5]);
                    int defensaEsp = Integer.parseInt(partes[6]);
                    int velocidad = Integer.parseInt(partes[7]);

                    //Se extraen los ataques del Pokemon
                    List<Ataque> ataques = new ArrayList<>();
                    for (int i = 8; i + 2 < partes.length; i += 3) {
                        String nombreAtaque = partes[i];
                        String tipoDanio = partes[i + 1];
                        int potencia = Integer.parseInt(partes[i + 2]);
                        ataques.add(new Ataque(nombreAtaque, tipoDanio, potencia));
                    }

                    //Se crea el objeto Pokemon con todos sus atributos y ataques
                    Pokemon p = new Pokemon(nombre, tipo, hp, ataque, defensa, ataqueEsp, defensaEsp, velocidad, ataques);

                    //Se agrega el Pokemon al equipo del entrenador actual
                    equipo.add(p);
                }
            }

            //Se asegura de agregar el último entrenador leído
            if (entrenadorActual != null) {
                entrenadores.add(entrenadorActual);
            }

        } catch (IOException e) {
            //Captura cualquier error al leer el archivo
            System.out.println("Error al leer el archivo: " + e.getMessage());
        }

//Devuelve la lista de entrenadores cargados desde el archivo
        return entrenadores;

    }

    private String convertirPokemonATexto(Pokemon p) {
        StringBuilder sb = new StringBuilder();
        sb.append(p.getNombre()).append(",");
        sb.append(p.getTipo().name()).append(",");
        sb.append(p.getHp()).append(",");
        sb.append(p.getAtaque()).append(",");
        sb.append(p.getDefensa()).append(",");
        sb.append(p.getAtaqueEspecial()).append(",");
        sb.append(p.getDefensaEspecial()).append(",");
        sb.append(p.getVelocidad());

        for (Ataque a : p.getAtaques()) {
            sb.append(",").append(a.getNombre());
            sb.append(",").append(a.getTipoDanio());
            sb.append(",").append(a.getPotencia());
        }

        return sb.toString();
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