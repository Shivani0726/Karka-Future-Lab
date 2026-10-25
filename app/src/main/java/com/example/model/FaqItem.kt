package com.example.model

data class FaqItem(
  val id: Int,
  val question: String,
  val answer: String
)

object FaqData {
  val questions: List<FaqItem> = listOf(
    FaqItem(
      id = 1,
      question = "What is Karka AI & Tech Academy?",
      answer = "Karka AI & Tech Academy (karka.academy, Instagram @karka.ai) is a modern technology career accelerator designed for college students, recent graduates, and career switchers. We provide intensive, cohort-based engineering training in full-stack web, AI/ML, data analytics, QA automation, mobile apps, and product design with an emphasis on practical portfolio projects and placement support."
    ),
    FaqItem(
      id = 2,
      question = "What career tracks are offered at Karka Academy?",
      answer = "Karka Academy's core programs include Full Stack Web Development (MERN), AI & Data Science (with GenAI & LLM workflows), Data Analytics with AI, QA Automation Testing, and UI/UX Product Design, along with specialized electives in Mobile and Cloud. All tracks are regularly updated to reflect current engineering hiring benchmarks."
    ),
    FaqItem(
      id = 3,
      question = "Is the academy suitable for complete beginners?",
      answer = "Yes! All foundational tracks start with zero-assumption introductory modules covering core programming concepts, logical thinking, and computer science basics before moving into advanced frameworks. Mentors provide 1-on-1 code reviews and support throughout."
    ),
    FaqItem(
      id = 4,
      question = "How does the Pay After Placement model work?",
      answer = "Under Pay After Placement (Learn Till Job), eligible candidates enroll and complete their intensive training with ₹0 upfront tuition fees. Course fee repayment begins only after you secure and join a qualifying tech job. Specific agreement clauses, fee structures, and qualifying salary thresholds are detailed in the formal Karka Academy enrollment contract."
    ),
    FaqItem(
      id = 5,
      question = "How can students enquire or apply for an upcoming cohort?",
      answer = "You can fill out the enquiry form directly in this application, connect via Instagram @karka.ai, or visit karka.academy. An admissions counselor will reach out to understand your academic background, schedule a baseline screening, and guide you through available cohort batches."
    ),
    FaqItem(
      id = 6,
      question = "What kind of practical projects will I build?",
      answer = "Students build production-grade capstone applications and portfolio projects rather than basic tutorial clones. Examples include AI resume analysis pipelines, multi-tenant placement drive portals, end-to-end automated test suites, and interactive data analytics dashboards to showcase on your GitHub and resume."
    )
  )
}
