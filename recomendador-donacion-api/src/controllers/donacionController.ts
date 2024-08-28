import { Request, Response } from "express";
import { obtenerComunidadesCercanas } from "../services/comunidadService";

export async function obtenerLugares(req: Request, res: Response) {
  const lat = parseFloat(req.query.lat as string);
  const lon = parseFloat(req.query.lon as string);
  const limite = parseInt(req.query.limite as string) || 5;
  const distanciaMaxEnKM =
    parseFloat(req.query.distanciaMaxEnKM as string) || 100;

  if (isNaN(lat) || isNaN(lon)) {
    return res
      .status(400)
      .json({ error: "Faltan parámetros de latitud o longitud válidos" });
  }

  try {
    const lugares = await obtenerComunidadesCercanas(
      lat,
      lon,
      distanciaMaxEnKM,
      limite
    );

    res.json({ lugares });
  } catch (error) {
    console.error(error);
    res.status(500).json({ error: "Error al obtener comunidades cercanas" });
  }
}
