package com.example.myfirstapplication.menu.presentation

import android.Manifest
import android.app.Activity
import android.app.AlarmManager
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.provider.Settings
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.myfirstapplication.calendar.presentation.viewmodel.CalendarViewModel
import com.example.myfirstapplication.menu.presentation.viewmodel.MenuViewModel
import com.example.myfirstapplication.navigation.Graph
import com.example.myfirstapplication.profile.presentation.viewmodel.DrugViewModel
import com.example.myfirstapplication.scheme.presentation.viewmodel.SchemeViewModel

@Composable
fun MenuScreen(navController: NavController) {
    val viewModel = hiltViewModel<MenuViewModel>()
    val drugViewModel = hiltViewModel<DrugViewModel>()
    val schemeViewModel = hiltViewModel<SchemeViewModel>()
    val calendarViewModel = hiltViewModel<CalendarViewModel>()

    val drugs by drugViewModel.drugs.collectAsState()
    val schemesState by viewModel.schemesState.collectAsStateWithLifecycle()
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        checkAndRequestPermissions(context)
        calendarViewModel.scheduleAllNotifications(context)
    }



    MenuContent(
        navigate = {
            navController.navigate(Graph.FORM_CHOICE)
        },
        itemsList = schemesState,
        drugs = drugs,
        onDelete = {
            schemeViewModel.softDeleteScheme(it)
            viewModel.refreshSchemes()
        }
    )
}

fun checkAndRequestPermissions(context: Context) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        if (!alarmManager.canScheduleExactAlarms()) {
            val intent = Intent(Settings.ACTION_REQUEST_SCHEDULE_EXACT_ALARM).apply {
                data = Uri.parse("package:" + context.packageName)
                flags = Intent.FLAG_ACTIVITY_NEW_TASK
            }
            context.startActivity(intent)
        }
    }

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        val permissionCheck = ContextCompat.checkSelfPermission(
            context, Manifest.permission.POST_NOTIFICATIONS
        )

        if (permissionCheck != PackageManager.PERMISSION_GRANTED && context is Activity) {
            ActivityCompat.requestPermissions(
                context,
                arrayOf(Manifest.permission.POST_NOTIFICATIONS),
                1001 // любой requestCode
            )
        }
    }
}
