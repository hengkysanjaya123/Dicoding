package com.example.itss_wsc.myapplication

import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import android.support.v7.widget.SearchView
import android.util.Log
import android.view.*


class MatchesFragment : Fragment() {

    private lateinit var adapter: TabAdapter
    private lateinit var tabLayout: TabLayout
    private lateinit var viewPager: ViewPager
    private lateinit var currentFragment: FragmentSearchInterface

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.matches_fragment, container, false)
        return view;
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewPager = view.findViewById<ViewPager>(R.id.viewPager)
        tabLayout = view.findViewById<TabLayout>(R.id.tabLayout)

        setHasOptionsMenu(true)


        adapter = TabAdapter(childFragmentManager)
        adapter.addFragment(LastMatchFragment(), "Last Match")
        adapter.addFragment(NextMatchFragment(), "Next Match")
        viewPager.setAdapter(adapter)
        tabLayout.setupWithViewPager(viewPager)
//        viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
//
//            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
//
//            }
//
//            override fun onPageSelected(position: Int) {
//                // Here's your instance
//                currentFragment = adapter.getItem(position) as FragmentSearchInterface
//            }
//
//            override fun onPageScrollStateChanged(state: Int) {
//
//            }
//        })
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.search_menu, menu)

        val searchView = menu?.findItem(R.id.actionSearch)?.actionView as SearchView?

        searchView?.queryHint = "Search matches"

        searchView?.setOnQueryTextListener(object : android.support.v7.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
//                currentFragment = adapter.getItem(viewPager.currentItem) as FragmentSearchInterface
//                currentFragment.OnSearch(query);
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                currentFragment = adapter.getItem(viewPager.currentItem) as FragmentSearchInterface
                currentFragment.OnSearch(newText);
                return false
            }
        })

    }
}
