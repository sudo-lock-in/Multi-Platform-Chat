import java.io.*;
import java.net.*;
import java.util.*;

public class ConsoleClient implements Runnable {
        private Socket socket = null;
        private Scanner scnr = null;

    public ConsoleClient(Socket socket) throws IOException {
        this.socket = socket;
        this.scnr = new Scanner(new InputStreamReader(socket.getInputStream()));
    }

    @Override
        public void run() {
        try {
            String serverMessage;
            while ((serverMessage = scnr.nextLine()) != null) {
                System.out.println("Server: " + serverMessage);
            }
        } catch (Exception e) {
            System.out.println("Lost connection to server.");
            }
        }
        public static void main(String[] args) throws IOException {
        System.out.println("Connecting to server...");
        try {
            Socket socket = new Socket("127.0.0.1", 59001);
            ConsoleClient task = new ConsoleClient(socket);
            Thread client = new Thread(task);
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            Scanner scnr = new Scanner(System.in);

            client.start();
            System.out.println("Connected to server. Type your messages below:");
            while (true) {
                if (scnr.hasNextLine()) {
                    String input = scnr.nextLine();
                    out.println(input);
                }
            }
        } catch (IOException e) {
                System.out.println("Failed to connect.");
            }
        }
    }
