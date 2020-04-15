package wasteless.server.export.report;

import wasteless.server.presentation.dto.WasteCalculatorDTO;

public interface Report {
    void dumpReport(WasteCalculatorDTO wasteCalculatorDTO);
}
