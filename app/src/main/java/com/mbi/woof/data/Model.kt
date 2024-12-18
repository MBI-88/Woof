package com.mbi.woof.data

import com.mbi.woof.R

class Model() {

    fun LoadResources(): List<DataSource> {
        return listOf<DataSource>(
            DataSource(R.drawable.koda, R.string.dog_name_1, 2, R.string.dog_description_1),
            DataSource(R.drawable.lola, R.string.dog_name_2, 16, R.string.dog_description_2),
            DataSource(R.drawable.frankie, R.string.dog_name_3, 2, R.string.dog_description_3),
            DataSource(R.drawable.nox, R.string.dog_name_4, 8, R.string.dog_description_4),
            DataSource(R.drawable.faye, R.string.dog_name_5, 8, R.string.dog_description_5),
            DataSource(R.drawable.bella, R.string.dog_name_6, 14, R.string.dog_description_6),
            DataSource(R.drawable.moana, R.string.dog_name_7, 2, R.string.dog_description_7),
            DataSource(R.drawable.tzeitel, R.string.dog_name_8, 7, R.string.dog_description_8),
            DataSource(R.drawable.leroy, R.string.dog_name_9, 4, R.string.dog_description_9)
        )
    }
}
