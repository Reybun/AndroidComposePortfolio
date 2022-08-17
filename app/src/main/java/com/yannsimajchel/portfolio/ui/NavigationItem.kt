package com.yannsimajchel.portfolio.ui

import com.yannsimajchel.portfolio.R

sealed class NavigationItem(var route: String, var icon: Int, var title: Int) {
    object Profile : NavigationItem("profile",
        R.drawable.ic_baseline_person_24,
        R.string.navigationItem_title_profile)

    object Experiences : NavigationItem("experience",
        R.drawable.ic_baseline_work_24,
        R.string.navigationItem_title_experiences)

    object Skills : NavigationItem("skill",
        R.drawable.ic_baseline_checklist_rtl_24,
        R.string.navigationItem_title_skills)

    object Bonus : NavigationItem("bonus",
        R.drawable.ic_baseline_question_mark_24,
        R.string.navigationItem_title_bonus)
}