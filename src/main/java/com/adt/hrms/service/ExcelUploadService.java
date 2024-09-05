package com.adt.hrms.service;

import com.adt.hrms.model.CandidateFollowUp;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

public class ExcelUploadService {

    public static boolean isValidExcelFile(MultipartFile file) {
        return Objects.equals(file.getContentType(), "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
    }

    public static List<CandidateFollowUp> getCandidateDataFromExcel(InputStream inputStream) {
        List<CandidateFollowUp> candidates = new ArrayList<>();
        try (XSSFWorkbook workbook = new XSSFWorkbook(inputStream)) {
            XSSFSheet sheet = workbook.getSheetAt(0);
            Iterator<Row> rowIterator = sheet.iterator();
            int rowIndex = 0;
            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                if (rowIndex == 0) {
                    rowIndex++;
                    continue;
                }
                CandidateFollowUp candidate = parseRow(row, rowIndex);
                if (candidate != null) {
                    candidates.add(candidate);
                }
                rowIndex++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return candidates;
    }

    private static CandidateFollowUp parseRow(Row row, int rowIndex) {
        CandidateFollowUp candidate = new CandidateFollowUp();
        Iterator<Cell> cellIterator = row.iterator();
        int cellIndex = 0;
        while (cellIterator.hasNext()) {
            Cell cell = cellIterator.next();
            CellType cellType = cell.getCellType();
            try {
                switch (cellIndex) {
                    case 0:
                        if (cellType == CellType.NUMERIC) {
                            candidate.setId((int) cell.getNumericCellValue());
                        }
                        break;
                    case 1:
                        if (cellType == CellType.STRING) {
                            candidate.setCandDesignation(cell.getStringCellValue());
                        }
                        break;
                    case 2:
                        if (cellType == CellType.STRING) {
                            candidate.setCandidateName(cell.getStringCellValue());
                        }
                        break;
                    case 3:
                        if (cellType == CellType.STRING) {
                            candidate.setPhoneNumber(cell.getStringCellValue().trim());
                        } else if (cellType == CellType.NUMERIC) {
                            candidate.setPhoneNumber(String.valueOf((long) cell.getNumericCellValue()));
                        }
                        break;
                    case 4:
                        if (cellType == CellType.NUMERIC) {
                            candidate.setCurrentSalary(BigDecimal.valueOf(cell.getNumericCellValue()));
                        } else if (cellType == CellType.STRING) {
                            candidate.setCurrentSalary(new BigDecimal(cell.getStringCellValue()));
                        }
                        break;
                    case 5:
                        if (cellType == CellType.NUMERIC) {
                            candidate.setExperienceYears(BigDecimal.valueOf(cell.getNumericCellValue()));
                        } else if (cellType == CellType.STRING) {
                            candidate.setExperienceYears(new BigDecimal(cell.getStringCellValue()));
                        }
                        break;
                    case 6:
                        if (cellType == CellType.NUMERIC) {
                            candidate.setExpectedSalary(BigDecimal.valueOf(cell.getNumericCellValue()));
                        } else if (cellType == CellType.STRING) {
                            candidate.setExpectedSalary(new BigDecimal(cell.getStringCellValue()));
                        }
                        break;
                    case 7:
                        if (cellType == CellType.BOOLEAN) {
                            candidate.setOfferInHand(cell.getBooleanCellValue());
                        }
                        break;
                    case 8:
                        if (cellType == CellType.STRING) {
                            candidate.setStatus(cell.getStringCellValue());
                        }
                        break;
                    case 9:
                        if (cellType == CellType.STRING) {
                            candidate.setNoticePeriod(Integer.valueOf(cell.getStringCellValue()));
                        } else if (cellType == CellType.NUMERIC) {
                            candidate.setNoticePeriod((int) cell.getNumericCellValue());
                        }
                        break;
                    case 10:
                        if (cellType == CellType.STRING) {
                            candidate.setTechnicalSkills(cell.getStringCellValue());
                        }
                        break;
                    case 11:
                        if (cellType == CellType.STRING) {
                            candidate.setEmailAddress(cell.getStringCellValue());
                        }
                        break;
                    case 12:
                        if (cellType == CellType.STRING) {
                            candidate.setCurrentLocation(cell.getStringCellValue());
                        }
                        break;
                    case 13:
                        if (cellType == CellType.STRING) {
                            candidate.setWorkPreference(cell.getStringCellValue());
                        }
                        break;
                    case 14:
                        if (cellType == CellType.STRING) {
                            candidate.setReasonForChange(cell.getStringCellValue());
                        }
                        break;
                    case 15:
                        if (cellType == CellType.STRING) {
                            candidate.setNativeLocation(cell.getStringCellValue());
                        }
                        break;
                    case 16:
                        if (cellType == CellType.NUMERIC) {
                            LocalDate date = cell.getLocalDateTimeCellValue().toLocalDate();
                            candidate.setUpdatedDate(date);
                        }
                        break;
                    case 17:
                        if (cellType == CellType.STRING) {
                            candidate.setUpdatedBy(cell.getStringCellValue());
                        }
                        break;
                    default:
                        break;
                }
            } catch (Exception e) {
                System.err.println("Error processing cell at row " + rowIndex + ", column " + cellIndex + ": " + e.getMessage());
            }
            cellIndex++;
        }
        return candidate;
    }
}
