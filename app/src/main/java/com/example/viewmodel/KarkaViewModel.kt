package com.example.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.model.CareerTrack
import com.example.model.CareerTrackData
import com.example.model.QuizData
import com.example.model.QuizRecommendation
import com.example.model.Section
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class ValidatedEnquiryDraft(
  val id: String = java.util.UUID.randomUUID().toString(),
  val fullName: String,
  val email: String,
  val phoneNumber: String,
  val interestedTrack: String,
  val isPapInterest: Boolean,
  val message: String,
  val timestamp: String = java.text.SimpleDateFormat("dd MMM yyyy, hh:mm a", java.util.Locale.getDefault()).format(java.util.Date())
) {
  fun toFormattedText(): String {
    return """
      *Karka AI & Tech Academy - Enrolment Enquiry*
      • Candidate: $fullName
      • Email: $email
      • Phone: $phoneNumber
      • Interested Track: $interestedTrack
      • Pay After Placement Interest: ${if (isPapInterest) "Yes" else "No"}
      • Message / Goals:
      $message
      
      (Generated from Karka Academy App - karka.academy)
    """.trimIndent()
  }
}

data class ContactFormState(
  val fullName: String = "",
  val email: String = "",
  val phoneNumber: String = "",
  val interestedTrack: String = "Full Stack Web Development (MERN)",
  val isPapInterest: Boolean = false,
  val message: String = "",
  val nameError: String? = null,
  val emailError: String? = null,
  val phoneError: String? = null,
  val trackError: String? = null,
  val messageError: String? = null,
  val isValidating: Boolean = false,
  val isValidatedLocally: Boolean = false,
  val activeDraft: ValidatedEnquiryDraft? = null,
  val localEnquiriesHistory: List<ValidatedEnquiryDraft> = emptyList()
)

data class KarkaUiState(
  val currentSection: Section = Section.HOME,
  val selectedTrackForDetail: CareerTrack? = null,
  val isQuizOpen: Boolean = false,
  val quizStep: Int = 0,
  val quizAnswers: Map<Int, String> = emptyMap(),
  val quizRecommendation: QuizRecommendation? = null,
  val bookmarkedTrackIds: Set<String> = emptySet(),
  val expandedFaqIds: Set<Int> = setOf(1, 4), // open intro and PAP by default
  val activeTrackFilter: String = "All",
  val trackSearchQuery: String = "",
  val contactForm: ContactFormState = ContactFormState(),
  val showSavedDrawer: Boolean = false
)

class KarkaViewModel : ViewModel() {

  private val _uiState = MutableStateFlow(KarkaUiState())
  val uiState: StateFlow<KarkaUiState> = _uiState.asStateFlow()

  fun onSectionSelected(section: Section) {
    _uiState.update { it.copy(currentSection = section) }
  }

  fun openTrackDetail(track: CareerTrack) {
    _uiState.update { it.copy(selectedTrackForDetail = track) }
  }

  fun closeTrackDetail() {
    _uiState.update { it.copy(selectedTrackForDetail = null) }
  }

  fun toggleBookmark(trackId: String) {
    _uiState.update { state ->
      val newBookmarks = state.bookmarkedTrackIds.toMutableSet()
      if (newBookmarks.contains(trackId)) {
        newBookmarks.remove(trackId)
      } else {
        newBookmarks.add(trackId)
      }
      state.copy(bookmarkedTrackIds = newBookmarks)
    }
  }

  fun toggleFaq(faqId: Int) {
    _uiState.update { state ->
      val newExpanded = state.expandedFaqIds.toMutableSet()
      if (newExpanded.contains(faqId)) {
        newExpanded.remove(faqId)
      } else {
        newExpanded.add(faqId)
      }
      state.copy(expandedFaqIds = newExpanded)
    }
  }

  fun setTrackFilter(filter: String) {
    _uiState.update { it.copy(activeTrackFilter = filter) }
  }

  fun setTrackSearch(query: String) {
    _uiState.update { it.copy(trackSearchQuery = query) }
  }

  // Pre-fill Enrolment for a specific track and jump to Contact
  fun enquireAboutTrack(trackTitle: String) {
    _uiState.update {
      it.copy(
        currentSection = Section.CONTACT,
        contactForm = it.contactForm.copy(
          interestedTrack = trackTitle,
          trackError = null,
          message = "Hello Karka Academy admissions team, I am interested in inquiring about the '$trackTitle' cohort. Please share the syllabus and upcoming batch dates.",
          messageError = null,
          isValidatedLocally = false,
          activeDraft = null
        )
      )
    }
  }

  // Pre-fill Enrolment specifically for Pay After Placement
  fun enquireAboutPap(trackTitle: String = "Full Stack Web Development (MERN)") {
    _uiState.update {
      it.copy(
        currentSection = Section.CONTACT,
        contactForm = it.contactForm.copy(
          interestedTrack = trackTitle,
          isPapInterest = true,
          trackError = null,
          message = "Hi Karka Academy admissions, I would like to check my eligibility for the Pay After Placement model for the $trackTitle track. Please advise on the agreement and screening steps.",
          messageError = null,
          isValidatedLocally = false,
          activeDraft = null
        )
      )
    }
  }

  // Quiz logic
  fun startQuiz() {
    _uiState.update {
      it.copy(
        isQuizOpen = true,
        quizStep = 0,
        quizAnswers = emptyMap(),
        quizRecommendation = null
      )
    }
  }

  fun answerQuizQuestion(questionId: Int, trackId: String) {
    _uiState.update { state ->
      val answers = state.quizAnswers + (questionId to trackId)
      val nextStep = state.quizStep + 1
      if (nextStep >= QuizData.questions.size) {
        val rec = QuizData.computeRecommendation(answers)
        state.copy(
          quizAnswers = answers,
          quizStep = nextStep,
          quizRecommendation = rec
        )
      } else {
        state.copy(
          quizAnswers = answers,
          quizStep = nextStep
        )
      }
    }
  }

  fun closeQuiz() {
    _uiState.update { it.copy(isQuizOpen = false) }
  }

  // Contact / Enquiry Form logic with robust validation
  fun updateContactName(name: String) {
    _uiState.update {
      it.copy(
        contactForm = it.contactForm.copy(
          fullName = name,
          nameError = if (name.trim().length >= 2) null else it.contactForm.nameError
        )
      )
    }
  }

  fun updateContactEmail(email: String) {
    val emailPattern = "^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$"
    _uiState.update {
      it.copy(
        contactForm = it.contactForm.copy(
          email = email,
          emailError = if (email.trim().matches(Regex(emailPattern))) null else it.contactForm.emailError
        )
      )
    }
  }

  fun updateContactPhone(phone: String) {
    val cleaned = phone.trim().replace(Regex("[\\s\\-\\(\\)]"), "")
    val indianPhonePattern = "^(?:\\+91|91|0)?[6-9]\\d{9}$"
    _uiState.update {
      it.copy(
        contactForm = it.contactForm.copy(
          phoneNumber = phone,
          phoneError = if (cleaned.matches(Regex(indianPhonePattern))) null else it.contactForm.phoneError
        )
      )
    }
  }

  fun updateContactTrack(track: String) {
    _uiState.update {
      it.copy(
        contactForm = it.contactForm.copy(
          interestedTrack = track,
          trackError = if (track.isNotBlank()) null else it.contactForm.trackError
        )
      )
    }
  }

  fun updateContactPapInterest(isPap: Boolean) {
    _uiState.update {
      it.copy(contactForm = it.contactForm.copy(isPapInterest = isPap))
    }
  }

  fun updateContactMessage(message: String) {
    val capped = if (message.length > 500) message.take(500) else message
    _uiState.update {
      it.copy(
        contactForm = it.contactForm.copy(
          message = capped,
          messageError = if (capped.trim().length >= 10 && capped.length <= 500) null else it.contactForm.messageError
        )
      )
    }
  }

  fun submitContactForm() {
    val form = _uiState.value.contactForm
    var hasError = false
    var nameErr: String? = null
    var emailErr: String? = null
    var phoneErr: String? = null
    var trackErr: String? = null
    var msgErr: String? = null

    // 1. Full Name: Required, min 2 characters
    val trimmedName = form.fullName.trim()
    if (trimmedName.isEmpty()) {
      nameErr = "Full Name is required"
      hasError = true
    } else if (trimmedName.length < 2) {
      nameErr = "Full Name must be at least 2 characters"
      hasError = true
    }

    // 2. Email Address: Required, valid email format
    val trimmedEmail = form.email.trim()
    val emailPattern = "^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$"
    if (trimmedEmail.isEmpty()) {
      emailErr = "Email Address is required"
      hasError = true
    } else if (!trimmedEmail.matches(Regex(emailPattern))) {
      emailErr = "Please enter a valid email address (e.g. name@domain.com)"
      hasError = true
    }

    // 3. Mobile Phone Number: Required, valid Indian mobile number format
    val cleanedPhone = form.phoneNumber.trim().replace(Regex("[\\s\\-\\(\\)]"), "")
    val indianPhonePattern = "^(?:\\+91|91|0)?[6-9]\\d{9}$"
    if (cleanedPhone.isEmpty()) {
      phoneErr = "Mobile Phone Number is required"
      hasError = true
    } else if (!cleanedPhone.matches(Regex(indianPhonePattern))) {
      phoneErr = "Please enter a valid 10-digit Indian mobile number (e.g. 9876543210 or +91 9876543210)"
      hasError = true
    }

    // 4. Interested Career Track: Required
    if (form.interestedTrack.trim().isEmpty()) {
      trackErr = "Interested Career Track is required"
      hasError = true
    }

    // 5. Message or Career Goals: Required, 10 to 500 characters
    val trimmedMessage = form.message.trim()
    if (trimmedMessage.isEmpty()) {
      msgErr = "Message or career goals is required"
      hasError = true
    } else if (trimmedMessage.length < 10) {
      msgErr = "Message must contain at least 10 characters (currently ${trimmedMessage.length})"
      hasError = true
    } else if (form.message.length > 500) {
      msgErr = "Message cannot exceed 500 characters (currently ${form.message.length})"
      hasError = true
    }

    // If validation fails, strictly prevent submission and NEVER display any success banner
    if (hasError) {
      _uiState.update {
        it.copy(
          contactForm = it.contactForm.copy(
            nameError = nameErr,
            emailError = emailErr,
            phoneError = phoneErr,
            trackError = trackErr,
            messageError = msgErr,
            isValidating = false,
            isValidatedLocally = false,
            activeDraft = null
          )
        )
      }
      return
    }

    // Genuine validation verification:
    // Does NOT claim the student is enrolled, and does NOT claim a remote server received it.
    // Prepares a validated draft and saves it to local device history.
    viewModelScope.launch {
      _uiState.update {
        it.copy(
          contactForm = it.contactForm.copy(
            isValidating = true,
            nameError = null,
            emailError = null,
            phoneError = null,
            trackError = null,
            messageError = null,
            isValidatedLocally = false,
            activeDraft = null
          )
        )
      }
      delay(400)
      val newDraft = ValidatedEnquiryDraft(
        fullName = trimmedName,
        email = trimmedEmail,
        phoneNumber = cleanedPhone,
        interestedTrack = form.interestedTrack,
        isPapInterest = form.isPapInterest,
        message = trimmedMessage
      )

      _uiState.update { state ->
        val updatedHistory = listOf(newDraft) + state.contactForm.localEnquiriesHistory.filter { it.email != newDraft.email || it.interestedTrack != newDraft.interestedTrack }
        state.copy(
          contactForm = state.contactForm.copy(
            isValidating = false,
            isValidatedLocally = true,
            activeDraft = newDraft,
            localEnquiriesHistory = updatedHistory
          )
        )
      }
    }
  }

  fun dismissValidationNotice() {
    _uiState.update {
      it.copy(
        contactForm = it.contactForm.copy(
          isValidatedLocally = false,
          activeDraft = null
        )
      )
    }
  }

  fun deleteLocalDraft(draftId: String) {
    _uiState.update { state ->
      val updated = state.contactForm.localEnquiriesHistory.filterNot { it.id == draftId }
      val currentActive = if (state.contactForm.activeDraft?.id == draftId) null else state.contactForm.activeDraft
      state.copy(
        contactForm = state.contactForm.copy(
          localEnquiriesHistory = updated,
          activeDraft = currentActive,
          isValidatedLocally = currentActive != null
        )
      )
    }
  }

  fun resetContactForm() {
    _uiState.update {
      it.copy(
        contactForm = it.contactForm.copy(
          fullName = "",
          email = "",
          phoneNumber = "",
          interestedTrack = "Full Stack Web Development (MERN)",
          isPapInterest = false,
          message = "",
          nameError = null,
          emailError = null,
          phoneError = null,
          trackError = null,
          messageError = null,
          isValidating = false,
          isValidatedLocally = false,
          activeDraft = null
        )
      )
    }
  }

  fun toggleSavedDrawer(show: Boolean) {
    _uiState.update { it.copy(showSavedDrawer = show) }
  }
}
