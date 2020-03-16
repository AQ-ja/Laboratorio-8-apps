package com.example.mygithub2

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import com.example.mygithub2.ApiService
import com.example.mygithub2.Git
import retrofit2.Response

class ViewModelHome : ViewModel() {
        // TODO: Implement the ViewModel

    private val _response = MutableLiveData<String>()

    var valors:String=""
    var existe = MutableLiveData<Boolean?>()

    private val _property = MutableLiveData<Git>()

    val property: LiveData<Git>
        get() = _property



    val responsa: LiveData<String>
        get() = _response


    init {

    }

    /**
     * Sets the value of the response LiveData to the Mars API status or the successful number of
     * Mars properties retrieved.
     */
    public fun getGithubProperties() {
        ApiServices.retrofitService.getProperties(valors).enqueue( object: retrofit2.Callback<Git> {
            override fun onFailure(call: Call<Git>, t: Throwable) {
                _response.value = "Error " + t.message
            }

            override fun onResponse(call: Call<Git>, response: Response<Git>){

                //ya muestra el nombre del usuario en caso haya
                _response.value = "Usuario: "+response.body()?.user
                if (response.body()?.user!=null){
                    _response.value = "Usuario: "+response.body()?.user
                    _property.value=response.body()
                    existe.value=false


                }else{//sino hay lo dice
                    _response.value = "No existe"
                    existe.value = true
                }
            }
        })
    }
}
