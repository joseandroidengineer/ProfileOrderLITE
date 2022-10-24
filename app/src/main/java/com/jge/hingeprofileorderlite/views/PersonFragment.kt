package com.jge.hingeprofileorderlite.views

import android.app.ActionBar.LayoutParams
import android.content.Context
import android.graphics.Typeface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.jge.hingeprofileorderlite.R
import com.jge.hingeprofileorderlite.Util
import com.jge.hingeprofileorderlite.objects.Person

class PersonFragment(private val person: Person): Fragment()  {
    private lateinit var linearLayout: LinearLayout
    private val hashMap = HashMap<Int, String>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.person_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews(view)
        configureLayout(view)
    }

    private fun createText(text:String, label:String?, context: Context){
        if(label!= null){
            createLabel(label, context)
        }
        val textView = createTextView(text, context)
        textView.textSize = 20f
        addViewToLayout(textView)
    }

    private fun createTextView(text: String, context: Context):TextView{
        val textView = TextView(context)
        val params = LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT)
        params.topMargin = 16
        params.bottomMargin = 16
        params.leftMargin = 16
        textView.layoutParams = params
        textView.text = text
        return textView
    }

    private fun createLabel(label:String, context: Context){
        val textView = createTextView(label,context)
        textView.typeface = Typeface.DEFAULT_BOLD
        textView.textSize = 24f
        addViewToLayout(textView)
    }

    private fun addViewToLayout(view:View){
        linearLayout.addView(view)
    }

    private fun createImageView(url:String, context: Context){
        val imageView = ImageView(context)
        imageView.layoutParams = ViewGroup.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT)
        imageView.adjustViewBounds = true
        Glide.with(this)
            .load(url)
            .into(imageView)
        linearLayout.addView(imageView)
    }

    private fun configureLayout(view: View) {
        for(x in 0..hashMap.size){
            when(hashMap[x]){
                "name" -> createText(person.name, null,view.context)
                "photo"-> createImageView(person.photoUrl, view.context)
                "gender"->{
                    if(person.gender == "m"){
                        createText("Male", "Gender",view.context)
                    }else{
                        createText("Female", "Gender",view.context)
                    }
                }
                "about"-> if(person.about!=null) createText(person.about, "About",view.context)

                "school"-> if(person.school!=null) createText(person.school, "School", view.context)

                "hobbies"->{
                    if(person.hobbies!=null){
                        val hobbies = Util.convertListToString(person.hobbies)
                        createText(hobbies, "Hobbies", view.context)
                    }
                }
            }
        }
    }

    private fun initViews(view: View) {
        linearLayout = view.findViewById(R.id.linear_layout)
    }

    fun setConfig(config: List<String>) {
        val max = config.size -1
        if(config.isNotEmpty()){
            for(x in 0..max){
                hashMap[x] = config[x]
            }
        }
    }
}