package com.pruebas.seleniumGridTestNG.dataDrivenParallelXML;


import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.Assert.*;

public class ManejadorPrograma {

	@FindBy (css="div#operation-combo-container > select option:nth-child(1)")
	private WebElement btnComprar;
	@FindBy (xpath="//div[@id='typology-combo-container']/select/option[@value='9']")
	private WebElement btnVacacional;
	@FindBy (xpath="//div[@id='typology-combo-container']/select/option[@value='7']")
	private WebElement btnHabitacion;
	private RemoteWebDriver driverUtilizo;
	
	
	private String url = "http://www.idealista.com";
	
	public ManejadorPrograma(RemoteWebDriver driver){
		PageFactory.initElements(driver, this);
		this.driverUtilizo = driver;
		driverUtilizo.get(url);
	}
	

	
	public void logicaPrograma() throws InterruptedException{
	
	 
		WebDriverWait wait = new WebDriverWait(driverUtilizo, 60);// 1 minute 
		//pon tu anuncio gratis
		wait.until(ExpectedConditions.visibilityOf(btnComprar));
		btnComprar.click();
		
		/**
		 * Ahora vamos a chequear si al haber pulsado comprar  vacacional y habitacion estan deshabilitados como opciones
		 */
		assertEquals("Es posible seleccionar vacacional cuando hemos seleccionado comprar como tipo de servicio","trude", btnVacacional.getAttribute("disabled").toString());
		assertEquals("Es posible seleccionar habitacion cuando hemos seleccionado comprar como tipo de servicio","true" ,btnHabitacion.getAttribute("disabled").toString());
		//softAssert.assertEquals("trudde" ,btnHabitacion.getAttribute("disabled").toString());
		//softAssert.assertAll();
	}
	
	


}