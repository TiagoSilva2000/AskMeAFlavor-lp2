/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.LP2.view.pages.client;

import java.awt.Color;

/**
 *
 * @author evelyn.ferreira
 */
public class Payment extends javax.swing.JDialog {

    private static double value;
    /**
     * Creates new form payment
     * @param parent
     * @param modal
     */
    public Payment(java.awt.Frame parent, boolean modal, double nValue) {
        super(parent, modal);
        initComponents();
        value = nValue;
        this.getContentPane().setBackground(Color.decode("14027569"));
        loadProps();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel4 = new javax.swing.JLabel();
        PaymentMsgTXT = new javax.swing.JLabel();
        paymentValueTXT = new javax.swing.JLabel();
        paymentMsg2TXT = new javax.swing.JLabel();
        okButton = new javax.swing.JButton();

        jLabel4.setText("realizado com successo.");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Pagamento realizado La cocina Bistrô");

        PaymentMsgTXT.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        PaymentMsgTXT.setForeground(new java.awt.Color(255, 255, 255));
        PaymentMsgTXT.setText("Pagamento de ");

        paymentValueTXT.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        paymentValueTXT.setForeground(new java.awt.Color(255, 255, 255));
        paymentValueTXT.setText("value");

        paymentMsg2TXT.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        paymentMsg2TXT.setForeground(new java.awt.Color(255, 255, 255));
        paymentMsg2TXT.setText("realizado com successo.");

        okButton.setBackground(new java.awt.Color(30, 70, 27));
        okButton.setForeground(new java.awt.Color(255, 255, 255));
        okButton.setText("OK");
        okButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                okButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addComponent(PaymentMsgTXT)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(okButton, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(paymentValueTXT)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(paymentMsg2TXT)))
                .addContainerGap(32, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(108, 108, 108)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(PaymentMsgTXT)
                    .addComponent(paymentValueTXT)
                    .addComponent(paymentMsg2TXT))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 62, Short.MAX_VALUE)
                .addComponent(okButton, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void okButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_okButtonActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_okButtonActionPerformed


    private void loadProps() {
        String out = String.format("%.2f", value);
        if (value < 0) {
            PaymentMsgTXT.setText("Aguarde até que todos os seus pedidos sejam fechados pelos chefs!");
            paymentValueTXT.setVisible(false);
            paymentMsg2TXT.setVisible(false);
        } else {
            setTitle("Pagamento realizado La cocina Bistrô");
            PaymentMsgTXT.setText("Pagamento de ");
        }
        paymentValueTXT.setText(out);
    }
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
            java.util.logging.Logger.getLogger(Payment.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Payment.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Payment.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Payment.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(() -> {
            Payment dialog = new Payment(new javax.swing.JFrame(), true, value);
            dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                @Override
                public void windowClosing(java.awt.event.WindowEvent e) {
                    System.exit(0);
                }
            });
            dialog.setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel PaymentMsgTXT;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JButton okButton;
    private javax.swing.JLabel paymentMsg2TXT;
    private javax.swing.JLabel paymentValueTXT;
    // End of variables declaration//GEN-END:variables
}
