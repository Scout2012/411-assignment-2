package org.csuf.cpsc411.simplehttpclient

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText

open class CustomActivity : AppCompatActivity() {
    lateinit var pList : MutableList<Person>
    lateinit var pService : PersonService

    fun refreshScreen(pObj : Person) {
        //
        Log.d("Person Service", "Refreshing the Screen. ")
        val firstNameView : EditText = findViewById(R.id.claim_title)
        val lastNameView : EditText = findViewById(R.id.claim_date)
//        val ssnView : EditText = findViewById(R.id.ssn)
        //
        firstNameView.setText(pObj.firstName)
        lastNameView.setText(pObj.lastName)

        // Enable/disable the button
        val addBtnView : Button = findViewById(R.id.add_btn)
        addBtnView.setEnabled(!pService.isLastObject())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val fldRowGenerator = ObjDetailScreenGenerator(this)
        val colView = fldRowGenerator.generate()
        setContentView(colView)

        val addBtnView : Button = findViewById(R.id.add_btn)
        val claimTitleView : EditText = findViewById(R.id.claim_title)
        val claimDateView : EditText = findViewById(R.id.claim_date)

        addBtnView.setOnClickListener{
            // get the next Person object
            Log.d("Detailed Activity", "Add Button Clicked")
            val claimTitle : String = claimTitleView.text.toString()
            val claimDate : String = claimDateView.text.toString()

//            val pObj = pService.next()
//            refreshScreen(pObj)
        }

        val pos = intent.getIntExtra("ElementId", 0)
        Log.d("Detailed Activity ", "Display ${pos} Person object")
        pService = PersonService.getInstance(this)
        if (pService.personList.count() != 0) {
            val pObj = pService.fetchAt(pos)
            refreshScreen(pObj)
        }
    }
}