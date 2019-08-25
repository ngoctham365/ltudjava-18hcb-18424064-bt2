/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ltudjava.hcb.bt2.bus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
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
        list.stream().forEach((l) -> {
            new ScoreDAO().insert(new Score(l.getStudentCode(), subject.getCode(), grade.getId(), null, null, null, null));
        });
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
                        } else if (-1 < ScoreBUS.createDetail(strings.get(0).trim(), strings.get(1).trim(), ses[1].trim(), HelperBUS.convertFloat(ses[3].trim()), HelperBUS.convertFloat(ses[4].trim()), HelperBUS.convertFloat(ses[5].trim()), HelperBUS.convertFloat(ses[3].trim()))) {
                            countStudentAdded++;
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return countStudentAdded;
    }

    public static TableModel getScoreTable(String gradeName, String subjectName) {
        Integer gradeId = new GradeDAO().getByName(gradeName).getId();
        String subjectCode = new SubjectDAO().getbyName(subjectName).get(0).getCode();
        List<Score> scores = new ScoreDAO().getByGradeSubject(gradeId, subjectCode);
        String[][] data = new String[scores.size()][6];

        for (int i = 0; i < scores.size(); i++) {
            String[] strings = new String[6];

            strings[0] = scores.get(i).getStudentId().trim();
            strings[1] = new StudentDAO().getByCode(scores.get(i).getStudentId()).getFullname();
            strings[2] = null == scores.get(i).getScodeHaft() ? "" : scores.get(i).getScodeHaft().toString();
            strings[3] = null == scores.get(i).getScoreFull() ? "" : scores.get(i).getScoreFull().toString();
            strings[4] = null == scores.get(i).getScoreAnother() ? "" : scores.get(i).getScoreAnother().toString();
            strings[5] = null == scores.get(i).getScoreSummary() ? "" : scores.get(i).getScoreSummary().toString();

            data[i] = strings;
        }

        return new DefaultTableModel(data, new String[]{"MSSV", "Họ tên", "Điểm GK", "Điểm CK", "Điểm khác", "Điểm tổng"}) {

            @Override
            public boolean isCellEditable(int row, int column) {
                return column > 1;
            }

        };
    }

    public static TableModel getScoreTable(String studentCode) {
        List<Score> scores = new ScoreDAO().getByStudent(studentCode);
        String[][] data = new String[scores.size()][6];

        for (int i = 0; i < scores.size(); i++) {
            String[] strings = new String[6];

            strings[0] = scores.get(i).getStudentId();
            strings[1] = new StudentDAO().getByCode(strings[0]).getFullname();
            strings[2] = scores.get(i).getScodeHaft()==null ? "" : scores.get(i).getScodeHaft().toString();
            strings[3] = scores.get(i).getScoreFull()==null ? "" : scores.get(i).getScoreFull().toString();
            strings[4] = scores.get(i).getScoreAnother()==null ? "" : scores.get(i).getScoreAnother().toString();
            strings[5] = scores.get(i).getScoreSummary()==null ? "" : scores.get(i).getScoreSummary().toString();

            data[i] = strings;
        }

        return new DefaultTableModel(data, new String[]{"MSSV", "Họ tên", "Điểm GK", "Điểm CK", "Điểm khác", "Điểm tổng"}) {

            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }

        };
    }

    public static boolean update(String studentCode, String gradeName, String subjectName, Float scoreHaft, Float scoreFull, Float scoreAnother, Float scoreSummary) {
        Integer gradeId = GradeBUS.getByName(gradeName).getId();
        String subjectCode = SubjectBUS.getByName(subjectName).get(0).getCode();
        Score score = new ScoreDAO().getByGradeSubjectStudent(gradeId, subjectCode, studentCode);
        score.setScodeHaft(scoreHaft != null ? scoreHaft : score.getScodeHaft());
        score.setScoreFull(scoreFull != null ? scoreHaft : score.getScoreFull());
        score.setScoreAnother(scoreAnother != null ? scoreAnother : score.getScoreAnother());
        score.setScoreFull(scoreFull != null ? scoreFull : score.getScoreFull());
        return new ScoreDAO().update(score);
    }

    public static Score getAccordingToStudentSubjectGrade(String studentCode, String subjectName, String gradeName) {
        Integer gradeId = GradeBUS.getByName(gradeName).getId();
        String subjectCode = SubjectBUS.getByName(subjectName).get(0).getCode();

        return new ScoreDAO().getByGradeSubjectStudent(gradeId, subjectCode, studentCode);
    }

    public static boolean register(String studentCode, String subjectName, String gradeName) {
        Integer gradeId = GradeBUS.getByName(gradeName).getId();
        String subjectCode = SubjectBUS.getByName(subjectName).get(0).getCode();

        return new ScoreDAO().insert(new Score(studentCode, subjectCode, gradeId)) > -1;
    }

    public static boolean delete(String studentCode, String subjectName, String gradeName) {
        Integer gradeId = GradeBUS.getByName(gradeName).getId();
        String subjectCode = SubjectBUS.getByName(subjectName).get(0).getCode();

        Score s = new ScoreDAO().getByGradeSubjectStudent(gradeId, subjectCode, studentCode);
        return new ScoreDAO().delete(s.getId());
    }

    public static boolean hasRegisterStudy(String studentCode, String subjectName) {
        String subjectCode = new SubjectDAO().getbyName(subjectName).get(0).getCode();

        return new ScoreDAO().getByStudentSubject(studentCode, subjectCode) != null;
    }

    static Score getByStudentSubject(String studentCode, String subjectCode) {
        return new ScoreDAO().getByStudentSubject(studentCode, subjectCode);
    }

    public static List<Map<String, ?>> dataReportBySubject(String gradeName, String subjectName) {
        List<Map<String, ?>> maps = new ArrayList<>();

        Integer gradeId = GradeBUS.getByName(gradeName).getId();
        String subjectCode = SubjectBUS.getByName(subjectName).get(0).getCode();
        List<Score> scores = new ScoreDAO().getByGradeSubject(gradeId, subjectCode);
        for (int i = 0; i < scores.size(); i++) {
            Map<String, Object> map = new HashMap<>();

            map.put("studentCode", scores.get(i).getStudentId());
            map.put("studentName", StudentBUS.getFullNameByCode(scores.get(i).getStudentId()));
            map.put("scoreHaft", scores.get(i).getScodeHaft()==null ? "" : scores.get(i).getScodeHaft().toString());
            map.put("scoreFull", scores.get(i).getScoreFull()==null ? "" : scores.get(i).getScoreFull().toString());
            map.put("scoreAnother", scores.get(i).getScoreAnother()==null ? "" : scores.get(i).getScoreAnother().toString());
            map.put("scoreSummary", scores.get(i).getScoreSummary()==null ? "" : scores.get(i).getScoreSummary().toString());
            map.put("result", scores.get(i).getScoreSummary()==null ? "" : scores.get(i).getScoreSummary() >= 5 ? "Đỗ" : "Hỏng");

            maps.add(map);
        }
        return maps;
    }

    static boolean delete(String studentCode) {
        List<Score> scores = new ScoreDAO().getByStudent(studentCode);
        for (Score score : scores) {
            if (!new ScoreDAO().delete(score.getId())) {
                return false;
            }
        }

        return true;
    }

    private static int createDetail(String gradeName, String subjectCode, String studentCode, Float haft, Float full, Float another, Float summary) {
        Integer gradeId = GradeBUS.getByName(gradeName).getId();

        return new ScoreDAO().insert(new Score(studentCode, subjectCode, gradeId, haft, full, another, summary));
    }

}
