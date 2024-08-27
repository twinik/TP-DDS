import express from "express";
import { router } from "./routes";
import { swaggerSpec } from "./docs/swagger";
const swaggerUi = require("swagger-ui-express");

const app = express();
const PORT = process.env.PORT || 3000;

app.use("/docs", swaggerUi.serve, swaggerUi.setup(swaggerSpec));
app.use("/api", router);

app.listen(PORT, () => {
  console.log(`Servidor corriendo en puerto ${PORT}`);
});
