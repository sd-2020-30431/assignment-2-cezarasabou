package wasteless.server.business;

import org.springframework.stereotype.Service;
import wasteless.server.export.factory.FactoryProvider;
import wasteless.server.export.factory.FactoryType;
import wasteless.server.export.report.Report;
import wasteless.server.export.report.ReportType;
import wasteless.server.presentation.dto.WasteCalculatorDTO;

@Service
public class ExportService {

    private FactoryProvider factoryProvider;

    public ExportService(FactoryProvider factoryProvider) {
        this.factoryProvider = factoryProvider;
    }

    public void exportTextWasteReport(WasteCalculatorDTO wasteCalculatorDTO) {
        Report textReport = getReportGenerator(ReportType.TEXT_REPORT);
        textReport.dumpReport(wasteCalculatorDTO);
    }

    public void exportJsonWasteReport(WasteCalculatorDTO wasteCalculatorDTO) {
        Report jsonReport = getReportGenerator(ReportType.JSON_REPORT);
        jsonReport.dumpReport(wasteCalculatorDTO);
    }

    private Report getReportGenerator(ReportType reportType) {
        return (Report) factoryProvider.getFactory(FactoryType.REPORT_FACTORY).create(reportType);
    }
}
