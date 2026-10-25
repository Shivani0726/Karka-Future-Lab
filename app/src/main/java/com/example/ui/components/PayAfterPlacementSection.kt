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
import androidx.compose.material.icons.filled.AssignmentTurnedIn
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Payments
import androidx.compose.material.icons.filled.Security
import androidx.compose.material.icons.filled.TrendingUp
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import com.example.model.PayAfterPlacementData
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

@Composable
fun PayAfterPlacementSection(
  onEnquirePapClick: () -> Unit,
  modifier: Modifier = Modifier
) {
  Box(
    modifier = modifier
      .fillMaxWidth()
      .background(NavyDark)
      .padding(horizontal = 20.dp, vertical = 36.dp),
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
        .background(NavySurface)
        .border(1.dp, TealPrimary.copy(alpha = 0.5f), RoundedCornerShape(20.dp))
        .padding(horizontal = 14.dp, vertical = 5.dp)
    ) {
      Text(
        text = "PAY AFTER PLACEMENT MODEL",
        style = MaterialTheme.typography.labelSmall.copy(
          fontWeight = FontWeight.Bold,
          color = TealPrimary,
          letterSpacing = 1.sp
        )
      )
    }

    Spacer(modifier = Modifier.height(14.dp))

    // Headline
    Text(
      text = PayAfterPlacementData.overviewHeadline,
      style = MaterialTheme.typography.headlineLarge.copy(
        textAlign = TextAlign.Center,
        fontWeight = FontWeight.Bold,
        color = PureWhite
      ),
      modifier = Modifier.testTag("pap_title")
    )

    Spacer(modifier = Modifier.height(10.dp))

    // Description
    Text(
      text = PayAfterPlacementData.overviewDescription,
      style = MaterialTheme.typography.bodyLarge.copy(
        textAlign = TextAlign.Center,
        color = PureWhite.copy(alpha = 0.85f),
        lineHeight = 22.sp
      ),
      modifier = Modifier
        .fillMaxWidth(0.95f)
        .testTag("pap_description")
    )

    Spacer(modifier = Modifier.height(28.dp))

    // 1. HOW THE MODEL WORKS (Numbered Steps)
    Text(
      text = "How the Model Works",
      style = MaterialTheme.typography.titleLarge.copy(
        fontWeight = FontWeight.Bold,
        color = TealPrimary,
        fontSize = 18.sp
      ),
      modifier = Modifier.fillMaxWidth()
    )

    Spacer(modifier = Modifier.height(12.dp))

    Column(
      modifier = Modifier.fillMaxWidth(),
      verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
      PayAfterPlacementData.howItWorksSteps.forEach { step ->
        Card(
          shape = RoundedCornerShape(14.dp),
          colors = CardDefaults.cardColors(containerColor = NavySurface),
          border = androidx.compose.foundation.BorderStroke(1.dp, TealPrimary.copy(alpha = 0.25f)),
          modifier = Modifier.fillMaxWidth()
        ) {
          Row(
            modifier = Modifier.padding(14.dp),
            verticalAlignment = Alignment.Top
          ) {
            Box(
              modifier = Modifier
                .size(32.dp)
                .clip(CircleShape)
                .background(TealPrimary),
              contentAlignment = Alignment.Center
            ) {
              Text(
                text = "${step.stepNumber}",
                style = MaterialTheme.typography.labelMedium.copy(
                  fontWeight = FontWeight.Bold,
                  color = NavyDark
                )
              )
            }

            Spacer(modifier = Modifier.width(12.dp))

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
                    color = PureWhite,
                    fontSize = 15.sp
                  )
                )

                Box(
                  modifier = Modifier
                    .clip(RoundedCornerShape(6.dp))
                    .background(NavyPrimary)
                    .border(0.8.dp, TealPrimary.copy(alpha = 0.4f), RoundedCornerShape(6.dp))
                    .padding(horizontal = 6.dp, vertical = 2.dp)
                ) {
                  Text(
                    text = step.highlightBadge,
                    style = MaterialTheme.typography.labelSmall.copy(
                      fontWeight = FontWeight.SemiBold,
                      color = TealPrimary,
                      fontSize = 10.sp
                    )
                  )
                }
              }

              Spacer(modifier = Modifier.height(4.dp))

              Text(
                text = step.description,
                style = MaterialTheme.typography.bodyMedium.copy(
                  color = PureWhite.copy(alpha = 0.8f),
                  lineHeight = 19.sp,
                  fontSize = 13.sp
                )
              )
            }
          }
        }
      }
    }

    Spacer(modifier = Modifier.height(28.dp))

    // 2. WHO MAY BE ELIGIBLE
    Text(
      text = "Who May Be Eligible",
      style = MaterialTheme.typography.titleLarge.copy(
        fontWeight = FontWeight.Bold,
        color = TealPrimary,
        fontSize = 18.sp
      ),
      modifier = Modifier.fillMaxWidth()
    )

    Spacer(modifier = Modifier.height(12.dp))

    Column(
      modifier = Modifier.fillMaxWidth(),
      verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
      PayAfterPlacementData.eligibilityList.forEach { item ->
        Card(
          shape = RoundedCornerShape(12.dp),
          colors = CardDefaults.cardColors(containerColor = NavySurface),
          border = androidx.compose.foundation.BorderStroke(1.dp, SlateBorder.copy(alpha = 0.15f)),
          modifier = Modifier.fillMaxWidth()
        ) {
          Row(
            modifier = Modifier.padding(12.dp),
            verticalAlignment = Alignment.Top
          ) {
            Icon(
              imageVector = Icons.Default.CheckCircle,
              contentDescription = null,
              tint = AccentEmerald,
              modifier = Modifier.size(20.dp)
            )
            Spacer(modifier = Modifier.width(10.dp))
            Column {
              Text(
                text = item.title,
                style = MaterialTheme.typography.titleSmall.copy(
                  fontWeight = FontWeight.Bold,
                  color = PureWhite
                )
              )
              Spacer(modifier = Modifier.height(2.dp))
              Text(
                text = item.description,
                style = MaterialTheme.typography.bodySmall.copy(
                  color = PureWhite.copy(alpha = 0.75f),
                  lineHeight = 17.sp
                )
              )
            }
          }
        }
      }
    }

    Spacer(modifier = Modifier.height(26.dp))

    // 3. IMPORTANT TERMS & CONDITIONS (Transparent & Accurate)
    Card(
      shape = RoundedCornerShape(14.dp),
      colors = CardDefaults.cardColors(containerColor = NavyPrimary),
      border = androidx.compose.foundation.BorderStroke(1.dp, TealPrimary.copy(alpha = 0.3f)),
      modifier = Modifier.fillMaxWidth()
    ) {
      Column(modifier = Modifier.padding(16.dp)) {
        Row(verticalAlignment = Alignment.CenterVertically) {
          Icon(
            imageVector = Icons.Default.Security,
            contentDescription = null,
            tint = TealPrimary,
            modifier = Modifier.size(20.dp)
          )
          Spacer(modifier = Modifier.width(8.dp))
          Text(
            text = "Transparent Terms & Agreement Principles",
            style = MaterialTheme.typography.titleMedium.copy(
              fontWeight = FontWeight.Bold,
              color = PureWhite
            )
          )
        }

        Spacer(modifier = Modifier.height(10.dp))

        PayAfterPlacementData.keyTerms.forEach { term ->
          Row(
            modifier = Modifier.padding(vertical = 3.dp),
            verticalAlignment = Alignment.Top
          ) {
            Icon(
              imageVector = Icons.Default.Check,
              contentDescription = null,
              tint = TealPrimary,
              modifier = Modifier
                .size(16.dp)
                .padding(top = 2.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
              text = term,
              style = MaterialTheme.typography.bodySmall.copy(
                color = PureWhite.copy(alpha = 0.85f),
                lineHeight = 18.sp
              )
            )
          }
        }

        Spacer(modifier = Modifier.height(12.dp))

        // Official disclaimer note
        Row(
          modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(8.dp))
            .background(NavySurface)
            .padding(10.dp),
          verticalAlignment = Alignment.Top
        ) {
          Icon(
            imageVector = Icons.Default.Info,
            contentDescription = null,
            tint = TealPrimary,
            modifier = Modifier.size(16.dp)
          )
          Spacer(modifier = Modifier.width(8.dp))
          Text(
            text = PayAfterPlacementData.termsDisclaimer,
            style = MaterialTheme.typography.labelSmall.copy(
              color = PureWhite.copy(alpha = 0.75f),
              fontSize = 11.sp,
              lineHeight = 16.sp
            )
          )
        }
      }
    }

    Spacer(modifier = Modifier.height(24.dp))

    // Clear Enquiry CTA Button
    Button(
      onClick = onEnquirePapClick,
      colors = ButtonDefaults.buttonColors(
        containerColor = TealPrimary,
        contentColor = NavyDark
      ),
      shape = RoundedCornerShape(12.dp),
      modifier = Modifier
        .fillMaxWidth()
        .height(48.dp)
        .testTag("enquire_pap_button")
    ) {
      Icon(
        imageVector = Icons.Default.Payments,
        contentDescription = null,
        modifier = Modifier.size(18.dp),
        tint = NavyDark
      )
      Spacer(modifier = Modifier.width(8.dp))
      Text(
        text = "Apply for Pay After Placement",
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
  }
}
}
