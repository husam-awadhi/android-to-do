package com.husam.to_do

import android.app.AlertDialog
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.husam.to_do.databinding.ActivityMainBinding
import com.husam.to_do.fragments.add.AddTodoDialog
import com.husam.to_do.models.TaskViewModel
import com.husam.to_do.providers.DatabaseProvider


class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Initialize Database.
        DatabaseProvider().initDatabase(this)

        _binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        val navView: BottomNavigationView = findViewById(R.id.nav_view)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController


        binding.floatingActionButton.setOnClickListener {
            AddTodoDialog().show(supportFragmentManager, AddTodoDialog.TAG)
        }
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.homeFragment, R.id.navigation_search, R.id.navigation_history
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp() || super.onSupportNavigateUp()
    }

    //top menu inflate.
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.top_bar_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val builder = AlertDialog.Builder(this)

        if (item.itemId == R.id.menu_delete_all) {
            builder.setPositiveButton(getString(R.string.yes)) { _, _ ->

                val message: String =
                    if (!TaskViewModel(application).deleteAll()) "Error While Deleting Task objects"
                    else "Data Cleared!"

                Toast.makeText(application, message, Toast.LENGTH_SHORT).show()
            }
                .setTitle(getString(R.string.delete_all))
                .setMessage(getString(R.string.confirmation))
        }

        builder.setNegativeButton(getString(R.string.no)) { _, _ -> }
            .create()
            .show()
        return super.onOptionsItemSelected(item)
    }


    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }
}
