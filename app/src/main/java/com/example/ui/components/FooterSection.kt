package com.example.ui.components

import android.content.Intent
import android.net.Uri
import androidx.compose.ui.platform.LocalContext
import androidx.compose.foundation.Image
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.R
import com.example.model.CareerTrack
import com.example.model.CareerTrackData
import com.example.model.Section
import com.example.ui.theme.DarkTextSecondary
import com.example.ui.theme.NavyDark
import com.example.ui.theme.NavyPrimary
import com.example.ui.theme.NavySurface
import com.example.ui.theme.PureWhite
import com.example.ui.theme.TealDark
import com.example.ui.theme.TealPrimary
import com.example.ui.theme.TealSoftBg

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun FooterSection(
  onSectionClick: (Section) -> Unit,
  onTrackClick: (CareerTrack) -> Unit,
  onBackToTopClick: () -> Unit,
  modifier: Modifier = Modifier
) {
  Box(
    modifier = modifier
      .fillMaxWidth()
      .background(NavyDark)
      .padding(horizontal = 20.dp, vertical = 32.dp),
    contentAlignment = Alignment.TopCenter
  ) {
    Column(
      modifier = Modifier
        .fillMaxWidth()
        .widthIn(max = 860.dp)
    ) {
    // Top Row: Brand & Back to Top Button
    Row(
      modifier = Modifier.fillMaxWidth(),
      horizontalArrangement = Arrangement.SpaceBetween,
      verticalAlignment = Alignment.CenterVertically
    ) {
      Row(verticalAlignment = Alignment.CenterVertically) {
        Image(
          painter = painterResource(id = R.drawable.img_karka_logo),
          contentDescription = "Karka AI & Tech Academy",
          modifier = Modifier
            .size(42.dp)
            .clip(CircleShape)
            .border(1.5.dp, TealPrimary, CircleShape),
          contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.width(10.dp))
        Column {
          Text(
            text = "Karka AI & Tech Academy",
            style = MaterialTheme.typography.titleMedium.copy(
              fontWeight = FontWeight.Bold,
              color = PureWhite,
              fontSize = 16.sp
            )
          )
          Text(
            text = "karka.academy • Instagram @karka.ai",
            style = MaterialTheme.typography.labelSmall.copy(
              color = TealPrimary,
              fontSize = 10.sp,
              letterSpacing = 0.5.sp
            )
          )
        }
      }

      // Back to Top Button
      Box(
        modifier = Modifier
          .size(40.dp)
          .clip(CircleShape)
          .background(NavySurface)
          .border(1.dp, TealPrimary.copy(alpha = 0.4f), CircleShape)
          .clickable { onBackToTopClick() }
          .testTag("footer_back_to_top_button"),
        contentAlignment = Alignment.Center
      ) {
        Icon(
          imageVector = Icons.Default.KeyboardArrowUp,
          contentDescription = "Back to top",
          tint = TealPrimary,
          modifier = Modifier.size(24.dp)
        )
      }
    }

    Spacer(modifier = Modifier.height(14.dp))

    Text(
      text = "Karka AI & Tech Academy equips college students and career switchers with modern software engineering skills, practical capstone projects, and dedicated Pay After Placement career acceleration.",
      style = MaterialTheme.typography.bodyMedium.copy(
        color = PureWhite.copy(alpha = 0.75f),
        lineHeight = 20.sp,
        fontSize = 13.sp
      )
    )

    Spacer(modifier = Modifier.height(20.dp))
    HorizontalDivider(color = PureWhite.copy(alpha = 0.12f), thickness = 0.8.dp)
    Spacer(modifier = Modifier.height(16.dp))

    // Navigation Links
    Text(
      text = "ACADEMY DIRECTORY",
      style = MaterialTheme.typography.labelSmall.copy(
        fontWeight = FontWeight.Bold,
        color = TealPrimary,
        letterSpacing = 1.sp
      )
    )
    Spacer(modifier = Modifier.height(10.dp))
    FlowRow(
      modifier = Modifier.fillMaxWidth(),
      horizontalArrangement = Arrangement.spacedBy(14.dp),
      verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
      Section.values().forEach { section ->
        Text(
          text = section.title,
          style = MaterialTheme.typography.labelMedium.copy(
            color = PureWhite.copy(alpha = 0.85f),
            fontWeight = FontWeight.SemiBold
          ),
          modifier = Modifier
            .clickable { onSectionClick(section) }
            .padding(vertical = 4.dp)
            .testTag("footer_link_${section.name.lowercase()}")
        )
      }
    }

    Spacer(modifier = Modifier.height(20.dp))

    // Career Tracks Links
    Text(
      text = "CAREER TRACKS",
      style = MaterialTheme.typography.labelSmall.copy(
        fontWeight = FontWeight.Bold,
        color = TealPrimary,
        letterSpacing = 1.sp
      )
    )
    Spacer(modifier = Modifier.height(10.dp))
    FlowRow(
      modifier = Modifier.fillMaxWidth(),
      horizontalArrangement = Arrangement.spacedBy(8.dp),
      verticalArrangement = Arrangement.spacedBy(6.dp)
    ) {
      CareerTrackData.allTracks.forEach { track ->
        Box(
          modifier = Modifier
            .clip(RoundedCornerShape(6.dp))
            .background(NavySurface)
            .border(0.8.dp, TealPrimary.copy(alpha = 0.25f), RoundedCornerShape(6.dp))
            .clickable { onTrackClick(track) }
            .padding(horizontal = 8.dp, vertical = 4.dp)
            .testTag("footer_track_${track.id}")
        ) {
          Text(
            text = track.title,
            style = MaterialTheme.typography.labelSmall.copy(
              color = PureWhite,
              fontWeight = FontWeight.Medium,
              fontSize = 11.sp
            )
          )
        }
      }
    }

    Spacer(modifier = Modifier.height(20.dp))
    HorizontalDivider(color = PureWhite.copy(alpha = 0.12f), thickness = 0.8.dp)
    Spacer(modifier = Modifier.height(16.dp))

    val context = LocalContext.current
    fun openUrl(url: String) {
      try {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        context.startActivity(intent)
      } catch (_: Exception) {}
    }

    // Official Handles & Social Channels
    Row(
      modifier = Modifier.fillMaxWidth(),
      horizontalArrangement = Arrangement.SpaceBetween,
      verticalAlignment = Alignment.CenterVertically
    ) {
      Column {
        Text(
          text = "Official Website: karka.academy",
          style = MaterialTheme.typography.bodySmall.copy(color = PureWhite.copy(alpha = 0.8f)),
          modifier = Modifier
            .clip(RoundedCornerShape(4.dp))
            .clickable { openUrl("https://karka.academy") }
            .padding(vertical = 2.dp)
        )
        Text(
          text = "Official Instagram: @karka.ai",
          style = MaterialTheme.typography.bodySmall.copy(color = TealPrimary),
          modifier = Modifier
            .clip(RoundedCornerShape(4.dp))
            .clickable { openUrl("https://instagram.com/karka.ai") }
            .padding(vertical = 2.dp)
        )
      }

      // Social Media Direct Links
      Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
        val socialLinks = listOf(
          "IG" to "https://instagram.com/karka.ai",
          "LN" to "https://linkedin.com",
          "GH" to "https://github.com",
          "YT" to "https://youtube.com"
        )
        socialLinks.forEach { (tag, url) ->
          Box(
            modifier = Modifier
              .size(32.dp)
              .clip(CircleShape)
              .background(NavySurface)
              .border(0.8.dp, TealPrimary.copy(alpha = 0.3f), CircleShape)
              .clickable { openUrl(url) },
            contentAlignment = Alignment.Center
          ) {
            Text(
              text = tag,
              style = MaterialTheme.typography.labelSmall.copy(
                fontWeight = FontWeight.Bold,
                color = TealPrimary,
                fontSize = 10.sp
              )
            )
          }
        }
      }
    }

    Spacer(modifier = Modifier.height(18.dp))

    // Copyright Notice
    Text(
      text = "© 2026 Karka AI & Tech Academy (karka.academy). All rights reserved.",
      style = MaterialTheme.typography.bodySmall.copy(
        color = PureWhite.copy(alpha = 0.5f),
        textAlign = TextAlign.Center,
        fontSize = 11.sp
      ),
      modifier = Modifier.fillMaxWidth()
    )
  }
}
}
