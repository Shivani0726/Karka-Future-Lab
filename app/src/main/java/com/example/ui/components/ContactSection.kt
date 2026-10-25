package com.example.ui.components

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
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
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.ContentCopy
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.OpenInNew
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Public
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.School
import androidx.compose.material.icons.filled.Send
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.model.CareerTrackData
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
import com.example.viewmodel.ContactFormState
import com.example.viewmodel.ValidatedEnquiryDraft
import java.net.URLEncoder

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun ContactSection(
  formState: ContactFormState,
  onNameChange: (String) -> Unit,
  onEmailChange: (String) -> Unit,
  onPhoneChange: (String) -> Unit,
  onTrackChange: (String) -> Unit,
  onPapInterestChange: (Boolean) -> Unit,
  onMessageChange: (String) -> Unit,
  onSubmit: () -> Unit,
  onReset: () -> Unit,
  onDeleteDraft: (String) -> Unit = {},
  modifier: Modifier = Modifier
) {
  val context = LocalContext.current
  val clipboardManager = LocalClipboardManager.current
  var trackDropdownExpanded by remember { mutableStateOf(false) }
  val availableTracks = CareerTrackData.allTracks.map { it.title }

  fun openWebUrl(url: String) {
    try {
      val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
      context.startActivity(intent)
    } catch (e: Exception) {
      Toast.makeText(context, "Could not open browser: ${e.localizedMessage}", Toast.LENGTH_SHORT).show()
    }
  }

  fun dialPhoneNumber(phoneNumber: String) {
    try {
      val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:$phoneNumber"))
      context.startActivity(intent)
    } catch (e: Exception) {
      Toast.makeText(context, "Could not launch dialer", Toast.LENGTH_SHORT).show()
    }
  }

  fun sendEmailDraft(draft: ValidatedEnquiryDraft) {
    try {
      val subject = "[Karka Academy Enrolment Enquiry] ${draft.interestedTrack} - ${draft.fullName}"
      val body = draft.toFormattedText()
      val uri = Uri.parse("mailto:admissions@karka.academy?subject=${Uri.encode(subject)}&body=${Uri.encode(body)}")
      val intent = Intent(Intent.ACTION_SENDTO, uri)
      context.startActivity(Intent.createChooser(intent, "Send Enquiry via Email"))
    } catch (e: Exception) {
      Toast.makeText(context, "No email client found. Enquiry text copied to clipboard.", Toast.LENGTH_LONG).show()
      clipboardManager.setText(AnnotatedString(draft.toFormattedText()))
    }
  }

  fun sendWhatsAppDraft(draft: ValidatedEnquiryDraft) {
    try {
      val textEncoded = URLEncoder.encode(draft.toFormattedText(), "UTF-8")
      // Official admissions contact number (+91 9876543210 / WhatsApp link)
      val url = "https://api.whatsapp.com/send?phone=919876543210&text=$textEncoded"
      val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
      context.startActivity(intent)
    } catch (e: Exception) {
      Toast.makeText(context, "Could not launch WhatsApp. Enquiry text copied to clipboard.", Toast.LENGTH_LONG).show()
      clipboardManager.setText(AnnotatedString(draft.toFormattedText()))
    }
  }

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
      // Pill
      Box(
        modifier = Modifier
          .clip(RoundedCornerShape(20.dp))
          .background(TealSoftBg)
          .border(1.dp, TealPrimary.copy(alpha = 0.5f), RoundedCornerShape(20.dp))
          .padding(horizontal = 14.dp, vertical = 5.dp)
      ) {
        Text(
          text = "ADMISSIONS & ENROLMENT",
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
        text = "Apply & Enquire for Upcoming Cohorts",
        style = MaterialTheme.typography.headlineLarge.copy(
          textAlign = TextAlign.Center,
          fontWeight = FontWeight.Bold,
          color = DarkText
        ),
        modifier = Modifier.testTag("contact_title")
      )

      Spacer(modifier = Modifier.height(10.dp))

      // Subtitle
      Text(
        text = "Validate your cohort application details below. Because this is a competition preview, you can dispatch your validated enquiry directly to admissions via Email, WhatsApp, or official links.",
        style = MaterialTheme.typography.bodyLarge.copy(
          textAlign = TextAlign.Center,
          color = DarkTextSecondary
        ),
        modifier = Modifier
          .fillMaxWidth(0.94f)
          .testTag("contact_subtitle")
      )

      Spacer(modifier = Modifier.height(20.dp))

      // Official Channel Info & Direct Contact Card
      Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = SlateBackground),
        border = androidx.compose.foundation.BorderStroke(1.dp, SlateBorder),
        modifier = Modifier.fillMaxWidth()
      ) {
        Column(modifier = Modifier.padding(16.dp)) {
          Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
          ) {
            Box(
              modifier = Modifier
                .size(42.dp)
                .clip(CircleShape)
                .background(NavyPrimary),
              contentAlignment = Alignment.Center
            ) {
              Icon(
                imageVector = Icons.Default.Public,
                contentDescription = null,
                tint = TealPrimary,
                modifier = Modifier.size(22.dp)
              )
            }
            Spacer(modifier = Modifier.width(12.dp))
            Column(modifier = Modifier.weight(1f)) {
              Text(
                text = "Karka AI & Tech Academy",
                style = MaterialTheme.typography.titleMedium.copy(
                  fontWeight = FontWeight.Bold,
                  color = DarkText
                )
              )
              Text(
                text = "Official Website: karka.academy • Instagram: @karka.ai",
                style = MaterialTheme.typography.bodySmall.copy(
                  color = DarkTextSecondary,
                  fontSize = 12.sp
                )
              )
            }
          }

          Spacer(modifier = Modifier.height(12.dp))
          HorizontalDivider(color = SlateBorder, thickness = 0.8.dp)
          Spacer(modifier = Modifier.height(12.dp))

          // Quick Touch Contact Actions
          FlowRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
          ) {
            OutlinedButton(
              onClick = { openWebUrl("https://karka.academy") },
              shape = RoundedCornerShape(10.dp),
              border = androidx.compose.foundation.BorderStroke(1.dp, SlateBorder),
              colors = ButtonDefaults.outlinedButtonColors(containerColor = PureWhite),
              contentPadding = androidx.compose.foundation.layout.PaddingValues(horizontal = 12.dp, vertical = 6.dp),
              modifier = Modifier.height(36.dp)
            ) {
              Icon(imageVector = Icons.Default.OpenInNew, contentDescription = null, modifier = Modifier.size(14.dp), tint = TealDark)
              Spacer(modifier = Modifier.width(6.dp))
              Text("karka.academy", fontSize = 11.sp, fontWeight = FontWeight.Bold, color = DarkText)
            }

            OutlinedButton(
              onClick = { openWebUrl("https://instagram.com/karka.ai") },
              shape = RoundedCornerShape(10.dp),
              border = androidx.compose.foundation.BorderStroke(1.dp, SlateBorder),
              colors = ButtonDefaults.outlinedButtonColors(containerColor = PureWhite),
              contentPadding = androidx.compose.foundation.layout.PaddingValues(horizontal = 12.dp, vertical = 6.dp),
              modifier = Modifier.height(36.dp)
            ) {
              Icon(imageVector = Icons.Default.OpenInNew, contentDescription = null, modifier = Modifier.size(14.dp), tint = TealDark)
              Spacer(modifier = Modifier.width(6.dp))
              Text("@karka.ai", fontSize = 11.sp, fontWeight = FontWeight.Bold, color = DarkText)
            }

            OutlinedButton(
              onClick = { dialPhoneNumber("+919876543210") },
              shape = RoundedCornerShape(10.dp),
              border = androidx.compose.foundation.BorderStroke(1.dp, SlateBorder),
              colors = ButtonDefaults.outlinedButtonColors(containerColor = PureWhite),
              contentPadding = androidx.compose.foundation.layout.PaddingValues(horizontal = 12.dp, vertical = 6.dp),
              modifier = Modifier.height(36.dp)
            ) {
              Icon(imageVector = Icons.Default.Call, contentDescription = null, modifier = Modifier.size(14.dp), tint = TealDark)
              Spacer(modifier = Modifier.width(6.dp))
              Text("Call Admissions", fontSize = 11.sp, fontWeight = FontWeight.Bold, color = DarkText)
            }

            OutlinedButton(
              onClick = {
                val emailIntent = Intent(Intent.ACTION_SENDTO, Uri.parse("mailto:admissions@karka.academy?subject=Cohort%20Admission%20Inquiry"))
                try {
                  context.startActivity(Intent.createChooser(emailIntent, "Email Admissions"))
                } catch (e: Exception) {
                  Toast.makeText(context, "admissions@karka.academy", Toast.LENGTH_SHORT).show()
                }
              },
              shape = RoundedCornerShape(10.dp),
              border = androidx.compose.foundation.BorderStroke(1.dp, SlateBorder),
              colors = ButtonDefaults.outlinedButtonColors(containerColor = PureWhite),
              contentPadding = androidx.compose.foundation.layout.PaddingValues(horizontal = 12.dp, vertical = 6.dp),
              modifier = Modifier.height(36.dp)
            ) {
              Icon(imageVector = Icons.Default.Email, contentDescription = null, modifier = Modifier.size(14.dp), tint = TealDark)
              Spacer(modifier = Modifier.width(6.dp))
              Text("Email Admissions", fontSize = 11.sp, fontWeight = FontWeight.Bold, color = DarkText)
            }
          }
        }
      }

      Spacer(modifier = Modifier.height(20.dp))

      // Enquiry Form Card
      Card(
        shape = RoundedCornerShape(18.dp),
        colors = CardDefaults.cardColors(containerColor = PureWhite),
        border = androidx.compose.foundation.BorderStroke(1.2.dp, SlateBorder),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        modifier = Modifier
          .fillMaxWidth()
          .testTag("contact_form_card")
      ) {
        Column(modifier = Modifier.padding(20.dp)) {
          // Transparent Backend Status Pill
          Row(
            modifier = Modifier
              .fillMaxWidth()
              .clip(RoundedCornerShape(8.dp))
              .background(SlateBackground)
              .border(0.8.dp, SlateBorder, RoundedCornerShape(8.dp))
              .padding(horizontal = 10.dp, vertical = 6.dp),
            verticalAlignment = Alignment.CenterVertically
          ) {
            Box(
              modifier = Modifier
                .size(8.dp)
                .clip(CircleShape)
                .background(TealDark)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
              text = "Competition Preview Mode • Real-time on-device validation",
              style = MaterialTheme.typography.labelSmall.copy(
                fontWeight = FontWeight.SemiBold,
                color = DarkTextSecondary,
                fontSize = 11.sp
              )
            )
          }

          Spacer(modifier = Modifier.height(14.dp))

          // Validated Draft Notice Box
          AnimatedVisibility(
            visible = formState.isValidatedLocally && formState.activeDraft != null,
            enter = fadeIn(),
            exit = fadeOut()
          ) {
            val draft = formState.activeDraft
            if (draft != null) {
              Column {
                Box(
                  modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(12.dp))
                    .background(AccentEmeraldBg)
                    .border(1.dp, AccentEmerald.copy(alpha = 0.5f), RoundedCornerShape(12.dp))
                    .padding(14.dp)
                ) {
                  Column {
                    Row(verticalAlignment = Alignment.Top) {
                      Icon(
                        imageVector = Icons.Default.CheckCircle,
                        contentDescription = null,
                        tint = AccentEmerald,
                        modifier = Modifier.size(20.dp)
                      )
                      Spacer(modifier = Modifier.width(10.dp))
                      Column {
                        Text(
                          text = "✓ Form Validated on Device (No Backend Connected)",
                          style = MaterialTheme.typography.labelLarge.copy(
                            fontWeight = FontWeight.Bold,
                            color = AccentEmerald
                          )
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                          text = "Your enquiry details have been verified and saved to device memory. Because this competition preview has no connected server or cloud database, enquiries cannot be transmitted over the internet automatically. You are NOT enrolled yet.\n\nTo officially submit your application to the Karka admissions team, choose an option below:",
                          style = MaterialTheme.typography.bodySmall.copy(
                            color = DarkText,
                            lineHeight = 18.sp,
                            fontSize = 12.sp
                          )
                        )
                      }
                    }

                    Spacer(modifier = Modifier.height(12.dp))
                    HorizontalDivider(color = AccentEmerald.copy(alpha = 0.25f), thickness = 0.8.dp)
                    Spacer(modifier = Modifier.height(12.dp))

                    // Direct Dispatch Actions
                    FlowRow(
                      modifier = Modifier.fillMaxWidth(),
                      horizontalArrangement = Arrangement.spacedBy(8.dp),
                      verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                      Button(
                        onClick = { sendEmailDraft(draft) },
                        colors = ButtonDefaults.buttonColors(containerColor = NavyPrimary, contentColor = PureWhite),
                        shape = RoundedCornerShape(8.dp),
                        contentPadding = androidx.compose.foundation.layout.PaddingValues(horizontal = 12.dp, vertical = 6.dp),
                        modifier = Modifier.height(36.dp)
                      ) {
                        Icon(imageVector = Icons.Default.Email, contentDescription = null, modifier = Modifier.size(14.dp), tint = TealPrimary)
                        Spacer(modifier = Modifier.width(6.dp))
                        Text("Send via Email", fontSize = 11.sp, fontWeight = FontWeight.Bold)
                      }

                      Button(
                        onClick = { sendWhatsAppDraft(draft) },
                        colors = ButtonDefaults.buttonColors(containerColor = TealDark, contentColor = PureWhite),
                        shape = RoundedCornerShape(8.dp),
                        contentPadding = androidx.compose.foundation.layout.PaddingValues(horizontal = 12.dp, vertical = 6.dp),
                        modifier = Modifier.height(36.dp)
                      ) {
                        Icon(imageVector = Icons.Default.Send, contentDescription = null, modifier = Modifier.size(14.dp), tint = PureWhite)
                        Spacer(modifier = Modifier.width(6.dp))
                        Text("Send via WhatsApp", fontSize = 11.sp, fontWeight = FontWeight.Bold)
                      }

                      OutlinedButton(
                        onClick = {
                          clipboardManager.setText(AnnotatedString(draft.toFormattedText()))
                          Toast.makeText(context, "Enquiry copied to clipboard!", Toast.LENGTH_SHORT).show()
                        },
                        colors = ButtonDefaults.outlinedButtonColors(contentColor = DarkText),
                        border = androidx.compose.foundation.BorderStroke(1.dp, SlateBorder),
                        shape = RoundedCornerShape(8.dp),
                        contentPadding = androidx.compose.foundation.layout.PaddingValues(horizontal = 10.dp, vertical = 6.dp),
                        modifier = Modifier.height(36.dp)
                      ) {
                        Icon(imageVector = Icons.Default.ContentCopy, contentDescription = null, modifier = Modifier.size(14.dp), tint = DarkText)
                        Spacer(modifier = Modifier.width(6.dp))
                        Text("Copy Text", fontSize = 11.sp, fontWeight = FontWeight.Medium)
                      }

                      TextButton(
                        onClick = onReset,
                        contentPadding = androidx.compose.foundation.layout.PaddingValues(horizontal = 10.dp, vertical = 6.dp),
                        modifier = Modifier.height(36.dp)
                      ) {
                        Icon(imageVector = Icons.Default.Refresh, contentDescription = null, tint = DarkTextSecondary, modifier = Modifier.size(14.dp))
                        Spacer(modifier = Modifier.width(4.dp))
                        Text("Reset Form", color = DarkTextSecondary, fontSize = 11.sp)
                      }
                    }
                  }
                }
                Spacer(modifier = Modifier.height(14.dp))
              }
            }
          }

          // 1. Full Name Field (Required, min 2 chars)
          OutlinedTextField(
            value = formState.fullName,
            onValueChange = onNameChange,
            label = { Text("Full Name *") },
            placeholder = { Text("e.g. Ramesh Kumar") },
            leadingIcon = {
              Icon(imageVector = Icons.Default.Person, contentDescription = null, tint = TealDark)
            },
            isError = formState.nameError != null,
            supportingText = {
              if (formState.nameError != null) {
                Text(text = formState.nameError, color = MaterialTheme.colorScheme.error, fontSize = 11.sp)
              }
            },
            singleLine = true,
            shape = RoundedCornerShape(10.dp),
            colors = OutlinedTextFieldDefaults.colors(
              focusedBorderColor = TealPrimary,
              unfocusedBorderColor = SlateBorder,
              focusedContainerColor = SlateBackground,
              unfocusedContainerColor = PureWhite
            ),
            modifier = Modifier
              .fillMaxWidth()
              .testTag("contact_input_name")
          )

          Spacer(modifier = Modifier.height(4.dp))

          // 2. Email Address Field (Required, valid format)
          OutlinedTextField(
            value = formState.email,
            onValueChange = onEmailChange,
            label = { Text("Email Address *") },
            placeholder = { Text("e.g. ramesh@gmail.com") },
            leadingIcon = {
              Icon(imageVector = Icons.Default.Email, contentDescription = null, tint = TealDark)
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            isError = formState.emailError != null,
            supportingText = {
              if (formState.emailError != null) {
                Text(text = formState.emailError, color = MaterialTheme.colorScheme.error, fontSize = 11.sp)
              }
            },
            singleLine = true,
            shape = RoundedCornerShape(10.dp),
            colors = OutlinedTextFieldDefaults.colors(
              focusedBorderColor = TealPrimary,
              unfocusedBorderColor = SlateBorder,
              focusedContainerColor = SlateBackground,
              unfocusedContainerColor = PureWhite
            ),
            modifier = Modifier
              .fillMaxWidth()
              .testTag("contact_input_email")
          )

          Spacer(modifier = Modifier.height(4.dp))

          // 3. Phone Number Field (Required, Indian mobile format)
          OutlinedTextField(
            value = formState.phoneNumber,
            onValueChange = onPhoneChange,
            label = { Text("Mobile Phone Number *") },
            placeholder = { Text("e.g. 9876543210 or +91 9876543210") },
            leadingIcon = {
              Icon(imageVector = Icons.Default.Phone, contentDescription = null, tint = TealDark)
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
            isError = formState.phoneError != null,
            supportingText = {
              if (formState.phoneError != null) {
                Text(text = formState.phoneError, color = MaterialTheme.colorScheme.error, fontSize = 11.sp)
              } else {
                Text("10-digit Indian mobile number for admission updates", fontSize = 11.sp, color = DarkTextSecondary)
              }
            },
            singleLine = true,
            shape = RoundedCornerShape(10.dp),
            colors = OutlinedTextFieldDefaults.colors(
              focusedBorderColor = TealPrimary,
              unfocusedBorderColor = SlateBorder,
              focusedContainerColor = SlateBackground,
              unfocusedContainerColor = PureWhite
            ),
            modifier = Modifier
              .fillMaxWidth()
              .testTag("contact_input_phone")
          )

          Spacer(modifier = Modifier.height(6.dp))

          // 4. Interested Track Dropdown (Required)
          ExposedDropdownMenuBox(
            expanded = trackDropdownExpanded,
            onExpandedChange = { trackDropdownExpanded = !trackDropdownExpanded },
            modifier = Modifier.fillMaxWidth()
          ) {
            OutlinedTextField(
              value = formState.interestedTrack,
              onValueChange = {},
              readOnly = true,
              label = { Text("Interested Career Track *") },
              leadingIcon = {
                Icon(imageVector = Icons.Default.School, contentDescription = null, tint = TealDark)
              },
              trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = trackDropdownExpanded) },
              isError = formState.trackError != null,
              supportingText = {
                if (formState.trackError != null) {
                  Text(text = formState.trackError, color = MaterialTheme.colorScheme.error, fontSize = 11.sp)
                }
              },
              shape = RoundedCornerShape(10.dp),
              colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = TealPrimary,
                unfocusedBorderColor = SlateBorder,
                focusedContainerColor = SlateBackground,
                unfocusedContainerColor = PureWhite
              ),
              modifier = Modifier
                .fillMaxWidth()
                .menuAnchor()
                .testTag("contact_input_track")
            )

            ExposedDropdownMenu(
              expanded = trackDropdownExpanded,
              onDismissRequest = { trackDropdownExpanded = false },
              modifier = Modifier.background(PureWhite)
            ) {
              availableTracks.forEach { trackName ->
                DropdownMenuItem(
                  text = { Text(text = trackName, fontSize = 13.sp, color = DarkText) },
                  onClick = {
                    onTrackChange(trackName)
                    trackDropdownExpanded = false
                  }
                )
              }
            }
          }

          Spacer(modifier = Modifier.height(6.dp))

          // Pay After Placement Interest Checkbox
          Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
              .fillMaxWidth()
              .clip(RoundedCornerShape(8.dp))
              .background(TealSoftBg)
              .padding(horizontal = 10.dp, vertical = 6.dp)
          ) {
            Checkbox(
              checked = formState.isPapInterest,
              onCheckedChange = onPapInterestChange,
              colors = CheckboxDefaults.colors(
                checkedColor = TealDark,
                checkmarkColor = PureWhite,
                uncheckedColor = TealDark
              ),
              modifier = Modifier.size(20.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
              text = "I am interested in exploring the Pay After Placement model for this track",
              style = MaterialTheme.typography.labelSmall.copy(
                color = DarkText,
                fontWeight = FontWeight.SemiBold,
                fontSize = 11.sp
              )
            )
          }

          Spacer(modifier = Modifier.height(10.dp))

          // 5. Message Field (Required, 10 to 500 chars)
          OutlinedTextField(
            value = formState.message,
            onValueChange = { if (it.length <= 500) onMessageChange(it) },
            label = { Text("Your Message or Career Goals *") },
            placeholder = { Text("Share your current college degree, graduation year, or questions for our mentors...") },
            minLines = 3,
            maxLines = 5,
            isError = formState.messageError != null,
            supportingText = {
              Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
              ) {
                if (formState.messageError != null) {
                  Text(text = formState.messageError, color = MaterialTheme.colorScheme.error, fontSize = 11.sp)
                } else {
                  Text("Min. 10 characters", fontSize = 11.sp, color = DarkTextSecondary)
                }
                Text("${formState.message.length}/500", fontSize = 11.sp, color = DarkTextSecondary)
              }
            },
            shape = RoundedCornerShape(10.dp),
            colors = OutlinedTextFieldDefaults.colors(
              focusedBorderColor = TealPrimary,
              unfocusedBorderColor = SlateBorder,
              focusedContainerColor = SlateBackground,
              unfocusedContainerColor = PureWhite
            ),
            modifier = Modifier
              .fillMaxWidth()
              .testTag("contact_input_message")
          )

          Spacer(modifier = Modifier.height(14.dp))

          // Submit & Validate Button
          Button(
            onClick = onSubmit,
            enabled = !formState.isValidating,
            colors = ButtonDefaults.buttonColors(
              containerColor = TealPrimary,
              contentColor = NavyDark
            ),
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier
              .fillMaxWidth()
              .height(48.dp)
              .testTag("contact_submit_button")
          ) {
            if (formState.isValidating) {
              CircularProgressIndicator(
                color = NavyDark,
                modifier = Modifier.size(20.dp),
                strokeWidth = 2.dp
              )
              Spacer(modifier = Modifier.width(10.dp))
              Text("Checking Form Inputs...", style = MaterialTheme.typography.labelLarge.copy(color = NavyDark))
            } else {
              Icon(
                imageVector = Icons.Default.Send,
                contentDescription = null,
                modifier = Modifier.size(18.dp),
                tint = NavyDark
              )
              Spacer(modifier = Modifier.width(8.dp))
              Text(
                text = "Validate & Submit Enquiry",
                style = MaterialTheme.typography.labelLarge.copy(
                  fontWeight = FontWeight.Bold,
                  color = NavyDark
                )
              )
            }
          }

          // Local Draft History Section
          if (formState.localEnquiriesHistory.isNotEmpty()) {
            Spacer(modifier = Modifier.height(20.dp))
            HorizontalDivider(color = SlateBorder, thickness = 0.8.dp)
            Spacer(modifier = Modifier.height(14.dp))

            Row(verticalAlignment = Alignment.CenterVertically) {
              Icon(imageVector = Icons.Default.History, contentDescription = null, tint = TealDark, modifier = Modifier.size(16.dp))
              Spacer(modifier = Modifier.width(6.dp))
              Text(
                text = "Saved Local Enquiries (${formState.localEnquiriesHistory.size})",
                style = MaterialTheme.typography.labelMedium.copy(
                  fontWeight = FontWeight.Bold,
                  color = DarkText
                )
              )
            }

            Spacer(modifier = Modifier.height(8.dp))

            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
              formState.localEnquiriesHistory.forEach { savedDraft ->
                Card(
                  shape = RoundedCornerShape(10.dp),
                  colors = CardDefaults.cardColors(containerColor = SlateBackground),
                  border = androidx.compose.foundation.BorderStroke(1.dp, SlateBorder),
                  modifier = Modifier.fillMaxWidth()
                ) {
                  Row(
                    modifier = Modifier
                      .fillMaxWidth()
                      .padding(10.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                  ) {
                    Column(modifier = Modifier.weight(1f)) {
                      Text(
                        text = "${savedDraft.fullName} • ${savedDraft.interestedTrack}",
                        style = MaterialTheme.typography.labelMedium.copy(
                          fontWeight = FontWeight.Bold,
                          color = DarkText
                        )
                      )
                      Text(
                        text = "Saved on ${savedDraft.timestamp} • Pending Dispatch",
                        style = MaterialTheme.typography.bodySmall.copy(
                          color = DarkTextSecondary,
                          fontSize = 10.sp
                        )
                      )
                    }

                    Row(verticalAlignment = Alignment.CenterVertically) {
                      IconButton(
                        onClick = { sendEmailDraft(savedDraft) },
                        modifier = Modifier.size(30.dp)
                      ) {
                        Icon(imageVector = Icons.Default.Email, contentDescription = "Send Email", tint = TealDark, modifier = Modifier.size(16.dp))
                      }
                      IconButton(
                        onClick = {
                          clipboardManager.setText(AnnotatedString(savedDraft.toFormattedText()))
                          Toast.makeText(context, "Copied enquiry draft!", Toast.LENGTH_SHORT).show()
                        },
                        modifier = Modifier.size(30.dp)
                      ) {
                        Icon(imageVector = Icons.Default.ContentCopy, contentDescription = "Copy text", tint = DarkText, modifier = Modifier.size(16.dp))
                      }
                      IconButton(
                        onClick = { onDeleteDraft(savedDraft.id) },
                        modifier = Modifier.size(30.dp)
                      ) {
                        Icon(imageVector = Icons.Default.Delete, contentDescription = "Delete draft", tint = DarkTextSecondary, modifier = Modifier.size(16.dp))
                      }
                    }
                  }
                }
              }
            }
          }
        }
      }
    }
  }
}
