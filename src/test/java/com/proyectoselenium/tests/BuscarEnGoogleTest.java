package com.proyectoselenium.tests;
 
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.junit.*;
import static org.junit.Assert.*;
 
public class BuscarEnGoogleTest {
    protected WebDriver driver;
    private StringBuffer verificationErrors = new StringBuffer();
    @Before
    public void setUp() {
        // Iniciamos el driver de Mozilla Firefox
    	
    	System.setProperty("webdriver.gecko.driver", "/home/eduriol/git/selenium-google-example/src/main/resources/geckodriver");    	
        driver = new FirefoxDriver();
         
        // Navegamos a la página principal de Google
        driver.get("http://www.google.com");
    }
    @Test
    public void testGoogleSearch() {
        try {
            // Buscamos el campo de texto por su atributo nombre
            WebElement element = driver.findElement(By.name("q"));
             
            // Introducimos un valor en el campo para realizar la búsqueda
            element.sendKeys("Como empezar a automatizar con Selenium");
             
            // Enviamos el formulario. WebDriver encontrará el formulario al que pertenece el campo automáticamente
            element.submit();
             
            // La búsqueda de Google funciona dinámicamente con JavaScript
            // Así que introducimos un tiempo de espera de 10 segundos
            (new WebDriverWait(driver, 10)).until(new ExpectedCondition<Boolean>() {
                public Boolean apply(WebDriver d) {
                    //System.out.println(driver.getTitle());
                    return d.getTitle().toLowerCase().startsWith("como empezar a automatizar con selenium");
                }
            });
            // Deber�amos ver: "Como empezar a automatizar con Selenium - Buscar con Google" en el t�tulo
            assertEquals("Como empezar a automatizar con Selenium - Buscar con Google", driver.getTitle());
        } catch (Error e) {
            // Capture and append Exceptions/Errors
            verificationErrors.append(e.toString());
            System.out.println(driver.getTitle());
        }
    }
    @Test
    public void testImFeelingLucky() {
    	try {
            WebElement searchTextbox = driver.findElement(By.name("q"));
            searchTextbox.sendKeys("Jenkins for dummies");
    		(new WebDriverWait(driver,5)).until(new ExpectedCondition<Boolean>() {
    			public Boolean apply(WebDriver d) {
    				return d.findElement(By.id("sbsb_f")).isDisplayed();
    			}
    		});            
    		WebElement masInformacionLink = driver.findElement(By.id("sbsb_f"));
    		masInformacionLink.click();
    		(new WebDriverWait(driver,10)).until(new ExpectedCondition<Boolean>() {
    			public Boolean apply(WebDriver d) {
    				return d.getTitle().toLowerCase().startsWith("realizar búsquedas con autocompletar");
    			}
    		});
    		assertEquals("Realizar búsquedas con autocompletar - Ayuda de Búsqueda web", driver.getTitle());
    	} catch (Error e) {
    		verificationErrors.append(e.toString());
    		System.out.println(driver.getTitle());
    	}
    }
    @After
    public void tearDown() throws Exception {
        // Cerramos el navegador Firefox
        driver.quit();
        String verificationErrorString = verificationErrors.toString();
        if (!"".equals(verificationErrorString)) {
            fail(verificationErrorString);
        }
    }
}
