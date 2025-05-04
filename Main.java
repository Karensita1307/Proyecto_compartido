import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("_____BATALLA DE POKEMONES_____");
        System.out.print("Ingrese el nombre del primer entrenador: ");
        String nombre1 = sc.nextLine();
        List<Pokemon> equipo1 = crearEquipo(sc);
        Entrenador entrenador1 = new Entrenador(nombre1, equipo1);

        System.out.print("Ingrese el nombre del segundo entrenador: ");
        String nombre2 = sc.nextLine();
        List<Pokemon> equipo2 = crearEquipo(sc);
        Entrenador entrenador2 = new Entrenador(nombre2, equipo2);
        System.out.println("--------------------------------------------");
        System.out.println("Entrenadores, ¡Preparence!");
        imprimirEquipo(entrenador1.getNombre(), equipo1);
        imprimirEquipo(entrenador2.getNombre(), equipo2);
        System.out.println("¡¡¡Que Empiece La Batalla!!!");
        Pokemon poke1 = entrenador1.elegirPokemon();
        Pokemon poke2 = entrenador2.elegirPokemon();

        while (poke1 != null && poke2 != null) {
            System.out.println("\n" + poke1.getNombre() + " (HP: " + poke1.getHp() + ") (VEL: " + poke1.getVelocidad() + ") VS " + poke2.getNombre() + " (HP: " + poke2.getHp() + ") (VEL: " + poke2.getVelocidad() + ")");

            Pokemon primero = (poke1.getVelocidad() >= poke2.getVelocidad()) ? poke1 : poke2;
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

        sc.close();
    }

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

    private static Ataque elegirAtaque(Scanner sc, Pokemon pokemon) {
        System.out.println("Elige un ataque para " + pokemon.getNombre() + ":");
        List<Ataque> ataques = pokemon.getAtaques();
        for (int i = 0; i < ataques.size(); i++) {
            System.out.println((i + 1) + ". " + ataques.get(i).getNombre() + " (Potencia: " + ataques.get(i).getPotencia() + ")");
        }
        int opcion = sc.nextInt();
        sc.nextLine();
        return ataques.get(opcion - 1);
    }
    private static void imprimirEquipo(String nombreEntrenador, List<Pokemon> equipo) {
        System.out.println("Equipo de " + nombreEntrenador + ":");
        for (Pokemon p : equipo) {
            System.out.println("  - " + p.getNombre() + " (" + p.getTipo() + ", HP: " + p.getHp() + ")");
            System.out.println("    Ataques:");
            for (Ataque a : p.getAtaques()) {
                System.out.println("      * " + a.getNombre() + " (Tipo: " + a.getTipoDanio() + ", Potencia: " + a.getPotencia() + ")");
            }
        }
        System.out.println();
    }
}

