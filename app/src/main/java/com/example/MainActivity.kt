package com.example

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.model.Section
import com.example.ui.components.AboutSection
import com.example.ui.components.CareerDetailDialog
import com.example.ui.components.CareerQuizDialog
import com.example.ui.components.CareerSection
import com.example.ui.components.ContactSection
import com.example.ui.components.FaqSection
import com.example.ui.components.FooterSection
import com.example.ui.components.HeroSection
import com.example.ui.components.KarkaNavBar
import com.example.ui.components.KarkaNavDrawerContent
import com.example.ui.components.PayAfterPlacementSection
import com.example.ui.components.ProjectsSection
import com.example.ui.components.SavedCareersDrawer
import com.example.ui.components.StudentSuccessSection
import com.example.ui.theme.KarkaTheme
import com.example.ui.theme.NavyDark
import com.example.ui.theme.NavyPrimary
import com.example.ui.theme.PureWhite
import com.example.ui.theme.SlateBackground
import com.example.ui.theme.TealDark
import com.example.ui.theme.TealPrimary
import com.example.viewmodel.KarkaViewModel
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    enableEdgeToEdge()
    setContent {
      KarkaTheme {
        KarkaAcademyApp()
      }
    }
  }
}

@Composable
fun KarkaAcademyApp(
  viewModel: KarkaViewModel = viewModel()
) {
  val uiState by viewModel.uiState.collectAsStateWithLifecycle()
  val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
  val listState = rememberLazyListState()
  val scope = rememberCoroutineScope()

  // Track if scrolled past hero to display Floating Back to Top Button
  val showBackToTop by remember {
    derivedStateOf { listState.firstVisibleItemIndex > 0 || listState.firstVisibleItemScrollOffset > 300 }
  }

  // Handle system back button
  BackHandler(enabled = drawerState.isOpen || uiState.isQuizOpen || uiState.selectedTrackForDetail != null || uiState.showSavedDrawer) {
    when {
      drawerState.isOpen -> scope.launch { drawerState.close() }
      uiState.isQuizOpen -> viewModel.closeQuiz()
      uiState.selectedTrackForDetail != null -> viewModel.closeTrackDetail()
      uiState.showSavedDrawer -> viewModel.toggleSavedDrawer(false)
    }
  }

  ModalNavigationDrawer(
    drawerState = drawerState,
    drawerContent = {
      KarkaNavDrawerContent(
        currentSection = uiState.currentSection,
        onSectionClick = { section ->
          viewModel.onSectionSelected(section)
          scope.launch {
            listState.animateScrollToItem(section.index)
          }
        },
        onEnquireClick = {
          viewModel.onSectionSelected(Section.CONTACT)
          scope.launch {
            listState.animateScrollToItem(Section.CONTACT.index)
          }
        },
        onCloseDrawer = {
          scope.launch { drawerState.close() }
        }
      )
    }
  ) {
    Scaffold(
      modifier = Modifier
        .fillMaxSize()
        .background(SlateBackground)
        .statusBarsPadding(),
      topBar = {
        KarkaNavBar(
          currentSection = uiState.currentSection,
          onSectionClick = { section ->
            viewModel.onSectionSelected(section)
            scope.launch {
              listState.animateScrollToItem(section.index)
            }
          },
          onEnquireNowClick = {
            viewModel.onSectionSelected(Section.CONTACT)
            scope.launch {
              listState.animateScrollToItem(Section.CONTACT.index)
            }
          },
          onOpenMenuClick = {
            scope.launch { drawerState.open() }
          },
          savedCount = uiState.bookmarkedTrackIds.size,
          onOpenSavedClick = {
            viewModel.toggleSavedDrawer(true)
          }
        )
      },
      floatingActionButton = {
        AnimatedVisibility(
          visible = showBackToTop,
          enter = fadeIn(),
          exit = fadeOut()
        ) {
          FloatingActionButton(
            onClick = {
              scope.launch {
                listState.animateScrollToItem(0)
                viewModel.onSectionSelected(Section.HOME)
              }
            },
            containerColor = TealPrimary,
            contentColor = NavyDark,
            shape = CircleShape,
            elevation = FloatingActionButtonDefaults.elevation(defaultElevation = 4.dp),
            modifier = Modifier
              .padding(bottom = 16.dp)
              .navigationBarsPadding()
              .testTag("floating_back_to_top_button")
          ) {
            Icon(
              imageVector = Icons.Default.KeyboardArrowUp,
              contentDescription = "Scroll to top",
              modifier = Modifier.size(24.dp),
              tint = NavyDark
            )
          }
        }
      }
    ) { innerPadding ->
      LazyColumn(
        state = listState,
        modifier = Modifier
          .fillMaxSize()
          .padding(innerPadding)
          .navigationBarsPadding()
      ) {
        // Section 0: Home / Hero
        item(key = "section_home") {
          HeroSection(
            onExploreTracksClick = {
              viewModel.onSectionSelected(Section.CAREER_TRACKS)
              scope.launch {
                listState.animateScrollToItem(Section.CAREER_TRACKS.index)
              }
            },
            onExplorePapClick = {
              viewModel.onSectionSelected(Section.PAY_AFTER_PLACEMENT)
              scope.launch {
                listState.animateScrollToItem(Section.PAY_AFTER_PLACEMENT.index)
              }
            },
            onStartQuizClick = {
              viewModel.startQuiz()
            }
          )
        }

        // Section 1: Career Tracks
        item(key = "section_career_tracks") {
          CareerSection(
            activeFilter = uiState.activeTrackFilter,
            onFilterChange = { viewModel.setTrackFilter(it) },
            searchQuery = uiState.trackSearchQuery,
            onSearchChange = { viewModel.setTrackSearch(it) },
            bookmarkedIds = uiState.bookmarkedTrackIds,
            onToggleBookmark = { viewModel.toggleBookmark(it) },
            onViewDetailsClick = { viewModel.openTrackDetail(it) },
            onEnquireTrackClick = { trackTitle ->
              viewModel.enquireAboutTrack(trackTitle)
              scope.launch {
                listState.animateScrollToItem(Section.CONTACT.index)
              }
            }
          )
        }

        // Section 2: Pay After Placement
        item(key = "section_pap") {
          PayAfterPlacementSection(
            onEnquirePapClick = {
              viewModel.enquireAboutPap()
              scope.launch {
                listState.animateScrollToItem(Section.CONTACT.index)
              }
            }
          )
        }

        // Section 3: Student Success & Placements
        item(key = "section_student_success") {
          StudentSuccessSection()
        }

        // Section 4: Real Internship Projects
        item(key = "section_projects") {
          ProjectsSection()
        }

        // Section 5: About
        item(key = "section_about") {
          AboutSection()
        }

        // Section 6: FAQs
        item(key = "section_faq") {
          FaqSection(
            expandedFaqIds = uiState.expandedFaqIds,
            onToggleFaq = { viewModel.toggleFaq(it) }
          )
        }

        // Section 7: Enrolment & Contact
        item(key = "section_contact") {
          ContactSection(
            formState = uiState.contactForm,
            onNameChange = { viewModel.updateContactName(it) },
            onEmailChange = { viewModel.updateContactEmail(it) },
            onPhoneChange = { viewModel.updateContactPhone(it) },
            onTrackChange = { viewModel.updateContactTrack(it) },
            onPapInterestChange = { viewModel.updateContactPapInterest(it) },
            onMessageChange = { viewModel.updateContactMessage(it) },
            onSubmit = { viewModel.submitContactForm() },
            onReset = { viewModel.resetContactForm() },
            onDeleteDraft = { viewModel.deleteLocalDraft(it) }
          )
        }

        // Section 8: Footer
        item(key = "section_footer") {
          FooterSection(
            onSectionClick = { section ->
              viewModel.onSectionSelected(section)
              scope.launch {
                listState.animateScrollToItem(section.index)
              }
            },
            onTrackClick = { track ->
              viewModel.openTrackDetail(track)
            },
            onBackToTopClick = {
              scope.launch {
                listState.animateScrollToItem(0)
                viewModel.onSectionSelected(Section.HOME)
              }
            }
          )
        }
      }
    }

    // Modal: Career Track Detail Dialog
    if (uiState.selectedTrackForDetail != null) {
      val track = uiState.selectedTrackForDetail!!
      CareerDetailDialog(
        track = track,
        isBookmarked = uiState.bookmarkedTrackIds.contains(track.id),
        onToggleBookmark = { viewModel.toggleBookmark(track.id) },
        onEnquireForTrack = { trackTitle ->
          viewModel.enquireAboutTrack(trackTitle)
          scope.launch {
            listState.animateScrollToItem(Section.CONTACT.index)
          }
        },
        onDismiss = { viewModel.closeTrackDetail() }
      )
    }

    // Modal: Career Discovery Quiz Dialog
    if (uiState.isQuizOpen) {
      CareerQuizDialog(
        currentStep = uiState.quizStep,
        recommendation = uiState.quizRecommendation,
        onAnswerSelected = { qId, trackId ->
          viewModel.answerQuizQuestion(qId, trackId)
        },
        onViewTrackDetails = { recommendedTrack ->
          viewModel.openTrackDetail(recommendedTrack)
        },
        onEnquireTrack = { trackTitle ->
          viewModel.enquireAboutTrack(trackTitle)
          scope.launch {
            listState.animateScrollToItem(Section.CONTACT.index)
          }
        },
        onRestartQuiz = {
          viewModel.startQuiz()
        },
        onDismiss = {
          viewModel.closeQuiz()
        }
      )
    }

    // Bottom Sheet: Saved Tracks Plan
    if (uiState.showSavedDrawer) {
      SavedCareersDrawer(
        savedIds = uiState.bookmarkedTrackIds,
        onRemoveBookmark = { viewModel.toggleBookmark(it) },
        onExploreTrack = { viewModel.openTrackDetail(it) },
        onDismiss = { viewModel.toggleSavedDrawer(false) }
      )
    }
  }
}
