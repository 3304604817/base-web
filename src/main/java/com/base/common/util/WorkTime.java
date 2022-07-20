package com.base.common.util;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * WorkTime
 * 计算工作时常
 *
 * @author mqpearth
 * @date 2021.8.7 20:41
 */
public class WorkTime {

    // 工作日上午开始工作时间
    private LocalTime amStart = LocalTime.of(8, 30);
    // 工作日上午结束工作时间
    private LocalTime amEnd = LocalTime.of(12, 0);
    // 工作日下午开始工作时间
    private LocalTime pmStart = LocalTime.of(13, 0);
    // 工作日下午结束工作时间
    private LocalTime pmEnd = LocalTime.of(17, 30);

    /**
     * 每周工作日
     * 0：周日
     * 1：周一
     * 2：周二
     * 3：周三
     * 4：周四
     * 5：周五
     * 6：周六
     */
    private int[] weekdays = new int[]{1, 2, 3, 4, 5};

    // 加班日定义，可与 weekdays 工作日重合（当一个日期既是加班日期也是休假日期，默认当天是休假）
    private List<LocalDate> workOverDates;

    // 法定节假日，可与 weekdays 工作日重合（当一个日期既是加班日期也是休假日期，默认当天是休假）
    private List<LocalDate> legalHolidays;

    public WorkTime() {
    }

    public WorkTime(List<LocalDate> workOverDates, List<LocalDate> legalHolidays) {
        this.workOverDates = Objects.nonNull(workOverDates) ? workOverDates : new ArrayList<>(1);
        this.legalHolidays = Objects.nonNull(legalHolidays) ? legalHolidays : new ArrayList<>(1);
    }

    public WorkTime(LocalTime amStart, LocalTime amEnd, LocalTime pmStart, LocalTime pmEnd,
                    List<LocalDate> workOverDates, List<LocalDate> legalHolidays) {
        this.amStart = Objects.nonNull(amStart) ? amStart : this.amStart;
        this.amEnd = Objects.nonNull(amEnd) ? amEnd : this.amEnd;
        this.pmStart = Objects.nonNull(pmStart) ? pmStart : this.pmStart;
        this.pmEnd = Objects.nonNull(pmEnd) ? pmEnd : this.pmEnd;
        this.workOverDates = workOverDates;
        this.legalHolidays = legalHolidays;
    }

    public WorkTime(LocalTime amStart, LocalTime amEnd, LocalTime pmStart, LocalTime pmEnd,
                    int[] weekdays,
                    List<LocalDate> workOverDates, List<LocalDate> legalHolidays) {
        this.amStart = Objects.nonNull(amStart) ? amStart : this.amStart;
        this.amEnd = Objects.nonNull(amEnd) ? amEnd : this.amEnd;
        this.pmStart = Objects.nonNull(pmStart) ? pmStart : this.pmStart;
        this.pmEnd = Objects.nonNull(pmEnd) ? pmEnd : this.pmEnd;
        this.weekdays = Objects.nonNull(weekdays) ? weekdays : this.weekdays;
        this.workOverDates = workOverDates;
        this.legalHolidays = legalHolidays;
    }

    public long explainWorkTime(LocalDateTime wkStartDateTime, LocalDateTime wkEndDateTime){
        LocalDate wkStartDate = wkStartDateTime.toLocalDate();
        LocalDate wkEndDate = wkEndDateTime.toLocalDate();

        // 当前正在计算的日期
        LocalDate calculatingDate = wkStartDate;

        // 工作时间总和(单位分钟)
        long workTimeSum = 0;
        while (calculatingDate.isBefore(wkEndDate) || calculatingDate.isEqual(wkEndDate)){
            // 计算 calculatingDate 是周几
            int nowWeekDay = (calculatingDate.getDayOfWeek().getValue()) % 7;

            // calculatingDate 是法定节假日则进入下一循环
            if(legalHolidays.contains(calculatingDate)) {
                calculatingDate = calculatingDate.plusDays(1);
                continue;
            }

            // 是工作日计算时间
            if(this.isContains(weekdays, nowWeekDay) || workOverDates.contains(calculatingDate)){
                // 情况1：当前计算日期是开始日期 且 结束日期大于开始日期(开始日期和结束日期不能是同一天)
                if(calculatingDate.isEqual(wkStartDate) && wkStartDate.isBefore(wkEndDate)){
                    // 开始时间 <= 上午上班时间
                    if(wkStartDateTime.toLocalTime().isBefore(amStart) || wkStartDateTime.toLocalTime().equals(amStart)){
                        Duration durationAm = Duration.between(amStart, amEnd);
                        Duration durationPm = Duration.between(pmStart, pmEnd);
                        workTimeSum = workTimeSum + durationAm.toMinutes() + durationPm.toMinutes();
                    }
                    // 上午上班时间 < 开始时间 <= 上午下班时间
                    else if(amStart.isBefore(wkStartDateTime.toLocalTime()) && (wkStartDateTime.toLocalTime().isBefore(amEnd) || wkStartDateTime.toLocalTime().equals(amEnd))){
                        Duration durationAm = Duration.between(wkStartDateTime.toLocalTime(), amEnd);
                        Duration durationPm = Duration.between(pmStart, pmEnd);
                        workTimeSum = workTimeSum + durationAm.toMinutes() + durationPm.toMinutes();
                    }
                    // 上午下班时间 < 开始时间 <= 下午上班时间
                    else if(amEnd.isBefore(wkStartDateTime.toLocalTime()) && (wkStartDateTime.toLocalTime().isBefore(pmStart) || wkStartDateTime.toLocalTime().equals(pmStart))){
                        Duration durationPm = Duration.between(pmStart, pmEnd);
                        workTimeSum = workTimeSum + durationPm.toMinutes();
                    }
                    // 下午上班时间 < 开始时间 <= 下午下班时间
                    else if(pmStart.isBefore(wkStartDateTime.toLocalTime()) && (wkStartDateTime.toLocalTime().isBefore(pmEnd) || wkStartDateTime.toLocalTime().equals(pmEnd))){
                        Duration durationPm = Duration.between(wkStartDateTime.toLocalTime(), pmEnd);
                        workTimeSum = workTimeSum + durationPm.toMinutes();
                    }
                    else{}
                }

                // 情况2：当前计算日期是结束日期 且 结束日期大于开始日期(开始日期和结束日期不能是同一天)
                if(calculatingDate.isEqual(wkEndDate) && wkStartDate.isBefore(wkEndDate)){
                    // 上午上班时间 < 结束时间 <= 上午下班时间
                    if(amStart.isBefore(wkEndDateTime.toLocalTime()) && (wkEndDateTime.toLocalTime().isBefore(amEnd) || wkEndDateTime.toLocalTime().equals(amEnd))){
                        Duration durationAm = Duration.between(amStart, wkEndDateTime.toLocalTime());
                        workTimeSum = workTimeSum + durationAm.toMinutes();
                    }
                    // 上午下班时间 < 结束时间 <= 下午上班时间
                    else if(amEnd.isBefore(wkEndDateTime.toLocalTime()) && (wkEndDateTime.toLocalTime().isBefore(pmStart) || wkEndDateTime.toLocalTime().equals(pmStart))){
                        Duration durationAm = Duration.between(amStart, amEnd);
                        workTimeSum = workTimeSum + durationAm.toMinutes();
                    }
                    // 下午上班时间 < 结束时间 <= 下午下班时间
                    else if(pmStart.isBefore(wkEndDateTime.toLocalTime()) && (wkEndDateTime.toLocalTime().isBefore(pmEnd) || wkEndDateTime.toLocalTime().equals(pmEnd))){
                        Duration durationAm = Duration.between(amStart, amEnd);
                        Duration durationPm = Duration.between(pmStart, wkEndDateTime.toLocalTime());
                        workTimeSum = workTimeSum + durationAm.toMinutes() + durationPm.toMinutes();
                    }
                    // 下午下班时间 < 结束时间
                    else if(pmEnd.isBefore(wkEndDateTime.toLocalTime())){
                        Duration durationAm = Duration.between(amStart, amEnd);
                        Duration durationPm = Duration.between(pmStart, pmEnd);
                        workTimeSum = workTimeSum + durationAm.toMinutes() + durationPm.toMinutes();
                    }
                    else{}
                }

                // 情况3：当前计算日期既不是开始日期也不是结束日期 且 结束日期大于开始日期(开始日期和结束日期不能是同一天)
                if(!calculatingDate.isEqual(wkStartDate) && !calculatingDate.isEqual(wkEndDate) && wkStartDate.isBefore(wkEndDate)){
                    Duration durationAm = Duration.between(amStart, amEnd);
                    Duration durationPm = Duration.between(pmStart, pmEnd);
                    workTimeSum = workTimeSum + durationAm.toMinutes() + durationPm.toMinutes();
                }

                // 情况4：当前计算时间是开始日期(结束日期) 且 结束日期和开始日期是同一天
                if(calculatingDate.isEqual(wkStartDate) && calculatingDate.isEqual(wkEndDate) && wkStartDate.isEqual(wkEndDate)){
                    // 开始时间 <= 上午上班时间 < 结束时间 <= 上午下班时间
                    if((wkStartDateTime.toLocalTime().isBefore(amStart) || wkStartDateTime.toLocalTime().equals(amStart)) && amStart.isBefore(wkEndDateTime.toLocalTime()) && (wkEndDateTime.toLocalTime().isBefore(amEnd) || wkEndDateTime.toLocalTime().equals(amEnd))){
                        Duration durationAm = Duration.between(amStart, wkEndDateTime.toLocalTime());
                        workTimeSum = workTimeSum + durationAm.toMinutes() + durationAm.toMinutes();
                    }
                    // 开始时间 <= 上午上班时间 < 上午下班时间 < 结束时间 <= 下午上班时间
                    else if((wkStartDateTime.toLocalTime().isBefore(amStart) || wkStartDateTime.toLocalTime().equals(amStart)) && amEnd.isBefore(wkEndDateTime.toLocalTime()) && (wkEndDateTime.toLocalTime().isBefore(pmStart) || wkEndDateTime.toLocalTime().equals(pmStart))){
                        Duration durationAm = Duration.between(amStart, amEnd);
                        workTimeSum = workTimeSum + durationAm.toMinutes();
                    }
                    // 开始时间 <= 上午上班时间 < 上午下班时间 < 下午上班时间 < 结束时间 <= 下午下班时间
                    else if((wkStartDateTime.toLocalTime().isBefore(amStart) || wkStartDateTime.toLocalTime().equals(amStart)) && pmStart.isBefore(wkEndDateTime.toLocalTime()) && (wkEndDateTime.toLocalTime().isBefore(pmEnd) || wkEndDateTime.toLocalTime().equals(pmEnd))){
                        Duration durationAm = Duration.between(amStart, amEnd);
                        Duration durationPm = Duration.between(pmStart, wkEndDateTime.toLocalTime());
                        workTimeSum = workTimeSum + durationAm.toMinutes() + durationPm.toMinutes();
                    }
                    // 开始时间 <= 上午上班时间 < 上午下班时间 < 下午上班时间 < 下午下班时间 < 结束时间
                    else if((wkStartDateTime.toLocalTime().isBefore(amStart) || wkStartDateTime.toLocalTime().equals(amStart)) && pmEnd.isBefore(wkEndDateTime.toLocalTime())){
                        Duration durationAm = Duration.between(amStart, amEnd);
                        Duration durationPm = Duration.between(pmStart, pmEnd);
                        workTimeSum = workTimeSum + durationAm.toMinutes() + durationPm.toMinutes();
                    }

                    // 上午上班时间 < 开始时间 <= 结束时间 <= 上午下班时间
                    else if(amStart.isBefore(wkStartDateTime.toLocalTime()) && (wkStartDateTime.toLocalTime().isBefore(wkEndDateTime.toLocalTime()) || wkStartDateTime.toLocalTime().equals(wkEndDateTime.toLocalTime())) && (wkEndDateTime.toLocalTime().isBefore(amEnd) || wkEndDateTime.toLocalTime().equals(amEnd))){
                        Duration durationAm = Duration.between(wkStartDateTime.toLocalTime(), wkEndDateTime.toLocalTime());
                        workTimeSum = workTimeSum + durationAm.toMinutes();
                    }
                    // 上午上班时间 < 开始时间 <= 上午下班时间 < 结束时间 <= 下午上班时间
                    else if(amStart.isBefore(wkStartDateTime.toLocalTime()) && (wkStartDateTime.toLocalTime().isBefore(amEnd) || wkStartDateTime.toLocalTime().equals(amEnd)) && amEnd.isBefore(wkEndDateTime.toLocalTime()) && (wkEndDateTime.toLocalTime().isBefore(pmStart) || wkEndDateTime.toLocalTime().equals(pmStart))){
                        Duration durationAm = Duration.between(wkStartDateTime.toLocalTime(), amEnd);
                        workTimeSum = workTimeSum + durationAm.toMinutes();
                    }
                    // 上午上班时间 < 开始时间 <= 上午下班时间 < 下午上班时间 < 结束时间 <= 下午下班时间
                    if(amStart.isBefore(wkStartDateTime.toLocalTime()) && (wkStartDateTime.toLocalTime().isBefore(amEnd) || wkStartDateTime.toLocalTime().equals(amEnd)) && pmStart.isBefore(wkEndDateTime.toLocalTime()) && (wkEndDateTime.toLocalTime().isBefore(pmEnd) || wkEndDateTime.toLocalTime().equals(pmEnd))){
                        Duration durationAm = Duration.between(wkStartDateTime.toLocalTime(), amEnd);
                        Duration durationPm = Duration.between(pmStart, wkEndDateTime.toLocalTime());
                        workTimeSum = workTimeSum + durationAm.toMinutes() + durationPm.toMinutes();
                    }
                    // 上午上班时间 < 开始时间 <= 上午下班时间 < 下午上班时间 < 下午下班时间 < 结束时间
                    else if(amStart.isBefore(wkStartDateTime.toLocalTime()) && (wkStartDateTime.toLocalTime().isBefore(amEnd) || wkStartDateTime.toLocalTime().equals(amEnd)) && pmEnd.isBefore(wkEndDateTime.toLocalTime())){
                        Duration durationAm = Duration.between(wkStartDateTime.toLocalTime(), amEnd);
                        Duration durationPm = Duration.between(pmStart, pmEnd);
                        workTimeSum = workTimeSum + durationAm.toMinutes() + durationPm.toMinutes();
                    }

                    // 上午下班时间 < 开始时间 <= 下午上班时间 < 结束时间 <= 下午下班时间
                    else if(amEnd.isBefore(wkStartDateTime.toLocalTime()) && (wkStartDateTime.toLocalTime().isBefore(pmStart) || wkStartDateTime.toLocalTime().equals(pmStart)) && pmStart.isBefore(wkEndDateTime.toLocalTime()) && (wkEndDateTime.toLocalTime().isBefore(pmEnd) || wkEndDateTime.toLocalTime().equals(pmEnd))){
                        Duration durationPm = Duration.between(pmStart, wkEndDateTime.toLocalTime());
                        workTimeSum = workTimeSum + durationPm.toMinutes();
                    }
                    // 上午下班时间 < 开始时间 <= 下午上班时间 < 下午下班时间 < 结束时间
                    else if(amEnd.isBefore(wkStartDateTime.toLocalTime()) && (wkStartDateTime.toLocalTime().isBefore(pmStart) || wkStartDateTime.toLocalTime().equals(pmStart)) && pmEnd.isBefore(wkEndDateTime.toLocalTime())){
                        Duration durationPm = Duration.between(pmStart, pmEnd);
                        workTimeSum = workTimeSum + durationPm.toMinutes();
                    }

                    // 下午上班时间 < 开始时间 <= 结束时间 <= 下午下班时间
                    else if(pmStart.isBefore(wkStartDateTime.toLocalTime()) && (wkStartDateTime.toLocalTime().isBefore(wkEndDateTime.toLocalTime()) || wkStartDateTime.toLocalTime().equals(wkEndDateTime.toLocalTime())) && (wkEndDateTime.toLocalTime().isBefore(pmEnd) || wkEndDateTime.toLocalTime().equals(pmEnd))){
                        Duration durationPm = Duration.between(wkStartDateTime.toLocalTime(), wkEndDateTime.toLocalTime());
                        workTimeSum = workTimeSum + durationPm.toMinutes();
                    }
                    // 下午上班时间 < 开始时间 <= 下午下班时间 < 结束时间
                    else if(pmStart.isBefore(wkStartDateTime.toLocalTime()) && (wkStartDateTime.toLocalTime().isBefore(pmEnd) || wkStartDateTime.toLocalTime().equals(pmEnd)) && pmEnd.isBefore(wkEndDateTime.toLocalTime())){
                        Duration durationPm = Duration.between(wkStartDateTime.toLocalTime(), pmEnd);
                        workTimeSum = workTimeSum + durationPm.toMinutes();
                    }
                    else{}
                }
            }

            // 计算完成，当前日期加一，计算下一天
            calculatingDate = calculatingDate.plusDays(1);
        }
        return workTimeSum;
    }


    /**
     * 判断当前数字在不在数组weekdays中
     */
    private Boolean isContains(int[] weekdays, int nowWeekDay){
        if(0 == weekdays.length || null == weekdays){
            return false;
        }else {
            for(int weekday:weekdays){
                if(weekday == nowWeekDay){
                    return true;
                }
            }
            return false;
        }
    }
}
