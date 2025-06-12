package modelo;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GeneradorAleatorio {
    //Metodo estatico nombres de Pokemon
    private static final String[] nombresPokemon = {
            "Charmander", "Squirtle", "Bulbasaur", "Pikachu", "Sandshrew", "Pidgey",
            "Growlithe", "Poliwag", "Oddish", "Magnemite", "Diglett", "Zubat", "Snorlax", "Charizard", "Liten",
            "Chimchar", "Piplup", "Sobble", "Mudkip", "Snivy", "Sprigatito"
    };

    //Metodo estatico nombres de Ataque
    private static final String[] nombresAtaques = {
            "Placaje", "Lanzallamas", "Pistola Agua", "Impactrueno", "Latigazo", "Tornado", "Terremoto", "Rayo Solar",
            "Puño", "Machetazo", "Chanclazo", "Correazo", "Llama Feroz", "Hidrobomba", "Cascada", "Marea viva", "Burbuja",
            "Hoja Afilada", "Hierva Loca", "Descarga", "Ondatron", "Martillazo", "Bofetada Psiquica"
    };

    //Metodo estatico tipo de daño
    private static final String[] tiposDanio = {"Físico", "Especial"};

    private static final Random random = new Random(); //Instanciamos

    //Metodo estatico generar equipo aleatorio
    public static List<Pokemon> generarEquipoAleatorio() {
        List<Pokemon> equipo = new ArrayList<>();

        //Se ejecuta 3 veces para obtener equipo Pokemon
        for (int i = 0; i < 3; i++) {
            String nombre = nombresPokemon[random.nextInt(nombresPokemon.length)]; //Se obtiene nombre Pokemon random
            TipoPokemon tipo = TipoPokemon.values()[random.nextInt(TipoPokemon.values().length)]; //Se obtiene tipo Pokemon random
            int hp = 100 + random.nextInt(101); //Se obtiene vida Pokemon random ( Entre 100 y 200 )

            //Se obtienen datos ( Entre 30 y 80 )
            int ataque = 30 + random.nextInt(51);
            int defensa = 30 + random.nextInt(51);
            int atEspecial = 30 + random.nextInt(51);
            int defEspecial = 30 + random.nextInt(51);
            int velocidad = 30 + random.nextInt(51);

            List<Ataque> ataques = new ArrayList<>();
            //Se ejecuta 2 veces para obtener ataques
            for (int j = 0; j < 2; j++) {
                String nomAtaque = nombresAtaques[random.nextInt(nombresAtaques.length)]; //Se obtiene nombre ataque random
                String tipoDanio = tiposDanio[random.nextInt(tiposDanio.length)]; //Se obtiene tipo daño random
                int potencia = 20 + random.nextInt(81); //Se obtiene potencia random( Entre 20 y 100 )
                ataques.add(new Ataque(nomAtaque, tipoDanio, potencia)); //Añadir datos a la lista
            }
            //Agregar datos a Pokemon
            equipo.add(new Pokemon(nombre, tipo, hp, ataque, defensa, atEspecial, defEspecial, velocidad, ataques));
        }
        return equipo;
    }
}