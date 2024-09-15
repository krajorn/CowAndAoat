package view;

import controller.CowController;
import controller.MilkController;
import model.Cow;
import model.Milk;
import javax.swing.*;

public class CowDetailsPage extends javax.swing.JFrame {

    private Cow cow;  // เก็บข้อมูลของวัวที่ค้นพบ
    private String cowCsvFile;  // ไฟล์ CSV สำหรับ cow
    private String milkCsvFile;  // ไฟล์ CSV สำหรับ milk
    private CowController cowController;  // ตัวแปรสำหรับ CowController
    private MilkController milkController;  // ตัวแปรสำหรับ MilkController

    // Constructor รับ Cow object และ path ของไฟล์ CSV
    public CowDetailsPage(Cow cow, String cowCsvFile, String milkCsvFile) {
        this.cow = cow;
        this.cowCsvFile = cowCsvFile;  // path สำหรับ cow
        this.milkCsvFile = milkCsvFile;  // path สำหรับ milk
        this.cowController = new CowController(cowCsvFile);  // สร้าง CowController โดยใช้ path ของไฟล์ CSV
        this.milkController = new MilkController(milkCsvFile);  // สร้าง MilkController โดยใช้ path ของไฟล์ CSV
        initComponents();
    }

    // สร้าง UI
    private void initComponents() {

        JLabel cowInfoLabel = new JLabel();
        JButton closeButton = new JButton();
        JButton milkingButton = new JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        // จัดรูปแบบข้อความใน cowInfoLabel โดยใช้ข้อมูลจาก Cow object
        cowInfoLabel.setText(formatCowInfoAsHtml());
        cowInfoLabel.setFont(new java.awt.Font("Helvetica Neue", 0, 14));

        closeButton.setText("Close");
        closeButton.addActionListener(evt -> closeButtonActionPerformed(evt));

        // ตรวจสอบเงื่อนไขว่า cow.getMilk() เท่ากับ 4 หรือไม่
        if (cow.getMilk() != null && cow.getMilk() == 4) {
            milkingButton.setText("Milking");
            milkingButton.addActionListener(evt -> milkingButtonActionPerformed(evt));
        } else {
            milkingButton.setVisible(false);  // ซ่อนปุ่มหากเงื่อนไขไม่เป็นจริง
        }
        
                // ตรวจสอบเงื่อนไขว่า cow.getMilk() เท่ากับ 4 หรือไม่
            if (cow.getMilk() != null && cow.getMilk() == 4) {
                milkingButton.setText("Milking");
                milkingButton.addActionListener(evt -> milkingButtonActionPerformed(evt));
            } else {
                milkingButton.setVisible(false);  // ซ่อนปุ่มหากเงื่อนไขไม่เป็นจริง
            }

        // จัดการ layout ให้แสดงผลในหน้าต่าง
        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(50, 50, 50)
                    .addComponent(cowInfoLabel, GroupLayout.PREFERRED_SIZE, 300, GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(50, Short.MAX_VALUE))
                .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addContainerGap(150, Short.MAX_VALUE)
                    .addComponent(milkingButton, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(closeButton, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
                    .addGap(150, 150, 150))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(30, 30, 30)
                    .addComponent(cowInfoLabel, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 50, Short.MAX_VALUE)
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(milkingButton)
                        .addComponent(closeButton))
                    .addGap(50, 50, 50))
        );

        pack();
    }

    // Method สำหรับปิดหน้าจอ
    private void closeButtonActionPerformed(java.awt.event.ActionEvent evt) {
        this.dispose();
    }

    // Method สำหรับการทำ milking
    private void milkingButtonActionPerformed(java.awt.event.ActionEvent evt) {
        // Logic สำหรับการเพิ่ม milk ในตาราง Milk
        int milkToAdd = cow.getYear() + cow.getMonth();

        // หา milk object ที่เกี่ยวข้องใน MilkController
        Milk milk = milkController.findMilkById("1");

        // โอกาส 5% ในการลดค่านมจาก 4 เหลือ 3
        double randomChance = Math.random();  // สุ่มค่าในช่วง 0 ถึง 1
        if (randomChance < 0.05) {  // ตรวจสอบว่าค่าที่สุ่มได้น้อยกว่า 5% หรือไม่
            cow.setMilk(3);  // ลดค่า milk จาก 4 เหลือ 3
            cowController.updateMilk(Integer.parseInt(cow.getId()), 3);  // อัปเดตข้อมูลในไฟล์ CSV ของ cow
            JOptionPane.showMessageDialog(this, "Milk reduced to 3 due to random chance.");
        }

        // ถ้าเจอ milk ให้เพิ่มจำนวนขวดนมและอัปเดต
        if (milk != null) {
            milk.setBottle(milk.getBottle() + milkToAdd);
            milkController.updateMilkBottle(milk);  // อัปเดตข้อมูลในไฟล์ CSV
            JOptionPane.showMessageDialog(this, "Milking completed! Milk updated by " + milkToAdd + " bottles.");
        } else {
            JOptionPane.showMessageDialog(this, "Milk data not found for cow ID: " + cow.getId(), "Error", JOptionPane.ERROR_MESSAGE);
        }

        // อัปเดตข้อมูลที่แสดงบน UI
        initComponents();
        
        // หลังจากเสร็จสิ้นการ Milking นำกลับไปที่หน้า landingPage
            new landingPage().setVisible(true);  // เปิดหน้า landingPage
            this.dispose();  // ปิดหน้าปัจจุบัน
    }

    // Method สำหรับจัดรูปแบบข้อมูลเป็น HTML โดยใช้ข้อมูลจาก Cow object
    private String formatCowInfoAsHtml() {
        return "<html>"
                + "<h2>Cow Information</h2>"
                + "<p><strong>ID:</strong> " + cow.getId() + "</p>"
                + "<p><strong>Year:</strong> " + cow.getYear() + "</p>"
                + "<p><strong>Month:</strong> " + cow.getMonth() + "</p>"
                + "<p><strong>breast:</strong> " + cow.getMilk() + " bottles</p>"
                + "</html>";
    }
}