package com.example.composeriky.data




import com.google.gson.annotations.SerializedName
//En @SerializedName(value =  "success") pones el nombre q aparece el de val puede ser otro
data class RetrofitResponse (@SerializedName(value =  "results") val results: List<RikyItemResponse>)
data class RikyItemResponse (
    @SerializedName(value= "id") val rikiId: Int,
    @SerializedName(value= "name") val rikiName: String,
    @SerializedName(value= "image") val rikiImage: String)