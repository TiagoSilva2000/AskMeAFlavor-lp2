/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.LP2.view.pages;

import com.LP2.app.Session;
import com.LP2.controllers.LoginVV;
import com.LP2.database.users.ClientController;
import com.placeholder.PlaceHolder;
import java.awt.Color;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.SwingConstants;

/**
 *
 * @author evelyn.ferreira
 */
public class Register extends javax.swing.JFrame {

    /**
     * Creates new form register
     */
    public Register() {
        initComponents();
        this.getContentPane().setBackground(Color.decode("14027569"));

        PlaceHolder placeHolderName = new PlaceHolder(registerNameTXT, Color.white, Color.white, "Aqui vai seu nome:D", false, "Verdana", 14);
        PlaceHolder placeHolderPassword = new PlaceHolder(registerPasswordTXT, Color.white, Color.white, "Aqui vai a sua senha :-)", false, "Verdana", 14);
        PlaceHolder placeHolderConfirmPassword = new PlaceHolder(registerConfirmPasswordTXT, Color.white, Color.white, "E aqui é só repetir a senha :))", false, "Verdana", 14);
        PlaceHolder placeHolderEmail = new PlaceHolder(registerEmailTXT, Color.white, Color.white, "E aqui seu melhor endereço de Email :P", false, "Verdana", 14);

        registerNameTXT.setFont(new Font("Verdana", 0, 14));
        registerNameTXT.setHorizontalAlignment(SwingConstants.CENTER);
        registerNameTXT.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.white));
        registerNameTXT.setBackground(Color.decode("14027569"));
        registerNameTXT.setForeground(Color.white);
        registerNameTXT.setCaretColor(Color.white);

        registerPhoneTXT.setFont(new Font("Verdana", 0, 14));
        registerPhoneTXT.setHorizontalAlignment(SwingConstants.CENTER);
        registerPhoneTXT.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.white));
        registerPhoneTXT.setBackground(Color.decode("14027569"));
        registerPhoneTXT.setForeground(Color.white);
        registerPhoneTXT.setCaretColor(Color.white);

        registerEmailTXT.setFont(new Font("Verdana", 0, 14));
        registerEmailTXT.setHorizontalAlignment(SwingConstants.CENTER);
        registerEmailTXT.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.white));
        registerEmailTXT.setBackground(Color.decode("14027569"));
        registerEmailTXT.setForeground(Color.white);
        registerEmailTXT.setCaretColor(Color.white);

        registerCodeTXT.setFont(new Font("Verdana", 0, 14));
        registerCodeTXT.setHorizontalAlignment(SwingConstants.CENTER);
        registerCodeTXT.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.white));
        registerCodeTXT.setBackground(Color.decode("14027569"));
        registerCodeTXT.setForeground(Color.white);
        registerCodeTXT.setCaretColor(Color.white);

        char value = (char)0;
        registerConfirmPasswordTXT.setEchoChar(value);
        registerConfirmPasswordTXT.setFont(new Font("Verdana", 0, 14));
        registerConfirmPasswordTXT.setHorizontalAlignment(SwingConstants.CENTER);
        registerConfirmPasswordTXT.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.white));
        registerConfirmPasswordTXT.setBackground(Color.decode("14027569"));
        registerConfirmPasswordTXT.setForeground(Color.white);
        registerConfirmPasswordTXT.setCaretColor(Color.white);

        registerPasswordTXT.setEchoChar(value);
        registerPasswordTXT.setFont(new Font("Verdana", 0, 14));
        registerPasswordTXT.setHorizontalAlignment(SwingConstants.CENTER);
        registerPasswordTXT.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.white));
        registerPasswordTXT.setBackground(Color.decode("14027569"));
        registerPasswordTXT.setForeground(Color.white);
        registerPasswordTXT.setCaretColor(Color.white);

        wrongPasswordLBL.setVisible(false);
        emptyDataLBL.setVisible(false);

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        registerBackBTN = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        registerNameTXT = new javax.swing.JTextField();
        registerPhoneTXT = new javax.swing.JFormattedTextField();
        registerCodeTXT = new javax.swing.JFormattedTextField();
        registerPasswordTXT = new javax.swing.JPasswordField();
        registerConfirmPasswordTXT = new javax.swing.JPasswordField();
        registerEmailTXT = new javax.swing.JTextField();
        wrongPasswordLBL = new javax.swing.JLabel();
        emptyDataLBL = new javax.swing.JLabel();
        registerBTN = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Cadastro La Cocina Bristrô");
        setResizable(false);

        registerBackBTN.setBackground(new java.awt.Color(38, 70, 27));
        registerBackBTN.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        registerBackBTN.setForeground(new java.awt.Color(255, 255, 255));
        registerBackBTN.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/back16.png"))); // NOI18N
        registerBackBTN.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                registerBackBTNMouseClicked(evt);
            }
        });

        jPanel1.setOpaque(false);

        registerNameTXT.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        registerNameTXT.setSelectionColor(new java.awt.Color(214, 11, 49));
        registerNameTXT.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                registerNameTXTFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                registerNameTXTFocusLost(evt);
            }
        });

        try {
            registerPhoneTXT.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("(##)#####-####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        registerPhoneTXT.setToolTipText("Seu melhor telefone");
        registerPhoneTXT.setSelectionColor(new java.awt.Color(214, 11, 49));
        registerPhoneTXT.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                registerPhoneTXTFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                registerPhoneTXTFocusLost(evt);
            }
        });

        try {
            registerCodeTXT.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("###.###.###-##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        registerCodeTXT.setToolTipText("Seu CPF vai aqui");
        registerCodeTXT.setSelectionColor(new java.awt.Color(214, 11, 49));
        registerCodeTXT.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                registerCodeTXTFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                registerCodeTXTFocusLost(evt);
            }
        });

        registerPasswordTXT.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        registerPasswordTXT.setToolTipText("Sua senha");
        registerPasswordTXT.setDisabledTextColor(new java.awt.Color(255, 0, 0));
        registerPasswordTXT.setSelectionColor(new java.awt.Color(214, 11, 49));
        registerPasswordTXT.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                registerPasswordTXTFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                registerPasswordTXTFocusLost(evt);
            }
        });
        registerPasswordTXT.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                           registerPasswordTXTKeyTyped(evt);
                       }
        });
        registerConfirmPasswordTXT.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        registerConfirmPasswordTXT.setToolTipText("Confirmar senha");
        registerConfirmPasswordTXT.setSelectionColor(new java.awt.Color(214, 11, 49));
        registerConfirmPasswordTXT.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                registerConfirmPasswordTXTFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                registerConfirmPasswordTXTFocusLost(evt);
            }
        });

        registerConfirmPasswordTXT.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                           registerConfirmPasswordTXTKeyTyped(evt);
                       }
        });
        registerEmailTXT.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        registerEmailTXT.setSelectionColor(new java.awt.Color(214, 11, 49));
        registerEmailTXT.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                registerEmailTXTFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                registerEmailTXTFocusLost(evt);
            }
        });

        wrongPasswordLBL.setFont(new java.awt.Font("Verdana", 3, 14)); // NOI18N
        wrongPasswordLBL.setForeground(new java.awt.Color(255, 255, 255));
        wrongPasswordLBL.setText("*As senhas não coincidem");

        emptyDataLBL.setFont(new java.awt.Font("Verdana", 3, 14)); // NOI18N
        emptyDataLBL.setForeground(new java.awt.Color(255, 255, 255));
        emptyDataLBL.setText("*Você deve preencher todos os campos");

        registerBTN.setBackground(new java.awt.Color(38, 70, 27));
        registerBTN.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        registerBTN.setForeground(new java.awt.Color(255, 255, 255));
        registerBTN.setText("Cadastrar");
        registerBTN.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                registerBTNMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(registerCodeTXT, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(registerPhoneTXT, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(registerEmailTXT)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(registerPasswordTXT, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(62, 62, 62)
                        .addComponent(registerConfirmPasswordTXT, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(emptyDataLBL, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(registerNameTXT)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(wrongPasswordLBL)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(registerBTN, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(registerNameTXT, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(registerPasswordTXT, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(registerConfirmPasswordTXT, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(registerEmailTXT, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(registerCodeTXT, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(registerPhoneTXT, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(wrongPasswordLBL)
                    .addComponent(registerBTN, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(emptyDataLBL)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/logo.png"))); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(registerBackBTN)
                .addGap(92, 92, 92)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(22, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(19, 19, 19))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap(19, Short.MAX_VALUE)
                        .addComponent(registerBackBTN)
                        .addGap(92, 92, 92))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(37, 37, 37))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void registerPasswordTXTKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_passwordTXTKeyTyped
        registerPasswordTXT.setEchoChar('°');
    }//GEN-LAST:event_registerPasswordTXTKeyTyped

    private void registerConfirmPasswordTXTKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_passwordTXTKeyTyped
        registerConfirmPasswordTXT.setEchoChar('°');
    }//GEN-LAST:event_registerConfirmPasswordTXTKeyTyped

    private void registerBackBTNMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_registerBackBTNMouseClicked
        this.dispose();
        Login loginScreen = new Login();
        loginScreen.setVisible(true);
    }//GEN-LAST:event_registerBackBTNMouseClicked

    private void registerBTNMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_registerBTNMouseClicked
        if(!new String(registerPasswordTXT.getPassword()).equals(new String(registerConfirmPasswordTXT.getPassword()))){
            wrongPasswordLBL.setVisible(true);
        }
        final String name = registerNameTXT.getText().trim();
        final String email = registerEmailTXT.getText().trim();
        final String pass = new String(registerPasswordTXT.getPassword()).trim();
        final String passConfirm = new String(registerConfirmPasswordTXT.getPassword()).trim();
        final String phone = registerPhoneTXT.getText().trim();
        final String idCode = registerCodeTXT.getText().trim();


        if(name.isEmpty() ||
           email.isEmpty() ||
           idCode.isEmpty() ||
           phone.isEmpty() ||
           pass.isEmpty() ||
           passConfirm.isEmpty()){
                emptyDataLBL.setVisible(true);
        } else {
            Session.sigin(name, email, pass, idCode);
            LoginVV.login(name, pass);
            wrongPasswordLBL.setVisible(false);
            emptyDataLBL.setVisible(false);
            this.dispose();
            UsersAccount ua = new UsersAccount();
            ua.setVisible(true);
        }
    }//GEN-LAST:event_registerBTNMouseClicked

    private void registerNameTXTFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_registerNameTXTFocusLost
        registerNameTXT.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.white));
    }//GEN-LAST:event_registerNameTXTFocusLost

    private void registerNameTXTFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_registerNameTXTFocusGained
        registerNameTXT.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.decode("6554655")));
    }//GEN-LAST:event_registerNameTXTFocusGained

    private void registerPasswordTXTFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_registerPasswordTXTFocusGained
        registerPasswordTXT.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.decode("6554655")));

    }//GEN-LAST:event_registerPasswordTXTFocusGained

    private void registerPasswordTXTFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_registerPasswordTXTFocusLost
        registerPasswordTXT.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.white));
    }//GEN-LAST:event_registerPasswordTXTFocusLost

    private void registerConfirmPasswordTXTFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_registerConfirmPasswordTXTFocusGained
        registerConfirmPasswordTXT.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.decode("6554655")));
    }//GEN-LAST:event_registerConfirmPasswordTXTFocusGained

    private void registerConfirmPasswordTXTFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_registerConfirmPasswordTXTFocusLost
        registerConfirmPasswordTXT.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.white));
    }//GEN-LAST:event_registerConfirmPasswordTXTFocusLost

    private void registerEmailTXTFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_registerEmailTXTFocusGained
        registerEmailTXT.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.decode("6554655")));
    }//GEN-LAST:event_registerEmailTXTFocusGained

    private void registerEmailTXTFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_registerEmailTXTFocusLost
        registerPasswordTXT.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.white));
    }//GEN-LAST:event_registerEmailTXTFocusLost

    private void registerCodeTXTFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_registerCodeTXTFocusGained
        registerCodeTXT.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.decode("6554655")));
    }//GEN-LAST:event_registerCodeTXTFocusGained

    private void registerCodeTXTFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_registerCodeTXTFocusLost
        registerCodeTXT.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.white));
    }//GEN-LAST:event_registerCodeTXTFocusLost

    private void registerPhoneTXTFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_registerPhoneTXTFocusGained
        registerPhoneTXT.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.decode("6554655")));
    }//GEN-LAST:event_registerPhoneTXTFocusGained

    private void registerPhoneTXTFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_registerPhoneTXTFocusLost
        registerPhoneTXT.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.white));
    }//GEN-LAST:event_registerPhoneTXTFocusLost

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
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Register.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Register.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Register.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Register.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new Register().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel emptyDataLBL;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JButton registerBTN;
    private javax.swing.JButton registerBackBTN;
    private javax.swing.JFormattedTextField registerCodeTXT;
    private javax.swing.JPasswordField registerConfirmPasswordTXT;
    private javax.swing.JTextField registerEmailTXT;
    private javax.swing.JTextField registerNameTXT;
    private javax.swing.JPasswordField registerPasswordTXT;
    private javax.swing.JFormattedTextField registerPhoneTXT;
    private javax.swing.JLabel wrongPasswordLBL;
    // End of variables declaration//GEN-END:variables
}
