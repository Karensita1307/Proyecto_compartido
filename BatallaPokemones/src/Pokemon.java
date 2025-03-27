import utils.Ataques_P;
import utils.Tipos_P;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;


public class Pokemon {
    //Atributos
    private String nombre;
    private short puntosSalud;
    private ArrayList <Ataques_P> ataques = new ArrayList<>();
    private ArrayList <Tipos_P> tipos = new ArrayList<>();

    //Metodos
    public void atacar(){
        System.out.println("Nada por el momento");
    }
    public void recibirDaño(){
        System.out.println("Nada por el momento");
    }
    
    //Constructor 
    public Pokemon(String nombre, short puntosSalud) {
        this.nombre = nombre;
        this.puntosSalud = puntosSalud;
        ataques = new ArrayList<Ataques_P>();
        tipos = new ArrayList<Tipos_P>();

    }

    //Setters
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public void setPuntosSalud(short puntosSalud) {
        this.puntosSalud = puntosSalud;
    }
    public void setAtaques() {
        Ataques_P ListaAtaques[] = Ataques_P.values();
        for (int i = 0; i < 4; i++) {
            System.out.println("Ingresa cuatro ataques (uno por uno):");
            for(Ataques_P ataque: Ataques_P.values()){
            if(ataque != null)
                System.out.println("-" + ataque);
            }
            try (Scanner scanner = new Scanner(System.in)) {
                String ataqueS = scanner.next();
                Ataques_P ataquen = Ataques_P.valueOf(ataqueS);
                ataques.add(ataquen);
                ListaAtaques[Arrays.binarySearch(ListaAtaques, ataquen)] = null;
            }
        }
    }
    public void setTipos() {
        Tipos_P Listatipos[] = Tipos_P.values();
        for (int i = 0; i < 1; i++) {
            System.out.println("Ingrese el tipo:");
            for(Tipos_P tipos: Tipos_P.values()){
            if(tipos != null)
                System.out.println("-" + tipos);
            }
            try (Scanner scanner = new Scanner(System.in)) {
                String tipoS = scanner.next();
                Ataques_P tipon = Ataques_P.valueOf(tipoS);
                ataques.add(tipon);
                Listatipos[Arrays.binarySearch(Listatipos, tipon)] = null;
            }
        }
    }


    //Getters
    public String getNombre() {
        return nombre;
    }
    public short getPuntosSalud() {
        return puntosSalud;
    }
    public ArrayList<Ataques_P> getAtaques() {
        return ataques;
    }
    public ArrayList<Tipos_P> getTipos() {
        return tipos;
    }
    


}
