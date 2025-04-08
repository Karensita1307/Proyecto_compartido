import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Crear un Scanner para leer la entrada del usuario
        Scanner scanner = new Scanner(System.in);
        int option;

        do {
            System.out.println("_____BATALLA DE POKEMONES_____");
            System.out.println("1. Crear mi equipo Pokémon.");
            System.out.println("2. Crear automáticamente mi equipo.");
            System.out.println("3. Salir.");

            System.out.print("Seleccione una opción: ");
            option = scanner.nextInt();
            scanner.nextLine();//Salto de linea

            switch (option) {
                case 1:
                    //Crear el primer entrenador
                    System.out.print("Ingrese el nombre del primer entrenador: ");
                    String nombreEntrenador1 = scanner.nextLine();
                    System.out.print("Ingrese el nombre del equipo: ");
                    String nombreEquipo1 = scanner.nextLine();
                    Entrenador entrenador1 = new Entrenador(nombreEntrenador1, nombreEquipo1);
                    Pokemon pokeEntrenador1 = new Pokemon();
                    //Crear primer equipo Pokémon
                    pokeEntrenador1.setTipos();
                    pokeEntrenador1.setNombre();
                    pokeEntrenador1.setPuntosSalud();
                    pokeEntrenador1.setAtaques();

                    //Muestra de datos del primer entrenador
                    System.out.println("----Información del Entrenador 1----");
                    System.out.println("Nombre: " + entrenador1.getNombre());
                    System.out.println("Equipo: " + entrenador1.getEquipo());
                    System.out.println("Nombres de los Pokémones: " + pokeEntrenador1.getNombre());
                    System.out.println("Tipos: " + pokeEntrenador1.getTipos());
                    System.out.println("HP: " +pokeEntrenador1.getPuntosSalud());
                    System.out.println("Ataques: " + pokeEntrenador1.getAtaques());

                    // Crear el segundo entrenador
                    System.out.print("Ingrese el nombre del segundo entrenador: ");
                    String nombreEntrenador2 = scanner.nextLine();
                    System.out.print("Ingrese el nombre del equipo: ");
                    String nombreEquipo2 = scanner.nextLine();
                    Entrenador entrenador2 = new Entrenador(nombreEntrenador2, nombreEquipo2);
                    Pokemon pokeEntrenador2 = new Pokemon();
                    //Crear segundo equipo Pokémon
                    pokeEntrenador2.setTipos();
                    pokeEntrenador2.setNombre();
                    pokeEntrenador2.setPuntosSalud();
                    pokeEntrenador2.setAtaques();

                    //Muestra de datos del segundo entrenador
                    System.out.println("----Información del Entrenador 2----");
                    System.out.println("Nombre: " + entrenador2.getNombre());
                    System.out.println("Equipo: " + entrenador2.getEquipo());
                    System.out.println("Nombres de los Pokémones: " + pokeEntrenador2.getNombre());
                    System.out.println("Tipos: " + pokeEntrenador2.getTipos());
                    System.out.println("HP: " +pokeEntrenador2.getPuntosSalud());
                    System.out.println("Ataques: " + pokeEntrenador2.getAtaques());

                    //Empieza la batalla - Instanciamos primer entrenador
                    pokeEntrenador1.setInfoPoke1();
                    pokeEntrenador1.setInfoPoke2();
                    pokeEntrenador1.setInfoPoke3();

                    System.out.println("--------------------------------------------");
                    System.out.println("Entrenadores, ¡Preparence!");
                    System.out.println("¡¡¡Que Empiece La Batalla!!!");
                    System.out.println(entrenador1.getNombre() +", elige tu Pokémon! ");
                    //Muestra equipo Pokémon
                    System.out.println(pokeEntrenador1.getInfoPoke1());
                    System.out.println(pokeEntrenador1.getInfoPoke2());
                    System.out.println(pokeEntrenador1.getInfoPoke3());
                    System.out.print("Elige una opción del 1 al 3: ");
                    String entrada = scanner.nextLine();
                    int opc = Integer.parseInt(entrada);
                    //Mandamos lista a metodo de entrenador
                    while(true){
                        if(opc == 1){
                            entrenador1.setPokemonElegido1(pokeEntrenador1.getInfoPoke1());
                            break;
                        } else if(opc == 2){
                            entrenador1.setPokemonElegido1(pokeEntrenador1.getInfoPoke2());
                            break;
                        } else if(opc == 3){
                            entrenador1.setPokemonElegido1(pokeEntrenador1.getInfoPoke3());
                            break;
                        } else {
                            System.out.println("Opción invalida. Intentelo nuevamente.");
                            entrada = scanner.nextLine();
                            opc = Integer.parseInt(entrada);
                        }
                    }

                    //Instanciamos segundo entrenador
                    pokeEntrenador2.setInfoPoke1();
                    pokeEntrenador2.setInfoPoke2();
                    pokeEntrenador2.setInfoPoke3();

                    System.out.println(entrenador2.getNombre() +", elige tu Pokémon! ");
                    //Muestra equipo Pokémon
                    System.out.println(pokeEntrenador2.getInfoPoke1());
                    System.out.println(pokeEntrenador2.getInfoPoke2());
                    System.out.println(pokeEntrenador2.getInfoPoke3());
                    System.out.print("Elige una opción del 1 al 3: ");
                    String entrada1 = scanner.nextLine();
                    int opc1 = Integer.parseInt(entrada1);
                    //Mandamos lista a metodo de entrenador
                    while(true){
                        if(opc1 == 1){
                            entrenador2.setPokemonElegido2(pokeEntrenador2.getInfoPoke1());
                            break;
                        } else if(opc1 == 2){
                            entrenador2.setPokemonElegido2(pokeEntrenador2.getInfoPoke2());
                            break;
                        } else if(opc1 == 3){
                            entrenador2.setPokemonElegido2(pokeEntrenador2.getInfoPoke3());
                            break;
                        } else {
                            System.out.println("Opción invalida. Intentelo nuevamente.");
                            entrada1 = scanner.nextLine();
                            opc1 = Integer.parseInt(entrada1);
                        }
                    }
                    //Mandamos parametros a metodo de Pokémon
                    pokeEntrenador1.comparacionPoke(entrenador1.getPokemonElegido1(), entrenador2.getPokemonElegido2());
                    break;
                case 2:
                    //Crear el primer entrenador random
                    System.out.print("Ingrese el nombre del primer entrenador: ");
                    String nombreEntrenadorR1 = scanner.nextLine();
                    System.out.print("Ingrese el nombre del equipo: ");
                    String nombreEquipoR1 = scanner.nextLine();
                    Entrenador entrenadorR1 = new Entrenador(nombreEntrenadorR1, nombreEquipoR1);
                    EquipoRandom equipoRandom1 = new EquipoRandom();
                    //Crear equipo Pokémon
                    equipoRandom1.setNombre();
                    equipoRandom1.setTiposRandom();
                    equipoRandom1.setPuntosSaludRandom();
                    equipoRandom1.setAtaquesRandom();

                    //Muestra de datos del primer entrenador random
                    System.out.println("----Información del Entrenador 1----");
                    System.out.println("Entrenador: " + entrenadorR1.getNombre());
                    System.out.println("Equipo: " + entrenadorR1.getEquipo());
                    System.out.println("Nombres de los Pokémones: " + equipoRandom1.getNombre());
                    System.out.println("Tipos: " + equipoRandom1.getTiposRandom());
                    System.out.println("HP: " + equipoRandom1.getPuntosSaludRandom());
                    System.out.println("Ataques: " + equipoRandom1.getAtaquesRandom());

                    //Crea el segundo entrenador random
                    System.out.print("Ingrese el nombre del segundo entrenador: ");
                    String nombreEntrenadorR2 = scanner.nextLine();
                    System.out.print("Ingrese el nombre del equipo: ");
                    String nombreEquipoR2 = scanner.nextLine();
                    Entrenador entrendorR2 = new Entrenador(nombreEntrenadorR2, nombreEquipoR2);
                    EquipoRandom equipoRandom2 = new EquipoRandom();
                    //Crea equipo Pokémon
                    equipoRandom2.setNombre();
                    equipoRandom2.setTiposRandom();
                    equipoRandom2.setPuntosSaludRandom();
                    equipoRandom2.setAtaquesRandom();

                    //Muestra de datos del primer entrenadorR2 random
                    System.out.println("----Información del Entrenador 2----");
                    System.out.println("Entrenador: " + entrendorR2.getNombre());
                    System.out.println("Equipo: " + entrendorR2.getEquipo());
                    System.out.println("Nombres de los Pokémones: " + equipoRandom2.getNombre());
                    System.out.println("Tipos: " + equipoRandom2.getTiposRandom());
                    System.out.println("HP: " + equipoRandom2.getPuntosSaludRandom());
                    System.out.println("Ataques: " + equipoRandom2.getAtaquesRandom());
                    break;
                case 3:
                    System.out.println("Saliendo del programa...");
                    break;
                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
            }
        } while (option != 3);

        scanner.close();
    }
}