package com.store.utilities;
//com.store.utilities.Listners
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class Listners implements ITestListener{

	ExtentSparkReporter htmlReporter;
	ExtentReports reports;
	ExtentTest test;
	
	ReadConfig readConfig = new ReadConfig();
	
	public void configureReport() {
		
		String timeStamp = new SimpleDateFormat("yyyy.mm.dd hh.mm.ss").format(new Date());
		String reportName = "Shopping cart reoprt" + timeStamp +".html";
		
		htmlReporter = new ExtentSparkReporter(System.getProperty("user.dir")+ "//Reports//" + reportName);
		reports = new ExtentReports();
		reports.attachReporter(htmlReporter);
		
		//add test environment details to report
		reports.setSystemInfo("OS", "Windows");
		reports.setSystemInfo("Browser", readConfig.getbrowser());
		reports.setSystemInfo("Tester", "Amit Negi");
		
		//change look and feel of the report
		htmlReporter.config().setDocumentTitle("Extent Listner Report");
		htmlReporter.config().setReportName("This 1st report");
		htmlReporter.config().setTheme(Theme.DARK);
		}
	
	
	//LISTNER METHODS-----------------------------------
	
	//ON START: method is called when any test starts
	public void onStart(ITestContext Result) {
		configureReport();
		System.out.println("Start method invoked");
	}
	
	
	//ON FINISH: method is called when all test cases are executed
	public void onFinish(ITestContext Result) {
		System.out.println("onFinish method invoked");
		reports.flush(); //it is mandatory to call flush() method to ensure information is written to the started reporter.
	}
	
	
	//When test case gets failed, then this method is called
	public void onTestFailure(ITestResult Result) {
		System.out.println("Name of the test method failed:" + Result.getName());
		test = reports.createTest(Result.getName());
		test.log(Status.FAIL, MarkupHelper.createLabel("Name of the failed test case is: " + Result.getName(), ExtentColor.RED));
		
		//adding screenshot in report
		String ScreenshotPath = System.getProperty("user.dir") + "\\Screenshots\\" + Result.getName() + ".png";
		File ScreenshotFile = new File(ScreenshotPath);
		
		if(ScreenshotFile.exists()) {
			test.fail("Below is the screenshot for failed test" + test.addScreenCaptureFromPath(ScreenshotPath));
		}
	}
	
	
	//When test got skipped then this method is called
	public void onTestSkipped(ITestResult Result) {
		System.out.println("Name of the test method skipped:" + Result.getName());
		test = reports.createTest(Result.getName());
		test.log(Status.SKIP, MarkupHelper.createLabel("Name of skipped test case:"+ Result.getName(), ExtentColor.YELLOW));
	}
	
	
	//when tset starts then this method is called
	public void onTestStart(ITestResult Result) {
		System.out.println("Name of the test method started:" + Result.getName());
	}
	
	//When test case is passed then this method is called
	public void onTestSuccess(ITestResult Result) {
		System.out.println("Name of test method passed:"+ Result.getName());
		test=reports.createTest(Result.getName());
		test.log(Status.PASS, MarkupHelper.createLabel("Nam of test case passes"+ Result.getName(), ExtentColor.GREEN));
		
	}
	
	
	
}
