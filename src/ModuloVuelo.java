public class ModuloVuelo extends Modulo {
    private final TipoInstrumento tipoInstrumento;
    private final double datosPorCiclo;     
    private final double energiaPorCiclo;  
    private int ciclosAcumulados;     

    public ModuloVuelo(String id, String nombre, double salud, boolean activo, double costoConstruccion, TipoInstrumento tipoInstrumento, double datosPorCiclo, double energiaPorCiclo, int ciclosAcumulados) {
        super(id, nombre, salud, activo, costoConstruccion);
        this.tipoInstrumento = tipoInstrumento;
        this.datosPorCiclo = datosPorCiclo;
        this.energiaPorCiclo = energiaPorCiclo;
        this.ciclosAcumulados = ciclosAcumulados;
    }

    @Override
    public String procesarCiclo(Mision mision) {
        String prefijo = "Vuelo: "+ getId()+ " "+getNombre()+" " +tipoInstrumento;
        if (!isActivo()) {
            return prefijo + "El módulo está inactivo";
        }
        if (mision.getEnergiaDisponible() < energiaPorCiclo) {
            return prefijo + "Energía insuficiente, se necesitan "+energiaPorCiclo+ "y solo se tiene: "+ mision.getEnergiaDisponible();
        }
        mision.modificarEnergia(-energiaPorCiclo);
        mision.modificarDatosEnOrbita(datosPorCiclo);
        ciclosAcumulados++;
        return prefijo + "Se recolectó: "+datosPorCiclo+" y consumió: "+energiaPorCiclo+ " de energía";
    }

    public TipoInstrumento getTipoInstrumento() {
        return tipoInstrumento;
    }

    public double getDatosPorCiclo() {
        return datosPorCiclo;
    }

    public double getEnergiaPorCiclo() {
        return energiaPorCiclo;
    }

    public int getCiclosAcumulados() {
        return ciclosAcumulados;
    }

    public double getTotalRecolectado() {
        return datosPorCiclo * ciclosAcumulados;
    }

    @Override
    public String toString() {
        return super.toString()+" Tipo: Módulo de Vuelo, Instrumento: "+tipoInstrumento+" Datos por ciclo: "+datosPorCiclo+"  Energía por ciclo: "+energiaPorCiclo+" Ciclos acumulados: "+ ciclosAcumulados+ " Total recolectado" +getTotalRecolectado();     
    }
}
