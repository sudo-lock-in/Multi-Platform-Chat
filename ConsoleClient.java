import java.io.*;
import java.net.*;
import java.util.*;
import java.util.function.Consumer;

public class ConsoleClient implements Runnable {
    private Socket socket;
    private Scanner scnr;
    private Consumer<String> onMessageReceived;

    public ConsoleClient(Socket socket, Consumer<String> onMessageReceived) throws IOException {
        this.socket = socket;
        this.scnr = new Scanner(new InputStreamReader(socket.getInputStream()));
        this.onMessageReceived = onMessageReceived;
    }

    @Override
    public void run() {
        try {
            while (scnr.hasNextLine()) {
                String serverMessage = scnr.nextLine();
                onMessageReceived.accept(serverMessage);
            }
        } catch (Exception e) {
            onMessageReceived.accept("System: Lost connection to server.");
        }
    }

    public static void main(String[] args) throws IOException {
        System.out.println("Connecting to server...");
        try {
            Socket socket = new Socket("127.0.0.1", 59001);
            ConsoleClient task = new ConsoleClient(socket, System.out::println);
            new Thread(task).start();

            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            Scanner input = new Scanner(System.in);
            while (true) {
                if (input.hasNextLine()) out.println(input.nextLine());
            }
        } catch (IOException e) {
            System.out.println("Failed to connect.");
        }
    }
}
