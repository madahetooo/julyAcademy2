package com.bamboogeeks.julyacademy2.navigationdrawerapp

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Telephony.Mms.Inbox
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import com.bamboogeeks.julyacademy2.R
import com.bamboogeeks.julyacademy2.auth.LoginScreen
import com.bamboogeeks.julyacademy2.databinding.ActivityNavigationDrawerAppBinding
import com.bamboogeeks.julyacademy2.navigationdrawerapp.fragments.DraftsFragment
import com.bamboogeeks.julyacademy2.navigationdrawerapp.fragments.InboxFragment
import com.bamboogeeks.julyacademy2.navigationdrawerapp.fragments.SentEmailFragment
import com.bamboogeeks.julyacademy2.navigationdrawerapp.fragments.StarredFragment

class NavigationDrawerAppActivity : AppCompatActivity() {
    private lateinit var binding: ActivityNavigationDrawerAppBinding
    lateinit var toggle: ActionBarDrawerToggle
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNavigationDrawerAppBinding.inflate(layoutInflater)
        setContentView(binding.root)

        toggle = ActionBarDrawerToggle(this, binding.drawerLayout, R.string.open, R.string.close)
        binding.drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding.navView.setNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.miInbox -> {
                    val inboxFragment = InboxFragment()
                    setCurrentFragment(inboxFragment)
                    binding.drawerLayout.close()
                }

                R.id.miStarred -> {
                    val starredFragment = StarredFragment()
                    setCurrentFragment(starredFragment)
                    binding.drawerLayout.close()
                }

                R.id.miSentEmail -> {
                    val sentEmailFragment = SentEmailFragment()
                    setCurrentFragment(sentEmailFragment)
                    binding.drawerLayout.close()
                }

                R.id.miDrafts -> {
                    val draftFragment = DraftsFragment()
                    setCurrentFragment(draftFragment)
                    binding.drawerLayout.close()
                }
                R.id.miLogOut ->{
                    val exitAlertDialog = AlertDialog.Builder(this)
                        .setIcon(R.drawable.ic_logout)
                        .setTitle("Exit")
                        .setMessage("Are you sure?")
                        .setCancelable(false)
                        .setPositiveButton("Yes"){
                            dialoginterface:DialogInterface,
                                listner:Int ->
                            val intent = Intent(this,LoginScreen::class.java)
                            startActivity(intent)
                            finish()
                        }
                        .setNegativeButton("No"){
                            dialogInterface:DialogInterface,
                                listner:Int ->
                            dialogInterface.cancel()
                        }
                    val alertDialog = exitAlertDialog.create()
                    alertDialog.show()
                }

            }
            true
        }


    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (toggle.onOptionsItemSelected(item)) {
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    private fun setCurrentFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.clContent, fragment)
            commit()
        }
    }
}