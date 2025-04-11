public enum TipoPokemon {
    FUEGO, AGUA, PLANTA, ELECTRICO, TIERRA, VOLADOR;

    public double calcularVentajaContra(TipoPokemon enemigo) {
        switch (this) {
            case FUEGO:
                return (enemigo == PLANTA) ? 1.3 : 1.0;
            case AGUA:
                return (enemigo == FUEGO || enemigo == TIERRA) ? 1.3 : 1.0;
            case PLANTA:
                return (enemigo == AGUA || enemigo == TIERRA) ? 1.3 : 1.0;
            case ELECTRICO:
                return (enemigo == AGUA || enemigo == VOLADOR) ? 1.3 : 1.0;
            case TIERRA:
                return (enemigo == ELECTRICO || enemigo == FUEGO) ? 1.3 : 1.0;
            case VOLADOR:
                return (enemigo == PLANTA) ? 1.3 : 1.0;
            default:
                return 1.0;
        }
    }
}
//ya no aguanto mas