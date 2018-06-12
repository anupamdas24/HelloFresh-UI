/*
 * The package provides the data by interacting with MS excel file, which we treat as Test Data Provider
 */

package com.utilities;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Calendar;

import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCreationHelper;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFHyperlink;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import jxl.Sheet;
import jxl.Workbook;

public class ExcelDP {

	public static String path;
	public static FileInputStream fis = null;
	public static FileOutputStream fileOut = null;
	private static XSSFWorkbook workbook = null;
	private static XSSFSheet sheet = null;
	private static XSSFRow row = null;
	private static XSSFCell cell = null;

	public ExcelDP(String path) {

		this.path = path;
		try {
			fis = new FileInputStream(path);
			org.apache.poi.ss.usermodel.Workbook workbook=WorkbookFactory.create(fis);
			org.apache.poi.ss.usermodel.Sheet sheet = workbook.getSheetAt(0);
			fis.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

		
	// data provider

	public static String[][] getTableArray(String filename, String sheetName) throws Exception {
		try {
			String FilePath = System.getProperty("user.dir") + "/src/test/resources/" + filename + ".xls";
			FileInputStream fs = new FileInputStream(FilePath);
			Workbook workbook = Workbook.getWorkbook(fs);
			Sheet sheet = workbook.getSheet(sheetName);
			int rows = sheet.getRows();
			int cols = sheet.getColumns();
			String[][] tabArray = new String[rows - 1][cols];
			for (int i = 1; i < rows; i++) {
				for (int j = 0; j < cols; j++) {
					tabArray[i - 1][j] = sheet.getCell(j, i).getContents();
				}
			}
			workbook.close();
			return (tabArray);
		} catch (Exception e) {
			System.out.println(e + Thread.currentThread().getStackTrace()[1].getClassName() + " dataprovider");
			return null;
		}
	}

}
