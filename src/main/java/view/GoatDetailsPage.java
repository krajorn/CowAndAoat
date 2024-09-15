package view;

import javax.swing.*;

public class GoatDetailsPage extends javax.swing.JFrame {

    public GoatDetailsPage() {
        initComponents();
    }

    private void initComponents() {

        JLabel goatLabel = new JLabel();
        JButton closeButton = new JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        goatLabel.setText("GOAT!!");
        goatLabel.setFont(new java.awt.Font("Helvetica Neue", 1, 48)); 
        goatLabel.setHorizontalAlignment(SwingConstants.CENTER);

        closeButton.setText("ไล่ออก");
        closeButton.addActionListener(evt -> closeButtonActionPerformed(evt));

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(100, 100, 100)
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                        .addComponent(goatLabel, GroupLayout.PREFERRED_SIZE, 300, GroupLayout.PREFERRED_SIZE)
                        .addComponent(closeButton, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE))
                    .addContainerGap(100, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(50, 50, 50)
                    .addComponent(goatLabel, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
                    .addGap(50, 50, 50)
                    .addComponent(closeButton)
                    .addContainerGap(100, Short.MAX_VALUE))
        );

        pack();
    }

    // ปรับปรุงให้กลับไปหน้า landingPage เพราะ ก็ยังเนียนอยู่ในฝูงวัวอยู่ดี
    private void closeButtonActionPerformed(java.awt.event.ActionEvent evt) {
        new landingPage().setVisible(true); // เปิดหน้า landingPage
        this.dispose();  // ปิดหน้าปัจจุบัน (GoatDetailsPage)
    }

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(() -> {
            new GoatDetailsPage().setVisible(true);
        });
    }
}