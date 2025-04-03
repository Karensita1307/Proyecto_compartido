import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import utils.Ataques_P;
import utils.Tipos_P;

public class Entrenador {
    private String nombre;
    private String equipo;
    private ArrayList <Ataques_P> ataques = new ArrayList<>();
    private ArrayList <Tipos_P> tipos = new ArrayList<>();

    public Entrenador(String nombre, String equipo) {
        this.nombre = nombre;
        this.equipo = equipo;
        ataques = new ArrayList<Ataques_P>();
        tipos = new ArrayList<Tipos_P>();
    }

    public String getNombre() {
        return nombre;
    }


    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setAtaques() {
        Ataques_P ListaAtaques[] = Ataques_P.values();
        Scanner scanner = new Scanner(System.in);

        System.out.println("selecciona cuatro ataques :");
        System.out.println("Ataques disponibles:");
                for(Ataques_P ataque: Ataques_P.values()){
                    System.out.println("-" + ataque);
                }
        for (int i = 0; i < 4; i++) {
            while (true) {
                System.out.print("Selecciona un ataque: ");
                String ataqueS = scanner.nextLine();
                try{
                    Ataques_P ataqueSeleccionado = Ataques_P.valueOf(ataqueS);
                    ataques.add(ataqueSeleccionado);//Agregar el ataque seleccionado a la lista
                    break; // Salir del bucle si el tipo es valido
                } catch (IllegalArgumentException e) {
                    System.out.println("Ataque no válido. Intenta de nuevo.");
                }
            }
        }
        System.out.println("Has elegido: " + ataques);
    }

    public void setTipos() {
        Tipos_P listaTipos[] = Tipos_P.values();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Selecciona 3 tipos para el Pokémon:");
        System.out.println("Tipos disponibles:");
                for (Tipos_P tipo : listaTipos) {
                    System.out.println("- " + tipo);
                }
        for (int i = 0; i < 3; i++) {
            while (true) {
                System.out.print("Selecciona un tipo: ");
                String tipoS = scanner.nextLine();
                try {
                    Tipos_P tipoSeleccionado = Tipos_P.valueOf(tipoS);
                    tipos.add(tipoSeleccionado); // Agregar el tipo seleccionado a la lista
                    break; // Salir del bucle si el tipo es válido
                } catch (IllegalArgumentException e) {
                    System.out.println("Tipo no válido. Intenta de nuevo.");
                }
            }
        }
        System.out.println("Has elegido: " + tipos);
    }

    public String getEquipo() {
        return equipo;
    }


    public void setEquipo(String equipo) {
        this.equipo = equipo;
    }

    public ArrayList<Ataques_P> getAtaques() {
        return ataques;
    }

    public ArrayList<Tipos_P> getTipos() {
        return tipos;
    }


}  
