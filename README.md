# Hana ESG Platform (MVP)

AI + 블록체인 기반 부동산 ESG 금융 의사결정 플랫폼 MVP입니다.

## 기술 스택
- Frontend: React + TypeScript + Vite
- Backend: Java 17 + Spring Boot + Gradle
- Database: PostgreSQL

## 현재 구현 범위
- `POST /blockchain/save`: 자산 입력 저장 + ESG 점수 계산 + SHA-256 해시체인 블록 생성
- `GET /blockchain/history`: 블록체인 이력 조회 + 체인 무결성 확인
- `POST /loan/recommendation`: 점수 기반 대출 추천
- `GET /health`: 서버 상태 확인

## 폴더 구조
- `frontend/`: React 화면
- `backend/`: Spring API + DB 저장 + 해시체인 로직
- `legacy/`: 기존 정적 HTML 백업

## 로컬 실행 방법
### 1) PostgreSQL 준비
1. PostgreSQL 실행
2. DB 생성: `esg_platform`

### 2) Backend 실행
```bash
cd backend
./gradlew bootRun
```

기본 포트: `http://localhost:8080`

선택 환경변수:
- `DB_URL` (기본: `jdbc:postgresql://localhost:5432/esg_platform`)
- `DB_USERNAME` (기본: `minjae`)
- `DB_PASSWORD` (기본: 빈 값)

### 3) Frontend 실행
```bash
cd frontend
npm install
npm run dev
```

기본 포트: `http://localhost:5500`

## 팀 협업 규칙 (권장)
1. `main` 직접 커밋 금지
2. 기능별 브랜치 사용: `feature/<name>`
3. PR 생성 후 리뷰로 병합
4. 커밋 메시지 예시
   - `feat: blockchain save api`
   - `feat: input page api integration`
   - `fix: cors origin config`

## 역할 분담 기준 (현재 계획)
- 1번: 데이터 수집 파이프라인, DB 테이블, SHA-256, Spring Batch
- 2번: Python AI 연동 클라이언트, 대출 심사 엔진, 조회 API, JWT
- 3번: AI 모델 전처리/학습/점수 산출 고도화