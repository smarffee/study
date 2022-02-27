package mianshi;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * @author slj
 * @date 2020/10/28/9:48
 */
public class Hello {
    public static void main(String[] args) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String startDate1 = "2020-10-27";
        String amStartTime1 = "09:00";
        String amEndTime1 = "11:00";
        String pmStartTime1 = "14:00";
        String pmEndTime1 = "17:00";

        LocalDate startDate = LocalDate.parse(startDate1);
        // 开始面试时间
        LocalDateTime startDateTime = null;
        LocalTime amStartTime = null;
        LocalTime amEndTime = null;
        LocalTime pmStartTime = null;
        LocalTime pmEndTime = null;
        if (amStartTime1 != null && !amStartTime1.isEmpty() && amEndTime1 != null && !amEndTime1.isEmpty()) {
            //上午开始面试时间戳
            amStartTime = LocalTime.parse(amStartTime1);
            //上午结束面试时间戳
            amEndTime = LocalTime.parse(amEndTime1);
        }
        if (pmStartTime1 != null && !pmStartTime1.isEmpty() && pmEndTime1 != null && !pmEndTime1.isEmpty()) {
            //下午开始面试时间戳
            pmStartTime = LocalTime.parse(pmStartTime1);
            //下午结束面试时间戳
            pmEndTime = LocalTime.parse(pmEndTime1);
        }
        if (amStartTime != null) {
            startDateTime = LocalDateTime.of(startDate, amStartTime);
        } else {
            startDateTime = LocalDateTime.of(startDate, pmStartTime);
        }

        //1. 面试的开始时间是上午开始时间
        LocalDateTime interviewStartTime = startDateTime;

        // 面试时长
        int interviewTime = 35;

        for (int i = 0; i < 50; i++) {
            interviewStartTime = calcInterviewStartTime(amStartTime, amEndTime,
                    pmStartTime, pmEndTime, interviewStartTime, interviewTime);
            Date s1 = Date.from(interviewStartTime.atZone(ZoneId.systemDefault()).toInstant());
            interviewStartTime = interviewStartTime.plusMinutes(interviewTime);
            Date s2 = Date.from(interviewStartTime.minusSeconds(1).atZone(ZoneId.systemDefault()).toInstant());
            System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(s1) + "  " + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(s2));
        }
        System.out.println("哈哈哈");
    }

    private static LocalDateTime calcInterviewStartTime(LocalTime amStartTime, LocalTime amEndTime,
                                                        LocalTime pmStartTime, LocalTime pmEndTime,
                                                        LocalDateTime interviewStartTime,
                                                        int interviewTime) {

        //当前的面试时间段
        LocalDateTime amStartDateTime = amStartTime == null ? null : LocalDateTime.of(interviewStartTime.toLocalDate(), amStartTime);
        LocalDateTime amEndDateTime = amEndTime == null ? null : LocalDateTime.of(interviewStartTime.toLocalDate(), amEndTime);
        LocalDateTime pmStartDateTime = pmStartTime == null ? null : LocalDateTime.of(interviewStartTime.toLocalDate(), pmStartTime);
        LocalDateTime pmEndDateTime = pmEndTime == null ? null : LocalDateTime.of(interviewStartTime.toLocalDate(), pmEndTime);

        //当前的面试结束时间
        LocalDateTime nextInterviewEndTime = interviewStartTime.plusMinutes(interviewTime);

        // 上午有面试
        if (amEndDateTime != null) {
            //1. 如果面试结束时间在上午结束之前
            if (nextInterviewEndTime.isBefore(amEndDateTime) || nextInterviewEndTime.isEqual(amEndDateTime)) {
                return interviewStartTime;
            }
            if(nextInterviewEndTime.isAfter(amEndDateTime)){
                // 下午有面试
                if(pmEndDateTime != null){
                    // 面试结束时间在下午开始时间之前
                    // 面试结束时间在下午开始时间到结束时间中间
                    if(nextInterviewEndTime.isBefore(pmStartDateTime) ||
                            (interviewStartTime.isBefore(amEndDateTime) && nextInterviewEndTime.isAfter(pmStartDateTime) && nextInterviewEndTime.isBefore(pmEndDateTime))){
                        interviewStartTime = pmStartDateTime;
                        nextInterviewEndTime = interviewStartTime.plusMinutes(interviewTime);
                    }
                    // 面试结束时间在下午结束时间之前
                    if (nextInterviewEndTime.isBefore(pmEndDateTime) || nextInterviewEndTime.isEqual(pmEndDateTime)) {
                        return interviewStartTime;
                    }
                    // 面试结束时间在下午结束时间之后
                    if (nextInterviewEndTime.isAfter(pmEndDateTime)) {
                        amStartDateTime = amStartDateTime.plusDays(1L);
                        interviewStartTime = amStartDateTime;

                        return calcInterviewStartTime(amStartTime, amEndTime, pmStartTime, pmEndTime,
                                interviewStartTime, interviewTime);
                    }
                } else { // 下午没面试
                    amStartDateTime = amStartDateTime.plusDays(1L);
                    interviewStartTime = amStartDateTime;

                    return calcInterviewStartTime(amStartTime, amEndTime, pmStartTime, pmEndTime,
                            interviewStartTime, interviewTime);
                }
            }
        }
        // 下午有面试
        if(pmEndDateTime != null){
            if (nextInterviewEndTime.isBefore(pmEndDateTime) || nextInterviewEndTime.isEqual(pmEndDateTime)) {
                return interviewStartTime;
            }
            if (nextInterviewEndTime.isAfter(pmEndDateTime)) {
                // 上午有面试
                if(amEndDateTime != null){
                    amStartDateTime = amStartDateTime.plusDays(1L);
                    interviewStartTime = amStartDateTime;
                } else { // 上午没面试
                    pmStartDateTime = pmStartDateTime.plusDays(1L);
                    interviewStartTime = pmStartDateTime;
                }
                return calcInterviewStartTime(amStartTime, amEndTime, pmStartTime, pmEndTime,
                        interviewStartTime, interviewTime);
            }
        }

        return calcInterviewStartTime(amStartTime, amEndTime, pmStartTime, pmEndTime,
                interviewStartTime, interviewTime);
    }
}
