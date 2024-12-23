package com.example.enhanceskills

class UserModel {
    var username: String? =null
    var dob:String?=null
    var city : String?=null
    var image : String?=null
    constructor(username:String,dob:String,city:String,image:String){
        this.username=username.toString()
        this.dob=dob.toString()
        this.city=city.toString()
        this.image=image.toString()
    }
}
