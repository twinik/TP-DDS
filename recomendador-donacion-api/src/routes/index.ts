import { Router } from "express";
import { obtenerLugares } from "../controllers/donacionController";
import { obtenerApiKey } from "../controllers/apiKeyController";
import { apiKeyMiddleware } from "../middlewares/apiKeyMiddleware";

const router = Router();

/**
 * @swagger
 * /locaciones-donacion:
 *   get:
 *     summary: Obtiene comunidades cercanas para donaciones
 *     description: Devuelve las comunidades más cercanas a una ubicación dada, dentro de un rango de distancia y con un límite de resultados.
 *     security:
 *          - apiKeyAuth: []
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
 *           default: 100
 *         required: false
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
 *         description: Parámetros de latitud o longitud inválidos
 *         content:
 *           application/json:
 *             schema:
 *               type: object
 *               properties:
 *                 error:
 *                   type: string
 *                   example: Faltan parámetros de latitud o longitud válidos
 *  components:
 *       securitySchemes:
 *          apiKeyAuth:
 *           type: apiKey
 *           in: header
 *           name: Authorization
 *           description: "API Key requerida en el header 'Authorization'. Ejemplo: 'Authorization:{apiKey}'"
 */
router.get("/locaciones-donacion", apiKeyMiddleware, obtenerLugares);

/**
 * @swagger
 * /key:
 *   get:
 *     summary: Devuelve una API Key
 *     description: Devuelve una API Key que deberá ser guardada y enviada en el header del request para utilizar el servicio de recomendacion
 *     responses:
 *       200:
 *         description:
 *         content:
 *           application/json:
 *             schema:
 *               type: object
 *               properties:
 *                 key:
 *                   type: string
 */
router.get("/key", obtenerApiKey);

export { router };
