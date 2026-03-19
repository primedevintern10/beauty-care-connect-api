# Beauty Care Connect API - Temporary Setup Guide (Windows)

This guide helps you run the project without permanent JAVA_HOME or Maven setup.
It is useful for remote setup on another laptop.

## 1. Prerequisites

1. Windows laptop with internet.
2. Project folder available locally.
3. MongoDB Atlas connection URL.
4. PowerShell terminal.

## 2. Update MongoDB Connection

Open `src/main/resources/application.properties` and set:

```properties
spring.data.mongodb.uri=<YOUR_MONGODB_ATLAS_URI>
spring.data.mongodb.database=Dev
spring.mvc.pathmatch.matching-strategy = ANT_PATH_MATCHER
```

Example URI format:

```text
mongodb+srv://<username>:<password>@<cluster-url>/?retryWrites=true&w=majority
```

## 3. Run with Temporary Environment Only

From the project root, run these commands:

```powershell
Set-ExecutionPolicy -Scope Process Bypass
.\run-api-temp.ps1
```

What this script does:

1. Finds Java 21, or installs Temurin 21 if missing.
2. Downloads portable Maven 3.9.6 into `%TEMP%` if missing.
3. Sets `JAVA_HOME`, `MAVEN_HOME`, and `Path` only for this terminal session.
4. Starts the API with `mvn spring-boot:run`.

Important:

- No permanent system environment variables are changed.
- Closing terminal removes temporary env values.

## 4. Verify API Is Running

When startup is successful:

1. Open Swagger UI:
   - `http://localhost:8080/swagger-ui/index.html`
2. If Swagger opens, backend is running correctly.

## 5. Quick Login Test

Use endpoint:

- `POST /auth/login`

Request body example:

```json
{
  "username": "admin",
  "password": "password123"
}
```

## 6. Stop the API

In the same PowerShell window, press:

- `Ctrl + C`

## 7. Remote Setup Steps (for sister)

1. Share this project folder.
2. Ask her to open PowerShell in project root.
3. Run:

```powershell
Set-ExecutionPolicy -Scope Process Bypass
.\run-api-temp.ps1
```

4. Ask her to open:
   - `http://localhost:8080/swagger-ui/index.html`

## 8. Troubleshooting

### Problem: `mvnw.cmd` not recognized
Reason: Running command outside project folder or missing wrapper files.
Fix: Use the provided script:

```powershell
.\run-api-temp.ps1
```

### Problem: `JAVA_HOME not found`
Fix: Run the script; it installs Java 21 and sets env vars temporarily.

### Problem: Mongo authentication/connection error
Check:

1. Atlas username/password in URI.
2. Atlas Network Access allows your public IP.
3. `spring.data.mongodb.database=Dev` matches your DB name.

### Problem: Port 8080 already in use
Run with another port:

```powershell
mvn spring-boot:run "-Dspring-boot.run.arguments=--server.port=8081"
```

Then open:

- `http://localhost:8081/swagger-ui/index.html`

## 9. Optional: Clean Temporary Maven Cache

If needed, remove downloaded portable Maven:

```powershell
Remove-Item "$env:TEMP\apache-maven-3.9.6" -Recurse -Force
Remove-Item "$env:TEMP\apache-maven-3.9.6-bin.zip" -Force
```

---

Setup complete when:

1. App starts without errors.
2. Swagger UI loads.
3. Login endpoint responds.
