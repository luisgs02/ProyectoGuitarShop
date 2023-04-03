package proyectoGuitarShop

class LoginUser {
    val users = arrayListOf(Usuario("luisg@bedu.com", "Bedu"))
    var usuarioActual: Usuario? = null

    fun registerUser() {
        println("Ingresa tu correo electrónico: ")
        val email = readLine()!!.toString()

        println("Ingresa password: ")
        val password = readLine()!!.toString()

        val existeEmail = users.find {
            it.email == email
        }

        if (existeEmail == null) {       //Si no existe el correo electrónico proporcionado por el usuario se crea el usuario y notificamos al usuario
            users.add(Usuario(email, password))
            println("Registro realizado con exito !")
        } else {
            println("Ya esta en uso este correo electrónico. Puede intentar con otro. ")
        }
    }


    fun login(estado: Boolean): Boolean {
        var sesion = estado
        println("Ingresa correo electrónico")
        val email = readLine()!!.toString()
        println("Ingresa contraseña")
        val password = readLine()!!.toString()
        println("Validando usuario...")


        val usuario = users.find {
            it.email == email && it.password == password
        }
        if (usuario != null) {
            println("Inicio de sesión exitoso")
            sesion = true
            usuarioActual=usuario

        } else {
            println("email o contraseña incorrecto")
            sesion = false
        }
        return sesion
    }
}
