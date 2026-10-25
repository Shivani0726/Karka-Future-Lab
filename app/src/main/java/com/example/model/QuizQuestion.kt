package com.example.model

data class QuizOption(
  val text: String,
  val matchedTrackId: String,
  val optionReason: String
)

data class QuizQuestion(
  val id: Int,
  val question: String,
  val subtitle: String,
  val options: List<QuizOption>
)

data class QuizRecommendation(
  val track: CareerTrack,
  val reasonWhySuitable: String,
  val suggestedBeginnerSkills: List<String>
)

object QuizData {
  val questions: List<QuizQuestion> = listOf(
    QuizQuestion(
      id = 1,
      question = "What kind of technological creation excites you most?",
      subtitle = "Pick what you would feel proudest building from scratch:",
      options = listOf(
        QuizOption(
          text = "Building end-to-end web apps with interactive React frontends and Node databases",
          matchedTrackId = "fullstack-mern",
          optionReason = "You enjoy seeing both the visual interface and server data flow come together."
        ),
        QuizOption(
          text = "Training AI models, exploring neural algorithms, and building smart automated assistants",
          matchedTrackId = "ai-datascience",
          optionReason = "You are energized by machine intelligence, statistical reasoning, and automated decision engines."
        ),
        QuizOption(
          text = "Uncovering business trends, creating Power BI dashboards, and translating data into strategy",
          matchedTrackId = "data-analytics",
          optionReason = "You enjoy analytical problem-solving and guiding management decisions with data."
        ),
        QuizOption(
          text = "Writing automated test suites, finding edge-case software bugs, and ensuring bulletproof quality",
          matchedTrackId = "qa-automation",
          optionReason = "You have an eagle eye for detail, precision, and reliable system performance."
        ),
        QuizOption(
          text = "Crafting slick native smartphone apps with fluid animations for millions of daily users",
          matchedTrackId = "mobile-app-dev",
          optionReason = "You love touch ergonomics, mobile sensors, and apps that fit right in users' hands."
        ),
        QuizOption(
          text = "Designing clean, intuitive Figma interfaces, user research, and wireframe prototyping",
          matchedTrackId = "uiux-design",
          optionReason = "You have an eye for visual aesthetics, empathy for users, and intuitive product flows."
        )
      )
    ),
    QuizQuestion(
      id = 2,
      question = "What type of daily problem solving gives you the most energy?",
      subtitle = "Think about how you like to spend your focused project hours:",
      options = listOf(
        QuizOption(
          text = "Writing clean JavaScript/TypeScript, integrating REST APIs, and designing responsive screens",
          matchedTrackId = "fullstack-mern",
          optionReason = "Fullstack development combines creative interface polish with rigorous logic."
        ),
        QuizOption(
          text = "Cleaning datasets, training scikit-learn models, and evaluating accuracy metrics in Python",
          matchedTrackId = "ai-datascience",
          optionReason = "AI and data engineering thrive on curiosity, experimental evaluation, and pattern discovery."
        ),
        QuizOption(
          text = "Writing advanced SQL queries, calculating DAX metrics, and visualizing KPI charts",
          matchedTrackId = "data-analytics",
          optionReason = "Business analytics combines data querying with visual storytelling."
        ),
        QuizOption(
          text = "Writing Selenium/Cypress automation scripts, API contract testing, and CI/CD pipelines",
          matchedTrackId = "qa-automation",
          optionReason = "Quality engineering ensures mission-critical code never fails in production."
        ),
        QuizOption(
          text = "Building Jetpack Compose/Flutter layouts, handling local storage, and testing on real devices",
          matchedTrackId = "mobile-app-dev",
          optionReason = "Mobile engineering provides immediate, tactile feedback on physical devices."
        ),
        QuizOption(
          text = "Creating interactive prototypes in Figma and conducting user feedback interviews",
          matchedTrackId = "uiux-design",
          optionReason = "Product design requires deep user empathy and creative problem framing."
        )
      )
    ),
    QuizQuestion(
      id = 3,
      question = "What is your primary career goal over the next 6-12 months?",
      subtitle = "Choose the milestone that matters most to you:",
      options = listOf(
        QuizOption(
          text = "Becoming a versatile Full Stack Developer ready for high-demand software hiring drives",
          matchedTrackId = "fullstack-mern",
          optionReason = "Fullstack developers have the widest hiring footprint across early-stage and enterprise firms."
        ),
        QuizOption(
          text = "Entering the booming AI & Generative Data job market as an ML Associate or Data Scientist",
          matchedTrackId = "ai-datascience",
          optionReason = "AI skills are currently commanding premium compensation and rapid career acceleration."
        ),
        QuizOption(
          text = "Securing a high-growth role as a Data Analyst or Business Intelligence Specialist",
          matchedTrackId = "data-analytics",
          optionReason = "Every modern enterprise relies on data analysts to interpret customer and sales metrics."
        ),
        QuizOption(
          text = "Entering software engineering as a high-demand QA Automation Engineer / SDET",
          matchedTrackId = "qa-automation",
          optionReason = "Automation testing is one of the most accessible and high-growth entry points into tech."
        ),
        QuizOption(
          text = "Publishing native applications to Google Play and landing a specialized Mobile Engineer role",
          matchedTrackId = "mobile-app-dev",
          optionReason = "Mobile engineering provides a tangible portfolio that hiring managers can download directly."
        ),
        QuizOption(
          text = "Building a standout portfolio and joining a tech company as an Associate Product Designer",
          matchedTrackId = "uiux-design",
          optionReason = "Great designers who understand engineering constraints are exceptionally valued."
        )
      )
    )
  )

  fun computeRecommendation(answers: Map<Int, String>): QuizRecommendation {
    val counts = answers.values.groupingBy { it }.eachCount()
    val winningId = counts.maxByOrNull { it.value }?.key ?: "fullstack-mern"
    val track = CareerTrackData.allTracks.find { it.id == winningId } ?: CareerTrackData.allTracks.first()

    val reason = when (track.id) {
      "fullstack-mern" -> "Based on your preference for building complete digital products, full-stack web development allows you to craft both user interfaces and scalable database backends—making you a versatile and highly employable software engineer."
      "ai-datascience" -> "Your interest in intelligent reasoning, automated prediction, and data-driven insights matches Karka's AI & Data Science track, where you will learn to build and deploy practical machine learning and generative AI applications."
      "data-analytics" -> "Your strength in analytical thinking and business insights aligns with Data Analytics with AI, preparing you to master SQL, Power BI, and automated intelligence reporting."
      "qa-automation" -> "Your attention to detail, precision, and passion for software reliability aligns with QA Automation Testing, training you to build end-to-end automated test suites with Selenium and Cypress."
      "mobile-app-dev" -> "Your excitement for direct user interaction, responsive ergonomics, and smartphone apps aligns with Mobile Engineering, where you will develop native Android (Compose) and cross-platform apps."
      "cloud-devops" -> "Your enthusiasm for systems reliability, containerization, and automated deployments fits Cloud Computing & DevOps, training you to architect scalable planetary infrastructure."
      else -> "Your visual creativity and user empathy align with UI/UX & Product Design, equipping you to design intuitive software experiences and design systems in Figma."
    }

    val beginnerSkills = when (track.id) {
      "fullstack-mern" -> listOf("HTML/CSS fundamentals", "JavaScript ES6+ basics", "Git version control")
      "ai-datascience" -> listOf("Python basic syntax", "Data manipulation with Pandas", "Linear algebra basics")
      "data-analytics" -> listOf("SQL select & join queries", "Excel functions", "Chart visualization basics")
      "qa-automation" -> listOf("Manual testing principles", "Basic Java / JavaScript", "Bug reporting with Jira")
      "mobile-app-dev" -> listOf("Kotlin fundamentals", "Object-oriented design", "Android Studio setup")
      "cloud-devops" -> listOf("Linux command line", "Basic networking (DNS, HTTP)", "Docker basics")
      else -> listOf("Figma interface basics", "Design thinking principles", "Typography & spacing")
    }

    return QuizRecommendation(
      track = track,
      reasonWhySuitable = reason,
      suggestedBeginnerSkills = beginnerSkills
    )
  }
}
