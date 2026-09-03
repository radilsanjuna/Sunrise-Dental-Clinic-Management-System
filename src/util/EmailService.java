/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import java.util.Properties;

public class EmailService {

    private static final String SMTP_HOST = "smtp.gmail.com";
    private static final String SMTP_PORT = "587";

    // Your Gmail address
    private static final String SENDER_EMAIL = "radilsanjuna20010822@gmail.com";

    // Gmail App Password
    private static final String SENDER_PASSWORD = "dyxjrtbukzamrcyl";

    public static void sendAppointmentConfirmation(
            String patientEmail,
            String patientName,
            String appointmentNumber,
            String dentistName,
            String appointmentDate,
            String appointmentTime,
            String treatmentName) {

        Properties properties = new Properties();

        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", SMTP_HOST);
        properties.put("mail.smtp.port", SMTP_PORT);

        Session session = Session.getInstance(properties,
                new Authenticator() {
                    @Override
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(
                                SENDER_EMAIL,
                                SENDER_PASSWORD
                        );
                    }
                });

        try {
            Message message = new MimeMessage(session);

            message.setFrom(new InternetAddress(SENDER_EMAIL));
            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse(patientEmail)
            );

            message.setSubject(
                    "Appointment Confirmation - Sunrise Dental Clinic"
            );

            String emailBody =
                    "Dear " + patientName + ",\n\n"
                    + "Your appointment has been successfully scheduled.\n\n"
                    + "Appointment Number: " + appointmentNumber + "\n"
                    + "Dentist: " + dentistName + "\n"
                    + "Date: " + appointmentDate + "\n"
                    + "Time: " + appointmentTime + "\n"
                    + "Treatment: " + treatmentName + "\n\n"
                    + "Thank you,\n"
                    + "Sunrise Dental Clinic";

            message.setText(emailBody);

            Transport.send(message);

            System.out.println("Appointment confirmation email sent successfully.");

        } catch (MessagingException e) {
            System.out.println("Error sending email: " + e.getMessage());
        }
    }
}