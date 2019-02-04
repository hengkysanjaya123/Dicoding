package com.example.itss_wsc.myapplication

import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import android.view.*


class Favorites_Fragment : Fragment() {

    private lateinit var adapter: TabAdapter
    private lateinit var tabLayout: TabLayout
    private lateinit var viewPager: ViewPager
    private lateinit var currentFragment: FragmentSearchInterface

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.favorites_fragment, container, false)
        return view;
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewPager = view.findViewById<ViewPager>(R.id.viewPager)
        tabLayout = view.findViewById<TabLayout>(R.id.tabLayout)

        setHasOptionsMenu(true)


        adapter = TabAdapter(childFragmentManager)
        adapter.addFragment(Favorite_matchesFragment(), "MATCHES")

        adapter.addFragment(FavoriteTeamsFragment(), "TEAMS")
        viewPager.setAdapter(adapter)
        tabLayout.setupWithViewPager(viewPager)
    }
}
