package com.proyectoselenium.tests;
 
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.junit.*;
import static org.junit.Assert.*;
 
public class BuscarEnGoogleTest {
    protected WebDriver driver;
    
    @Before
    public void setUp() {
    	System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");    	
        driver = new ChromeDriver();
        driver.get("http://www.google.com");
    }
    
    @Test
    public void testGoogleSearch() {
        WebElement element = driver.findElement(By.name("q"));
        element.sendKeys("Como empezar a automatizar con Selenium");
        element.submit();
        (new WebDriverWait(driver, 10)).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                //System.out.println(driver.getTitle());
                return d.getTitle().toLowerCase().startsWith("como empezar a automatizar con selenium");
            }
        });
        assertEquals("Como empezar a automatizar con Selenium - Buscar con Google", driver.getTitle());
    }

    @After
    public void tearDown() throws Exception {
        driver.quit();
    }
}
