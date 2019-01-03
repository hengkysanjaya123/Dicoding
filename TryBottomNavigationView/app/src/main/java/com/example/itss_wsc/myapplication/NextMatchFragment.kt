package com.example.itss_wsc.myapplication

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast

class NextMatchFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val v = inflater.inflate(R.layout.nextmatch_layout, container, false)

        Toast.makeText(v.context, "From Next Fragment", Toast.LENGTH_SHORT).show()

        return v
    }
}