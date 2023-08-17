package genericUtilities;

import java.io.IOException;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

/**
 * This class provides implementation to the ITestListener Interface of TestNG
 * 
 * @author SAMPAT
 */
public class ListenerImplementationClass implements ITestListener {

	ExtentReports report;
	ExtentTest test;

	/**
	 * Current script Execution is strated/ Single execution
	 */
	public void onTestStart(ITestResult result) {
		String methodName = result.getMethod().getMethodName();
		System.out.println(methodName + "test execution is Start");

		test = report.createTest(methodName);

	}

	public void onTestSuccess(ITestResult result) {
		String methodName = result.getMethod().getMethodName();
		test.log(Status.PASS,methodName+" --> Success");
	
	}

	public void onTestFailure(ITestResult result) {

		WebDriverUtility wUtil = new WebDriverUtility();
		JavaUtility jUtil = new JavaUtility();

		String methodName = result.getMethod().getMethodName();
		
		test.log(Status.FAIL, methodName+" --> Failed");
		test.log(Status.FAIL, result.getThrowable());
		
//		createOrgTest-30-11-2022 17-23-45;
		String screenshotName = methodName + "-" + jUtil.getsystemDateInFormate();
		
		try {
			String path = wUtil.takestheScreenshot(BaseClass.sdriver, screenshotName);
			test.addScreenCaptureFromPath(path);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void onTestSkipped(ITestResult result) {
		String methodName = result.getMethod().getMethodName();

		test.log(Status.SKIP, methodName + " --> Skipped");
		test.log(Status.SKIP, result.getThrowable());

	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {

	}

	public void onTestFailedWithTimeout(ITestResult result) {

	}

	/**
	 * Execution is started/ Whole Execution
	 */
	public void onStart(ITestContext context) {
//		Configure the report
		System.out.println("Execution Started");

//		Create Object of ExtentSparkreporter	1Report-1 Dec 2022 17-34-3.html//
		ExtentSparkReporter htmlReport = new ExtentSparkReporter(
				".\\ExtentsReports\\Report-" + new JavaUtility().getsystemDateInFormate() + ".html");
		htmlReport.config().setDocumentTitle("exeecution report");
		htmlReport.config().setTheme(Theme.DARK);
		htmlReport.config().setReportName("vTiger Execution report");

//		Attach the hmlReport to Extent Report 2
		report = new ExtentReports();
		report.attachReporter(htmlReport);

		report.setSystemInfo("Base Platform", "Windows");
		report.setSystemInfo("Base Broser", "Chrome");
		report.setSystemInfo("Base Environment", "Testing");
		report.setSystemInfo("Base URL", "http://localhost:8888");
		report.setSystemInfo("Author", "Sampat");

	}

	/**
	 * Execution is Ended
	 */
	public void onFinish(ITestContext context) {
		System.out.println("Execution Finished");

//		Flush the report - consolidate the status of every test script and generate the report 3
		report.flush();
	}
}
