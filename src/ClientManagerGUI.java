package src;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.*;
import java.util.Comparator;
import java.util.ArrayList;
import java.util.List;

public class ClientManagerGUI extends JFrame {
    private static final String CSV_FILE = "data/clients.csv";
    private static final String[] HEADERS = {"Name", "Password", "Files"};
    private java.util.List<Client> clients = new ArrayList<>();
    private DefaultTableModel tableModel;
    private JTable table;

    public ClientManagerGUI() {
        setTitle("Client Manager");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 400);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Table
        tableModel = new DefaultTableModel(HEADERS, 0) {
            public boolean isCellEditable(int row, int column) { return false; }
        };
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        // Buttons
        JPanel buttonPanel = new JPanel();
        JButton addBtn = new JButton("Add");
        JButton editBtn = new JButton("Edit");
        JButton deleteBtn = new JButton("Delete");
        JButton saveBtn = new JButton("Save");
        JButton exitBtn = new JButton("Exit");
        buttonPanel.add(addBtn);
        buttonPanel.add(editBtn);
        buttonPanel.add(deleteBtn);
        buttonPanel.add(saveBtn);
        buttonPanel.add(exitBtn);
        add(buttonPanel, BorderLayout.SOUTH);

        // Button actions
        addBtn.addActionListener(e -> addClientDialog());
        editBtn.addActionListener(e -> editClientDialog());
        deleteBtn.addActionListener(e -> deleteClient());
        saveBtn.addActionListener(e -> saveClients());
        exitBtn.addActionListener(e -> {
            saveClients();
            System.exit(0);
        });

        loadClients();
        refreshTable();
    }

    private void loadClients() {
        clients.clear();
        File file = new File(CSV_FILE);
        if (!file.exists()) {
            JOptionPane.showMessageDialog(this, "CSV file not found: " + CSV_FILE);
            return;
        }
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            boolean first = true;
            while ((line = br.readLine()) != null) {
                if (first) { first = false; continue; }
                line = line.trim();
                if (line.isEmpty()) continue;
                String[] parts = line.split(",", -1);
                if (parts.length >= 2) {
                    String name = parts[0].trim();
                    String pass = parts[1].trim();
                    List<String> files = new ArrayList<>();
                    for (int i = 2; i < parts.length; i++) {
                        String f = parts[i].trim();
                        if (!f.isEmpty()) files.add(f);
                    }
                    if (!name.isEmpty() && !pass.isEmpty()) {
                        clients.add(new Client(name, pass, files));
                    }
                }
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error reading CSV: " + e.getMessage());
        }
        refreshTable();
    }

    private void saveClients() {
        try (PrintWriter pw = new PrintWriter(new FileWriter(CSV_FILE))) {
            pw.println(String.join(",", HEADERS));
            clients.sort(Comparator.comparing(c -> c.name.toLowerCase()));
            for (Client c : clients) {
                StringBuilder sb = new StringBuilder();
                sb.append(c.name).append(",").append(c.password);
                for (String f : c.files) {
                    sb.append(",").append(f);
                }
                pw.println(sb.toString());
            }
            JOptionPane.showMessageDialog(this, "Clients saved.");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error writing CSV: " + e.getMessage());
        }
    }

    private void refreshTable() {
        tableModel.setRowCount(0);
        for (Client c : clients) {
            String filesStr = String.join("\n", c.files);
            tableModel.addRow(new Object[]{c.name, c.password, filesStr});
        }
    }

    private void addClientDialog() {
        JTextField nameField = new JTextField();
        JTextField passField = new JTextField();
        JTextArea filesArea = new JTextArea(4, 30);
        filesArea.setLineWrap(true);
        filesArea.setWrapStyleWord(true);
        JScrollPane filesScroll = new JScrollPane(filesArea);
        Object[] fields = {"Name:", nameField, "Password:", passField, "File Paths (one per line):", filesScroll};
        int res = JOptionPane.showConfirmDialog(this, fields, "Add Client", JOptionPane.OK_CANCEL_OPTION);
        if (res == JOptionPane.OK_OPTION) {
            String name = nameField.getText().trim();
            String pass = passField.getText().trim();
            List<String> files = new ArrayList<>();
            for (String line : filesArea.getText().split("\n")) {
                String f = line.trim();
                if (!f.isEmpty()) files.add(f);
            }
            if (name.isEmpty() || pass.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Name and password cannot be empty.");
                return;
            }
            for (Client c : clients) {
                if (c.name.equalsIgnoreCase(name)) {
                    JOptionPane.showMessageDialog(this, "Client already exists.");
                    return;
                }
            }
            clients.add(new Client(name, pass, files));
            refreshTable();
        }
    }

    private void editClientDialog() {
        int row = table.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Select a client to edit.");
            return;
        }
        Client c = clients.get(row);
        JTextField nameField = new JTextField(c.name);
        JTextField passField = new JTextField(c.password);
        JTextArea filesArea = new JTextArea(4, 30);
        filesArea.setLineWrap(true);
        filesArea.setWrapStyleWord(true);
        filesArea.setText(String.join("\n", c.files));
        JScrollPane filesScroll = new JScrollPane(filesArea);
        Object[] fields = {"Name:", nameField, "Password:", passField, "File Paths (one per line):", filesScroll};
        int res = JOptionPane.showConfirmDialog(this, fields, "Edit Client", JOptionPane.OK_CANCEL_OPTION);
        if (res == JOptionPane.OK_OPTION) {
            String newName = nameField.getText().trim();
            String newPass = passField.getText().trim();
            List<String> files = new ArrayList<>();
            for (String line : filesArea.getText().split("\n")) {
                String f = line.trim();
                if (!f.isEmpty()) files.add(f);
            }
            if (newName.isEmpty() || newPass.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Name and password cannot be empty.");
                return;
            }
            for (int i = 0; i < clients.size(); i++) {
                if (i != row && clients.get(i).name.equalsIgnoreCase(newName)) {
                    JOptionPane.showMessageDialog(this, "Another client with that name exists.");
                    return;
                }
            }
            c.name = newName;
            c.password = newPass;
            c.files = files;
            refreshTable();
        }
    }

    private void deleteClient() {
        int row = table.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Select a client to delete.");
            return;
        }
        Client c = clients.get(row);
        int res = JOptionPane.showConfirmDialog(this, "Delete '" + c.name + "'?", "Confirm Delete", JOptionPane.YES_NO_OPTION);
        if (res == JOptionPane.YES_OPTION) {
            clients.remove(row);
            refreshTable();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ClientManagerGUI().setVisible(true));
    }

    static class Client {
        String name, password;
        List<String> files;
        Client(String name, String password) {
            this.name = name;
            this.password = password;
            this.files = new ArrayList<>();
        }
        Client(String name, String password, List<String> files) {
            this.name = name;
            this.password = password;
            this.files = files;
        }
    }
}
