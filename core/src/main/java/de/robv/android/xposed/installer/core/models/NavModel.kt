package de.robv.android.xposed.installer.core.models

import android.graphics.drawable.Drawable
import android.support.annotation.DrawableRes

data class NavModel(val pos: Int?, @DrawableRes val icon: Drawable?, val title: String?)