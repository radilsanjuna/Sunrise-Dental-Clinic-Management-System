/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;

import model.Bill;

import java.io.File;
import java.io.FileOutputStream;

public class ReceiptPDF {
      public static File generateReceipt(Bill bill) throws Exception {

        String fileName =
                bill.getBillNumber() + ".pdf";

        String filePath =
                System.getProperty("user.home")
                + File.separator
                + "Documents"
                + File.separator
                + fileName;

        File file = new File(filePath);

        Document document = new Document();

        PdfWriter.getInstance(
                document,
                new FileOutputStream(file)
        );

        document.open();

        // Clinic name
        Font titleFont = new Font(
                Font.HELVETICA,
                18,
                Font.BOLD
        );

        Paragraph title =
                new Paragraph(
                        "SUNRISE DENTAL CLINIC",
                        titleFont
                );

        title.setAlignment(Element.ALIGN_CENTER);

        document.add(title);

        Paragraph address =
                new Paragraph("Colombo");

        address.setAlignment(Element.ALIGN_CENTER);

        document.add(address);

        document.add(new Paragraph(" "));

        // Bill information
        document.add(
                new Paragraph(
                        "Bill Number: "
                        + bill.getBillNumber()
                )
        );

        document.add(
                new Paragraph(
                        "Appointment Number: "
                        + bill.getAppointmentNumber()
                )
        );

        document.add(
                new Paragraph(
                        "Bill Date: "
                        + bill.getBillDate()
                )
        );

        document.add(new Paragraph(" "));

        // Patient information
        document.add(
                new Paragraph(
                        "Patient Name: "
                        + bill.getPatientName()
                )
        );

        document.add(
                new Paragraph(
                        "Phone: "
                        + bill.getPhoneNumber()
                )
        );

        document.add(
                new Paragraph(
                        "Dentist: "
                        + bill.getDentistName()
                )
        );

        document.add(
                new Paragraph(
                        "Treatment: "
                        + bill.getTreatmentName()
                )
        );

        document.add(new Paragraph(" "));

        // Appointment information
        document.add(
                new Paragraph(
                        "Appointment Date: "
                        + bill.getAppointmentDate()
                )
        );

        document.add(
                new Paragraph(
                        "Appointment Time: "
                        + bill.getAppointmentTime()
                )
        );

        document.add(new Paragraph(" "));

        // Billing information
        document.add(
                new Paragraph(
                        "Treatment Cost: Rs. "
                        + bill.getTreatmentCost()
                )
        );

        document.add(
                new Paragraph(
                        "Consultation Fee: Rs. "
                        + bill.getConsultationFee()
                )
        );

        document.add(
                new Paragraph(
                        "----------------------------------------"
                )
        );

        Font totalFont = new Font(
                Font.HELVETICA,
                14,
                Font.BOLD
        );

        document.add(
                new Paragraph(
                        "TOTAL: Rs. "
                        + bill.getTotalAmount(),
                        totalFont
                )
        );

        document.add(
                new Paragraph(
                        "Payment Status: "
                        + bill.getPaymentStatus()
                )
        );

        document.add(new Paragraph(" "));

        Paragraph thankYou =
                new Paragraph(
                        "Thank you for visiting Sunrise Dental Clinic."
                );

        thankYou.setAlignment(Element.ALIGN_CENTER);

        document.add(thankYou);

        document.close();

        return file;
    }
}
