package com.its_omar.testcollaborators.Model

//class Colaborador ( id:Int, nombre: String, email:String, latitud:Double, longitud:Double){
//
//    var id:Int = 0
//    var nombre:String = ""
//    var email:String = ""
//    var latitud:Double = 0.0
//    var longitud:Double = 0.0
//
//    init {
//        this.id = id
//        this.nombre = nombre
//        this.email = email
//        this.latitud = latitud
//        this.longitud = longitud
//    }
//
//}

data class Colaborador (
    val id : Int,
    val nombre : String,
    val email : String,
    val latitud : Double,
    val longitud : Double,
        )