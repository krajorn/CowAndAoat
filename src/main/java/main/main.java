package main;

import view.landingPage;

/**
 * Main class to start the program.
 */
public class main {
    public static void main(String[] args) {
        // เรียกใช้งานหน้า NewJFrame (View)
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new landingPage().setVisible(true);  // เปิดหน้า View (NewJFrame)
            }
        });
    }
}