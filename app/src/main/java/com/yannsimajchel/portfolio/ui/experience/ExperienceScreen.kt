package com.yannsimajchel.portfolio.ui.experience

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.yannsimajchel.portfolio.R
import com.yannsimajchel.portfolio.domain.model.ExperienceData
import com.yannsimajchel.portfolio.ui.tools.ExpandableCardExperience

class ExperienceScreen() {

    @SuppressLint("UnusedMaterialScaffoldPaddingParameter")
    @Composable
    fun MainScreen() {
        val workExperience = ExperienceData(LocalContext.current).workExperience
        val studentExperience = ExperienceData(LocalContext.current).studentExperience
        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        Text(
                            text = "Experiences",
                            fontSize = 20.sp,
                            color = MaterialTheme.colors.onPrimary,
                        )
                    },
                    backgroundColor = MaterialTheme.colors.primary
                )
            },
            content = {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(MaterialTheme.colors.surface)
                ) {
                    item {
                        Text(
                            text = stringResource(R.string.experienceScreen_category_job),
                            color = MaterialTheme.colors.onSurface,
                            fontSize = 20.sp,
                            textAlign = TextAlign.Start,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(top = 18.dp, start = 18.dp, bottom = 6.dp),

                            )
                    }
                    items(items = workExperience, itemContent = { experience ->
                        ExpandableCardExperience(
                            header = experience.name,
                            subtitle = experience.role,
                            date = experience.period,
                            description = experience.missionList,
                            skills = experience.skillsList,
                            imageRes = experience.imageRes,
                        )
                    })
                    item {
                        Text(
                            text = stringResource(R.string.experienceScreen_category_student),
                            color = MaterialTheme.colors.onSurface,
                            fontSize = 20.sp,
                            textAlign = TextAlign.Start,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(top = 18.dp, start = 18.dp, bottom = 6.dp),

                            )
                    }
                    items(items = studentExperience, itemContent = { experience ->
                        ExpandableCardExperience(
                            header = experience.name,
                            subtitle = experience.role,
                            date = experience.period,
                            description = experience.missionList,
                            skills = experience.skillsList,
                            imageRes = experience.imageRes,
                        )
                    })
                }
            }
        )

    }
}