package com.example.tripsapplicationskotlins.utils.exts

import android.widget.EditText

val EditText.value
    get() = text.toString()