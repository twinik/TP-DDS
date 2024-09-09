import { Request, Response } from "express"
import { generateApiKey } from "generate-api-key"
import { crearApiKey } from "../services/apiKeyService"

export async function obtenerApiKey(req: Request, res: Response) {
	try {
		const key: string = generateApiKey({
			method: "string",
			length: 32,
		}).toString()

		await crearApiKey(key)

		res.status(201).json({
			mensaje: "API key generada con éxito",
			key,
		})
	} catch (error) {
		console.error("Error al generar la API key:", error)
		res.status(500).json({
			error: "Hubo un error al generar la API key",
		})
	}
}
