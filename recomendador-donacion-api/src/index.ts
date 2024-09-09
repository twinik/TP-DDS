import express from "express"
import { router } from "./routes"
import { swaggerSpec } from "./docs/swagger"
import { AppDataSource } from "./data-source"
const swaggerUi = require("swagger-ui-express")

const app = express()
const PORT = process.env.PORT || 3000

AppDataSource.initialize()
	.then(() => {})
	.catch((error) => console.log(error))

app.use("/docs", swaggerUi.serve, swaggerUi.setup(swaggerSpec))
app.use("/api", router)

app.listen(PORT, () => {
	console.log(`Servidor corriendo en puerto ${PORT}`)
})
