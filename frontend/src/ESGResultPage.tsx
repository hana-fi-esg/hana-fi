import React, { useEffect, useState } from "react";

export default function ESGResultPage() {
  const finalScore = 72;
  const [animatedScore, setAnimatedScore] = useState(0);

  useEffect(() => {
    let start = 0;
    const interval = setInterval(() => {
      start += 2;
      setAnimatedScore(start);
      if (start >= finalScore) clearInterval(interval);
    }, 10);
    return () => clearInterval(interval);
  }, []);

  return (
    <div
      style={{
        background:
          "radial-gradient(circle at top right, #1e3a8a22, transparent), #0f172a",
        minHeight: "100vh",
        padding: "40px",
        color: "white",
      }}
    >
      <div
        style={{
          maxWidth: "1200px",
          margin: "0 auto",
          display: "flex",
          flexDirection: "column",
          gap: "24px",
        }}
      >

        {/*히어로 + 정보 패널 */}
<div
  style={{
    display: "flex",
    gap: "20px",
  }}
>
  {/* 왼쪽: 점수 */}
  <div
    style={{
      flex: 2,
      borderRadius: "24px",
      padding: "40px",
      background: "linear-gradient(135deg, #1e3a8a, #2563eb)",
      boxShadow: "0 25px 70px rgba(37,99,235,0.5)",
    }}
  >
    <div style={{ fontSize: "13px", opacity: 0.8 }}>
      TOTAL ESG SCORE
    </div>

    <div style={{ fontSize: "90px", fontWeight: 900 }}>
      {animatedScore}
    </div>

    <div style={{ fontSize: "18px" }}>Grade B</div>

    <div
      style={{
        marginTop: "20px",
        height: "8px",
        background: "rgba(255,255,255,0.2)",
        borderRadius: "10px",
      }}
    >
      <div
        style={{
          width: `${animatedScore}%`,
          height: "8px",
          background: "#fff",
          borderRadius: "10px",
        }}
      />
    </div>
  </div>

  {/* 오른쪽: 정보 패널 */}
  <div
    style={{
      flex: 1,
      background: "#1e293b",
      borderRadius: "24px",
      padding: "24px",
      boxShadow: "0 15px 40px rgba(0,0,0,0.4)",
      display: "flex",
      flexDirection: "column",
      justifyContent: "space-between",
    }}
  >
    {[
      { label: "RISK LEVEL", value: "MEDIUM", color: "#f59e0b" },
      { label: "STABILITY", value: "STABLE", color: "#22c55e" },
      { label: "CONFIDENCE", value: "87%", color: "#3b82f6" },
      { label: "LOAN ELIGIBILITY", value: "APPROVED", color: "#22c55e" },
    ].map((item) => (
      <div key={item.label} style={{ marginBottom: "12px" }}>
        <div style={{ fontSize: "11px", opacity: 0.6 }}>
          {item.label}
        </div>

        <div
          style={{
            fontSize: "16px",
            fontWeight: 700,
            color: item.color,
          }}
        >
          {item.value}
        </div>
      </div>
    ))}
  </div>
</div>
      {/* ESG SCORE BAR (고급 버전) */}
<div
  style={{
    display: "flex",
    gap: "12px",
    marginTop: "-10px",
  }}
>
  {[
    { label: "Environment", value: 65, color: "#22c55e" },
    { label: "Social", value: 45, color: "#3b82f6" },
    { label: "Governance", value: 35, color: "#a855f7" },
  ].map((item) => (
    <div
      key={item.label}
      style={{
        flex: 1,
        background: "#1e293b",
        borderRadius: "12px",
        padding: "14px",
        boxShadow: "0 6px 20px rgba(0,0,0,0.3)",
        position: "relative",
      }}
    >
      {/* 상단 라인 (고급 포인트) */}
      <div
        style={{
          position: "absolute",
          top: 0,
          left: 0,
          right: 0,
          height: "3px",
          background: item.color,
          borderTopLeftRadius: "12px",
          borderTopRightRadius: "12px",
        }}
      />

      <div style={{ fontSize: "11px", opacity: 0.6 }}>
        {item.label}
      </div>

      <div style={{ fontSize: "20px", fontWeight: 700 }}>
        {item.value}
      </div>
    </div>
  ))}
</div>

        {/* 핵심: 분석 인사이트 (ESG 대신) */}
        <div style={{ display: "flex", gap: "20px" }}>
          {[
            {
              title: "RISK FACTORS",
              content: ["Vacancy Rate Increase", "Carbon Exposure"],
            },
            {
              title: "IMPROVEMENT",
              content: ["Green Certification", "Energy Optimization"],
            },
            {
              title: "AI INSIGHT",
              content: ["Stable asset with moderate financial risk"],
            },
          ].map((item) => (
            <div
              key={item.title}
              style={{
                flex: 1,
                background: "#1e293b",
                borderRadius: "18px",
                padding: "22px",
                boxShadow: "0 10px 30px rgba(0,0,0,0.35)",
              }}
            >
              <div style={{ fontSize: "11px", opacity: 0.6 }}>
                {item.title}
              </div>

              <div style={{ marginTop: "10px", fontSize: "13px", lineHeight: "1.6" }}>
                {item.content.map((c) => (
                  <div key={c}>• {c}</div>
                ))}
              </div>
            </div>
          ))}
        </div>

        {/* 하단 */}
        <div style={{ display: "flex", gap: "20px" }}>

          {/* 점수 근거 */}
          <div
            style={{
              flex: 2,
              background: "#1e293b",
              borderRadius: "18px",
              padding: "22px",
              boxShadow: "0 10px 30px rgba(0,0,0,0.35)",
            }}
          >
            <div style={{ fontSize: "12px", opacity: 0.6 }}>
              SCORING ANALYSIS
            </div>

            <div
              style={{
                marginTop: "12px",
                display: "grid",
                gridTemplateColumns: "1fr 1fr",
                gap: "10px",
              }}
            >
              {[
                "+20 Energy Efficiency",
                "+15 Certification",
                "-10 Vacancy Risk",
                "+10 Accessibility",
              ].map((item) => (
                <div
                  key={item}
                  style={{
                    background: "#334155",
                    padding: "12px",
                    borderRadius: "10px",
                    fontSize: "13px",
                    textAlign: "center",
                  }}
                >
                  {item}
                </div>
              ))}
            </div>
          </div>

          {/* 금융 */}
          <div
            style={{
              flex: 1,
              background: "#1e293b",
              borderRadius: "18px",
              padding: "22px",
              boxShadow: "0 10px 30px rgba(0,0,0,0.35)",
            }}
          >
            <div style={{ fontSize: "12px", opacity: 0.6 }}>
              FINANCIAL RESULT
            </div>

            <div style={{ marginTop: "12px", fontSize: "14px" }}>
              <div>Risk Grade: Medium</div>
              <div>LTV Limit: 60%</div>

              <div
                style={{
                  marginTop: "10px",
                  fontSize: "26px",
                  fontWeight: 800,
                  color: "#3b82f6",
                }}
              >
                -0.8%
              </div>
            </div>
          </div>
        </div>

        {/* 블록체인 */}
        <div
          style={{
            background: "#1e293b",
            borderRadius: "18px",
            padding: "20px",
            border: "1px solid #334155",
            boxShadow: "0 10px 30px rgba(0,0,0,0.35)",
          }}
        >
          <div style={{ fontSize: "12px", opacity: 0.6 }}>
            BLOCKCHAIN RECORD
          </div>

          <div style={{ fontSize: "12px", marginTop: "6px" }}>
            Immutable · Verified · Secure
          </div>

          <div style={{ fontSize: "11px", opacity: 0.7 }}>
            0xA94F23C91B7E...C21D
          </div>
        </div>

      </div>
    </div>
  );
}