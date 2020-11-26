package org.csuf.cpsc411.simplehttpclient

import android.content.Context
import android.graphics.Color
import android.view.Gravity
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView

class LabelColumnGenerator(val ctx : Context) {

    fun generate() : LinearLayout {
        val layoutObj = LinearLayout(ctx)
        val lParams = ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT)
        layoutObj.layoutParams = lParams
        layoutObj.orientation = LinearLayout.VERTICAL
        layoutObj.setBackgroundColor(Color.BLACK)

        val lbParams = LinearLayout.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT)
        lbParams.weight = 1.0F
        lbParams.topMargin = 5

        var lbl = TextView(ctx)
        lbl.text = "Claim Title"
        lbl.gravity = Gravity.CENTER
        lbl.setBackgroundColor(Color.WHITE)
        layoutObj.addView(lbl, lbParams)

        lbl = TextView(ctx)
        lbl.text = "Date"
        lbl.gravity = Gravity.CENTER
        lbl.setBackgroundColor(Color.WHITE)
        layoutObj.addView(lbl, lbParams)

        return layoutObj
    }
}