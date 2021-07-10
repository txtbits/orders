package com.txtbits.orders.util;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import com.txtbits.orders.model.Order;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.csv.QuoteMode;
import org.springframework.web.multipart.MultipartFile;


public class CSVHelper {
    public static String TYPE = "text/csv";

    public static boolean hasCSVFormat(MultipartFile file) {
        return TYPE.equals(file.getContentType())
                || Objects.requireNonNull(file.getContentType()).equals("application/vnd.ms-excel");
    }

    public static List<Order> csvToOrders(InputStream is) {
        try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
             CSVParser csvParser = new CSVParser(fileReader,
                     CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim())) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/M/d HH:mm:ss");
            List<Order> ordersList = new ArrayList<>();

            Iterable<CSVRecord> csvRecords = csvParser.getRecords();

            for (CSVRecord csvRecord : csvRecords) {
                Order order = new Order();
                order.setOrderId(Long.parseLong(csvRecord.get("Order ID")));
                order.setRegion(csvRecord.get("Region"));
                order.setCountry(csvRecord.get("Country"));
                order.setItemType(csvRecord.get("Item Type"));
                order.setSalesChannel(csvRecord.get("Sales Channel"));
                order.setOrderPriority(csvRecord.get("Order Priority"));
                order.setOrderPriority(csvRecord.get("Order Priority"));
                try {
                    order.setOrderDate(new SimpleDateFormat("dd/MM/yyyy").parse(csvRecord.get("Order Date")));
                    order.setShipDate(new SimpleDateFormat("dd/MM/yyyy").parse(csvRecord.get("Ship Date")));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                order.setUnitsSold(Integer.parseInt(csvRecord.get("Units Sold")));
                order.setUnitPrice(new BigDecimal(csvRecord.get("Unit Price")));
                order.setTotalRevenue(new BigDecimal(csvRecord.get("Total Revenue")));
                order.setTotalCost(new BigDecimal(csvRecord.get("Total Cost")));
                order.setTotalProfit(new BigDecimal(csvRecord.get("Total Profit")));
                ordersList.add(order);
            }

            return ordersList;
        } catch (IOException e) {
            throw new RuntimeException("fail to parse CSV file: " + e.getMessage());
        }
    }

    public static ByteArrayInputStream ordersToCSV(List<Order> ordersList) {
        final CSVFormat format = CSVFormat.DEFAULT.withQuoteMode(QuoteMode.MINIMAL);

        try (ByteArrayOutputStream out = new ByteArrayOutputStream();
             CSVPrinter csvPrinter = new CSVPrinter(new PrintWriter(out), format)) {
            for (Order order : ordersList) {
                List<String> data = Arrays.asList(
                        String.valueOf(order.getOrderId()),
                        order.getRegion(),
                        String.valueOf(order.getTotalProfit())
                );

                csvPrinter.printRecord(data);
            }

            csvPrinter.flush();
            return new ByteArrayInputStream(out.toByteArray());
        } catch (IOException e) {
            throw new RuntimeException("fail to import data to CSV file: " + e.getMessage());
        }
    }
}
