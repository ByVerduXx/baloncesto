import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import lombok.extern.slf4j.Slf4j;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

@Slf4j
class PruebasPhantomjsIT {
    private static WebDriver driver = null;

    @BeforeEach
    void setUp() {
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setJavascriptEnabled(true);
        caps.setCapability(PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY, "/usr/bin/phantomjs");
        caps.setCapability(PhantomJSDriverService.PHANTOMJS_CLI_ARGS,
                new String[] { "--web-security=no", "--ignore-ssl-errors=yes" });
        driver = new PhantomJSDriver(caps);
    }

    @AfterEach
    void tearDown() {
        driver.close();
        driver.quit();
    }

    @Test
    void tituloIndexTest() {
        driver.navigate().to("http://localhost:8080/Baloncesto/");
        assertEquals("Votacion mejor jugador liga ACB", driver.getTitle(),
                "El titulo no es correcto");
        log.info(driver.getTitle());
    }

    @Test
    void resetVotosTest() {
        driver.navigate().to("http://localhost:8080/Baloncesto/");
        driver.findElement(By.name("B3")).click();
        driver.navigate().to("http://localhost:8080/Baloncesto/");
        driver.findElement(By.name("B4")).click();
        // Esperamos a que cargue la página
        new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(By.tagName("table")));

        WebElement tablaVotos = driver.findElement(By.tagName("table"));
        List<WebElement> filasVotos = tablaVotos.findElements(By.tagName("tr"));

        for (int i = 1; i < filasVotos.size(); i++) {
            List<WebElement> celdas = filasVotos.get(i).findElements(By.tagName("td"));
            String votosTexto = celdas.get(1).getText();
            int votos = Integer.parseInt(votosTexto);

            assertEquals(0, votos);
        }
    }

    @Test
    void voteOtherPlayerTest() {
        driver.navigate().to("http://localhost:8080/Baloncesto/");
        driver.findElements(By.name("R1")).get(3).click(); // Otros
        driver.findElement(By.name("txtOtros")).sendKeys("Verdu");
        driver.findElement(By.name("B1")).click(); // Votar
        driver.navigate().to("http://localhost:8080/Baloncesto/");
        driver.findElement(By.name("B4")).click(); // Ver votos
        // Esperamos a que cargue la página
        new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(By.tagName("table")));

        WebElement tablaVotos = driver.findElement(By.tagName("table"));
        List<WebElement> filasVotos = tablaVotos.findElements(By.tagName("tr"));

        boolean encontrado = false;

        for (int i = 1; i < filasVotos.size(); i++) {
            List<WebElement> celdas = filasVotos.get(i).findElements(By.tagName("td"));
            String nombre = celdas.get(0).getText();
            if (nombre.equals("Verdu")) {
                String votosTexto = celdas.get(1).getText();
                int votos = Integer.parseInt(votosTexto);
                assertEquals(1, votos);
                encontrado = true;
            }
        }

        assertEquals(true, encontrado);
    }
}
