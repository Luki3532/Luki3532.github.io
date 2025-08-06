package src;
import java.io.*;
import java.util.*;

public class ClientManager {
    private static final String CSV_FILE = "../data/clients.csv";
    private static final String[] HEADERS = {"Name", "Password"};
    private static List<Client> clients = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        loadClients();
        while (true) {
            System.out.println("\n--- Client Manager Menu ---");
            System.out.println("1. List clients");
            System.out.println("2. Add client");
            System.out.println("3. Edit client");
            System.out.println("4. Delete client");
            System.out.println("5. Save and Exit");
            System.out.print("Choose an option: ");
            String choice = scanner.nextLine();
            switch (choice) {
                case "1":
                    listClients();
                    break;
                case "2":
                    addClient();
                    break;
                case "3":
                    editClient();
                    break;
                case "4":
                    deleteClient();
                    break;
                case "5":
                    saveClients();
                    System.out.println("Saved. Exiting.");
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private static void loadClients() {
        clients.clear();
        File file = new File(CSV_FILE);
        if (!file.exists()) {
            System.out.println("No CSV file found. A new one will be created on save.");
            return;
        }
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            boolean first = true;
            while ((line = br.readLine()) != null) {
                if (first) { first = false; continue; } // skip header
                String[] parts = line.split(",", -1);
                if (parts.length >= 2) {
                    clients.add(new Client(parts[0].trim(), parts[1].trim()));
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading CSV: " + e.getMessage());
        }
    }

    private static void saveClients() {
        try (PrintWriter pw = new PrintWriter(new FileWriter(CSV_FILE))) {
            pw.println(String.join(",", HEADERS));
            clients.sort(Comparator.comparing(c -> c.name.toLowerCase()));
            for (Client c : clients) {
                pw.println(c.name + "," + c.password);
            }
        } catch (IOException e) {
            System.out.println("Error writing CSV: " + e.getMessage());
        }
    }

    private static void listClients() {
        if (clients.isEmpty()) {
            System.out.println("No clients found.");
            return;
        }
        System.out.println("\nClients:");
        for (int i = 0; i < clients.size(); i++) {
            System.out.printf("%d. %s\n", i + 1, clients.get(i).name);
        }
    }

    private static void addClient() {
        System.out.print("Enter client name: ");
        String name = scanner.nextLine().trim();
        if (name.isEmpty()) {
            System.out.println("Name cannot be empty.");
            return;
        }
        for (Client c : clients) {
            if (c.name.equalsIgnoreCase(name)) {
                System.out.println("Client already exists.");
                return;
            }
        }
        System.out.print("Enter password: ");
        String password = scanner.nextLine().trim();
        if (password.isEmpty()) {
            System.out.println("Password cannot be empty.");
            return;
        }
        clients.add(new Client(name, password));
        System.out.println("Client added.");
    }

    private static void editClient() {
        listClients();
        if (clients.isEmpty()) return;
        System.out.print("Enter client number to edit: ");
        int idx = getIntInput(1, clients.size());
        if (idx == -1) return;
        Client c = clients.get(idx - 1);
        System.out.print("Edit name (leave blank to keep '" + c.name + "'): ");
        String newName = scanner.nextLine().trim();
        if (!newName.isEmpty()) {
            for (Client other : clients) {
                if (other != c && other.name.equalsIgnoreCase(newName)) {
                    System.out.println("Another client with that name exists.");
                    return;
                }
            }
            c.name = newName;
        }
        System.out.print("Edit password (leave blank to keep current): ");
        String newPass = scanner.nextLine().trim();
        if (!newPass.isEmpty()) {
            c.password = newPass;
        }
        System.out.println("Client updated.");
    }

    private static void deleteClient() {
        listClients();
        if (clients.isEmpty()) return;
        System.out.print("Enter client number to delete: ");
        int idx = getIntInput(1, clients.size());
        if (idx == -1) return;
        Client c = clients.get(idx - 1);
        System.out.print("Are you sure you want to delete '" + c.name + "'? (y/n): ");
        String confirm = scanner.nextLine().trim().toLowerCase();
        if (confirm.equals("y")) {
            clients.remove(idx - 1);
            System.out.println("Client deleted.");
        } else {
            System.out.println("Delete cancelled.");
        }
    }

    private static int getIntInput(int min, int max) {
        String input = scanner.nextLine().trim();
        try {
            int val = Integer.parseInt(input);
            if (val < min || val > max) {
                System.out.println("Invalid number.");
                return -1;
            }
            return val;
        } catch (NumberFormatException e) {
            System.out.println("Invalid input.");
            return -1;
        }
    }

    static class Client {
        String name;
        String password;
        Client(String name, String password) {
            this.name = name;
            this.password = password;
        }
    }
}
