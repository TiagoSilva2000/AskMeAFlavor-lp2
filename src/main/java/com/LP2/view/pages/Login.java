/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.LP2.view.pages;
import com.LP2.controllers.LoginVV;
import com.LP2.server.utils.Constants;
import com.placeholder.PlaceHolder;
import java.awt.event.KeyEvent;
import java.awt.Color;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.SwingConstants;


import com.LP2.view.pages.client.UsersAccount;
import com.LP2.view.pages.cook.KitchenAccount;
import com.LP2.view.pages.manager.ManagerAccount;
import com.LP2.view.pages.client.Register;

/**
 *
 * @author evelyn.ferreira
 */
public class Login extends javax.swing.JFrame {

    /**
     * Creates new form login
     */
    public Login() {
        initComponents();
        this.getContentPane().setBackground(Color.decode("14027569"));

        PlaceHolder placeHolderLogin = new PlaceHolder(loginTXT, Color.white, Color.white, "Nome de Usuário", false, "Verdana", 14);
        PlaceHolder placeHolderPassword = new PlaceHolder(passwordTXT, Color.white, Color.white, "Senha", false, "Verdana", 14);

        loginTXT.setFont(new Font("Verdana", 0, 14));
        loginTXT.setHorizontalAlignment(SwingConstants.CENTER);
        loginTXT.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.white));
        loginTXT.setBackground(Color.decode("14027569"));
        loginTXT.setForeground(Color.white);
        loginTXT.setCaretColor(Color.white);

        char value = (char)0;
        passwordTXT.setEchoChar(value);
        passwordTXT.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.white));
        passwordTXT.setFont(new Font("Verdana", 0, 14));
        passwordTXT.setBackground(Color.decode("14027569"));
        passwordTXT.setCaretColor(Color.white);
        passwordTXT.setHorizontalAlignment(SwingConstants.CENTER);
        passwordTXT.setForeground(Color.white);


       }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        loginPNL = new javax.swing.JPanel();
        loginTXT = new javax.swing.JTextField();
        passwordTXT = new javax.swing.JPasswordField();
        iconIMG = new javax.swing.JLabel();
        showMenuLBL = new javax.swing.JLabel();
        loginBTN = new javax.swing.JButton();
        registerLBL = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setAutoRequestFocus(false);
        setBackground(new java.awt.Color(255, 51, 51));
        setResizable(false);

        loginPNL.setOpaque(false);

        loginTXT.setBackground(new java.awt.Color(214, 11, 49));
        loginTXT.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        loginTXT.setForeground(new java.awt.Color(255, 255, 255));
        loginTXT.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        loginTXT.setBorder(null);
        loginTXT.setCaretColor(new java.awt.Color(255, 255, 255));
        loginTXT.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        loginTXT.setDisabledTextColor(new java.awt.Color(153, 153, 153));
        loginTXT.setMargin(new java.awt.Insets(5, 2, 2, 2));
        loginTXT.setSelectionColor(new java.awt.Color(214, 11, 49));
        loginTXT.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                loginTXTFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                loginTXTFocusLost(evt);
            }
        });

        passwordTXT.setBackground(new java.awt.Color(214, 11, 49));
        passwordTXT.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        passwordTXT.setForeground(new java.awt.Color(255, 255, 255));
        passwordTXT.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        passwordTXT.setSelectionColor(new java.awt.Color(214, 11, 49));
        passwordTXT.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                passwordTXTFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                passwordTXTFocusLost(evt);
            }
        });
        passwordTXT.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                passwordTXTKeyTyped(evt);
            }
            public void keyPressed(java.awt.event.KeyEvent evt) {
                passwordTXTKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout loginPNLLayout = new javax.swing.GroupLayout(loginPNL);
        loginPNL.setLayout(loginPNLLayout);
        loginPNLLayout.setHorizontalGroup(
            loginPNLLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(loginPNLLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(loginPNLLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(passwordTXT, javax.swing.GroupLayout.DEFAULT_SIZE, 317, Short.MAX_VALUE)
                    .addComponent(loginTXT))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        loginPNLLayout.setVerticalGroup(
            loginPNLLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(loginPNLLayout.createSequentialGroup()
                .addComponent(loginTXT, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(passwordTXT, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        iconIMG.setIcon(new javax.swing.ImageIcon((System.getProperty("user.dir") + "/assets/logo.png"))); // NOI18N

        showMenuLBL.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        showMenuLBL.setForeground(new java.awt.Color(255, 255, 255));
        showMenuLBL.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        showMenuLBL.setText("Ver cardápio");
        showMenuLBL.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        showMenuLBL.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                showMenuLBLMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                showMenuLBLMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                showMenuLBLMouseExited(evt);
            }
        });

        loginBTN.setBackground(new java.awt.Color(38, 70, 27));
        loginBTN.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        loginBTN.setForeground(new java.awt.Color(255, 255, 255));
        loginBTN.setText("Login");
        loginBTN.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                loginBTNMouseClicked(evt);
            }
        });

        registerLBL.setFont(new java.awt.Font("Verdana", 2, 12)); // NOI18N
        registerLBL.setForeground(new java.awt.Color(255, 255, 255));
        registerLBL.setText("Não tem uma conta? Crie aqui");
        registerLBL.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        registerLBL.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                registerLBLMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                registerLBLMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                registerLBLMouseExited(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(loginPNL, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(133, 133, 133)
                                .addComponent(registerLBL)
                                .addGap(55, 55, 55)
                                .addComponent(loginBTN, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(152, 152, 152)
                                .addComponent(iconIMG))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(234, 234, 234)
                                .addComponent(showMenuLBL)))
                        .addGap(0, 117, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addComponent(iconIMG)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(showMenuLBL)
                .addGap(33, 33, 33)
                .addComponent(loginPNL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(registerLBL)
                    .addComponent(loginBTN, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(72, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents
         private boolean verifyLogin(String login,String password, String value){
        return login.equals(value) && password.equals(value);
    }
    private void selectScreen(){
        LoginVV.login(loginTXT.getText(), new String(passwordTXT.getPassword()));
        if (LoginVV.getuser() == null) {
            System.out.println("ERRO DE AUTENTICAÇÃO!");
        }
        if (LoginVV.getuser().getUsertype() == Constants.getManagerCode()){
            ManagerAccount screenManager = new ManagerAccount();
            screenManager.setVisible(true);
            this.dispose();
            profileType = 1;
        }else if(LoginVV.getuser().getUsertype() == Constants.getClientCode()){
            UsersAccount screenUser = new UsersAccount();
            screenUser.setVisible(true);
            this.dispose();
            profileType = 3;
        }else if(LoginVV.getuser().getUsertype() == Constants.getCookCode()){
            KitchenAccount screenKitchen = new KitchenAccount();
            screenKitchen.setVisible(true);
            this.dispose();
            profileType = 2;
        }
    }
    private void loginBTNMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_loginBTNMouseClicked
        selectScreen();
    }//GEN-LAST:event_loginBTNMouseClicked

    private void showMenuLBLMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_showMenuLBLMouseClicked
        Menu menuScreen = new Menu();
        menuScreen.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_showMenuLBLMouseClicked

    private void showMenuLBLMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_showMenuLBLMouseEntered
        showMenuLBL.setForeground(Color.decode("6554655"));
    }//GEN-LAST:event_showMenuLBLMouseEntered

    private void showMenuLBLMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_showMenuLBLMouseExited
        showMenuLBL.setForeground(Color.white);
    }//GEN-LAST:event_showMenuLBLMouseExited

    private void passwordTXTKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_passwordTXTKeyPressed
        if(evt.getKeyCode() == KeyEvent.VK_ENTER) {
            selectScreen();
        }

    }//GEN-LAST:event_passwordTXTKeyPressed

    private void registerLBLMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_registerLBLMouseClicked
        Register registerScreen = new Register();
        registerScreen.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_registerLBLMouseClicked

    private void registerLBLMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_registerLBLMouseEntered
        registerLBL.setForeground(Color.decode("6554655"));
    }//GEN-LAST:event_registerLBLMouseEntered

    private void registerLBLMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_registerLBLMouseExited
        registerLBL.setForeground(Color.white);
    }//GEN-LAST:event_registerLBLMouseExited

    private void loginTXTFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_loginTXTFocusGained
        loginTXT.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.decode("6554655")));
    }//GEN-LAST:event_loginTXTFocusGained

    private void loginTXTFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_loginTXTFocusLost
        loginTXT.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.white));
    }//GEN-LAST:event_loginTXTFocusLost

    private void passwordTXTFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_passwordTXTFocusGained
        passwordTXT.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.decode("6554655")));
    }//GEN-LAST:event_passwordTXTFocusGained

    private void passwordTXTFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_passwordTXTFocusLost
        passwordTXT.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.white));
    }//GEN-LAST:event_passwordTXTFocusLost

    private void passwordTXTKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_passwordTXTKeyTyped
        passwordTXT.setEchoChar('°');
    }//GEN-LAST:event_passwordTXTKeyTyped

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Macintosh".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new Login().setVisible(true);
        });
    }
    public static int profileType;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel iconIMG;
    private javax.swing.JButton loginBTN;
    private javax.swing.JPanel loginPNL;
    private javax.swing.JTextField loginTXT;
    private javax.swing.JPasswordField passwordTXT;
    private javax.swing.JLabel registerLBL;
    private javax.swing.JLabel showMenuLBL;
    // End of variables declaration//GEN-END:variables
}
