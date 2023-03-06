package phoneBook.code;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.logging.Level;

import static com.sun.xml.internal.ws.spi.db.BindingContextFactory.LOGGER;

public class PhoneBook {
    private ArrayList<Contacts> list = new ArrayList<>();
    public String addContact(String name, int phone) {
        Contacts contact = new Contacts(name, phone);
            list.add(contact);
        logToFile("Successfully added contact: " + name + " " + phone);
            return "Contact added";
        }
    public String delFromList(String name, int phone) {
        boolean result = false;
        for (Contacts contact : list) {
            if (contact.getName().equals(name) && contact.getPhone() == phone) {
                list.remove(contact);
                result = true;
                break;
            }
        }
            if (result) {
                return "Contact deleted!";
            } else {
                return "Contact didn't found.";
            }
        }
    public String getList() {
        String res = "Your contacts: " + "\n";
        for (Contacts contact: list) {
                res += contact.getName() + " " + contact.getPhone() + "\n";
            }
        return res;

    }
    private static void logToFile(String message) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("src/main/java/phoneBook/code/phone_book.txt", true))) {
            writer.write(String.valueOf(LocalDateTime.now()));
            writer.write(message);
            writer.newLine();
        } catch (IOException ex) {
            LOGGER.log(Level.SEVERE, "Failed to log message to file", ex);
        }
    }
}

