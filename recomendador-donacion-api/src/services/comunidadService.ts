import path from "path";
import fs from "fs";
import { Comunidad } from "../models/comunidad";

export function obtenerComunidades(): Comunidad[] {
  const rutaArchivo = path.join(__dirname, "..", "data", "comunidades.json");
  const datosBrutos = fs.readFileSync(rutaArchivo, "utf-8");
  return JSON.parse(datosBrutos) as Comunidad[];
}
