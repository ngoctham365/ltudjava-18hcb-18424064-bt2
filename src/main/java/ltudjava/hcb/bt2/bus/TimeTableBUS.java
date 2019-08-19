/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ltudjava.hcb.bt2.bus;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import ltudjava.hcb.bt2.dao.*;
import ltudjava.hcb.bt2.dto.*;

/**
 *
 * @author Jossion
 */
public class TimeTableBUS {

    @SuppressWarnings({"null", "CallToPrintStackTrace"})
    public static Integer saveInfoListTimeTableFromFileCSV(javax.swing.JFrame frame) {
        Integer countStudentAdded = 0;
        List<String> strings = HelperBUS.openFileToGetContent(frame);
        if (strings.size() > 0) {
            strings.set(0, strings.get(0).replace("TKB", ""));
            Grade g = new GradeDAO().getByName(strings.get(0));
            if (g == null) {
                if (JOptionPane.showConfirmDialog(frame, "Hiện tại lớp " + strings.get(0) + " chưa có trong dữ liệu.\nMuốn tạo lớp mới và thêm dữ liệu?") == JOptionPane.OK_OPTION) {
                    g = new Grade(strings.get(0));
                    g.setId(new GradeDAO().insert(new Grade(strings.get(0))));
                } else {
                    return -1;
                }
            } else if (JOptionPane.showConfirmDialog(frame, "Muốn tạo lịch học cho lớp " + strings.get(0) + "?") != JOptionPane.OK_OPTION) {
                return -1;
            }

            for (int idx = 2; idx < strings.size(); idx++) {
                try {
                    String[] ses = strings.get(idx).split(",");
                    if (ses.length == 4) {

                        Subject subject = new SubjectDAO().getById(ses[1].trim());
                        if (subject == null && JOptionPane.OK_OPTION == JOptionPane.showConfirmDialog(frame, "Không có môn " + HelperBUS.concatWithIconMoveRight(ses[1], ses[2]) + " trong dữ liệu. \n"
                                + "Tôi muốn tạo môn này?")) {
                            if (!ses[1].trim().equals(new SubjectDAO().insert(new Subject(ses[1].trim(), ses[2].trim())))) {
                                JOptionPane.showMessageDialog(frame, "Thêm môn học thất bại!");
                                return -1;
                            }
                        } else if (!subject.getName().equals(ses[2].trim()) && JOptionPane.OK_OPTION != JOptionPane.showConfirmDialog(frame, "Môn học có mã " + ses[1] + " bị sai tên.\n"
                                + "Tên đầu vào: " + ses[2].trim() + ".\n"
                                + "Tên trong dữ liệu: " + subject.getName() + "\nMuốn tiếp tục thêm lịch học theo mã môn học?")) {
                            return -1;
                        }

                        if (null != new TimeTableDAO().insert(new TimeTable(new TimeTableId(subject.getCode(), g.getId()), ses[3].trim()))) {
                            countStudentAdded++;
                            ScoreBUS.addAllStudentInGradeToTimeTable(subject, g);
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return countStudentAdded;
    }

    public static List<TimeTable> getbyGrade(String gradeName) {
        Integer gradeId = new GradeDAO().getByName(gradeName).getId();
        return new TimeTableDAO().getByGrade(gradeId);
    }

    public static TableModel getListToGUI(String studentCode) {
        List<TimeTable> timeTables = new ArrayList<>();
        if (studentCode.isEmpty()) {
            timeTables = new TimeTableDAO().getAll();
        } else {
            List<Score> scores = new ScoreDAO().getByStudent(studentCode);
            for (Score score : scores) {
                timeTables.add(new TimeTableDAO().getByScoreId(score.getSubjectId()));
            }
        }
        String[][] data = new String[timeTables.size()][3];
        for (int i = 0; i < timeTables.size(); i++) {
            TimeTable get = timeTables.get(i);
            String[] ses = new String[3];

            ses[0] = GradeBUS.getGrade(String.valueOf(timeTables.get(i).getId().getGrade())).getName();
            ses[1] = SubjectBUS.getByCode(timeTables.get(i).getId().getSubjectCode()).getName();
            ses[2] = timeTables.get(i).getRoom();

            data[i] = ses;
        }
        return new DefaultTableModel(data, new String[]{"Lớp", "Môn học", "Phòng học"}) {

            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }

        };
    }

    public static TimeTable getByGradeSubject(String gradeName, String subjectName) {
        Integer gradeId = new GradeDAO().getByName(gradeName).getId();
        String subjectCode = new SubjectDAO().getbyName(subjectName).get(0).getCode();
        return new TimeTableDAO().getByGradeAndSubject(gradeId, subjectCode);
    }

    public static boolean add(String gradeName, String subjectName, String room) {
        Integer gradeId = new GradeDAO().getByName(gradeName).getId();
        String subjectCode = new SubjectDAO().getbyName(subjectName).get(0).getCode();

        if (new TimeTableDAO().insert(new TimeTable(new TimeTableId(subjectCode, gradeId), room)) != null) {
            List<Student> students = StudentBUS.getByGrade(gradeName);
            if (!students.stream().noneMatch((student) -> (new ScoreDAO().insert(new Score(student.getStudentCode(), subjectCode, gradeId, null, null, null, null)) != -1))) {
                return false;
            }
        } else {
            return false;
        }
        return true;
    }

    public static boolean relateRoom(String gradeName, String subjectName, String room) {
        Integer gradeId = new GradeDAO().getByName(gradeName).getId();
        String subjectCode = new SubjectDAO().getbyName(subjectName).get(0).getCode();

        return new TimeTableDAO().update(new TimeTable(new TimeTableId(subjectCode, gradeId), room));
    }

    public static boolean delete(String gradeName, String subjectName, String room) {
        Integer gradeId = new GradeDAO().getByName(gradeName).getId();
        String subjectCode = new SubjectDAO().getbyName(subjectName).get(0).getCode();
        List<Score> scores = new ScoreDAO().getByGradeSubject(gradeId, subjectName);
        if (!scores.stream().noneMatch((score) -> (!score.getScodeHaft().isNaN()
                || !score.getScoreFull().isNaN()
                || !score.getScoreAnother().isNaN()
                || !score.getScoreSummary().isNaN()))) {
            return false;
        }
        scores.stream().forEach((score) -> {
            new ScoreDAO().delete(score.getId());
        });
        return new TimeTableDAO().delete(new TimeTableId(subjectCode, gradeId));
    }

}
