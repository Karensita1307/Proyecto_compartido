import java.util.Arrays;

import utils.Ataques_P;
import utils.Tipos_P;

public class Main {
    public static void main(String[] args) {
        Pokemon poque1 = new Pokemon("Carlos", (short) 100);
        Pokemon poque2 = new Pokemon("Bryan", (short) 105);
        Pokemon poque3 = new Pokemon("Karen", (short) 99);
        poque1.setAtaques();
        System.out.println(Arrays.toString(poque1.getAtaques().toArray()));
        //poque1.setTipos();
        //System.out.println(Arrays.toString(poque1.getTipos().toArray()));
    }

}
