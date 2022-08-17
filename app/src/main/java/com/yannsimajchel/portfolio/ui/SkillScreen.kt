package com.yannsimajchel.portfolio.ui

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.sp
import com.yannsimajchel.portfolio.domain.model.SkillsData
import com.yannsimajchel.portfolio.ui.tools.ExpandableCardSkill

class SkillScreen() {
    @SuppressLint("UnusedMaterialScaffoldPaddingParameter")
    @Composable
    fun MainScreen() {

        val skillsData = SkillsData(LocalContext.current).skillsData
        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        Text(
                            text = "Skills",
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
                    items(items = skillsData, itemContent = { skill ->
                        ExpandableCardSkill(
                            header = skill.categoryName,
                            skills = skill.components,
                            imageRes = skill.icon,
                        )
                    })
                }
            }
        )

    }

}