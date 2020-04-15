package wasteless.server.export.report;

import wasteless.server.presentation.dto.WasteCalculatorDTO;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class TextReport implements Report {
    @Override
    public void dumpReport(WasteCalculatorDTO wasteCalculatorDTO) {

        try {
            Files.createDirectories(Paths.get("reports"));
            Path path = Paths.get("reports","text-report.txt");
            new File(path.toUri());

            writeToFile(path, "This is a wasteless text report \n");
            writeToFile(path, "The list name is: " + wasteCalculatorDTO.getGroceryListName() + " \n");
            writeToFile(path, "The number of calories consumed are: " + wasteCalculatorDTO.getWasteResult());
            writeToFile(path, "The burndown rate goal you chose is: " + wasteCalculatorDTO.getBurndownRateGoal()+"\n");
            writeToFile(path, "The waste level is: " + (wasteCalculatorDTO.getWasteResult() - wasteCalculatorDTO.getBurndownRateGoal()) + "\n");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void writeToFile(Path path, String textToWrite) {
        try {
            Files.write(path, textToWrite.getBytes(), StandardOpenOption.APPEND);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
