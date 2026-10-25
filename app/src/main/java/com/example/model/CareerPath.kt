package com.example.model

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Analytics
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.BugReport
import androidx.compose.material.icons.filled.Cloud
import androidx.compose.material.icons.filled.Code
import androidx.compose.material.icons.filled.DesignServices
import androidx.compose.material.icons.filled.PhoneAndroid
import androidx.compose.material.icons.filled.Security
import androidx.compose.ui.graphics.vector.ImageVector

data class StepMilestone(
  val stepNumber: Int,
  val title: String,
  val description: String,
  val duration: String
)

data class ProjectIdea(
  val title: String,
  val description: String,
  val techStack: String
)

data class CareerRole(
  val roleName: String,
  val typicalHiringDomains: String,
  val avgSalaryInfo: String
)

data class CareerTrack(
  val id: String,
  val title: String,
  val category: String,
  val shortDescription: String,
  val prerequisites: String,
  val durationLabel: String,
  val icon: ImageVector,
  val about: String,
  val skillsStudentsLearn: List<String>,
  val roadmap: List<StepMilestone>,
  val exampleProjects: List<ProjectIdea>,
  val careerOpportunities: List<CareerRole>,
  val isPayAfterPlacementEligible: Boolean = true,
  val isFlagshipOfficial: Boolean = true,
  val trackBadge: String = "Official Flagship Cohort",
  val officialStatusNote: String = "Active Enrollment • AI-First Curriculum"
)

object CareerTrackData {
  const val tracksDisclaimer = "Course syllabi and active cohort schedules are regularly updated to reflect current engineering hiring benchmarks. Please verify current batch schedules and seat availability directly with Karka Academy admissions via karka.academy or @karka.ai."

  val allTracks: List<CareerTrack> = listOf(
    CareerTrack(
      id = "fullstack-mern",
      title = "Full Stack Web Development (MERN)",
      category = "Web & Software",
      shortDescription = "Master end-to-end fullstack web engineering with React, Node.js, Express, MongoDB, and modern AI development workflows.",
      prerequisites = "Beginner friendly • No prior coding required",
      durationLabel = "16 - 20 Weeks (Cohort-based)",
      icon = Icons.Default.Code,
      about = "Karka Academy's flagship full-stack track trains candidates from fundamental programming to scalable multi-tier architectures. Students build production-ready applications with React, Node.js, Express, and MongoDB, backed by Git collaboration, prompt engineering, and deployment sprints.",
      skillsStudentsLearn = listOf("JavaScript (ES6+)", "React.js & Hooks", "Node.js & Express", "MongoDB & Mongoose", "REST APIs", "Tailwind CSS", "Git & GitHub", "AI Coding Workflows"),
      roadmap = listOf(
        StepMilestone(1, "Web Foundations & Modern JS", "HTML5, CSS3, Flexbox/Grid, and core ES6+ JavaScript concepts.", "Weeks 1-4"),
        StepMilestone(2, "Frontend Engineering with React", "Component state, routing, hooks, API consumption, and responsive UI.", "Weeks 5-8"),
        StepMilestone(3, "Backend Server & Database Design", "Express microservices, JWT authentication, MongoDB schemas, and REST endpoints.", "Weeks 9-12"),
        StepMilestone(4, "Capstone Project & Placement Sprints", "Build an authentic fullstack capstone, mock interviews, and resume reviews.", "Weeks 13-18")
      ),
      exampleProjects = listOf(
        ProjectIdea("Campus Placement & Hiring Portal", "Fullstack portal with candidate filtering, resume uploads, and interview status tracking.", "React, Node.js, MongoDB, Express"),
        ProjectIdea("Collaborative Code Snippet Hub", "Real-time developer notebook with syntax highlighting and team workspaces.", "MERN, Tailwind, WebSockets")
      ),
      careerOpportunities = listOf(
        CareerRole("Full Stack Developer", "Tech Startups, Product MNCs, IT Services", "High Demand"),
        CareerRole("Frontend React Engineer", "SaaS Companies & Digital Agencies", "High Demand"),
        CareerRole("Backend Node.js Developer", "Fintech & E-commerce Platforms", "Steady Growth")
      ),
      isFlagshipOfficial = true,
      trackBadge = "Official Flagship Cohort",
      officialStatusNote = "Flagship Offering • AI-First Curriculum"
    ),
    CareerTrack(
      id = "ai-datascience",
      title = "AI, Machine Learning & Data Science",
      category = "AI & Data",
      shortDescription = "Build intelligent software agents, predictive algorithms, and generative AI models solving real enterprise challenges.",
      prerequisites = "Basic math curiosity • Open to all college disciplines",
      durationLabel = "18 - 24 Weeks (Intensive)",
      icon = Icons.Default.AutoAwesome,
      about = "Immerse yourself in modern Artificial Intelligence. From foundational Python scripting and data wrangling with Pandas to building predictive machine learning models, neural networks, and deploying LLM applications with LangChain and FastAPI.",
      skillsStudentsLearn = listOf("Python for Data Science", "Pandas & NumPy", "Machine Learning (Scikit-Learn)", "Deep Learning (PyTorch)", "Prompt Engineering & LLMs", "SQL & Database Queries", "Data Visualization", "FastAPI Serving"),
      roadmap = listOf(
        StepMilestone(1, "Python & Mathematical Tooling", "Data structures, algorithms, statistics, numpy, and pandas manipulation.", "Weeks 1-4"),
        StepMilestone(2, "Machine Learning Algorithms", "Supervised/unsupervised models, regression, decision trees, model validation.", "Weeks 5-9"),
        StepMilestone(3, "Deep Learning & Applied GenAI", "Neural networks, image classification, transformers, and API fine-tuning.", "Weeks 10-15"),
        StepMilestone(4, "Production AI Capstone & Placement", "Deploy models to cloud endpoints, portfolio showcase, and technical interview drills.", "Weeks 16-20")
      ),
      exampleProjects = listOf(
        ProjectIdea("AI Resume Analyzer & Matcher", "NLP pipeline scoring resume compatibility against tech job descriptions.", "Python, LangChain, FastAPI"),
        ProjectIdea("Predictive Customer Retention Engine", "ML classification model forecasting user retention for subscription services.", "Pandas, Scikit-Learn, Streamlit")
      ),
      careerOpportunities = listOf(
        CareerRole("Machine Learning Engineer", "AI Startups, Enterprise Labs", "High Demand"),
        CareerRole("Data Scientist / AI Specialist", "E-commerce, Banking, Healthcare", "Fast Growing"),
        CareerRole("Associate AI Developer", "Software Product Teams", "Emerging Domain")
      ),
      isFlagshipOfficial = true,
      trackBadge = "Official Flagship Cohort",
      officialStatusNote = "Flagship Offering • GenAI & ML Focus"
    ),
    CareerTrack(
      id = "data-analytics",
      title = "Data Analytics with AI",
      category = "AI & Data",
      shortDescription = "Transform complex business datasets into actionable visual intelligence using SQL, Power BI, Python, and automated reporting.",
      prerequisites = "Beginner friendly • Analytical thinking mindset",
      durationLabel = "14 - 18 Weeks",
      icon = Icons.Default.Analytics,
      about = "Master corporate business analytics. Learn advanced SQL querying, data cleaning with Python, interactive dashboard creation in Power BI / Tableau, and using Generative AI for automated executive summaries.",
      skillsStudentsLearn = listOf("Advanced SQL", "Power BI & Tableau", "Python (Pandas)", "Excel & Google Sheets", "Data Modeling", "Statistical Analysis", "Executive Dashboards", "AI Analytics Prompts"),
      roadmap = listOf(
        StepMilestone(1, "SQL & Relational Databases", "Complex queries, window functions, aggregations, and data normalization.", "Weeks 1-4"),
        StepMilestone(2, "Python Data Analysis", "Cleaning messy data, Exploratory Data Analysis (EDA), and automated scripts.", "Weeks 5-8"),
        StepMilestone(3, "BI Visualization & Dashboards", "Interactive Power BI reports, DAX formulas, KPI tracking, and UI design.", "Weeks 9-12"),
        StepMilestone(4, "Business Case Studies & Placement", "Real domain case studies (e-commerce, fintech) and technical interview prep.", "Weeks 13-16")
      ),
      exampleProjects = listOf(
        ProjectIdea("Retail E-Commerce Sales Performance Dashboard", "Comprehensive Power BI dashboard tracking revenue, customer acquisition cost, and cohort churn.", "SQL, Power BI, DAX"),
        ProjectIdea("Healthcare Operational Efficiency Reporter", "Automated SQL + Python pipeline summarizing hospital bed turnaround and patient wait times.", "Python, SQL, Streamlit")
      ),
      careerOpportunities = listOf(
        CareerRole("Data Analyst", "Fintech, E-commerce, Healthcare", "High Demand"),
        CareerRole("Business Intelligence Engineer", "Enterprise SaaS & Consultancies", "Steady Growth"),
        CareerRole("Reporting Analyst", "Corporate Operations & Logistics", "Entry Friendly")
      ),
      isFlagshipOfficial = true,
      trackBadge = "Official Core Program",
      officialStatusNote = "Official Track • Business Analytics"
    ),
    CareerTrack(
      id = "qa-automation",
      title = "QA Automation Testing",
      category = "Web & Software",
      shortDescription = "Ensure flawless software quality with automated test frameworks, Selenium, Cypress, API testing, and CI/CD pipelines.",
      prerequisites = "Beginner friendly • Detail-oriented mindset",
      durationLabel = "14 - 18 Weeks",
      icon = Icons.Default.BugReport,
      about = "Learn modern software quality engineering. Master test automation from UI testing with Selenium and Cypress to backend API validation with Postman and RestAssured, integrated into GitHub Actions CI/CD pipelines.",
      skillsStudentsLearn = listOf("Core Java / JavaScript", "Selenium WebDriver", "Cypress / Playwright", "API Testing (Postman/RestAssured)", "TestNG / JUnit", "Git & GitHub Actions", "SQL for Testers", "Bug Tracking (Jira)"),
      roadmap = listOf(
        StepMilestone(1, "Testing Principles & Core Language", "Manual testing fundamentals, test cases, Java / JavaScript essentials.", "Weeks 1-4"),
        StepMilestone(2, "Web UI Automation", "Writing robust test scripts with Selenium & Cypress, page object models.", "Weeks 5-8"),
        StepMilestone(3, "API & Database Automation", "RestAssured API testing, JSON assertions, and SQL database validation.", "Weeks 9-12"),
        StepMilestone(4, "CI/CD Pipeline Integration & Placement", "Automating test runs in GitHub Actions, test reports, and interview drills.", "Weeks 13-16")
      ),
      exampleProjects = listOf(
        ProjectIdea("Automated E-Commerce Checkout Test Suite", "Full regression test suite covering cart flows, coupon validations, and edge cases.", "Cypress, JavaScript, GitHub Actions"),
        ProjectIdea("REST API Verification Framework", "Automated API suite validating status codes, response times, and payload schemas.", "Postman, Newman, RestAssured")
      ),
      careerOpportunities = listOf(
        CareerRole("QA Automation Engineer", "Product MNCs, IT Services", "High Demand"),
        CareerRole("Software Development Engineer in Test (SDET)", "Tech Startups, Fintech", "High Value"),
        CareerRole("Quality Assurance Specialist", "Enterprise Software Consultancies", "Steady Growth")
      ),
      isFlagshipOfficial = true,
      trackBadge = "Official Core Program",
      officialStatusNote = "Official Track • Quality Engineering"
    ),
    CareerTrack(
      id = "uiux-design",
      title = "UI/UX & Product Design",
      category = "Design",
      shortDescription = "Design intuitive user experiences, interactive prototypes, and scalable design systems for web and mobile platforms.",
      prerequisites = "Zero coding required • Creative and empathetic mindset",
      durationLabel = "12 - 16 Weeks",
      icon = Icons.Default.DesignServices,
      about = "Learn the complete design thinking process from user research and wireframing to high-fidelity Figma prototyping, micro-interactions, usability testing, and handoff to engineering teams.",
      skillsStudentsLearn = listOf("Figma Mastery", "User Research & Personas", "Wireframing & Information Architecture", "Design Systems & Auto-Layout", "Interactive Prototyping", "Usability Testing", "Material 3 Guidelines", "Developer Handoff"),
      roadmap = listOf(
        StepMilestone(1, "Design Thinking & User Empathy", "User interviews, journey mapping, and problem statement definitions.", "Weeks 1-3"),
        StepMilestone(2, "Wireframing & Figma Tooling", "Components, auto-layout, styles, and typography hierarchies.", "Weeks 4-7"),
        StepMilestone(3, "High-Fidelity Prototyping", "Micro-interactions, design system tokens, and user testing reviews.", "Weeks 8-11"),
        StepMilestone(4, "Portfolio Case Study & Placement", "Publishing 2 comprehensive Behance/Notion case studies and interview prep.", "Weeks 12-16")
      ),
      exampleProjects = listOf(
        ProjectIdea("Healthcare Booking & Patient Records App", "Comprehensive mobile UX redesign with accessibility-first appointment booking.", "Figma, User Testing"),
        ProjectIdea("EdTech Dashboard Design System", "Scalable component library with dark/light themes and responsive desktop views.", "Figma Design Tokens")
      ),
      careerOpportunities = listOf(
        CareerRole("UI/UX Designer", "Product Startups, Digital Agencies", "High Demand"),
        CareerRole("Product Design Associate", "Fintech & Consumer Tech", "Fast Growing"),
        CareerRole("Interaction Designer", "Enterprise Software Companies", "High Value")
      ),
      isFlagshipOfficial = true,
      trackBadge = "Official Core Program",
      officialStatusNote = "Official Track • Product Design"
    ),
    CareerTrack(
      id = "mobile-app-dev",
      title = "Mobile App Development (Android & Flutter)",
      category = "Mobile",
      shortDescription = "Create native and high-performance cross-platform mobile apps with Kotlin, Jetpack Compose, and Flutter.",
      prerequisites = "Beginner friendly • Logical thinking mindset",
      durationLabel = "16 - 20 Weeks",
      icon = Icons.Default.PhoneAndroid,
      about = "Learn modern mobile application development with Kotlin and Flutter. Build responsive UIs with declarative layouts, connect cloud databases, implement offline synchronization, and publish apps to the Google Play Store.",
      skillsStudentsLearn = listOf("Kotlin", "Jetpack Compose", "Flutter & Dart", "REST API Integration", "Firebase & SQLite", "Mobile Architecture (MVVM)", "Material 3 Design", "Google Play Release"),
      roadmap = listOf(
        StepMilestone(1, "Language Essentials", "Object-oriented programming in Kotlin / Dart and null safety.", "Weeks 1-4"),
        StepMilestone(2, "Declarative UI Frameworks", "Building fluid screens with Jetpack Compose & Flutter widgets.", "Weeks 5-8"),
        StepMilestone(3, "State, APIs & Local Persistence", "ViewModel, Coroutines, REST networking with Retrofit, and Room/SQLite.", "Weeks 9-13"),
        StepMilestone(4, "App Publishing & Placement Sprints", "Performance tuning, unit testing, APK signing, and mobile interview prep.", "Weeks 14-18")
      ),
      exampleProjects = listOf(
        ProjectIdea("Campus Transit & Timetable App", "Interactive mobile application with real-time route updates and offline cache.", "Kotlin, Jetpack Compose, Room"),
        ProjectIdea("Student Micro-Habit Tracker", "Gamified daily goal tracker with custom local notifications and streak metrics.", "Flutter, Firebase, Material 3")
      ),
      careerOpportunities = listOf(
        CareerRole("Android Software Engineer", "Tech Startups, Enterprise Mobility", "High Demand"),
        CareerRole("Flutter App Developer", "Agencies, SaaS Companies", "Steady Growth"),
        CareerRole("Mobile Frontend Specialist", "Product-Driven Organizations", "High Demand")
      ),
      isFlagshipOfficial = false,
      trackBadge = "Specialized Cohort",
      officialStatusNote = "Specialized Track • Check Batch Availability"
    ),
    CareerTrack(
      id = "cloud-devops",
      title = "Cloud Computing & DevOps",
      category = "Cloud & Infrastructure",
      shortDescription = "Architect reliable, globally scalable cloud infrastructure with automated CI/CD and container pipelines.",
      prerequisites = "Basic computer science or IT familiarity recommended",
      durationLabel = "16 - 20 Weeks",
      icon = Icons.Default.Cloud,
      about = "Master cloud systems administration, Docker containerization, Kubernetes orchestration, Infrastructure as Code, and automated deployment pipelines across AWS and cloud platforms.",
      skillsStudentsLearn = listOf("Linux Command Line", "Docker & Containers", "Kubernetes", "AWS Fundamentals", "CI/CD (GitHub Actions)", "Terraform (IaC)", "Monitoring & Logs", "Networking (VPC, DNS)"),
      roadmap = listOf(
        StepMilestone(1, "Linux & Networking Foundations", "Shell scripting, user management, SSH, DNS, and IP networking.", "Weeks 1-4"),
        StepMilestone(2, "Containerization with Docker", "Writing Dockerfiles, multi-stage builds, and Docker Compose networking.", "Weeks 5-8"),
        StepMilestone(3, "Cloud Services & CI/CD Pipelines", "AWS EC2/S3/RDS, automated GitHub Actions testing and deployments.", "Weeks 9-13"),
        StepMilestone(4, "Kubernetes & Infrastructure As Code", "Cluster deployments, auto-scaling, Terraform scripts, and mock interviews.", "Weeks 14-18")
      ),
      exampleProjects = listOf(
        ProjectIdea("Automated Multi-Environment CI/CD", "Continuous deployment pipeline running tests and deploying containers to AWS.", "Docker, GitHub Actions, AWS"),
        ProjectIdea("High-Availability Web Cluster", "Load-balanced microservices cluster with health check monitoring and alerts.", "Kubernetes, Terraform, Grafana")
      ),
      careerOpportunities = listOf(
        CareerRole("DevOps Engineer", "Cloud Consultancies, Product Firms", "High Demand"),
        CareerRole("Cloud Support Associate", "Enterprise Cloud Providers", "Consistent Hiring"),
        CareerRole("Site Reliability Engineer (SRE)", "High-Traffic SaaS Companies", "High Compensation")
      ),
      isFlagshipOfficial = false,
      trackBadge = "Specialized Cohort",
      officialStatusNote = "Specialized Track • Check Batch Availability"
    )
  )
}
