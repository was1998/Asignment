package com.cg;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTRow;

public class Test {

	public static void main(String[] args) throws InvalidFormatException, IOException {
		// TODO Auto-generated method stub
		File file = new File("C:\\Users\\MOHAKHTA\\OneDrive - Capgemini\\Desktop\\All test\\Book.xlsx");
		XSSFWorkbook workBook = new XSSFWorkbook(file);
		XSSFSheet workSheet = workBook.getSheetAt(0);
		XSSFSheet workSheetTwo = workBook.getSheetAt(1);
		System.out.println("------------------------------------------------------------------");
		System.out.println("|                     Showing Mismatch Results                   |");
		System.out.println("------------------------------------------------------------------");
		System.out.println("|        VALUE1         |        VALUE2       |    ROW  |  CELL  |");
		System.out.println("------------------------------------------------------------------");
		
		for (int i = 1; i < workSheet.getPhysicalNumberOfRows(); i++) {
			Row rowFromFirstSheet = workSheet.getRow(i);
			Row rowFromScndSheet = workSheetTwo.getRow(i);
			for (int c = 0; c < rowFromFirstSheet.getLastCellNum(); c++) {
				CellType string=rowFromFirstSheet.getCell(c).getCellType();
				if (rowFromFirstSheet.getCell(c).getCellType().toString().equals("NUMERIC")) {
					if (rowFromFirstSheet.getCell(c).getNumericCellValue() != rowFromScndSheet.getCell(c)
							.getNumericCellValue()) {
						System.out.printf("|"+"%-23d"+"|"+"%-22d"+"|"+"%-8d"+"|"+"%-8d"+"|",(int)rowFromFirstSheet.getCell(c).getNumericCellValue(),(int)rowFromScndSheet.getCell(c).getNumericCellValue(),i,c+1);
						System.out.println("");
						
//						System.out.println((int)rowFromFirstSheet.getCell(c).getNumericCellValue() + " "
//								+ rowFromScndSheet.getCell(c).getNumericCellValue()+" "+"at line number"+i);
					}
				} else if (rowFromFirstSheet.getCell(c).getCellType().toString().equals("STRING")) {
					if (rowFromFirstSheet.getCell(c).getStringCellValue() != rowFromScndSheet.getCell(c)
							.getStringCellValue()) {
						System.out.printf("|"+"%-23s"+"|"+"%-22s"+"|"+"%-8s"+"|"+"%-8s"+"|",rowFromFirstSheet.getCell(c).getStringCellValue(),rowFromScndSheet.getCell(c).getStringCellValue(),i,c+1);
//						System.out.println(rowFromFirstSheet.getCell(c).getStringCellValue() + " "
//								+ rowFromScndSheet.getCell(c).getStringCellValue()+" "+i);
						System.out.println("");
					
					}

				}
			}

		}
		System.out.println("-----------------------------------------------------------------");
	}

                                         
}
