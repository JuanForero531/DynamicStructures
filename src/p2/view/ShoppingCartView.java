package p2.view;


import java.util.Vector;
import javax.swing.JOptionPane;
import p2.model.Item;

public interface ShoppingCartView {
    String getItemName();

    double getItemPrice();

    void clearItemFields();

    void updateCart(Vector<Item> cart);

    void updateTotalPrice(double totalPrice);

   
    
    
}