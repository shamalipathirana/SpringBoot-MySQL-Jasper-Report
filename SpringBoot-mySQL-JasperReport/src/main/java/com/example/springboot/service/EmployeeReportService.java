package com.example.springboot.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.springboot.entity.Employee;
import com.example.springboot.repository.ReportRepository;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;



@Service
public class EmployeeReportService {

	@Autowired
	private ReportRepository reportRepository;

	public String generateReport() {
		try {

			List<Employee> employees = reportRepository.findAll();

			String reportPath = "C:\\Users\\Hp\\Documents\\workspase1\\SpringBoot-mySQL-JasperReport\\src\\main\\resources\\reports";

			// Compile the Jasper report from .jrxml to .japser
			JasperReport jasperReport = JasperCompileManager
					.compileReport(reportPath + "\\Employee.jrxml");

			// Get your data source
			JRBeanCollectionDataSource jrBeanCollectionDataSource = new JRBeanCollectionDataSource(employees);

			// Add parameters
			Map<String, Object> parameters = new HashMap<>();

			parameters.put("createdBy", "Websparrow.org");

			// Fill the report
			JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters,
					jrBeanCollectionDataSource);

			// Export the report to a PDF file
			JasperExportManager.exportReportToPdfFile(jasperPrint, reportPath + "\\Employee.pdf");

			System.out.println("Done");

			return "Report successfully generated @path= " + reportPath;
		} catch (Exception e) {
			e.printStackTrace();
			return "Error--> check the console log";
		}
	}
}
