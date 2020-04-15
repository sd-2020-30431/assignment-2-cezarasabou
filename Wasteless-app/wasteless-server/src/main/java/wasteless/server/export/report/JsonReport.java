package wasteless.server.export.report;

import com.google.gson.Gson;
import wasteless.server.presentation.dto.WasteCalculatorDTO;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class JsonReport implements Report {

    @Override
    public void dumpReport(WasteCalculatorDTO wasteCalculatorDTO) {
        Gson gson = new Gson();
        try {
            Files.createDirectories(Paths.get("reports"));
            Path path = Paths.get("reports","json-report.json");
            FileWriter fileWriter = new FileWriter(path.toFile());
            gson.toJson(wasteCalculatorDTO, fileWriter);
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
