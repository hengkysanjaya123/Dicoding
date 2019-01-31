package com.example.itss_wsc.myapplication


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.itss_wsc.myapplication.Model.Teams
import kotlinx.android.synthetic.main.fragment_team_overview.*

class team_overview_fragment() : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_team_overview, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        if(arguments !=null){
            var description = arguments?.getString("description")
            tvTeamOverview.text = description
        }
        super.onViewCreated(view, savedInstanceState)
    }
}
