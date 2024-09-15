package controller;


import model.Cow;
import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CowController {

    private String csvFile;

    // Constructor รับ path ไปที่ไฟล์ CSV
    public CowController(String csvFile) {
        this.csvFile = csvFile;
    }
    
    public void updateMilk(int cowId, int newMilk) {
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
                if (Integer.parseInt(rowData[0]) == cowId) {
                    // อัปเดตค่า milk ตาม cowId
                    rowData[3] = String.valueOf(newMilk);  // แก้ไขค่า milk
                }
                csvData.add(rowData);  // เก็บข้อมูลแถวทั้งหมด
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
            System.out.println("Milk value updated successfully.");
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error writing to CSV file.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Method สำหรับค้นหาวัวจากไฟล์ CSV ตามรหัส (id)
    public Cow findCowByCode(String cowCodeInput) {
        // อ่านข้อมูลจากไฟล์ CSV เพื่อค้นหา Cow ตาม id
        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            String line;
            boolean isHeader = true;
            while ((line = br.readLine()) != null) {
                if (isHeader) {
                    isHeader = false;  // ข้ามบรรทัดแรกที่เป็น header
                    continue;
                }

                String[] rowData = line.split(",");
                if (rowData[0].equals(cowCodeInput)) {
                    // ถ้าพบข้อมูลที่ตรงกับ cowCodeInput จะสร้าง Cow object และ return
                    return new Cow(
                        rowData[0],                    // id (string)
                        Integer.parseInt(rowData[1]),  // year
                        Integer.parseInt(rowData[2]),  // month
                        Integer.parseInt(rowData[3])   // milk
                    );
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error reading CSV file.", "Error", JOptionPane.ERROR_MESSAGE);
        }

        // ถ้าไม่พบข้อมูล cow ตาม id ที่ระบุ ให้ return null
        return null;
    }
}