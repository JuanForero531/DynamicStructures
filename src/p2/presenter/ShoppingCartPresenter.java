package p2.presenter;


import java.util.Vector;
import javax.swing.SwingUtilities;
import p2.model.Item;
import p2.view.*;
public class ShoppingCartPresenter {
    private Vector<Item> cart;
    private ShoppingCartView view;

    public ShoppingCartPresenter(ShoppingCartView view) {
        cart = new Vector<>();
        this.view = view;
    }

    public void addItemToCart() {
        String name = view.getItemName();
        double price = view.getItemPrice();

        Item item = new Item(name, price);
        cart.add(item);

        view.clearItemFields();
        view.updateCart(cart);
        double totalPrice = calculateTotalPrice();
        view.updateTotalPrice(totalPrice);
    }

    private double calculateTotalPrice() {
        double totalPrice = 0;
        for (Item item : cart) {
            totalPrice += item.getPrice();
        }
        return totalPrice;
    } 
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                ShoppingCartGUI shoppingCartGUI = new ShoppingCartGUI();
            }
        });
    }
}
    




  
