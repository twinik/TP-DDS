const swaggerJSDoc = require("swagger-jsdoc");
import path from "path";

const swaggerDefinition = {
  openapi: "3.0.0",
  info: {
    title: "Recomendador de locaciones para donaciones - API",
    version: "1.0.0",
  },
};

const options = {
  swaggerDefinition,
  apis: [path.join(__dirname, "..", "routes", "*.ts")],
};

export const swaggerSpec = swaggerJSDoc(options);
