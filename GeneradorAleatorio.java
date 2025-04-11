import java.util.*;

public class GeneradorAleatorio {

    private static final String[] nombresPokemon = {
        "Charmander", "Squirtle", "Bulbasaur", "Pikachu", "Sandshrew", "Pidgey",
        "Growlithe", "Poliwag", "Oddish", "Magnemite", "Diglett", "Zubat"
    };

    private static final String[] nombresAtaques = {
        "Placaje", "Lanzallamas", "Pistola Agua", "Impactrueno", "Latigazo", "Tornado", "Terremoto", "Rayo Solar"
    };

    private static final String[] tiposDanio = {"Físico", "Especial"};

    private static final Random random = new Random();

    public static List<Pokemon> generarEquipoAleatorio() {
        List<Pokemon> equipo = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            String nombre = nombresPokemon[random.nextInt(nombresPokemon.length)];
            TipoPokemon tipo = TipoPokemon.values()[random.nextInt(TipoPokemon.values().length)];
            int hp = 100 + random.nextInt(101); // entre 100 y 200

            List<Ataque> ataques = new ArrayList<>();
            for (int j = 0; j < 2; j++) {
                String nomAtaque = nombresAtaques[random.nextInt(nombresAtaques.length)];
                String tipoDanio = tiposDanio[random.nextInt(tiposDanio.length)];
                int potencia = 20 + random.nextInt(81); // entre 20 y 100
                ataques.add(new Ataque(nomAtaque, tipoDanio, potencia));
            }

            equipo.add(new Pokemon(nombre, tipo, hp, ataques));
        }
        return equipo;
    }
}

