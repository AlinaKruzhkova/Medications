package com.example.myfirstapplication.navigation

import androidx.annotation.DrawableRes
import com.example.myfirstapplication.R

enum class BottomItem(val title: String, @DrawableRes val iconId: Int, val route: String) {
    Drug("Drug", R.drawable.drug, "Drug"),
    Calendar("Calendar", R.drawable.calendar, "Calendar"),
    MyProfile("My Profile", R.drawable.my_profile, "MyProfile")
}
