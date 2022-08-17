package com.yannsimajchel.portfolio.domain.model

import android.content.Context
import com.yannsimajchel.portfolio.R

class ExperienceData(context: Context) {
    private val companyOptimetriks = CompanyModel(
        name = "Optimetriks",
        role = context.getString(R.string.profileScreen_informationItem_title_developer),
        period = "2020-2022",
        missionList = listOf(
            context.getString(R.string.experienceScreen_company_mission_modularisation),
            context.getString(R.string.experienceScreen_company_mission_quality),
            context.getString(R.string.experienceScreen_company_mission_facilitating),
            context.getString(R.string.experienceScreen_company_mission_design),
            context.getString(R.string.experienceScreen_company_mission_specification),
            context.getString(R.string.experienceScreen_company_mission_test),
        ),
        skillsList = listOf(
            "Kotlin",
            "Java",
            "Couroutines Flow",
            "MVVM",
            "MVI",
            "Clean Architecture",
            "Jetpack Compose",
            "Databinding",
            "Espresso",
            "Mockk",
            "Kakao",
            "Room",
            "Retrofit",
            "Koin Injection",
            "Koin Test",
            "Git",
            context.getString(R.string.skillScreen_skill_agility),
            "Jira",
        ),
        imageRes = R.drawable.fieldpro,
    )
    private val rush = CompanyModel(
        name = "Rush",
        role = "Chef de projet et développeur",
        period = "avr. 2020 - juil. 2020",
        missionList = listOf(
            context.getString(R.string.experienceScreen_rush_desc)
        ),
        skillsList = listOf(
            context.getString(R.string.skillScreen_skill_team),
            "Google Cloud Platform",
            "Electron",
            "Javascript",
            "Steam API",
        ),
        imageRes = R.drawable.intech,
    )

    private val coroMap = CompanyModel(
        name = "CoroMap",
        role = context.getString(R.string.profileScreen_informationItem_title_developer),
        period = "mars 2020 - avr. 2020",
        missionList = listOf(
            context.getString(R.string.experienceScreen_coromap_desc1),
            context.getString(R.string.experienceScreen_coromap_desc2),

        ),
        skillsList = listOf(
            "Android",
            "GoogleMap API",
            "Java",
        ),
        imageRes = R.drawable.intech,
    )


    val workExperience = listOf(
        companyOptimetriks,
    )
    val studentExperience = listOf(
        rush,
        coroMap
    )
}