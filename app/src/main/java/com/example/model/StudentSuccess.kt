package com.example.model

data class PlacementStory(
  val id: String,
  val candidateLabel: String,
  val trackCompleted: String,
  val placedRole: String,
  val hiringDomain: String,
  val batchInfo: String,
  val storySnippet: String,
  val isVerifiedAlumni: Boolean = false
)

object StudentSuccessData {
  const val sectionTitle = "Career Pathways & Placement Roles"
  const val sectionSubtitle = "Representative graduate career trajectories, core competencies, and targeted industry hiring domains."

  const val transparencyDisclaimer = "Transparency & Verification Notice: Individual student names, testimonials, and company placement records are not published here to protect alumni privacy and uphold factual accuracy. For verified alumni spotlights, video interviews, and current batch hiring updates, please visit official Karka Academy channels at karka.academy and on Instagram @karka.ai."

  val placementStories = listOf(
    PlacementStory(
      id = "pathway-1",
      candidateLabel = "Representative Graduate Profile",
      trackCompleted = "Full Stack Web Development (MERN)",
      placedRole = "Full Stack Web Developer",
      hiringDomain = "Enterprise SaaS & Cloud Platforms",
      batchInfo = "Curriculum Target Pathway",
      storySnippet = "Prepares candidates through intensive MERN coding sprints, RESTful API design, database schemas, and building 3 portfolio web applications to crack technical engineering interviews."
    ),
    PlacementStory(
      id = "pathway-2",
      candidateLabel = "Representative Graduate Profile",
      trackCompleted = "AI, Machine Learning & Data Science",
      placedRole = "Associate AI & Data Engineer",
      hiringDomain = "FinTech, HealthTech & Predictive Analytics",
      batchInfo = "Curriculum Target Pathway",
      storySnippet = "Equips learners with Python, Pandas, statistical modeling, prompt engineering, and LLM orchestration to qualify for technical roles in modern data and AI product teams."
    ),
    PlacementStory(
      id = "pathway-3",
      candidateLabel = "Representative Graduate Profile",
      trackCompleted = "Data Analytics with AI",
      placedRole = "Business Intelligence & Data Analyst",
      hiringDomain = "E-Commerce, Banking & Logistics",
      batchInfo = "Curriculum Target Pathway",
      storySnippet = "Focuses on complex SQL querying, Power BI interactive reporting, automated data extraction pipelines, and delivering executive business intelligence summaries."
    ),
    PlacementStory(
      id = "pathway-4",
      candidateLabel = "Representative Graduate Profile",
      trackCompleted = "QA Automation Testing",
      placedRole = "QA Automation Engineer / SDET",
      hiringDomain = "Software Product Companies & IT Services",
      batchInfo = "Curriculum Target Pathway",
      storySnippet = "Trains candidates in robust test automation frameworks using Selenium, Cypress, Postman API validation, and automated GitHub Actions test integration."
    ),
    PlacementStory(
      id = "pathway-5",
      candidateLabel = "Representative Graduate Profile",
      trackCompleted = "UI/UX & Product Design",
      placedRole = "Associate Product Designer",
      hiringDomain = "Digital Product Studios & Tech Startups",
      batchInfo = "Curriculum Target Pathway",
      storySnippet = "Covers user research methodologies, Figma design systems, wireframing, high-fidelity interactive prototyping, and building two comprehensive portfolio case studies."
    ),
    PlacementStory(
      id = "pathway-6",
      candidateLabel = "Representative Graduate Profile",
      trackCompleted = "Mobile App Development",
      placedRole = "Android & Mobile Developer",
      hiringDomain = "Consumer Tech & Mobility Startups",
      batchInfo = "Curriculum Target Pathway",
      storySnippet = "Develops expertise in Kotlin, Jetpack Compose declarative UI, coroutines, offline Room caching, and deploying applications to the Google Play Store."
    )
  )
}
