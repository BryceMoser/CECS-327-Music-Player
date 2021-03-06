/*
 * Created by JFormDesigner on Mon Feb 04 19:50:01 PST 2019
 */

package csulb.cecs327.Client.Views;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import csulb.cecs327.Client.Models.User;
import csulb.cecs327.Client.Services.Proxy;
import net.miginfocom.swing.*;


/**
 * @author Kevin
 */
public class RegisterDialog extends JDialog {
    private Proxy proxy;
    
    public RegisterDialog(Frame owner) {
        super(owner);
        initComponents();
    }
    
    public RegisterDialog(Proxy proxy) {
        this.proxy = proxy;
        initComponents();
    }
    
    public RegisterDialog(Dialog owner) {
        super(owner);
        initComponents();
    }
    
    private void okButtonMouseClicked(MouseEvent e) {
        String userName = userNameTextField.getText();
        String password = String.valueOf(passwordTextField.getPassword());
        String email = emailTextField.getText();
        GsonBuilder gsonBuilder = new GsonBuilder();
        //gsonBuilder.registerTypeAdapter(User.class, new UserDeserializer());
        Gson gson = gsonBuilder.create();
        if (userName.equals("")) {
            JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), "Verify that the username is entered.");
        } else if (password.equals("")) {
            JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), "Verify that the passowrd is entered.");
        } else if (email.equals("")) {
            JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), "Verify that email is entered.");
        } else {
            User newUser = new User(userName, password, email, new ArrayList<>());
            proxy.synchExecution("register", new String[]{gson.toJson(newUser)});
            dispose();
        }
    }
    
 
    
    
    private void cancelButtonMouseClicked(MouseEvent e) {
        dispose();
    }
    
    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - PRAMOD REDDY CHAMALA
        dialogPane = new JPanel();
        contentPanel = new JPanel();
        label1 = new JLabel();
        userNameTextField = new JTextField();
        label2 = new JLabel();
        passwordTextField = new JPasswordField();
        label3 = new JLabel();
        emailTextField = new JTextField();
        buttonBar = new JPanel();
        okButton = new JButton();
        cancelButton = new JButton();
        
        //======== this ========
        Container contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());
        
        //======== dialogPane ========
        {
            
            // JFormDesigner evaluation mark
            dialogPane.setBorder(new javax.swing.border.CompoundBorder(
                    new javax.swing.border.TitledBorder(new javax.swing.border.EmptyBorder(0, 0, 0, 0),
                            "JFormDesigner Evaluation", javax.swing.border.TitledBorder.CENTER,
                            javax.swing.border.TitledBorder.BOTTOM, new java.awt.Font("Dialog", java.awt.Font.BOLD, 12),
                            java.awt.Color.red), dialogPane.getBorder())); dialogPane.addPropertyChangeListener(new java.beans.PropertyChangeListener(){public void propertyChange(java.beans.PropertyChangeEvent e){if("border".equals(e.getPropertyName()))throw new RuntimeException();}});
            
            dialogPane.setLayout(new BorderLayout());
            
            //======== contentPanel ========
            {
                contentPanel.setLayout(new MigLayout(
                        "insets dialog,hidemode 3",
                        // columns
                        "[fill]" +
                                "[fill]" +
                                "[fill]" +
                                "[fill]",
                        // rows
                        "[]" +
                                "[]" +
                                "[]" +
                                "[]"));
                
                //---- label1 ----
                label1.setText("Username:");
                contentPanel.add(label1, "cell 1 1");
                contentPanel.add(userNameTextField, "cell 2 1,wmin 125");
                
                //---- label2 ----
                label2.setText("Password:");
                contentPanel.add(label2, "cell 1 2");
                contentPanel.add(passwordTextField, "cell 2 2");
                
                //---- label3 ----
                label3.setText("Email:");
                contentPanel.add(label3, "cell 1 3");
                contentPanel.add(emailTextField, "cell 2 3");
            }
            dialogPane.add(contentPanel, BorderLayout.NORTH);
            
            //======== buttonBar ========
            {
                buttonBar.setLayout(new MigLayout(
                        "insets dialog,alignx right",
                        // columns
                        "[button,fill]" +
                                "[button,fill]",
                        // rows
                        null));
                
                //---- okButton ----
                okButton.setText("OK");
                okButton.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        okButtonMouseClicked(e);
                    }
                });
                buttonBar.add(okButton, "cell 0 0");
                
                //---- cancelButton ----
                cancelButton.setText("Cancel");
                cancelButton.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        cancelButtonMouseClicked(e);
                    }
                });
                buttonBar.add(cancelButton, "cell 1 0");
            }
            dialogPane.add(buttonBar, BorderLayout.SOUTH);
        }
        contentPane.add(dialogPane, BorderLayout.CENTER);
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }
    
    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - PRAMOD REDDY CHAMALA
    private JPanel dialogPane;
    private JPanel contentPanel;
    private JLabel label1;
    private JTextField userNameTextField;
    private JLabel label2;
    private JPasswordField passwordTextField;
    private JLabel label3;
    private JTextField emailTextField;
    private JPanel buttonBar;
    private JButton okButton;
    private JButton cancelButton;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
    
    
}

