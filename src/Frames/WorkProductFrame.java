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

        setLayout(new GridLayout(2,1));
        JPanel jp1 = new JPanel(new GridLayout(1,1));
        jp1.add(new JLabel("Quantity: "));
        jp1.add(t1);

        JPanel jp2 = new JPanel(new GridLayout(1,1));
        jp2.add(buyButton);
        jp2.add(sellButton);

        add(jp1);
        add(jp2);
    }
     boolean tryTrade(boolean bought){
        String quantity = t1.getText();
        if(quantity.contains("%")){
            new ErrorFrame("'%' is an illegal symbol");
            return false;
        }
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
            new ErrorFrame("The string \"" + t1.getText() + "\" cannot be converted to an int.");
        } catch (SellTooMuchException k){
            new ErrorFrame("There is not enough to sell");
        } catch (Exception e){
            e.printStackTrace(System.err);
        }
        return false;
    }
    public static void createAndShow(String name){
        WorkProductFrame fr = new WorkProductFrame(name);
        fr.setVisible(true);
    }
    class SellTooMuchException extends Exception{ }
}
