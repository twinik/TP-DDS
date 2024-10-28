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

    public Set<String> buscarPermisosPara(Context ctx) {
        if (ctx.method().name().equals("GET")) {
            return switch (ctx.matchedPath()) {
                //ADMIN
                case "/admin/tecnicos/nuevo" -> Set.of("alta-tecnico");
                case "/admin/visitas-tecnico/nuevo" -> Set.of("alta-tecnico");
                case "/admin/formularios/nuevo" -> Set.of("alta-formulario");
                case "/admin/modelos-heladeras/nuevo" -> Set.of("alta-modelo-heladera");
                case "/admin/tarjeta/nuevo" -> Set.of("alta-cod-tarjeta");
                //COLABORAR
                case "/colaborar/donar-dinero" -> Set.of("donar-dinero");
                case "/colaborar/registrar-persona-vulnerable/registrar-tutorados" -> Set.of("alta-persona-vulnerable");
                case "/colaborar/donar-vianda" -> Set.of("donar-viandas");
                case "/colaborar/distribuir-viandas" -> Set.of("redistribuir-viandas");
                case "/colaborar/colocar-heladera" -> Set.of("colocar-heladeras");
                case "/colaborar/ofrecer-producto" -> Set.of("ofrecer-productos");
                case "/colaborar/registrar-persona-vulnerable" -> Set.of("alta-vulnerable");
                case "/colaborar/registrar-persona-vulnerable/{id}/registrar-tutorados" -> Set.of("alta-vulnerable");
                case "/heladeras/{id}/suscribirse" -> Set.of("colaborador-base");
                case "/heladeras/{id}/reportar-falla-tecnica" -> Set.of("colaborador-base");
                case "/heladeras/mis-heladeras" -> Set.of("colocar-heladeras");
                case "/perfil" -> Set.of("colaborador-base");
                case "/perfil/{id}" -> Set.of("colaborador-base");
                default -> new HashSet<>();
            };
        } else if (ctx.method().name().equals("POST")) {
            return switch (ctx.matchedPath()) {
                case "/admin/tecnicos/nuevo" -> Set.of("alta-tecnico");
                case "/admin/visitas-tecnico/nuevo" -> Set.of("alta-tecnico");
                case "/admin/formularios/nuevo" -> Set.of("alta-formulario");
                case "/admin/modelos-heladeras/nuevo" -> Set.of("alta-modelo-heladera");
                case "/admin/tarjeta/nuevo" -> Set.of("alta-cod-tarjeta");
                case "/colaborar/ofrecer-producto" -> Set.of("ofrecer-productos");
                case "/colaborar/donar-dinero" -> Set.of("donar-dinero");
                case "/colaborar/donar-vianda" -> Set.of("donar-viandas");
                case "/colaborar/distribuir-viandas" -> Set.of("redistribuir-viandas");
                case "/colaborar/colocar-heladera" -> Set.of("colocar-heladeras");
                case "/productos" -> Set.of("ofrecer-productos");
                case "/colaborar/registrar-persona-vulnerable" -> Set.of("alta-vulnerable");
                case "/colaborar/registrar-persona-vulnerable/{id}/registrar-tutorados" -> Set.of("alta-vulnerable");
                case "/heladeras/{id}/suscribirse" -> Set.of("colaborador-base");
                case "/heladeras/{id}/reportar-falla-tecnica" -> Set.of("colaborador-base");
                case "/canjear-producto/{id}" -> Set.of("colaborador-base");
                case "/heladeras/mis-heladeras" -> Set.of("colocar-heladeras");
                case "/heladeras/mis-heladeras/{id}" -> Set.of("colocar-heladeras");
                case "/perfil/{id}" -> Set.of("colaborador-base");
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

    public Set<String> fromFormaColaboracion(FormaColaboracion formaColaboracion) {
        return switch (formaColaboracion.getNombreInterno()) {
            case "DONACION_DINERO" -> Set.of("donar-dinero");
            case "DONACION_VIANDA" -> Set.of("donar-viandas");
            case "REDISTRIBUCION_VIANDA" -> Set.of("redistribuir-viandas");
            case "REGISTRO_PERSONA" -> Set.of("alta-vulnerable");
            case "OFRECER_PRODUCTOS" -> Set.of("ofrecer-productos");
            case "COLOCACION_HELADERA" -> Set.of("colocar-heladeras");
            default -> null;
        };
    }

}
