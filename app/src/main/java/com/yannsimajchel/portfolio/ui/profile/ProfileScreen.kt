package com.yannsimajchel.portfolio.ui.profile

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.yannsimajchel.portfolio.MainActivity
import com.yannsimajchel.portfolio.R
import com.yannsimajchel.portfolio.ui.NavigationItem
import com.yannsimajchel.portfolio.ui.tools.ChipSkill
import com.yannsimajchel.portfolio.ui.tools.getDiffYears
import com.yannsimajchel.portfolio.ui.tools.modifyIf
import com.yannsimajchel.portfolio.ui.tools.noRippleClickable
import kotlinx.coroutines.launch
import me.onebone.toolbar.CollapsingToolbarScaffold
import me.onebone.toolbar.ScrollStrategy
import me.onebone.toolbar.rememberCollapsingToolbarScaffoldState
import java.util.*


class ProfileScreen(
    private val activity: MainActivity,
    private val viewModel: ProfileViewModel,
    private val navController: NavController,
) {

    /**
     *
     */
    private fun initObservers() {
        viewModel.viewModelScope.launch {
            collectEffects()
        }
    }

    /**
     *
     */
    private suspend fun collectEffects() {
        viewModel.effect.collect { effect ->
            when (effect) {
                ProfileContract.Effect.OnUserSendEmail -> sendEmail()
                ProfileContract.Effect.OnUserOpenLinkedIn -> openLinkedIn()
                ProfileContract.Effect.OnUserOpenGithub -> openGithub()
            }
        }
    }


    /**
     *
     */
    private fun updateDarkModeUI() {
        viewModel.setEvent(ProfileContract.Event.OnUserUpdateDarkMode)
    }

    /**
     * send email
     */
    private fun sendEmail() {
        try {
            val intent =
                Intent(Intent.ACTION_VIEW, Uri.parse("mailto:" + "yann.sim@outlook.com"))
            intent.putExtra(
                Intent.EXTRA_SUBJECT,
                activity.getString(R.string.profileScreen_intent_mail_subject)
            )
            intent.putExtra(
                Intent.EXTRA_TEXT,
                activity.getString(R.string.profileScreen_intent_mail_body)
            )
            activity.startActivity(intent)
        } catch (e: ActivityNotFoundException) {
            Toast.makeText(activity.applicationContext, e.message, Toast.LENGTH_SHORT).show()
        }
    }

    /**
     *
     */
    private fun openLinkedIn() {
        try {
            val intent =
                Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse("https://www.linkedin.com/in/yann-simajchel/")
                )
            activity.startActivity(intent)
        } catch (e: ActivityNotFoundException) {
            Toast.makeText(activity.applicationContext, e.message, Toast.LENGTH_SHORT).show()
        }
    }

    /**
     *
     */
    private fun openGithub() {
        try {
            val url = "https://github.com/Reybun"
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse(url)
            activity.startActivity(intent)
        } catch (e: ActivityNotFoundException) {
            Toast.makeText(activity.applicationContext, e.message, Toast.LENGTH_SHORT).show()
        }
    }


    @Composable
    fun MainScreen() {
        /**
         * observe view model
         */
        initObservers()

        val state = rememberCollapsingToolbarScaffoldState()

        CollapsingToolbarScaffold(
            modifier = Modifier
                .background(MaterialTheme.colors.primary)
                .fillMaxSize(),
            state = state,
            scrollStrategy = ScrollStrategy.ExitUntilCollapsed,
            toolbar = {
                val textSize = (20 + (30 - 18) * state.toolbarState.progress).sp

                Column(
                    modifier = Modifier
                        .background(MaterialTheme.colors.primary)
                        .fillMaxWidth()
                        .height(250.dp)
                        .road(Alignment.Center, Alignment.TopCenter),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Image(
                        painter = painterResource(R.drawable.pp),
                        contentDescription = "profile picture",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .alpha(state.toolbarState.progress)
                            .size(120.dp)
                            .clip(CircleShape)
                            .border(4.dp, MaterialTheme.colors.onPrimary, CircleShape)
                    )
                }

                Text(
                    text = "Yann Simajchel",
                    modifier = Modifier
                        .road(Alignment.TopStart, Alignment.BottomCenter)
                        .padding(16.dp),
                    color = MaterialTheme.colors.onPrimary,
                    fontWeight = FontWeight.Bold,
                    fontSize = textSize,
                )

                Image(
                    painter = painterResource(R.drawable.ic_baseline_dark_mode_24),
                    contentDescription = "dark mode",
                    colorFilter = ColorFilter.tint(color = MaterialTheme.colors.onPrimary),
                    modifier = Modifier
                        .road(Alignment.TopEnd, Alignment.TopEnd)
                        .padding(16.dp)
                        .noRippleClickable {
                            updateDarkModeUI()
                        }
                )
            }
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(12.dp, 12.dp, 0.dp, 0.dp))
                    .background(MaterialTheme.colors.surface)
                    .padding(top = 24.dp)
            ) {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    item {
                        Text(
                            modifier = Modifier.padding(
                                top = 16.dp,
                                bottom = 36.dp,
                                start = 24.dp,
                                end = 24.dp
                            ),
                            color = MaterialTheme.colors.onSurface,
                            fontStyle = FontStyle.Italic,
                            textAlign = TextAlign.Center,
                            text = stringResource(
                                R.string.profileScreen_description,
                                getDiffYears(Date(916790400000), Date())
                            )
                        )
                    }
                    item {
                        LazyRow(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            val skills = listOf(
                                "Kotlin",
                                "Compose",
                                "MVI",
                                "Clean Architecture",
                                "Datastore",
                                "Koin",
                                "Git",
                                "Material"
                            )
                            itemsIndexed(skills) { index, skill ->
                                Box(
                                    modifier = Modifier
                                        .padding(
                                            start = if (index == 0) 24.dp else 6.dp,
                                            end = if (index == skills.lastIndex) 24.dp else 0.dp,
                                        )
                                ) {
                                    ChipSkill(text = skill)
                                }
                            }
                            item {
                                Image(
                                    painter = painterResource(id = R.drawable.ic_three_dots_24),
                                    contentDescription = "last item",
                                    colorFilter = ColorFilter.tint(Color.LightGray),
                                    modifier = Modifier
                                        .padding(end = 24.dp)
                                        .noRippleClickable {
                                            navController.navigate(NavigationItem.Skills.route)
                                        },
                                )
                            }
                        }
                    }
                    item {
                        Column(modifier = Modifier.padding(vertical = 36.dp)) {
                            InformationItem(
                                text = stringResource(R.string.profileScreen_informationItem_title_developer),
                                icon = R.drawable.ic_baseline_android_24,
                                action = {},
                            )
                            InformationItem(
                                text = stringResource(R.string.profileScreen_informationItem_title_app),
                                icon = R.drawable.ic_baseline_create_24,
                                action = {},
                            )
                            InformationItem(
                                text = "Paris",
                                icon = R.drawable.ic_baseline_place_24,
                                action = {},
                            )
                            InformationItem(
                                text = stringResource(
                                    id = R.string.profileScreen_informationItem_title_age,
                                    getDiffYears(Date(916790400000), Date())
                                ),
                                icon = R.drawable.ic_baseline_calendar_today_24,
                                action = {},
                            )
                            InformationItem(
                                text = "yann.sim@outlook.com",
                                icon = R.drawable.ic_baseline_alternate_email_24,
                                action = { viewModel.setEvent(ProfileContract.Event.OnUserClickOnEmail) },
                                hasLink = true
                            )
                            InformationItem(
                                text = stringResource(R.string.profileScreen_informationItem_title_linkedin),
                                icon = R.drawable.ic_linkedin_fill_24,
                                action = { viewModel.setEvent(ProfileContract.Event.OnUserClickOnLinkedIn) },
                                hasLink = true
                            )
                            InformationItem(
                                text = stringResource(R.string.profileScreen_informationItem_title_github),
                                icon = R.drawable.ic_github_24,
                                action = { viewModel.setEvent(ProfileContract.Event.OnUserClickOnGithub) },
                                hasLink = true,
                                isLastItem = true
                            )

                        }
                    }
                }
                val colors = listOf(MaterialTheme.colors.surface, Color.Transparent)
                Box(
                    Modifier
                        .fillMaxWidth()
                        .height(24.dp)
                        .background(Brush.verticalGradient(colors))
                        .align(Alignment.TopCenter)
                )
            }

        }
    }


    @Composable
    private fun InformationItem(
        text: String,
        icon: Int,
        action: () -> Unit,
        hasLink: Boolean = false,
        isLastItem: Boolean = false,
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 24.dp, end = 24.dp, bottom = if (isLastItem) 0.dp else 24.dp)
                .noRippleClickable {
                    action.invoke()
                },
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = if (hasLink) Arrangement.SpaceBetween else Arrangement.Center,
        ) {
            Image(
                painter = painterResource(icon),
                contentDescription = "email",
                colorFilter = ColorFilter.tint(color = MaterialTheme.colors.primary),
                modifier = Modifier.size(24.dp)
            )
            Text(
                text = text,
                textAlign = TextAlign.Center,
                fontSize = 16.sp,
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .modifyIf(!hasLink) {
                        fillMaxWidth()
                    },
                maxLines = 1,
                color = MaterialTheme.colors.onSurface
            )
            if (hasLink) {
                Image(
                    painter = painterResource(R.drawable.ic_external_link_24),
                    contentDescription = "link",
                    colorFilter = ColorFilter.tint(color = MaterialTheme.colors.primary),
                    modifier = Modifier.size(16.dp)
                )
            }

        }
    }
}

