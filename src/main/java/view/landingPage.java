package view;

import controller.CowController;
import model.Cow;
import javax.swing.JOptionPane;

public class landingPage extends javax.swing.JFrame {

    private CowController cowController;

    public landingPage() {
        initComponents();
        
        String currentDir = System.getProperty("user.dir");
        String cowFile = currentDir + "/data/cow.csv";
        String milkFile = currentDir + "/data/milk.csv";  

        cowController = new CowController(cowFile); // ระบุ path ของไฟล์ CSV
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Helvetica Neue", 0, 18)); // NOI18N
        jLabel1.setText("enter your cow code");

        jButton1.setText("enter");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(109, 109, 109)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTextField1)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(124, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1)
                .addContainerGap(115, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>                        

private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {                                         
    String cowCode = jTextField1.getText(); // รับค่า cow code จาก Text Field

    // Validation - ตรวจสอบเงื่อนไขที่กำหนด
    if (!cowCode.matches("\\d+")) {
        JOptionPane.showMessageDialog(this, "Cow code must be digits only.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
        return;
    }

    if (cowCode.length() != 8) {
        JOptionPane.showMessageDialog(this, "Cow code must be exactly 8 digits long.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
        return;
    }

    if (cowCode.startsWith("0")) {
        JOptionPane.showMessageDialog(this, "Cow code must not start with 0.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
        return;
    }

    // ถ้าผ่านการตรวจสอบทั้งหมดแล้ว ค้นหาข้อมูลใน CSV
    Cow cow = cowController.findCowByCode(cowCode); // ค้นหาข้อมูลวัวใน CSV

    if (cow != null) {
        // Log ค่า cow และ cow.getMilk() 
        System.out.println("Cow found: " + cow);
        System.out.println("Milk value: " + cow.getMilk());

        if (cow.getMilk() != 0) {
            System.out.println("Navigating to CowDetailsPage");
            CowDetailsPage cowDetailsPage = new CowDetailsPage(cow, System.getProperty("user.dir") + "/data/cow.csv", System.getProperty("user.dir") + "/data/milk.csv");
            cowDetailsPage.setVisible(true);
            this.dispose();  // ปิดหน้าปัจจุบัน
        } else {
            System.out.println("Navigating to GoatDetailsPage");
            // ถ้า cow.getMilk() เป็น null ให้เปิดหน้า GoatDetailsPage
            GoatDetailsPage goatDetailsPage = new GoatDetailsPage();
            goatDetailsPage.setVisible(true);
            this.dispose();  // ปิดหน้าปัจจุบัน
        }
    } else {
        // ถ้าไม่พบวัวใน CSV
        System.out.println("Cow with code " + cowCode + " not found.");
        JOptionPane.showMessageDialog(this, "Cow with code " + cowCode + " not found.");
    }
}

    // Variables declaration - do not modify                     
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration                   
}