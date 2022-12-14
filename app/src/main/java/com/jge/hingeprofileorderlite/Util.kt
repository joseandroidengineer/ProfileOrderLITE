package com.jge.hingeprofileorderlite

import android.content.Context
import android.content.res.Resources
import android.util.TypedValue




object Util {
    fun checkIfObjectIsNonNull(`object`: Any?): Boolean {
        if (`object` is String) {
            checkIfStringIsEmpty(`object`)
        } else return `object` != null
        return false
    }

    private fun checkIfStringIsEmpty(`object`: String): Boolean {
        return `object` != ""
    }

    fun convertListToString(list: List<String>):String{
        if(checkIfObjectIsNonNull(list) && list.isNotEmpty()){
            val max = list.size - 1
            val stringBuilder = StringBuilder()
            for(i in 0..max){
                if(i != max){
                    stringBuilder.append("${list[i]}, ")
                }else{
                    stringBuilder.append(list[i])
                }
            }
            return stringBuilder.toString()
        }
        return ""
    }

    fun convertToPx(dp: Float, context: Context):Int{
        val r: Resources = context.resources
        return TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            dp,
            r.displayMetrics
        ).toInt()
    }
}
