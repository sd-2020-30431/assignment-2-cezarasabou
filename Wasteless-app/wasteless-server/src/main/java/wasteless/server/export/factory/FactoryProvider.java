package wasteless.server.export.factory;

import org.springframework.stereotype.Component;

@Component
public class FactoryProvider {

    public AbstractFactory getFactory(FactoryType factoryType){

        if(factoryType == FactoryType.REPORT_FACTORY) {
            return new ReportFactory();
        }

        return null;
    }
}
