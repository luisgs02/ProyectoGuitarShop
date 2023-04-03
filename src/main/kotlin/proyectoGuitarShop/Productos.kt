package proyectoGuitarShop

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

open class Productos(override val name: String) : Carrito() {
    val list = arrayListOf<Cart>()
    var endTotal: Double = 0.0

    val acoustic = mapOf(
        "Taylor-SE" to 35000,
        "Ibañez GA5TCE-AM" to 6500,
        "Fender CD-60SCE" to 5800,
        "Daisy Rock 14-7" to 1800,
        "Clasica Yamaha" to 4700,
        "Baby Taylor" to 10000,
        "Fender Newporter" to 6500,
        "Fender CC-60SCE2" to 5700
    )
    val electric = mapOf(
        "Squier Telecaster" to 6000,
        "Ibañez RG" to 7000,
        "Gibson Les Paul" to 26000,
        "Gibson SG" to 35000,
        "Fender stratocaster" to 15000,
        "Gresch 45-T" to 27000,
        "PRS Santana" to 23000,
        "Jackson PS" to 28000
    )
    val pedaleras = mapOf(
        "NUX MG-300" to 25000,
        "Harley Benton DNAfx Git" to 35000,
        "Mooer GE 150" to 30000,
        "Line 6 HX Stomp" to 20000,
        "Boss GT-1000 Core" to 26000,
        "Headrush Gigboard" to 25500,
        "Hotone Ampero II Stomp" to 41500,
        "Boss MS-3" to 32000
    )
    val amplifiers = mapOf(
        "Spitfire 21" to 14000,
        "Marshall 1530" to 10000,
        "Vox AC30" to 15000,
        "Spider Line 6" to 12000,
        "Boss Katana" to 11500,
        "Fender Hot Rod Deluxe" to 23000,
        "Fender Mustang" to 25000,
        "Fender 57 Custom Deluxe" to 30000
    )
    val plumillas = mapOf(
        "Fender 12 picks" to 200,
        "Gibson picks" to 300,
        "Harley Benton Nylon" to 300,
        "Shiwaki" to 200,
        "Dunlop" to 260,
    )

    fun menuCart(nameProduct: String) {
        try {
            println()
            println("-----------------Cargando menú----------------------")
            GlobalScope.launch {
                delay(1000)
            }
            Thread.sleep(1000)
            println("*****************************************************")
            println("Escribe un número de opción del menu : ")
            println()
            println("(1)--> Agregar producto al carrito")
            println("(2)--> Ver carrito de compras")
            println("(3)--> Ver catálogo")
            println("(4)--> Realizar Pago")
            println("(5)--> Regresar al menú principal")
            var number = Integer.valueOf(readLine())
            when (number) {
                1 -> {
                    println("Ingresa nombre del Producto: ")
                    val name = readLine()!!.toString()
                    println("Ingresa la cantidad a comprar: ")
                    var quantity = Integer.valueOf(readLine())
                    addCart(nameProduct, name, quantity)
                }

                2 -> {
                    totalPrice()
                }

                3 -> {
                    val products = Productos("Guitarras")
                    println("Ingresa nombre del catalogo del Producto: ")
                    println(" ----------- Guitarras acústicas -------")
                    println(" ----------- Guitarras eléctricas ------")
                    println(" ----------- Pedaleras -----------------")
                    println(" ----------- Amplificadores ------------")
                    println(" ----------- Plumillas -----------------")
                    val name = readLine()!!.toString()
                    this.getInventary(name) // llamada a getInventary() desde la instancia actual de Productos
                    menuCart(nameProduct)
                }

                4 -> {
                    val statusPago = Pago(name)
                    statusPago.statusPayment()
                }

                5 -> main()
                else -> println("Opción no valida")
            }
        } catch (e: NumberFormatException) {
            println("Por favor, escribe una opción válida")
        }

    }


    override fun addCart(
        name: String,
        description: String,
        quantity: Int
    ) {   ///Función para agregar productos al mercado
        when (name) {
            "Guitarras acústicas" -> {
                val price = acoustic[description]!!.toLong()
                list.add(Cart(description, price, quantity, (price * quantity)))
                menuCart(name)
            }

            "Guitarras eléctricas" -> {
                val price = electric[description]!!.toLong()
                list.add(Cart(description, price, quantity, (price * quantity)))
                menuCart(name)
            }

            "Pedaleras" -> {
                val price = pedaleras[description]!!.toLong()
                list.add(Cart(description, price, quantity, (price * quantity)))
                menuCart(name)
            }

            "Amplificadores" -> {
                val price = amplifiers[description]!!.toLong()
                list.add(Cart(description, price, quantity, (price * quantity)))
                menuCart(name)
            }

            "Plumillas" -> {
                val price = plumillas[description]!!.toLong()
                list.add(Cart(description, price, quantity, (price * quantity)))
                menuCart(name)
            }

            else -> println("Producto no encontrado en el inventario")
        }
    }

    override fun totalPrice() {       ////////Función total de Pago
        val totalPrice = quotePrice()
        if (totalPrice == 0.0) {
            println("El carrito de compras esta vacio :(")
            menuCart(name)
        } else {
            for (producto in list) {
                val detailBuy = """ Nombre                 Cantidad                          Precio
 ${producto.name}             ${producto.quantity}                          ${producto.price}  
                                                          Subtotal : ${producto.priceTotal}"""
                println(detailBuy)
            }
            if (totalPrice >= 10000) {        //Valida si el Pago total es mayor ó igual 900
                println("ENVIO GRATIS para compras mayores a $10000")  //Si es mayor ó igual a 900 el costo de envio es gratis
                println("Total de compra:  $totalPrice")
                endTotal = totalPrice
                menuCart(name)
            } else {
                println("Total de compra más costo de envio de $150 es : ${totalPrice + 150}") //Si es menor a 900 se cobra el costo de envio a $150.MXN
                endTotal = totalPrice
                menuCart(name)
            }
        }
    }

    override fun quotePrice(): Double {   //Función que muestra los productos en el carrito
        if (list.isEmpty()) {      //// Realiza una validacion si el carrito esta vacio
            return 0.0
        } else {
            for (producto in list) {
                return producto.priceTotal.toDouble()  ///Regresa el Precio Total del Carrito si este contiene productos
            }
        }
        return 0.0 ///Regresa 0.0 si el carrito esta vacio
    }

    fun getInventary(name: String) {    //Función para mostrar los productos de la tienda
        var total = 0.0 // nueva variable para almacenar el total de los productos seleccionados
        when (name) {
            "Guitarras acústicas" -> {
                println("---------------Guitarras acústicas price details-------------------")
                println("Nombre______________________________Precio")
                println()
                for (acousticas in acoustic) {
                    println("${acousticas.key} ------------> $ ${acousticas.value}")
                }
                menuCart(name)
            }

            "Guitarras eléctricas" -> {
                println("---------------Guitarras eléctricas price details-------------------")
                println("Nombre______________________________Precio")
                println()
                for (electrica in electric) {
                    println("${electrica.key} ------------> $ ${electrica.value}")
                }
                menuCart(name)
            }

            "Pedaleras" -> {
                println("---------------Pedaleras price details-------------------")
                println("Nombre______________________________Precio")
                println()
                for (pedalera in pedaleras) {
                    println("${pedalera.key} ------------> $ ${pedalera.value}")
                }
                menuCart(name)
            }

            "Amplificadores" -> {
                println("---------------Amplificadores price details-------------------")
                println("Nombre______________________________Precio")
                println()
                for (amplis in amplifiers) {
                    println("${amplis.key} ------------> $ ${amplis.value}")
                }
                menuCart(name)
            }

            "Plumillas" -> {
                println("---------------Plumillas price details-------------------")
                println("Nombre______________________________Precio")
                println()
                for (picks in plumillas) {
                    println("${picks.key} ------------> $ ${picks.value}")
                }
                menuCart(name)
            }

            else -> println("Producto no encontrado en el inventario")
        }
    }
}