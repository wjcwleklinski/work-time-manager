package com.wjcwleklinski.worktimemanager.controller;

import com.wjcwleklinski.worktimemanager.service.EmployeeService;
import com.wjcwleklinski.worktimemanager.service.ProjectService;
import com.wjcwleklinski.worktimemanager.service.StatisticsService;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.HtmlExporter;
import net.sf.jasperreports.export.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("report")
public class ReportController {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private ProjectService projectService;

    @Autowired
    private StatisticsService statisticsService;

    @GetMapping
    public String index(ModelMap modelMap) {
        return "report/index";
    }

    @GetMapping(value = "employee")
    public void employeeReport(HttpServletResponse response) throws Exception {
        JasperPrint print = prepareReport(response, employeeService.report(), "/reports/employee_report.jrxml");
        HtmlExporter exporter = prepareExporter(new SimpleExporterInput(print),
                new SimpleHtmlExporterOutput(response.getWriter()),
                new SimpleHtmlReportConfiguration());
        exporter.exportReport();
    }

    @GetMapping(value = "project")
    public void projectReport(HttpServletResponse response) throws Exception{

        JasperPrint print = prepareReport(response, projectService.report(), "/reports/project_report.jrxml");
        HtmlExporter exporter = prepareExporter(new SimpleExporterInput(print),
                new SimpleHtmlExporterOutput(response.getWriter()),
                new SimpleHtmlReportConfiguration());
        exporter.exportReport();
    }

    @RequestMapping(value = "statistics")
    public void statistics(HttpServletResponse response) throws Exception {
        JasperPrint print = prepareReport(response, statisticsService.report(),
                "/reports/statistics.jrxml");

        SimpleHtmlReportConfiguration configuration = new SimpleHtmlReportConfiguration();
        configuration.setEmbedImage(true);

        HtmlExporter exporter = prepareExporter(new SimpleExporterInput(print),
                new SimpleHtmlExporterOutput(response.getWriter()),
                configuration);

        exporter.exportReport();

    }

    private JasperPrint prepareReport(HttpServletResponse response, List<Map<String, Object>> collectionDataSource,
                               String resourceStreamPath) throws Exception{
        response.setContentType("text/html");
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(collectionDataSource);
        InputStream inputStream = this.getClass().getResourceAsStream(resourceStreamPath);
        JasperReport report = JasperCompileManager.compileReport(inputStream);

        return JasperFillManager.fillReport(report, null, dataSource);
    }

    private HtmlExporter prepareExporter(ExporterInput input, HtmlExporterOutput output,
                                         HtmlReportConfiguration configuration) {

        HtmlExporter exporter = new HtmlExporter(DefaultJasperReportsContext.getInstance());
        exporter.setExporterInput(input);
        exporter.setExporterOutput(output);
        exporter.setConfiguration(configuration);

        return exporter;
    }
}
