package api.utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviders {

	@DataProvider(name = "Data")
	public String[][] getAllData() throws IOException {
		String path = System.getProperty("user.dir") + "//testData//Userdata.xlsx";
		XLUtility xl = new XLUtility(path);

		int totalrows = xl.getRowCount("Sheet1");
		int totalcols = xl.getCellCount("Sheet1", 1);

		String apidata[][] = new String[totalrows][totalcols];

		for (int i = 1; i <= totalrows; i++) {
			for (int j = 0; j < totalcols; j++) {
				apidata[i - 1][j] = xl.getCellData("Sheet1", i, j);
			}
		}

		return apidata;
	}

	@DataProvider(name = "UserNames")
	public String[] getUserNames() throws IOException {
		String path = System.getProperty("user.dir") + "//testData//Userdata.xlsx";
		XLUtility xl = new XLUtility(path);

		int totalrows = xl.getRowCount("Sheet1");

		String apidata[] = new String[totalrows];// storing data in 1 dimensional array

		for (int i = 1; i <= totalrows; i++) {
			apidata[i - 1] = xl.getCellData("Sheet1", i, 1);

		}

		return apidata;
	}

	@DataProvider(name = "storeData")
	public String[][] getStoreData() throws IOException {
		String path = System.getProperty("user.dir") + ".\\testData\\Storedata.xlsx";
		XLUtility xl = new XLUtility(path);

		int totalrows = xl.getRowCount("Sheet1");
		int totalcols = xl.getCellCount("Sheet1", 1);

		String apidata[][] = new String[totalrows][totalcols];

		for (int i = 1; i <= totalrows; i++) {

			for (int j = 0; j < totalcols; j++) {

				apidata[i - 1][j] = xl.getCellData("Sheet1", i, j);// java
			}
		}

		return apidata;

	}

	@DataProvider(name = "ids")
	public String[] getid() throws IOException {

		String path = System.getProperty("user.dir") + "\\testData\\Storedata.xlsx";

		XLUtility xl = new XLUtility(path);

		int totalrows = xl.getRowCount("Sheet1");

		String apidata[] = new String[totalrows];

		for (int i = 1; i <= totalrows; i++) {

			apidata[i - 1] = xl.getCellData("Sheet1", i, 0);
		}
		return apidata;

	}
	
	@DataProvider(name="PetData")
	public Object[][] getPetData() throws IOException{
		
		String path=System.getProperty("user.dir")+"\\testData\\PetData.xlsx";
		XLUtility x1=new XLUtility(path);
	int totalRows =	x1.getRowCount("sheet1");
	int totalCells = x1.getCellCount("sheet1", 1);
	Object[][] apidata=new Object[totalRows][totalCells];
	
	for(int i=1; i<=totalRows; i++) {
		for(int j=0; j<totalCells; j++) {
			apidata[i-1][j] = x1.getCellData("sheet1",i,j);
		}
		
	}
	
		return apidata;
		}
	@DataProvider(name="Ids")
	public String[]getId() throws IOException{
		
		String path=System.getProperty("user.dir")+"\\testData\\PetData.xlsx";
		XLUtility x1=new XLUtility(path);
	int totalRows=	x1.getRowCount("sheet1");
		String apidata[]=new String[totalRows];
		
		for(int i=1;i<=totalRows;i++) {
			apidata[i-1]=x1.getCellData("sheet1",i, 0);
		}
		return apidata;
		
	}
}
