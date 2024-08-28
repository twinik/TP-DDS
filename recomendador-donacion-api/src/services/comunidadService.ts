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
