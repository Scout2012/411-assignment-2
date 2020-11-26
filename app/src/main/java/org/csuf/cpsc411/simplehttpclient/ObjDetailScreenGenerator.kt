package org.csuf.cpsc411.simplehttpclient

import android.content.Context
import android.graphics.Color
import android.view.Gravity
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView

class ObjDetailScreenGenerator(val ctx : Context) {
    lateinit var layoutObj : LinearLayout
    fun generate() : LinearLayout {
        // 1. Create a linearLayout object
        layoutObj = LinearLayout(ctx)
        val lParams = ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT
        )
        layoutObj.layoutParams = lParams
        layoutObj.orientation = LinearLayout.VERTICAL
        layoutObj.setBackgroundColor(Color.WHITE)

        // 2. Add ObjDetailSection
        val fldRowGenerator = ObjDetailSectionGenerator(ctx)

        val colView = fldRowGenerator.generate()
        layoutObj.addView(colView)

        // 3. Add Next Button LinearLayout
        val nLayout = LinearLayout(ctx)

        val nParams = LinearLayout.LayoutParams(
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT)
        nParams.topMargin = 5

        // only applied to height now
        nParams.gravity = Gravity.RIGHT
        nLayout.layoutParams = nParams
        nLayout.orientation = LinearLayout.HORIZONTAL
        nLayout.setBackgroundColor(Color.GRAY)
        //
        val nButton = Button(ctx)
        nButton.text = "Add"
        nButton.setId(R.id.add_btn)
        nButton.setBackgroundColor(Color.LTGRAY)

        val nbParams = LinearLayout.LayoutParams(
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        nbParams.gravity = Gravity.BOTTOM
        nLayout.addView(nButton, nbParams)

        layoutObj.addView(nLayout, nParams)

        // 4. Add Status LinearLayout
        val statusLayout = LinearLayout(ctx)

        val statusParams = LinearLayout.LayoutParams(
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT)

        statusParams.gravity = Gravity.CENTER
        statusLayout.layoutParams = statusParams
        statusLayout.orientation = LinearLayout.HORIZONTAL
        statusLayout.setBackgroundColor(Color.WHITE)

        val statusText = TextView(ctx)
        statusText.text = "Status"
        statusText.setId(R.id.status_text)

        statusLayout.addView(statusText, statusParams)

        val statusValueParams = LinearLayout.LayoutParams(
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT)
        statusValueParams.leftMargin = 200

        val statusValueText = TextView(ctx)
        statusValueText.text = "Unknown"
        statusValueText.setId(R.id.status_value_text)

        statusLayout.addView(statusValueText, statusValueParams)

        layoutObj.addView(statusLayout, statusParams)
        return layoutObj
    }
}