# FileSharing

## PostgreSQL quick start on MAC

Install:
```bash
brew update
brew install postgresql
```

Start/stop service
```bash
brew services start postgresql
brew services stop postgresql
```

Create db for tests
```bash
psql postgres

CREATE DATABASE fileshare;
CREATE USER usr WITH PASSWORD 'password';
```

## Init database

```bash
psql -U usr -d fileshare -f src/main/resources/schema.sql
psql -U usr -d fileshare -f src/main/resources/procedures/save_file.sql
psql -U usr -d fileshare -f src/main/resources/procedures/get_file.sql
```

## Check database hints

Open _psql_:
```bash
psql -U usr -d fileshare
```

Useful _psql_ commands:
```
\d files        // - show structure of a table 'files'
\df             // - show all functions
\df <func>      // - show specific function
```

Invoke procedures
```
SELECT save_file('example.txt', 'sample content'::bytea);
SELECT * FROM get_file('example.txt');
```
