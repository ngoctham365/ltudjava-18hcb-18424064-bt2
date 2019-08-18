/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ltudjava.hcb.bt2.bus;

import java.util.List;
import javax.swing.JOptionPane;
import ltudjava.hcb.bt2.dao.*;
import ltudjava.hcb.bt2.dto.*;

/**
 *
 * @author Jossion
 */
public class ScoreBUS {

    static Score getByID(int scoreId) {
        return new ScoreDAO().getById(scoreId);
    }

    static void addAllStudentInGradeToTimeTable(Subject subject, Grade grade) {
        List<Student> list = new StudentDAO().getByGrade(grade.getId());
        for (Student l : list) {
            new ScoreDAO().insert(new Score(l.getStudentCode(), subject.getCode(), grade.getId(), null, null, null, null));
        }
    }

    public static Integer saveInfoListScoreFromFileCSV(javax.swing.JFrame frame) {

        Integer countStudentAdded = 0;
        List<String> strings = HelperBUS.openFileToGetContent(frame);
        if (strings.size() > 0) {
            strings.add(1, strings.get(0).replace("Diem_", "").split("-")[1]);
            strings.set(0, strings.get(0).replace("Diem_", "").split("-")[0].trim());

            Grade g = new GradeDAO().getByName(strings.get(0));
            if (g == null) {
                if (JOptionPane.showConfirmDialog(frame, "Hiện tại lớp " + strings.get(0) + " chưa có trong dữ liệu.\nMuốn tạo lớp mới và thêm dữ liệu?") == JOptionPane.OK_OPTION) {
                    g = new Grade(strings.get(0));
                    g.setId(new GradeDAO().insert(new Grade(strings.get(0))));
                    g.setName(strings.get(0));
                } else {
                    return -1;
                }
            } else if (JOptionPane.showConfirmDialog(frame, "Muốn thêm sinh viên vào danh sách lớp " + strings.get(0) + " vào danh sách?") != JOptionPane.OK_OPTION) {
                return -1;
            } else if (null == new TimeTableDAO().getByGradeAndSubject(g.getId(), strings.get(1))) {
                JOptionPane.showMessageDialog(frame, "Không tồn tại lịch học của môn có mã " + strings.get(0) + " trong lớp " + g.getName() + ".\n"
                        + "Vì vậy không thể nhập điểm từ tập tin .CSV.");
                return -1;
            }

            for (int idx = 3; idx < strings.size(); idx++) {
                try {
                    String[] ses = strings.get(idx).split(",");
                    if (ses.length == 7) {
                        Score score = new ScoreDAO().getByGradeSubjectStudent(g.getId(), strings.get(1), ses[1].trim());
                        if (null != score) {
                            score.setScodeHaft(HelperBUS.convertFloat(ses[3].trim()));
                            score.setScoreFull(HelperBUS.convertFloat(ses[4].trim()));
                            score.setScoreAnother(HelperBUS.convertFloat(ses[5].trim()));
                            score.setScoreSummary(HelperBUS.convertFloat(ses[6].trim()));

                            if (new ScoreDAO().update(score)) {
                                countStudentAdded++;
                            }
                        }
                        countStudentAdded++;
                    }
                } catch (Exception e) {
                }
            }
        }
        return countStudentAdded;
    }

}
