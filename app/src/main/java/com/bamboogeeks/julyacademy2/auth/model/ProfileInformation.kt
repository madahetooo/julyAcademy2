package com.bamboogeeks.julyacademy2.auth.model

data class ProfileInformation(
    val fullName:String ="",
    val emailAddress:String="",
    val phoneNumber:String="",
    val password:String = "",
) {
}