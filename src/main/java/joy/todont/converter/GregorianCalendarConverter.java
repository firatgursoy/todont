package joy.todont.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

/**
 * Created by GNO on 3.06.2017.
 */
@Component
public class GregorianCalendarConverter implements Converter<java.util.GregorianCalendar,String>{
    static SimpleDateFormat fmt = new SimpleDateFormat("dd-MMM-yyyy");
    @Override
    public String convert(GregorianCalendar gregorianCalendar) {
        fmt.setCalendar(gregorianCalendar);
        return fmt.format(gregorianCalendar.getTime());
    }
}
