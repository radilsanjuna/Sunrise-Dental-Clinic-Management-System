package util;

public class EmailTest {

    public static void main(String[] args) {

        String testEmail = "radilsanjuna20010822@gmail.com";

        System.out.println("Testing email address: [" + testEmail + "]");

        EmailService.sendAppointmentConfirmation(
                testEmail,
                "Test Patient",
                "APP-TEST-001",
                "Dr. Silva",
                "10 September 2026",
                "10:00 AM",
                "Dental Cleaning"
        );

        System.out.println("Email test completed.");
    }
}