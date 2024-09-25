package utilities;

import java.io.File;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormatter;

//import org.apache.poi.ss.usermodel.Cell;

//import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class XLUtility {
	
	public FileInputStream fis;
	public FileOutputStream fos;
	public XSSFWorkbook workbook;
	public XSSFSheet sheet;
	public XSSFRow row;
	public XSSFCell cell;
	public CellStyle style;   
	String path;

	 public XLUtility(String path)
	{
		this.path=path;
	}
	
	public int getRowCount(String Sheetname) throws IOException
	{

		fis=new FileInputStream(path);
		XSSFWorkbook workbook=new XSSFWorkbook(fis);
		XSSFSheet sheet=workbook.getSheet(Sheetname);
		int rowcnt=sheet.getLastRowNum();
		workbook.close();
		fis.close();
		return rowcnt;
	}
	
	public int getCellCount(String Sheetname,int rownum) throws IOException
	{

		fis=new FileInputStream(path);
		XSSFWorkbook workbook=new XSSFWorkbook(fis);
		XSSFSheet sheet=workbook.getSheet(Sheetname);
		 row=sheet.getRow(rownum);
		int cellcnt= row.getLastCellNum();
		workbook.close();
		fis.close();
		return cellcnt;
	}
	
	
	public String getCellData(String Sheetname,int rownum,int colnum) throws IOException
	{

		fis=new FileInputStream(path);
		XSSFWorkbook workbook=new XSSFWorkbook(fis);
		XSSFSheet sheet=workbook.getSheet(Sheetname);
		 row=sheet.getRow(rownum);
		 cell=row.getCell(colnum);
		
		DataFormatter formatter=new DataFormatter();
		String data;
		try
		{
			data=formatter.formatCellValue(cell);
		}
		catch(Exception e)
		{
			data="";
		}
		
		workbook.close();
		fis.close();
		return data;
	}
	
	public String setCellData(String sheetName,int rownum,int colnum,String data) throws IOException
	{

		File xlfile=new File(path);
		if(!xlfile.exists())    // If file not exists then create new file
		{
		workbook=new XSSFWorkbook();
		fos=new FileOutputStream(path);
		workbook.write(fos);
		}
				
		fis=new FileInputStream(path);
		workbook=new XSSFWorkbook(fis);
			
		if(workbook.getSheetIndex(sheetName)==-1) // If sheet not exists then create new Sheet
			workbook.createSheet(sheetName);
		
		sheet=workbook.getSheet(sheetName);
					
		if(sheet.getRow(rownum)==null)   // If row not exists then create new Row
				sheet.createRow(rownum);
		row=sheet.getRow(rownum);
		
		cell=row.createCell(colnum);
		cell.setCellValue(data);
		fos=new FileOutputStream(path);
		workbook.write(fos);		
		workbook.close();
		fis.close();
		fos.close();
		return data;
	}
	
public void PutCellData(String xlFilePath, String Sheetname, int rowNum,int col,String Text) throws IOException {
		
		fis=new FileInputStream(xlFilePath);
		XSSFWorkbook workbook=new XSSFWorkbook(fis);
		XSSFSheet sheet=workbook.getSheet(Sheetname);
		
		if(sheet.getRow(rowNum)==null)
		{
			row=sheet.createRow(rowNum);
		}
		else
		{
			row=sheet.getRow(rowNum);
		}
		
		
		if(row.getCell(col)==null)
		{
			cell=row.createCell(col);
		}
		else
		{
			cell=row.getCell(col);
		}
		
		cell=sheet.getRow(rowNum).getCell(col);
		cell.setCellValue(Text);
		
		fos=new FileOutputStream(xlFilePath);
		workbook.write(fos);
		
		fos.close();
		workbook.close();
		fis.close();
		
	}
	
	
}
