import java.util.ArrayList;
import java.util.List;

public class Mision {
    private double energiaDisponible;
    private double datosEnOrbita;
    private double datosDescargadosTotales;
    private final List<Modulo> modulos;

    public Mision() {
        this.energiaDisponible = 0;
        this.datosEnOrbita = 0;
        this.datosDescargadosTotales = 0;
        this.modulos = new ArrayList<>();
    }

    public double getEnergiaDisponible() {
        return energiaDisponible;
    }

    public void modificarEnergia(double cantidad) {
        double nuevo = energiaDisponible + cantidad;
        if (nuevo < 0) {
            throw new IllegalArgumentException("La energía disponible no puede ser negativa");
        }
        energiaDisponible = nuevo;
    }

    public double getDatosEnOrbita() {
        return datosEnOrbita;
    }

    public void modificarDatosEnOrbita(double cantidad) {
        double nuevo = datosEnOrbita + cantidad;
        if (nuevo < 0) {
            throw new IllegalArgumentException("Los datos en órbita no pueden ser negativos");
        }
        datosEnOrbita = nuevo;
    }

    public double getDatosDescargadosTotales() {
        return datosDescargadosTotales;
    }

    public void registrarDescargaTotal(double cantidad) {
        if (cantidad < 0) {
            throw new IllegalArgumentException("La cantidad descargada no puede ser negativa");
        }
        datosDescargadosTotales += cantidad;
    }

    public List<Modulo> getModulos() {
        return modulos;
    }

    public void agregarModulo(Modulo modulo) {
        if (modulo != null) {
            modulos.add(modulo);
        }
    }
}
