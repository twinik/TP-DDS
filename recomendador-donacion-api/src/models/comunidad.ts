import { Column, Entity, PrimaryGeneratedColumn } from "typeorm";

@Entity()
export class Comunidad {
  @PrimaryGeneratedColumn()
  id: number;

  @Column()
  nombre: string;

  @Column("decimal", { precision: 10, scale: 8 })
  lat: number;

  @Column("decimal", { precision: 10, scale: 8 })
  lon: number;
}
