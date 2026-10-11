public class ModuloTierra extends Modulo {
    private final String estacion;              
    private final double capacidadDescarga;     
    private final double energiaPorDescarga;    
    private double totalDescargado;         

    public ModuloTierra(String id, String nombre, double salud, boolean activo, double costoConstruccion, String estacion, double capacidadDescarga, double energiaPorDescarga, double totalDescargado) {
        super(id, nombre, salud, activo, costoConstruccion);
        this.estacion = estacion;
        this.capacidadDescarga = capacidadDescarga;
        this.energiaPorDescarga = energiaPorDescarga;
        this.totalDescargado = totalDescargado;
    }

    @Override
    public String procesarCiclo(Mision mision) {
        String prefijo = "Tierra: " + getId() + "  " + getNombre();
        if (!isActivo()) {
            return prefijo + "El módulo está inactivo";
        }
        if (mision.getEnergiaDisponible() < energiaPorDescarga) {
            return prefijo + "Energía insuficiente para descargar, se necesitan: "+energiaPorDescarga+ "y solo se tiene: "+ mision.getEnergiaDisponible();
        }
        if (mision.getDatosEnOrbita() <= 0) {
            return prefijo + "No hay datos pendientes en órbita";
        }

        double descarga = Math.min(capacidadDescarga, mision.getDatosEnOrbita());
        mision.modificarEnergia(-energiaPorDescarga); 
        mision.modificarDatosEnOrbita(-descarga);
        mision.registrarDescargaTotal(descarga);
        totalDescargado += descarga;
        return prefijo + " Descargó: " +descarga+ " capacidad: " + capacidadDescarga+ " y consumió: "+energiaPorDescarga + " de energía";
    }

    public String getEstacion() {
        return estacion;
    }

    public double getCapacidadDescarga() {
        return capacidadDescarga;
    }

    public double getEnergiaPorDescarga() {
        return energiaPorDescarga;
    }

    public double getTotalDescargado() {
        return totalDescargado;
    }

    @Override
    public String toString() {
        return super.toString() + " Tipo: Módulo de Tierra,  Estación: "+ estacion+ " Capacidad de descarga: "+ capacidadDescarga+" Energía por descarga: "+ energiaPorDescarga+" Total descargado: "+ totalDescargado;
    }
}
