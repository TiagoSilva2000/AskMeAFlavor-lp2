/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.LP2.view.pages.manager;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.MenuElement;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.plaf.basic.BasicMenuBarUI;
import javax.swing.table.DefaultTableModel;

import com.LP2.view.pages.Login;
import com.LP2.view.pages.warnings.General;
import com.LP2.controllers.items.DrinkController;
import com.LP2.controllers.items.FoodController;
import com.LP2.controllers.items.ItemController;
import com.LP2.view.pages.EditProfile;

import static com.LP2.view.pages.CustomizeMenuBar.CustomizeMenuBar;

/**
 *
 * @author Blueevee
 */
public class ManagerAccount extends javax.swing.JFrame {

    /**
     * Creates new form managerAcount
     */
    public ManagerAccount() {
        initComponents();
        this.getContentPane().setBackground(Color.decode("14027569"));
        CustomizeMenuBar(managerMENU, Color.decode("14027569"), Color.white );
        loadTable();
        this.setResizable(false);

        productsTBL.getModel().addTableModelListener(new TableModelListener(){

            @Override
            public void tableChanged(TableModelEvent e) {
                int rowIdx = e.getFirstRow();
                if (rowIdx > -1) {
                    int id = Integer.parseInt(productsTBL.getValueAt(rowIdx, 0).toString());
                    String name = productsTBL.getValueAt(rowIdx, 1).toString();
                    double price = Double.parseDouble(productsTBL.getValueAt(rowIdx, 2).toString());
                    String type = productsTBL.getValueAt(rowIdx, 3).toString();
                    String extra = productsTBL.getValueAt(rowIdx, 4).toString();

                    if (type.equals("comida"))
                        FoodController.update(id, name, price, true, extra, null);
                    else
                        DrinkController.update(id, name, price, true, extra, null);
                }
            }
        });
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        managerBackBTN = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        productsTBL = new javax.swing.JTable();
        logo = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        removeLabel = new javax.swing.JLabel();
        removeTXTField = new javax.swing.JTextField();
        removeButton = new javax.swing.JButton();
        managerMENU = new javax.swing.JMenuBar();
        registerItensMENU = new javax.swing.JMenu();
        employeeMENU = new javax.swing.JMenu();
        editProfileMENU = new javax.swing.JMenu();
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Gerência La Cocina Bistrô");

        managerBackBTN.setBackground(new java.awt.Color(38, 70, 27));
        managerBackBTN.setIcon(new javax.swing.ImageIcon((System.getProperty("user.dir") + "/assets/back16.png"))); // NOI18N
        managerBackBTN.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                managerBackBTNMouseClicked(evt);
            }
        });

        productsTBL.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "CÓDIGO", "ITEM", "PREÇO", "TIPO", "FORNECEDOR/DESCRIÇÃO"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, true, true, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(productsTBL);
        if (productsTBL.getColumnModel().getColumnCount() > 0) {
            productsTBL.getColumnModel().getColumn(0).setPreferredWidth(2);
            productsTBL.getColumnModel().getColumn(1).setPreferredWidth(80);
            productsTBL.getColumnModel().getColumn(2).setPreferredWidth(2);
            productsTBL.getColumnModel().getColumn(3).setPreferredWidth(5);
            productsTBL.getColumnModel().getColumn(4).setPreferredWidth(20);
        }

        logo.setIcon(new javax.swing.ImageIcon((System.getProperty("user.dir") + "/assets/logo.png"))); // NOI18N

        jButton1.setBackground(new java.awt.Color(38, 70, 27));
        jButton1.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Salvar");

        removeLabel.setFont(new java.awt.Font("DejaVu Sans", 0, 16)); // NOI18N
        removeLabel.setForeground(new java.awt.Color(255, 255, 255));
        removeLabel.setText("Código: ");

        removeTXTField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeTXTFieldActionPerformed(evt);
            }
        });

        removeButton.setBackground(new java.awt.Color(255, 51, 51));
        removeButton.setForeground(new java.awt.Color(255, 255, 255));
        removeButton.setText("Remover");
        removeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeButtonActionPerformed(evt);
            }
        });

        registerItensMENU.setIcon(new javax.swing.ImageIcon((System.getProperty("user.dir") + "/assets/food16.png"))); // NOI18N
        registerItensMENU.setText("Cadastrar itens");
        registerItensMENU.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                registerItensMENUMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                registerItensMENUMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                registerItensMENUMouseExited(evt);
            }
        });
        managerMENU.add(registerItensMENU);

        employeeMENU.setIcon(new javax.swing.ImageIcon((System.getProperty("user.dir") + "/assets/func16.png"))); // NOI18N
        employeeMENU.setText("Funcionários");
        employeeMENU.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                employeeMENUMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                employeeMENUMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                employeeMENUMouseExited(evt);
            }
        });
        managerMENU.add(employeeMENU);

        editProfileMENU.setIcon(new javax.swing.ImageIcon((System.getProperty("user.dir") + "/assets/profileEdit16.png"))); // NOI18N
        editProfileMENU.setText("Perfil");
        editProfileMENU.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                editProfileMENUMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                editProfileMENUMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                editProfileMENUMouseExited(evt);
            }
        });
        managerMENU.add(editProfileMENU);

        setJMenuBar(managerMENU);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 843, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(managerBackBTN)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(removeLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(removeTXTField, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(removeButton)))
                        .addGap(17, 17, 17)
                        .addComponent(logo, javax.swing.GroupLayout.PREFERRED_SIZE, 248, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(managerBackBTN)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(removeLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(removeTXTField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(removeButton))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(logo, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)))
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 319, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void managerBackBTNMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_managerBackBTNMouseClicked
        this.dispose();
        Login loginScreen = new Login();
        loginScreen.setVisible(true);
    }//GEN-LAST:event_managerBackBTNMouseClicked

    private void registerItensMENUMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_registerItensMENUMouseClicked
       this.dispose();
       RegisterProducts registerProductsScreen = new RegisterProducts();
       registerProductsScreen.setVisible(true);
    }//GEN-LAST:event_registerItensMENUMouseClicked

    private void employeeMENUMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_employeeMENUMouseClicked
       this.dispose();
       ManageEmployees manageEmployeesScreen = new ManageEmployees();
       manageEmployeesScreen.setVisible(true);
    }//GEN-LAST:event_employeeMENUMouseClicked

    private void registerItensMENUMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_registerItensMENUMouseEntered
        registerItensMENU.setForeground(Color.decode("6554655"));
        registerItensMENU.setIcon(new ImageIcon(System.getProperty("user.dir") + "/assets/hoverFood16.png"));
    }//GEN-LAST:event_registerItensMENUMouseEntered

    private void registerItensMENUMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_registerItensMENUMouseExited
        registerItensMENU.setForeground(Color.white);
        registerItensMENU.setIcon(new ImageIcon(System.getProperty("user.dir") + "/assets/food16.png"));
    }//GEN-LAST:event_registerItensMENUMouseExited

    private void employeeMENUMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_employeeMENUMouseEntered
        employeeMENU.setForeground(Color.decode("6554655"));
        employeeMENU.setIcon(new ImageIcon(System.getProperty("user.dir") + "/assets/hoverFunc16.png"));
    }//GEN-LAST:event_employeeMENUMouseEntered

    private void employeeMENUMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_employeeMENUMouseExited
        employeeMENU.setForeground(Color.white);
        employeeMENU.setIcon(new ImageIcon(System.getProperty("user.dir") + "/assets/func16.png"));
    }//GEN-LAST:event_employeeMENUMouseExited

    private void editProfileMENUMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_editProfileMENUMouseEntered
        editProfileMENU.setForeground(Color.decode("6554655"));
        editProfileMENU.setIcon(new ImageIcon(System.getProperty("user.dir") + "/assets/hoverProfileEdit.png"));
    }//GEN-LAST:event_editProfileMENUMouseEntered

    private void editProfileMENUMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_editProfileMENUMouseExited
        editProfileMENU.setForeground(Color.white);
        editProfileMENU.setIcon(new ImageIcon(System.getProperty("user.dir") + "/assets/profileEdit16.png"));
    }//GEN-LAST:event_editProfileMENUMouseExited

    private void editProfileMENUMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_editProfileMENUMouseClicked
        this.dispose();
        EditProfile editProfileScreen = new EditProfile();
        editProfileScreen.setVisible(true);
    }//GEN-LAST:event_editProfileMENUMouseClicked

    private void removeTXTFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeTXTFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_removeTXTFieldActionPerformed

    private void removeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeButtonActionPerformed
        final String inputCode = removeTXTField.getText();
        final int itemCode;

        if (inputCode.length() == 0) {
            General gen = new General("Nenhum código foi inserido.");
            gen.setVisible(true);
        } else {
            try {
                itemCode = Integer.parseInt(inputCode);
                //removeLineByItemCode(itemCode);
                ItemController.delete(itemCode);
                General gen = new General("Sucesso na Operação.");
                this.dispose();
                ManagerAccount ma = new ManagerAccount();
                ma.setVisible(true);
                gen.setVisible(true);
            } catch(final Exception e) {
                e.printStackTrace();
                General gen = new General("Erro durante a remoção.");
                gen.setVisible(true);
                return;
            }
        }
    }//GEN-LAST:event_removeButtonActionPerformed
    private int removeLineByItemCode(final int id) {
        DefaultTableModel model = (DefaultTableModel) productsTBL.getModel();
        //Vector<Vector> rows = model.getDataVector();
        int currId;

        productsTBL.repaint();
        // model.fireTableDataChanged();
        return 1;
        // System.out.println("size: " + rows.size());
        // for (int i = 0; i < rows.size(); i++) {
        //     currId = Integer.parseInt(rows.elementAt(i).elementAt(0).toString());
        //     System.out.println(currId);
        //     if (currId == id) {
        //         System.out.println("i: " + i);
        //         model.removeRow(i);
        //         return 1;
        //     }
        // }
        // return 0;
    }

    private void loadTable(){

        DefaultTableModel model = (DefaultTableModel) productsTBL.getModel();
        model.setNumRows(0);
        Object[][] items = com.LP2.models.utils.Menu.getMatrixMenuToManager();

        productsTBL.getColumnModel().getColumn(0).setPreferredWidth(5);
        productsTBL.getColumnModel().getColumn(1).setPreferredWidth(50);
        productsTBL.getColumnModel().getColumn(2).setPreferredWidth(10);
        productsTBL.getColumnModel().getColumn(3).setPreferredWidth(30);
        productsTBL.getColumnModel().getColumn(4).setPreferredWidth(30);

//        pegar do bd e preencher as linhas
        for (int i = 0; i < items.length; i++) {
            model.addRow(items[i]);
        }
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
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ManagerAccount.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new ManagerAccount().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu editProfileMENU;
    private javax.swing.JMenu employeeMENU;
    private javax.swing.JButton jButton1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel logo;
    private javax.swing.JButton managerBackBTN;
    private javax.swing.JMenuBar managerMENU;
    private javax.swing.JTable productsTBL;
    private javax.swing.JMenu registerItensMENU;
    private javax.swing.JButton removeButton;
    private javax.swing.JLabel removeLabel;
    private javax.swing.JTextField removeTXTField;
    // End of variables declaration//GEN-END:variables
}
