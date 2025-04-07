import java.util.ArrayList;
import java.util.Scanner;

import utils.Tipos_P;
import utils.Ataques_P;

public class EquipoRandom {
    //Atributos
    private ArrayList<String> nombre = new ArrayList<>();
    private ArrayList<String> puntosSaludRandom = new ArrayList<>();
    private ArrayList<Tipos_P> tiposRandom = new ArrayList<>();
    private ArrayList<Ataques_P> ataquesRandom = new ArrayList<>();

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
        ataquesRandom = new ArrayList<Ataques_P>();
    }

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
        while (ataquesRandom.size() < 4) {
            int indice = (int) (Math.random() * todos.length); // número aleatorio
            Ataques_P randomPoke = todos[indice];
            ataquesRandom.add(randomPoke);
        }
    }

    public void setPuntosSaludRandom() {
        while(puntosSaludRandom.size() < 3){
            int indice = (int) (Math.random() * 101); //Obtenemos valor random
            if(indice >= 50){
                puntosSaludRandom.add(String.valueOf(indice));
            }
        }
    }


    public ArrayList<String> getNombre() {
        return nombre;
    }

    public ArrayList<String> getPuntosSaludRandom() {
        return puntosSaludRandom;
    }

    public ArrayList<Ataques_P> getAtaquesRandom() {
        return ataquesRandom;
    }

    public ArrayList<Tipos_P> getTipos() {
        return tiposRandom;
    }
}
