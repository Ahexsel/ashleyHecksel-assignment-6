package com.coderscampus;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class StreamApplication {
	
	public static void main(String[] args) {
		// not need for assignment used to check paths 
		String filePathModel3 = "model3.csv";
        String filePathModelS = "modelS.csv";
        String filePathModelX = "modelX.csv";

        // not need for assignment used to check paths - Print absolute paths
        System.out.println("Absolute Path Model3: " + new File(filePathModel3).getAbsolutePath());
        System.out.println("Absolute Path ModelS: " + new File(filePathModelS).getAbsolutePath());
        System.out.println("Absolute Path ModelX: " + new File(filePathModelX).getAbsolutePath());
		
	
		List<SalesRecord> dataModel3 = readCSVFile("model3.csv");
		List<SalesRecord> dataModelS = readCSVFile("modelS.csv");
		List<SalesRecord> dataModelX = readCSVFile("modelX.csv");
		
		generateYearlyReport(dataModel3, "model3");
		generateYearlyReport(dataModelS, "modelS");
		generateYearlyReport(dataModelX, "modelX");
		
	}
	
		 private static void generateYearlyReport(List<SalesRecord> data, String modelName) {
			 Map<Integer, List<SalesRecord>> salesByYear = data.stream()
		                .filter(sale -> sale != null)
		                .collect(Collectors.groupingBy(sale -> Integer.parseInt(sale.getDate().substring(0, 4))));

		        System.out.println(modelName + " Yearly Sales Report");
		        System.out.println("---------------------------");

		        salesByYear.forEach((year, sales) -> {
		            int totalSales = sales.stream().mapToInt(SalesRecord::getUnitsSold).sum();
		            System.out.println(year + " -> " + totalSales);
		        });

		        String bestMonth = findBestMonth(salesByYear);
		        String worstMonth = findWorstMonth(salesByYear);

		        System.out.println("The best month for " + modelName + " was: " + bestMonth);
		        System.out.println("The worst month for " + modelName + " was: " + worstMonth);

		        System.out.println();
	}
 
		private static String findBestMonth(Map<Integer, List<SalesRecord>> salesByYear) {
			return salesByYear.values().stream()
	                .flatMap(List::stream)
	                .collect(Collectors.groupingBy(SalesRecord::getMonth, Collectors.summingInt(SalesRecord::getUnitsSold)))
	                .entrySet().stream()
	                .max(Map.Entry.comparingByValue())
	                .map(Map.Entry::getKey)
	                .orElse("No data");
		}
		
		private static String findWorstMonth(Map<Integer, List<SalesRecord>> salesByYear) {
			return salesByYear.values().stream()
	                .flatMap(List::stream)
	                .collect(Collectors.groupingBy(SalesRecord::getMonth, Collectors.summingInt(SalesRecord::getUnitsSold)))
	                .entrySet().stream()
	                .min(Map.Entry.comparingByValue())
	                .map(Map.Entry::getKey)
	                .orElse("No data");
		}

		private static List<SalesRecord> readCSVFile(String filePath) {
			 try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
		            return br.lines()
		                    .skip(1) // Skip header line
		                    .map(StreamApplication::parseLine)
		                    .filter(record -> record != null)
		                    .collect(Collectors.toList());
		        } catch (IOException e) {
		            // Log or handle the exception appropriately
		            e.printStackTrace();
		            return List.of(); // Return an empty list in case of an error
		        }                   
		 }
		 
			 private static SalesRecord parseLine(String line) {
			        String[] parts = line.split(",");
			        if (parts.length >= 4) {
			        	try {
			            String date = parts[0];
			            String model = parts[1];
			            int unitsSold = Integer.parseInt(parts[2]);
			            double revenue = Double.parseDouble(parts[3]);
			            return new SalesRecord(date, model, unitsSold, revenue);
			        } catch (NumberFormatException e) {
			        	e.printStackTrace();
			        	return null;
			        	}
			        } else {
			            return null;
			        }
			    }


}
