package com.example

import com.example.viewmodel.KarkaViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class ExampleUnitTest {

  private val testDispatcher = StandardTestDispatcher()

  @Before
  fun setUp() {
    Dispatchers.setMain(testDispatcher)
  }

  @After
  fun tearDown() {
    Dispatchers.resetMain()
  }

  @Test
  fun `empty form submission fails validation on required fields and never shows success`() {
    val viewModel = KarkaViewModel()
    viewModel.resetContactForm()
    viewModel.updateContactTrack("") // clear track as well

    viewModel.submitContactForm()

    val state = viewModel.uiState.value.contactForm
    assertFalse("Validated flag must remain false", state.isValidatedLocally)
    assertNull("Active draft must be null", state.activeDraft)
    assertEquals("Full Name is required", state.nameError)
    assertEquals("Email Address is required", state.emailError)
    assertEquals("Mobile Phone Number is required", state.phoneError)
    assertEquals("Interested Career Track is required", state.trackError)
    assertEquals("Message or career goals is required", state.messageError)
  }

  @Test
  fun `invalid email formats trigger error`() {
    val viewModel = KarkaViewModel()
    viewModel.updateContactName("Priya Sharma")
    viewModel.updateContactPhone("9876543210")
    viewModel.updateContactTrack("AI & Machine Learning")
    viewModel.updateContactMessage("I want to learn AI and build production models.")

    // Test invalid email
    viewModel.updateContactEmail("invalid-email-format")
    viewModel.submitContactForm()

    val state = viewModel.uiState.value.contactForm
    assertFalse(state.isValidatedLocally)
    assertNull(state.activeDraft)
    assertEquals("Please enter a valid email address (e.g. name@domain.com)", state.emailError)
  }

  @Test
  fun `invalid Indian phone numbers trigger error while valid formats pass`() {
    val viewModel = KarkaViewModel()
    viewModel.updateContactName("Rohan Verma")
    viewModel.updateContactEmail("rohan@example.com")
    viewModel.updateContactTrack("Full Stack Web Development (MERN)")
    viewModel.updateContactMessage("Interested in MERN stack cohort and placement.")

    // Invalid: begins with 1
    viewModel.updateContactPhone("1234567890")
    viewModel.submitContactForm()
    var state = viewModel.uiState.value.contactForm
    assertFalse(state.isValidatedLocally)
    assertEquals("Please enter a valid 10-digit Indian mobile number (e.g. 9876543210 or +91 9876543210)", state.phoneError)

    // Invalid: too short
    viewModel.updateContactPhone("98765")
    viewModel.submitContactForm()
    state = viewModel.uiState.value.contactForm
    assertFalse(state.isValidatedLocally)
    assertEquals("Please enter a valid 10-digit Indian mobile number (e.g. 9876543210 or +91 9876543210)", state.phoneError)

    // Valid: 10-digit starting with 9
    viewModel.updateContactPhone("9876543210")
    assertNull(viewModel.uiState.value.contactForm.phoneError)

    // Valid: +91 with space
    viewModel.updateContactPhone("+91 9876543210")
    assertNull(viewModel.uiState.value.contactForm.phoneError)
  }

  @Test
  fun `message field enforces minimum 10 chars and caps at 500 chars`() {
    val viewModel = KarkaViewModel()
    viewModel.updateContactName("Kavita Nair")
    viewModel.updateContactEmail("kavita@domain.com")
    viewModel.updateContactPhone("8765432109")
    viewModel.updateContactTrack("Mobile App Development (Kotlin & Compose)")

    // Too short (under 10 chars)
    viewModel.updateContactMessage("Hi there")
    viewModel.submitContactForm()
    var state = viewModel.uiState.value.contactForm
    assertFalse(state.isValidatedLocally)
    assertTrue(state.messageError?.contains("at least 10 characters") == true)

    // Exceeding 500 chars is capped automatically by input handler
    val longString = "A".repeat(550)
    viewModel.updateContactMessage(longString)
    assertEquals(500, viewModel.uiState.value.contactForm.message.length)
  }

  @Test
  fun `valid form succeeds locally and prepares direct draft without claiming enrollment`() = runTest {
    val viewModel = KarkaViewModel()
    viewModel.updateContactName("Ananya Sundaram")
    viewModel.updateContactEmail("ananya@gmail.com")
    viewModel.updateContactPhone("+91 9123456789")
    viewModel.updateContactTrack("Data Engineering & Cloud (AWS / GCP)")
    viewModel.updateContactPapInterest(true)
    viewModel.updateContactMessage("Looking for career guidance regarding data engineering and Pay After Placement.")

    viewModel.submitContactForm()
    advanceUntilIdle()

    val state = viewModel.uiState.value.contactForm
    assertTrue("Should be successfully validated locally", state.isValidatedLocally)
    assertNull(state.nameError)
    assertNull(state.emailError)
    assertNull(state.phoneError)
    assertNull(state.trackError)
    assertNull(state.messageError)

    val draft = state.activeDraft
    assertNotNull("Draft should be created", draft)
    assertEquals("Ananya Sundaram", draft!!.fullName)
    assertEquals("ananya@gmail.com", draft.email)
    assertEquals("+919123456789", draft.phoneNumber)
    assertEquals("Data Engineering & Cloud (AWS / GCP)", draft.interestedTrack)
    assertTrue(draft.isPapInterest)
    assertTrue("Must be saved to local history", state.localEnquiriesHistory.contains(draft))

    val formatted = draft.toFormattedText()
    assertTrue(formatted.contains("Ananya Sundaram"))
    assertTrue(formatted.contains("Data Engineering & Cloud (AWS / GCP)"))
    assertFalse("Must never claim student is enrolled", formatted.contains("Successfully Enrolled") || formatted.contains("You are enrolled"))
  }
}
