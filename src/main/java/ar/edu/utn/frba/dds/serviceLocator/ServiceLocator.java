package ar.edu.utn.frba.dds.serviceLocator;

import ar.edu.utn.frba.dds.domain.colaboraciones.calculadores.CalculadorPuntos;
import ar.edu.utn.frba.dds.domain.heladeras.RecomendadorHeladeras;
import ar.edu.utn.frba.dds.domain.reportes.ReportesFactory;
import ar.edu.utn.frba.dds.repositories.IAlertasRepository;
import ar.edu.utn.frba.dds.repositories.IDonacionesViandaRepository;
import ar.edu.utn.frba.dds.repositories.IFallasTecnicasRepository;
import ar.edu.utn.frba.dds.repositories.IRedistribucionesViandaRepository;
import ar.edu.utn.frba.dds.repositories.IViandasRepository;
import ar.edu.utn.frba.dds.repositories.imp.*;
import net.bytebuddy.implementation.bytecode.Throw;
import java.util.HashMap;
import java.util.Map;

/**
 * ServiceLocator utilizado para obtener repositorios
 */
public class ServiceLocator {

    private static final Map<String, Object> services = new HashMap<>();

    public static void add(String nombre, Object service) {
        services.put(nombre, service);
    }

    public static Object get(String nombre) {
        if (!existeService(nombre)) {
            switch (nombre) {
                case "alertasRepository" -> add("alertasRepository", new AlertasRepository());
                case "altaPersonaVulnerableRepository" ->
                        add("altaPersonaVulnerableRepository", new AltaPersonaVulnerableRepository());
                case "aperturasHeladeraRepository" ->
                        add("aperturasHeladeraRepository", new AperturasHeladeraRepository());
                case "calculadorPuntos" -> add("calculadorPuntos", new CalculadorPuntos());
                case "campoRepository" -> add("campoRepository", new CampoRepository());
                case "canjesRepository" -> add("canjesRepository", new CanjeProductoRepository());
                case "colaboradoresRepository" -> add("colaboradoresRepository", new ColaboradoresRepository());
                case "colocacionHeladeraRepository" ->
                        add("colocacionHeladeraRepository", new ColocacionHeladeraRepository());
                case "donacionDineroRepository" -> add("donacionDineroRepository", new DonacionDineroRepository());
                case "donacionesViandaRepository" ->
                        add("donacionesViandaRepository", new DonacionesVIandaRepository());
                case "fallasTecnicasRepository" -> add("fallasTecnicasRepository", new FallasTecnicasRepository());
                case "formasColaboracionRepository" ->
                        add("formasColaboracionRepository", new FormasColaboracionRespository());
                case "formulariosRepository" -> add("formulariosRepository", new FormularioRepository());
                case "heladerasRepository" -> add("heladerasRepository", new HeladeraRepository());
                case "mediosDeContactoRepository" -> add("mediosDeContactoRepository", new MedioContactoRepository());
                case "modelosHeladeraRepository" -> add("modelosHeladeraRepository", new ModeloHeladeraRepository());
                case "motivosRedistribucionRepository" ->
                        add("motivosRedistribucionRepository", new MotivoRedistribucionRepository());
                case "ofertasProductoRepository" -> add("ofertasProductoRepository", new OfertaProductoRepository());
                case "opcionesRepository" -> add("opcionesRepository", new OpcionRepository());
                case "personasVulnerablesRepository" ->
                        add("personasVulnerablesRepository", new PersonaVulnerableRepository());
                case "productosRepository" -> add("productosRepository", new ProductoRepository());
                case "recomendadorHeladeras" -> add("recomendadorHeladeras", new RecomendadorHeladeras());
                case "redistribucionesViandaRepository" ->
                        add("redistribucionesViandaRepository", new RedistribucionesViandaRepository());
                case "registrosTemperaturaRepository" ->
                        add("registrosTemperaturaRepository", new RegistrosTemperaturaRepository());
                case "reportesFactory" ->
                        add("reportesFactory", new ReportesFactory(get("viandasRepository", IViandasRepository.class),
                                get("donacionesViandaRepository", IDonacionesViandaRepository.class),
                                get("redistribucionesViandaRepository", IRedistribucionesViandaRepository.class),
                                get("fallasTecnicasRepository", IFallasTecnicasRepository.class),
                                get("alertasRepository", IAlertasRepository.class)));
                case "reportesRepository" -> add("reportesRepository", new ReportesRepository());
                case "respuestasCampoRepository" -> add("respuestasCampoRepository", new RespuestasCampoRepository());
                case "respuestasFormularioRepository" ->
                        add("respuestasFormularioRepository", new RespuestasFormularioRepository());
                case "sensoresMovimientoRepository" ->
                        add("sensoresMovimientoRepository", new SensoresMovimientoRepository());
                case "sensoresTemperaturaRepository" ->
                        add("sensoresTemperaturaRepository", new SensoresTemperaturaRepository());
                case "solicitudesAperturaHeladeraRepository" ->
                        add("solicitudesAperturaHeladeraRepository", new SolcitudesAperturaHeladeraRepository());
                case "suscripcionesRepository" -> add("suscripcionesRepository", new SuscripcionesRepository());
                case "tarjetasColaboradorRepository" ->
                        add("tarjetasColaboradorRepository", new TarjetasColaboradorRepository());
                case "tarjetasRepository" -> add("tarjetasRepository", new TarjetaRepository());
                case "tecnicosRepository" -> add("tecnicosRepository", new TecnicosRepository());
                case "usosTarjetaRepository" -> add("usosTarjetaRepository", new UsosTarjetaRepository());
                case "usuariosRepository" -> add("usuariosRepository", new UsuariosRepository());
                case "viandasRepository" -> add("viandasRepository", new ViandasRepository());
                case "visitasTecnicoRepository" -> add("visitasTecnicoRepository", new VisitasTecnicoRepository());
                default -> throw new IllegalArgumentException("nombre invalido");

            }
        }
        return services.get(nombre);
    }

    @SuppressWarnings("unchecked")
    public static <T> T get(String nombre, Class<T> tipo) {
        Object service = get(nombre);
        if (tipo.isInstance(service)) {
            return (T) service;
        }
        throw new IllegalArgumentException("tipo de clase invalido");
    }


    private static boolean existeService(String nombre) {
        return services.containsKey(nombre);
    }
}
