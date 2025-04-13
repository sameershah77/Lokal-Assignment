package com.sameer.lokalassignment.View


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.sameer.lokalassignment.R
import com.sameer.lokalassignment.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        replaceFragment(JobFragment())

        binding.bottomNavigation.setOnItemSelectedListener { item ->
            val itemId = item.itemId
            if (itemId == R.id.bottom_job) {
                replaceFragment(JobFragment())
            } else if (itemId == R.id.bottom_bookmark) {
                replaceFragment(BookmarkFragment())
            }
            true
        }
    }
    fun replaceFragment(fragment: Fragment) {
        val fragManager = supportFragmentManager
        val fragTransaction = fragManager.beginTransaction()
        fragTransaction.replace(R.id.fragment_container, fragment)
        fragTransaction.commit()
    }
}