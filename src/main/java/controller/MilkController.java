package controller;

import model.Milk;
import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class MilkController {

    private String csvFile;

    // Constructor รับ path ของไฟล์ CSV
    public MilkController(String csvFile) {
        this.csvFile = csvFile;
    }

    // Method สำหรับค้นหา Milk โดยใช้ Id (ตอนนี้ id เป็น String)
    public Milk findMilkById(String milkId) {
        // อ่านข้อมูลจากไฟล์ CSV เพื่อค้นหา Milk ตาม Id
        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] rowData = line.split(",");
                if (rowData[0].equals(milkId)) {  // เปรียบเทียบ id เป็น String
                    // ถ้าพบข้อมูลที่ตรงกับ milkId จะสร้าง Milk object และ return
                    return new Milk(
                        Integer.parseInt(rowData[0]),  // แปลง id เป็น int
                        Integer.parseInt(rowData[1])   // bottle
                    );
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error reading CSV file.", "Error", JOptionPane.ERROR_MESSAGE);
        }

        // ถ้าไม่พบข้อมูล milk ตาม id ที่ระบุ ให้ return null
        return null;
    }

    // Method สำหรับอัปเดตข้อมูลขวดนม (bottle) ในไฟล์ CSV โดยใช้ Id
    public void updateMilkBottle(Milk milk) {
        List<String[]> csvData = new ArrayList<>();

        // อ่านข้อมูลทั้งหมดจากไฟล์ CSV
        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            String line;
            boolean isHeader = true;
            while ((line = br.readLine()) != null) {
                if (isHeader) {
                    // ข้ามบรรทัดแรกซึ่งเป็น header
                    isHeader = false;
                    csvData.add(line.split(","));  // เก็บ header ไว้
                    continue;
                }

                String[] rowData = line.split(",");
                if (Integer.parseInt(rowData[0]) == milk.getId()) {  // เปรียบเทียบ id
                    // อัปเดตข้อมูลขวดนม
                    rowData[1] = String.valueOf(milk.getBottle());
                }
                csvData.add(rowData);
            }
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error reading CSV file.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // เขียนข้อมูลทั้งหมดกลับไปที่ไฟล์ CSV
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(csvFile))) {
            for (String[] row : csvData) {
                bw.write(String.join(",", row));
                bw.newLine();  // เขียนแต่ละแถวใหม่
            }
            System.out.println("Milk CSV file updated successfully.");
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error writing to CSV file.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Method สำหรับการเพิ่มจำนวน bottle ใน milk
    public void increaseBottle(Milk milk) {
        milk.setBottle(milk.getBottle() + 1); // เพิ่มจำนวนขวดนม
        updateMilkBottle(milk);               // อัปเดตข้อมูลในไฟล์ CSV
    }

    // Method ใหม่สำหรับดึงข้อมูล milk ทั้งหมดจากไฟล์ CSV
    public List<Milk> getAllMilks() {
        List<Milk> milks = new ArrayList<>();

        // อ่านข้อมูลทั้งหมดจากไฟล์ CSV
        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            String line;
            boolean isHeader = true;
            while ((line = br.readLine()) != null) {
                if (isHeader) {
                    isHeader = false;  // ข้ามบรรทัดแรกที่เป็น header
                    continue;
                }

                String[] rowData = line.split(",");
                Milk milk = new Milk(
                    Integer.parseInt(rowData[0]),  // id
                    Integer.parseInt(rowData[1])   // bottle
                );
                milks.add(milk);  // เพิ่ม milk object ในลิสต์
            }
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error reading milk CSV file.", "Error", JOptionPane.ERROR_MESSAGE);
        }

        return milks;  // คืนค่าลิสต์ milk ทั้งหมด
    }
}