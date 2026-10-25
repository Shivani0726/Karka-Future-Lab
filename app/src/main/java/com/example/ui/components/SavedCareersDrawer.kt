package com.example.ui.components

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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.School
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.model.CareerTrack
import com.example.model.CareerTrackData
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SavedCareersDrawer(
  savedIds: Set<String>,
  onRemoveBookmark: (String) -> Unit,
  onExploreTrack: (CareerTrack) -> Unit,
  onDismiss: () -> Unit
) {
  val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
  val savedTracks = CareerTrackData.allTracks.filter { savedIds.contains(it.id) }

  ModalBottomSheet(
    onDismissRequest = onDismiss,
    sheetState = sheetState,
    containerColor = PureWhite,
    dragHandle = null,
    modifier = Modifier.testTag("saved_tracks_sheet")
  ) {
    Column(
      modifier = Modifier
        .fillMaxWidth()
        .widthIn(max = 680.dp)
        .align(Alignment.CenterHorizontally)
        .padding(20.dp)
    ) {
      Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
      ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
          Box(
            modifier = Modifier
              .size(36.dp)
              .clip(CircleShape)
              .background(NavyPrimary),
            contentAlignment = Alignment.Center
          ) {
            Icon(
              imageVector = Icons.Default.Bookmark,
              contentDescription = null,
              tint = TealPrimary,
              modifier = Modifier.size(18.dp)
            )
          }
          Spacer(modifier = Modifier.width(10.dp))
          Column {
            Text(
              text = "Saved Career Tracks",
              style = MaterialTheme.typography.titleMedium.copy(
                fontWeight = FontWeight.Bold,
                color = DarkText
              )
            )
            Text(
              text = "${savedTracks.size} Track(s) in Your Study Plan",
              style = MaterialTheme.typography.labelSmall.copy(color = TealDark)
            )
          }
        }

        IconButton(onClick = onDismiss, modifier = Modifier.testTag("close_saved_sheet_button")) {
          Icon(imageVector = Icons.Default.Close, contentDescription = "Close", tint = DarkText)
        }
      }

      Spacer(modifier = Modifier.height(16.dp))

      if (savedTracks.isEmpty()) {
        Card(
          shape = RoundedCornerShape(14.dp),
          colors = CardDefaults.cardColors(containerColor = SlateBackground),
          border = androidx.compose.foundation.BorderStroke(1.dp, SlateBorder),
          modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 20.dp)
        ) {
          Column(
            modifier = Modifier
              .fillMaxWidth()
              .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
          ) {
            Icon(
              imageVector = Icons.Default.Bookmark,
              contentDescription = null,
              tint = TealDark.copy(alpha = 0.5f),
              modifier = Modifier.size(40.dp)
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(
              text = "No Tracks Saved Yet",
              style = MaterialTheme.typography.titleMedium.copy(
                fontWeight = FontWeight.Bold,
                color = DarkText
              )
            )
            Spacer(modifier = Modifier.height(6.dp))
            Text(
              text = "Bookmark career tracks to compare their curricula, prerequisites, and Pay After Placement eligibility side-by-side.",
              style = MaterialTheme.typography.bodyMedium.copy(
                color = DarkTextSecondary,
                fontSize = 13.sp
              ),
              modifier = Modifier.fillMaxWidth(0.9f)
            )
          }
        }
      } else {
        LazyColumn(
          modifier = Modifier.fillMaxWidth(),
          verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
          items(savedTracks, key = { it.id }) { track ->
            Card(
              shape = RoundedCornerShape(12.dp),
              colors = CardDefaults.cardColors(containerColor = SlateBackground),
              border = androidx.compose.foundation.BorderStroke(1.dp, SlateBorder),
              modifier = Modifier.fillMaxWidth()
            ) {
              Row(
                modifier = Modifier
                  .fillMaxWidth()
                  .padding(12.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
              ) {
                Row(
                  modifier = Modifier.weight(1f),
                  verticalAlignment = Alignment.CenterVertically
                ) {
                  Box(
                    modifier = Modifier
                      .size(38.dp)
                      .clip(RoundedCornerShape(8.dp))
                      .background(NavyPrimary),
                    contentAlignment = Alignment.Center
                  ) {
                    Icon(
                      imageVector = track.icon,
                      contentDescription = null,
                      tint = TealPrimary,
                      modifier = Modifier.size(18.dp)
                    )
                  }
                  Spacer(modifier = Modifier.width(10.dp))
                  Column {
                    Text(
                      text = track.title,
                      style = MaterialTheme.typography.titleSmall.copy(
                        fontWeight = FontWeight.Bold,
                        color = DarkText
                      )
                    )
                    Text(
                      text = "${track.skillsStudentsLearn.size} skills • ${track.durationLabel}",
                      style = MaterialTheme.typography.labelSmall.copy(
                        color = DarkTextSecondary,
                        fontSize = 11.sp
                      )
                    )
                  }
                }

                Row(verticalAlignment = Alignment.CenterVertically) {
                  Button(
                    onClick = {
                      onExploreTrack(track)
                      onDismiss()
                    },
                    colors = ButtonDefaults.buttonColors(
                      containerColor = NavyPrimary,
                      contentColor = PureWhite
                    ),
                    shape = RoundedCornerShape(8.dp),
                    contentPadding = androidx.compose.foundation.layout.PaddingValues(horizontal = 10.dp, vertical = 4.dp),
                    modifier = Modifier.height(32.dp)
                  ) {
                    Text("Details", fontSize = 11.sp, fontWeight = FontWeight.Bold)
                  }

                  IconButton(onClick = { onRemoveBookmark(track.id) }) {
                    Icon(
                      imageVector = Icons.Default.Delete,
                      contentDescription = "Remove from plan",
                      tint = DarkTextSecondary,
                      modifier = Modifier.size(18.dp)
                    )
                  }
                }
              }
            }
          }
        }
      }

      Spacer(modifier = Modifier.height(20.dp))
    }
  }
}
