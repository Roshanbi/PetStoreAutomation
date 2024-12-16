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

		String apidata[] = new String[totalrows];// storing data in 2 dimensional array

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
}
