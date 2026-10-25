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
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.School
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.R
import com.example.model.Section
import com.example.ui.theme.DarkText
import com.example.ui.theme.NavyPrimary
import com.example.ui.theme.PureWhite
import com.example.ui.theme.SlateBackground
import com.example.ui.theme.SlateBorder
import com.example.ui.theme.TealDark
import com.example.ui.theme.TealPrimary
import com.example.ui.theme.TealSoftBg

@Composable
fun KarkaNavBar(
  currentSection: Section,
  onSectionClick: (Section) -> Unit,
  onEnquireNowClick: () -> Unit,
  onOpenMenuClick: () -> Unit,
  savedCount: Int,
  onOpenSavedClick: () -> Unit,
  modifier: Modifier = Modifier
) {
  Column(
    modifier = modifier
      .fillMaxWidth()
      .background(NavyPrimary)
      .shadow(elevation = 4.dp),
    horizontalAlignment = Alignment.CenterHorizontally
  ) {
    // Top Row: Brand Logo, Name, Quick Actions, Enquire Now, Hamburger
    Row(
      modifier = Modifier
        .fillMaxWidth()
        .widthIn(max = 1100.dp)
        .padding(horizontal = 16.dp, vertical = 10.dp),
      verticalAlignment = Alignment.CenterVertically,
      horizontalArrangement = Arrangement.SpaceBetween
    ) {
      // Brand Logo & Title
      Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
          .clip(RoundedCornerShape(8.dp))
          .clickable { onSectionClick(Section.HOME) }
          .padding(4.dp)
          .testTag("brand_logo_button")
      ) {
        Image(
          painter = painterResource(id = R.drawable.img_karka_logo),
          contentDescription = "Karka AI & Tech Academy Logo",
          modifier = Modifier
            .size(38.dp)
            .clip(CircleShape)
            .border(1.5.dp, TealPrimary, CircleShape),
          contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.width(10.dp))
        Column {
          Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
              text = "Karka ",
              style = MaterialTheme.typography.titleMedium.copy(
                fontWeight = FontWeight.ExtraBold,
                color = PureWhite,
                fontSize = 17.sp
              )
            )
            Text(
              text = "AI Academy",
              style = MaterialTheme.typography.titleMedium.copy(
                fontWeight = FontWeight.ExtraBold,
                color = TealPrimary,
                fontSize = 17.sp
              )
            )
          }
          Text(
            text = "karka.academy • @karka.ai",
            style = MaterialTheme.typography.labelSmall.copy(
              color = TealPrimary.copy(alpha = 0.85f),
              fontSize = 9.sp,
              letterSpacing = 0.5.sp
            )
          )
        }
      }

      // Actions
      Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
      ) {
        // Saved Tracks Bookmark Button with Badge
        IconButton(
          onClick = onOpenSavedClick,
          modifier = Modifier.testTag("saved_tracks_button")
        ) {
          BadgedBox(
            badge = {
              if (savedCount > 0) {
                Badge(
                  containerColor = TealPrimary,
                  contentColor = NavyPrimary
                ) {
                  Text(text = "$savedCount", fontSize = 10.sp, fontWeight = FontWeight.Bold)
                }
              }
            }
          ) {
            Icon(
              imageVector = Icons.Default.Bookmark,
              contentDescription = "Saved Tracks",
              tint = if (savedCount > 0) TealPrimary else PureWhite.copy(alpha = 0.8f)
            )
          }
        }

        // Enquire Now Button (Vibrant Teal)
        Button(
          onClick = onEnquireNowClick,
          colors = ButtonDefaults.buttonColors(
            containerColor = TealPrimary,
            contentColor = NavyPrimary
          ),
          shape = RoundedCornerShape(20.dp),
          contentPadding = androidx.compose.foundation.layout.PaddingValues(horizontal = 14.dp, vertical = 6.dp),
          modifier = Modifier
            .height(38.dp)
            .testTag("enquire_nav_button")
        ) {
          Icon(
            imageVector = Icons.Default.School,
            contentDescription = null,
            modifier = Modifier.size(16.dp),
            tint = NavyPrimary
          )
          Spacer(modifier = Modifier.width(6.dp))
          Text(
            text = "Enquire Now",
            style = MaterialTheme.typography.labelLarge.copy(
              fontWeight = FontWeight.Bold,
              color = NavyPrimary
            )
          )
        }

        // Mobile Hamburger Menu Button
        IconButton(
          onClick = onOpenMenuClick,
          modifier = Modifier.testTag("hamburger_menu_button")
        ) {
          Icon(
            imageVector = Icons.Default.Menu,
            contentDescription = "Open Navigation Menu",
            tint = PureWhite
          )
        }
      }
    }

    // Scrollable navigation pill tab bar in Navy surface
    ScrollableTabRow(
      selectedTabIndex = currentSection.index,
      containerColor = NavyPrimary,
      contentColor = PureWhite,
      edgePadding = 12.dp,
      indicator = { tabPositions ->
        if (currentSection.index < tabPositions.size) {
          TabRowDefaults.SecondaryIndicator(
            modifier = Modifier.tabIndicatorOffset(tabPositions[currentSection.index]),
            height = 3.dp,
            color = TealPrimary
          )
        }
      },
      divider = {
        HorizontalDivider(color = TealPrimary.copy(alpha = 0.2f), thickness = 1.dp)
      },
      modifier = Modifier
        .fillMaxWidth()
        .widthIn(max = 1100.dp)
    ) {
      Section.values().forEach { section ->
        val isSelected = currentSection == section
        Tab(
          selected = isSelected,
          onClick = { onSectionClick(section) },
          text = {
            Text(
              text = section.title,
              style = MaterialTheme.typography.labelMedium.copy(
                fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium,
                color = if (isSelected) TealPrimary else PureWhite.copy(alpha = 0.75f)
              ),
              modifier = Modifier.padding(horizontal = 4.dp, vertical = 6.dp)
            )
          },
          modifier = Modifier.testTag("nav_tab_${section.name.lowercase()}")
        )
      }
    }
  }
}
