package utilities;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.openqa.selenium.bidi.module.Browser;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import testBase.BaseClass;

public class ExtentReportManager implements ITestListener{
	
	public ExtentSparkReporter sparkReporter;
	public ExtentReports extent;
	public ExtentTest test;
	
	String repName;
	
	 public void onStart(ITestContext testContext) {
	    
			/*
			 * SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy.HH.mm.ss"); Date dt =
			 * new Date(); String currentDateTimeStamp = df.format(dt);
			 */
		 
		 String timeStamp = new SimpleDateFormat("dd.MM.yyyy.HH.mm.ss").format(new Date());
		 
		 repName = "Test-Report - " + timeStamp + ".html";
		 sparkReporter = new ExtentSparkReporter(".\\reports\\" + repName);
		 
		 sparkReporter.config().setDocumentTitle("Opencart Automation Report"); //title of the report
		 sparkReporter.config().setReportName("Opencart Functional Testing");
		 sparkReporter.config().setTheme(Theme.DARK);
		 
		 extent = new ExtentReports();
		 
		 extent.attachReporter(sparkReporter);
		 extent.setSystemInfo("Application", "OpenCart");
		 extent.setSystemInfo("Module", "Admin");
		 extent.setSystemInfo("Sub Module", "Customers");
		 extent.setSystemInfo("UserName", System.getProperty("user.name"));
		 extent.setSystemInfo("Enviromment", "QA");
		 
		 String os = testContext.getCurrentXmlTest().getParameter("os");
		 extent.setSystemInfo("Operating System", os);
		 
		 String browser = testContext.getCurrentXmlTest().getParameter("browser");
		 extent.setSystemInfo("Browser", browser);
		 
		 List<String> includeGroups = testContext.getCurrentXmlTest().getIncludedGroups();
		 if(!includeGroups.isEmpty())
		 {
			 extent.setSystemInfo("Groups", includeGroups.toString());
		 }
		 
	  }

	
	  public void onTestSuccess(ITestResult result) 
	  {
		  
		  test = extent.createTest(result.getTestClass().getName());
		  test.assignCategory(result.getMethod().getGroups()); //to display groups in report
		  test.log(Status.PASS,result.getName()+"got succesfuly executed");

	  }
	  
	  public void onTestFailure(ITestResult result) 
	  {
		  test = extent.createTest(result.getTestClass().getName());
		  test.assignCategory(result.getMethod().getGroups());
		  test.log(Status.FAIL, result.getName()+" got failed");
		  test.log(Status.INFO, result.getThrowable().getMessage());
		  
		  try {
			  String impPath = new BaseClass().captureSceen(result.getName());
			  test.addScreenCaptureFromPath(impPath);
		  }
		  catch(IOException e1)
		  {
			  e1.printStackTrace();
		  }
	  }
	  
	  public void onTestSkipped(ITestResult result) 
	  {
		  test = extent.createTest(result.getTestClass().getName());
		  test.assignCategory(result.getMethod().getGroups());
		  test.log(Status.SKIP, result.getName()+" got skipped");
		  test.log(Status.INFO, result.getThrowable().getMessage());
	  }
	  
	  public void onFinish(ITestContext testContext) 
	  {
		    extent.flush();
		    
		    String pathOfExtentReport = System.getProperty("user.dir") + "\\reports\\" + repName;
		    File extentReport = new File(pathOfExtentReport);
		    
		    try
		    {
		    	Desktop.getDesktop().browse(extentReport.toURI()); //open this report on browser automatically
		    }
		    catch(IOException e)
		    {
		    	e.printStackTrace();
		    }
		    
	  }
	  	  
	  
}
