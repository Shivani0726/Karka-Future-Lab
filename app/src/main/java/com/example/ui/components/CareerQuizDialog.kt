package com.example.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Psychology
import androidx.compose.material.icons.filled.RestartAlt
import androidx.compose.material.icons.filled.School
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.example.model.CareerTrack
import com.example.model.QuizData
import com.example.model.QuizRecommendation
import com.example.ui.theme.DarkText
import com.example.ui.theme.DarkTextSecondary
import com.example.ui.theme.NavyDark
import com.example.ui.theme.NavyPrimary
import com.example.ui.theme.PureWhite
import com.example.ui.theme.SlateBackground
import com.example.ui.theme.SlateBorder
import com.example.ui.theme.TealDark
import com.example.ui.theme.TealPrimary
import com.example.ui.theme.TealSoftBg

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun CareerQuizDialog(
  currentStep: Int,
  recommendation: QuizRecommendation?,
  onAnswerSelected: (questionId: Int, trackId: String) -> Unit,
  onViewTrackDetails: (CareerTrack) -> Unit,
  onEnquireTrack: (String) -> Unit,
  onRestartQuiz: () -> Unit,
  onDismiss: () -> Unit
) {
  val isFinished = currentStep >= QuizData.questions.size && recommendation != null

  Dialog(
    onDismissRequest = onDismiss,
    properties = DialogProperties(usePlatformDefaultWidth = false)
  ) {
    Surface(
      shape = RoundedCornerShape(20.dp),
      color = PureWhite,
      border = androidx.compose.foundation.BorderStroke(1.5.dp, SlateBorder),
      modifier = Modifier
        .fillMaxWidth(0.95f)
        .widthIn(max = 680.dp)
        .padding(vertical = 24.dp)
        .testTag("career_quiz_dialog")
    ) {
      Column(
        modifier = Modifier
          .fillMaxWidth()
          .verticalScroll(rememberScrollState())
          .padding(20.dp)
      ) {
        // Header
        Row(
          modifier = Modifier.fillMaxWidth(),
          horizontalArrangement = Arrangement.SpaceBetween,
          verticalAlignment = Alignment.CenterVertically
        ) {
          Row(verticalAlignment = Alignment.CenterVertically) {
            Box(
              modifier = Modifier
                .size(38.dp)
                .clip(CircleShape)
                .background(NavyPrimary),
              contentAlignment = Alignment.Center
            ) {
              Icon(
                imageVector = Icons.Default.Psychology,
                contentDescription = null,
                tint = TealPrimary,
                modifier = Modifier.size(20.dp)
              )
            }
            Spacer(modifier = Modifier.width(10.dp))
            Column {
              Text(
                text = "Career Track Discovery Quiz",
                style = MaterialTheme.typography.titleMedium.copy(
                  fontWeight = FontWeight.Bold,
                  color = DarkText
                )
              )
              Text(
                text = if (!isFinished) "Question ${currentStep + 1} of ${QuizData.questions.size}" else "Personalized Recommendation",
                style = MaterialTheme.typography.labelSmall.copy(
                  color = TealDark,
                  fontWeight = FontWeight.Bold
                )
              )
            }
          }

          IconButton(onClick = onDismiss, modifier = Modifier.testTag("close_quiz_button")) {
            Icon(imageVector = Icons.Default.Close, contentDescription = "Close quiz", tint = DarkText)
          }
        }

        Spacer(modifier = Modifier.height(18.dp))

        if (!isFinished) {
          // Question Content
          val question = QuizData.questions[currentStep]

          Text(
            text = question.question,
            style = MaterialTheme.typography.titleLarge.copy(
              fontWeight = FontWeight.Bold,
              color = DarkText,
              fontSize = 17.sp,
              lineHeight = 24.sp
            )
          )

          Spacer(modifier = Modifier.height(4.dp))

          Text(
            text = question.subtitle,
            style = MaterialTheme.typography.bodySmall.copy(
              color = DarkTextSecondary,
              fontSize = 12.sp
            )
          )

          Spacer(modifier = Modifier.height(14.dp))

          // Options
          Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
            question.options.forEachIndexed { index, option ->
              Card(
                shape = RoundedCornerShape(12.dp),
                colors = CardDefaults.cardColors(containerColor = SlateBackground),
                border = androidx.compose.foundation.BorderStroke(1.dp, SlateBorder),
                modifier = Modifier
                  .fillMaxWidth()
                  .clip(RoundedCornerShape(12.dp))
                  .clickable { onAnswerSelected(question.id, option.matchedTrackId) }
                  .testTag("quiz_option_${currentStep}_$index")
              ) {
                Row(
                  modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp),
                  verticalAlignment = Alignment.CenterVertically
                ) {
                  Box(
                    modifier = Modifier
                      .size(26.dp)
                      .clip(CircleShape)
                      .background(NavyPrimary),
                    contentAlignment = Alignment.Center
                  ) {
                    Text(
                      text = "${('A'.code + index).toChar()}",
                      style = MaterialTheme.typography.labelSmall.copy(
                        fontWeight = FontWeight.Bold,
                        color = TealPrimary
                      )
                    )
                  }
                  Spacer(modifier = Modifier.width(10.dp))
                  Text(
                    text = option.text,
                    style = MaterialTheme.typography.bodyMedium.copy(
                      fontWeight = FontWeight.Medium,
                      color = DarkText,
                      fontSize = 13.sp
                    ),
                    modifier = Modifier.weight(1f)
                  )
                }
              }
            }
          }
        } else if (recommendation != null) {
          // Recommendation Results View
          val track = recommendation.track

          Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
          ) {
            Box(
              modifier = Modifier
                .size(60.dp)
                .clip(CircleShape)
                .background(NavyPrimary)
                .border(2.dp, TealPrimary, CircleShape),
              contentAlignment = Alignment.Center
            ) {
              Icon(
                imageVector = track.icon,
                contentDescription = null,
                tint = TealPrimary,
                modifier = Modifier.size(32.dp)
              )
            }

            Spacer(modifier = Modifier.height(12.dp))

            Box(
              modifier = Modifier
                .clip(RoundedCornerShape(8.dp))
                .background(TealSoftBg)
                .padding(horizontal = 10.dp, vertical = 4.dp)
            ) {
              Text(
                text = "RECOMMENDED TRACK",
                style = MaterialTheme.typography.labelSmall.copy(
                  fontWeight = FontWeight.Bold,
                  color = TealDark
                )
              )
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(
              text = track.title,
              style = MaterialTheme.typography.headlineMedium.copy(
                fontWeight = FontWeight.Bold,
                color = DarkText,
                textAlign = TextAlign.Center,
                fontSize = 19.sp
              )
            )

            Spacer(modifier = Modifier.height(12.dp))

            // Why this track is suitable
            Card(
              shape = RoundedCornerShape(12.dp),
              colors = CardDefaults.cardColors(containerColor = SlateBackground),
              border = androidx.compose.foundation.BorderStroke(1.dp, SlateBorder),
              modifier = Modifier.fillMaxWidth()
            ) {
              Column(modifier = Modifier.padding(14.dp)) {
                Text(
                  text = "Why This Track Fits You:",
                  style = MaterialTheme.typography.labelMedium.copy(
                    fontWeight = FontWeight.Bold,
                    color = DarkText
                  )
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                  text = recommendation.reasonWhySuitable,
                  style = MaterialTheme.typography.bodyMedium.copy(
                    color = DarkTextSecondary,
                    fontSize = 13.sp,
                    lineHeight = 18.sp
                  )
                )

                Spacer(modifier = Modifier.height(10.dp))

                Text(
                  text = "Suggested Beginner Skills to Explore:",
                  style = MaterialTheme.typography.labelSmall.copy(
                    fontWeight = FontWeight.Bold,
                    color = TealDark
                  )
                )
                Spacer(modifier = Modifier.height(6.dp))
                FlowRow(
                  modifier = Modifier.fillMaxWidth(),
                  horizontalArrangement = Arrangement.spacedBy(6.dp),
                  verticalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                  recommendation.suggestedBeginnerSkills.forEach { skill ->
                    Box(
                      modifier = Modifier
                        .clip(RoundedCornerShape(6.dp))
                        .background(TealSoftBg)
                        .padding(horizontal = 8.dp, vertical = 3.dp)
                    ) {
                      Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(imageVector = Icons.Default.Check, contentDescription = null, tint = TealDark, modifier = Modifier.size(12.dp))
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                          text = skill,
                          style = MaterialTheme.typography.labelSmall.copy(
                            fontWeight = FontWeight.SemiBold,
                            color = TealDark,
                            fontSize = 11.sp
                          )
                        )
                      }
                    }
                  }
                }
              }
            }

            Spacer(modifier = Modifier.height(14.dp))

            // Clear recommendation disclaimer per user prompt
            Row(
              modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(8.dp))
                .background(SlateBackground)
                .padding(8.dp),
              verticalAlignment = Alignment.CenterVertically
            ) {
              Icon(imageVector = Icons.Default.Info, contentDescription = null, tint = DarkTextSecondary, modifier = Modifier.size(14.dp))
              Spacer(modifier = Modifier.width(6.dp))
              Text(
                text = "Note: This result is a guided recommendation based on your answers, not a guaranteed career outcome.",
                style = MaterialTheme.typography.labelSmall.copy(
                  color = DarkTextSecondary,
                  fontSize = 10.sp
                )
              )
            }

            Spacer(modifier = Modifier.height(18.dp))

            // Action Buttons
            Button(
              onClick = {
                onViewTrackDetails(track)
                onDismiss()
              },
              colors = ButtonDefaults.buttonColors(
                containerColor = NavyPrimary,
                contentColor = PureWhite
              ),
              shape = RoundedCornerShape(10.dp),
              modifier = Modifier
                .fillMaxWidth()
                .height(44.dp)
                .testTag("explore_quiz_result_button")
            ) {
              Icon(imageVector = Icons.Default.Visibility, contentDescription = null, modifier = Modifier.size(16.dp))
              Spacer(modifier = Modifier.width(6.dp))
              Text("View Track Curriculum & Details", fontWeight = FontWeight.Bold)
            }

            Spacer(modifier = Modifier.height(8.dp))

            Button(
              onClick = {
                onEnquireTrack(track.title)
                onDismiss()
              },
              colors = ButtonDefaults.buttonColors(
                containerColor = TealPrimary,
                contentColor = NavyDark
              ),
              shape = RoundedCornerShape(10.dp),
              modifier = Modifier
                .fillMaxWidth()
                .height(44.dp)
                .testTag("enquire_quiz_result_button")
            ) {
              Icon(imageVector = Icons.Default.School, contentDescription = null, modifier = Modifier.size(16.dp), tint = NavyDark)
              Spacer(modifier = Modifier.width(6.dp))
              Text("Enquire for This Track", fontWeight = FontWeight.Bold, color = NavyDark)
            }

            Spacer(modifier = Modifier.height(6.dp))

            OutlinedButton(
              onClick = onRestartQuiz,
              colors = ButtonDefaults.outlinedButtonColors(contentColor = DarkText),
              border = androidx.compose.foundation.BorderStroke(1.dp, SlateBorder),
              shape = RoundedCornerShape(10.dp),
              modifier = Modifier
                .fillMaxWidth()
                .height(40.dp)
                .testTag("restart_quiz_button")
            ) {
              Icon(imageVector = Icons.Default.RestartAlt, contentDescription = null, modifier = Modifier.size(15.dp))
              Spacer(modifier = Modifier.width(6.dp))
              Text("Retake Assessment", fontSize = 12.sp, fontWeight = FontWeight.SemiBold)
            }
          }
        }
      }
    }
  }
}
