public abstract class Modulo {
    private final String id;
    private final String nombre;
    private double salud;
    private boolean activo;
    private final double costoConstruccion;

    protected Modulo(String id, String nombre, double salud, boolean activo, double costoConstruccion) {
        this.id = id;
        this.nombre = nombre;
        this.salud = salud;
        this.activo = activo;
        this.costoConstruccion = costoConstruccion;
    }

    public String getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public double getSalud() {
        return salud;
    }

    public boolean isActivo() {
        return activo;
    }

    public double getCostoConstruccion() {
        return costoConstruccion;
    }

    public abstract String procesarCiclo(Mision mision);

    @Override
    public String toString() {
        return "Id: " + id + " Nombre: " + nombre + " Salud: " + salud + " Activo: " + activo + " Costo de construcción: Q." + costoConstruccion;
    }
}
