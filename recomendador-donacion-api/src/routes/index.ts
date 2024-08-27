import { Router } from "express";
import { obtenerLugares } from "../controllers/donacionController";

const router = Router();

/**
 * @openapi
 * /locaciones-donacion:
 *   get:
 *     summary: Obtiene las comunidades más cercanas para donaciones
 *     parameters:
 *       - in: query
 *         name: lat
 *         schema:
 *           type: number
 *         required: true
 *         description: Latitud del punto de referencia
 *       - in: query
 *         name: lon
 *         schema:
 *           type: number
 *         required: true
 *         description: Longitud del punto de referencia
 *       - in: query
 *         name: limite
 *         schema:
 *           type: integer
 *           default: 5
 *         description: Cantidad máxima de comunidades a devolver
 *       - in: query
 *         name: distanciaMaxEnKM
 *         schema:
 *           type: number
 *           default: 2
 *         description: Distancia máxima en kilómetros desde el punto de referencia
 *     responses:
 *       200:
 *         description: Lista de comunidades recomendadas
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
 *                       nombre:
 *                         type: string
 *                       lat:
 *                         type: number
 *                       lon:
 *                         type: number
 *                       distanciaEnKM:
 *                         type: number
 *       400:
 *         description: Parámetros de latitud o longitud inválidos
 */
router.get("/locaciones-donacion", obtenerLugares);

export { router };
