package com.data.newsapps.data.model

import com.google.gson.annotations.SerializedName

data class ResponseEverything (

	@SerializedName("status") val status : String,
	@SerializedName("totalResults") val totalResults : Int,
	@SerializedName("articles") val articles : List<Articles>
)