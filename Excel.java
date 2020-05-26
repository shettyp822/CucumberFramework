package com.cucumberFramework.enums;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Excel {
    public static HashMap<String, String> excelDataHash= new HashMap<>();

    public static void readExcel(String filePath,String fileName,String sheetName, Integer rowNum) throws IOException{

        //Create an object of File class to open xlsx file

        File file = new File(filePath+"\\"+fileName);

        //Create an object of FileInputStream class to read excel file

        FileInputStream inputStream = new FileInputStream(file);

        Workbook tesData = null;

        //Find the file extension by splitting file name in substring  and getting only extension name

        String fileExtensionName = fileName.substring(fileName.indexOf("."));

        //Check condition if the file is xlsx file

        if(fileExtensionName.equals(".xlsx")){
            //If it is xlsx file then create object of XSSFWorkbook class
            tesData = new XSSFWorkbook(inputStream);
        }

        Sheet testSheet = tesData.getSheet(sheetName);

        //Create a loop over all the rows of excel file to read it
        Row row = testSheet.getRow(rowNum);
        Row row0 = testSheet.getRow(0);
        for (int j = 0; j < row.getLastCellNum(); j++) {
            Cell cell = row.getCell(j);
            cell.setCellType(Cell.CELL_TYPE_STRING);
            excelDataHash.put(row0.getCell(j).toString().toLowerCase(),row.getCell(j).toString());
            System.out.print(cell.getStringCellValue()+"||");
        }
            System.out.println();
   }

    public  static String getExcelValue(String key)
    {
        if(excelDataHash.toString().equalsIgnoreCase("{}")) {
            return key;
        }
        else
        {
            return excelDataHash.get(key.toLowerCase());
        }

    }
}
