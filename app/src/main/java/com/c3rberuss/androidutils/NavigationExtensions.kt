package com.c3rberuss.androidutils

import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.NavDirections
import androidx.navigation.NavOptions
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController

fun AppCompatActivity.getNavController(id: Int): NavController {
    return (supportFragmentManager.findFragmentById(id) as NavHostFragment).navController
}

fun Fragment.getNavController(id: Int): NavController {
    return (childFragmentManager.findFragmentById(id) as NavHostFragment).navController
}

fun Fragment.navigate(destination: Int) {
    try {
        findNavController().navigate(destination)
    } catch (e: Exception) {
        Log.e("Navigation", "${e.message}")
    }
}

fun Fragment.navigate(destination: NavDirections) {
    try {
        findNavController().navigate(destination)
    } catch (e: Exception) {
        Log.e("Navigation", "${e.message}")
    }
}

fun Fragment.navigateOff(destination: Int) {

    try {
        with(findNavController()) {
            navigate(
                destination,
                null,
                NavOptions.Builder().setPopUpTo(graph.id, true).build()
            )
        }
    } catch (e: Exception) {
        Log.e("Navigation", "${e.message}")
    }
}

fun Fragment.navigateOff(destination: NavDirections) {

    try {
        with(findNavController()) {
            navigate(
                destination,
                NavOptions.Builder().setPopUpTo(graph.id, true).build()
            )
        }
    } catch (e: Exception) {
        Log.e("Navigation", "${e.message}")
    }
}

fun NavController.navigateOff(destination: Int) {
    try {
        navigate(
            destination,
            null,
            NavOptions.Builder().setPopUpTo(graph.id, true).build()
        )
    } catch (e: Exception) {
        Log.e("Navigation", "${e.message}")
    }
}

fun NavController.navigateOff(destination: NavDirections) {
    try {
        navigate(
            destination,
            NavOptions.Builder().setPopUpTo(graph.id, true).build()
        )
    } catch (e: Exception) {
        Log.e("Navigation", "${e.message}")
    }
}