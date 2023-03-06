package phoneBook.code;



import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(12345)) {
            System.out.println("Server started, waiting for connection....");
            Socket socket = serverSocket.accept();
            System.out.println("Client connected to server");
            DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
            DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());

            PhoneBook cb = new PhoneBook();

            while (true) {
                String clientRequest = dataInputStream.readUTF();
                if (clientRequest.equals("0")) {
                    break;
                }

                else if (clientRequest.equals("1")) {
                    dataOutputStream.writeUTF("Name added");
                    String name = dataInputStream.readUTF();
                    String outputFile = "phoneBook/code/phone_book.txt";
                    String inputFile = dataInputStream.readUTF();
                    try(FileOutputStream output = new FileOutputStream(outputFile);
                        FileInputStream input = new FileInputStream(inputFile)){
                        byte[] buffer = new byte[64000];
                        while (input.available() > 0){
                            int real = input.read(buffer);
                            output.write(buffer, 0, real);
                        }
                    }
                    dataOutputStream.writeUTF("Phone number added");
                    int phone = Integer.parseInt(dataInputStream.readUTF());
                    String outputPhone = "phoneBook/code/phone_book.txt";
                    String inputPhone = dataInputStream.readUTF();
                    try(FileOutputStream output = new FileOutputStream(outputPhone);
                        FileInputStream inputP = new FileInputStream(inputPhone)){
                        byte[] buffer = new byte[64000];
                        while (inputP.available() > 0){
                            int real = inputP.read(buffer);
                            output.write(buffer, 0, real);
                        }
                    }

                    dataOutputStream.writeUTF(cb.addContact(name, phone));

                } else if (clientRequest.equals("2")) {
                    dataOutputStream.writeUTF("Name: ");
                    String name = dataInputStream.readUTF();
                    dataOutputStream.writeUTF("Phone number: ");
                    int phone = Integer.parseInt(dataInputStream.readUTF());

                    dataOutputStream.writeUTF(cb.delFromList(name, phone));

                } else if (clientRequest.equals("3")) {
                    dataOutputStream.writeUTF("Phone book: ");
                    dataOutputStream.writeUTF(cb.getList());
                    String inputFile = "phoneBook/code/phone_book.txt";
                    try(FileReader in = new FileReader(inputFile);
                        BufferedReader reader = new BufferedReader(in)){
                        while (reader.ready()){
                            String line = reader.readLine();
                            System.out.println(line);
                        }
                    }

                } else {
                    dataOutputStream.writeUTF("Incorrect request.");
                }


            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}