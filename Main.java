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
        System.out.println("¡¡¡Que Empiece La Batalla!!!");
        Pokemon poke1 = entrenador1.elegirPokemon();
        Pokemon poke2 = entrenador2.elegirPokemon();

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

        sc.close();
    }

    private static List<Pokemon> crearEquipo(Scanner sc) {
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
            sc.nextLine(); // limpiar buffer

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

            equipo.add(new Pokemon(nombre, tipo, hp, ataques));
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
        sc.nextLine(); // limpiar buffer
        return ataques.get(opcion - 1);
    }
}

