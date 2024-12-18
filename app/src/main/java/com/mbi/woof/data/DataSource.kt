package com.mbi.woof.data

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class DataSource(
    @DrawableRes val imageResourceId: Int,
    @StringRes   val stringResourceId: Int,
    val age:Int,
    @StringRes val hobbies: Int
)
