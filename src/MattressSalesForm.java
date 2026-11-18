import javax.swing.*; import java.io.FileWriter; import java.io.IOException;

public class MattressSalesForm extends JFrame {


private JTextField txtCustomer;
private JTextField txtPrice;
private JComboBox<String> cmbProduct;
private JButton btnProcess;

public MattressSalesForm() {

    setTitle("Mattress Sales Application");

    setSize(400, 300);

    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    setLayout(null);

    JLabel lblCustomer = new JLabel("Customer Name:");

    lblCustomer.setBounds(30, 30, 120, 25);

    add(lblCustomer);

    txtCustomer = new JTextField();

    txtCustomer.setBounds(160, 30, 180, 25);

    add(txtCustomer);

    JLabel lblProduct = new JLabel("Product Name:");

    lblProduct.setBounds(30, 80, 120, 25);

    add(lblProduct);

    cmbProduct = new JComboBox<>();

    cmbProduct.addItem("Cozy Nights Monarch");

    cmbProduct.addItem("Restonic Barbados");

    cmbProduct.addItem("Sealy Phuket");

    cmbProduct.setBounds(160, 80, 180, 25);

    add(cmbProduct);

    JLabel lblPrice = new JLabel("Product Price:");

    lblPrice.setBounds(30, 130, 120, 25);

    add(lblPrice);

    txtPrice = new JTextField();

    txtPrice.setBounds(160, 130, 180, 25);

    add(txtPrice);

    btnProcess = new JButton("Process");

    btnProcess.setBounds(130, 190, 120, 30);

    add(btnProcess);

    btnProcess.addActionListener(e -> processSales());

    setLocationRelativeTo(null);
}

private void processSales() {

    try {

        String customer = txtCustomer.getText();

        String product = cmbProduct.getSelectedItem().toString();

        double price = Double.parseDouble(txtPrice.getText());

        if (customer.trim().isEmpty() || price <= 0) {

            JOptionPane.showMessageDialog(this,
                    "Please enter valid customer details and price.");

            return;
        }

        double vat = price * 0.15;

        double commission = price * 0.05;

        double total = price + vat + commission;

        String message = "Customer Name: " + customer
                + "\nProduct: " + product
                + "\nVAT: R " + vat
                + "\nCOMMISSION: R " + commission
                + "\nTOTAL: R " + total;

        JOptionPane.showMessageDialog(this, message);

        saveToFile(customer, product, vat, commission, total);

    } catch (NumberFormatException e) {

        JOptionPane.showMessageDialog(this,
                "Please enter a valid product price.");
    }
}

private void saveToFile(String customer, String product,
        double vat, double commission, double total) {

    try {

        FileWriter writer = new FileWriter("data.txt", true);

        writer.write("DATA LOG\n");

        writer.write("********************************\n");

        writer.write("Customer Name: " + customer + "\n");

        writer.write("Product: " + product + "\n");

        writer.write("VAT: R " + vat + "\n");

        writer.write("COMMISSION: R " + commission + "\n");

        writer.write("TOTAL: R " + total + "\n");

        writer.write("********************************\n");

        writer.close();

        JOptionPane.showMessageDialog(this,
                "Information successfully saved to data.txt");

    } catch (IOException e) {

        JOptionPane.showMessageDialog(this,
                "Error saving file: " + e.getMessage());
    }
}

public static void main(String[] args) {

    new MattressSalesForm().setVisible(true);
}
}

