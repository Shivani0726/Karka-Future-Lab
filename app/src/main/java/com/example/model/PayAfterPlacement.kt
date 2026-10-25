package com.example.model

data class PapStep(
  val stepNumber: Int,
  val title: String,
  val description: String,
  val highlightBadge: String
)

data class PapEligibilityCriteria(
  val title: String,
  val description: String
)

object PayAfterPlacementData {
  const val overviewHeadline = "Learn with ₹0 Upfront Tuition. Pay After Placement."
  const val overviewDescription = "At Karka AI & Tech Academy, our Pay After Placement (Learn Till Job) model allows eligible candidates to undergo intensive tech training without heavy upfront fees. You focus on mastering in-demand engineering tracks and building practical portfolio projects—your course fee becomes payable only after you secure a qualifying tech job."

  val howItWorksSteps = listOf(
    PapStep(
      stepNumber = 1,
      title = "Apply & Screening Assessment",
      description = "Submit your application, complete a baseline aptitude assessment, and speak with academic counselors to select an aligned career track.",
      highlightBadge = "₹0 Upfront Tuition"
    ),
    PapStep(
      stepNumber = 2,
      title = "AI-First Hands-on Training",
      description = "Learn full-time or cohort-based with daily coding drills, generative AI workflows, and live mentor code reviews.",
      highlightBadge = "AI-First Curriculum"
    ),
    PapStep(
      stepNumber = 3,
      title = "Capstone & Portfolio Projects",
      description = "Build practical production-standard projects that demonstrate verified engineering competence to technical interviewers.",
      highlightBadge = "Portfolio Building"
    ),
    PapStep(
      stepNumber = 4,
      title = "Dedicated Placement Assistance",
      description = "1-on-1 resume optimization, algorithmic problem-solving drills, mock technical interviews, and participation in hiring drives.",
      highlightBadge = "Placement Support"
    ),
    PapStep(
      stepNumber = 5,
      title = "Course Fee Repayment Post-Hiring",
      description = "Upon receiving and accepting a qualifying tech job offer meeting the agreed threshold, commence transparent monthly installments from your salary.",
      highlightBadge = "Career Aligned"
    )
  )

  val eligibilityList = listOf(
    PapEligibilityCriteria(
      title = "Final Year Students & Recent Graduates",
      description = "Students completing or recently graduated from B.E./B.Tech, B.Sc, BCA, MCA, or related degree programs seeking high-growth software roles."
    ),
    PapEligibilityCriteria(
      title = "Motivated Career Switchers",
      description = "Professionals from non-tech or support backgrounds committed to transitioning into software development and data roles."
    ),
    PapEligibilityCriteria(
      title = "Commitment to Daily Participation",
      description = "Readiness to dedicate required daily hours to practical assignments, peer reviews, and live mentor workshops."
    ),
    PapEligibilityCriteria(
      title = "Baseline Aptitude & Screening",
      description = "Successful completion of the preliminary Karka Academy aptitude screening and orientation counseling interview."
    )
  )

  val keyTerms = listOf(
    "Zero upfront tuition fees during the active training period for eligible candidates.",
    "Course fee repayment commences only after you successfully secure and join a qualifying tech job.",
    "Repayment is structured into manageable, transparent monthly installments from your income.",
    "No unconditional placement guarantee: Career outcomes depend on candidate diligence, attendance, code submissions, and hiring drives.",
    "Specific agreement conditions, fee structures, and qualifying salary thresholds are detailed in the formal Karka Academy enrollment contract."
  )

  const val termsDisclaimer = "Official Notice: Specific agreement terms, fee structures, repayment schedules, and candidate eligibility criteria are governed exclusively by the formal Karka Academy candidate enrollment agreement. To review the complete verified terms and conditions, please consult the admissions office directly via karka.academy or WhatsApp."
}
