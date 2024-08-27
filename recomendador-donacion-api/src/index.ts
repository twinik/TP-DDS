import express, { Request, Response } from "express";
import path from "path";
import fs from "fs";

const app = express();
const PORT = process.env.PORT || 3000;

interface Comunidad {
  id: number;
  nombre: string;
  lat: number;
  lon: number;
}

function cargarComunidades(): Comunidad[] {
  const rutaArchivo = path.join(__dirname, "data", "comunidades.json");
  const datosBrutos = fs.readFileSync(rutaArchivo, "utf-8");
  return JSON.parse(datosBrutos) as Comunidad[];
}

const comunidades = cargarComunidades();

function obtenerDistancia(
  lat1: number,
  lon1: number,
  lat2: number,
  lon2: number
): number {
  const R = 6371; // Radio de la Tierra en km
  const dLat = ((lat2 - lat1) * Math.PI) / 180;
  const dLon = ((lon2 - lon1) * Math.PI) / 180;
  const a =
    Math.sin(dLat / 2) * Math.sin(dLat / 2) +
    Math.cos((lat1 * Math.PI) / 180) *
      Math.cos((lat2 * Math.PI) / 180) *
      Math.sin(dLon / 2) *
      Math.sin(dLon / 2);
  const c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
  return R * c;
}

app.get("/locaciones-donacion", (req: Request, res: Response) => {
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

  const lugares = comunidades
    .map((comunidad) => {
      const distancia = obtenerDistancia(
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
});

app.listen(PORT, () => {
  console.log(`Servidor corriendo en puerto ${PORT}`);
});
