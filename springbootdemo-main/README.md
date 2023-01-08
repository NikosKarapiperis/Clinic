### Run a postgres database using docker

```bash
docker run --name spb_db --rm -e  POSTGRES_PASSWORD=pass123 -e POSTGRES_DB=students --net=host -v pgdata14:/var/lib/postgresql/data  -d postgres:14
```
run init sql scripts
```bash
docker run --name spb_db --rm -e  POSTGRES_PASSWORD=pass123 -e POSTGRES_DB=students --net=host -v "$(pwd)"/assets/db:/docker-entrypoint-initdb.d -v pgdata14:/var/lib/postgresql/data -d postgres:14
```

## remove db data
```bash
docker volume rm pgdata14
```

## connect to database using psql

```bash
sudo apt install postgresl-client
psql -h localhost -U postgres -d students -p 5432 -W
```

# Branches:
* [Main](https://gitlab.com/atsadimas/springbootdemo). Spring boot application with thymeleaf, spring security and bootstrap 5
Existing Users and Roles in pre-configured initial sql

| USER  | PASSWORD | ROLES       |
|-------|----------|-------------|
| user1 | pass123  | USER        |
| user2 | pass123  | USER        |
| admin | pass123  | USER, ADMIN |
| root  | pass123  | ADMIN       |
 
You can create users using /register
* [Security](https://gitlab.com/atsadimas/springbootdemo/-/tree/security). Spring Boot Backend with JWT Authentication

API endpoints provided to register users

## Links:
* [install docker](https://tinyurl.com/2m3bhahn)