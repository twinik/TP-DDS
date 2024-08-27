import { Request, Response } from "express";
import { obtenerComunidades } from "../services/comunidadService";
import { calcularDistancia } from "../utils/distanceCalculator";

export function obtenerLugares(req: Request, res: Response) {
  const lat = parseFloat(req.query.lat as string);
  const lon = parseFloat(req.query.lon as string);
  const limite = parseInt(req.query.limite as string) || 5;
  const distanciaMaxEnKM =
    parseFloat(req.query.distanciaMaxEnKM as string) || 2;

  if (isNaN(lat) || isNaN(lon)) {
    return res
      .status(400)
      .json({ error: "Faltan parámetros de latitud o longitud válidos" });
  }

  const comunidades = obtenerComunidades();

  const lugares = comunidades
    .map((comunidad) => {
      const distancia = calcularDistancia(
        lat,
        lon,
        comunidad.lat,
        comunidad.lon
      );
      return {
        ...comunidad,
        distanciaEnKM: parseFloat(distancia.toFixed(2)),
      };
    })
    .sort((a, b) => a.distanciaEnKM - b.distanciaEnKM)
    .filter((comunidad) => comunidad.distanciaEnKM <= distanciaMaxEnKM)
    .slice(0, limite);

  res.json({ lugares });
}
