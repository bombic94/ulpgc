package es.ulpgc.eii.p1;

/**
 * Abstract class representing contact (can be person or group of contacts)
 */
public abstract class Contact {

    /**
     * Name of contact
     */
    private final String name;

    /**
     * ID of contact
     */
    private final int id;

    /**
     * Create new contact with given name and generated ID
     * @param name Name of contact
     */
    public Contact(String name) {
        this.name = name;
        this.id = SMSTools.getUniqueId();
    }

    /**
     * Getter for name of contact
     * @return name of contact
     */
    public String getName() {
        return name;
    }

    /**
     * Getter for ID of contact
     * @return ID of contact
     */
    public int getId() {
        return id;
    }

    /**
     * Send SMS with given text
     * @param text Text of SMS
     */
    public abstract void sendSMS(String text);
}
