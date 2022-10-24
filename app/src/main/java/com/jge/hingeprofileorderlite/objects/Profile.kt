package com.jge.hingeprofileorderlite.objects

import com.google.gson.annotations.SerializedName

class Profile(@SerializedName("profile")val listOfConfigs: ArrayList<String>)
