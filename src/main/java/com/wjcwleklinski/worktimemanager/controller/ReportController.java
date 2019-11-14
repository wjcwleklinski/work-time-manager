package com.wjcwleklinski.worktimemanager.controller;

import com.wjcwleklinski.worktimemanager.service.EmployeeProjectService;
import com.wjcwleklinski.worktimemanager.service.EmployeeService;
import com.wjcwleklinski.worktimemanager.service.ProjectService;
import com.wjcwleklinski.worktimemanager.service.StatisticsService;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.HtmlExporter;
import net.sf.jasperreports.engine.util.JRSaver;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleHtmlExporterOutput;
import net.sf.jasperreports.export.SimpleHtmlReportConfiguration;
import net.sf.jasperreports.view.JasperViewer;
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
    private EmployeeService employeeService;

    @Autowired
    private ProjectService projectService;

    @Autowired
    private EmployeeProjectService employeeProjectService;

    @Autowired
    private StatisticsService statisticsService;

    @GetMapping
    public String index(ModelMap modelMap) {
        return "report/index";
    }

    @GetMapping(value = "employee")
    public void employeeReport(HttpServletResponse response) throws Exception {
        response.setContentType("text/html");
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(employeeService.report());
        InputStream inputStream = this.getClass().getResourceAsStream("/reports/employee_report.jrxml");
        JasperReport report = JasperCompileManager.compileReport(inputStream);

        //JRBeanCollectionDataSource dataSource2 = new JRBeanCollectionDataSource(employeeProjectService.report()); //
        InputStream is2 = this.getClass().getResourceAsStream("/reports/ep_report.jrxml");
        JasperReport epReport = JasperCompileManager.compileReport(is2);
        JRSaver.saveObject(epReport, "epReport.jasper");

        JasperPrint print = JasperFillManager.fillReport(report, null, dataSource);
        //JasperPrint subreportPrinter = JasperFillManager.fillReport(epReport, null, dataSource2); //
        HtmlExporter exporter = new HtmlExporter(DefaultJasperReportsContext.getInstance());
        exporter.setExporterInput(new SimpleExporterInput(print));
        exporter.setExporterOutput(new SimpleHtmlExporterOutput(response.getWriter()));
        exporter.exportReport();
    }


    @GetMapping(value = "project")
    public void projectReport(HttpServletResponse response) throws Exception {
        response.setContentType("text/html");
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(projectService.report());
        InputStream inputStream = this.getClass().getResourceAsStream("/reports/project_report.jrxml");
        JasperReport report = JasperCompileManager.compileReport(inputStream);

        //JRSaver.saveObject(report, "projectReport.jasper");

        JasperPrint print = JasperFillManager.fillReport(report, null, dataSource);
        HtmlExporter exporter = new HtmlExporter(DefaultJasperReportsContext.getInstance());
        exporter.setExporterInput(new SimpleExporterInput(print));
        exporter.setExporterOutput(new SimpleHtmlExporterOutput(response.getWriter()));
        exporter.exportReport();
    }

    @RequestMapping(value = "statistics")
    public void statistics(HttpServletResponse response) throws Exception {
        response.setContentType("text/html");
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(statisticsService.report());
        InputStream inputStream = this.getClass().getResourceAsStream("/reports/statistics.jrxml");
        JasperReport report = JasperCompileManager.compileReport(inputStream);


        JasperPrint print = JasperFillManager.fillReport(report, null, dataSource);

//        JasperViewer.viewReport(print);

        HtmlExporter exporter = new HtmlExporter(DefaultJasperReportsContext.getInstance());
        exporter.setExporterInput(new SimpleExporterInput(print));
        exporter.setExporterOutput(new SimpleHtmlExporterOutput(response.getWriter()));

        SimpleHtmlReportConfiguration configuration = new SimpleHtmlReportConfiguration();
        configuration.setEmbedImage(true);
        exporter.setConfiguration(configuration);

        exporter.exportReport();


    }

}
