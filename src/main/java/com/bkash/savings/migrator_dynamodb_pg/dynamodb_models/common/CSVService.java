//package com.bkash.savings.migrator_dynamodb_pg.dynamodb_models.common;
//
//import com.bkash.savings.models.dataio.dto.ProcessCSVResponse;
//import org.apache.commons.csv.CSVRecord;
//
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.util.List;
//
//
//public interface CSVService {
//
//    void generateCSV(HttpServletResponse response, String[] headers, List<List<String>> listOfItem, String fileName)
//            throws IOException;
//
//    void generateCSV(List<?> data, List<String> headers, String tableName, HttpServletResponse response);
//
//    ProcessCSVResponse processCSV(Iterable<CSVRecord> csvRecords, long numberOfRecords, String tableName, List<String> headers);
//
//}