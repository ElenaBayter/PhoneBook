package phoneBook.code;
import java.util.ArrayList;

public class PhoneBook {
    private ArrayList<Contacts> list = new ArrayList<>();
    public String addContact(String name, int phone) {
        Contacts contact = new Contacts(name, phone);
            list.add(contact);
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
}

