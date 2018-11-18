package es.ulpgc.eii.p1;

import java.util.Arrays;

/**
 * Group object extending contact. Has array of contacts and methods for working with contacts
 */
public class Group extends Contact {

    /**
     * Array of contacts
     */
    private Contact[] contacts;

    /**
     * Create new Group with given name and initialize empty array of contacts
     * @param name Name of group
     */
    public Group(String name) {
        super(name);
        contacts = new Contact[]{};
    }

    /**
     * For each contact in group send SMS to contact number
     * (if contact instanceof Group, method is called recursively)
     * @param text Text of SMS
     */
    @Override
    public void sendSMS(String text) {
        for (Contact contact : contacts) {
            contact.sendSMS(text);
        }
    }

    /**
     * Check if contact ID is member of this group.
     * Method is called recursively for group objects
     * @param id ID of contact
     * @return True if contact is member of the group
     */
    public boolean isMember(int id) {
        if (this.getId() == id) return true;
        for (Contact contact : contacts) {
            if (contact.getId() == id) return true;
            if (contact instanceof Group) {
                if (((Group) contact).isMember(id)) return true;
            }
        }
        return false;
    }

    /**
     * Add contact to group. If contact is already in group, don't add it.
     * @param contact Contact object to add to group
     * @return True, if contact is added, false, if contact was already in the group.
     */
    public boolean add(Contact contact) {
        if (isMember(contact.getId())) return false;

        contacts = Arrays.copyOf(contacts, contacts.length + 1);
        contacts[contacts.length - 1] = contact;

        return true;
    }

    /**
     * Remove contact from group. If contact was not found in group (not recursively), return false.
     * @param id ID of contact to remove
     * @return True, if contact was found and removed, false, if contact was not in the group.
     */
    public boolean remove(int id) {
        for(int i = 0; i < contacts.length; i++){
            if(contacts[i].getId() == id){
                for(int j = i; j < contacts.length - 1; j++){
                    contacts[j] = contacts[j+1];
                }
                contacts = Arrays.copyOf(contacts, contacts.length - 1);
                return true;
            }
        }
        return false;

    }

    /**
     * Getter for array of contacts in group (not recursively)
     * @return Array of contacts in group
     */
    public Contact[] getContacts() {
       return Arrays.copyOf(contacts, contacts.length);
    }

    /**
     * String representation of Group object. Contains name of group and list of persons.
     * @return String representation
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.getName()).append("\n");
        for (Contact contact : contacts) {
            sb.append(contact.toString()).append("\n");
        }
        return sb.toString();
    }
}
