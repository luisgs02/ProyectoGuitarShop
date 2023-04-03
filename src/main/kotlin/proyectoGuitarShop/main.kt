package proyectoGuitarShop

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

fun main() {
    val loginUser = LoginUser()
    var estado = false
    var logged = false // variable para almacenar el estado de inicio de sesión del usuario

    do {//************************************************************************************
        println("---------------Bienvenido a Guitar Shop by Luis Garcia Santin--------------")
        println("              ╔════╗╔╗╔╗╔══╗╔══╗╔══╗")
        println("              ║╔╗╔╗║║║║║║══╣╚╗╔╝║╔═╝")
        println("              ║║║║║║║╚╝║╠══║╔╝╚╗║╚═╗")
        println("              ╚╝╚╝╚╝╚══╝╚══╝╚══╝╚══╝")
        println("                         ,-.        _.---._")
        println("                        |  `\\.__.-''       `.")
        println("                         \\  _        _  ,.   \\")
        println("   ,+++=._________________)_||______|_|_||    |")
        println("  (_.ooo.===================||======|=|=||    |")
        println("     ~~'                 |  ~'      `~' o o  /")
        println("                          \\   /~`\\     o o  /")
        println("                           `~'    `-.____.-'")
        println("")

        var status = loginUser.login(estado)

        GlobalScope.launch {
            delay(1000)
        }
        Thread.sleep(1000)


        if (status) {
            println("---------Cargando menú principal....................")
            try {
                //Aquí se abre las lista de opciones del menú
                println("Selecciona una opción : ")
                println(" 1 --> Registro de new user")
                println(" 2 --> Ver inventario")
                println(" 3 --> Salir")

                var opcion = Integer.valueOf(readLine())


                when (opcion) {//--------------------------------------------
                    1 -> loginUser.registerUser()
                    2 -> {
                        if(loginUser.usuarioActual!=null){
                            val products = Productos("Guitarras")
                            println("Ingresa la opción deseada: ")

                            println(" >>>>>> Guitarras acústicas >>>>>>")
                            println(" >>>>>> Guitarras eléctricas >>>>>>")
                            println(" >>>>>> Plumillas >>>>>>")
                            println(" >>>>>> Amplificadores >>>>>>")
                            println(" >>>>>> Pedaleras >>>>>>")

                            val name = readLine()!!.toString()
                            products.getInventary(name)
                        } else {
                            println("Debe iniciar sesión primero")
                        }
                    }

                    3 -> break
                    else -> println("Opción no válida")
                }//-------------------------------------------------------
            } catch (e: NumberFormatException) {
                println("Por favor, escribe una opción válida")
            }
        } else {
            println("No se ingresó a la plataforma")
        }
        println("¿Regresar al menú de inicio? (SI/NO)")
        var cont = readLine()!!.toString()
    }//**************************************************************************************
    while (cont.toLowerCase()=="si")
    println("Gracias, te esperamos de vuelta!")
}
