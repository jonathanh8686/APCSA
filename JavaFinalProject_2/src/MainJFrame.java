/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author wug0356
 */
import java.awt.*;
import java.io.File;
import java.util.List;
import java.util.Scanner;
import java.io.IOException;
public class MainJFrame extends javax.swing.JFrame {

    /**
     * Creates new form MainJFrame
     */
    public MainJFrame() {
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

        jPanel1 = new javax.swing.JPanel();
        TitleLabel = new javax.swing.JLabel();
        StartButton = new javax.swing.JButton();
        ScoreLabel = new javax.swing.JLabel();
        printScoreLabel = new javax.swing.JLabel();
        HighscoreLabel = new javax.swing.JLabel();
        printHighscoreLabel = new javax.swing.JLabel();
        ScoreboardButton = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jPanel1.setPreferredSize(new java.awt.Dimension(800, 500));
        jPanel1.setLayout(null);

        TitleLabel.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        TitleLabel.setForeground(new java.awt.Color(255, 255, 255));
        TitleLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        TitleLabel.setText("Ball Blast");
        jPanel1.add(TitleLabel);
        TitleLabel.setBounds(0, 0, 500, 41);

        StartButton.setText("Start");
        StartButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                StartButtonActionPerformed(evt);
            }
        });
        jPanel1.add(StartButton);
        StartButton.setBounds(210, 290, 100, 23);

        ScoreLabel.setForeground(new java.awt.Color(255, 255, 255));
        ScoreLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        ScoreLabel.setText("Previous Score:");
        jPanel1.add(ScoreLabel);
        ScoreLabel.setBounds(0, 47, 250, 38);

        printScoreLabel.setForeground(new java.awt.Color(255, 255, 255));
        printScoreLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jPanel1.add(printScoreLabel);
        printScoreLabel.setBounds(256, 47, 236, 38);

        HighscoreLabel.setForeground(new java.awt.Color(255, 255, 255));
        HighscoreLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        HighscoreLabel.setText("Highscore:");
        jPanel1.add(HighscoreLabel);
        HighscoreLabel.setBounds(0, 91, 250, 38);

        printHighscoreLabel.setForeground(new java.awt.Color(255, 255, 255));
        printHighscoreLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jPanel1.add(printHighscoreLabel);
        printHighscoreLabel.setBounds(256, 91, 236, 38);

        ScoreboardButton.setText("Scoreboard");
        ScoreboardButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ScoreboardButtonActionPerformed(evt);
            }
        });
        jPanel1.add(ScoreboardButton);
        ScoreboardButton.setBounds(210, 310, 100, 23);

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Capture.PNG"))); // NOI18N
        jLabel2.setText("jLabel2");
        jLabel2.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        jPanel1.add(jLabel2);
        jLabel2.setBounds(-6, -6, 500, 380);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 494, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 371, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void StartButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_StartButtonActionPerformed
        // TODO add your handling code here:
        BallBlast game = new BallBlast();
    }//GEN-LAST:event_StartButtonActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        // TODO add your handling code here:
        try
        {
            Scanner scanP = new Scanner(new File("scores.local"));
            printScoreLabel.setText(scanP.nextLine());
            Scanner scanH = new Scanner(new File("highScores.csv"));
            printHighscoreLabel.setText(scanH.nextLine().split(",")[1]);
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
        
    }//GEN-LAST:event_formWindowOpened

    private void ScoreboardButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ScoreboardButtonActionPerformed
        // TODO add your handling code here:
        Scoreboard sb = new Scoreboard();
        sb.setVisible(true);
    }//GEN-LAST:event_ScoreboardButtonActionPerformed

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
            java.util.logging.Logger.getLogger(MainJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainJFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel HighscoreLabel;
    private javax.swing.JLabel ScoreLabel;
    private javax.swing.JButton ScoreboardButton;
    private javax.swing.JButton StartButton;
    private javax.swing.JLabel TitleLabel;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel printHighscoreLabel;
    private javax.swing.JLabel printScoreLabel;
    // End of variables declaration//GEN-END:variables
}
