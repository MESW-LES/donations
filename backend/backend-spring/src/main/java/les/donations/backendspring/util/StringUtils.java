package les.donations.backendspring.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class StringUtils {

    public static String convertDateToString(Date date){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        return simpleDateFormat.format(date);
    }
}
