/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ltudjava.hcb.bt2.gui;

import ltudjava.hcb.bt2.dto.User;

/**
 *
 * @author Jossion
 */
public class MainFrame extends javax.swing.JFrame {

    private User user = null;

    /**
     * Creates new form MainFrame
     */
    public MainFrame() {
        initComponents();
    }

    MainFrame(User nd) {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        btnModifyPass = new javax.swing.JButton();
        btnScore = new javax.swing.JButton();
        btnStudent = new javax.swing.JButton();
        btnGrade = new javax.swing.JButton();
        btnSubject = new javax.swing.JButton();
        btnTimeTable = new javax.swing.JButton();
        btnListGradeAccordingToSource = new javax.swing.JButton();
        btnReport = new javax.swing.JButton();
        btnRemarking = new javax.swing.JButton();
        btnSeeScore = new javax.swing.JButton();
        btnCourseRegister = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("CÁC CHỨC NĂNG");
        jLabel1.setToolTipText("");
        jLabel1.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        btnModifyPass.setText("ĐỔI MẬT KHẨU");
        btnModifyPass.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModifyPassActionPerformed(evt);
            }
        });

        btnScore.setText("QUẢN LÝ ĐIỂM");
        btnScore.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnScoreActionPerformed(evt);
            }
        });

        btnStudent.setText("QUẢN LÝ SINH VIÊN");
        btnStudent.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnStudentActionPerformed(evt);
            }
        });

        btnGrade.setText("QUẢN LÝ LỚP");
        btnGrade.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGradeActionPerformed(evt);
            }
        });

        btnSubject.setText("QUẢN LÝ MÔN HỌC");
        btnSubject.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSubjectActionPerformed(evt);
            }
        });

        btnTimeTable.setText("THỜI KHÓA BIỂU");
        btnTimeTable.setToolTipText("");
        btnTimeTable.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimeTableActionPerformed(evt);
            }
        });

        btnListGradeAccordingToSource.setText("DS LỚP THEO MÔN");
        btnListGradeAccordingToSource.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnListGradeAccordingToSourceActionPerformed(evt);
            }
        });

        btnReport.setText("XUẤT BẢNG ĐIỂM");
        btnReport.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReportActionPerformed(evt);
            }
        });

        btnRemarking.setText("QUẢN LÝ PHÚC KHẢO");
        btnRemarking.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRemarkingActionPerformed(evt);
            }
        });

        btnSeeScore.setText("XEM ĐIỂM");
        btnSeeScore.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSeeScoreActionPerformed(evt);
            }
        });

        btnCourseRegister.setText("ĐĂNG KÝ MÔN HỌC");
        btnCourseRegister.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCourseRegisterActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnModifyPass, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnScore, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnStudent, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnGrade, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnSubject, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnTimeTable, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnListGradeAccordingToSource, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnReport, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnRemarking, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnSeeScore, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnCourseRegister, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnModifyPass)
                    .addComponent(btnScore)
                    .addComponent(btnStudent))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnGrade)
                    .addComponent(btnSubject)
                    .addComponent(btnTimeTable))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnListGradeAccordingToSource)
                    .addComponent(btnReport)
                    .addComponent(btnRemarking))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSeeScore)
                    .addComponent(btnCourseRegister))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnStudentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnStudentActionPerformed
        if (this.user != null && this.user.getRole().equals("GIAOVU")) {
            StudentMngFrame frame = new StudentMngFrame();
            StudentMngFrame.showed = true;
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
            frame.setResizable(false);
            this.setVisible(false);
        }
    }//GEN-LAST:event_btnStudentActionPerformed

    private void btnTimeTableActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimeTableActionPerformed
        if (this.user != null && !TimeTableFrame.showed) {
            TimeTableFrame frame = new TimeTableFrame();
            TimeTableFrame.showed = true;
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
            frame.setResizable(false);
            this.setVisible(false);
        }
    }//GEN-LAST:event_btnTimeTableActionPerformed

    private void btnRemarkingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemarkingActionPerformed
        if (this.user != null && !TimeTableFrame.showed) {
            TimeTableFrame frame = new TimeTableFrame();
            TimeTableFrame.showed = true;
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
            frame.setResizable(false);
            this.setVisible(false);
        }
    }//GEN-LAST:event_btnRemarkingActionPerformed

    private void btnCourseRegisterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCourseRegisterActionPerformed
        if (this.user != null && !TimeTableFrame.showed) {
            TimeTableFrame frame = new TimeTableFrame();
            TimeTableFrame.showed = true;
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
            frame.setResizable(false);
            this.setVisible(false);
        }
    }//GEN-LAST:event_btnCourseRegisterActionPerformed

    private void btnModifyPassActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModifyPassActionPerformed
        if (this.user != null && !ModifyPassFrame.showed) {
            ModifyPassFrame.showed = true;
            ModifyPassFrame frame = new ModifyPassFrame(user.getName(), user.getPass());
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
            frame.setResizable(false);
            this.setVisible(false);
        }
    }//GEN-LAST:event_btnModifyPassActionPerformed

    private void btnScoreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnScoreActionPerformed
        if (this.user != null && this.user.getRole().equals("GIAOVU") && !ScoreMngFrame.showed) {
            ScoreMngFrame.showed = true;
            ScoreMngFrame frame = new ScoreMngFrame();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
            frame.setResizable(false);
            this.setVisible(false);
        }
    }//GEN-LAST:event_btnScoreActionPerformed

    private void btnGradeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGradeActionPerformed
        if (this.user != null && !TimeTableFrame.showed) {
            TimeTableFrame frame = new TimeTableFrame();
            TimeTableFrame.showed = true;
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
            frame.setResizable(false);
            this.setVisible(false);
        }
    }//GEN-LAST:event_btnGradeActionPerformed

    private void btnSubjectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSubjectActionPerformed
        if (this.user != null && !TimeTableFrame.showed) {
            TimeTableFrame frame = new TimeTableFrame();
            TimeTableFrame.showed = true;
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
            frame.setResizable(false);
            this.setVisible(false);
        }
    }//GEN-LAST:event_btnSubjectActionPerformed

    private void btnListGradeAccordingToSourceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnListGradeAccordingToSourceActionPerformed
        if (this.user != null && !TimeTableFrame.showed) {
            TimeTableFrame frame = new TimeTableFrame();
            TimeTableFrame.showed = true;
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
            frame.setResizable(false);
            this.setVisible(false);
        }
    }//GEN-LAST:event_btnListGradeAccordingToSourceActionPerformed

    private void btnReportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReportActionPerformed
        if (this.user != null && !TimeTableFrame.showed) {
            TimeTableFrame frame = new TimeTableFrame();
            TimeTableFrame.showed = true;
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
            frame.setResizable(false);
            this.setVisible(false);
        }
    }//GEN-LAST:event_btnReportActionPerformed

    private void btnSeeScoreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSeeScoreActionPerformed
        if (this.user != null && !TimeTableFrame.showed) {
            TimeTableFrame frame = new TimeTableFrame();
            TimeTableFrame.showed = true;
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
            frame.setResizable(false);
            this.setVisible(false);
        }
    }//GEN-LAST:event_btnSeeScoreActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCourseRegister;
    private javax.swing.JButton btnGrade;
    private javax.swing.JButton btnListGradeAccordingToSource;
    private javax.swing.JButton btnModifyPass;
    private javax.swing.JButton btnRemarking;
    private javax.swing.JButton btnReport;
    private javax.swing.JButton btnScore;
    private javax.swing.JButton btnSeeScore;
    private javax.swing.JButton btnStudent;
    private javax.swing.JButton btnSubject;
    private javax.swing.JButton btnTimeTable;
    private javax.swing.JLabel jLabel1;
    // End of variables declaration//GEN-END:variables
}