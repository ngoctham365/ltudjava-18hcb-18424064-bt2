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
public class TimeTableBUS {

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
                            ScoreBUS.addAllStudentInGradeToTimeTable(subject,g);
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return countStudentAdded;
    }

}
