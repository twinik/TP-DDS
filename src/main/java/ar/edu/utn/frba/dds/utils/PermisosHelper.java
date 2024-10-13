package ar.edu.utn.frba.dds.utils;

import ar.edu.utn.frba.dds.models.domain.colaboradores.FormaColaboracion;
import ar.edu.utn.frba.dds.models.domain.colaboradores.autenticacion.Permiso;
import ar.edu.utn.frba.dds.models.repositories.IPermisosRepository;
import ar.edu.utn.frba.dds.serviceLocator.ServiceLocator;
import io.javalin.http.Context;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class PermisosHelper {

    private static PermisosHelper instancia;
    private final Set<Permiso> permisosDisponibles = new HashSet<>();
    private IPermisosRepository permisosRepository;

    private PermisosHelper() {
        this.permisosRepository = ServiceLocator.get(IPermisosRepository.class);
        permisosDisponibles.addAll(this.permisosRepository.buscarTodos());
    }

    public static PermisosHelper getInstance() {
        if (instancia == null) {
            instancia = new PermisosHelper();
        }
        return instancia;
    }

    public Set<Permiso> buscarPermisosPara(Context ctx) {
        if (ctx.method().name().equals("GET")) {
            return switch (ctx.matchedPath()) {
                //ADMIN
                case "/admin/tecnicos/nuevo" -> this.buscarPorNombres("alta-tecnico");
                case "/admin/visitas-tecnico/nuevo" -> this.buscarPorNombres("alta-tecnico");
                case "/admin/formularios/nuevo" -> this.buscarPorNombres("alta-formulario");
                case "/admin/modelos-heladeras/nuevo" -> this.buscarPorNombres("alta-modelo-heladera");
                case "/admin/tarjeta/nuevo" -> this.buscarPorNombres("alta-cod-tarjeta");
                //COLABORAR
                case "/colaborar/donar-dinero" -> this.buscarPorNombres("donar-dinero");
                case "/colaborar/registrar-persona-vulnerable/registrar-tutorados" ->
                        this.buscarPorNombres("alta-persona-vulnerable");
                case "/colaborar/donar-vianda" -> this.buscarPorNombres("donar-viandas");
                case "/colaborar/distribuir-viandas" -> this.buscarPorNombres("redistribuir-viandas");
                case "/colaborar/colocar-heladera" -> this.buscarPorNombres("colocar-heladeras");
                case "/colaborar/ofrecer-producto" -> this.buscarPorNombres("ofrecer-productos");
                case "/colaborar/registrar-persona-vulnerable" -> this.buscarPorNombres("alta-vulnerable");
                case "/colaborar/registrar-persona-vulnerable/{id}/registrar-tutorados" ->
                        this.buscarPorNombres("alta-vulnerable");
                case "/heladeras/{id}/suscribirse" -> this.buscarPorNombres("colaborador-base");
                case "/heladeras/{id}/reportar-falla-tecnica" -> this.buscarPorNombres("colaborador-base");
                default -> new HashSet<>();
            };
        } else if (ctx.method().name().equals("POST")) {
            return switch (ctx.matchedPath()) {
                case "/admin/tecnicos/nuevo" -> this.buscarPorNombres("alta-tecnico");
                case "/admin/visitas-tecnico/nuevo" -> this.buscarPorNombres("alta-tecnico");
                case "/admin/formularios/nuevo" -> this.buscarPorNombres("alta-formulario");
                case "/admin/modelos-heladeras/nuevo" -> this.buscarPorNombres("alta-modelo-heladera");
                case "/admin/tarjeta/nuevo" -> this.buscarPorNombres("alta-cod-tarjeta");
                case "/colaborar/ofrecer-producto" -> this.buscarPorNombres("ofrecer-productos");
                case "/colaborar/donar-dinero" -> this.buscarPorNombres("donar-dinero");
                case "/colaborar/donar-vianda" -> this.buscarPorNombres("donar-viandas");
                case "/colaborar/distribuir-viandas" -> this.buscarPorNombres("redistribuir-viandas");
                case "/colaborar/colocar-heladera" -> this.buscarPorNombres("colocar-heladeras");
                case "/productos" -> this.buscarPorNombres("ofrecer-productos");
                case "/colaborar/registrar-persona-vulnerable" -> this.buscarPorNombres("alta-vulnerable");
                case "/colaborar/registrar-persona-vulnerable/{id}/registrar-tutorados" ->
                        this.buscarPorNombres("alta-vulnerable");
                case "/heladeras/{id}/suscribirse" -> this.buscarPorNombres("colaborador-base");
                case "/heladeras/{id}/reportar-falla-tecnica" -> this.buscarPorNombres("colaborador-base");
                case  "/canjear-producto/{id}" -> this.buscarPorNombres("colaborador-base");
                default -> new HashSet<>();
            };
        }
        return new HashSet<>();

    }

    public void agregarPermiso(Permiso p) {
        this.permisosRepository.guardar(p);
        this.permisosDisponibles.add(p);
    }

    public Set<Permiso> buscarPorNombres(String... nombres) {
        return this.permisosDisponibles.stream().filter(p ->
                Arrays.stream(nombres).anyMatch(nombre -> p.getDesc_interna().equalsIgnoreCase(nombre))
        ).collect(Collectors.toSet());
    }

    public Set<Permiso> fromFormaColaboracion(FormaColaboracion formaColaboracion) {
        return switch (formaColaboracion.getNombreInterno()) {
            case "DONACION_DINERO" -> this.buscarPorNombres("donar-dinero");
            case "DONACION_VIANDA" -> this.buscarPorNombres("donar-viandas");
            case "REDISTRIBUCION_VIANDA" -> this.buscarPorNombres("redistribuir-viandas");
            case "REGISTRO_PERSONA" -> this.buscarPorNombres("alta-vulnerable");
            case "OFRECER_PRODUCTOS" -> this.buscarPorNombres("ofrecer-productos");
            case "COLOCACION_HELADERA" -> this.buscarPorNombres("colocar-heladeras");
            default -> null;
        };
    }

}
