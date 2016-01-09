
## Play 2.4 testbench

### Get started

Requirements:

- JDK 1.8
- sbt
- Local Postgres database named `playtestbench` as configured in [`application.conf`](conf/application.conf).
Alternatively, comment out the `db.*` configs to skip the database.

```
sbt test
sbt run
```
