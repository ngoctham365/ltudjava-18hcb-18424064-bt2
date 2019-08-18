/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ltudjava.hcb.bt2.bus;

import java.util.List;
import javax.swing.table.DefaultTableModel;
import ltudjava.hcb.bt2.dao.RemarkingDAO;
import ltudjava.hcb.bt2.dto.Remarking;

/**
 *
 * @author 
 */
public class RemarkingBUS {
    public static DefaultTableModel getData() {
        List<Remarking> listRemarking=new RemarkingDAO().getAll();
        Object[][] data=new Object[listRemarking.size()][7];
        for (Remarking iRemarking : listRemarking) {
            String[] ses=new String[7];
            ScoreBUS.getByID(iRemarking.getId().getScoreId()).getStudentId();
            ses[0]=ScoreBUS.getByID(iRemarking.getId().getScoreId()).getStudentId();
            ses[1]=StudentBUS.getFullNameByCode(ses[0]);
            ses[2]=HelperBUS.concatWithIconMoveRight(ScoreBUS.getByID(iRemarking.getId().getScoreId()).getSubjectId(), SubjectBUS.getNameById(ScoreBUS.getByID(iRemarking.getId().getScoreId()).getSubjectId()));
            ses[3]=iRemarking.getId().getScoreType();
            ses[4]=iRemarking.getScoreDesired().toString();
            ses[5]=iRemarking.getReason();
            ses[6]=iRemarking.getStatus();
        }
        return new DefaultTableModel(data, new Object[]{"MSSV", "Họ tên SV","Môn","Cột điểm","Điểm mong muốn","Lý do", "Tình trạng"}){

            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
            
        };
    }
}