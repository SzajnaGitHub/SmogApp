package com.espressoit.measurementstationlist.util

import androidx.appcompat.widget.SearchView

inline fun SearchView.onQueryTextChange(crossinline listener: (String) -> Unit) {
    setOnQueryTextListener(object : SearchView.OnQueryTextListener {

        override fun onQueryTextSubmit(query: String?): Boolean = true

        override fun onQueryTextChange(newText: String?): Boolean {
            listener.invoke(newText.orEmpty())
            return true
        }
    })
}
