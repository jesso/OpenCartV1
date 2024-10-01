package utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviders {
	//Data Provider 1
	@DataProvider(name="LoginData")
	public String[][] getData() throws IOException
	{
	//	String path = "G:\\selenium_workspace\\OpenCartV1\\testData\\LoginData.xlsx";
	//	String path = ".\\testData\\LoginData.xlsx";
		String path = ".\\testData\\Opencart_LoginData.xlsx";
		ExcelUtility xlUtility = new ExcelUtility(path);
		
		int totalrows=xlUtility.getRowCount("Sheet1");	
		int totalcols=xlUtility.getCellCount("Sheet1",1);
				
		String logindata[][]=new String[totalrows][totalcols];//created for two dimension array which can store the data user and password
		
		for(int i=1;i<=totalrows;i++)  //1   //read the data from xl storing in two deminsional array
		{		
			for(int j=0;j<totalcols;j++)  //0    i is rows j is col
			{
				logindata[i-1][j]= xlUtility.getCellData("Sheet1",i, j);  //1,0
			}
		}
	return logindata;//returning two dimension array
				

	}
}
