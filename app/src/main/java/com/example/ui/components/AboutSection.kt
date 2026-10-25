package com.example.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.BusinessCenter
import androidx.compose.material.icons.filled.Code
import androidx.compose.material.icons.filled.Groups
import androidx.compose.material.icons.filled.School
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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

@Composable
fun AboutSection(modifier: Modifier = Modifier) {
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
        text = "ABOUT THE ACADEMY",
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
      text = "Engineering Education Built for Real Careers",
      style = MaterialTheme.typography.headlineLarge.copy(
        textAlign = TextAlign.Center,
        fontWeight = FontWeight.Bold,
        color = DarkText
      ),
      modifier = Modifier.testTag("about_title")
    )

    Spacer(modifier = Modifier.height(10.dp))

    // Description
    Text(
      text = "Karka AI & Tech Academy bridges the gap between traditional college degrees and high-paying engineering careers. We replace passive textbook lectures with intensive code reviews, genuine capstone sprints, and direct placement pipelines.",
      style = MaterialTheme.typography.bodyLarge.copy(
        textAlign = TextAlign.Center,
        color = DarkTextSecondary,
        lineHeight = 22.sp
      ),
      modifier = Modifier
        .fillMaxWidth(0.95f)
        .testTag("about_description")
    )

    Spacer(modifier = Modifier.height(26.dp))

    // Three feature cards
    Column(
      modifier = Modifier.fillMaxWidth(),
      verticalArrangement = Arrangement.spacedBy(14.dp)
    ) {
      AboutFeatureCard(
        icon = Icons.Default.Code,
        title = "Production-First Curriculum",
        description = "Learn the exact tools used by senior engineering teams today—React, Node.js, Python, PyTorch, Docker, and Flutter—with Git pull-request workflows.",
        bulletPoints = listOf("Zero outdated textbook slides", "Daily coding and architectural reviews", "Industry-standard Git & CI/CD workflows")
      )

      AboutFeatureCard(
        icon = Icons.Default.Groups,
        title = "Practitioner 1-on-1 Mentorship",
        description = "Get guided by experienced software developers and tech leads who have built scalable software at top tech firms and high-growth startups.",
        bulletPoints = listOf("Daily live doubt clearing", "Personalized portfolio feedback", "Technical interview mock evaluations")
      )

      AboutFeatureCard(
        icon = Icons.Default.BusinessCenter,
        title = "Hiring Partner Placement Drives",
        description = "Our dedicated placement cell matches trained candidates directly with hiring partners, venture-funded startups, and established software enterprises.",
        bulletPoints = listOf("Curated alumni placement network", "Pay After Placement agreement option", "Resume and GitHub optimization")
      )
    }

    Spacer(modifier = Modifier.height(20.dp))

    // Campus & Verification Note
    Row(
      modifier = Modifier
        .fillMaxWidth()
        .clip(RoundedCornerShape(10.dp))
        .background(SlateBackground)
        .border(1.dp, SlateBorder, RoundedCornerShape(10.dp))
        .padding(12.dp),
      verticalAlignment = Alignment.Top
    ) {
      Icon(
        imageVector = Icons.Default.School,
        contentDescription = null,
        tint = TealDark,
        modifier = Modifier.size(16.dp)
      )
      Spacer(modifier = Modifier.width(8.dp))
      Text(
        text = "Campus & Admissions: Karka AI & Tech Academy operates in Nagercoil, Tamil Nadu, and provides both intensive in-person bootcamps and online cohorts. Connect via karka.academy or Instagram @karka.ai for campus tours and batch schedules.",
        style = MaterialTheme.typography.bodySmall.copy(
          color = DarkTextSecondary,
          fontSize = 11.sp,
          lineHeight = 16.sp
        )
      )
    }
  }
}
}

@Composable
fun AboutFeatureCard(
  icon: ImageVector,
  title: String,
  description: String,
  bulletPoints: List<String>,
  modifier: Modifier = Modifier
) {
  Card(
    shape = RoundedCornerShape(16.dp),
    colors = CardDefaults.cardColors(containerColor = SlateBackground),
    border = androidx.compose.foundation.BorderStroke(1.dp, SlateBorder),
    modifier = modifier.fillMaxWidth()
  ) {
    Column(modifier = Modifier.padding(18.dp)) {
      Row(verticalAlignment = Alignment.CenterVertically) {
        Box(
          modifier = Modifier
            .size(44.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(NavyPrimary),
          contentAlignment = Alignment.Center
        ) {
          Icon(
            imageVector = icon,
            contentDescription = null,
            tint = TealPrimary,
            modifier = Modifier.size(24.dp)
          )
        }
        Spacer(modifier = Modifier.width(14.dp))
        Text(
          text = title,
          style = MaterialTheme.typography.titleMedium.copy(
            fontWeight = FontWeight.Bold,
            color = DarkText,
            fontSize = 16.sp
          )
        )
      }

      Spacer(modifier = Modifier.height(10.dp))

      Text(
        text = description,
        style = MaterialTheme.typography.bodyMedium.copy(
          color = DarkTextSecondary,
          lineHeight = 20.sp
        )
      )

      Spacer(modifier = Modifier.height(12.dp))

      Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
        bulletPoints.forEach { point ->
          Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
              imageVector = Icons.Default.Star,
              contentDescription = null,
              tint = TealDark,
              modifier = Modifier.size(13.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
              text = point,
              style = MaterialTheme.typography.labelSmall.copy(
                fontWeight = FontWeight.SemiBold,
                color = DarkText,
                fontSize = 12.sp
              )
            )
          }
        }
      }
    }
  }
}
