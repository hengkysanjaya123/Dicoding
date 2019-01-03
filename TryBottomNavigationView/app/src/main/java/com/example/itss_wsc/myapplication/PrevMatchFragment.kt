package com.example.itss_wsc.myapplication

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast

class PrevMatchFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val v = inflater.inflate(R.layout.prevmatch_layout, container, false)
        Toast.makeText(v.context, "From Prev Fragment", Toast.LENGTH_SHORT).show()
        return v
    }
}