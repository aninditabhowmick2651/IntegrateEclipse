package utils;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class retryAnalyzer implements IRetryAnalyzer{
	int count=0;
	int retry=1;
	public boolean retry(ITestResult result) {
		
		while(count<retry)// TODO Auto-generated method stub
		{
			count++;
			return true;
		}
			return false;
	}
	

}
