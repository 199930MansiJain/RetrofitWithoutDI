package com.example.retrofitwithoutdi.model

data class ReposnseList(
    val items: ArrayList<RecyclerData>? = null
)

data class RecyclerData(val name: String, val description: String,val owner: Owner)


data class Owner(val avatar_url: String)