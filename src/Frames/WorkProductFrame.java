package Frames;

import Models.ProductTableModel;
import back.Product;
import back.Storage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WorkProductFrame extends JFrame {

    JTextField t1;
    JButton sellButton,buyButton;
    String name;
    public WorkProductFrame(String name){
        this.name = name;
        setTitle("Trade product "+name);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(300, 400);
        init();
        setLocationRelativeTo(null);
    }
    void init(){
        t1 = new JTextField();
        sellButton = new JButton("Sell");
        buyButton = new JButton("Buy");
        buyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(tryTrade(true))
                    dispose();
            }
        });
        sellButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(tryTrade(false))
                    dispose();
            }
        });

        setLayout(new BorderLayout());
        JPanel jp1 = new JPanel(new GridLayout(1,1));
        jp1.add(new JLabel("Quantity: "));
        jp1.add(t1);

        add(jp1, BorderLayout.NORTH);
        add(buyButton, BorderLayout.CENTER);
        add(sellButton, BorderLayout.SOUTH);
    }
     boolean tryTrade(boolean bought){
        String quantity = t1.getText();
        int q;
        try{
            q = Integer.parseInt(quantity);
            if(!bought) {
                if(q > Storage.getInstance().findProduct(name).getQuantityInStock()){
                    throw new SellTooMuchException();
                }
                q = -q;
            }
            ProductTableModel.tradeProduct(name, q);
            return true;
        } catch (NumberFormatException e){
            JOptionPane.showMessageDialog(null,"Not an integer");
        } catch (SellTooMuchException k){
            JOptionPane.showMessageDialog(null,"There is not so much to sell");
        } catch (Exception ign){}
        return false;
    }
    public static void createAndShow(String name){
        WorkProductFrame fr = new WorkProductFrame(name);
        fr.setVisible(true);
    }
    class SellTooMuchException extends Exception{ }
}
