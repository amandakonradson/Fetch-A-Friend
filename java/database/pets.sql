DROP TABLE IF EXISTS "user_pet"; 
DROP TABLE IF EXISTS "pets";

CREATE TABLE "pets" (
  "pet_id" serial,
  "name" varchar(50),
  "breed" varchar(30),
  "birth_year" int,
  "gender" varchar(10),
  "temperament" varchar(1000),
  "size" varchar(100),
  "spayed_neutered" varchar(5),
  PRIMARY KEY ("pet_id")
);

CREATE TABLE "user_pet" (
  "pet_id" int NOT NULL,
  "user_id" int NOT NULL,
  CONSTRAINT "PK_user_pet" PRIMARY KEY ("pet_id", "user_id"),
  CONSTRAINT "FK_user_pet_user" FOREIGN KEY ("user_id") REFERENCES "users" ("user_id"),
  CONSTRAINT "FK_user_pet_pets" FOREIGN KEY ("pet_id") REFERENCES "pets" ("pet_id")
  );

ALTER TABLE "users" ADD "email" varchar(320) UNIQUE;

 

