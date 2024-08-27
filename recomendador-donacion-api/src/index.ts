import express, { Request, Response } from "express";
import path from "path";
import fs from "fs";
const swaggerJSDoc = require("swagger-jsdoc");
const swaggerUi = require("swagger-ui-express");

// Swagger definition
const swaggerDefinition = {
  openapi: "3.0.0",
  info: {
    title: "Recomendador de locaciones para donaciones - API",
    version: "1.0.0",
  },
};

const options = {
  swaggerDefinition,
  apis: ["./src/*.ts"],
};

const swaggerSpec = swaggerJSDoc(options);

const app = express();
const PORT = process.env.PORT || 3000;

app.use("/docs", swaggerUi.serve, swaggerUi.setup(swaggerSpec));

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

/**
 * @swagger
 * /locaciones-donacion:
 *   get:
 *     summary: Obtiene comunidades cercanas para donaciones
 *     description: Devuelve las comunidades más cercanas a una ubicación dada, dentro de un rango de distancia y con un límite de resultados.
 *     parameters:
 *       - in: query
 *         name: lat
 *         schema:
 *           type: number
 *           format: float
 *         required: true
 *         description: Latitud de la ubicación de referencia
 *       - in: query
 *         name: lon
 *         schema:
 *           type: number
 *           format: float
 *         required: true
 *         description: Longitud de la ubicación de referencia
 *       - in: query
 *         name: limite
 *         schema:
 *           type: integer
 *           default: 5
 *         description: Número máximo de comunidades a devolver
 *       - in: query
 *         name: distanciaMaxEnKM
 *         schema:
 *           type: number
 *           format: float
 *           default: 2
 *         description: Distancia máxima en kilómetros para filtrar las comunidades
 *     responses:
 *       200:
 *         description: Lista de comunidades cercanas
 *         content:
 *           application/json:
 *             schema:
 *               type: object
 *               properties:
 *                 lugares:
 *                   type: array
 *                   items:
 *                     type: object
 *                     properties:
 *                       id:
 *                         type: integer
 *                         example: 1
 *                       nombre:
 *                         type: string
 *                         example: Comunidad A
 *                       lat:
 *                         type: number
 *                         format: float
 *                         example: -34.612700
 *                       lon:
 *                         type: number
 *                         format: float
 *                         example: -58.418000
 *                       distanciaEnKM:
 *                         type: number
 *                         format: float
 *                         example: 0.12
 *       400:
 *         description: Error en los parámetros de latitud o longitud
 *         content:
 *           application/json:
 *             schema:
 *               type: object
 *               properties:
 *                 error:
 *                   type: string
 *                   example: Faltan parámetros de latitud o longitud válidos
 */

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
