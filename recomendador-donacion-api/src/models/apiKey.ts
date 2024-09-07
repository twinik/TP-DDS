import {
	Column,
	CreateDateColumn,
	Entity,
	PrimaryGeneratedColumn
} from "typeorm";

@Entity("api_key")
export class ApiKey {
	@PrimaryGeneratedColumn()
	id: number;

	@Column({ unique: true })
	key: String;

	@CreateDateColumn()
	created_at: Date;

	@Column({ default: true })
	valid: boolean;
}
