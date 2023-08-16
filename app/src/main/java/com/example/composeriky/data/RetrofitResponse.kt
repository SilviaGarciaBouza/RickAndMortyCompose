package com.example.composeriky.data




import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
//En @SerializedName(value =  "success") pones el nombre q aparece el de val puede ser otro
data class RetrofitResponse (@SerializedName(value =  "results") val results: List<RikyItemResponse>)

//Flow la sig linea
@Entity
data class RikyItemResponse (
  @PrimaryKey  @SerializedName(value= "id") val rikiId: Int,
    @SerializedName(value= "name") val rikiName: String,
    @SerializedName(value= "image") val rikiImage: String)