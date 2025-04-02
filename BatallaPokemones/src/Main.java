import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Crear un Scanner para leer la entrada del usuario
        Scanner scanner = new Scanner(System.in);
        int option;

        do {
            System.out.println("_____BATALLA DE POKEMONES_____");
            System.out.println("1. Crear mi equipo pokemon");
            System.out.println("2. Crear automaticamente mi equipo");
            System.out.println("3. Salir");

            System.out.print("Seleccione una opción: ");
            option = scanner.nextInt();
            scanner.nextLine();//Salto de linea

            switch (option) {
                case 1:
                    //Datos del primer entrenador
                    System.out.print("Ingrese el nombre del  primer entrenador: ");
                    String nombreEntrenador1 = scanner.nextLine();
                    System.out.print("Ingrese el nombre del equipo: ");
                    String nombreEquipo1 = scanner.nextLine();
                    Entrenador poke1 = new Entrenador(nombreEntrenador1, nombreEquipo1);
                    poke1.setTipos();
                    poke1.setAtaques();

                    //Datos finales del primer entrenador 
                    System.out.println("Entrenador 1: " + poke1.getNombre());
                    System.out.println("Equipo 1: " + poke1.getEquipo());
                    System.out.println("Tipos: " + poke1.getTipos());
                    System.out.println("Ataques: " + poke1.getAtaques());

                    // Crear el segundo entrenador
                    System.out.print("Ingrese el nombre del  segundo entrenador: ");
                    String nombreEntrenador2 = scanner.nextLine();
                    System.out.print("Ingrese el nombre del equipo: ");
                    String nombreEquipo2 = scanner.nextLine();
                    Entrenador poke2 = new Entrenador(nombreEntrenador2, nombreEquipo2);
                    poke2.setTipos();
                    poke2.setAtaques();

                    //Datos finales del segundo entrenador 
                    System.out.println("Entrenador 2: " + poke2.getNombre());
                    System.out.println("Equipo 2: " + poke2.getEquipo());
                    System.out.println("Tipos: " + poke2.getTipos());
                    System.out.println("Ataques: " + poke2.getAtaques());
                    break;
                case 2:
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
