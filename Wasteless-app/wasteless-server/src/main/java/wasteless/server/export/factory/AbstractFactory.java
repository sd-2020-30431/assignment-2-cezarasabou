package wasteless.server.export.factory;

import wasteless.server.export.report.ReportType;

public interface AbstractFactory<T> {
    T create(ReportType reportType);
}
