package com.springboot;

import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@SpringBootConfiguration
@ComponentScan(value = {"com.*"})
@EnableAutoConfiguration
public class DemoApplication  {



//	public static void main(String[] args) {
//		SpringApplication.run(DemoApplication.class, args);
//	}
//}
public static void main(String[] args) throws Exception{
	List<Map<String, Object>> list_data = new  ArrayList<Map<String, Object>>();
	//第一列数据
	List<Object> list_data0 = new  ArrayList<Object>();
	//第二列数据
	List<Object> list_data1 = new  ArrayList<Object>();
	//固定电话
	List<Map<String, Object>> list_tephone = new  ArrayList<Map<String, Object>>();
	List<Object> list_tephone0 = new  ArrayList<Object>();
	List<Object> list_tephone1 = new  ArrayList<Object>();
	File file = new File("C:\\Users\\dahai\\Desktop\\2.7w.xls");
	String[][] result = getData(file, 1);
	int sum = 0;
	int rowLength = result.length;

	List<Object> list = new  ArrayList<>();
       for(int i=0;i<rowLength;i++) {
		   for (int m = 0; m < result[i].length; m++) {
			   boolean bool = isNumericZidai(result[i][m]);
			   if (bool) {
			   	if (result[i][m].length() == 11){
			   		list.add(result[i][m]);
//					sum = sum + 1;
				}
			   }
		   }
	   }
		List<Object> list1 = list;
	   for (int a=0 ; a<list.size(); a++){
		  for(int b = 0; b<list1.size(); b++){
		  	if (list.get(a) == list1.get(b)){
		  		list.remove(a);
			}
		  }
	   }
	System.out.println("手机号总数是：" + list.size());






//		   String data0 = "";
//		   String data1 = "";
//		   String[] a = new String[8];
//		for(int j=0;j<result[i].length;j++) {
//			data0 = result[i][0];
//			//第二列数据
//			data1 = result[i][1];
//			a = data1.split(" ");
//		}
//		   //不为空
//		   if(!"".equals(data1)){
//			//只有一段字符，没有空格区分
//			if (a.length == 1){
//				//一段字符是11时是手机号
//				if (data1.length()==11){
//					Map<String, Object> map = new  HashMap<String, Object>();
//					map.put("one", data0);
//					map.put("two", data1);
//					list_data.add(map);
//				}else {
//					Map<String, Object> map = new  HashMap<String, Object>();
//					map.put("one", data0);
//					map.put("two", data1);
//					list_tephone.add(map);
//				}
//				//字段长度为二，有空格区分
//			}else {
//				//第二段字符11为手机号
//				if (a[1].length() == 11){
//					Map<String, Object> map = new  HashMap<String, Object>();
//					map.put("one", data0);
//					map.put("two", data1);
//					list_data.add(map);
//				}
//				else {
//					Map<String, Object> map = new  HashMap<String, Object>();
//					map.put("one", data0);
//					map.put("two", data1);
//					list_tephone.add(map);
//				}
//			}
//
//		   }else{
//			   Map<String, Object> map = new  HashMap<String, Object>();
//					map.put("one", data0);
//					map.put("two", data1);
//					list_tephone.add(map);
//		   }

//		   System.out.println("list_tephone1::" + list_tephone.size());
//	}
//	exportFeedBack(list_tephone);
}
	/**
	 * 读取Excel的内容，第一维数组存储的是一行中格列的值，二维数组存储的是多少个行
	 * @param file 读取数据的源Excel
	 * @param ignoreRows 读取数据忽略的行数，比喻行头不需要读入 忽略的行数为1
	 * @return 读出的Excel中数据的内容
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public static String[][] getData(File file, int ignoreRows)
			throws FileNotFoundException, IOException {
		List<String[]> result = new ArrayList<String[]>();
		int rowSize = 0;
		BufferedInputStream in = new BufferedInputStream(new FileInputStream(
				file));
		// 打开HSSFWorkbook
		POIFSFileSystem fs = new POIFSFileSystem(in);
		HSSFWorkbook wb = new HSSFWorkbook(fs);
		HSSFCell cell = null;
		for (int sheetIndex = 0; sheetIndex < wb.getNumberOfSheets(); sheetIndex++) {
			HSSFSheet st = wb.getSheetAt(sheetIndex);
			// 第一行为标题，不取
			for (int rowIndex = ignoreRows; rowIndex <= st.getLastRowNum(); rowIndex++) {
				HSSFRow row = st.getRow(rowIndex);
				if (row == null) {
					continue;
				}
				int tempRowSize = row.getLastCellNum() + 1;
				if (tempRowSize > rowSize) {
					rowSize = tempRowSize;
				}
				String[] values = new String[rowSize];
				Arrays.fill(values, "");
				boolean hasValue = false;
				for (short columnIndex = 0; columnIndex <= row.getLastCellNum(); columnIndex++) {
					String value = "";
					cell = row.getCell(columnIndex);
					if (cell != null) {
						// 注意：一定要设成这个，否则可能会出现乱码
						//cell.setEncoding(HSSFCell.ENCODING_UTF_16);
						switch (cell.getCellType()) {
							case HSSFCell.CELL_TYPE_STRING:
								value = cell.getStringCellValue();
								break;
							case HSSFCell.CELL_TYPE_NUMERIC:
								if (HSSFDateUtil.isCellDateFormatted(cell)) {
									Date date = cell.getDateCellValue();
									if (date != null) {
										value = new SimpleDateFormat("yyyy-MM-dd")
												.format(date);
									} else {
										value = "";
									}
								} else {
									value = new DecimalFormat("0").format(cell
											.getNumericCellValue());
								}
								break;
							case HSSFCell.CELL_TYPE_FORMULA:
								// 导入时如果为公式生成的数据则无值
								if (!cell.getStringCellValue().equals("")) {
									value = cell.getStringCellValue();
								} else {
									value = cell.getNumericCellValue() + "";
								}
								break;
							case HSSFCell.CELL_TYPE_BLANK:
								break;
							case HSSFCell.CELL_TYPE_ERROR:
								value = "";
								break;
							case HSSFCell.CELL_TYPE_BOOLEAN:
								value = (cell.getBooleanCellValue() == true ? "Y"
										: "N");
								break;
							default:
								value = "";
						}
					}
					if (columnIndex == 0 && value.trim().equals("")) {
						break;
					}
					values[columnIndex] = rightTrim(value);
					hasValue = true;
				}

				if (hasValue) {
					result.add(values);
				}
			}
		}
		in.close();
		String[][] returnArray = new String[result.size()][rowSize];
		for (int i = 0; i < returnArray.length; i++) {
			returnArray[i] = (String[]) result.get(i);
		}
		return returnArray;
	}

	/**
	 * 去掉字符串右边的空格
	 * @param str 要处理的字符串
	 * @return 处理后的字符串
	 */
	public static String rightTrim(String str) {
		if (str == null) {
			return "";
		}
		int length = str.length();
		for (int i = length - 1; i >= 0; i--) {
			if (str.charAt(i) != 0x20) {
				break;
			}
			length--;
		}
		return str.substring(0, length);
	}

//生成excle工具类
	public static HSSFWorkbook getHSSFWorkbook(String sheetName,String []title,String [][]values, HSSFWorkbook wb){
		// 第一步，创建一个webbook，对应一个Excel文件
		if(wb == null){
			wb = new HSSFWorkbook();
		}
		// 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
		HSSFSheet sheet = wb.createSheet(sheetName);
		// 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short
		HSSFRow row = sheet.createRow(0);
		// 第四步，创建单元格，并设置值表头 设置表头居中
		HSSFCellStyle style = wb.createCellStyle();
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式

		HSSFCell cell = null;
		//创建标题
		for(int i=0;i<title.length;i++){
			cell = row.createCell(i);
			cell.setCellValue(title[i]);
			cell.setCellStyle(style);
		}
		//创建内容
		for(int i=0;i<values.length;i++){
			row = sheet.createRow(i + 1);
			for(int j=0;j<values[i].length;j++){
				row.createCell(j).setCellValue(values[i][j]);
			}
		}

		return wb;
	}

//导入表格数据
	public static void exportFeedBack(List<Map<String, Object>> list){

		String fileName = "手机名单.xls"; //文件名
		String sheetName = "手机号名单";//sheet名

		String []title = new String[]{"公司名称","地址/手机号"};//标题

//		List<Feedback> list = appinfoService.getAllFeedbackForExcel(searchText, strType, startDate, endDate);//内容list

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		String [][]values = new String[list.size()][];

		for(int i=0;i<list.size();i++){

			values[i] = new String[title.length];
//			values[i][0] = list.get(i)+"";
			values[i][0] = (String) list.get(i).get("one");
			values[i][1] = (String) list.get(i).get("two");
		}


		HSSFWorkbook wb = getHSSFWorkbook(sheetName, title, values, null);

		//将文件存到指定位置
		try {
			FileOutputStream fout = new FileOutputStream("C:\\Users\\dahai\\Desktop\\手机号公司.xls");
			wb.write(fout);
			fout.flush();
			fout.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void setResponseHeader(HttpServletResponse response, String fileName) {
		try {
			try {
				fileName = new String(fileName.getBytes(),"ISO8859-1");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			response.setContentType("application/octet-stream;charset=ISO8859-1");
			response.setHeader("Content-Disposition", "attachment;filename="+ fileName);
			response.addHeader("Pargam", "no-cache");
			response.addHeader("Cache-Control", "no-cache");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public static boolean isNumericZidai(String str) {
		for (int i = 0; i < str.length(); i++) {
//			System.out.println(str.charAt(i));
			if (!Character.isDigit(str.charAt(i))) {
				return false;
			}
		}
		return true;
	}
}
