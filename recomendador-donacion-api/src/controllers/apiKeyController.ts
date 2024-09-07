import { Request, Response } from "express";
import { ApiKeyResults, generateApiKey } from "generate-api-key";
import { crearApiKey } from "../services/apiKeyService";

export async function obtenerApiKey(req: Request, res: Response) {
	const key: string = generateApiKey({
		method: "string",
		length: 32
	}).toString();

	await crearApiKey(key);

	res.send({ key });
}
