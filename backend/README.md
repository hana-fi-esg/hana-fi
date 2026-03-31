# Backend (Spring Boot + Gradle + PostgreSQL)

## 5분 실행 (팀 공통)
1. PostgreSQL 실행 (Docker 권장)
```bash
cd backend
docker compose up -d
```
2. Java 17 설정
```bash
export JAVA_HOME=/opt/homebrew/opt/openjdk@17/libexec/openjdk.jdk/Contents/Home
export PATH="$JAVA_HOME/bin:$PATH"
```
3. 백엔드 실행
```bash
cd backend
export DB_URL=jdbc:postgresql://localhost:5432/esg_platform
export DB_USERNAME=postgres
export DB_PASSWORD=postgres
./gradlew bootRun
```
4. 확인
```bash
curl -s http://localhost:8080/health
```

## Windows (PowerShell)
```powershell
cd backend
docker compose up -d
$env:DB_URL="jdbc:postgresql://localhost:5432/esg_platform"
$env:DB_USERNAME="postgres"
$env:DB_PASSWORD="postgres"
.\gradlew.bat bootRun
```

## 환경변수
- `DB_URL` 기본: `jdbc:postgresql://localhost:5432/esg_platform`
- `DB_USERNAME` 기본: `postgres`
- `DB_PASSWORD` 기본: 빈 값 (Docker 사용 시 `postgres` 권장)
- `APP_CORS_ALLOWED_ORIGINS` 예시:
  - `http://localhost:5500,http://127.0.0.1:5500,http://localhost:5173,http://127.0.0.1:5173`

샘플 파일: `backend/.env.example`

## 현재 API
- `POST /blockchain/save`
- `GET /blockchain/history`
- `POST /loan/recommendation`
- `GET /health`
- `POST /batch/integrity/run`
- `GET /batch/integrity/runs`

## 참고
- Java toolchain 17 오류 시:
  - Mac/Linux: `./gradlew -Dorg.gradle.java.installations.paths="$JAVA_HOME" bootRun`
  - Windows: `.\gradlew.bat -Dorg.gradle.java.installations.paths="$env:JAVA_HOME" bootRun`
