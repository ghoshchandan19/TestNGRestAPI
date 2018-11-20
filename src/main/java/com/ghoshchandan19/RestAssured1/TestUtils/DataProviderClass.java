package com.ghoshchandan19.RestAssured1.TestUtils;

import java.lang.reflect.Method;
import java.util.Hashtable;

import org.testng.annotations.DataProvider;

import com.ghoshchandan19.RestAssured1.Setup.TestSetup;

public class DataProviderClass extends TestSetup{
	
	/*
	@DataProvider(name="dp")
	public Object[][] getData(Method m)//Reflection concept
	{
		String sheetName=m.getName();
		int rows=excelReader.getRowCount(sheetName);
		int cols=excelReader.getColumnCount(sheetName);
		System.out.println(rows);
		System.out.println(cols);
		Object[][] data=new Object[rows-1][1];
		Hashtable<String,String> table=null;
		for(int rowNum=2;rowNum<=rows;rowNum++)
		{
			table=new Hashtable<String,String>();
			for(int colNum=0;colNum<cols;colNum++)
			{
				table.put(excelReader.getCellData(sheetName,colNum,1),excelReader.getCellData(sheetName, colNum, rowNum));
				data[rowNum-2][0]=table;
			}
			
		}
		return data;
		*/
	
	@DataProvider(name="dp")
	public  Object[][] getData(Method m) {

		String sheetName=configProperty.getSheetName();
		int rows = excelReader.getRowCount(sheetName);//100
		String testName = m.getName();
		int testCaseRowNum = 1;

		for (testCaseRowNum = 1; testCaseRowNum <= rows; testCaseRowNum++) {

			String testCaseName = excelReader.getCellData(sheetName, 0, testCaseRowNum);
			//System.out.println("TestCase name in excel-->"+testCaseName);
			if (testCaseName.equalsIgnoreCase(testName))
				break;

		}// Checking total rows in test case

		int dataStartRowNum = testCaseRowNum + 2;//dataStartRowNum=8

		int testRows = 0;
		while (!excelReader.getCellData(sheetName, 0, dataStartRowNum + testRows).equals("endOfTestData")) {

			testRows++;
		}
		// Checking total cols in test case

		//System.out.println("Total no of rows:"+testRows);
		int colStartColNum = testCaseRowNum + 1;//7
		int testCols = 0;

		while (!excelReader.getCellData(sheetName, testCols, colStartColNum).equals("")) {

			testCols++;

		}
		//[2][1]
		
		Object[][] data = new Object[testRows][1];
		//object[][] data= new Object[2][1];
		//data[0][0]=
		//data[1][0]=

		int i = 0;
		for (int rNum = dataStartRowNum; rNum < (dataStartRowNum + testRows); rNum++) {

			Hashtable<String, String> table = new Hashtable<String, String>();

		
			for (int cNum = 0; cNum < testCols; cNum++) {

				String colName = excelReader.getCellData(sheetName, cNum, colStartColNum);
				String testData = excelReader.getCellData(sheetName, cNum, rNum);
				

				table.put(colName, testData);

			}

			data[i][0] = table;
			i++;

		}

		return data;
		
		
		

	}
	

}
