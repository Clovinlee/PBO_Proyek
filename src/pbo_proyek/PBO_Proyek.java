/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package pbo_proyek;
/**
 *
 * @author chris
 */
public class PBO_Proyek {
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        DB.init();
        Form_Login frm_login = new Form_Login();
        frm_login.setVisible(true);
        // DetailAccount_Form frm = new DetailAccount_Form();
        // frm.setVisible(true);
        // System.out.println("DEBUG TESTING test");
        // Form_Account fa = new Form_Account();
        // fa.setVisible(true);
        // Form_Menu frm_menu = new Form_Menu();
        // frm_menu.setVisible(true);
        // TestForm frm_test = new TestForm();
        // frm_test.setVisible(true);
        // Form_Stock frm_stok = new Form_Stock();
        // frm_stok.setVisible(true);
    }
    
}
