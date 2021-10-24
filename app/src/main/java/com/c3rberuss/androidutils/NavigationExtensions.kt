package com.c3rberuss.androidutils

import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.NavDirections
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
            popBackStack(graph.id, true)
            navigate(destination)
        }
    } catch (e: Exception) {
        Log.e("Navigation", "${e.message}")
    }
}

fun Fragment.navigateOff(destination: NavDirections) {

    try {
        with(findNavController()) {
            popBackStack(graph.id, true)
            navigate(destination)
        }
    } catch (e: Exception) {
        Log.e("Navigation", "${e.message}")
    }
}

fun NavController.navigateOff(destination: Int) {
    try {
        popBackStack(graph.id, true)
        navigate(destination)
    } catch (e: Exception) {
        Log.e("Navigation", "${e.message}")
    }
}

fun NavController.navigateOff(destination: NavDirections) {
    try {
        popBackStack(graph.id, true)
        navigate(destination)
    } catch (e: Exception) {
        Log.e("Navigation", "${e.message}")
    }
}