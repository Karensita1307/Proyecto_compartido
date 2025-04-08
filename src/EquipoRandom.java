import java.util.ArrayList;
import java.util.Scanner;

import utils.Tipos_P;
import utils.Ataques_P;

public class EquipoRandom {
    //Atributos
    private ArrayList<String> nombre = new ArrayList<>();
    private ArrayList<String> puntosSaludRandom = new ArrayList<>();
    private ArrayList<Tipos_P> tiposRandom = new ArrayList<>();
    private ArrayList<String> ataquesRandom = new ArrayList<>();

    //Metodos
    public void atacar() {
        System.out.println("Nada por el momento");
    }

    public void recibirDaño() {
        System.out.println("Nada por el momento");
    }

    //Constructor
    public EquipoRandom() {
        nombre = new ArrayList<String>();
        puntosSaludRandom = new ArrayList<String>();
        tiposRandom = new ArrayList<Tipos_P>();
        ataquesRandom = new ArrayList<String>();
    }

    //Setters
    public void setNombre() {
        System.out.println("Nombres de los Pokémones");
        Scanner scanner = new Scanner(System.in);
        for (int i = 0; i < 3; i++) {
            System.out.print("Ingrese el nombre del Pokémon " + (i + 1) + ": "); //Pide nombres del pokémon y los agrega a la lista
            String nombreIngresado = scanner.nextLine();
            nombre.add(nombreIngresado);
        }
    }

    public void setTiposRandom() {
        Tipos_P[] todos = Tipos_P.values();
        while (tiposRandom.size() < 3) {
            int indice = (int) (Math.random() * todos.length); //Número aleatorio
            Tipos_P randomPoke = todos[indice];
            tiposRandom.add(randomPoke);
        }
    }

    public void setAtaquesRandom() {
        Ataques_P[] todos = Ataques_P.values();
        while (ataquesRandom.size() < 12) {
            int indice = (int) (Math.random() * todos.length); //Número aleatorio
            Ataques_P randomPoke = todos[indice];
            ataquesRandom.add(String.valueOf(randomPoke));
            int tipoR = (int)(Math.random() * 2);
            //Agregamos aleatoriamente String
            if (tipoR == 0) {
                ataquesRandom.add("Físico");
            } else {
                ataquesRandom.add("Especial");
            }
            int dañoR = (int)(Math.random() * 101); //Agregamos daño aleatorio
            ataquesRandom.add(String.valueOf(dañoR));
        }
    }

    public void setPuntosSaludRandom() {
        while(puntosSaludRandom.size() < 3){
            int indice = (int) (Math.random() * 101); //Obtenemos valor random
            puntosSaludRandom.add(String.valueOf(indice));
        }
    }

    //Getters
    public ArrayList<String> getNombre() {
        return nombre;
    }

    public ArrayList<String> getPuntosSaludRandom() {
        return puntosSaludRandom;
    }

    public ArrayList<String> getAtaquesRandom() {
        return ataquesRandom;
    }

    public ArrayList<Tipos_P> getTiposRandom() {
        return tiposRandom;
    }
}
