import com.google.common.reflect.TypeToken
import com.google.gson.Gson
import io.restassured.RestAssured
import spock.lang.Specification

class PetstoreApiSpec extends Specification {

    def "Se crea un usuario correctamente y se devuelven sus datos"() {
        given: "Los datos de un usuario"
        def userPayload = [
                id       : 1,
                username : "UserIndiTest",
                firstName: "Alex",
                lastName : "Test",
                email    : "alex@etest.com",
                password : "123456",
                phone    : "1234567890",
                userStatus: 1
        ]

        when: "Se hace la petición POST de creación del usuario"
        def createResponse = RestAssured.given()
                .contentType("application/json")
                .body(userPayload)
                .post("https://petstore.swagger.io/v2/user")

        then: "La petición se realiza correctamente"
        createResponse.statusCode == 200
        println("Creación de usuario correcta")

        when: "Esperamos hasta que el usuario esté disponible"
        def maxRetries = 5
        def retryCount = 0
        def getUserResponse

        while (retryCount < maxRetries) {
            getUserResponse = RestAssured.get("https://petstore.swagger.io/v2/user/UserIndiTest?nocache=" + System.currentTimeMillis())

            if (getUserResponse.statusCode == 200) {
                println "Usuario encontrado después de ${retryCount + 1} intentos"
                break
            }

            println "Usuario no encontrado, reintentando en 2 segundos... (Intento ${retryCount + 1} de ${maxRetries})"
            Thread.sleep(2000) // Espera 2 segundos antes de reintentar
            retryCount++
        }

        then: "La respuesta incluye el username correcto"
        assert getUserResponse.statusCode == 200 : "El usuario aún no está disponible después de ${maxRetries} intentos"
        getUserResponse.jsonPath().getString("username") == "UserIndiTest"
        println("Datos del usuario recuperados con éxito")
    }



    def "Listar nombres de mascotas vendidas y nombres repetidos"() {
        when: "Se obtiene la lista de mascotas vendidas desde la API"
        def response = RestAssured.get("https://petstore.swagger.io/v2/pet/findByStatus?status=sold")

        then: "La petición es exitosa"
        response.statusCode == 200

        when: "Se extrae la lista de mascotas vendidas"
        def pets = new Gson().fromJson(response.asString(), new TypeToken<List<Map<String, Object>>>() {}.getType())
        def soldPetsList = pets.findAll { it.containsKey("id") && it.containsKey("name") }
                .collect { [id: it.get("id"), name: it.get("name")] }

        then: "Se obtiene una lista válida de mascotas"
        soldPetsList.size() > 0
        println "Lista de mascotas vendidas:"
        soldPetsList.each { println "{id: ${it.id}, name: ${it.name}}" }

        when: "Se cuentan los nombres de las mascotas"
        PetNameCounter counter = new PetNameCounter(soldPetsList)
        Map<String, Integer> nameCounts = counter.countPetNames()

        then: "Se obtiene un conteo correcto de nombres de mascotas"
        nameCounts != null
        nameCounts.size() >= 0

        and: "Se imprime el conteo de nombres repetidos"
        println "Conteo de nombres repetidos de mascotas vendidas:"
        nameCounts.each { if (it.value > 1){ println "${it.key}: ${it.value}" }}
    }


}