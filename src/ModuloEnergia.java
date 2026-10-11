public class ModuloEnergia extends Modulo {
    private final double energiaGeneradaPorCiclo;
    private int ciclosAcumulados;  

    public ModuloEnergia(String id, String nombre, double salud, boolean activo, double costoConstruccion, double energiaGeneradaPorCiclo, int ciclosAcumulados) {
        super(id, nombre, salud, activo, costoConstruccion);
        this.energiaGeneradaPorCiclo = energiaGeneradaPorCiclo;
        this.ciclosAcumulados = ciclosAcumulados;
    }

    @Override
    public String procesarCiclo(Mision mision) {
        String prefijo = "Energía: " + getId() + " - " + getNombre();

        if (!isActivo()) {
            return prefijo + "Está inactivo";
        }

        mision.modificarEnergia(energiaGeneradaPorCiclo);
        ciclosAcumulados++;
        return prefijo + "generó " + String.format("%.1f", energiaGeneradaPorCiclo) + " de energía";
    }

    public double getEnergiaGeneradaPorCiclo() {
        return energiaGeneradaPorCiclo;
    }

    public int getCiclosAcumulados() {
        return ciclosAcumulados;
    }

    public double getTotalGenerado() {
        return energiaGeneradaPorCiclo * ciclosAcumulados;
    }

    @Override
    public String toString() {
        return super.toString() + "Tipo: Módulo de Energía, Energía por ciclo: "+energiaGeneradaPorCiclo+" Ciclos acumulados: "+ciclosAcumulados+ "Total generado: "+getTotalGenerado();
    }
}
