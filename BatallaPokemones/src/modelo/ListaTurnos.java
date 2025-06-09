package modelo;

public class ListaTurnos {
    private NodoPokemon cabeza; //Atributo tipo NodoPokemon

    public void agregarPorVelocidad(Pokemon p) {
        NodoPokemon nuevo = new NodoPokemon(p); //Instanciamos

        //Si la lista es vacia o el nuevo Pokemon es mas rapido que el primero
        if (cabeza == null || p.getVelocidad() > cabeza.pokemon.getVelocidad()) {
            nuevo.siguiente = cabeza;
            cabeza = nuevo; //El nuevo Pokemon se convierte en cabeza
        } else {
            NodoPokemon actual = cabeza;
            //Buscar la posicion donde insertar ( Hasta encontrar uno con menor velocidad )
            while (actual.siguiente != null && actual.siguiente.pokemon.getVelocidad() >= p.getVelocidad()) {
                actual = actual.siguiente;
            }
            //Se agrega el Pokemon en orden de acuerdo a su velocidad
            nuevo.siguiente = actual.siguiente;
            actual.siguiente = nuevo;
        }
    }

    //Metodo obtener y reinsertar Pokemon
    public Pokemon obtenerYReinsertar() {
        if (cabeza == null) return null;

        Pokemon primero = cabeza.pokemon;
        cabeza = cabeza.siguiente; //Avanzar en la lista ( Remover el primero )

        //Si el Pokemon sigue vivo, se vuelve a insertar en la lista por velocidad
        if (primero.estaVivo()) {
            agregarPorVelocidad(primero);
        }
        return primero;
    }
}
