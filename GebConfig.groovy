import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.chrome.ChromeOptions

ChromeOptions options = new ChromeOptions()
options.addArguments("--remote-allow-origins=*")  // Evita bloqueos de WebSocket
options.addArguments("--disable-gpu")  // Para evitar errores en Windows
options.addArguments("--no-sandbox")  // Evita restricciones en entornos seguros
options.addArguments("--disable-dev-shm-usage")  // Soluciona problemas de memoria compartida
options.addArguments("--disable-software-rasterizer") // Evita fallos gráficos


driver = { new ChromeDriver(options) }