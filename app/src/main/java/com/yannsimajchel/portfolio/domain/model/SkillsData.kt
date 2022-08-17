package com.yannsimajchel.portfolio.domain.model

import android.content.Context
import com.yannsimajchel.portfolio.R

class SkillsData(context: Context) {
    private val androidSkills = listOf(
        "Kotlin",
        "Java",
        "Couroutines Flow",
        "Jetpack Compose",
        "Databinding",
        "Espresso",
        "Mockk",
        "Kakao",
        "Turbine",
        "Robolectric",
        "Room",
        "Datastore",
        "Retrofit",
        "Koin Injection",
        "Koin Test",
        "Git",
    )

    private val architectureSkills = listOf(
        "MVVM",
        "MVI",
        "Clean Architecture",
    )

    private val serverSkills = listOf(
        "Node.JS",
        "C# ASP.NET",
        "Spring",
        "Express",
        "Docker + Kubernetes",
        "Google Cloud Platform (GCP)",
    )

    private val webSkills = listOf(
        "HTML",
        "CSS",
        "JS",
        "Vue.JS",
        "PHP",
        "Bootstrap"
    )

    private val knowledgeSkills = listOf(
        "Flutter",
        "Swift",
        "SQLite",
        "Worker",
    )

    private val lifeSkills = listOf(
        context.getString(R.string.skillScreen_skill_smiling),
        context.getString(R.string.skillScreen_skill_curious),
        context.getString(R.string.skillScreen_skill_gardening),
    )

    private val managementSkills = listOf(
        context.getString(R.string.skillScreen_skill_agility),
        "Jira",
        context.getString(R.string.skillScreen_skill_team),
        "Planning",
        context.getString(R.string.skillScreen_skill_quotation),
        context.getString(R.string.skillScreen_skill_invoicing),
        context.getString(R.string.skillScreen_skill_retroplanning)
    )

    val skillsData = listOf(
        SkillModel("Android", androidSkills, R.drawable.ic_baseline_android_24),
        SkillModel("Architecture", architectureSkills, R.drawable.ic_baseline_cleaning_services_24),
        SkillModel("Management", managementSkills, R.drawable.ic_baseline_manage_accounts_24),
        SkillModel("Web", webSkills, R.drawable.ic_baseline_computer_24),
        SkillModel("Server", serverSkills, R.drawable.ic_baseline_cloud_queue_24),
        SkillModel("Knowledge", knowledgeSkills, R.drawable.ic_baseline_lightbulb_24),
        SkillModel("Life", lifeSkills, R.drawable.ic_baseline_nightlife_24)
    )
}