package genericUtilities;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * This class consists of java specific methods
 * 
 * @author Dell
 *
 */
public class JavaUtility {
	/**
	 * This method will generate random number for every execution
	 * 
	 * @return
	 */
	public int getRandomNumber() {
		Random ran = new Random();
		return ran.nextInt(1000);
	}

	/**
	 * This method will generate the current system date
	 * 
	 * @return
	 */
	public String getSystemDate() {
		Date d = new Date();
		return d.toString();
	}

	/**
	 * This method is used to generate current time
	 */
	public String getcurrentTime() {
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("dd_MM_yyyy_hh_mm_sss");
		String actualDate = sdf.format(date);
		return actualDate;
	}

	/**
	 * This method will generate the current system date in specific format
	 * 
	 * @return
	 */
	public String getsystemDateInFormate() {
		Date d = new Date();
		String[] dArr = d.toString().split(" ");
		String date = dArr[2];
		String month = dArr[1];
		String year = dArr[5];
		String time = dArr[3].replace(':', '-');
		String dateInFormate = date + " " + month + " " + year + " " + time;
		return dateInFormate;
	}

}
