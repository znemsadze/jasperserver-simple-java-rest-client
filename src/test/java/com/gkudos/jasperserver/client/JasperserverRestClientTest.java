package com.gkudos.jasperserver.client;

import static org.testng.Assert.assertNotNull;
import java.io.File;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
 

/**
 * 
 * @author juanmendez@gkudos.com
 * 
 */

public class JasperserverRestClientTest {
	public static final Logger LOGGER = LoggerFactory.getLogger(JasperserverRestClientTest.class);
	
	private final static String serverUrl = "http://192.168.9.38:8080/jasperserver-pro";
	private final static String serverUser = "jasperadmin|organization_1";
	private final static String serverPassword = "jasperadmin";
	
	private static File outPutDir;
	
	@BeforeTest
	public void beforeTest() {
		LOGGER.debug("beforeTest...");
		outPutDir = new File(System.getProperty("java.io.tmpdir"));
		assertNotNull(outPutDir);
	}

	@AfterTest
	public void afterTest() {
		LOGGER.debug("afterTest...");
	}
	

	/**
	 * Ejecuta el reporte y retorna un archivo
	 * @throws Exception 
	 */
	@Test
	public static void testGetReportAsFile() throws Exception {
//		LOGGER.debug("testGetReportAsFile");
//		try {
		outPutDir=new File("/opt/pdfs");
			Report report = new Report();
			report.setUrl("/reports/samples/Department");
			report.setOutputFolder(outPutDir.getAbsolutePath());
			LOGGER.info(report.toString());
			JasperserverRestClient client = JasperserverRestClient.getInstance(serverUrl, serverUser, serverPassword);
			File reportFile  = client.getReportAsFile(report);
			assertNotNull(reportFile);
			LOGGER.debug("reportFile:"+reportFile.getAbsolutePath());
//		} catch (Exception e) {
//			fail(e.getMessage(), e);
//		}
	}
	
//	@Test
//	public void testGetBigReportAsFile() {
//		LOGGER.debug("testGetBigReportAsFile");
//		try {
//			Report report = new Report();
//			report.setUrl("/reports/samples/SalesByMonth");
//			report.setOutputFolder(outPutDir.getAbsolutePath());
//			LOGGER.info(report.toString());
//			JasperserverRestClient client = JasperserverRestClient.getInstance(serverUrl, serverUser, serverPassword);
//			File reportFile = client.getReportAsFile(report);
//			assertNotNull(reportFile);
//			LOGGER.debug("reportFile:"+reportFile.getAbsolutePath());
//		} catch (Exception e) {
//			fail(e.getMessage(), e);
//		}
//	}
//	
//	@Test
//	public void testGetBigReportAsExcelFile() {
//		LOGGER.debug("testGetBigReportAsExcelFile");
//		try {
//			Report report = new Report();
//			report.setFormat(Report.FORMAT_EXCEL);
//			report.setUrl("/reports/samples/SalesByMonth");
//			report.setOutputFolder(outPutDir.getAbsolutePath());
//			LOGGER.info(report.toString());
//			JasperserverRestClient client = JasperserverRestClient.getInstance(serverUrl, serverUser, serverPassword);
//			File reportFile = client.getReportAsFile(report);
//			assertNotNull(reportFile);
//			LOGGER.debug("reportFile:"+reportFile.getAbsolutePath());
//		} catch (Exception e) {
//			fail(e.getMessage(), e);
//		}
//	}
//	
//	@Test
	public static void  testGetReportWithParamsAsFile() throws Exception {
		LOGGER.debug("testGetReportWithParamsAsFile");
		 
			Report report = new Report();
			report.setUrl("/reports/samples/Department");
			report.setOutputFolder(outPutDir.getAbsolutePath());
			report.addParameter("department", "11");
			LOGGER.info(report.toString());
			JasperserverRestClient client = JasperserverRestClient.getInstance(serverUrl, serverUser, serverPassword);
			File reportFile = client.getReportAsFile(report);
			assertNotNull(reportFile);
			LOGGER.debug("reportFile:"+reportFile.getAbsolutePath());
		 
	}

	
	public static void main(String[] argv){
		try {
			testGetReportAsFile();
			testGetReportWithParamsAsFile() ;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	 
	}
}
