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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Layers
import androidx.compose.material.icons.filled.Lightbulb
import androidx.compose.material.icons.filled.OpenInNew
import androidx.compose.material.icons.filled.Terminal
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
import com.example.model.InternshipProject
import com.example.model.InternshipProjectData
import com.example.ui.theme.DarkText
import com.example.ui.theme.DarkTextSecondary
import com.example.ui.theme.NavyPrimary
import com.example.ui.theme.PureWhite
import com.example.ui.theme.SlateBackground
import com.example.ui.theme.SlateBorder
import com.example.ui.theme.TealDark
import com.example.ui.theme.TealPrimary
import com.example.ui.theme.TealSoftBg

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun ProjectsSection(
  modifier: Modifier = Modifier
) {
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
      .background(SlateBackground)
      .padding(horizontal = 20.dp, vertical = 32.dp),
    contentAlignment = Alignment.TopCenter
  ) {
    Column(
      modifier = Modifier
        .fillMaxWidth()
        .widthIn(max = 860.dp),
      horizontalAlignment = Alignment.CenterHorizontally
    ) {
      // Pill
      Box(
        modifier = Modifier
          .clip(RoundedCornerShape(20.dp))
          .background(TealSoftBg)
          .border(1.dp, TealPrimary.copy(alpha = 0.5f), RoundedCornerShape(20.dp))
          .padding(horizontal = 14.dp, vertical = 5.dp)
      ) {
        Text(
          text = "CURRICULUM CAPSTONE BLUEPRINTS",
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
        text = InternshipProjectData.sectionTitle,
        style = MaterialTheme.typography.headlineLarge.copy(
          textAlign = TextAlign.Center,
          fontWeight = FontWeight.Bold,
          color = DarkText
        ),
        modifier = Modifier.testTag("projects_section_title")
      )

      Spacer(modifier = Modifier.height(10.dp))

      // Subtitle
      Text(
        text = InternshipProjectData.sectionSubtitle,
        style = MaterialTheme.typography.bodyLarge.copy(
          textAlign = TextAlign.Center,
          color = DarkTextSecondary
        ),
        modifier = Modifier
          .fillMaxWidth(0.92f)
          .testTag("projects_section_subtitle")
      )

      Spacer(modifier = Modifier.height(20.dp))

      // Transparency Notice Card
      Card(
        shape = RoundedCornerShape(14.dp),
        colors = CardDefaults.cardColors(containerColor = PureWhite),
        border = androidx.compose.foundation.BorderStroke(1.dp, SlateBorder),
        modifier = Modifier.fillMaxWidth()
      ) {
        Column(modifier = Modifier.padding(16.dp)) {
          Row(verticalAlignment = Alignment.Top) {
            Icon(
              imageVector = Icons.Default.Info,
              contentDescription = null,
              tint = TealDark,
              modifier = Modifier.size(18.dp)
            )
            Spacer(modifier = Modifier.width(10.dp))
            Column {
              Text(
                text = "Educational Project Transparency",
                style = MaterialTheme.typography.titleSmall.copy(
                  fontWeight = FontWeight.Bold,
                  color = DarkText
                )
              )
              Spacer(modifier = Modifier.height(3.dp))
              Text(
                text = InternshipProjectData.projectsDisclaimer,
                style = MaterialTheme.typography.bodySmall.copy(
                  color = DarkTextSecondary,
                  fontSize = 12.sp,
                  lineHeight = 17.sp
                )
              )
            }
          }

          Spacer(modifier = Modifier.height(10.dp))

          OutlinedButton(
            onClick = { openUrl("https://karka.academy") },
            shape = RoundedCornerShape(8.dp),
            border = androidx.compose.foundation.BorderStroke(1.dp, SlateBorder),
            colors = ButtonDefaults.outlinedButtonColors(containerColor = SlateBackground),
            modifier = Modifier.height(34.dp)
          ) {
            Icon(imageVector = Icons.Default.OpenInNew, contentDescription = null, modifier = Modifier.size(13.dp), tint = TealDark)
            Spacer(modifier = Modifier.width(6.dp))
            Text("Inquire About Student Portfolio Demos", fontSize = 11.sp, fontWeight = FontWeight.Bold, color = DarkText)
          }
        }
      }

      Spacer(modifier = Modifier.height(20.dp))

      // Projects List
      Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(16.dp)
      ) {
        InternshipProjectData.projects.forEach { project ->
          ProjectCard(project = project)
        }
      }
    }
  }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun ProjectCard(
  project: InternshipProject,
  modifier: Modifier = Modifier
) {
  Card(
    shape = RoundedCornerShape(18.dp),
    colors = CardDefaults.cardColors(containerColor = PureWhite),
    border = androidx.compose.foundation.BorderStroke(1.2.dp, SlateBorder),
    elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
    modifier = modifier.fillMaxWidth()
  ) {
    Column(modifier = Modifier.padding(18.dp)) {
      // Top Row: Code Icon + Track Tag + Status
      Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
      ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
          Box(
            modifier = Modifier
              .size(42.dp)
              .clip(RoundedCornerShape(10.dp))
              .background(NavyPrimary),
            contentAlignment = Alignment.Center
          ) {
            Icon(
              imageVector = Icons.Default.Terminal,
              contentDescription = null,
              tint = TealPrimary,
              modifier = Modifier.size(22.dp)
            )
          }

          Spacer(modifier = Modifier.width(10.dp))

          Box(
            modifier = Modifier
              .clip(RoundedCornerShape(6.dp))
              .background(TealSoftBg)
              .padding(horizontal = 8.dp, vertical = 3.dp)
          ) {
            Text(
              text = project.track,
              style = MaterialTheme.typography.labelSmall.copy(
                fontWeight = FontWeight.Bold,
                color = TealDark,
                fontSize = 11.sp
              )
            )
          }
        }

        Box(
          modifier = Modifier
            .clip(RoundedCornerShape(6.dp))
            .background(SlateBackground)
            .border(0.8.dp, SlateBorder, RoundedCornerShape(6.dp))
            .padding(horizontal = 8.dp, vertical = 3.dp)
        ) {
          Text(
            text = project.repoOrDemoStatus,
            style = MaterialTheme.typography.labelSmall.copy(
              color = DarkTextSecondary,
              fontSize = 10.sp,
              fontWeight = FontWeight.SemiBold
            )
          )
        }
      }

      Spacer(modifier = Modifier.height(14.dp))

      // Title
      Text(
        text = project.title,
        style = MaterialTheme.typography.titleMedium.copy(
          fontWeight = FontWeight.Bold,
          color = DarkText,
          fontSize = 17.sp
        )
      )

      Spacer(modifier = Modifier.height(8.dp))

      // Problem Being Solved
      Row(
        modifier = Modifier
          .fillMaxWidth()
          .clip(RoundedCornerShape(8.dp))
          .background(SlateBackground)
          .padding(10.dp),
        verticalAlignment = Alignment.Top
      ) {
        Icon(
          imageVector = Icons.Default.Lightbulb,
          contentDescription = null,
          tint = TealDark,
          modifier = Modifier
            .size(16.dp)
            .padding(top = 1.dp)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Column {
          Text(
            text = "Problem Solved:",
            style = MaterialTheme.typography.labelSmall.copy(
              fontWeight = FontWeight.Bold,
              color = DarkText,
              fontSize = 11.sp
            )
          )
          Text(
            text = project.problemSolved,
            style = MaterialTheme.typography.bodySmall.copy(
              color = DarkTextSecondary,
              fontSize = 12.sp,
              lineHeight = 17.sp
            )
          )
        }
      }

      Spacer(modifier = Modifier.height(10.dp))

      // Description
      Text(
        text = project.description,
        style = MaterialTheme.typography.bodyMedium.copy(
          color = DarkTextSecondary,
          lineHeight = 19.sp,
          fontSize = 13.sp
        )
      )

      Spacer(modifier = Modifier.height(12.dp))

      // Architecture Highlights
      Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
        project.architectureHighlights.forEach { highlight ->
          Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
              imageVector = Icons.Default.Layers,
              contentDescription = null,
              tint = TealDark,
              modifier = Modifier.size(14.dp)
            )
            Spacer(modifier = Modifier.width(6.dp))
            Text(
              text = highlight,
              style = MaterialTheme.typography.bodySmall.copy(
                color = DarkText,
                fontWeight = FontWeight.Medium,
                fontSize = 12.sp
              )
            )
          }
        }
      }

      Spacer(modifier = Modifier.height(14.dp))

      // Tech Stack Badges
      FlowRow(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(6.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp)
      ) {
        project.techStack.forEach { tech ->
          Box(
            modifier = Modifier
              .clip(RoundedCornerShape(6.dp))
              .background(TealSoftBg)
              .padding(horizontal = 8.dp, vertical = 3.dp)
          ) {
            Text(
              text = tech,
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
