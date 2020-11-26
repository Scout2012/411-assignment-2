package org.csuf.cpsc411.simplehttpclient

import android.util.Log
import android.widget.TextView
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.loopj.android.http.AsyncHttpClient
import com.loopj.android.http.AsyncHttpResponseHandler
import cz.msebera.android.httpclient.Header
import cz.msebera.android.httpclient.entity.StringEntity
import java.lang.reflect.Type

class ClaimService (val ctx : CustomActivity){

    var claimList : MutableList<Claim> = mutableListOf()
    var currentIndx : Int = 0

    companion object {
        private var pService : ClaimService? = null

        fun getInstance(act : CustomActivity) : ClaimService {
            if (pService == null) {
                pService = ClaimService(act)
            }

            return pService!!
        }
    }

    fun next() : Claim {
        currentIndx = currentIndx + 1
        return claimList[currentIndx]
    }

    fun isLastObject() : Boolean  {
        if (currentIndx == claimList.count()-1) return true
        return false
    }

    fun fetchAt(e : Int) : Claim {
        currentIndx = e
        return claimList[currentIndx]
    }

    inner class GetAllServiceRespHandler : AsyncHttpResponseHandler() {
        override fun onSuccess(
            statusCode: Int,
            headers: Array<out Header>?,
            responseBody: ByteArray?
        ) {
            // JSON string
            if (responseBody != null) {
                Log.d("Claim Service", "The response JSON string is ${String(responseBody)}")
                val gson = Gson()
                val claimListType: Type = object : TypeToken<List<Claim>>() {}.type
                claimList = gson.fromJson(String(responseBody), claimListType)
                //
                ctx.refreshScreen(claimList[currentIndx])
                //
                //act.runOnUiThread {
                //    cbLambdaFunction()
                //}
                Log.d("Claim Service", "The Claim List: ${claimList}")
            }
        }

        override fun onFailure(
            statusCode: Int,
            headers: Array<out Header>?,
            responseBody: ByteArray?,
            error: Throwable?
        ) {
            TODO("Not yet implemented")
        }
    }

    inner class addServiceRespHandler : AsyncHttpResponseHandler() {
        override fun onSuccess(
            statusCode: Int,
            headers: Array<out Header>?,
            responseBody: ByteArray?
        ) {
            if (responseBody != null) {
                val respStr = String(responseBody)
                val statusValue = ctx.findViewById<TextView>(R.id.status_value_text)
                statusValue.text = "Successfully added claim to database!"
                Log.d("Claim Service", "The add Service response : ${respStr}")
            }
        }

        override fun onFailure(
            statusCode: Int,
            headers: Array<out Header>?,
            responseBody: ByteArray?,
            error: Throwable?
        ) {
            val statusValue = ctx.findViewById<TextView>(R.id.status_value_text)
            statusValue.text = "Could not add claim to database! Server returned status code ${statusCode}"
            Log.d("Claim Service", error?.message.toString())
        }
    }

    fun addClaim(pObj : Claim) {
        Log.d("Claim Service", "Adding Claim")
        val client = AsyncHttpClient()
        val requestUrl = "http://0.0.0.0:8080/ClaimService/add"
        // 1. Convert the pObj into JSON string
        val pJsonString= Gson().toJson(pObj)
        // 2. Send the post request
        val entity = StringEntity(pJsonString)
        Log.d("Claim Service", pJsonString + "  " + entity)
        // cxt is an Android Application Context object
       var postResponse = client.post(ctx, requestUrl, entity,"application/json", addServiceRespHandler())
        Log.d("Claim Service", postResponse.toString())

    }

    fun getAll()  {
        //var pList : List<Claim> = mutableListOf()
        // Call Http
        //clientObj = clObj
        val client = AsyncHttpClient()
        val requestUrl = "http://0.0.0.0:8080/ClaimService/getAll"
        //
        Log.d("Claim Service", "About Sending the HTTP Request. ")
        //
        client.get(requestUrl, GetAllServiceRespHandler())
    }
}