package cn.lefer.tomu.base.constant;

import cn.lefer.tools.Date.LeferDate;
import org.apache.commons.lang3.time.DateUtils;

import java.util.Date;

public enum GraceDateClassification {
    JUST("刚刚"),
    YESTERDAY("昨天"),
    FEW_DAYS_AGO("几天前"),
    MANY_DAYS_AGO("许多天前")
    ;
    private final String value;
    GraceDateClassification(String value) {
        this.value=value;
    }
    public String value(){
        return value;
    }

    public static GraceDateClassification get(Date date){
        Date now = LeferDate.today();
        if(DateUtils.isSameDay(date,now)) return JUST;
        if(DateUtils.isSameDay(DateUtils.addDays(date,1),now)) return YESTERDAY;
        if(date.before(now) && (DateUtils.addDays(date,7).after(now)||DateUtils.isSameDay(DateUtils.addDays(date,7),now))) return FEW_DAYS_AGO;
        return MANY_DAYS_AGO;
    }
}
