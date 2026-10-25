package com.example.ui.components

import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Business
import androidx.compose.material.icons.filled.EmojiEvents
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.OpenInNew
import androidx.compose.material.icons.filled.School
import androidx.compose.material.icons.filled.VerifiedUser
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.model.PlacementStory
import com.example.model.StudentSuccessData
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
fun StudentSuccessSection(modifier: Modifier = Modifier) {
  val context = LocalContext.current

  fun openUrl(url: String) {
    try {
      val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
      context.startActivity(intent)
    } catch (e: Exception) {
      Toast.makeText(context, "Could not open browser", Toast.LENGTH_SHORT).show()
    }
  }

  Box(
    modifier = modifier
      .fillMaxWidth()
      .background(PureWhite)
      .padding(horizontal = 20.dp, vertical = 32.dp),
    contentAlignment = Alignment.TopCenter
  ) {
    Column(
      modifier = Modifier
        .fillMaxWidth()
        .widthIn(max = 860.dp),
      horizontalAlignment = Alignment.CenterHorizontally
    ) {
      // Section Pill
      Box(
        modifier = Modifier
          .clip(RoundedCornerShape(20.dp))
          .background(TealSoftBg)
          .border(1.dp, TealPrimary.copy(alpha = 0.5f), RoundedCornerShape(20.dp))
          .padding(horizontal = 14.dp, vertical = 5.dp)
      ) {
        Text(
          text = "CAREER PATHWAYS & ROLES",
          style = MaterialTheme.typography.labelSmall.copy(
            fontWeight = FontWeight.Bold,
            color = TealDark,
            letterSpacing = 1.sp
          )
        )
      }

      Spacer(modifier = Modifier.height(12.dp))

      // Title
      Text(
        text = StudentSuccessData.sectionTitle,
        style = MaterialTheme.typography.headlineLarge.copy(
          textAlign = TextAlign.Center,
          fontWeight = FontWeight.Bold,
          color = DarkText
        ),
        modifier = Modifier.testTag("student_success_title")
      )

      Spacer(modifier = Modifier.height(10.dp))

      // Subtitle
      Text(
        text = StudentSuccessData.sectionSubtitle,
        style = MaterialTheme.typography.bodyLarge.copy(
          textAlign = TextAlign.Center,
          color = DarkTextSecondary
        ),
        modifier = Modifier
          .fillMaxWidth(0.92f)
          .testTag("student_success_subtitle")
      )

      Spacer(modifier = Modifier.height(20.dp))

      // Prominent Transparency & Verification Card
      Card(
        shape = RoundedCornerShape(14.dp),
        colors = CardDefaults.cardColors(containerColor = SlateBackground),
        border = androidx.compose.foundation.BorderStroke(1.dp, SlateBorder),
        modifier = Modifier.fillMaxWidth()
      ) {
        Column(modifier = Modifier.padding(16.dp)) {
          Row(verticalAlignment = Alignment.Top) {
            Icon(
              imageVector = Icons.Default.VerifiedUser,
              contentDescription = null,
              tint = TealDark,
              modifier = Modifier.size(20.dp)
            )
            Spacer(modifier = Modifier.width(10.dp))
            Column {
              Text(
                text = "Factual Integrity & Alumni Privacy Notice",
                style = MaterialTheme.typography.titleSmall.copy(
                  fontWeight = FontWeight.Bold,
                  color = DarkText
                )
              )
              Spacer(modifier = Modifier.height(4.dp))
              Text(
                text = StudentSuccessData.transparencyDisclaimer,
                style = MaterialTheme.typography.bodySmall.copy(
                  color = DarkTextSecondary,
                  fontSize = 12.sp,
                  lineHeight = 17.sp
                )
              )
            }
          }

          Spacer(modifier = Modifier.height(12.dp))

          FlowRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
          ) {
            OutlinedButton(
              onClick = { openUrl("https://instagram.com/karka.ai") },
              shape = RoundedCornerShape(8.dp),
              border = androidx.compose.foundation.BorderStroke(1.dp, SlateBorder),
              colors = ButtonDefaults.outlinedButtonColors(containerColor = PureWhite),
              modifier = Modifier.height(36.dp)
            ) {
              Icon(imageVector = Icons.Default.OpenInNew, contentDescription = null, modifier = Modifier.size(14.dp), tint = TealDark)
              Spacer(modifier = Modifier.width(6.dp))
              Text("View Alumni Updates on @karka.ai", fontSize = 11.sp, fontWeight = FontWeight.Bold, color = DarkText)
            }

            OutlinedButton(
              onClick = { openUrl("https://karka.academy") },
              shape = RoundedCornerShape(8.dp),
              border = androidx.compose.foundation.BorderStroke(1.dp, SlateBorder),
              colors = ButtonDefaults.outlinedButtonColors(containerColor = PureWhite),
              modifier = Modifier.height(36.dp)
            ) {
              Icon(imageVector = Icons.Default.OpenInNew, contentDescription = null, modifier = Modifier.size(14.dp), tint = TealDark)
              Spacer(modifier = Modifier.width(6.dp))
              Text("Official karka.academy", fontSize = 11.sp, fontWeight = FontWeight.Bold, color = DarkText)
            }
          }
        }
      }

      Spacer(modifier = Modifier.height(20.dp))

      // Stories Grid
      Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(14.dp)
      ) {
        StudentSuccessData.placementStories.forEach { story ->
          PlacementStoryCard(story = story)
        }
      }
    }
  }
}

@Composable
fun PlacementStoryCard(
  story: PlacementStory,
  modifier: Modifier = Modifier
) {
  Card(
    shape = RoundedCornerShape(16.dp),
    colors = CardDefaults.cardColors(containerColor = SlateBackground),
    border = androidx.compose.foundation.BorderStroke(1.dp, SlateBorder),
    modifier = modifier.fillMaxWidth()
  ) {
    Column(modifier = Modifier.padding(16.dp)) {
      // Header: Avatar icon + Candidate label + Batch tag
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
              imageVector = Icons.Default.School,
              contentDescription = null,
              tint = TealPrimary,
              modifier = Modifier.size(20.dp)
            )
          }

          Spacer(modifier = Modifier.width(10.dp))

          Column {
            Text(
              text = story.candidateLabel,
              style = MaterialTheme.typography.titleSmall.copy(
                fontWeight = FontWeight.Bold,
                color = DarkText
              )
            )
            Text(
              text = story.batchInfo,
              style = MaterialTheme.typography.bodySmall.copy(
                color = DarkTextSecondary,
                fontSize = 11.sp
              )
            )
          }
        }

        Box(
          modifier = Modifier
            .clip(RoundedCornerShape(6.dp))
            .background(TealSoftBg)
            .padding(horizontal = 8.dp, vertical = 3.dp)
        ) {
          Text(
            text = "Target Role",
            style = MaterialTheme.typography.labelSmall.copy(
              fontWeight = FontWeight.Bold,
              color = TealDark,
              fontSize = 10.sp
            )
          )
        }
      }

      Spacer(modifier = Modifier.height(12.dp))

      // Placed Role & Hiring Domain
      Row(verticalAlignment = Alignment.CenterVertically) {
        Icon(
          imageVector = Icons.Default.EmojiEvents,
          contentDescription = null,
          tint = TealDark,
          modifier = Modifier.size(16.dp)
        )
        Spacer(modifier = Modifier.width(6.dp))
        Text(
          text = story.placedRole,
          style = MaterialTheme.typography.titleMedium.copy(
            fontWeight = FontWeight.Bold,
            color = NavyPrimary,
            fontSize = 15.sp
          )
        )
      }

      Spacer(modifier = Modifier.height(4.dp))

      Row(verticalAlignment = Alignment.CenterVertically) {
        Icon(
          imageVector = Icons.Default.Business,
          contentDescription = null,
          tint = DarkTextSecondary,
          modifier = Modifier.size(14.dp)
        )
        Spacer(modifier = Modifier.width(6.dp))
        Text(
          text = "Target Domains: ${story.hiringDomain}",
          style = MaterialTheme.typography.bodySmall.copy(
            color = DarkTextSecondary,
            fontSize = 12.sp
          )
        )
      }

      Spacer(modifier = Modifier.height(8.dp))

      // Track completed pill
      Box(
        modifier = Modifier
          .clip(RoundedCornerShape(6.dp))
          .background(PureWhite)
          .border(0.8.dp, SlateBorder, RoundedCornerShape(6.dp))
          .padding(horizontal = 8.dp, vertical = 3.dp)
      ) {
        Text(
          text = "Track: ${story.trackCompleted}",
          style = MaterialTheme.typography.labelSmall.copy(
            color = DarkText,
            fontWeight = FontWeight.SemiBold,
            fontSize = 11.sp
          )
        )
      }

      Spacer(modifier = Modifier.height(10.dp))

      Text(
        text = story.storySnippet,
        style = MaterialTheme.typography.bodyMedium.copy(
          color = DarkTextSecondary,
          lineHeight = 19.sp,
          fontSize = 13.sp
        )
      )
    }
  }
}
