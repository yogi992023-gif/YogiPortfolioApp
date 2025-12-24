package com.yogi.portfolio.portfolio.Activity

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import com.google.android.material.navigation.NavigationView
import com.yogi.portfolio.R
import com.yogi.portfolio.databinding.ActivityMainBinding
import com.yogi.portfolio.portfolio.ViewModel.CartViewModel
import com.yogi.portfolio.portfolio.ViewModel.WishlistBadgeViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.observeOn
import kotlinx.coroutines.launch
import kotlin.getValue

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration
    private val cartViewModel : CartViewModel by viewModels()

    private val wishlistBadgeVM: WishlistBadgeViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val drawerLayout: DrawerLayout = binding.drawerLayout
        val navView: NavigationView = binding.navigationView
        val toolbar = binding.toolbar
        setSupportActionBar(toolbar)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeButtonEnabled(true)

        val toggle = ActionBarDrawerToggle(
            this,
            binding.drawerLayout,
            binding.toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        binding.drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController

        appBarConfiguration = AppBarConfiguration(setOf(
                R.id.homeFragment,
                R.id.printerFragment,
                R.id.settingsFragment,
                R.id.locationTrackFragment,
                R.id.remoteIRFragment,
                R.id.menuAddFragment),
            binding.drawerLayout)

        // Connect Toolbar with NavController (shows hamburger icon)
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration)

        // Connect NavigationView with NavController
        NavigationUI.setupWithNavController(navView, navController)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_cart, menu)

        val item = menu?.findItem(R.id.action_cart)
        val actionView = item?.actionView
        val badgeTextView = actionView?.findViewById<TextView>(R.id.tvBadge)

        cartViewModel.cartItems.observe(this) { count ->
            badgeTextView?.text = count.size.toString()
            badgeTextView?.visibility = if (count.size > 0) View.VISIBLE else View.GONE
        }

        val item1 = menu?.findItem(R.id.action_wishlist)
        val actionView1 = item1?.actionView
        val badgeWishList = actionView1?.findViewById<TextView>(R.id.tvBadge_wish_list)

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                wishlistBadgeVM.wishlistCount.collect { count ->
                    badgeWishList?.text = count.toString()
                    badgeWishList?.visibility =
                        if (count > 0) View.VISIBLE else View.GONE
                }
            }
        }


        actionView?.setOnClickListener {
            onOptionsItemSelected(item)
        }

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_cart -> {
                navController.navigate(R.id.cartFragment)
                true
            }
            R.id.action_dashboard -> {
                navController.navigate(R.id.dashboardFragment)
                true
            }
            R.id.action_wishlist -> {
                navController.navigate(R.id.wishlistFragment)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }


    // Ensure the drawer icon opens the drawer
    override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(navController, appBarConfiguration) || super.onSupportNavigateUp()
    }
}