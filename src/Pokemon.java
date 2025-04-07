import java.util.ArrayList;
import java.util.Scanner;

import utils.Tipos_P;
import utils.Ataques_P;

public class Pokemon {
    //Atributos
    private ArrayList <String> nombre = new ArrayList<>();
    private ArrayList <String> puntosSalud = new ArrayList<>();
    private ArrayList <Tipos_P> tipos = new ArrayList<>();
    private ArrayList <String> ataques = new ArrayList<>();

    //Recolectamos la información total de los Pokémones
    private ArrayList <String> infoPoke1 = new ArrayList<>();
    private ArrayList <String> infoPoke2 = new ArrayList<>();
    private ArrayList <String> infoPoke3 = new ArrayList<>();

    //Metodos
    public void atacar(){
        System.out.println("Nada por el momento");
    }
    public void recibirDaño(){
        System.out.println("Nada por el momento");
    }
    
    //Constructor 
    public Pokemon() {
        nombre = new ArrayList<String>();
        puntosSalud = new ArrayList<String>();
        tipos = new ArrayList<Tipos_P>();
        ataques = new ArrayList<String>();
    }

    //Setters
    public void setNombre() {
        System.out.println("----Nombres de los Pokémones----");
        Scanner scanner = new Scanner(System.in);
        for (int i = 0; i < 3; i++) {
            System.out.print("Ingrese el nombre del Pokémon " +(i+1) +": ");
            String nombreIngresado = scanner.nextLine();
            nombre.add(nombreIngresado);
        }
    }

    public void setTipos() {
        Tipos_P listaTipos[] = Tipos_P.values();
        Scanner scanner = new Scanner(System.in);

        System.out.println("----Tipos de Pokémon----");
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
    }
    public void setAtaques(){
        System.out.println("----Ataques de los Pokémones----");
        Scanner scanner = new Scanner(System.in);
        for(int i = 0; i < 4; i++){
            System.out.println("Pokémon " +(i+1) +": ");
            System.out.print("Ingrese el nombre del ataque: ");
            String nombreAtaque = scanner.nextLine();
            ataques.add(nombreAtaque);
            System.out.print("Ingrese el tipo de daño del ataque (Fisico o Especial): ");
            String tipoAtaque = scanner.nextLine();
            while(true){
                if(tipoAtaque.equalsIgnoreCase("Especial") || tipoAtaque.equalsIgnoreCase("Fisico")){
                    ataques.add(tipoAtaque);
                    break;
                } else {
                    System.out.print("Tipo de daño del ataque no valido, Por favor seleccione las opciones disponibles: ");
                    tipoAtaque = scanner.nextLine();
                }
            }
            System.out.print("Ingrese el daño del ataque (Máx 30): ");
            //Obtenemos el String
            String entrada = scanner.nextLine();
            //Convertimos el texto a numero
            int dañoAtaque = Integer.parseInt(entrada);
            while(true){
                if(dañoAtaque < 0 || dañoAtaque > 30){
                    System.out.print("Dato invalido. Por favor ingrese nuevamente el daño: ");
                    entrada = scanner.nextLine();
                    dañoAtaque = Integer.parseInt(entrada);
                } else {
                    ataques.add(String.valueOf(dañoAtaque));
                    break;
                }
            }
        }
    }

    public void setPuntosSalud() {
        System.out.println("----Puntos de Salud de los Pokémones----");
        Scanner scanner = new Scanner(System.in);
        for (int i = 0; i < 3; i++) {
            System.out.print("Ingrese la vida del Pokémon " +(i+1) +" (Máx 100): ");
            String entrada = scanner.nextLine();
            int vidaPokemon = Integer.parseInt(entrada);
            while(true){
                if(vidaPokemon < 0 || vidaPokemon > 100){
                    System.out.print("Dato invalido. Por favor ingrese nuevamente la vida: ");
                    entrada = scanner.nextLine();
                    vidaPokemon = Integer.parseInt(entrada);
                } else {
                    puntosSalud.add(String.valueOf(vidaPokemon));
                    break;
                }
            }
        }
    }

    public void setInfoPoke1(){
        infoPoke1.add(nombre.get(0));
        infoPoke1.add(String.valueOf(tipos.get(0)));
        infoPoke1.add(puntosSalud.get(0));
        infoPoke1.add(ataques.get(0));
        infoPoke1.add(ataques.get(2));
        infoPoke1.add(ataques.get(3));
        infoPoke1.add(ataques.get(5));
        infoPoke1.add(ataques.get(6));
        infoPoke1.add(ataques.get(8));
        infoPoke1.add(ataques.get(9));
        infoPoke1.add(ataques.get(11));
    }
    public void setInfoPoke2(){
        infoPoke2.add(nombre.get(1));
        infoPoke2.add(String.valueOf(tipos.get(1)));
        infoPoke2.add(puntosSalud.get(1));
        infoPoke2.add(ataques.get(0));
        infoPoke2.add(ataques.get(2));
        infoPoke2.add(ataques.get(3));
        infoPoke2.add(ataques.get(5));
        infoPoke2.add(ataques.get(6));
        infoPoke2.add(ataques.get(8));
        infoPoke2.add(ataques.get(9));
        infoPoke2.add(ataques.get(11));
    }
    public void setInfoPoke3(){
        infoPoke3.add(nombre.get(2));
        infoPoke3.add(String.valueOf(tipos.get(2)));
        infoPoke3.add(puntosSalud.get(2));
        infoPoke3.add(ataques.get(0));
        infoPoke3.add(ataques.get(2));
        infoPoke3.add(ataques.get(3));
        infoPoke3.add(ataques.get(5));
        infoPoke3.add(ataques.get(6));
        infoPoke3.add(ataques.get(8));
        infoPoke3.add(ataques.get(9));
        infoPoke3.add(ataques.get(11));
    }

    //Getters
    public ArrayList<String>  getNombre() {
        return nombre;
    }

    public ArrayList<Tipos_P> getTipos() {
        return tipos;
    }

    public ArrayList<String> getAtaques() {
        return ataques;
    }

    public ArrayList<String> getPuntosSalud() {
        return puntosSalud;
    }

    public ArrayList<String> getInfoPoke1() {
        return infoPoke1;
    }

    public ArrayList<String> getInfoPoke2() {
        return infoPoke2;
    }

    public ArrayList<String> getInfoPoke3() {
        return infoPoke3;
    }
}
