package es.ulpgc.eii.p1;

/**
 * Person object extending contact. Has phone number and implements method to send SMS
 */
public class Person extends Contact {

    /**
     * Phone number of contact
     */
    private final String phoneNum;

    /**
     * Create new Person with given name and phone number
     * @param name Name of contact
     * @param phoneNum Phone number of contact
     */
    public Person(String name, String phoneNum) {
        super(name);
        this.phoneNum = phoneNum;
    }

    /**
     * Send SMS to persons phone number with given text
     * @param text Text of SMS
     */
    @Override
    public void sendSMS(String text) {
        SMSTools.sendMessage(phoneNum, text);
    }

    /**
     * String representation of Person object. Contains name and phone number.
     * @return String representation
     */
    @Override
    public String toString() {
        return super.getName() + ": " + phoneNum;
    }
}
