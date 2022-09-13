package com.example.tripsapplicationskotlins.ui.main

import AllFragment
import android.view.LayoutInflater
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelStoreOwner
import com.example.comic.utils.base.BaseActivity
import com.example.tripsapplicationskotlins.R
import com.example.tripsapplicationskotlins.databinding.ActivityMainBinding
import com.example.tripsapplicationskotlins.ui.home.HomeFragment
import com.example.tripsapplicationskotlins.ui.search.SearchFragment
import com.example.tripsapplicationskotlins.ui.update.UpdateFragment

class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>() {

    override fun inflateViewBinding(inflater: LayoutInflater) =
        ActivityMainBinding.inflate(inflater)

    override fun initView() {

        // default status
        replaceFragment(HomeFragment())
        changeActionBar("Home")

        viewBinding.bottomNavigationView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.iHome -> {
                    replaceFragment(HomeFragment())
                    changeActionBar("Home")
                    showToast("Home")
                }
                R.id.iAll -> {
                    replaceFragment(AllFragment())
                    changeActionBar("All")
                    showToast("All")
                }
                R.id.iSearch -> {
                    replaceFragment(SearchFragment())
                    changeActionBar("Search")
                    showToast("Search")
                }
                R.id.iUpdate -> {
                    replaceFragment(UpdateFragment())
                    changeActionBar("Update")
                    showToast("Update")
                }
            }
            true
        }

    }

    /**
     * Fragment all page in activity
     *
     * @param fragment return another fragments
     */
    private fun replaceFragment(fragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frameLayout, fragment)
        fragmentTransaction.commit()
    }

    override fun initData() {}

    override fun getViewModelClass(): Class<MainViewModel> {
        return MainViewModel::class.java
    }
}