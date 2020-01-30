package com.amazon.AmazonUI;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;


import com.amazon.AmazonUI.Utils.CommandLineParameters;
import com.amazon.AmazonUI.Utils.ExtentManager;
import com.amazon.AmazonUI.factory.UITestFactory;
import com.amazon.AmazonUI.factory.WebDriverFactory;
import com.google.common.io.Files;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;


public class UITest {
	protected WebDriver driver;
	protected Logger logger = Logger.getLogger(UITest.class);
	protected ExtentTest extentTest;
	protected UITestFactory pageFactory;
	protected String fileName;
	
    @BeforeClass(alwaysRun = true)
    @Parameters({ "testData"})
    public void setUp(@Optional String testData) throws Exception {
        logger.info("************Inside setup*************");
        fileName = testData; 
        initializeDriver();
    }
	protected WebDriver initializeDriver() {		
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability("browser", System.getProperty(CommandLineParameters.BROWSER));
		capabilities.setCapability("platformName", System.getProperty(CommandLineParameters.PLATFORM));
		capabilities.setCapability("marionette", false);
		driver = WebDriverFactory.createWebDriver(capabilities);

		return driver;
	}
	
    public boolean start(String url) {
        driver.get(url);
        driver.manage().window().fullscreen();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        return true;
    }

    public boolean stop() {
        driver.quit();
        return true;
    }
	
	
	@BeforeMethod(alwaysRun = true)
	public void handleTestMethodName(Method method,@Optional Object[] testData) {
		try {
			
			//TestData test = (TestData) testData[0];
			extentTest = ExtentManager.startTest(method.getName());
			pageFactory = new UITestFactory(driver, extentTest);
			
			Field[] fs = this.getClass().getDeclaredFields();
			fs[0].setAccessible(true);
			List<Field> objects = new ArrayList<Field>();
			for (Field property : fs) {
				if (property.getType().getSuperclass().isAssignableFrom(UIActions.class)) {
					objects.add(property);
					property.setAccessible(true);
					property.set(this, pageFactory.getObject(property.getType()));
				}
			}
			for (Field obj : objects){
				Method[] allMethods = obj.get(this).getClass().getMethods();
				for (Method function : allMethods) {
					if (function.getName().equalsIgnoreCase("setExtentTest"))
						function.invoke(obj.get(this), extentTest);
				}
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}
	public String takeScreenshot(String fileName) {
        try {
            logger.info("Taking Screenshot");
            File destDir = new File("images/");
            if (!destDir.exists())
                destDir.mkdir();
            File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            try {
                Files.copy(scrFile, new File("images/" + fileName));
            } catch (IOException e) {
                e.printStackTrace();
                logger.error("Not able to take the screenshot");
            }
        } catch (Exception ex) {
            logger.info(ex.getMessage());
        }
        return "images/" + fileName;
    }
	
	@AfterClass(alwaysRun = true)
	public void afterClass() {
		try {
			if (System.getProperty(CommandLineParameters.PLATFORM).equalsIgnoreCase("android")) {
				
				driver.close();
				logger.info("Closing App");
			}else{
				driver.quit();

			}
		} catch (Exception e) {
			logger.info("Error While Closing App");
		}
	}
	
	@AfterMethod(alwaysRun = true)
	protected void afterMethod(ITestResult result) throws Exception {

		try {

			if (result.getStatus() == ITestResult.FAILURE) {

				extentTest.log(LogStatus.FAIL, result.getThrowable().getMessage());
				logger.error("**** Test " + extentTest.getTest().getName() + " failed ******");
				String fileName = takeScreenshot(extentTest.getTest().getName());
				extentTest.log(LogStatus.INFO, "Snapshot is " + extentTest.addScreenCapture(fileName));

			} else if (result.getStatus() == ITestResult.SKIP) {
				extentTest.log(LogStatus.SKIP,
						extentTest.getTest().getName() + " Test skipped " + result.getThrowable());
				logger.info("**** Test" + extentTest.getTest().getName() + " Skipped ******");
			} else if (result.getStatus() == ITestResult.SUCCESS) {

				logger.info("**** Test" + extentTest.getTest().getName() + " passed ******");
			} else {
				extentTest.log(LogStatus.FAIL, result.getThrowable());
				logger.error("**** Test " + extentTest.getTest().getName() + " failed ******");
			}
		} catch (Exception ex) {
			extentTest.log(LogStatus.INFO, ex.getMessage());
			logger.warn(ex.getMessage());
		}
		ExtentManager.endTest(extentTest);
		ExtentManager.flush();
	}	
	
/*    @DataProvider(name = "testData")
    public Object[][] createTestData() throws IOException {
 
        Object[][] arr = new Object[getTestData().size()][1];
 
        for(int i =0; i < getTestData().size();i++){
            arr[i][0] = getTestData().get(i);
        }
 
        return arr;
    }
    
    private List<TestData> getTestData() throws IOException {
        List<TestData> testData = new ArrayList<TestData>();
        BufferedReader bReader = new BufferedReader(new FileReader(fileName));
 
        Iterable<CSVRecord> records = CSVFormat.DEFAULT.parse(bReader);
        for (CSVRecord record : records) {
        	TestData test = new TestData();
           
            testData.add(test);
        }
        return testData;
    }*/

}
