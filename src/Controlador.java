import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;

public class Controlador{
    private final Mision mision;
    private final Vista vista;

    public Controlador(Mision mision, Vista vista) {
        this.mision = mision;
        this.vista = vista;
    }

    public void iniciar() {
        cargarDatosIniciales();
        int opcion;
        do {
            opcion = vista.mostrarMenu();
            switch (opcion) {
                case 1:
                    listarModulos();
                    break;
                case 2:
                    buscarModulo();
                    break;
                case 3:
                    consultarCatalogo();
                    break;
                case 4:
                    avanzarCiclo();
                    break;
                case 5:
                    consultarResumenComunicaciones();
                    break;
                case 0:
                    vista.mostrarMensaje("Fin de la simulación");
                    break;
                default:
                    vista.mostrarMensaje("Opción inválida");
            }
        } while (opcion != 0);
    }

    private void cargarDatosIniciales() {
        mision.modificarEnergia(20.0);
        mision.modificarDatosEnOrbita(60.0);

        mision.agregarModulo(new ModuloEnergia("EN-01", "Panel Solar 1", 95, true, 1500, 10.0, 0));
        mision.agregarModulo(new ModuloVuelo("VU-01", "Cámara Óptica", 90, true, 3200,TipoInstrumento.CAMARA, 25.0, 6.0, 0));
        mision.agregarModulo(new ModuloTierra("TI-01", "Antena Quetzal", 88, true, 2800,"Ciudad de Guatemala", 30.0, 8.0, 420.0));
        mision.agregarModulo(new ModuloVuelo("VU-02", "Sensor", 97, true, 1800,TipoInstrumento.SENSOR, 12.0, 4.0, 0));
        mision.agregarModulo(new ModuloEnergia("EN-02", "Panel Solar 2", 80, true, 1300, 8.0, 0));
        mision.agregarModulo(new ModuloTierra("TI-02", "Antena Tikal", 92, true, 2200,"Petén", 20.0, 5.0, 260.0));
        mision.agregarModulo(new ModuloVuelo("VU-03", "Cámara Infrarroja", 60, false, 3500,TipoInstrumento.CAMARA, 18.0, 5.0, 0));
        mision.agregarModulo(new ModuloEnergia("EN-03", "Panel Solar 3", 55, false, 1700, 12.0, 0));
        mision.agregarModulo(new ModuloTierra("TI-03", "Antena Atitlán", 70, false, 3000,"Sololá", 40.0, 10.0, 80.0));
        mision.agregarModulo(new ModuloVuelo("VU-04", "Sensor", 85, true, 2100,TipoInstrumento.SENSOR, 15.0, 3.0, 0));
        mision.agregarModulo(new ModuloTierra("TI-04", "Antena Tecpán", 91, true, 2500,"Chimaltenango", 25.0, 6.0, 300.0));
        mision.agregarModulo(new ModuloEnergia("EN-04", "Panel Reserva", 99, true, 900, 6.0, 20));

        double historico = 0;
        for (Modulo m : mision.getModulos()) {
            if (m instanceof ModuloTierra) {
                historico += ((ModuloTierra) m).getTotalDescargado();
            }
        }
        mision.registrarDescargaTotal(historico);
    }

    private void listarModulos() {
        vista.listarModulos(mision.getModulos());
    }

    private void buscarModulo() {
        String criterio = vista.pedirDatoBusqueda();
        if (criterio == null || criterio.isBlank()) {
            vista.mostrarMensaje("Debe ingresar un ID o un nombre");
            return;
        }

        String buscado = normalizar(criterio);
        List<Modulo> encontrados = new ArrayList<>();
        for (Modulo m : mision.getModulos()) {
            if (normalizar(m.getId()).equals(buscado) || normalizar(m.getNombre()).contains(buscado)) {
                encontrados.add(m);
            }
        }

        if (encontrados.isEmpty()) {
            vista.mostrarMensaje("No se encontró ningún módulo con: " + criterio);
        } else {
            for (Modulo m : encontrados) {
                vista.mostrarDetalleModulo(m);
            }
        }
    }

    private void consultarCatalogo() {
        List<Modulo> catalogo = new ArrayList<>(mision.getModulos());
        vista.mostrarCatalogo(catalogo);
    }

    private void avanzarCiclo() {
        StringBuilder reporte = new StringBuilder();
        for (Modulo m : mision.getModulos()) {
            reporte.append(m.procesarCiclo(mision));
        }
        vista.mostrarResultadoCiclo(reporte.toString(), mision);
    }

    private void consultarResumenComunicaciones() {
        int total = 0;
        int activos = 0;
        double capacidadTotal = 0;
        double historico = 0;
        double maximo = 0;
        List<ModuloTierra> tierra = new ArrayList<>();
        for (Modulo m : mision.getModulos()) {
            if (m instanceof ModuloTierra) {
                ModuloTierra t = (ModuloTierra) m;
                tierra.add(t);
                total++;
                if (t.isActivo()) {
                    activos++;
                    capacidadTotal += t.getCapacidadDescarga();
                }
                historico += t.getTotalDescargado();
                maximo = Math.max(maximo, t.getTotalDescargado());
            }
        }
        List<ModuloTierra> tops = new ArrayList<>();
        for (ModuloTierra t : tierra) {
            if (Double.compare(t.getTotalDescargado(), maximo) == 0) {
                tops.add(t);
            }
        }
        vista.mostrarResumenComunicaciones(total, activos, capacidadTotal, historico, tops);
    }

    private String normalizar(String texto) {
        return Normalizer.normalize(texto, Normalizer.Form.NFD)
                .replaceAll("\\p{M}", "").toLowerCase().trim();
    }
}
