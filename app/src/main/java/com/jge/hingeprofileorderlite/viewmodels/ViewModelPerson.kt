package com.jge.hingeprofileorderlite.viewmodels

import androidx.lifecycle.MutableLiveData
import com.jge.hingeprofileorderlite.objects.Person
import com.jge.hingeprofileorderlite.repo.PeopleRepository
import kotlinx.coroutines.*
import javax.inject.Inject

class ViewModelPerson @Inject constructor(
    private val repository: PeopleRepository) {
    private var job:Job? = null
    val profileList = MutableLiveData<List<String>>()
    val peopleList = MutableLiveData<List<Person>>()
    val loading = MutableLiveData<Boolean>()

    fun loadProfileConfig(){
        job = CoroutineScope(Dispatchers.IO).launch {
            val response = repository.getConfigProfile()
            val responseB = repository.getPeople()
            withContext(Dispatchers.Main){
                if(response.isSuccessful){
                    profileList.postValue(response.body()!!.listOfConfigs)
                }
                if(responseB.isSuccessful){
                    peopleList.postValue(responseB.body()!!.listOfPeople)
                }
                loading.value = false
            }
        }
    }
}