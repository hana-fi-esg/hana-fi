# Backend (Spring Boot + Gradle + PostgreSQL)

## Team Run Guide

This project is designed so each teammate can run it with their own local database settings.
Do not commit personal values such as DB password or local Java install paths.

### Prerequisites
- Java 17
- PostgreSQL installed and running
- Database created: `esg_platform`

### Environment Variables
The backend reads these values from each developer's local environment:
- `DB_URL`
- `DB_USERNAME`
- `DB_PASSWORD`

Default expectations:
- `DB_URL=jdbc:postgresql://localhost:5432/esg_platform`
- `DB_USERNAME=postgres`
- `DB_PASSWORD=` your local PostgreSQL password

### Windows PowerShell
```powershell
$env:DB_URL="jdbc:postgresql://localhost:5432/esg_platform"
$env:DB_USERNAME="postgres"
$env:DB_PASSWORD="your-postgres-password"
.\gradlew.bat bootRun
```

### macOS / Linux
```bash
export DB_URL=jdbc:postgresql://localhost:5432/esg_platform
export DB_USERNAME=postgres
export DB_PASSWORD=your-postgres-password
./gradlew bootRun
```

### Notes
- PowerShell requires `.\gradlew.bat`, not just `gradlew.bat`
- Keep passwords out of Git commits
- Each teammate can use a different password and local PostgreSQL setup

## 왜 이 구조로 시작했는가
- `Spring Boot`: REST API를 빠르게 만들기 좋고 팀원이 많아져도 구조가 안정적입니다.
- `Gradle`: 빌드 속도가 빠르고 설정이 유연합니다.
- `PostgreSQL`: 관계형 데이터(자산, 점수, 블록 기록) 무결성 관리에 강합니다.

## 현재 MVP 1단계 구현 범위
- `POST /blockchain/save`: 입력 데이터를 저장하고 ESG 점수/대출추천/해시체인 블록을 생성
- `GET /blockchain/history`: 블록체인 이력 조회
- `GET /health`: 서버 상태 확인
- `POST /loan/recommendation`: 점수 기반 대출추천 계산

## 실행
1. PostgreSQL 실행 후 DB `esg_platform` 준비
2. Java 17 설치 및 `JAVA_HOME` 설정 권장
3. 환경변수(선택)
   - `DB_URL`
   - `DB_USERNAME`
   - `DB_PASSWORD`
4. 실행 명령
   - Mac/Linux: `export JAVA_HOME=/opt/homebrew/opt/openjdk@17/libexec/openjdk.jdk/Contents/Home && ./gradlew bootRun`
   - Windows: `gradlew.bat bootRun`

Java 17 인식 오류가 날 때(Gradle toolchain):
- Mac/Linux: `./gradlew -Dorg.gradle.java.installations.paths="$JAVA_HOME" bootRun`
- Windows(PowerShell): `.\gradlew.bat -Dorg.gradle.java.installations.paths="$env:JAVA_HOME" bootRun`

참고:
- 일부 Mac 환경에서 `/usr/libexec/java_home -v 17`가 17을 못 찾을 수 있습니다.
- 그 경우 위의 Homebrew 경로(`/opt/homebrew/opt/openjdk@17/...`)를 직접 사용하세요.

기본값:
- `DB_URL=jdbc:postgresql://localhost:5432/esg_platform`
- `DB_USERNAME=postgres`
- `DB_PASSWORD=` (빈 값)

로컬 DB 사용자명이 `postgres`가 아니면:
- Mac/Linux: `DB_USERNAME=<내계정> ./gradlew bootRun`
- Windows(PowerShell): `$env:DB_USERNAME="<내계정>"; .\gradlew.bat bootRun`

## 핵심 용어
- **해시(SHA-256)**: 입력 데이터를 고정 길이 문자열로 변환한 값
- **해시 체인**: 현재 블록이 이전 블록 해시를 포함해 연결되는 구조
- **무결성**: 데이터가 중간에 변조되지 않았음을 검증할 수 있는 성질
- **Batch**: 대량/주기 작업을 자동으로 실행하는 처리 방식
