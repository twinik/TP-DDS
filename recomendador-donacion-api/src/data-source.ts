require("dotenv").config();
import "reflect-metadata";
import { DataSource } from "typeorm";
import { Comunidad } from "./models/comunidad";
import { ApiKey } from "./models/apiKey";

export const AppDataSource = new DataSource({
	type: "mysql",
	host: (process.env.DB_COMUNIDADES_HOST as string || "localhost"),
	port: (process.env.DB_COMUNIDADES_PORT || 3306) as number,
	username: process.env.DB_COMUNIDADES_USER,
	password: process.env.DB_COMUNIDADES_PSWD,
	database: process.env.DB_COMUNIDADES_DBNAME,
	synchronize: true,
	logging: true,
	entities: [Comunidad, ApiKey],
	subscribers: [],
	migrations: []
});
