import { AppDataSource } from "../data-source";
import { ApiKey } from "../models/apiKey";

export async function crearApiKey(key: string) {
	const keysRepo = AppDataSource.getRepository(ApiKey);
	keysRepo.save({ key });
}

export async function validarApiKey(key: string) {
	const keysRepo = AppDataSource.getRepository(ApiKey);
	const apiKey: ApiKey | null = await keysRepo.findOne({
		where: {
			key: key,
			valid: true
		}
	});
	return apiKey ? true : false;
}
