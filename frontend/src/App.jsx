import { useEffect, useState } from 'react';

const defaultRole = 'Backend Developer';

function App() {
  const [dashboard, setDashboard] = useState(null);
  const [careerRole, setCareerRole] = useState(defaultRole);
  const [experienceLevel, setExperienceLevel] = useState('Advanced');
  const [roadmap, setRoadmap] = useState([]);
  const [skills, setSkills] = useState([]);

  useEffect(() => {
    fetch('/api/dashboard')
      .then((res) => res.json())
      .then((data) => setDashboard(data));

    fetch('/api/skills')
      .then((res) => res.json())
      .then((data) => setSkills(data));
  }, []);

  const generateRoadmap = async () => {
    const res = await fetch('/api/roadmaps/generate', {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify({ careerRole, experienceLevel })
    });
    const data = await res.json();
    setRoadmap(data.stages || []);
  };

  const addSkill = async () => {
    const newSkill = { name: 'Docker', proficiency: 4 };
    const res = await fetch('/api/skills', {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify(newSkill)
    });
    const data = await res.json();
    setSkills((prev) => [...prev, data]);
  };

  return (
    <div className="app-shell">
      <aside className="sidebar">
        <div className="brand">SkillSync</div>
        <nav>
          <button className="nav-item active">Generate Roadmap</button>
          <button className="nav-item">Progress</button>
          <button className="nav-item">Insights</button>
          <button className="nav-item">Compare Careers</button>
          <button className="nav-item">Projects</button>
        </nav>

        <div className="profile-box">
          <div className="avatar">N</div>
          <div>
            <strong>{dashboard?.username || 'Nalin Tuscano'}</strong>
            <div className="muted">Front-end Dev</div>
          </div>
        </div>
      </aside>

      <main className="main-panel">
        <header className="topbar">
          <div>
            <h1>{dashboard?.message || 'Ready to level up your skills today?'}</h1>
          </div>
          <div className="stats">
            <div><span>3</span><small>STREAK</small></div>
            <div><span>0</span><small>XP TODAY</small></div>
            <div><span>0</span><small>BADGES</small></div>
          </div>
        </header>

        <section className="hero card">
          <h2>Generate Your <span>Career Roadmap</span></h2>
          <p>Select your career goal and experience level to get a personalized, AI-powered learning path.</p>

          <div className="controls">
            <label>
              <span>Career Role</span>
              <select value={careerRole} onChange={(e) => setCareerRole(e.target.value)}>
                <option>Frontend Developer</option>
                <option>Backend Developer</option>
                <option>Data Scientist</option>
                <option>DevOps Engineer</option>
                <option>Full Stack Developer</option>
              </select>
            </label>

            <label>
              <span>Experience Level</span>
              <select value={experienceLevel} onChange={(e) => setExperienceLevel(e.target.value)}>
                <option>Beginner</option>
                <option>Intermediate</option>
                <option>Advanced</option>
              </select>
            </label>
          </div>

          <div className="cta-row">
            <button className="primary" onClick={generateRoadmap}>Generate My Roadmap</button>
            <button className="secondary" onClick={addSkill}>Add Sample Skill</button>
          </div>
        </section>

        <section className="cards-grid">
          {roadmap.length > 0 ? roadmap.map((stage, index) => (
            <div className="mini-card" key={index}>
              <h3>{stage}</h3>
            </div>
          )) : (
            <>
              <div className="mini-card"><h3>Frontend Developer</h3><p>$85,000/yr</p></div>
              <div className="mini-card"><h3>Backend Developer</h3><p>$95,000/yr</p></div>
              <div className="mini-card"><h3>Data Scientist</h3><p>$110,000/yr</p></div>
              <div className="mini-card"><h3>DevOps Engineer</h3><p>$105,000/yr</p></div>
            </>
          )}
        </section>

        <section className="skills-panel card">
          <h3>Current Skills</h3>
          <ul>
            {skills.length > 0 ? skills.map((skill, index) => (
              <li key={index}>{skill.name} · Level {skill.proficiency}</li>
            )) : (
              <li>Java · Level 5</li>
            )}
          </ul>
        </section>
      </main>
    </div>
  );
}

export default App;
