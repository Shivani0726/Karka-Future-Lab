package com.example.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.material.icons.filled.HelpOutline
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.model.FaqData
import com.example.model.FaqItem
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
fun FaqSection(
  expandedFaqIds: Set<Int>,
  onToggleFaq: (Int) -> Unit,
  modifier: Modifier = Modifier
) {
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
        text = "COMMON ADMISSIONS QUESTIONS",
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
      text = "Frequently Asked Questions",
      style = MaterialTheme.typography.headlineLarge.copy(
        textAlign = TextAlign.Center,
        fontWeight = FontWeight.Bold,
        color = DarkText
      ),
      modifier = Modifier.testTag("faq_title")
    )

    Spacer(modifier = Modifier.height(10.dp))

    // Subtitle
    Text(
      text = "Clear answers on our career tracks, eligibility, Pay After Placement guidelines, and cohort applications.",
      style = MaterialTheme.typography.bodyLarge.copy(
        textAlign = TextAlign.Center,
        color = DarkTextSecondary
      ),
      modifier = Modifier
        .fillMaxWidth(0.92f)
        .testTag("faq_subtitle")
    )

    Spacer(modifier = Modifier.height(24.dp))

    // FAQ Accordion Cards
    Column(
      modifier = Modifier.fillMaxWidth(),
      verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
      FaqData.questions.forEach { item ->
        val isExpanded = expandedFaqIds.contains(item.id)
        FaqAccordionCard(
          item = item,
          isExpanded = isExpanded,
          onToggle = { onToggleFaq(item.id) }
        )
      }
    }
  }
}
}

@Composable
fun FaqAccordionCard(
  item: FaqItem,
  isExpanded: Boolean,
  onToggle: () -> Unit,
  modifier: Modifier = Modifier
) {
  val rotationState by animateFloatAsState(
    targetValue = if (isExpanded) 180f else 0f,
    label = "faq_rotation"
  )

  Card(
    shape = RoundedCornerShape(14.dp),
    colors = CardDefaults.cardColors(
      containerColor = PureWhite
    ),
    border = androidx.compose.foundation.BorderStroke(
      1.dp,
      if (isExpanded) TealPrimary else SlateBorder
    ),
    elevation = CardDefaults.cardElevation(defaultElevation = 1.dp),
    modifier = modifier
      .fillMaxWidth()
      .clip(RoundedCornerShape(14.dp))
      .clickable { onToggle() }
      .testTag("faq_card_${item.id}")
  ) {
    Column(modifier = Modifier.padding(16.dp)) {
      Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
      ) {
        Row(
          modifier = Modifier.weight(1f),
          verticalAlignment = Alignment.CenterVertically
        ) {
          Box(
            modifier = Modifier
              .size(30.dp)
              .clip(CircleShape)
              .background(if (isExpanded) NavyPrimary else TealSoftBg),
            contentAlignment = Alignment.Center
          ) {
            Icon(
              imageVector = Icons.Default.HelpOutline,
              contentDescription = null,
              tint = if (isExpanded) TealPrimary else TealDark,
              modifier = Modifier.size(16.dp)
            )
          }

          Spacer(modifier = Modifier.width(12.dp))

          Text(
            text = item.question,
            style = MaterialTheme.typography.titleMedium.copy(
              fontWeight = FontWeight.Bold,
              color = DarkText,
              fontSize = 15.sp
            )
          )
        }

        Icon(
          imageVector = Icons.Default.KeyboardArrowDown,
          contentDescription = if (isExpanded) "Collapse question" else "Expand question",
          tint = TealDark,
          modifier = Modifier
            .size(24.dp)
            .rotate(rotationState)
        )
      }

      AnimatedVisibility(
        visible = isExpanded,
        enter = fadeIn() + expandVertically(),
        exit = fadeOut() + shrinkVertically()
      ) {
        Column {
          Spacer(modifier = Modifier.height(10.dp))
          HorizontalDivider(color = SlateBorder, thickness = 0.8.dp)
          Spacer(modifier = Modifier.height(10.dp))
          Text(
            text = item.answer,
            style = MaterialTheme.typography.bodyMedium.copy(
              color = DarkTextSecondary,
              lineHeight = 22.sp,
              fontSize = 14.sp
            )
          )
        }
      }
    }
  }
}
