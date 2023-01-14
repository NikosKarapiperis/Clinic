### Run a postgres database using docker

```bash
docker run --name spb_db --rm -e  POSTGRES_PASSWORD=pass123 -e POSTGRES_DB=clinic --net=host -v pgdata14:/var/lib/postgresql/data  -d postgres:14
```
run init sql scripts
```bash
docker run --name spb_db --rm -e  POSTGRES_PASSWORD=pass123 -e POSTGRES_DB=clinic --net=host -v "$(pwd)"/assets/db:/docker-entrypoint-initdb.d -v pgdata14:/var/lib/postgresql/data -d postgres:14
```

## remove db data
```bash
docker volume rm pgdata14
```

## connect to database using psql

```bash
sudo apt install postgresl-client
psql -h localhost -U postgres -d clinic -p 5432 -W
```

# Existing Users and Roles in pre-configured initial sql

| USER  | PASSWORD | ROLES       |
|-------|----------|-------------|
| root  | 12345678 |  ADMIN      |
| user1 | 12345678 | SECRETARY   |
| user2 | 12345678 | DOCTOR      |
| user3 | 12345678 | PATIENT     |
 


## Links:
* [install docker](https://tinyurl.com/2m3bhahn)


## Initializing values with users.sql file
```bash
\i {The absolute path of users.sql file }
```
