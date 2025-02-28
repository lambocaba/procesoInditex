import Pages.GooglePage
import Pages.WikipediaPage
import io.github.bonigarcia.wdm.WebDriverManager
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.chrome.ChromeOptions
import geb.spock.GebSpec
import org.openqa.selenium.OutputType
import org.openqa.selenium.TakesScreenshot
import java.nio.file.Files
import java.nio.file.Paths


class TestFrontSpec extends GebSpec {

    def setup() {
        println "Configurando WebDriverManager..."

        // Forzar la instalación de ChromeDriver correcto
        WebDriverManager.chromedriver().setup()

        // Configurar opciones para evitar bloqueos
        ChromeOptions options = new ChromeOptions()
        options.addArguments("--remote-allow-origins=*")
        options.addArguments("--disable-gpu")
        options.addArguments("--no-sandbox")
        options.addArguments("--disable-dev-shm-usage")
        options.addArguments("--disable-software-rasterizer")
        options.addArguments("--remote-debugging-port=9222")
        options.addArguments("user-agent=Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/114.0.5735.199 Safari/537.36")


        // Evitar detección de WebDriver
        Map<String, Object> prefs = new HashMap<>()
        prefs.put("credentials_enable_service", false)
        prefs.put("profile.password_manager_enabled", false)

        options.setExperimentalOption("prefs", prefs)
        options.setExperimentalOption("excludeSwitches", ["enable-automation"])
        options.setExperimentalOption("useAutomationExtension", false)

        // Forzar Selenium a usar un ChromeDriver específico
        System.setProperty("webdriver.chrome.driver", "C:\\chromedriver\\chromedriver.exe")

        // Inicializar el driver con opciones
        driver = new ChromeDriver(options)
        println "ChromeDriver iniciado correctamente"
    }

    def cleanup() {
        if (driver) {
            println "Cerrando el navegador"
            driver.quit()
        }
    }

    def "Ejercicio 2"() {
        given: "vamos a la página principal de Google"
        to GooglePage

        when: "se hace click en el botón 'Aceptar'"
        acceptButton.click()
        and: "se rellena la barra de búsqueda"
        waitFor { searchBarInput.displayed }
        searchBarInput.value("automatización")
        and: "se hace click en el botón 'Búsqueda con Google'"
        searchButton.click()
        and: "se hace click en el link de wikipedia resultante"
        waitFor { wikiLink.displayed }
        wikiLink.click()

        then:"llegamos a la página de wikipedia"
        waitFor {driver.getCurrentUrl().contains("wikipedia")}
        at WikipediaPage

        when: "Buscamos la frase específica y extraemos el año"
        def textoCompleto = textoWikipedia.text()

        // Buscar la frase exacta
        def match = (textoCompleto =~ /Un molino harinero automático fue desarrollado por Oliver Evans en (\d{4}), convirtiéndose en el primer proceso industrial completamente automatizado\./)
        def anno = "No encontrado"

        then: "Imprimimos el año correcto"
        if (match) {
            anno = match[0][1]  // Extrae solo el año de la frase encontrada
        }

        println "El primer proceso automático ocurrió en: $anno"
        assert anno != "No encontrado"

        when: "Tomamos un screenshot de la página"
        def screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)
        Files.write(Paths.get("screenshot.png"), screenshot)
        println "Captura de pantalla guardada como 'screenshot.png'"

        then: "La captura de pantalla se ha guardado correctamente"
        assert Files.exists(Paths.get("screenshot.png"))
    }

}