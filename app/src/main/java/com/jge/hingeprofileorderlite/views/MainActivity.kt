package com.jge.hingeprofileorderlite.views

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.jge.hingeprofileorderlite.R
import com.jge.hingeprofileorderlite.Util
import com.jge.hingeprofileorderlite.dependencyinjection.AppComponent
import com.jge.hingeprofileorderlite.dependencyinjection.DaggerAppComponent
import com.jge.hingeprofileorderlite.objects.Person
import com.jge.hingeprofileorderlite.viewmodels.ViewModelPerson
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    private var config: List<String> = ArrayList()
    private lateinit var listOfPeople: List<Person>
    private val listOfFragments = ArrayList<Fragment>()
    private lateinit var appComponent: AppComponent
    private var currentFragment:Int = 0

    private lateinit var toolbar: Toolbar
    private lateinit var fab:FloatingActionButton
    private lateinit var  progressBar: ProgressBar

    @Inject
    lateinit var viewModelPerson: ViewModelPerson

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViews()

        appComponent = DaggerAppComponent.builder().build()
        appComponent.inject(this)

        fab.setOnClickListener {
            supportFragmentManager
                .beginTransaction()
                .setCustomAnimations(R.anim.slide_in, R.anim.fade_out)
                .replace(R.id.fragment_container, listOfFragments[currentFragment])
                .addToBackStack(listOfFragments[currentFragment].tag)
                .commit()

            currentFragment++
            if(currentFragment >= listOfFragments.size){
                fab.visibility = View.GONE
            }
        }
        startNetworkConnection()
    }

    override fun onBackPressed() {
        if(currentFragment > 0){
            fab.visibility = View.VISIBLE
            currentFragment--
        }
        super.onBackPressed()
    }

    private fun startNetworkConnection() {
        viewModelPerson.peopleList.observe(this){
            if (Util.checkIfObjectIsNonNull(it)) {
                listOfPeople = it
                createInstancesOfFragments()
                handleUIWhenApiSucceeds()
            } else {
                handleUIWhenApiFails()
            }
        }

        viewModelPerson.profileList.observe(this){
            if (it != null) {
                config = it
            }
        }

        viewModelPerson.loading.observe(this){
            if(!it){
                progressBar.visibility = View.GONE
                fab.visibility = View.VISIBLE
            }
        }
        viewModelPerson.loadProfileConfig()
    }

    private fun createInstancesOfFragments() {
        for(person in listOfPeople){
            val personFragment = PersonFragment(person)
            personFragment.setConfig(config)
            listOfFragments.add(personFragment)
        }
    }

    private fun handleUIWhenApiSucceeds() {
        if(listOfFragments.size > 0){
            supportFragmentManager.beginTransaction()
                .add(R.id.fragment_container,listOfFragments[currentFragment])
                .commit()
            currentFragment++
        }
    }

    private fun handleUIWhenApiFails() {
        Toast.makeText(this, "Failed to load", Toast.LENGTH_SHORT).show()
    }

    private fun initViews() {
        toolbar = findViewById(R.id.toolbar)
        fab =  findViewById(R.id.fab)
        progressBar = findViewById(R.id.pb)
    }
}