package es.ulpgc.eii.p1;

public class SMSTools {

    static int uniqueId = 0;

    public static int getUniqueId() {
        return uniqueId++;
    }

    public static void sendMessage(String phoneNum, String text) {
        System.out.println("Sending SMS to " + phoneNum + ": " + text);
    }
}
