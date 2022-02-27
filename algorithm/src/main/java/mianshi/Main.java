package mianshi;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        // 每场面试时长，单位：秒
        Integer interviewTime = 60 * 70;

        Interview interview = new Interview();
        interview.setAmStartTime("09:00:00");
        interview.setAmEndTime("11:00:00");
        interview.setPmStartTime("14:00:00");
        interview.setPmEndTime("17:00:00");
        interview.setRoomId(1);
        interview.setInterviewTime(interviewTime);
        interview.setStartTime("2020-10-01");

        List<Student> studentList = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            Student student = new Student(i);
            studentList.add(student);
        }

        calcInterviewTimeForStu(interview, studentList);

        for (Student student : studentList) {
            System.out.println(student);
        }

        System.out.println(interview);
    }

    /**
     * 计算每个学生的面试开始时间
     *
     * @param interview 面试基本信息
     * @param studentList 学生信息
     */
    public static void calcInterviewTimeForStu(Interview interview, List<Student> studentList) {

        //上午开始面试时间戳
        LocalDate amStartDate = LocalDate.parse(interview.getStartTime());
        LocalTime amStartTime = LocalTime.parse(interview.getAmStartTime());
        LocalDateTime amStartDateTime = LocalDateTime.of(amStartDate, amStartTime);

        //上午结束面试时间戳
        LocalTime amEndTime = LocalTime.parse(interview.getAmEndTime());

        //下午开始面试时间戳
        LocalTime pmStartTime = LocalTime.parse(interview.getPmStartTime());

        //下午结束面试时间戳
        LocalTime pmEndTime = LocalTime.parse(interview.getPmEndTime());

        //面试时长 单位:秒
        int interviewTime = interview.getInterviewTime();

        /****** 初始化下一个面试的开始时间和结束时间 *******/
        //1. 面试的开始时间是上午开始时间
        LocalDateTime interviewStartTime = amStartDateTime;

        for (Student student : studentList) {
            interviewStartTime = calcInterviewStartTime(amStartTime, amEndTime,
                    pmStartTime, pmEndTime, interviewStartTime, interviewTime);

            /*****设置考生的考试时间******/
            student.setRoomId(interview.getRoomId());
            student.setInterviewStartTimeStamp(interviewStartTime);
            student.setInterviewEndTimeStamp(interviewStartTime.plusSeconds(interviewTime - 1));
            student.setInterviewTime(interviewTime);

            interviewStartTime = interviewStartTime.plusSeconds(interviewTime);
        }

    }

    private static LocalDateTime calcInterviewStartTime(LocalTime startTime, LocalTime endTime,
                                                        LocalDateTime interviewStartTime,
                                                        int interviewTime) {
        //时间段
        LocalDateTime startDateTime = LocalDateTime.of(interviewStartTime.toLocalDate(), startTime);
        LocalDateTime endDateTime = LocalDateTime.of(interviewStartTime.toLocalDate(), endTime);

        //根据预计面试时间interviewStartTime, 计算此次面试的结束时间
        LocalDateTime nextInterviewEndTime = interviewStartTime.plusSeconds(interviewTime);

        //1. 如果面试结束时间在结束之前
        if (nextInterviewEndTime.isBefore(endDateTime) || nextInterviewEndTime.isEqual(endDateTime)) {
            return interviewStartTime;
        }

        //4. 如果下一个面试结束时间在结束之后
        //只能第二天面试了
        startDateTime = startDateTime.plusDays(1L);
        //第二天的开始时间当作是预计面试时间
        interviewStartTime = startDateTime;

        return calcInterviewStartTime(startTime, endTime, interviewStartTime, interviewTime);
    }

    /**
     *
     * @param amStartTime 上午开始面试时间点, 只有时分秒, 没有年月日
     * @param amEndTime 上午结束面试时间点, 只有时分秒, 没有年月日
     * @param pmStartTime 下午开始面试时间点, 只有时分秒, 没有年月日
     * @param pmEndTime 下午面试结束时间点, 只有时分秒, 没有年月日
     * @param interviewStartTime 预计面试的日期时间, 时分秒年月日都有
     * @param interviewTime 面试时长 单位：秒
     * @return
     */
    private static LocalDateTime calcInterviewStartTime(LocalTime amStartTime, LocalTime amEndTime,
                                                        LocalTime pmStartTime, LocalTime pmEndTime,
                                                        LocalDateTime interviewStartTime,
                                                        int interviewTime) {

        //根据预计面试时间interviewStartTime, 获取当天的面试有效时间段, 时分秒年月日都有
        LocalDateTime amStartDateTime = LocalDateTime.of(interviewStartTime.toLocalDate(), amStartTime);
        LocalDateTime amEndDateTime = LocalDateTime.of(interviewStartTime.toLocalDate(), amEndTime);
        LocalDateTime pmStartDateTime = LocalDateTime.of(interviewStartTime.toLocalDate(), pmStartTime);
        LocalDateTime pmEndDateTime = LocalDateTime.of(interviewStartTime.toLocalDate(), pmEndTime);

        //根据预计面试时间interviewStartTime, 计算此次面试的结束时间
        LocalDateTime nextInterviewEndTime = interviewStartTime.plusSeconds(interviewTime);

        //1. 如果下一个面试结束时间在上午结束之前
        if (nextInterviewEndTime.isBefore(amEndDateTime) || nextInterviewEndTime.isEqual(amEndDateTime)) {
            return interviewStartTime;
        }

        //2. 如果下一个面试结束时间落在上午结束和下午开始之间
        if (nextInterviewEndTime.isAfter(amEndDateTime) && nextInterviewEndTime.isBefore(pmStartDateTime)) {
            interviewStartTime = pmStartDateTime;
            nextInterviewEndTime = interviewStartTime.plusSeconds(interviewTime);
        }

        //3. 如果下一个面试结束时间在下午结束之前
        if (nextInterviewEndTime.isBefore(pmEndDateTime) || nextInterviewEndTime.isEqual(pmEndDateTime)) {
            return interviewStartTime;
        }

        //4. 如果下一个面试结束时间在下午结束之后
        if (nextInterviewEndTime.isAfter(pmEndDateTime)) {
            //只能第二天面试了
            amStartDateTime = amStartDateTime.plusDays(1L);

            //第二天的开始时间当作是预计面试时间
            interviewStartTime = amStartDateTime;
        }

        // 传入面试的开始时间点,amStartTime/amEndTime/pmStartTime/pmEndTime ,
        // 还有预计面试时间interviewStartTime, 面试时长interviewTime
        // 重新计算面试时间
        return calcInterviewStartTime(amStartTime, amEndTime, pmStartTime, pmEndTime,
                interviewStartTime, interviewTime);
    }

}

@Setter
@Getter
class Student {

    private int id; //学生id

    private int roomId;

    private int interviewTime; //面试时长

    private LocalDateTime interviewStartTimeStamp; //面试开始时间戳

    private LocalDateTime interviewEndTimeStamp; //面试结束时间戳

    public Student(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        // 日期时间转字符串
        String interviewStartTime = interviewStartTimeStamp.format(formatter);
        String interviewEndTime = interviewEndTimeStamp.format(formatter);

        return "Student{" +
                "id=" + id +
                ", roomId=" + roomId +
                ", interviewTime=" + interviewTime +
                ", interviewStartTime='" + interviewStartTime + '\'' +
                ", interviewEndTime='" + interviewEndTime + '\'' +
                '}';
    }
}


@Setter
@Getter
@ToString
class Interview {

    private String amStartTime;

    private String amEndTime;

    private String pmStartTime;

    private String pmEndTime;

    private Integer roomId;

    private Integer interviewTime; //面试时长 单位:秒

    private String startTime; //开始日期

}