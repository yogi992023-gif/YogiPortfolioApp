package com.yogi.portfolio.portfolio.Activity

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import com.google.android.material.navigation.NavigationView
import com.yogi.portfolio.R
import com.yogi.portfolio.databinding.ActivityMainBinding
import com.yogi.portfolio.portfolio.ViewModel.CartViewModel
import com.yogi.portfolio.portfolio.ViewModel.WishlistBadgeViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration
    private val cartViewModel : CartViewModel by viewModels()
    private val wishlistBadgeVM: WishlistBadgeViewModel by viewModels()

    private var cartBadgeTextView: TextView? = null
    private var wishlistBadgeTextView: TextView? = null
    private var drawerToggle: ActionBarDrawerToggle? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: NavigationView = binding.navigationView
        val toolbar = binding.toolbar
        setSupportActionBar(toolbar)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeButtonEnabled(true)

        drawerToggle = ActionBarDrawerToggle(
            this,
            binding.drawerLayout,
            binding.toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        drawerToggle?.let { binding.drawerLayout.addDrawerListener(it) }
        drawerToggle?.syncState()

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController

        appBarConfiguration = AppBarConfiguration(setOf(
                R.id.homeFragment,
                R.id.printerFragment,
                R.id.settingsFragment,
                R.id.locationTrackFragment,
                R.id.remoteIRFragment,
                R.id.menuAddFragment,
                R.id.networkFragment),
            binding.drawerLayout)

        // Connect Toolbar with NavController (shows hamburger icon)
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration)

        // Connect NavigationView with NavController
        NavigationUI.setupWithNavController(navView, navController)

        setupObservers()
    }

    private fun setupObservers() {
        cartViewModel.cartItems.observe(this) { count ->
            updateCartBadge(count.size)
        }

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                wishlistBadgeVM.wishlistCount.collect { count ->
                    updateWishlistBadge(count)
                }
            }
        }
    }

    private fun updateCartBadge(count: Int) {
        cartBadgeTextView?.apply {
            text = count.toString()
            visibility = if (count > 0) View.VISIBLE else View.GONE
        }
    }

    private fun updateWishlistBadge(count: Int) {
        wishlistBadgeTextView?.apply {
            text = count.toString()
            visibility = if (count > 0) View.VISIBLE else View.GONE
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_cart, menu)

        menu?.findItem(R.id.action_cart)?.let { cartItem ->
            val actionView = cartItem.actionView
            cartBadgeTextView = actionView?.findViewById(R.id.tvBadge)
            updateCartBadge(cartViewModel.cartItems.value?.size ?: 0)
            actionView?.setOnClickListener {
                onOptionsItemSelected(cartItem)
            }
        }

        menu?.findItem(R.id.action_wishlist)?.let { wishlistItem ->
            val actionView = wishlistItem.actionView
            wishlistBadgeTextView = actionView?.findViewById(R.id.tvBadge_wish_list)
            updateWishlistBadge(wishlistBadgeVM.wishlistCount.value)
            actionView?.setOnClickListener {
                onOptionsItemSelected(wishlistItem)
            }
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

    override fun onDestroy() {
        drawerToggle?.let {
            binding.drawerLayout.removeDrawerListener(it)
            drawerToggle = null
        }
        cartBadgeTextView = null
        wishlistBadgeTextView = null
        super.onDestroy()
    }
}