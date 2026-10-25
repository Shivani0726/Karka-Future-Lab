package com.example.model

data class InternshipProject(
  val id: String,
  val title: String,
  val track: String,
  val problemSolved: String,
  val techStack: List<String>,
  val description: String,
  val architectureHighlights: List<String>,
  val repoOrDemoStatus: String = "Curriculum Capstone Blueprint"
)

object InternshipProjectData {
  const val sectionTitle = "Capstone & Portfolio Projects"
  const val sectionSubtitle = "Curriculum Project Blueprints: Production-grade problem statements and software architectures developed during cohort training to build job-ready portfolios."

  val projects = listOf(
    InternshipProject(
      id = "project-1",
      title = "AI-Powered Resume Matcher & Career Fit Engine",
      track = "AI, Machine Learning & Data Science",
      problemSolved = "Helps college placement cells automatically parse bulk student resumes against corporate job descriptions to identify skill gaps.",
      techStack = listOf("Python", "LangChain", "FastAPI", "Pandas", "Streamlit", "ChromaDB"),
      description = "An automated NLP pipeline that extracts candidate technical proficiencies, generates contextual match scores, and provides specific recommendations for missing prerequisite tools.",
      architectureHighlights = listOf("Cosine similarity vector search", "FastAPI microservice endpoints", "Interactive candidate score dashboard"),
      repoOrDemoStatus = "Curriculum Capstone Blueprint"
    ),
    InternshipProject(
      id = "project-2",
      title = "Collegiate Placement Drive Management Platform",
      track = "Full Stack Web (MERN)",
      problemSolved = "Eliminates fragmented spreadsheets during on-campus hiring by coordinating candidate rounds, shortlists, and interview feedback.",
      techStack = listOf("React.js", "Node.js", "Express", "MongoDB", "Tailwind CSS", "JWT Auth"),
      description = "Fullstack multi-tenant web application featuring role-based dashboards for college admins, hiring company coordinators, and applicants with real-time status alerts.",
      architectureHighlights = listOf("JWT secure role-based access control", "Responsive Tailwind UI for desktop & mobile", "RESTful schema design with Mongoose"),
      repoOrDemoStatus = "Curriculum Capstone Blueprint"
    ),
    InternshipProject(
      id = "project-3",
      title = "Automated E-Commerce End-to-End Test Suite",
      track = "QA Automation Testing",
      problemSolved = "Prevents revenue leakage by executing automated regression and checkout verification before production releases.",
      techStack = listOf("Cypress", "JavaScript", "Postman", "Newman", "GitHub Actions", "Jira"),
      description = "End-to-end automated testing framework covering critical checkout workflows, coupon edge-case validations, and scheduled API health monitors.",
      architectureHighlights = listOf("Page Object Model architectural pattern", "Automated GitHub Actions CI/CD trigger", "Detailed HTML test execution reports"),
      repoOrDemoStatus = "Curriculum Capstone Blueprint"
    ),
    InternshipProject(
      id = "project-4",
      title = "Retail Performance & Customer Intelligence Dashboard",
      track = "Data Analytics with AI",
      problemSolved = "Consolidates multi-channel sales records into real-time KPI metrics and automated cohort retention analytics.",
      techStack = listOf("SQL", "Power BI", "DAX", "Python", "Pandas", "Excel"),
      description = "Interactive business intelligence reporting suite analyzing customer acquisition costs, gross margins, and regional sales performance trends.",
      architectureHighlights = listOf("Star schema dimensional modeling in SQL", "Advanced DAX time-intelligence metrics", "Automated executive summary generation"),
      repoOrDemoStatus = "Curriculum Capstone Blueprint"
    ),
    InternshipProject(
      id = "project-5",
      title = "Healthcare Patient Portal & Design System Case Study",
      track = "UI/UX & Product Design",
      problemSolved = "Simplifies medical appointments and health record access through an accessibility-first design thinking process.",
      techStack = listOf("Figma", "Design Tokens", "Wireframing", "User Research", "Usability Testing"),
      description = "Comprehensive two-part design case study covering user persona mapping, accessible color contrast hierarchies, and interactive appointment booking prototypes.",
      architectureHighlights = listOf("WCAG 2.1 AA accessible typography & contrast", "Modular design system tokens in Figma", "Tested with 12 user interview sessions"),
      repoOrDemoStatus = "Curriculum Portfolio Case Study"
    ),
    InternshipProject(
      id = "project-6",
      title = "Offline-First Campus Transit & Telemetry App",
      track = "Mobile App Development",
      problemSolved = "Provides university students with live shuttle bus arrivals, crowd density estimates, and campus route navigation.",
      techStack = listOf("Kotlin", "Jetpack Compose", "Room Database", "Retrofit", "Google Maps SDK"),
      description = "Native Android application built with Jetpack Compose. Utilizes offline-first Room caching so students can view route timetables even with spotty campus Wi-Fi.",
      architectureHighlights = listOf("MVI architecture with Kotlin Coroutines & Flow", "Offline-first local Room synchronization", "Clean Material Design 3 guidelines"),
      repoOrDemoStatus = "Curriculum Capstone Blueprint"
    )
  )

  const val projectsDisclaimer = "Notice: The projects listed above are representative capstone problem statements and portfolio blueprints developed during curriculum training to mirror modern software industry workflows. They are educational project frameworks, not claimed commercial client contracts. Explore verified live student repositories during admissions counseling."
}
