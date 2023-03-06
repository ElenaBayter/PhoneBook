package phoneBook.code;




import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try (Socket socket = new Socket("localhost", 12345)) {
            DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
            DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
            while (true) {
                System.out.println("Choose your action: press '1' - add contact, " +
                        " '2' - delete contact, '3' - to look a phone book, '0' - exit");
                String request = scanner.nextLine();
                dataOutputStream.writeUTF(request);
                if (request.equals("0")) {
                    System.out.println("End");
                    break;
                } if (request.equals("1")){
                    System.out.println("Write a name and phone number of contact: ");
                    String contact = scanner.nextLine();
                    dataOutputStream.writeUTF(contact);
                    System.out.println("Получили сообщение от сервера: " + dataInputStream.readUTF());

                } if(request.equals("2")){
                    System.out.println("Write a name of contact: ");
                    String name = scanner.nextLine();
                    dataOutputStream.writeUTF(name);
                    System.out.println("Write a phone number: ");
                    String phone = scanner.nextLine();
                    dataOutputStream.writeUTF(phone);
                    System.out.println("Получили сообщение от сервера: " + dataInputStream.readUTF());
                } if (request.equals("3")){
                    System.out.println("Получили сообщение от сервера: " + dataInputStream.readUTF());
                } else {
                    System.out.println("Incorrect request!");
                }

            }
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();

        }
        finally{
            scanner.close();
        }
    }
}