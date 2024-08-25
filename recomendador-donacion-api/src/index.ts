import express, { Request, Response } from "express";

const app = express();
const PORT = process.env.PORT || 3000;

app.get("/api/recomendacion", (req: Request, res: Response) => {
  res.json({
    recomendacion: "Recomendacion",
  });
});

app.listen(PORT, () => {
  console.log(`Servidor corriendo en puerto ${PORT}`);
});
