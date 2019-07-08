package pl.sda.springdemo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class CmToInchConverter {

    private boolean isCmToInch;

    public CmToInchConverter(@Value("${application.isCmToInch}")boolean isCmToInch) {
        this.isCmToInch = isCmToInch;
    }

    public double convert(double value){
        if (isCmToInch){
            return value*2.54;
        }else{
            return value/2.54;
        }
    }
}
