package com.example.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.ContactMail
import androidx.compose.material.icons.filled.EmojiEvents
import androidx.compose.material.icons.filled.Explore
import androidx.compose.material.icons.filled.HelpOutline
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Payments
import androidx.compose.material.icons.filled.School
import androidx.compose.material.icons.filled.Terminal
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.R
import com.example.model.Section
import com.example.ui.theme.DarkText
import com.example.ui.theme.DarkTextSecondary
import com.example.ui.theme.NavyDark
import com.example.ui.theme.NavyPrimary
import com.example.ui.theme.PureWhite
import com.example.ui.theme.SlateBorder
import com.example.ui.theme.TealDark
import com.example.ui.theme.TealPrimary
import com.example.ui.theme.TealSoftBg

@Composable
fun KarkaNavDrawerContent(
  currentSection: Section,
  onSectionClick: (Section) -> Unit,
  onEnquireClick: () -> Unit,
  onCloseDrawer: () -> Unit
) {
  ModalDrawerSheet(
    drawerContainerColor = NavyDark,
    drawerShape = RoundedCornerShape(topEnd = 20.dp, bottomEnd = 20.dp),
    modifier = Modifier.width(310.dp)
  ) {
    Column(
      modifier = Modifier
        .fillMaxHeight()
        .padding(20.dp),
      verticalArrangement = Arrangement.SpaceBetween
    ) {
      Column {
        // Drawer Header
        Row(
          modifier = Modifier.fillMaxWidth(),
          horizontalArrangement = Arrangement.SpaceBetween,
          verticalAlignment = Alignment.CenterVertically
        ) {
          Row(verticalAlignment = Alignment.CenterVertically) {
            Image(
              painter = painterResource(id = R.drawable.img_karka_logo),
              contentDescription = "Karka Academy Logo",
              modifier = Modifier
                .size(42.dp)
                .clip(CircleShape)
                .border(1.5.dp, TealPrimary, CircleShape),
              contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.width(10.dp))
            Column {
              Text(
                text = "Karka AI & Tech",
                style = MaterialTheme.typography.titleMedium.copy(
                  fontWeight = FontWeight.Bold,
                  color = PureWhite
                )
              )
              Text(
                text = "karka.academy",
                style = MaterialTheme.typography.labelSmall.copy(
                  color = TealPrimary,
                  fontSize = 10.sp
                )
              )
            }
          }

          IconButton(onClick = onCloseDrawer) {
            Icon(imageVector = Icons.Default.Close, contentDescription = "Close navigation", tint = PureWhite)
          }
        }

        Spacer(modifier = Modifier.height(18.dp))
        HorizontalDivider(color = PureWhite.copy(alpha = 0.15f), thickness = 1.dp)
        Spacer(modifier = Modifier.height(14.dp))

        // Navigation Items for all 8 sections
        Section.values().forEach { section ->
          val isSelected = currentSection == section
          val icon = when (section) {
            Section.HOME -> Icons.Default.Home
            Section.CAREER_TRACKS -> Icons.Default.Explore
            Section.PAY_AFTER_PLACEMENT -> Icons.Default.Payments
            Section.STUDENT_SUCCESS -> Icons.Default.EmojiEvents
            Section.PROJECTS -> Icons.Default.Terminal
            Section.ABOUT -> Icons.Default.Info
            Section.FAQ -> Icons.Default.HelpOutline
            Section.CONTACT -> Icons.Default.ContactMail
          }

          NavigationDrawerItem(
            icon = {
              Icon(
                imageVector = icon,
                contentDescription = null,
                tint = if (isSelected) TealPrimary else PureWhite.copy(alpha = 0.7f)
              )
            },
            label = {
              Text(
                text = section.title,
                style = MaterialTheme.typography.titleSmall.copy(
                  fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium,
                  color = if (isSelected) TealPrimary else PureWhite
                )
              )
            },
            selected = isSelected,
            onClick = {
              onSectionClick(section)
              onCloseDrawer()
            },
            colors = NavigationDrawerItemDefaults.colors(
              selectedContainerColor = NavyPrimary,
              unselectedContainerColor = NavyDark
            ),
            shape = RoundedCornerShape(10.dp),
            modifier = Modifier
              .padding(vertical = 2.dp)
              .testTag("drawer_item_${section.name.lowercase()}")
          )
        }
      }

      // Drawer Footer: Enquire Now Button & Instagram handle
      Column {
        Button(
          onClick = {
            onEnquireClick()
            onCloseDrawer()
          },
          colors = ButtonDefaults.buttonColors(
            containerColor = TealPrimary,
            contentColor = NavyDark
          ),
          shape = RoundedCornerShape(12.dp),
          modifier = Modifier
            .fillMaxWidth()
            .height(46.dp)
            .testTag("drawer_enquire_button")
        ) {
          Icon(
            imageVector = Icons.Default.School,
            contentDescription = null,
            modifier = Modifier.size(18.dp),
            tint = NavyDark
          )
          Spacer(modifier = Modifier.width(8.dp))
          Text("Enquire for Upcoming Batch", fontWeight = FontWeight.Bold, color = NavyDark)
        }

        Spacer(modifier = Modifier.height(10.dp))

        Text(
          text = "Official Instagram: @karka.ai",
          style = MaterialTheme.typography.labelSmall.copy(
            color = PureWhite.copy(alpha = 0.6f),
            fontSize = 11.sp
          ),
          modifier = Modifier.fillMaxWidth()
        )
      }
    }
  }
}
