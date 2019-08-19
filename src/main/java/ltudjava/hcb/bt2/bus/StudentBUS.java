/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ltudjava.hcb.bt2.bus;

import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import ltudjava.hcb.bt2.dao.*;
import ltudjava.hcb.bt2.dto.*;

/**
 *
 * @author
 */
public class StudentBUS {

    public static String getFullNameByCode(String studentCode) {
        return getByCode(studentCode).getFullname();
    }

    public static Student getByCode(String studentCode) {
        return new StudentDAO().getByCode(studentCode);
    }

    public static Integer saveInfoListStudentFromFileCSV(javax.swing.JFrame frame) {
        Integer countStudentAdded = 0;
        List<String> strings = HelperBUS.openFileToGetContent(frame);
        if (strings.size() > 0) {
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
            }

            for (int idx = 2; idx < strings.size(); idx++) {
                try {
                    String[] ses = strings.get(idx).split(",");
                    if (ses.length == 5 && ses[1].equals(new StudentDAO().insert(new Student(ses[1].trim(), ses[2].trim(), ses[3].trim(), ses[4].trim(), g.getId())))) {
                        countStudentAdded++;
                    }
                } catch (Exception e) {
                }
            }
        }
        return countStudentAdded;
    }

    public static boolean changeInfo(Student s) {
        return new StudentDAO().update(s);
    }

    public static List<Student> getByGrade(String gradeName) {
        Integer gradeId = new GradeDAO().getByName(gradeName).getId();
        return new StudentDAO().getByGrade(gradeId);
    }

    public static TableModel getToGuiAccordingToGrade(String gradeName) {
        List<Student> students;
        if (gradeName.isEmpty()) {
            students = new StudentDAO().getAll();
        } else {
            students = new StudentDAO().getByGrade(GradeBUS.getByName(gradeName).getId());
        }

        String[][] data = new String[students.size()][5];

        for (int i = 0; i < students.size(); i++) {
            String[] strings = new String[5];

            strings[0] = students.get(i).getStudentCode();
            strings[1] = students.get(i).getFullname();
            strings[2] = students.get(i).getSex();
            strings[3] = students.get(i).getPersonCode();
            strings[4] = new GradeDAO().getById(students.get(i).getGrade()).getName();

            data[i] = strings;
        }

        return new DefaultTableModel(data, new String[]{"MSSV", "Họ tên", "Giới tính", "CMND", "Lớp"}) {

            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 4;
            }

        };
    }

    public static boolean add(String code, String fullName, String personCode, String gradeName) {
        Integer gradeId = new GradeDAO().getByName(gradeName).getId();
        return code.equals(new StudentDAO().insert(new Student(code, fullName, "Nam", personCode, gradeId)));
    }

    public static boolean remove(String code) {
        return new StudentDAO().delete(code);
    }

}
