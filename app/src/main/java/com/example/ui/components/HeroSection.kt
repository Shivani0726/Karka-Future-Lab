package com.example.ui.components

import android.content.Intent
import android.net.Uri
import androidx.compose.ui.platform.LocalContext
import androidx.compose.foundation.clickable
import androidx.compose.foundation.Image
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
import androidx.compose.material.icons.filled.AssignmentTurnedIn
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Code
import androidx.compose.material.icons.filled.Explore
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Payments
import androidx.compose.material.icons.filled.Psychology
import androidx.compose.material.icons.filled.School
import androidx.compose.material.icons.filled.TrendingUp
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.R
import com.example.ui.theme.AccentEmerald
import com.example.ui.theme.AccentEmeraldBg
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
fun HeroSection(
  onExploreTracksClick: () -> Unit,
  onExplorePapClick: () -> Unit,
  onStartQuizClick: () -> Unit,
  modifier: Modifier = Modifier
) {
  Box(
    modifier = modifier
      .fillMaxWidth()
      .background(
        brush = Brush.verticalGradient(
          colors = listOf(
            NavyPrimary,
            NavyDark,
            SlateBackground
          )
        )
      )
      .padding(horizontal = 20.dp, vertical = 28.dp),
    contentAlignment = Alignment.TopCenter
  ) {
    Column(
      modifier = Modifier
        .fillMaxWidth()
        .widthIn(max = 860.dp),
      horizontalAlignment = Alignment.CenterHorizontally
    ) {
      val context = LocalContext.current
      // Season/Brand Badge
      Surface(
        color = NavySurface,
        shape = RoundedCornerShape(30.dp),
        border = androidx.compose.foundation.BorderStroke(1.dp, TealPrimary.copy(alpha = 0.4f)),
        modifier = Modifier
          .shadow(elevation = 2.dp, shape = RoundedCornerShape(30.dp))
          .clip(RoundedCornerShape(30.dp))
          .clickable {
            try {
              context.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("https://karka.academy")))
            } catch (_: Exception) {}
          }
          .testTag("brand_tag_badge")
      ) {
        Row(
          verticalAlignment = Alignment.CenterVertically,
          modifier = Modifier.padding(horizontal = 14.dp, vertical = 6.dp)
        ) {
          Box(
            modifier = Modifier
              .size(8.dp)
              .clip(CircleShape)
              .background(TealPrimary)
          )
          Spacer(modifier = Modifier.width(8.dp))
          Text(
            text = "KARKA.ACADEMY • INSTAGRAM @KARKA.AI",
            style = MaterialTheme.typography.labelSmall.copy(
              fontWeight = FontWeight.Bold,
              color = TealPrimary,
              letterSpacing = 0.8.sp
            )
          )
        }
      }

      Spacer(modifier = Modifier.height(18.dp))

      // Main Headline
      val headlineText = buildAnnotatedString {
        append("Launch Your Tech Career with\n")
        withStyle(
          style = SpanStyle(
            color = TealPrimary,
            fontWeight = FontWeight.ExtraBold
          )
        ) {
          append("Karka AI & Tech Academy")
        }
      }

      Text(
        text = headlineText,
        style = MaterialTheme.typography.displayMedium.copy(
          textAlign = TextAlign.Center,
          fontWeight = FontWeight.Bold,
          lineHeight = 38.sp,
          color = PureWhite
        ),
        modifier = Modifier
          .fillMaxWidth()
          .testTag("hero_headline")
      )

      Spacer(modifier = Modifier.height(14.dp))

      // Concise description of career-focused technology learning approach
      Text(
        text = "Job-oriented cohorts in Full Stack Web, AI & Machine Learning, Mobile Apps, and DevOps. Learn from engineering practitioners with real internship projects and a transparent Pay After Placement model.",
        style = MaterialTheme.typography.bodyLarge.copy(
          textAlign = TextAlign.Center,
          color = PureWhite.copy(alpha = 0.85f),
          lineHeight = 22.sp
        ),
        modifier = Modifier
          .fillMaxWidth(0.96f)
          .testTag("hero_description")
      )

      Spacer(modifier = Modifier.height(22.dp))

      // Primary & Secondary CTAs
      FlowRow(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(10.dp, Alignment.CenterHorizontally),
        verticalArrangement = Arrangement.spacedBy(10.dp)
      ) {
        // Primary CTA: Explore Career Tracks
        Button(
          onClick = onExploreTracksClick,
          colors = ButtonDefaults.buttonColors(
            containerColor = TealPrimary,
            contentColor = NavyDark
          ),
          shape = RoundedCornerShape(12.dp),
          elevation = ButtonDefaults.buttonElevation(defaultElevation = 3.dp),
          modifier = Modifier
            .height(48.dp)
            .testTag("cta_explore_tracks")
        ) {
          Icon(
            imageVector = Icons.Default.Explore,
            contentDescription = null,
            modifier = Modifier.size(18.dp),
            tint = NavyDark
          )
          Spacer(modifier = Modifier.width(8.dp))
          Text(
            text = "Explore Career Tracks",
            style = MaterialTheme.typography.labelLarge.copy(
              fontWeight = FontWeight.Bold,
              color = NavyDark
            )
          )
          Spacer(modifier = Modifier.width(4.dp))
          Icon(
            imageVector = Icons.Default.KeyboardArrowRight,
            contentDescription = null,
            modifier = Modifier.size(18.dp),
            tint = NavyDark
          )
        }

        // Secondary CTA: Explore Pay After Placement
        Button(
          onClick = onExplorePapClick,
          colors = ButtonDefaults.buttonColors(
            containerColor = NavySurface,
            contentColor = TealPrimary
          ),
          border = androidx.compose.foundation.BorderStroke(1.2.dp, TealPrimary),
          shape = RoundedCornerShape(12.dp),
          modifier = Modifier
            .height(48.dp)
            .testTag("cta_explore_pap")
        ) {
          Icon(
            imageVector = Icons.Default.Payments,
            contentDescription = null,
            modifier = Modifier.size(18.dp),
            tint = TealPrimary
          )
          Spacer(modifier = Modifier.width(8.dp))
          Text(
            text = "Explore Pay After Placement",
            style = MaterialTheme.typography.labelLarge.copy(
              fontWeight = FontWeight.Bold,
              color = TealPrimary
            )
          )
        }
      }

      Spacer(modifier = Modifier.height(14.dp))

      // Discovery Quiz Quick Trigger
      OutlinedButton(
        onClick = onStartQuizClick,
        colors = ButtonDefaults.outlinedButtonColors(
          contentColor = PureWhite.copy(alpha = 0.9f)
        ),
        border = androidx.compose.foundation.BorderStroke(1.dp, PureWhite.copy(alpha = 0.3f)),
        shape = RoundedCornerShape(20.dp),
        modifier = Modifier.height(36.dp)
      ) {
        Icon(
          imageVector = Icons.Default.Psychology,
          contentDescription = null,
          modifier = Modifier.size(15.dp),
          tint = TealPrimary
        )
        Spacer(modifier = Modifier.width(6.dp))
        Text(
          text = "Unsure what to learn? Take 2-Min Career Quiz",
          fontSize = 12.sp,
          fontWeight = FontWeight.SemiBold,
          color = PureWhite
        )
      }

      Spacer(modifier = Modifier.height(24.dp))

      // Professional Visual Asset: 3D Tech Academy Visual
      Card(
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = NavySurface),
        border = androidx.compose.foundation.BorderStroke(1.5.dp, TealPrimary.copy(alpha = 0.3f)),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
        modifier = Modifier
          .fillMaxWidth()
          .testTag("hero_visual_card")
      ) {
        Column {
          Box {
            Image(
              painter = painterResource(id = R.drawable.img_karka_hero),
              contentDescription = "Students developing AI software at Karka Tech Academy",
              contentScale = ContentScale.Crop,
              modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
            )

            // Floating Badge 1: Pay After Placement
            Surface(
              color = NavyDark.copy(alpha = 0.92f),
              shape = RoundedCornerShape(16.dp),
              border = androidx.compose.foundation.BorderStroke(1.dp, TealPrimary),
              modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(10.dp)
            ) {
              Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp)
              ) {
                Icon(
                  imageVector = Icons.Default.CheckCircle,
                  contentDescription = null,
                  tint = AccentEmerald,
                  modifier = Modifier.size(14.dp)
                )
                Spacer(modifier = Modifier.width(6.dp))
                Text(
                  text = "Pay After Placement Option",
                  style = MaterialTheme.typography.labelSmall.copy(
                    fontWeight = FontWeight.Bold,
                    color = PureWhite,
                    fontSize = 11.sp
                  )
                )
              }
            }

            // Floating Badge 2: Practical Capstone Projects
            Surface(
              color = NavyDark.copy(alpha = 0.92f),
              shape = RoundedCornerShape(16.dp),
              border = androidx.compose.foundation.BorderStroke(1.dp, TealPrimary),
              modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(10.dp)
            ) {
              Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp)
              ) {
                Icon(
                  imageVector = Icons.Default.Code,
                  contentDescription = null,
                  tint = TealPrimary,
                  modifier = Modifier.size(14.dp)
                )
                Spacer(modifier = Modifier.width(6.dp))
                Text(
                  text = "Practical Capstone Projects",
                  style = MaterialTheme.typography.labelSmall.copy(
                    fontWeight = FontWeight.Bold,
                    color = PureWhite,
                    fontSize = 11.sp
                  )
                )
              }
            }
          }
        }
      }

      Spacer(modifier = Modifier.height(24.dp))

      // Key Highlights: Career Tracks, Practical Projects, Placement-Focused Learning
      Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(12.dp)
      ) {
        HeroHighlightCard(
          icon = Icons.Default.School,
          title = "Industry-Aligned Career Tracks",
          subtitle = "Curated fullstack web, AI/ML, data analytics, QA automation, and design tracks matching modern hiring bars."
        )
        HeroHighlightCard(
          icon = Icons.Default.AssignmentTurnedIn,
          title = "Capstone & Portfolio Projects",
          subtitle = "Develop production-standard software repositories and portfolio applications to prepare for industry interviews."
        )
        HeroHighlightCard(
          icon = Icons.Default.TrendingUp,
          title = "Placement-Focused Mentorship",
          subtitle = "1-on-1 code reviews, resume enhancement, algorithmic interview preparation, and direct hiring partner referrals."
        )
      }
    }
  }
}

@Composable
fun HeroHighlightCard(
  icon: ImageVector,
  title: String,
  subtitle: String,
  modifier: Modifier = Modifier
) {
  Card(
    shape = RoundedCornerShape(16.dp),
    colors = CardDefaults.cardColors(containerColor = PureWhite),
    border = androidx.compose.foundation.BorderStroke(1.dp, SlateBorder),
    elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
    modifier = modifier.fillMaxWidth()
  ) {
    Row(
      modifier = Modifier
        .fillMaxWidth()
        .padding(14.dp),
      verticalAlignment = Alignment.CenterVertically
    ) {
      Box(
        modifier = Modifier
          .size(44.dp)
          .clip(RoundedCornerShape(12.dp))
          .background(TealSoftBg),
        contentAlignment = Alignment.Center
      ) {
        Icon(
          imageVector = icon,
          contentDescription = null,
          tint = TealDark,
          modifier = Modifier.size(24.dp)
        )
      }
      Spacer(modifier = Modifier.width(14.dp))
      Column(modifier = Modifier.weight(1f)) {
        Text(
          text = title,
          style = MaterialTheme.typography.titleMedium.copy(
            fontWeight = FontWeight.Bold,
            color = DarkText,
            fontSize = 15.sp
          )
        )
        Spacer(modifier = Modifier.height(3.dp))
        Text(
          text = subtitle,
          style = MaterialTheme.typography.bodyMedium.copy(
            color = DarkTextSecondary,
            fontSize = 13.sp,
            lineHeight = 18.sp
          )
        )
      }
    }
  }
}
