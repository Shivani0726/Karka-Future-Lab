package com.example.ui.components

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
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.BookmarkBorder
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Payments
import androidx.compose.material.icons.filled.School
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.model.CareerTrack
import com.example.model.CareerTrackData
import com.example.ui.theme.AccentEmerald
import com.example.ui.theme.AccentEmeraldBg
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
fun CareerSection(
  activeFilter: String,
  onFilterChange: (String) -> Unit,
  searchQuery: String,
  onSearchChange: (String) -> Unit,
  bookmarkedIds: Set<String>,
  onToggleBookmark: (String) -> Unit,
  onViewDetailsClick: (CareerTrack) -> Unit,
  onEnquireTrackClick: (String) -> Unit,
  modifier: Modifier = Modifier
) {
  val categories = listOf(
    "All",
    "Web & Software",
    "AI & Data",
    "Design",
    "Mobile",
    "Cloud & Infrastructure"
  )

  val filteredTracks = CareerTrackData.allTracks.filter { track ->
    val matchesFilter = if (activeFilter == "All") true else track.category == activeFilter
    val matchesSearch = if (searchQuery.isBlank()) true else {
      track.title.contains(searchQuery, ignoreCase = true) ||
        track.shortDescription.contains(searchQuery, ignoreCase = true) ||
        track.skillsStudentsLearn.any { it.contains(searchQuery, ignoreCase = true) }
    }
    matchesFilter && matchesSearch
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
    // Section Header Pill
    Box(
      modifier = Modifier
        .clip(RoundedCornerShape(20.dp))
        .background(TealSoftBg)
        .border(1.dp, TealPrimary.copy(alpha = 0.5f), RoundedCornerShape(20.dp))
        .padding(horizontal = 14.dp, vertical = 5.dp)
    ) {
      Text(
        text = "INDUSTRY-ALIGNED TRACKS",
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
      text = "Career Tracks & Specializations",
      style = MaterialTheme.typography.headlineLarge.copy(
        textAlign = TextAlign.Center,
        fontWeight = FontWeight.Bold,
        color = DarkText
      ),
      modifier = Modifier.testTag("career_tracks_title")
    )

    Spacer(modifier = Modifier.height(10.dp))

    // Subtitle
    Text(
      text = "Explore job-ready tech curricula designed around current engineering hiring benchmarks with Pay After Placement eligibility.",
      style = MaterialTheme.typography.bodyLarge.copy(
        textAlign = TextAlign.Center,
        color = DarkTextSecondary
      ),
      modifier = Modifier
        .fillMaxWidth(0.92f)
        .testTag("career_tracks_subtitle")
    )

    Spacer(modifier = Modifier.height(20.dp))

    // Search bar
    OutlinedTextField(
      value = searchQuery,
      onValueChange = onSearchChange,
      modifier = Modifier
        .fillMaxWidth()
        .testTag("track_search_input"),
      placeholder = {
        Text("Search skills or tracks (e.g. React, Python, Flutter, Docker)...", fontSize = 13.sp)
      },
      leadingIcon = {
        Icon(imageVector = Icons.Default.Search, contentDescription = null, tint = TealDark)
      },
      trailingIcon = {
        if (searchQuery.isNotEmpty()) {
          IconButton(onClick = { onSearchChange("") }) {
            Icon(imageVector = Icons.Default.Clear, contentDescription = "Clear search", tint = DarkTextSecondary)
          }
        }
      },
      shape = RoundedCornerShape(14.dp),
      colors = OutlinedTextFieldDefaults.colors(
        focusedBorderColor = TealPrimary,
        unfocusedBorderColor = SlateBorder,
        focusedContainerColor = PureWhite,
        unfocusedContainerColor = PureWhite
      ),
      singleLine = true
    )

    Spacer(modifier = Modifier.height(14.dp))

    // Category Filter Chips
    FlowRow(
      modifier = Modifier.fillMaxWidth(),
      horizontalArrangement = Arrangement.spacedBy(8.dp),
      verticalArrangement = Arrangement.spacedBy(6.dp)
    ) {
      categories.forEach { cat ->
        val selected = activeFilter == cat
        FilterChip(
          selected = selected,
          onClick = { onFilterChange(cat) },
          label = {
            Text(
              text = cat,
              style = MaterialTheme.typography.labelSmall.copy(
                fontWeight = if (selected) FontWeight.Bold else FontWeight.Medium
              )
            )
          },
          colors = FilterChipDefaults.filterChipColors(
            selectedContainerColor = NavyPrimary,
            selectedLabelColor = PureWhite,
            containerColor = PureWhite,
            labelColor = DarkText
          ),
          border = FilterChipDefaults.filterChipBorder(
            enabled = true,
            selected = selected,
            borderColor = if (selected) NavyPrimary else SlateBorder
          ),
          shape = RoundedCornerShape(20.dp),
          modifier = Modifier.testTag("filter_chip_$cat")
        )
      }
    }

    Spacer(modifier = Modifier.height(20.dp))

    // Track Cards
    Column(
      modifier = Modifier.fillMaxWidth(),
      verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
      filteredTracks.forEach { track ->
        CareerTrackCard(
          track = track,
          isBookmarked = bookmarkedIds.contains(track.id),
          onToggleBookmark = { onToggleBookmark(track.id) },
          onViewDetails = { onViewDetailsClick(track) },
          onEnquireNow = { onEnquireTrackClick(track.title) }
        )
      }
    }

    Spacer(modifier = Modifier.height(20.dp))

    // Track Disclaimer Banner
    Row(
      modifier = Modifier
        .fillMaxWidth()
        .clip(RoundedCornerShape(10.dp))
        .background(PureWhite)
        .border(1.dp, SlateBorder, RoundedCornerShape(10.dp))
        .padding(12.dp),
      verticalAlignment = Alignment.Top
    ) {
      Icon(
        imageVector = Icons.Default.Info,
        contentDescription = null,
        tint = TealDark,
        modifier = Modifier.size(16.dp)
      )
      Spacer(modifier = Modifier.width(8.dp))
      Text(
        text = CareerTrackData.tracksDisclaimer,
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

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun CareerTrackCard(
  track: CareerTrack,
  isBookmarked: Boolean,
  onToggleBookmark: () -> Unit,
  onViewDetails: () -> Unit,
  onEnquireNow: () -> Unit,
  modifier: Modifier = Modifier
) {
  Card(
    shape = RoundedCornerShape(18.dp),
    colors = CardDefaults.cardColors(containerColor = PureWhite),
    border = androidx.compose.foundation.BorderStroke(1.2.dp, SlateBorder),
    elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
    modifier = modifier
      .fillMaxWidth()
      .testTag("track_card_${track.id}")
  ) {
    Column(modifier = Modifier.padding(18.dp)) {
      // Top Row: Icon + Category + Duration + Bookmark
      Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
      ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
          Box(
            modifier = Modifier
              .size(46.dp)
              .clip(RoundedCornerShape(12.dp))
              .background(NavyPrimary),
            contentAlignment = Alignment.Center
          ) {
            Icon(
              imageVector = track.icon,
              contentDescription = null,
              tint = TealPrimary,
              modifier = Modifier.size(24.dp)
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
                    fontWeight = FontWeight.Bold,
                    color = TealDark,
                    fontSize = 11.sp
                  )
                )
              }

              Box(
                modifier = Modifier
                  .clip(RoundedCornerShape(8.dp))
                  .background(if (track.isFlagshipOfficial) AccentEmeraldBg else SlateBackground)
                  .border(0.6.dp, if (track.isFlagshipOfficial) AccentEmerald.copy(alpha = 0.5f) else SlateBorder, RoundedCornerShape(8.dp))
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
            Spacer(modifier = Modifier.height(3.dp))
            Text(
              text = track.durationLabel,
              style = MaterialTheme.typography.labelSmall.copy(
                color = DarkTextSecondary,
                fontSize = 10.sp
              )
            )
          }
        }

        IconButton(
          onClick = onToggleBookmark,
          modifier = Modifier.testTag("bookmark_${track.id}")
        ) {
          Icon(
            imageVector = if (isBookmarked) Icons.Default.Bookmark else Icons.Default.BookmarkBorder,
            contentDescription = if (isBookmarked) "Remove bookmark" else "Bookmark track",
            tint = if (isBookmarked) TealDark else DarkTextSecondary
          )
        }
      }

      Spacer(modifier = Modifier.height(14.dp))

      // Track Title
      Text(
        text = track.title,
        style = MaterialTheme.typography.titleLarge.copy(
          fontWeight = FontWeight.Bold,
          color = DarkText,
          fontSize = 18.sp
        )
      )

      Spacer(modifier = Modifier.height(6.dp))

      // Short Description
      Text(
        text = track.shortDescription,
        style = MaterialTheme.typography.bodyMedium.copy(
          color = DarkTextSecondary,
          lineHeight = 20.sp
        )
      )

      Spacer(modifier = Modifier.height(12.dp))

      // Prerequisites / Beginner Suitability
      Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
          .fillMaxWidth()
          .clip(RoundedCornerShape(8.dp))
          .background(SlateBackground)
          .border(0.8.dp, SlateBorder, RoundedCornerShape(8.dp))
          .padding(horizontal = 10.dp, vertical = 6.dp)
      ) {
        Icon(
          imageVector = Icons.Default.Info,
          contentDescription = null,
          tint = TealDark,
          modifier = Modifier.size(15.dp)
        )
        Spacer(modifier = Modifier.width(6.dp))
        Text(
          text = track.prerequisites,
          style = MaterialTheme.typography.labelSmall.copy(
            color = DarkText,
            fontSize = 11.sp,
            fontWeight = FontWeight.Medium
          )
        )
      }

      Spacer(modifier = Modifier.height(12.dp))

      // Skills Students Can Learn
      Text(
        text = "Key Skills:",
        style = MaterialTheme.typography.labelSmall.copy(
          fontWeight = FontWeight.Bold,
          color = DarkText
        )
      )
      Spacer(modifier = Modifier.height(6.dp))
      FlowRow(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(6.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp)
      ) {
        track.skillsStudentsLearn.take(5).forEach { skill ->
          Box(
            modifier = Modifier
              .clip(RoundedCornerShape(6.dp))
              .background(TealSoftBg)
              .padding(horizontal = 8.dp, vertical = 3.dp)
          ) {
            Text(
              text = skill,
              style = MaterialTheme.typography.labelSmall.copy(
                fontWeight = FontWeight.SemiBold,
                color = TealDark,
                fontSize = 11.sp
              )
            )
          }
        }
        if (track.skillsStudentsLearn.size > 5) {
          Box(
            modifier = Modifier
              .clip(RoundedCornerShape(6.dp))
              .background(SlateBackground)
              .padding(horizontal = 6.dp, vertical = 3.dp)
          ) {
            Text(
              text = "+${track.skillsStudentsLearn.size - 5} more",
              style = MaterialTheme.typography.labelSmall.copy(
                color = DarkTextSecondary,
                fontSize = 10.sp
              )
            )
          }
        }
      }

      Spacer(modifier = Modifier.height(14.dp))

      // Possible Career Opportunities preview
      Row(verticalAlignment = Alignment.CenterVertically) {
        Text(
          text = "Roles: ",
          style = MaterialTheme.typography.labelSmall.copy(
            fontWeight = FontWeight.Bold,
            color = DarkText
          )
        )
        Text(
          text = track.careerOpportunities.joinToString(", ") { it.roleName },
          style = MaterialTheme.typography.bodySmall.copy(
            color = DarkTextSecondary,
            fontSize = 12.sp
          ),
          maxLines = 1
        )
      }

      Spacer(modifier = Modifier.height(16.dp))

      // Two Buttons: "View Details" and "Enquire Now"
      Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(10.dp)
      ) {
        OutlinedButton(
          onClick = onViewDetails,
          colors = ButtonDefaults.outlinedButtonColors(contentColor = NavyPrimary),
          border = androidx.compose.foundation.BorderStroke(1.2.dp, NavyPrimary),
          shape = RoundedCornerShape(10.dp),
          modifier = Modifier
            .weight(1f)
            .height(42.dp)
            .testTag("view_details_${track.id}")
        ) {
          Icon(imageVector = Icons.Default.Visibility, contentDescription = null, modifier = Modifier.size(16.dp))
          Spacer(modifier = Modifier.width(6.dp))
          Text("View Details", style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Bold))
        }

        Button(
          onClick = onEnquireNow,
          colors = ButtonDefaults.buttonColors(
            containerColor = TealPrimary,
            contentColor = NavyDark
          ),
          shape = RoundedCornerShape(10.dp),
          modifier = Modifier
            .weight(1f)
            .height(42.dp)
            .testTag("enquire_track_${track.id}")
        ) {
          Icon(imageVector = Icons.Default.School, contentDescription = null, modifier = Modifier.size(16.dp), tint = NavyDark)
          Spacer(modifier = Modifier.width(6.dp))
          Text("Enquire Now", style = MaterialTheme.typography.labelLarge.copy(fontWeight = FontWeight.Bold, color = NavyDark))
        }
      }
    }
  }
}
