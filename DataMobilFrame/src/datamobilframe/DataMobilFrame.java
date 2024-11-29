package datamobilframe;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class DataMobilFrame extends JFrame {
    private JTextField txtIdMobil, txtMerk, txtTahun, txtHarga;
    private DefaultTableModel tableModel;
    private JTable table;

    // Koneksi Database
    private Connection conn;
    private PreparedStatement stmt;
    private ResultSet rs;

    public DataMobilFrame() {
        connectToDatabase();

        setTitle("CRUD Data Mobil");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Panel Input
        JPanel inputPanel = new JPanel(new GridLayout(4, 2, 10, 10));
        inputPanel.setBorder(BorderFactory.createTitledBorder("Input Data Mobil"));

        inputPanel.add(new JLabel("ID Mobil:"));
        txtIdMobil = new JTextField();
        inputPanel.add(txtIdMobil);

        inputPanel.add(new JLabel("Merk:"));
        txtMerk = new JTextField();
        inputPanel.add(txtMerk);

        inputPanel.add(new JLabel("Tahun:"));
        txtTahun = new JTextField();
        inputPanel.add(txtTahun);

        inputPanel.add(new JLabel("Harga:"));
        txtHarga = new JTextField();
        inputPanel.add(txtHarga);

        add(inputPanel, BorderLayout.NORTH);

        // Table
        tableModel = new DefaultTableModel(new String[]{"ID Mobil", "Merk", "Tahun", "Harga"}, 0);
        table = new JTable(tableModel);
        add(new JScrollPane(table), BorderLayout.CENTER);

        // Panel Tombol
        JPanel buttonPanel = new JPanel(new FlowLayout());
        JButton btnCreate = new JButton("Create");
        JButton btnRead = new JButton("Read");
        JButton btnUpdate = new JButton("Update");
        JButton btnDelete = new JButton("Delete");
        buttonPanel.add(btnCreate);
        buttonPanel.add(btnRead);
        buttonPanel.add(btnUpdate);
        buttonPanel.add(btnDelete);
        add(buttonPanel, BorderLayout.SOUTH);

        // Tombol CRUD
        btnCreate.addActionListener(e -> createData());
        btnRead.addActionListener(e -> loadTableData());
        btnUpdate.addActionListener(e -> updateData());
        btnDelete.addActionListener(e -> deleteData());

        loadTableData();
    }

    private void connectToDatabase() {
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/ShowroomMobil", "root", "");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Koneksi database gagal: " + ex.getMessage());
            System.exit(1);
        }
    }

    private void loadTableData() {
        try {
            tableModel.setRowCount(0);
            String sql = "SELECT * FROM Mobil";
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();
            while (rs.next()) {
                tableModel.addRow(new Object[]{
                        rs.getInt("Id_Mobil"),
                        rs.getString("Merk"),
                        rs.getInt("Tahun"),
                        rs.getLong("Harga")
                });
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
        }
    }

    private void createData() {
        String id = txtIdMobil.getText();
        String merk = txtMerk.getText();
        String tahun = txtTahun.getText();
        String harga = txtHarga.getText();

        if (id.isEmpty() || merk.isEmpty() || tahun.isEmpty() || harga.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Semua field harus diisi!");
            return;
        }

        try {
            String sql = "INSERT INTO Mobil (Id_Mobil, Merk, Tahun, Harga) VALUES (?, ?, ?, ?)";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, Integer.parseInt(id));
            stmt.setString(2, merk);
            stmt.setInt(3, Integer.parseInt(tahun));
            stmt.setLong(4, Long.parseLong(harga));
            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Data berhasil ditambahkan!");
            loadTableData();
            clearFields();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
        }
    }

    private void updateData() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(null, "Pilih baris yang ingin diupdate!");
            return;
        }

        String id = txtIdMobil.getText();
        String merk = txtMerk.getText();
        String tahun = txtTahun.getText();
        String harga = txtHarga.getText();

        try {
            String sql = "UPDATE Mobil SET Merk = ?, Tahun = ?, Harga = ? WHERE Id_Mobil = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, merk);
            stmt.setInt(2, Integer.parseInt(tahun));
            stmt.setLong(3, Long.parseLong(harga));
            stmt.setInt(4, Integer.parseInt(id));
            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Data berhasil diupdate!");
            loadTableData();
            clearFields();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
        }
    }

    private void deleteData() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(null, "Pilih baris yang ingin dihapus!");
            return;
        }

        String id = tableModel.getValueAt(selectedRow, 0).toString();

        try {
            String sql = "DELETE FROM Mobil WHERE Id_Mobil = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, Integer.parseInt(id));
            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Data berhasil dihapus!");
            loadTableData();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
        }
    }

    private void clearFields() {
        txtIdMobil.setText("");
        txtMerk.setText("");
        txtTahun.setText("");
        txtHarga.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new DataMobilFrame().setVisible(true));
    }
}
