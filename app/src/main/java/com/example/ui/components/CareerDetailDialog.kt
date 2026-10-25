package com.example.ui.components

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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.BookmarkBorder
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Engineering
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Lightbulb
import androidx.compose.material.icons.filled.Route
import androidx.compose.material.icons.filled.School
import androidx.compose.material.icons.filled.Work
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
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
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.example.model.CareerTrack
import com.example.ui.theme.DarkText
import com.example.ui.theme.DarkTextSecondary
import com.example.ui.theme.NavyDark
import com.example.ui.theme.NavyPrimary
import com.example.ui.theme.NavySurface
import com.example.ui.theme.PureWhite
import com.example.ui.theme.SlateBackground
import com.example.ui.theme.SlateBorder
import com.example.ui.theme.TealDark
import com.example.ui.theme.TealPrimary
import com.example.ui.theme.TealSoftBg

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun CareerDetailDialog(
  track: CareerTrack,
  isBookmarked: Boolean,
  onToggleBookmark: () -> Unit,
  onEnquireForTrack: (String) -> Unit,
  onDismiss: () -> Unit
) {
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
        .testTag("track_detail_dialog")
    ) {
      Column(
        modifier = Modifier
          .fillMaxWidth()
          .verticalScroll(rememberScrollState())
          .padding(20.dp)
      ) {
        // Top Bar: Icon + Title + Close Button
        Row(
          modifier = Modifier.fillMaxWidth(),
          horizontalArrangement = Arrangement.SpaceBetween,
          verticalAlignment = Alignment.Top
        ) {
          Row(
            modifier = Modifier.weight(1f),
            verticalAlignment = Alignment.CenterVertically
          ) {
            Box(
              modifier = Modifier
                .size(50.dp)
                .clip(RoundedCornerShape(14.dp))
                .background(NavyPrimary),
              contentAlignment = Alignment.Center
            ) {
              Icon(
                imageVector = track.icon,
                contentDescription = null,
                tint = TealPrimary,
                modifier = Modifier.size(26.dp)
              )
            }
            Spacer(modifier = Modifier.width(12.dp))
            Column {
              Row(
                horizontalArrangement = Arrangement.spacedBy(6.dp),
                verticalAlignment = Alignment.CenterVertically
              ) {
                Box(
                  modifier = Modifier
                    .clip(RoundedCornerShape(8.dp))
                    .background(TealSoftBg)
                    .padding(horizontal = 8.dp, vertical = 2.dp)
                ) {
                  Text(
                    text = track.category,
                    style = MaterialTheme.typography.labelSmall.copy(
                      color = TealDark,
                      fontWeight = FontWeight.Bold,
                      fontSize = 10.sp
                    )
                  )
                }

                Box(
                  modifier = Modifier
                    .clip(RoundedCornerShape(8.dp))
                    .background(if (track.isFlagshipOfficial) com.example.ui.theme.AccentEmeraldBg else SlateBackground)
                    .border(0.6.dp, if (track.isFlagshipOfficial) com.example.ui.theme.AccentEmerald.copy(alpha = 0.5f) else SlateBorder, RoundedCornerShape(8.dp))
                    .padding(horizontal = 6.dp, vertical = 2.dp)
                ) {
                  Text(
                    text = track.trackBadge,
                    style = MaterialTheme.typography.labelSmall.copy(
                      fontWeight = FontWeight.SemiBold,
                      color = if (track.isFlagshipOfficial) TealDark else DarkTextSecondary,
                      fontSize = 9.sp
                    )
                  )
                }
              }
              Spacer(modifier = Modifier.height(4.dp))
              Text(
                text = track.title,
                style = MaterialTheme.typography.titleLarge.copy(
                  fontWeight = FontWeight.Bold,
                  color = DarkText,
                  fontSize = 18.sp
                )
              )
            }
          }

          IconButton(
            onClick = onDismiss,
            modifier = Modifier.testTag("close_detail_dialog_button")
          ) {
            Icon(imageVector = Icons.Default.Close, contentDescription = "Close dialog", tint = DarkText)
          }
        }

        Spacer(modifier = Modifier.height(14.dp))
        HorizontalDivider(color = SlateBorder, thickness = 1.dp)
        Spacer(modifier = Modifier.height(14.dp))

        // Duration & Prerequisites Info
        Row(
          modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(10.dp))
            .background(SlateBackground)
            .border(1.dp, SlateBorder, RoundedCornerShape(10.dp))
            .padding(12.dp),
          verticalAlignment = Alignment.CenterVertically,
          horizontalArrangement = Arrangement.SpaceBetween
        ) {
          Column {
            Text(
              text = "Cohort Duration",
              style = MaterialTheme.typography.labelSmall.copy(color = DarkTextSecondary)
            )
            Text(
              text = track.durationLabel,
              style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Bold, color = DarkText)
            )
          }

          Column {
            Text(
              text = "Eligibility",
              style = MaterialTheme.typography.labelSmall.copy(color = DarkTextSecondary)
            )
            Text(
              text = track.prerequisites,
              style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Bold, color = TealDark)
            )
          }
        }

        Spacer(modifier = Modifier.height(18.dp))

        // 1. About This Track
        SectionHeader(icon = Icons.Default.Info, title = "About This Career Track")
        Spacer(modifier = Modifier.height(8.dp))
        Text(
          text = track.about,
          style = MaterialTheme.typography.bodyLarge.copy(
            color = DarkTextSecondary,
            lineHeight = 22.sp
          )
        )

        Spacer(modifier = Modifier.height(18.dp))

        // 2. Skills Students Learn
        SectionHeader(icon = Icons.Default.Engineering, title = "Skills Students Will Learn")
        Spacer(modifier = Modifier.height(10.dp))
        FlowRow(
          modifier = Modifier.fillMaxWidth(),
          horizontalArrangement = Arrangement.spacedBy(8.dp),
          verticalArrangement = Arrangement.spacedBy(6.dp)
        ) {
          track.skillsStudentsLearn.forEach { skill ->
            Box(
              modifier = Modifier
                .clip(RoundedCornerShape(8.dp))
                .background(TealSoftBg)
                .border(1.dp, TealPrimary.copy(alpha = 0.3f), RoundedCornerShape(8.dp))
                .padding(horizontal = 10.dp, vertical = 5.dp)
            ) {
              Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                  imageVector = Icons.Default.Check,
                  contentDescription = null,
                  tint = TealDark,
                  modifier = Modifier.size(13.dp)
                )
                Spacer(modifier = Modifier.width(6.dp))
                Text(
                  text = skill,
                  style = MaterialTheme.typography.labelSmall.copy(
                    fontWeight = FontWeight.SemiBold,
                    color = TealDark,
                    fontSize = 12.sp
                  )
                )
              }
            }
          }
        }

        Spacer(modifier = Modifier.height(20.dp))

        // 3. Curriculum Roadmap
        SectionHeader(icon = Icons.Default.Route, title = "Curriculum Roadmap")
        Spacer(modifier = Modifier.height(10.dp))
        Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
          track.roadmap.forEach { step ->
            Card(
              shape = RoundedCornerShape(12.dp),
              colors = CardDefaults.cardColors(containerColor = SlateBackground),
              border = androidx.compose.foundation.BorderStroke(1.dp, SlateBorder),
              modifier = Modifier.fillMaxWidth()
            ) {
              Row(
                modifier = Modifier.padding(12.dp),
                verticalAlignment = Alignment.Top
              ) {
                Box(
                  modifier = Modifier
                    .size(26.dp)
                    .clip(CircleShape)
                    .background(NavyPrimary),
                  contentAlignment = Alignment.Center
                ) {
                  Text(
                    text = "${step.stepNumber}",
                    style = MaterialTheme.typography.labelSmall.copy(
                      color = TealPrimary,
                      fontWeight = FontWeight.Bold
                    )
                  )
                }
                Spacer(modifier = Modifier.width(10.dp))
                Column(modifier = Modifier.weight(1f)) {
                  Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                  ) {
                    Text(
                      text = step.title,
                      style = MaterialTheme.typography.titleMedium.copy(
                        fontWeight = FontWeight.Bold,
                        color = DarkText,
                        fontSize = 14.sp
                      )
                    )
                    Text(
                      text = step.duration,
                      style = MaterialTheme.typography.labelSmall.copy(
                        color = TealDark,
                        fontWeight = FontWeight.Bold,
                        fontSize = 11.sp
                      )
                    )
                  }
                  Spacer(modifier = Modifier.height(3.dp))
                  Text(
                    text = step.description,
                    style = MaterialTheme.typography.bodySmall.copy(
                      color = DarkTextSecondary,
                      lineHeight = 17.sp
                    )
                  )
                }
              }
            }
          }
        }

        Spacer(modifier = Modifier.height(20.dp))

        // 4. Example Internship Projects
        SectionHeader(icon = Icons.Default.Lightbulb, title = "Example Internship Projects")
        Spacer(modifier = Modifier.height(10.dp))
        Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
          track.exampleProjects.forEach { project ->
            Card(
              shape = RoundedCornerShape(12.dp),
              colors = CardDefaults.cardColors(containerColor = PureWhite),
              border = androidx.compose.foundation.BorderStroke(1.dp, SlateBorder),
              modifier = Modifier.fillMaxWidth()
            ) {
              Column(modifier = Modifier.padding(12.dp)) {
                Text(
                  text = project.title,
                  style = MaterialTheme.typography.titleMedium.copy(
                    fontWeight = FontWeight.Bold,
                    color = DarkText,
                    fontSize = 14.sp
                  )
                )
                Spacer(modifier = Modifier.height(3.dp))
                Text(
                  text = project.description,
                  style = MaterialTheme.typography.bodyMedium.copy(
                    color = DarkTextSecondary,
                    lineHeight = 18.sp,
                    fontSize = 13.sp
                  )
                )
                Spacer(modifier = Modifier.height(6.dp))
                Text(
                  text = "Tech Stack: ${project.techStack}",
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

        Spacer(modifier = Modifier.height(20.dp))

        // 5. Possible Career Opportunities
        SectionHeader(icon = Icons.Default.Work, title = "Possible Career Opportunities")
        Spacer(modifier = Modifier.height(10.dp))
        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
          track.careerOpportunities.forEach { role ->
            Row(
              modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(10.dp))
                .background(SlateBackground)
                .padding(10.dp),
              verticalAlignment = Alignment.CenterVertically,
              horizontalArrangement = Arrangement.SpaceBetween
            ) {
              Column(modifier = Modifier.weight(1f)) {
                Text(
                  text = role.roleName,
                  style = MaterialTheme.typography.labelMedium.copy(
                    fontWeight = FontWeight.Bold,
                    color = DarkText
                  )
                )
                Text(
                  text = role.typicalHiringDomains,
                  style = MaterialTheme.typography.bodySmall.copy(
                    color = DarkTextSecondary,
                    fontSize = 11.sp
                  )
                )
              }
              Box(
                modifier = Modifier
                  .clip(RoundedCornerShape(6.dp))
                  .background(TealSoftBg)
                  .padding(horizontal = 8.dp, vertical = 3.dp)
              ) {
                Text(
                  text = role.avgSalaryInfo,
                  style = MaterialTheme.typography.labelSmall.copy(
                    fontWeight = FontWeight.Bold,
                    color = TealDark,
                    fontSize = 10.sp
                  )
                )
              }
            }
          }
        }

        Spacer(modifier = Modifier.height(14.dp))

        // Admissions note
        Row(
          modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(8.dp))
            .background(SlateBackground)
            .border(0.8.dp, SlateBorder, RoundedCornerShape(8.dp))
            .padding(10.dp),
          verticalAlignment = Alignment.Top
        ) {
          Icon(
            imageVector = Icons.Default.Info,
            contentDescription = null,
            tint = TealDark,
            modifier = Modifier.size(15.dp)
          )
          Spacer(modifier = Modifier.width(8.dp))
          Text(
            text = "Track status: ${track.officialStatusNote}. Check with Karka Academy admissions (karka.academy or @karka.ai) to confirm upcoming batch start dates and seat limits.",
            style = MaterialTheme.typography.bodySmall.copy(
              color = DarkTextSecondary,
              fontSize = 11.sp,
              lineHeight = 15.sp
            )
          )
        }

        Spacer(modifier = Modifier.height(18.dp))

        // Bottom CTAs: Enquire for This Track + Bookmark
        Row(
          modifier = Modifier.fillMaxWidth(),
          horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
          OutlinedButton(
            onClick = onToggleBookmark,
            colors = ButtonDefaults.outlinedButtonColors(contentColor = NavyPrimary),
            border = androidx.compose.foundation.BorderStroke(1.2.dp, NavyPrimary),
            shape = RoundedCornerShape(10.dp),
            modifier = Modifier
              .weight(1f)
              .height(44.dp)
              .testTag("dialog_bookmark_button")
          ) {
            Icon(
              imageVector = if (isBookmarked) Icons.Default.Bookmark else Icons.Default.BookmarkBorder,
              contentDescription = null,
              modifier = Modifier.size(16.dp),
              tint = NavyPrimary
            )
            Spacer(modifier = Modifier.width(6.dp))
            Text(
              text = if (isBookmarked) "Saved" else "Save Track",
              style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Bold)
            )
          }

          Button(
            onClick = {
              onEnquireForTrack(track.title)
              onDismiss()
            },
            colors = ButtonDefaults.buttonColors(
              containerColor = TealPrimary,
              contentColor = NavyDark
            ),
            shape = RoundedCornerShape(10.dp),
            modifier = Modifier
              .weight(1f)
              .height(44.dp)
              .testTag("dialog_enquire_button")
          ) {
            Icon(imageVector = Icons.Default.School, contentDescription = null, modifier = Modifier.size(16.dp), tint = NavyDark)
            Spacer(modifier = Modifier.width(6.dp))
            Text(
              text = "Enquire Now",
              style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Bold, color = NavyDark)
            )
          }
        }
      }
    }
  }
}

@Composable
private fun SectionHeader(
  icon: ImageVector,
  title: String,
  modifier: Modifier = Modifier
) {
  Row(
    verticalAlignment = Alignment.CenterVertically,
    modifier = modifier
  ) {
    Box(
      modifier = Modifier
        .size(28.dp)
        .clip(CircleShape)
        .background(NavyPrimary),
      contentAlignment = Alignment.Center
    ) {
      Icon(
        imageVector = icon,
        contentDescription = null,
        tint = TealPrimary,
        modifier = Modifier.size(15.dp)
      )
    }
    Spacer(modifier = Modifier.width(10.dp))
    Text(
      text = title,
      style = MaterialTheme.typography.titleMedium.copy(
        fontWeight = FontWeight.Bold,
        color = DarkText,
        fontSize = 15.sp
      )
    )
  }
}
