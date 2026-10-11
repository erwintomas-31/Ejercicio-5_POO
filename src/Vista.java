import java.util.List;
import java.util.Scanner;

public class Vista {
    private final Scanner scanner = new Scanner(System.in);

    public int mostrarMenu() {
        System.out.println("DEFENSA DE SATÉLITE");
        System.out.println("1. Listar módulos de la misión");
        System.out.println("2. Buscar módulo por ID o nombre");
        System.out.println("3. Catálogo de costos de construcción");
        System.out.println("4. Avanzar un ciclo");
        System.out.println("5. Resumen de comunicaciones");
        System.out.println("0. Salir");
        System.out.print("Seleccione una opción: ");

        if (!scanner.hasNextLine()) {
            return 0;  
        }
        try {
            return Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    public void mostrarMensaje(String msj) {
        System.out.println(msj);
    }

    public void listarModulos(List<Modulo> modulos) {
        System.out.println("Módulos construidos:  " + modulos.size());
        for (Modulo m : modulos) {
            System.out.println(m);
        }
    }

    public void mostrarDetalleModulo(Modulo modulo) {
        System.out.println();
        System.out.println("Detalle del módulo: " + modulo.getNombre());
        System.out.println(modulo);
    }

    public String pedirDatoBusqueda() {
        System.out.print("Ingrese el ID o el nombre del módulo: ");
        return scanner.hasNextLine() ? scanner.nextLine().trim() : "";
    }

   public void mostrarCatalogo(List<Modulo> modulos) {
    System.out.println("Catálogo de costos de construcción: ");
    for (Modulo m : modulos) {
        System.out.println(m.getId() + " " + m.getNombre() + " " + m.getClass().getSimpleName() + " " + m.getCostoConstruccion());
    }
}

    public void mostrarResultadoCiclo(String reporte, Mision mision) {
        System.out.println();
        System.out.println("Resultado del ciclo: ");
        System.out.println(reporte);
        System.out.println("Estado de recursos");
        System.out.println("Energía disponible: " + mision.getEnergiaDisponible());
        System.out.println("Datos en órbita (pendientes): " + mision.getDatosEnOrbita());
        System.out.println("Datos descargados (misión): " + mision.getDatosDescargadosTotales());
    }

    public void mostrarResumenComunicaciones(int totalModulos, int activos, double capTotal,double historico, List<ModuloTierra> tops) {
        System.out.println();
        System.out.println("Resumen comunicaciones");
        System.out.println("Módulos de tierra: " + totalModulos + " (activos: " + activos + ")");
        System.out.println("Capacidad total de descarga por ciclo (activos): " +  capTotal);
        System.out.println("Total histórico descargado por los módulos de tierra: " +  historico);

        if (tops.isEmpty()) {
            System.out.println("No hay módulos de tierra registrados");
            return;
        }
        System.out.println(tops.size() == 1
                ? "Módulo de tierra con mayor descarga histórica:"
                : "Módulos de tierra empatados con mayor descarga histórica:");
        for (ModuloTierra t : tops) {
            System.out.println("  " + t.getNombre() + " ID: " + t.getId() + " Estación: " + t.getEstacion() + "Total descargado: " +  t.getTotalDescargado());
        }
    }
}
