import ESGResultPage from "./ESGResultPage";
import { Building2, Home, Link2, PenSquare, Sparkles, TrendingUp } from "lucide-react";
import { FormEvent, useEffect, useState } from "react";
import { Link, Navigate, Route, Routes } from "react-router-dom";
import { useNavigate } from "react-router-dom";
import axios from "axios";

type AssetInput = {
  assetName: string;
  assetType: string;
  floorArea: number;
  vacancyRate: number;
  footTraffic: number;
  energyUsage: number;
  transitAccess: string;
  greenCertification: string;
};

type SaveResponse = {
  success: boolean;
  assetId: number;
  esgResult: {
    totalScore: number;
    totalMax: number;
    grade: string;
  };
  loanResult: {
    riskGrade: string;
    interestAdjustment: string;
    ltvLimit: string;
    recommendation: string;
  };
  block: {
    blockIndex: number;
    blockHash: string;
  };
};

type HistoryItem = {
  blockIndex: number;
  timestamp: string;
  assetName: string;
  totalScore: number;
  grade: string;
  blockHash: string;
};

function Navbar() {
  return (
    <header className="topbar">
      <div className="wrapper topbar-inner">
        <Link className="brand" to="/">
          <div className="brand-icon">
            <Building2 size={18} />
          </div>
          <div>
            <strong>ESG Financial Platform</strong>
            <p>하나은행 X AI X 블록체인</p>
          </div>
        </Link>

        <nav className="nav-menu">
          <Link to="/">
            <Home size={16} />
            홈
          </Link>
          <Link to="/input">
            <PenSquare size={16} />
            자산 입력
          </Link>
          <Link to="/history">
            <Link2 size={16} />
            블록체인 이력
          </Link>
        </nav>
      </div>
    </header>
  );
}

function HomePage() {
  return (
    <>
      <section className="hero">
        <div className="wrapper">
          <div className="hero-badge">
            <span />
            AI + Blockchain Powered ESG Analysis
          </div>
          <h1>
            AI·블록체인 기반
            <br />
            <em>ESG 부동산 금융</em>
            <br />
            의사결정 플랫폼
          </h1>
          <p>
            환경(E)·사회(S)·지배구조(G) 데이터를 AI가 분석하고,
            <br />
            결과를 블록체인에 기록하여 신뢰할 수 있는 금융 심사를 지원합니다.
          </p>
          <Link className="hero-button" to="/input">
            <TrendingUp size={18} />
            ESG 분석 시작하기
          </Link>
        </div>
      </section>

      <section className="stats">
        <div className="wrapper stats-grid">
          <article>
            <h3>0</h3>
            <p>블록체인 기록 수</p>
          </article>
          <article>
            <h3 className="good">100%</h3>
            <p>체인 무결성</p>
          </article>
          <article>
            <h3>3</h3>
            <p>ESG 분석 항목</p>
          </article>
          <article>
            <h3 className="violet">AI</h3>
            <p>의사결정 엔진</p>
          </article>
        </div>
      </section>

      <section className="features wrapper">
        <header>
          <h2>핵심 기능 4가지</h2>
          <p>공정하고 투명한 ESG 금융 심사 프로세스</p>
        </header>
        <div className="feature-grid">
          <article className="feature-card">
            <div className="feature-icon blue">
              <Building2 size={20} />
            </div>
            <h3>자산 정보 입력</h3>
            <p>건물 정보, 에너지 사용량, 친환경 인증 등 8개 항목 입력</p>
          </article>
          <article className="feature-card">
            <div className="feature-icon green">
              <Sparkles size={20} />
            </div>
            <h3>ESG 점수 산출</h3>
            <p>AI 엔진이 E·S·G 항목을 분석해 점수를 산출</p>
          </article>
          <article className="feature-card">
            <div className="feature-icon amber">
              <PenSquare size={20} />
            </div>
            <h3>대출 심사 추천</h3>
            <p>등급 기반 우대금리·LTV·심사 의견 생성</p>
          </article>
          <article className="feature-card">
            <div className="feature-icon purple">
              <Link2 size={20} />
            </div>
            <h3>블록체인 기록</h3>
            <p>SHA-256 해시 체인으로 위변조 방지 저장</p>
          </article>
        </div>
      </section>
    </>
  );
}

function InputPage() {
  const navigate = useNavigate();
  const [form, setForm] = useState<AssetInput>({
    assetName: "",
    assetType: "오피스",
    floorArea: 30000,
    vacancyRate: 5.0,
    footTraffic: 50000,
    energyUsage: 120,
    transitAccess: "우수",
    greenCertification: "우수(그린2등급)",
  });
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState("");
  const [result, setResult] = useState<SaveResponse | null>(null);

  const onSubmit = async (e: FormEvent) => {
    e.preventDefault();
    setLoading(true);
    setError("");

    try {
  // 👉 가짜 결과 (백엔드 없이 출력용)
  setResult({
    success: true,
    assetId: 1,
    esgResult: {
      totalScore: 72,
      totalMax: 100,
      grade: "B",
    },
    loanResult: {
      riskGrade: "중간",
      interestAdjustment: "-0.8%",
      ltvLimit: "60%",
      recommendation: "우대금리 적용 가능",
    },
    block: {
      blockIndex: 1,
      blockHash: "0xA94F23C91B7E...C21D",
    },
  });
   navigate("/result");

} catch (err) {
      if (axios.isAxiosError(err)) {
        const message = err.response?.data?.message || `HTTP ${err.response?.status ?? "연결 실패"}`;
        setError(`요청 실패: ${message}`);
      } else {
        setError("요청 실패: 알 수 없는 오류");
      }
    } finally {
      setLoading(false);
    }
  };

  return (
    <main className="wrapper sub-page">
      <h2>자산 입력</h2>
      <p>입력 데이터를 저장하고, ESG 점수와 블록체인 기록을 생성합니다.</p>

      <form className="input-grid card" onSubmit={onSubmit}>
        <label>
          자산명
          <input
            value={form.assetName}
            onChange={(e) => setForm({ ...form, assetName: e.target.value })}
            required
          />
        </label>
        <label>
          자산 유형
          <select
            value={form.assetType}
            onChange={(e) => setForm({ ...form, assetType: e.target.value })}
          >
            <option value="오피스">오피스</option>
            <option value="리테일">리테일</option>
            <option value="호텔">호텔</option>
          </select>
        </label>
        <label>
          연면적
          <input
            type="number"
            value={form.floorArea}
            onChange={(e) => setForm({ ...form, floorArea: Number(e.target.value) })}
          />
        </label>
        <label>
          공실률(%)
          <input
            type="number"
            step="0.1"
            value={form.vacancyRate}
            onChange={(e) => setForm({ ...form, vacancyRate: Number(e.target.value) })}
          />
        </label>
        <label>
          유동인구
          <input
            type="number"
            value={form.footTraffic}
            onChange={(e) => setForm({ ...form, footTraffic: Number(e.target.value) })}
          />
        </label>
        <label>
          에너지 사용량
          <input
            type="number"
            value={form.energyUsage}
            onChange={(e) => setForm({ ...form, energyUsage: Number(e.target.value) })}
          />
        </label>
        <label>
          교통 접근성
          <select
            value={form.transitAccess}
            onChange={(e) => setForm({ ...form, transitAccess: e.target.value })}
          >
            <option value="매우우수">매우우수</option>
            <option value="우수">우수</option>
            <option value="보통">보통</option>
          </select>
        </label>
        <label>
          친환경 인증
          <select
            value={form.greenCertification}
            onChange={(e) => setForm({ ...form, greenCertification: e.target.value })}
          >
            <option value="최우수(그린1등급)">최우수(그린1등급)</option>
            <option value="우수(그린2등급)">우수(그린2등급)</option>
            <option value="일반">일반</option>
          </select>
        </label>

        <button className="hero-button submit" type="submit" disabled={loading}>
          {loading ? "저장 중..." : "ESG 분석 + 블록체인 저장"}
        </button>
      </form>

      {error && <p className="error">{error}</p>}

      {result && (
        <section className="card result-card">
          <h3>저장 결과</h3>
          <p>
            ESG 점수: <strong>{result.esgResult.totalScore}</strong> / {result.esgResult.totalMax} (
            {result.esgResult.grade})
          </p>
          <p>대출 리스크: {result.loanResult.riskGrade}</p>
          <p>금리 조정: {result.loanResult.interestAdjustment}</p>
          <p>블록 인덱스: #{result.block.blockIndex}</p>
          <p>블록 해시: {result.block.blockHash}</p>
        </section>
      )}
    </main>
  );
}

function HistoryPage() {
  const [items, setItems] = useState<HistoryItem[]>([]);
  const [integrity, setIntegrity] = useState<boolean | null>(null);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState("");

  useEffect(() => {
    const run = async () => {
      try {
        const res = await axios.get<{
          success: boolean;
          chainIntegrity: boolean;
          totalBlocks: number;
          history: HistoryItem[];
        }>("http://localhost:8080/blockchain/history");
        setItems(res.data.history);
        setIntegrity(res.data.chainIntegrity);
      } catch {
        setError("이력 조회 실패");
      } finally {
        setLoading(false);
      }
    };
    run();
  }, []);

  return (
    <main className="wrapper sub-page">
      <h2>블록체인 이력</h2>
      <p>체인 무결성: {integrity === null ? "-" : integrity ? "정상" : "오류"}</p>
      {loading && <p>로딩 중...</p>}
      {error && <p className="error">{error}</p>}

      <div className="history-list">
        {items.map((it) => (
          <article className="card" key={`${it.blockIndex}-${it.assetName}`}>
            <h3>
              #{it.blockIndex} - {it.assetName}
            </h3>
            <p>점수: {it.totalScore}</p>
            <p>등급: {it.grade}</p>
            <p>해시: {it.blockHash}</p>
            <p>시간: {new Date(it.timestamp).toLocaleString()}</p>
          </article>
        ))}
      </div>
    </main>
  );
}

export default function App() {
  return (
    <>
      <Navbar />
      <Routes>
        <Route path="/" element={<HomePage />} />
        <Route path="/input" element={<InputPage />} />
        <Route path="/history" element={<HistoryPage />} />
        <Route path="/result" element={<ESGResultPage />} />
        <Route path="*" element={<Navigate to="/" replace />} />
      </Routes>
    </>
  );
}