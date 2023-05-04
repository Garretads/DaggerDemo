package com.example.feature.content.list.presentation

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.feature.content.list.R

data class ContentListViewHolder(
    val view: View,
    val recyclerView: RecyclerView,
) {

    companion object {

        fun create(view: View) = ContentListViewHolder(
            view = view,
            recyclerView = view.findViewById(R.id.list),
        )

    }

}
