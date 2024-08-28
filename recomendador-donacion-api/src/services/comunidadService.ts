import path from "path";
import fs from "fs";
import { Comunidad } from "../models/comunidad";
import { AppDataSource } from "../data-source";

export function obtenerComunidadesDeJson(): Comunidad[] {
  const rutaArchivo = path.join(__dirname, "..", "data", "comunidades.json");
  const datosBrutos = fs.readFileSync(rutaArchivo, "utf-8");
  return JSON.parse(datosBrutos) as Comunidad[];
}

export async function obtenerComunidades(): Promise<Comunidad[]> {
  return await AppDataSource.manager.find(Comunidad);
}

export async function obtenerComunidadesCercanas(
  lat: number,
  lon: number,
  distanciaMaxEnKM: number,
  limite: number
): Promise<(Comunidad & { distanciaEnKM: number })[]> {
  const comunidadRepository = AppDataSource.getRepository(Comunidad);

  const comunidades = await comunidadRepository
    .createQueryBuilder("comunidad")
    .addSelect(
      `(6371 * acos(cos(radians(:lat)) * cos(radians(comunidad.lat)) * cos(radians(comunidad.lon) - radians(:lon)) + sin(radians(:lat)) * sin(radians(comunidad.lat))))`,
      "distanciaEnKM"
    )
    .where(
      `(6371 * acos(cos(radians(:lat)) * cos(radians(comunidad.lat)) * cos(radians(comunidad.lon) - radians(:lon)) + sin(radians(:lat)) * sin(radians(comunidad.lat)))) <= :distanciaMaxEnKM`,
      { lat, lon, distanciaMaxEnKM }
    )
    .orderBy("distanciaEnKM", "ASC")
    .limit(limite)
    .getRawMany();

  return comunidades.map((comunidad) => ({
    id: comunidad.comunidad_id,
    nombre: comunidad.comunidad_nombre,
    lat: parseFloat(comunidad.comunidad_lat),
    lon: parseFloat(comunidad.comunidad_lon),
    distanciaEnKM: parseFloat(comunidad.distanciaEnKM.toFixed(2)),
  }));
}
