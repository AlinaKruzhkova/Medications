package com.example.myfirstapplication.menu.presentation

import android.app.AlarmManager
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.provider.Settings
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
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
        checkAndRequestExactAlarmPermission(context)
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

fun checkAndRequestExactAlarmPermission(context: Context) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        if (!alarmManager.canScheduleExactAlarms()) {
            // Открыть настройки, чтобы пользователь дал разрешение
            val intent = Intent(Settings.ACTION_REQUEST_SCHEDULE_EXACT_ALARM).apply {
                data = Uri.parse("package:" + context.packageName)
            }
            context.startActivity(intent)
        }
    }
}
