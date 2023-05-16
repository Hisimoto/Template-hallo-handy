package HalloHandy.util;

import HalloHandy.dto.excel.ExcelCell;
import HalloHandy.dto.excel.ExcelColumn;
import HalloHandy.dto.excel.ExcelColumnType;
import HalloHandy.dto.TemplateDto;
import lombok.Data;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Service
public class ExportUtil {
    public byte[] exportTemplatesInfo(List<TemplateDto> templateDtos) {

        List<ExcelColumn> columns = new ArrayList<>() {{
            add(new ExcelColumn(ExcelColumnType.String, "Email"));
            add(new ExcelColumn(ExcelColumnType.String, "First Name"));
            add(new ExcelColumn(ExcelColumnType.String, "Last Name"));

        }};
        try (ByteArrayOutputStream stream = new ByteArrayOutputStream()) {
            String sheetName = "Emails";
            Workbook workbook = createWorkbook(sheetName, columns.size() - 1);

            List<List<ExcelCell>> rows = templateDtos.stream().map(template -> getTemplateRow(template)).collect(Collectors.toList());
            int rowIndex = 0;
            generateSheetContent(workbook, sheetName, rowIndex, columns.toArray(new ExcelColumn[0]), rows);

            workbook.write(stream);
            stream.flush();
            stream.close();

            // Closing the workbook
            workbook.close();
            return stream.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return new byte[0];
    }
    public static Workbook createWorkbook(String sheetName, int colCount) {

        Workbook workbook = new XSSFWorkbook();

        // Create a Sheet
        Sheet sheet = workbook.createSheet(sheetName);

//        // Create a Font for styling header cells
//        XSSFCellStyle headerStyle = (XSSFCellStyle)workbook.createCellStyle();
//        headerStyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
//        headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
//        headerStyle.setAlignment(HorizontalAlignment.CENTER);
//
//        /*
//        headerStyle.setBorderTop(BorderStyle.THIN);
//        headerStyle.setBorderRight(BorderStyle.THIN);
//        headerStyle.setBorderBottom(BorderStyle.THIN);
//        headerStyle.setBorderLeft(BorderStyle.THIN);
//        */
//
//        XSSFFont font = ((XSSFWorkbook) workbook).createFont();
//        font.setFontName("Arial");
//        font.setColor(IndexedColors.BLACK.getIndex());
//        font.setFontHeightInPoints((short) 13);
//        font.setBold(true);
//        headerStyle.setFont(font);
//
////
////        // Create a Row
////        Row headerRow = sheet.createRow(0);
////        Cell cell = headerRow.createCell(0);
////        cell.setCellValue(sheetName);
////        cell.setCellStyle(headerStyle);
//
//        sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, colCount));

        return workbook;
    }
    public static Workbook generateSheetContent(Workbook workbook, String sheetName, int rowIndex, ExcelColumn[] columns, List<List<ExcelCell>> rows) {

        if (rows.size()==0) return workbook;

        if (columns.length != rows.get(0).size())
            throw new RuntimeException("Number of columns is not the same as row fields");

        CreationHelper createHelper = workbook.getCreationHelper();

        // Get Sheet
        Sheet sheet = workbook.getSheet(sheetName);

        // Create a Font for styling header cells
        XSSFCellStyle headerStyle = (XSSFCellStyle)workbook.createCellStyle();

        XSSFFont font = ((XSSFWorkbook) workbook).createFont();
        font.setFontName("Arial");
        font.setFontHeightInPoints((short) 10);
        font.setBold(true);
        headerStyle.setFont(font);

        // Create a Row
        Row headerRow = sheet.createRow(rowIndex);

        // Create cells
        for(int i = 0; i < columns.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(columns[i].getTitle());
            cell.setCellStyle(headerStyle);
        }

        // Create Cell Style for formatting Date
        CellStyle dateCellStyle = workbook.createCellStyle();
        dateCellStyle.setDataFormat(createHelper.createDataFormat().getFormat("yyyy-MM-dd"));

        CellStyle dateTimeCellStyle = workbook.createCellStyle();
        dateTimeCellStyle.setDataFormat(createHelper.createDataFormat().getFormat("yyyy-MM-dd HH:mm:ss z"));

        // Create rows and cells with data
        int rowNum = rowIndex + 1;
        for(List<ExcelCell> cellList: rows) {
            Row row = sheet.createRow(rowNum++);

            for (int i = 0; i < columns.length; i++) {

                Cell cell = row.createCell(i);
                if (cellList.get(i).getValue() == null) continue;
                fillCell(cell, dateCellStyle, dateTimeCellStyle, columns[i], cellList.get(i));
            }
        }

        // Resize all columns to fit the content size
        for(int i = 0; i < columns.length; i++) {
            sheet.autoSizeColumn(i);
        }

        return workbook;
    }
    private static void fillCell(Cell cell, CellStyle dateCellStyle, CellStyle dateTimeCellStyle, ExcelColumn column, ExcelCell cellValue) {
        switch (column.getDataType()) {
            case Date:
                cell.setCellStyle(dateCellStyle);
                if (cellValue.getValue() instanceof LocalDate)
                    cell.setCellValue(Date.from (((LocalDate) cellValue.getValue()).atStartOfDay().toInstant(ZoneOffset.UTC)));
                else if(cellValue.getValue() instanceof String){
                    cell.setCellValue((String) cellValue.getValue());
                }
                else
                    cell.setCellValue((Date) cellValue.getValue());
                break;
            case DateTime:
                cell.setCellStyle(dateTimeCellStyle);
                if (cellValue.getValue() != null && !cellValue.getValue().toString().isEmpty()) {
                    if (cellValue.getValue() instanceof LocalDateTime) {
                        cell.setCellValue(Date.from(((LocalDateTime) cellValue.getValue()).toInstant(ZoneOffset.UTC)));
                    } else {
                        cell.setCellValue((Date) cellValue.getValue());
                    }
                } else {
                    cell.setCellValue("");
                }
                break;
            case Integer:
                cell.setCellValue((Integer) cellValue.getValue());
                break;
            case Long:
                cell.setCellValue((Long) cellValue.getValue());
                break;
            case Float:
                cell.setCellValue((Float) cellValue.getValue());
                break;
            case Double:
                cell.setCellValue((Double) cellValue.getValue());
                break;
            case Boolean:
                cell.setCellValue((Boolean) cellValue.getValue());
                break;
            case String:
                cell.setCellValue((String) cellValue.getValue());
                break;
        }
    }
    private List<ExcelCell> getTemplateRow(TemplateDto templateDto) {
        List<ExcelCell> row = new ArrayList<>();

        row.add(new ExcelCell(templateDto.getEmail()));
        row.add(new ExcelCell(templateDto.getName()));

        row.add(new ExcelCell(templateDto.getVorname()));
        return row;
    }
}
