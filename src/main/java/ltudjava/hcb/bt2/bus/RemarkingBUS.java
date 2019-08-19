/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ltudjava.hcb.bt2.bus;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import ltudjava.hcb.bt2.dao.RemarkingDAO;
import ltudjava.hcb.bt2.dao.ScoreDAO;
import ltudjava.hcb.bt2.dao.SubjectDAO;
import ltudjava.hcb.bt2.dto.Remarking;
import ltudjava.hcb.bt2.dto.RemarkingId;
import ltudjava.hcb.bt2.dto.Score;

/**
 *
 * @author
 */
public class RemarkingBUS {

    public static DefaultTableModel getData(String studentCode) {
        List<Remarking> listRemarking = new ArrayList<>();
        if (studentCode.isEmpty()) {
            listRemarking = new RemarkingDAO().getAll();
        } else {
            List<Score> scores = new ScoreDAO().getByStudent(studentCode);
            for (Score score : scores) {
                Remarking r = new RemarkingDAO().getById(score.getId());
                if (r != null) {
                    listRemarking.add(r);
                }
            }
        }
        Object[][] data = new Object[listRemarking.size()][7];
        for (Remarking iRemarking : listRemarking) {
            String[] ses = new String[7];
            ScoreBUS.getByID(iRemarking.getId().getScoreId()).getStudentId();
            ses[0] = ScoreBUS.getByID(iRemarking.getId().getScoreId()).getStudentId();
            ses[1] = StudentBUS.getFullNameByCode(ses[0]);
            ses[2] = HelperBUS.concatWithIconMoveRight(ScoreBUS.getByID(iRemarking.getId().getScoreId()).getSubjectId(), SubjectBUS.getNameById(ScoreBUS.getByID(iRemarking.getId().getScoreId()).getSubjectId()));
            ses[3] = iRemarking.getId().getScoreType();
            ses[4] = iRemarking.getScoreDesired().toString();
            ses[5] = iRemarking.getReason();
            ses[6] = iRemarking.getStatus();
        }
        return new DefaultTableModel(data, new Object[]{"MSSV", "Họ tên SV", "Môn", "Cột điểm", "Điểm mong muốn", "Lý do", "Tình trạng"}) {

            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }

        };
    }

    public static boolean create(String studentCode, String subjectName, String scoreType, String score, String reason) {
        String subjectCode = new SubjectDAO().getbyName(subjectName).get(0).getCode();
        Score s = ScoreBUS.getByStudentSubject(studentCode, subjectCode);

        return new RemarkingDAO().insert(new Remarking(new RemarkingId(s.getId(), scoreType),
                scoreType.contains("giữa") ? s.getScodeHaft()
                        : scoreType.contains("cuối") ? s.getScoreFull()
                                : scoreType.contains("khác") ? s.getScoreAnother() : s.getScoreSummary(),
                Float.parseFloat(score),
                reason, null)) != null;
    }

    public static boolean had(String studentCode, String subjectName, String scoreType) {
        String subjectCode = new SubjectDAO().getbyName(subjectName).get(0).getCode();
        Score s = ScoreBUS.getByStudentSubject(studentCode, subjectCode);

        return new RemarkingDAO().getByScoreAndScoreType(s.getId(),scoreType)!=null;
    }

}
