/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package pbo_proyek;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.LayoutManager;
import java.awt.Rectangle;
import java.io.File;
import java.io.IOException;
import java.awt.image.BufferedImage;
import java.io.InputStream;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author chris
 */
public class Form_Menu extends javax.swing.JFrame {
    
    /**
     * Creates new form Form_Menu
     */
    private Image logo;
    private JButton prev_btn;
    private Form_Login frm_login;
    
    private ArrayList<JPanel> listPanel = new ArrayList<>();
    
    public Form_Menu() {
        initComponents();
        interruptor.setVisible(false);
        initSubForm();
        // logo = originalImage.getScaledInstance(jPanel1.getWidth(),jPanel1.getHeight(),Image.SCALE_SMOOTH);
        BufferedImage bi;
        // InputStream is = Form_Login.class.getResourceAsStream("Resources/Font Awesome 5 Free-Solid-900.otf");
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            InputStream is = Form_Login.class.getResourceAsStream("Images/logo_compufy.png");
            bi = ImageIO.read(is);
            logo = bi.getScaledInstance(lb_logo.getWidth()*50/100,lb_logo.getHeight(),Image.SCALE_SMOOTH);
            ImageIcon ic = new ImageIcon(logo);
            lb_logo.setIcon(ic);
        } catch (Exception e) {
            System.out.println(e);
        }
        interruptor_default = interruptor.getSize();
        this.setMinimumSize(new Dimension(1030,612));
    }
    
    Dimension interruptor_default;
    
    public void restartForm(User usr){
        initSubForm();
        prev_btn = btn_transaction;
        pl_menu.setLayout(new BorderLayout());
        buttonPress(btn_stock);
        User user_login = User.getUser_login();
        lbl_nama.setText("Welcome, "+user_login.getNama());
        lbl_jabatan.setText(usr.getNama_jabatan());
        
        btn_laporan.setVisible(usr.getNama_jabatan().equalsIgnoreCase("Manager"));
        btn_account.setVisible(usr.getNama_jabatan().equalsIgnoreCase("Manager"));
        
        if(!usr.getNama_jabatan().equalsIgnoreCase("Manager")){
            interruptor.setSize(interruptor.getWidth(),interruptor.getHeight()+50);
        }else{
            interruptor.setSize(interruptor_default);
        }
        this.setSize(this.getMinimumSize());
        this.setExtendedState(this.NORMAL);
        this.setLocationRelativeTo(null);
    }
    
    public Form_Login getFrm_login() {
        return frm_login;
    }

    public void setFrm_login(Form_Login frm_login) {
        this.frm_login = frm_login;
    }
    
    
    public void initSubForm(){
        Form_Account frm_acc = new Form_Account();
        Form_Laporan frm_his = new Form_Laporan();
        Form_Stock frm_stock = new Form_Stock();
        Form_Transaction frm_trans = new Form_Transaction();
        listPanel.clear();
        frm_stock.initJabatan();
        listPanel.add(frm_stock.getPl());
        listPanel.add(frm_trans.getPl());
        listPanel.add(frm_his.getPl());
        listPanel.add(frm_acc.getPl());
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pl_leftbar = new java.awt.Panel();
        btn_stock = new javax.swing.JButton();
        btn_account = new javax.swing.JButton();
        btn_laporan = new javax.swing.JButton();
        btn_transaction = new javax.swing.JButton();
        lb_logo = new javax.swing.JLabel();
        pl_topbar = new java.awt.Panel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        btn_toggle = new javax.swing.JButton();
        interruptor = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        lbl_nama = new javax.swing.JLabel();
        lbl_jabatan = new javax.swing.JLabel();
        pl_menu = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                formComponentResized(evt);
            }
        });
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        pl_leftbar.setBackground(new java.awt.Color(41, 40, 48));

        btn_stock.setBackground(new java.awt.Color(41, 40, 48));
        btn_stock.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        btn_stock.setForeground(new java.awt.Color(222, 222, 222));
        btn_stock.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pbo_proyek/Images/box-solid.png"))); // NOI18N
        btn_stock.setText("Master Stock");
        btn_stock.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 15, 1, 1));
        btn_stock.setBorderPainted(false);
        btn_stock.setDefaultCapable(false);
        btn_stock.setFocusPainted(false);
        btn_stock.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btn_stock.setIconTextGap(20);
        btn_stock.setName("0"); // NOI18N
        btn_stock.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_laporanMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_laporanMouseExited(evt);
            }
        });
        btn_stock.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_stockActionPerformed(evt);
            }
        });

        btn_account.setBackground(new java.awt.Color(41, 40, 48));
        btn_account.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        btn_account.setForeground(new java.awt.Color(222, 222, 222));
        btn_account.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pbo_proyek/Images/user-circle-solid.png"))); // NOI18N
        btn_account.setText("Master Account");
        btn_account.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 15, 1, 1));
        btn_account.setBorderPainted(false);
        btn_account.setDefaultCapable(false);
        btn_account.setFocusPainted(false);
        btn_account.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btn_account.setIconTextGap(20);
        btn_account.setName("3"); // NOI18N
        btn_account.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_laporanMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_laporanMouseExited(evt);
            }
        });
        btn_account.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_accountActionPerformed(evt);
            }
        });

        btn_laporan.setBackground(new java.awt.Color(41, 40, 48));
        btn_laporan.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        btn_laporan.setForeground(new java.awt.Color(222, 222, 222));
        btn_laporan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pbo_proyek/Images/file-invoice-solid.png"))); // NOI18N
        btn_laporan.setText("Laporan");
        btn_laporan.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 15, 1, 1));
        btn_laporan.setBorderPainted(false);
        btn_laporan.setDefaultCapable(false);
        btn_laporan.setFocusPainted(false);
        btn_laporan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btn_laporan.setIconTextGap(20);
        btn_laporan.setName("2"); // NOI18N
        btn_laporan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_laporanMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_laporanMouseExited(evt);
            }
        });
        btn_laporan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_laporanActionPerformed(evt);
            }
        });

        btn_transaction.setBackground(new java.awt.Color(41, 40, 48));
        btn_transaction.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        btn_transaction.setForeground(new java.awt.Color(222, 222, 222));
        btn_transaction.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pbo_proyek/Images/cash-register-solid.png"))); // NOI18N
        btn_transaction.setText("Transaction");
        btn_transaction.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 15, 1, 1));
        btn_transaction.setBorderPainted(false);
        btn_transaction.setDefaultCapable(false);
        btn_transaction.setFocusPainted(false);
        btn_transaction.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btn_transaction.setIconTextGap(20);
        btn_transaction.setName("1"); // NOI18N
        btn_transaction.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_laporanMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_laporanMouseExited(evt);
            }
        });
        btn_transaction.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_transactionActionPerformed(evt);
            }
        });

        lb_logo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        pl_topbar.setBackground(new java.awt.Color(41, 40, 48));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(222, 222, 222));
        jLabel1.setText("Compufy");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(222, 222, 222));
        jLabel2.setText("v.1.5.3");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(222, 222, 222));
        jLabel3.setText("Enterprise Edition");

        javax.swing.GroupLayout pl_topbarLayout = new javax.swing.GroupLayout(pl_topbar);
        pl_topbar.setLayout(pl_topbarLayout);
        pl_topbarLayout.setHorizontalGroup(
            pl_topbarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pl_topbarLayout.createSequentialGroup()
                .addGroup(pl_topbarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 31, Short.MAX_VALUE)
                .addComponent(jLabel3))
            .addComponent(jSeparator1)
        );
        pl_topbarLayout.setVerticalGroup(
            pl_topbarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pl_topbarLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLabel1)
                .addGap(0, 0, 0)
                .addGroup(pl_topbarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14))
        );

        btn_toggle.setBackground(new java.awt.Color(41, 40, 48));
        btn_toggle.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        btn_toggle.setForeground(new java.awt.Color(255, 102, 102));
        btn_toggle.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pbo_proyek/Images/40px-semitransparent.png"))); // NOI18N
        btn_toggle.setBorder(null);
        btn_toggle.setBorderPainted(false);
        btn_toggle.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btn_toggle.setDefaultCapable(false);
        btn_toggle.setFocusPainted(false);
        btn_toggle.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btn_toggle.setIconTextGap(0);
        btn_toggle.setName("3"); // NOI18N
        btn_toggle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_toggleActionPerformed(evt);
            }
        });

        interruptor.setEnabled(false);

        javax.swing.GroupLayout pl_leftbarLayout = new javax.swing.GroupLayout(pl_leftbar);
        pl_leftbar.setLayout(pl_leftbarLayout);
        pl_leftbarLayout.setHorizontalGroup(
            pl_leftbarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btn_stock, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btn_account, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btn_transaction, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btn_laporan, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(pl_leftbarLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pl_leftbarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(interruptor, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pl_topbar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lb_logo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(pl_leftbarLayout.createSequentialGroup()
                        .addComponent(btn_toggle)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        pl_leftbarLayout.setVerticalGroup(
            pl_leftbarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pl_leftbarLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(interruptor, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lb_logo, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pl_topbar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(btn_stock, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(btn_transaction, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(btn_laporan, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(btn_account, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_toggle)
                .addContainerGap())
        );

        pl_leftbarLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btn_account, btn_laporan, btn_stock, btn_transaction});

        jPanel1.setBackground(new java.awt.Color(41, 40, 48));

        lbl_nama.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lbl_nama.setForeground(new java.awt.Color(222, 222, 222));
        lbl_nama.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbl_nama.setText("Welcome, <USER>");

        lbl_jabatan.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lbl_jabatan.setForeground(new java.awt.Color(222, 222, 222));
        lbl_jabatan.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbl_jabatan.setText("<Jabatan>");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lbl_nama, javax.swing.GroupLayout.DEFAULT_SIZE, 530, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addContainerGap(411, Short.MAX_VALUE)
                        .addComponent(lbl_jabatan, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbl_nama)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbl_jabatan)
                .addContainerGap(10, Short.MAX_VALUE))
        );

        pl_menu.setBackground(new java.awt.Color(84, 84, 96));

        javax.swing.GroupLayout pl_menuLayout = new javax.swing.GroupLayout(pl_menu);
        pl_menu.setLayout(pl_menuLayout);
        pl_menuLayout.setHorizontalGroup(
            pl_menuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        pl_menuLayout.setVerticalGroup(
            pl_menuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(pl_leftbar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pl_menu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(pl_menu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(pl_leftbar, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents
    
    public void buttonPress(JButton own){
        prev_btn.setBackground(Palette.getDark1());
        prev_btn = own;
        own.setBackground(Palette.getDark2());
        pl_menu.removeAll();
        pl_menu.revalidate();
        pl_menu.repaint();
        listPanel.get(Integer.parseInt(own.getName())).setSize(pl_menu.getSize());
        pl_menu.add(listPanel.get(Integer.parseInt(own.getName())),BorderLayout.CENTER);
    }
    
    private void btn_stockActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_stockActionPerformed
        buttonPress((JButton)evt.getSource());
    }//GEN-LAST:event_btn_stockActionPerformed

    private void btn_transactionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_transactionActionPerformed
        buttonPress((JButton)evt.getSource());
    }//GEN-LAST:event_btn_transactionActionPerformed

    private void btn_laporanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_laporanActionPerformed
        buttonPress((JButton)evt.getSource());
    }//GEN-LAST:event_btn_laporanActionPerformed

    private void btn_accountActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_accountActionPerformed
        buttonPress((JButton)evt.getSource());
    }//GEN-LAST:event_btn_accountActionPerformed

    private void formComponentResized(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentResized
        // TODO add your handling code here:
        // JPanel pl_show = (JPanel)pl_menu.getComponent(0);
        // pl_show.setSize(pl_menu.getSize());
        // pl_menu.validate();
    }//GEN-LAST:event_formComponentResized
    
    
    //60 244
    private void btn_toggleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_toggleActionPerformed
        panelToggle();
        
    }//GEN-LAST:event_btn_toggleActionPerformed
    
    public void panelToggle(){
        lb_logo.setVisible(!lb_logo.isVisible());
        pl_topbar.setVisible(!pl_topbar.isVisible());
        
        int h = pl_leftbar.getHeight();
        
        if(pl_leftbar.getWidth() <= 60){
            pl_leftbar.setPreferredSize(new Dimension(244,h));
            interruptor.setVisible(false);
        }else{
            pl_leftbar.setPreferredSize(new Dimension(60,h));
            interruptor.setVisible(true);
        }
    }
    
    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        // TODO add your handling code here:
        frm_login.setVisible(true);
        prev_btn.setBackground(Palette.getDark1());
        if(pl_leftbar.getWidth() <= 60){
            panelToggle();
        }
        this.setVisible(false);
    }//GEN-LAST:event_formWindowClosing

    private void btn_laporanMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_laporanMouseEntered
        // TODO add your handling code here:
        JButton own = (JButton)evt.getSource();
        if(own != prev_btn){
            own.setBackground(new Color(50,49,59));
        }
    }//GEN-LAST:event_btn_laporanMouseEntered

    private void btn_laporanMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_laporanMouseExited
        // TODO add your handling code here:
        JButton own = (JButton)evt.getSource();
        if(own != prev_btn){
            own.setBackground(Palette.getDark1());
        }
    }//GEN-LAST:event_btn_laporanMouseExited
    
    
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
            java.util.logging.Logger.getLogger(Form_Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Form_Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Form_Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Form_Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Form_Menu().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_account;
    private javax.swing.JButton btn_laporan;
    private javax.swing.JButton btn_stock;
    private javax.swing.JButton btn_toggle;
    private javax.swing.JButton btn_transaction;
    private javax.swing.JLabel interruptor;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel lb_logo;
    private javax.swing.JLabel lbl_jabatan;
    private javax.swing.JLabel lbl_nama;
    private java.awt.Panel pl_leftbar;
    private javax.swing.JPanel pl_menu;
    private java.awt.Panel pl_topbar;
    // End of variables declaration//GEN-END:variables
}
