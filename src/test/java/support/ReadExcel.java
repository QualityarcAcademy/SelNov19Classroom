package support;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadExcel {

	public static String[][] readExcelData(String sheetName) {
		String[][] data = null;
		// TODO Auto-generated method stub
		try {
			XSSFWorkbook workbook = new XSSFWorkbook(new FileInputStream("./data/"+sheetName+".xlsx"));		
			XSSFSheet sheet = workbook.getSheetAt(0);
			int lastRow = sheet.getLastRowNum();
			XSSFRow row = sheet.getRow(0);
			short lastColumn = row.getLastCellNum();
			data = new String[lastRow][lastColumn];
			
			for (int i = 1; i <= lastRow; i++) {
				XSSFRow row1 = sheet.getRow(i);
				for (int j = 0; j < lastColumn; j++) {
					String val = row1.getCell(j).getStringCellValue();
					data[i-1][j] = val;
				}
			}
			workbook.close();
			System.out.println(Arrays.deepToString(data));
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return data;
	}
	
}
