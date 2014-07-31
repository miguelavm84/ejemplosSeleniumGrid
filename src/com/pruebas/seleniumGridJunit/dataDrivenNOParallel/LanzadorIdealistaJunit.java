package com.pruebas.seleniumGridJunit.dataDrivenNOParallel;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.openqa.selenium.Platform;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import org.junit.AfterClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;


@RunWith(Parameterized.class)
public class LanzadorIdealistaJunit {
	private static String browser; // nombre del navegador (chrome,internet explorer,safari,firefox)
	private static String versionBrowser; // versi—n del navegador XX.XX
	private static String platformBrowser; // plataforma , Platform.MAC, Platform.WINDOWS , Platform.LINUX
	private static String URL_NODE_HUB; // http://localhost:4444/wd/hub
	private  static RemoteWebDriver driver;
	//private static WebDriver driver;
	@Rule public TestName name = new TestName();
	/**
	 * - INICIO - Logica para recoger los datos por fichero CVS configuracion de ejecuci—n en maquinas
	 * @return
	 * @throws IOException
	 */
	@Parameters
	public static Collection testData() throws IOException{
		return getTestData("ficheroPropiedadesGrid.csv");
	}
	
	  public LanzadorIdealistaJunit(String browser,String versionBrowser,String platformBrowser,String URL_NODE_HUB){
		  
	   this.browser=browser;
	   this.versionBrowser = versionBrowser;
	   this.platformBrowser = platformBrowser;
	   this.URL_NODE_HUB = URL_NODE_HUB;
	  }
	   
	  private static Collection<String[]> getTestData(String fileName) throws IOException {
			List <String[]> records = new ArrayList<String[]>();
			String record;
			
			BufferedReader file = new BufferedReader(new FileReader(fileName));
			
			while ((record=file.readLine())!=null){
				String fields[] = record.split(",");
				records.add(fields);
			}
			file.close();
			return records;
		}
	  
	  /**
		 * - FIN - Fin de L—gica para recoger los datos por fichero CVS configuracion de ejecuci—n en maquinas
		 * @return
	 * @throws MalformedURLException 
		 * @throws IOException
		 */
	

@Test
	public void generoDriver() throws InterruptedException, MalformedURLException{
		
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
	
@Test
	public void ComprarDeactivateHabitacionVacacional() throws InterruptedException, MalformedURLException{
		ManejadorDrivenNoParallel manejador = new ManejadorDrivenNoParallel(driver);
		manejador.logicaPrograma();
}
	
	
@AfterClass
public static void tearClassDown() {
	driver.quit();
	}	
}
