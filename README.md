# ProManage Automated Task Scheduling System

Small Java CLI app that stores projects in PostgreSQL and generates an optimal weekly schedule (Monday–Friday) maximizing revenue under deadline constraints.

Quick start

1. Create a PostgreSQL database named `promanage` and run `sql/init.sql`:

```ps
psql -U postgres -c "CREATE DATABASE promanage;"
psql -U postgres -d promanage -f sql/init.sql
```

2. Update `src/main/resources/application.properties` with your DB `db.url`, `db.user`, and `db.password`.

3. Build and run with Maven:

```ps
mvn package
java -cp target/automated-task-scheduler-1.0.0.jar com.promanage.Main
```

Usage

- Add projects (title, deadline in working days, revenue)
- List projects
- Generate weekly schedule: selects up to 5 projects (one per weekday) maximizing revenue while respecting deadlines (deadline measured in working days from Monday).

Scheduling algorithm

Greedy job sequencing: projects sorted by revenue desc; each project is placed in the latest available day <= min(deadline,5).
