package com.example.myfirstapplication.navigation

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemColors
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.myfirstapplication.R
import com.example.myfirstapplication.ui.theme.DarkBurgundy
import com.example.myfirstapplication.ui.theme.GrayPink
import com.example.myfirstapplication.ui.theme.Pink
import com.example.myfirstapplication.ui.theme.White


@Composable
fun HomeScreen(navController: NavHostController) {
    val bottomNavController = rememberNavController()
    Scaffold(
        bottomBar = {
            BottomBar(navController = bottomNavController)
        },
        modifier = Modifier.background(Pink)
    ) { padding ->
        Box(
            modifier = Modifier
                .padding(padding)
                .background(Pink)
        ) {
            HomeNavGraph(bottomNavController, navController)
        }
    }
}


@Composable
fun BottomBar(navController: NavHostController) {

    val screens = BottomItem.entries.toTypedArray()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    val shouldShowBottomBar = screens.any { it.route == currentDestination?.route }

    if (shouldShowBottomBar) {
        NavigationBar(
            containerColor = GrayPink,
            contentColor = DarkBurgundy,
            tonalElevation = 100.dp
        ) {
            screens.forEach { screen ->

                val isSelected = currentDestination?.hierarchy?.any {
                    it.route == screen.route
                } == true

                val textColor by animateColorAsState(
                    targetValue = if (isSelected) colorResource(R.color.white) else DarkBurgundy,
                    label = "textColor",
                    animationSpec = tween(durationMillis = 300)
                )

                NavigationBarItem(
                    selected = isSelected,
                    onClick = {
                        navController.navigate(screen.route) {
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }, icon = {
                        BottomBarItem(
                            selected = isSelected,
                            iconId = screen.iconId
                        )
                    }, label = {
                        CustomText(
                            selected = isSelected,
                            title = screen.title
                        )
                    },
                    colors = NavigationBarItemColors(
                        selectedIconColor = Color.Unspecified,
                        selectedTextColor = Color.Unspecified,
                        selectedIndicatorColor = Color.Transparent,
                        unselectedIconColor = Color.Unspecified,
                        unselectedTextColor = Color.Unspecified,
                        disabledIconColor = Color.Unspecified,
                        disabledTextColor = Color.Unspecified
                    )
                )
            }
        }
    }
}

@Composable
fun BottomBarItem(
    selected: Boolean,
    iconId: Int
) {
    Icon(
        painter = painterResource(id = iconId),
        contentDescription = "Icon",
        tint = animatedColor(selected).value
    )
}

@Composable
fun CustomText(
    selected: Boolean,
    title: String
) {
    Text(
        text = title,
        fontSize = 9.sp,
        color = animatedColor(selected).value
    )
}

@Composable
fun animatedColor(isSelected: Boolean): State<Color> {
    return animateColorAsState(
        targetValue = if (isSelected) White else DarkBurgundy,
        animationSpec = tween(durationMillis = 700, delayMillis = 200),
        label = "animatedColor"
    )
}
