package com.beryl.seabunne.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupWithNavController
import com.beryl.seabunne.R
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val drawerInside = findViewById<NavigationView>(R.id.nav_view)
        val navHost =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        drawerLayout = findViewById(R.id.drawer_layout)
        navController = navHost.navController

        setSupportActionBar(toolbar)

        menuInflater.inflate(R.menu.nav_drawer_menu, drawerInside.menu)
        appBarConfiguration = AppBarConfiguration(drawerInside.menu, drawerLayout)
        drawerInside.setupWithNavController(navController)
        toolbar.setupWithNavController(navController, appBarConfiguration)

        drawerLayout.setStatusBarBackgroundColor(getColor(R.color.colorPrimaryDark))
    }

    override fun onSupportNavigateUp(): Boolean {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawers()
        }
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

}
