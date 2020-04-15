package wasteless.server.export.factory;

import wasteless.server.export.report.JsonReport;
import wasteless.server.export.report.Report;
import wasteless.server.export.report.ReportType;
import wasteless.server.export.report.TextReport;

import static wasteless.server.export.report.ReportType.JSON_REPORT;

public class ReportFactory implements AbstractFactory<Report> {

    @Override
    public Report create(ReportType reportType) {
        if (reportType == JSON_REPORT){
            return new JsonReport();
        }
        else{
            return new TextReport();
        }
    }
}
