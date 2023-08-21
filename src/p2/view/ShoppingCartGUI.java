package p2.view;


import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import p2.model.Item;
import p2.presenter.ShoppingCartPresenter;
import p2.view.ShoppingCartView;

public class ShoppingCartGUI extends JFrame implements ShoppingCartView {
    private JTextField nameField;
    private JTextField priceField;
    private JTextArea cartTextArea;
    private JLabel totalPriceLabel;
    private ShoppingCartPresenter presenter;

    public ShoppingCartGUI() {
        presenter = new ShoppingCartPresenter(this);
        setupUI();
    }

    private void setupUI() {
        setTitle("Shopping Cart");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel(new FlowLayout());
        JLabel nameLabel = new JLabel("Item Name:");
        nameField = new JTextField(10);
        JLabel priceLabel = new JLabel("Item Price:");
        priceField = new JTextField(10);
        JButton addButton = new JButton("Add to Cart");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                presenter.addItemToCart();
            }
        });

        inputPanel.add(nameLabel);
        inputPanel.add(nameField);
        inputPanel.add(priceLabel);
        inputPanel.add(priceField);
        inputPanel.add(addButton);

        cartTextArea = new JTextArea(10, 30);
        JScrollPane scrollPane = new JScrollPane(cartTextArea);

        totalPriceLabel = new JLabel();

        add(inputPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(totalPriceLabel, BorderLayout.SOUTH);

        pack();
        setVisible(true);
    }

    @Override
    public String getItemName() {
        return nameField.getText();
    }

    @Override
    public double getItemPrice() {
        return Double.parseDouble(priceField.getText());
    }

    @Override
    public void clearItemFields() {
        nameField.setText("");
        priceField.setText("");
    }

    @Override
    public void updateCart(Vector<Item> cart) {
        cartTextArea.setText("");
        for (Item item : cart) {
            cartTextArea.append(item.toString() + "\n");
        }
    }

    @Override
    public void updateTotalPrice(double totalPrice) {
        totalPriceLabel.setText("Total Price: $" + totalPrice);
    }
    
    public void showErrorMessage(String message) {
        JOptionPane.showMessageDialog(this, message, "Error", JOptionPane.ERROR_MESSAGE);
    }
}