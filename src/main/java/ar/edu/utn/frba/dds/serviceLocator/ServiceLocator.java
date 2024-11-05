package ar.edu.utn.frba.dds.serviceLocator;

import ar.edu.utn.frba.dds.controllers.AlertasController;
import ar.edu.utn.frba.dds.controllers.AltaPersonaVulnerableController;
import ar.edu.utn.frba.dds.controllers.CanjeProductoController;
import ar.edu.utn.frba.dds.controllers.CargaMasivaController;
import ar.edu.utn.frba.dds.controllers.ColocacionHeladerasController;
import ar.edu.utn.frba.dds.controllers.DonacionDineroController;
import ar.edu.utn.frba.dds.controllers.FallasTecnicasController;
import ar.edu.utn.frba.dds.controllers.FormulariosController;
import ar.edu.utn.frba.dds.controllers.HeladerasController;
import ar.edu.utn.frba.dds.controllers.LoginController;
import ar.edu.utn.frba.dds.controllers.LogoutController;
import ar.edu.utn.frba.dds.controllers.ModelosHeladeraController;
import ar.edu.utn.frba.dds.controllers.OfertasProductoController;
import ar.edu.utn.frba.dds.controllers.PerfilController;
import ar.edu.utn.frba.dds.controllers.PosiblesCodigosTarjetasController;
import ar.edu.utn.frba.dds.controllers.RecomendacionesController;
import ar.edu.utn.frba.dds.controllers.RedistribucionViandaController;
import ar.edu.utn.frba.dds.controllers.RegistroController;
import ar.edu.utn.frba.dds.controllers.ReportesController;
import ar.edu.utn.frba.dds.controllers.RespuestaFormularioController;
import ar.edu.utn.frba.dds.controllers.SuscripcionesController;
import ar.edu.utn.frba.dds.controllers.TecnicosController;
import ar.edu.utn.frba.dds.controllers.ViandasController;
import ar.edu.utn.frba.dds.controllers.VisitasTecnicoController;
import ar.edu.utn.frba.dds.externapi.RecomendacionDonaciones;
import ar.edu.utn.frba.dds.externapi.RecomendadorDonacionesRetrofitAdapter;
import ar.edu.utn.frba.dds.helpers.ConfigReader;
import ar.edu.utn.frba.dds.helpers.TecnicosHelper;
import ar.edu.utn.frba.dds.logs.DDMetricsUtils;
import ar.edu.utn.frba.dds.models.domain.colaboraciones.calculadores.CalculadorPuntos;
import ar.edu.utn.frba.dds.models.domain.colaboraciones.calculadores.ICalculadorPuntos;
import ar.edu.utn.frba.dds.models.domain.colaboraciones.cargaMasiva.CargaColaboracionCsvReader;
import ar.edu.utn.frba.dds.models.domain.emailSending.SendGridMailSender;
import ar.edu.utn.frba.dds.models.domain.heladeras.CalculadorHeladerasCercanas;
import ar.edu.utn.frba.dds.models.domain.heladeras.RecomendadorHeladeras;
import ar.edu.utn.frba.dds.models.domain.recomendadorPuntosColocacion.RecomendadorDePuntosDeColocacion;
import ar.edu.utn.frba.dds.models.domain.recomendadorPuntosColocacion.RecomendadorRetrofitAdapter;
import ar.edu.utn.frba.dds.models.domain.reportes.ReportesFactory;
import ar.edu.utn.frba.dds.models.domain.telegramSender.BotTelegramSender;
import ar.edu.utn.frba.dds.models.domain.utils.TipoDocumentoMapper;
import ar.edu.utn.frba.dds.models.domain.whatsappSender.TwilioWhatsappSender;
import ar.edu.utn.frba.dds.models.repositories.IAlertasRepository;
import ar.edu.utn.frba.dds.models.repositories.IAltaPersonaVulnerableRepository;
import ar.edu.utn.frba.dds.models.repositories.IAperturasHeladeraRepository;
import ar.edu.utn.frba.dds.models.repositories.ICampoRepository;
import ar.edu.utn.frba.dds.models.repositories.ICanjeProductoRepository;
import ar.edu.utn.frba.dds.models.repositories.IColaboradoresRepository;
import ar.edu.utn.frba.dds.models.repositories.IColocacionHeladeraRepository;
import ar.edu.utn.frba.dds.models.repositories.IDonacionDineroRepository;
import ar.edu.utn.frba.dds.models.repositories.IDonacionesViandaRepository;
import ar.edu.utn.frba.dds.models.repositories.IFallasTecnicasRepository;
import ar.edu.utn.frba.dds.models.repositories.IFormasColaboracionRespository;
import ar.edu.utn.frba.dds.models.repositories.IFormularioRepository;
import ar.edu.utn.frba.dds.models.repositories.IHeladerasRepository;
import ar.edu.utn.frba.dds.models.repositories.IIncidentesRepository;
import ar.edu.utn.frba.dds.models.repositories.IMedioContactoRepository;
import ar.edu.utn.frba.dds.models.repositories.IModeloHeladeraRepository;
import ar.edu.utn.frba.dds.models.repositories.IMotivoRedistribucionRepository;
import ar.edu.utn.frba.dds.models.repositories.IOfertaProductoRepository;
import ar.edu.utn.frba.dds.models.repositories.IOpcionRepository;
import ar.edu.utn.frba.dds.models.repositories.IPermisosRepository;
import ar.edu.utn.frba.dds.models.repositories.IPersonaVulnerableRepository;
import ar.edu.utn.frba.dds.models.repositories.IPosiblesCodigosTarjetaRepository;
import ar.edu.utn.frba.dds.models.repositories.IProductoRepository;
import ar.edu.utn.frba.dds.models.repositories.IRedistribucionesViandaRepository;
import ar.edu.utn.frba.dds.models.repositories.IRegistrosTemperaturaRepository;
import ar.edu.utn.frba.dds.models.repositories.IReportesRepository;
import ar.edu.utn.frba.dds.models.repositories.IRespuestasCampoRepository;
import ar.edu.utn.frba.dds.models.repositories.IRespuestasFormularioRepository;
import ar.edu.utn.frba.dds.models.repositories.IRolesRepository;
import ar.edu.utn.frba.dds.models.repositories.ISensorMovimientoRepository;
import ar.edu.utn.frba.dds.models.repositories.ISensorTemperaturaRepository;
import ar.edu.utn.frba.dds.models.repositories.ISolicitudesAperturaHeladeraRepository;
import ar.edu.utn.frba.dds.models.repositories.ISuscripcionesRepository;
import ar.edu.utn.frba.dds.models.repositories.ITarjetasColaboradorRepository;
import ar.edu.utn.frba.dds.models.repositories.ITarjetasRepository;
import ar.edu.utn.frba.dds.models.repositories.ITecnicosRepository;
import ar.edu.utn.frba.dds.models.repositories.IUsosTarjetaRepository;
import ar.edu.utn.frba.dds.models.repositories.IUsuariosRepository;
import ar.edu.utn.frba.dds.models.repositories.IViandasRepository;
import ar.edu.utn.frba.dds.models.repositories.IVisitasTecnicoRepository;
import ar.edu.utn.frba.dds.models.repositories.imp.AlertasRepository;
import ar.edu.utn.frba.dds.models.repositories.imp.AltaPersonaVulnerableRepository;
import ar.edu.utn.frba.dds.models.repositories.imp.AperturasHeladeraRepository;
import ar.edu.utn.frba.dds.models.repositories.imp.CampoRepository;
import ar.edu.utn.frba.dds.models.repositories.imp.CanjeProductoRepository;
import ar.edu.utn.frba.dds.models.repositories.imp.ColaboradoresRepository;
import ar.edu.utn.frba.dds.models.repositories.imp.ColocacionHeladeraRepository;
import ar.edu.utn.frba.dds.models.repositories.imp.DonacionDineroRepository;
import ar.edu.utn.frba.dds.models.repositories.imp.DonacionesViandaRepository;
import ar.edu.utn.frba.dds.models.repositories.imp.FallasTecnicasRepository;
import ar.edu.utn.frba.dds.models.repositories.imp.FormasColaboracionRespository;
import ar.edu.utn.frba.dds.models.repositories.imp.FormularioRepository;
import ar.edu.utn.frba.dds.models.repositories.imp.HeladeraRepository;
import ar.edu.utn.frba.dds.models.repositories.imp.IncidentesRepository;
import ar.edu.utn.frba.dds.models.repositories.imp.MedioContactoRepository;
import ar.edu.utn.frba.dds.models.repositories.imp.ModeloHeladeraRepository;
import ar.edu.utn.frba.dds.models.repositories.imp.MotivoRedistribucionRepository;
import ar.edu.utn.frba.dds.models.repositories.imp.OfertaProductoRepository;
import ar.edu.utn.frba.dds.models.repositories.imp.OpcionRepository;
import ar.edu.utn.frba.dds.models.repositories.imp.PermisosRepository;
import ar.edu.utn.frba.dds.models.repositories.imp.PersonaVulnerableRepository;
import ar.edu.utn.frba.dds.models.repositories.imp.PosiblesCodigosTarjetaRepository;
import ar.edu.utn.frba.dds.models.repositories.imp.ProductoRepository;
import ar.edu.utn.frba.dds.models.repositories.imp.RedistribucionesViandaRepository;
import ar.edu.utn.frba.dds.models.repositories.imp.RegistrosTemperaturaRepository;
import ar.edu.utn.frba.dds.models.repositories.imp.ReportesRepository;
import ar.edu.utn.frba.dds.models.repositories.imp.RespuestasCampoRepository;
import ar.edu.utn.frba.dds.models.repositories.imp.RespuestasFormularioRepository;
import ar.edu.utn.frba.dds.models.repositories.imp.RolesRepository;
import ar.edu.utn.frba.dds.models.repositories.imp.SensoresMovimientoRepository;
import ar.edu.utn.frba.dds.models.repositories.imp.SensoresTemperaturaRepository;
import ar.edu.utn.frba.dds.models.repositories.imp.SolicitudesAperturaHeladeraRepository;
import ar.edu.utn.frba.dds.models.repositories.imp.SuscripcionesRepository;
import ar.edu.utn.frba.dds.models.repositories.imp.TarjetaRepository;
import ar.edu.utn.frba.dds.models.repositories.imp.TarjetasColaboradorRepository;
import ar.edu.utn.frba.dds.models.repositories.imp.TecnicosRepository;
import ar.edu.utn.frba.dds.models.repositories.imp.UsosTarjetaRepository;
import ar.edu.utn.frba.dds.models.repositories.imp.UsuariosRepository;
import ar.edu.utn.frba.dds.models.repositories.imp.ViandasRepository;
import ar.edu.utn.frba.dds.models.repositories.imp.VisitasTecnicoRepository;
import ar.edu.utn.frba.dds.services.AlertasService;
import ar.edu.utn.frba.dds.services.AltaPersonaVulnerableService;
import ar.edu.utn.frba.dds.services.CargaMasivaService;
import ar.edu.utn.frba.dds.services.ColaboradoresService;
import ar.edu.utn.frba.dds.services.ColocacionHeladerasService;
import ar.edu.utn.frba.dds.services.DonacionDineroService;
import ar.edu.utn.frba.dds.services.DonacionesViandaService;
import ar.edu.utn.frba.dds.services.FallasTecnicasService;
import ar.edu.utn.frba.dds.services.FileUploadService;
import ar.edu.utn.frba.dds.services.FormaColaboracionService;
import ar.edu.utn.frba.dds.services.FormulariosService;
import ar.edu.utn.frba.dds.services.HeladerasService;
import ar.edu.utn.frba.dds.services.IncidentesService;
import ar.edu.utn.frba.dds.services.MedioContactoService;
import ar.edu.utn.frba.dds.services.ModelosService;
import ar.edu.utn.frba.dds.services.MotivoRedistribucionService;
import ar.edu.utn.frba.dds.services.OfertasProductoService;
import ar.edu.utn.frba.dds.services.PosiblesCodigosService;
import ar.edu.utn.frba.dds.services.RedistribucionViandaService;
import ar.edu.utn.frba.dds.services.ReportesService;
import ar.edu.utn.frba.dds.services.RespuestaCampoService;
import ar.edu.utn.frba.dds.services.RespuestaFormularioService;
import ar.edu.utn.frba.dds.services.RolesService;
import ar.edu.utn.frba.dds.services.SolicitudAperturaHeladeraService;
import ar.edu.utn.frba.dds.services.SuscripcionesService;
import ar.edu.utn.frba.dds.services.TarjetasService;
import ar.edu.utn.frba.dds.services.TecnicosService;
import ar.edu.utn.frba.dds.services.UsuarioService;
import ar.edu.utn.frba.dds.services.ViandasService;
import io.micrometer.core.instrument.step.StepMeterRegistry;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * ServiceLocator utilizado para obtener repositorios
 */
public class ServiceLocator {

  private static final Map<Class<?>, Object> services = new HashMap<>();

  public static void add(Class<?> clase, Object service) {
    services.put(clase, service);
  }

  @SuppressWarnings("unchecked")
  public static <T> T get(Class<T> clase) {
    if (!existeService(clase)) {

      // REPOSITORIOS
      if (clase.equals(IAlertasRepository.class))
        add(clase, new AlertasRepository());
      else if (clase.equals(IAltaPersonaVulnerableRepository.class))
        add(clase, new AltaPersonaVulnerableRepository());
      else if (clase.equals(IAperturasHeladeraRepository.class))
        add(clase, new AperturasHeladeraRepository());
      else if (clase.equals(ICampoRepository.class))
        add(clase, new CampoRepository());
      else if (clase.equals(ICanjeProductoRepository.class))
        add(clase, new CanjeProductoRepository());
      else if (clase.equals(IColaboradoresRepository.class))
        add(clase, new ColaboradoresRepository());
      else if (clase.equals(IColocacionHeladeraRepository.class))
        add(clase, new ColocacionHeladeraRepository());
      else if (clase.equals(IDonacionDineroRepository.class))
        add(clase, new DonacionDineroRepository());
      else if (clase.equals(IDonacionesViandaRepository.class))
        add(clase, new DonacionesViandaRepository());
      else if (clase.equals(IFallasTecnicasRepository.class))
        add(clase, new FallasTecnicasRepository());
      else if (clase.equals(IFormasColaboracionRespository.class))
        add(clase, new FormasColaboracionRespository());
      else if (clase.equals(IFormularioRepository.class))
        add(clase, new FormularioRepository());
      else if (clase.equals(IIncidentesRepository.class))
        add(clase, new IncidentesRepository());
      else if (clase.equals(IHeladerasRepository.class))
        add(clase, new HeladeraRepository());
      else if (clase.equals(IMedioContactoRepository.class))
        add(clase, new MedioContactoRepository());
      else if (clase.equals(IModeloHeladeraRepository.class))
        add(clase, new ModeloHeladeraRepository());
      else if (clase.equals(IMotivoRedistribucionRepository.class))
        add(clase, new MotivoRedistribucionRepository());
      else if (clase.equals(IOfertaProductoRepository.class))
        add(clase, new OfertaProductoRepository());
      else if (clase.equals(IOpcionRepository.class))
        add(clase, new OpcionRepository());
      else if (clase.equals(IPermisosRepository.class))
        add(clase, new PermisosRepository());
      else if (clase.equals(IPersonaVulnerableRepository.class))
        add(clase, new PersonaVulnerableRepository());
      else if (clase.equals(IPosiblesCodigosTarjetaRepository.class))
        add(clase, new PosiblesCodigosTarjetaRepository());
      else if (clase.equals(IProductoRepository.class))
        add(clase, new ProductoRepository());
      else if (clase.equals(IRedistribucionesViandaRepository.class))
        add(clase, new RedistribucionesViandaRepository());
      else if (clase.equals(IRegistrosTemperaturaRepository.class))
        add(clase, new RegistrosTemperaturaRepository());
      else if (clase.equals(IReportesRepository.class))
        add(clase, new ReportesRepository());
      else if (clase.equals(IRespuestasCampoRepository.class))
        add(clase, new RespuestasCampoRepository());
      else if (clase.equals(IRespuestasFormularioRepository.class))
        add(clase, new RespuestasFormularioRepository());
      else if (clase.equals(IRolesRepository.class))
        add(clase, new RolesRepository());
      else if (clase.equals(ISensorMovimientoRepository.class))
        add(clase, new SensoresMovimientoRepository());
      else if (clase.equals(ISensorTemperaturaRepository.class))
        add(clase, new SensoresTemperaturaRepository());
      else if (clase.equals(ISolicitudesAperturaHeladeraRepository.class))
        add(clase, new SolicitudesAperturaHeladeraRepository());
      else if (clase.equals(ISuscripcionesRepository.class))
        add(clase, new SuscripcionesRepository());
      else if (clase.equals(ITarjetasColaboradorRepository.class))
        add(clase, new TarjetasColaboradorRepository());
      else if (clase.equals(ITarjetasRepository.class))
        add(clase, new TarjetaRepository());
      else if (clase.equals(ITecnicosRepository.class))
        add(clase, new TecnicosRepository());
      else if (clase.equals(IUsosTarjetaRepository.class))
        add(clase, new UsosTarjetaRepository());
      else if (clase.equals(IUsuariosRepository.class))
        add(clase, new UsuariosRepository());
      else if (clase.equals(IViandasRepository.class))
        add(clase, new ViandasRepository());
      else if (clase.equals(IVisitasTecnicoRepository.class))
        add(clase, new VisitasTecnicoRepository());

        // SERVICIOS
      else if (clase.equals(AlertasService.class))
        add(clase, new AlertasService(get(IAlertasRepository.class)));
      else if (clase.equals(AltaPersonaVulnerableService.class))
        add(clase, new AltaPersonaVulnerableService(get(IPersonaVulnerableRepository.class), get(IAltaPersonaVulnerableRepository.class), get(ColaboradoresService.class), get(ICalculadorPuntos.class), get(TarjetasService.class)));
      else if (clase.equals(CalculadorHeladerasCercanas.class)) {
        try {
          add(clase, new CalculadorHeladerasCercanas(get(IHeladerasRepository.class), Integer.parseInt(new ConfigReader("config.properties").getProperty("LIMITE_HELADERAS_CERCANAS"))));
        } catch (IOException e) {
          throw new RuntimeException(e);
        }
      } else if (clase.equals(ICalculadorPuntos.class))
        add(clase, new CalculadorPuntos());
      else if (clase.equals(CargaMasivaService.class))
        add(clase, new CargaMasivaService(
            get(FileUploadService.class),
            get(CargaColaboracionCsvReader.class),
            get(SendGridMailSender.class),
            get(IColaboradoresRepository.class),
            get(IFormasColaboracionRespository.class),
            get(ICalculadorPuntos.class),
            get(IDonacionesViandaRepository.class),
            get(IDonacionDineroRepository.class),
            get(IColocacionHeladeraRepository.class),
            get(IRedistribucionesViandaRepository.class),
            get(IAltaPersonaVulnerableRepository.class),
            get(IHeladerasRepository.class)
            , get(IRolesRepository.class)));
      else if (clase.equals(ColaboradoresService.class))
        add(clase, new ColaboradoresService(get(IColaboradoresRepository.class), get(MedioContactoService.class), get(FormaColaboracionService.class), get(RolesService.class), get(TarjetasService.class), get(IUsuariosRepository.class)));
      else if (clase.equals(ColocacionHeladerasService.class))
        add(clase, new ColocacionHeladerasService(get(IColocacionHeladeraRepository.class), get(ColaboradoresService.class), get(ModelosService.class), get(CalculadorHeladerasCercanas.class), get(IHeladerasRepository.class), get(ISensorMovimientoRepository.class), get(ISensorTemperaturaRepository.class)));
      else if (clase.equals(DonacionDineroService.class))
        add(clase, new DonacionDineroService(get(IDonacionDineroRepository.class), get(ColaboradoresService.class), get(ICalculadorPuntos.class)));
      else if (clase.equals(DonacionesViandaService.class))
        add(clase, new DonacionesViandaService(get(IDonacionesViandaRepository.class), get(ICalculadorPuntos.class)));
      else if (clase.equals(FallasTecnicasService.class))
        add(clase, new FallasTecnicasService(get(IFallasTecnicasRepository.class), get(ColaboradoresService.class), get(HeladerasService.class)));
      else if (clase.equals(FileUploadService.class))
        add(clase, new FileUploadService());
      else if (clase.equals(FormaColaboracionService.class))
        add(clase, new FormaColaboracionService(get(IFormasColaboracionRespository.class)));
      else if (clase.equals(FormulariosService.class))
        add(clase, new FormulariosService(get(IFormularioRepository.class), get(UsuarioService.class)));
      else if (clase.equals(HeladerasService.class))
        add(clase, new HeladerasService(get(IHeladerasRepository.class)));
      else if (clase.equals(IncidentesService.class))
        add(clase, new IncidentesService(get(IIncidentesRepository.class), get(HeladerasService.class)));
      else if (clase.equals(MedioContactoService.class))
        add(clase, new MedioContactoService());
      else if (clase.equals(ModelosService.class))
        add(clase, new ModelosService(get(IModeloHeladeraRepository.class)));
      else if (clase.equals(MotivoRedistribucionService.class))
        add(clase, new MotivoRedistribucionService(get(IMotivoRedistribucionRepository.class)));
      else if (clase.equals(OfertasProductoService.class))
        add(clase, new OfertasProductoService(get(IOfertaProductoRepository.class), get(ICanjeProductoRepository.class), get(ColaboradoresService.class)));
      else if (clase.equals(PosiblesCodigosService.class)) {
        add(clase, new PosiblesCodigosService(get(IPosiblesCodigosTarjetaRepository.class)));
      } else if (clase.equals(RecomendadorHeladeras.class))
        add(clase, new RecomendadorHeladeras());
      else if (clase.equals(RecomendadorDePuntosDeColocacion.class)) {
        try {
          add(clase, new RecomendadorDePuntosDeColocacion(RecomendadorRetrofitAdapter.getInstance()));
        } catch (IOException e) {
          throw new RuntimeException(e);
        }
      } else if (clase.equals(RedistribucionViandaService.class))
        add(clase, new RedistribucionViandaService(get(IRedistribucionesViandaRepository.class), get(ColaboradoresService.class), get(HeladerasService.class), get(MotivoRedistribucionService.class), get(SolicitudAperturaHeladeraService.class), get(ICalculadorPuntos.class)));
      else if (clase.equals(ReportesFactory.class))
        add(clase, new ReportesFactory(get(IViandasRepository.class), get(IDonacionesViandaRepository.class), get(IRedistribucionesViandaRepository.class), get(IFallasTecnicasRepository.class), get(IAlertasRepository.class)));
      else if (clase.equals(ReportesService.class))
        add(clase, new ReportesService(get(IReportesRepository.class)));
      else if (clase.equals(RespuestaCampoService.class))
        add(clase, new RespuestaCampoService(get(ICampoRepository.class), get(IOpcionRepository.class)));
      else if (clase.equals(RespuestaFormularioService.class))
        add(clase, new RespuestaFormularioService(get(IRespuestasFormularioRepository.class), get(ColaboradoresService.class), get(FormulariosService.class), get(RespuestaCampoService.class)));
      else if (clase.equals(RolesService.class))
        add(clase, new RolesService(get(IRolesRepository.class)));
      else if (clase.equals(SolicitudAperturaHeladeraService.class))
        add(clase, new SolicitudAperturaHeladeraService(get(ISolicitudesAperturaHeladeraRepository.class)));
      else if (clase.equals(SuscripcionesService.class))
        add(clase, new SuscripcionesService(get(ISuscripcionesRepository.class), get(ColaboradoresService.class)));
      else if (clase.equals(TarjetasService.class))
        add(clase, new TarjetasService(get(ITarjetasRepository.class), get(ITarjetasColaboradorRepository.class), get(IPosiblesCodigosTarjetaRepository.class)));
      else if (clase.equals(TecnicosHelper.class))
        add(clase, new TecnicosHelper(get(ITecnicosRepository.class)));
      else if (clase.equals(TecnicosService.class))
        add(clase, new TecnicosService(get(ITecnicosRepository.class), get(MedioContactoService.class), get(IVisitasTecnicoRepository.class), get(IncidentesService.class)));
      else if (clase.equals(TipoDocumentoMapper.class))
        add(clase, new TipoDocumentoMapper());
      else if (clase.equals(UsuarioService.class))
        add(clase, new UsuarioService(get(IUsuariosRepository.class), get(ColaboradoresService.class)));
      else if (clase.equals(ViandasService.class))
        add(clase, new ViandasService(get(IViandasRepository.class), get(HeladerasService.class), get(SolicitudAperturaHeladeraService.class), get(ColaboradoresService.class)));

        // CONTROLADORES
      else if (clase.equals(AlertasController.class))
        add(clase, new AlertasController(get(AlertasService.class), get(HeladerasService.class)));
      else if (clase.equals(AltaPersonaVulnerableController.class))
        add(clase, new AltaPersonaVulnerableController(get(AltaPersonaVulnerableService.class)));
      else if (clase.equals(CanjeProductoController.class))
        add(clase, new CanjeProductoController(get(OfertasProductoService.class)));
      else if (clase.equals(CargaMasivaController.class))
        add(clase, new CargaMasivaController(get(CargaMasivaService.class)));
      else if (clase.equals(ColocacionHeladerasController.class))
        add(clase, new ColocacionHeladerasController(get(ColocacionHeladerasService.class), get(ModelosService.class)));
      else if (clase.equals(DonacionDineroController.class))
        add(clase, new DonacionDineroController(get(DonacionDineroService.class)));
      else if (clase.equals(FallasTecnicasController.class))
        add(clase, new FallasTecnicasController(get(FallasTecnicasService.class), get(FileUploadService.class), get(HeladerasService.class)));
      else if (clase.equals(FormulariosController.class))
        add(clase, new FormulariosController(get(FormulariosService.class)));
      else if (clase.equals(HeladerasController.class))
        add(clase, new HeladerasController(get(HeladerasService.class)));
      else if (clase.equals(LoginController.class))
        add(clase, new LoginController(get(UsuarioService.class)));
      else if (clase.equals(LogoutController.class))
        add(clase, new LogoutController());
      else if (clase.equals(ModelosHeladeraController.class))
        add(clase, new ModelosHeladeraController(get(ModelosService.class)));
      else if (clase.equals(OfertasProductoController.class))
        add(clase, new OfertasProductoController(get(OfertasProductoService.class), get(FileUploadService.class)));
      else if (clase.equals(PerfilController.class))
        add(clase, new PerfilController(get(OfertasProductoService.class), get(ColaboradoresService.class), get(FormaColaboracionService.class)));
      else if (clase.equals(RegistroController.class))
        add(clase, new RegistroController(get(UsuarioService.class), get(ColaboradoresService.class), get(FormaColaboracionService.class)));
      else if (clase.equals(RedistribucionViandaController.class))
        add(clase, new RedistribucionViandaController(get(RedistribucionViandaService.class), get(MotivoRedistribucionService.class)));
      else if (clase.equals(ReportesController.class))
        add(clase, new ReportesController(get(ReportesService.class)));
      else if (clase.equals(RespuestaFormularioController.class))
        add(clase, new RespuestaFormularioController(get(RespuestaFormularioService.class), get(FormulariosService.class), get(ColaboradoresService.class)));
      else if (clase.equals(PosiblesCodigosTarjetasController.class))
        add(clase, new PosiblesCodigosTarjetasController(get(PosiblesCodigosService.class)));
      else if (clase.equals(TecnicosController.class))
        add(clase, new TecnicosController(get(TecnicosService.class)));
      else if (clase.equals(RecomendacionesController.class)) {
        try {
          add(clase, new RecomendacionesController(new RecomendacionDonaciones(RecomendadorDonacionesRetrofitAdapter.getInstance()), new RecomendadorDePuntosDeColocacion(RecomendadorRetrofitAdapter.getInstance())));
        } catch (IOException e) {
          throw new RuntimeException(e);
        }
      } else if (clase.equals(SuscripcionesController.class))
        add(clase, new SuscripcionesController(get(SuscripcionesService.class), get(HeladerasService.class)));
      else if (clase.equals(ViandasController.class))
        add(clase, new ViandasController(get(ViandasService.class)));
      else if (clase.equals(VisitasTecnicoController.class))
        add(clase, new VisitasTecnicoController(get(TecnicosService.class), get(IncidentesService.class), get(FileUploadService.class)));

        //EXTRAS
      else if (clase.equals(BotTelegramSender.class))
        add(clase, new BotTelegramSender());
      else if (clase.equals(CargaColaboracionCsvReader.class))
        add(clase, new CargaColaboracionCsvReader());
      else if (clase.equals(SendGridMailSender.class)) {
        try {
          add(clase, new SendGridMailSender());
        } catch (IOException e) {
          throw new RuntimeException(e);
        }
      } else if (clase.equals(StepMeterRegistry.class)) {
        add(clase, new DDMetricsUtils("heladeras_solidarias").getRegistry());
      } else if (clase.equals(TwilioWhatsappSender.class))
        add(clase, new TwilioWhatsappSender());
      else throw new IllegalArgumentException("No hay servicio provisto para esa clase");
    }
    return (T) services.get(clase);
  }

  private static boolean existeService(Class<?> clase) {
    return services.containsKey(clase);
  }
}
