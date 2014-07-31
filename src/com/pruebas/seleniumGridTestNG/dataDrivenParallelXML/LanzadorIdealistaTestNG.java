package com.pruebas.seleniumGridTestNG.dataDrivenParallelXML;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.Platform;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.Assertion;
import org.testng.asserts.SoftAssert;

public class LanzadorIdealistaTestNG {
	
	private RemoteWebDriver driver;
	@Parameters({ "browser","platformBrowser","versionBrowser","URL_NODE_HUB" })
	
	@Test
	public void generoDriver(String browser, String platformBrowser,String versionBrowser,String URL_NODE_HUB) throws InterruptedException, MalformedURLException{
		
		DesiredCapabilities cap = null;
		Platform plataform = null;
		
		
		/**
		 * - INICIO - Controlamos la plataforma en la que se va a lanzar el test
		 */
		
		if (platformBrowser.equals("MAC")){
			plataform = Platform.MAC;
		}
		
		if (platformBrowser.equals("WINDOWS"))
		{
			plataform  = Platform.WINDOWS;
		}
		
		if (platformBrowser.equals("LINUX")){
			plataform = Platform.LINUX;
		}
		
		if (platformBrowser.equals("")) // caso por defecto
		{
			plataform = Platform.ANY;
		}
		/**
		 * -FIN- Controlamos la plataforma en la que se va a lanzar el test
		 */
		
		
		/**
		 * - INICIO - logica para lanzar segun version del navegador
		 */
		
		if (browser.equals("firefox"))
		{
			cap = DesiredCapabilities.firefox();
			cap.setPlatform(plataform);
			cap.setBrowserName(browser);
			cap.setVersion(versionBrowser);
		}else if (browser.equals("chrome")) {
			cap = DesiredCapabilities.chrome();
			cap.setPlatform(plataform);
			cap.setBrowserName(browser);
			cap.setVersion(versionBrowser);
		} else if(browser.equals("internet explorer")){
			cap = DesiredCapabilities.internetExplorer();
			cap.setPlatform(plataform);
			cap.setBrowserName(browser);
			cap.setVersion(versionBrowser);
		} else if (browser.equals("safari")){
			cap = DesiredCapabilities.safari();
			cap.setPlatform(plataform);
			cap.setBrowserName(browser);
			/**
			 * - INICIO - Controlamos el caso en que no pusimos version de navegador alguna
			 */
			
			if (versionBrowser.equals("")){
				cap.setVersion("");
			}
			else
			{
				cap.setVersion(versionBrowser);
			}
			/**
			 * - FIN - Controlamos el caso en que no pusimos version de navegador alguna
			 */
			
		} 
		
		/**
		 * - FIN - logica para lanzar segun version del navegador
		 */
		cap.setJavascriptEnabled(true);	
		driver = new RemoteWebDriver(new URL(URL_NODE_HUB), cap);
	
		
	}
	
/**
 * Test en el cual vamos a chequear que al seleccionar comprar como tipo de operacion
 * habitacion y vacacional es desactivado y no posible de seleccionar en el area 
 * @throws InterruptedException
 * @throws MalformedURLException
 */
	
@Test (dependsOnMethods={"generoDriver"})
	public void ComprarDeactivateHabitacionVacacional() throws InterruptedException, MalformedURLException{
		com.pruebas.seleniumGridTestNG.dataDrivenParallelXML.ManejadorPrograma manejador = new com.pruebas.seleniumGridTestNG.dataDrivenParallelXML.ManejadorPrograma(driver);
		manejador.logicaPrograma();
		
}

@AfterTest
public void tearDown(){
	driver.quit();
}
}

