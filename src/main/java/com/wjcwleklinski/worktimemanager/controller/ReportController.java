package com.wjcwleklinski.worktimemanager.controller;

import com.wjcwleklinski.worktimemanager.service.EmployeeService;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.HtmlExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleHtmlExporterOutput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;

@Controller
@RequestMapping("report")
public class ReportController {

    @Autowired
    EmployeeService employeeService;

    @GetMapping
    public String index(ModelMap modelMap) {
        return "report/index";
    }

    @GetMapping(value = "employee")
    public void employeeReport(HttpServletResponse response) throws Exception {
        response.setContentType("text/html");
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(employeeService.report());
        InputStream inputStream = this.getClass().getResourceAsStream("/reports/report.jrxml");
        JasperReport report = JasperCompileManager.compileReport(inputStream);
        JasperPrint print = JasperFillManager.fillReport(report, null, dataSource);
        HtmlExporter exporter = new HtmlExporter(DefaultJasperReportsContext.getInstance());
        exporter.setExporterInput(new SimpleExporterInput(print));
        exporter.setExporterOutput(new SimpleHtmlExporterOutput(response.getWriter()));
        exporter.exportReport();
    }
}
