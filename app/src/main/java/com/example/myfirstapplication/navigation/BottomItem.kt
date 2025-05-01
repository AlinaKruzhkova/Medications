package com.example.myfirstapplication.navigation

import com.example.myfirstapplication.R

sealed class BottomItem(val title: String, val iconId: Int, val route: String) {
    data object MyProfile : BottomItem("My Profile", R.drawable.my_profile, "My Profile")
    data object Calendar : BottomItem("Calendar", R.drawable.calendar, "Calendar")
    data object Drug : BottomItem("Drug", R.drawable.drug, "Drug")
}