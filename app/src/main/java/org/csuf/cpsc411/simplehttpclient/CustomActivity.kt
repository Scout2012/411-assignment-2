package org.csuf.cpsc411.simplehttpclient

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

open class CustomActivity : AppCompatActivity() {
    lateinit var claimList : MutableList<Claim>
    lateinit var claimService : ClaimService

    fun refreshScreen(claimObj : Claim) {
        //
        Log.d("claim Service", "Refreshing the Screen. ")
        val firstNameView : EditText = findViewById(R.id.claim_title)
        val lastNameView : EditText = findViewById(R.id.claim_date)
//        val ssnView : EditText = findViewById(R.id.ssn)
        //
        firstNameView.setText(claimObj.claimTitle)
        lastNameView.setText(claimObj.claimDate)

        // Enable/disable the button
        val addBtnView : Button = findViewById(R.id.add_btn)
        addBtnView.setEnabled(!claimService.isLastObject())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val fldRowGenerator = ObjDetailScreenGenerator(this)
        val colView = fldRowGenerator.generate()
        setContentView(colView)

        val addBtnView : Button = findViewById(R.id.add_btn)
        val claimTitleView : EditText = findViewById(R.id.claim_title)
        val claimDateView : EditText = findViewById(R.id.claim_date)
        val statusValueView : TextView = findViewById(R.id.status_value_text)

        addBtnView.setOnClickListener{
            // get the next claim object
            Log.d("Detailed Activity", "Add Button Clicked")
            statusValueView.text = "Searching Database..."
            val claimTitle : String = claimTitleView.text.toString()
            val claimDate : String = claimDateView.text.toString()
            claimService.addClaim(Claim(claimTitle, claimDate))
        }

        val pos = intent.getIntExtra("ElementId", 0)
        Log.d("Detailed Activity ", "Display ${pos} claim object")
        claimService = ClaimService.getInstance(this)
        if (claimService.claimList.count() != 0) {
            val claimObj = claimService.fetchAt(pos)
            refreshScreen(claimObj)
        }
    }
}