package AppCentrale;

import javax.swing.JOptionPane;

public class Centrale 
{

    public static void main(String[] args) 
    {
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
            java.util.logging.Logger.getLogger(CentraleAchat.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CentraleAchat.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CentraleAchat.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CentraleAchat.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        
        String choixCentrale="";
        int Centrale=0;
        String[] TypeCentrale = {"Pièces","Pneus","Lubrifiants"};
        //Fenetre de dialogue pour choisir la centrale en question
        Centrale = JOptionPane.showOptionDialog(null, 
                "Sélectionnez la centrale", "Choix centrale", 
                JOptionPane.DEFAULT_OPTION, 
                JOptionPane.QUESTION_MESSAGE, 
                null, 
                TypeCentrale, 
                TypeCentrale[0]);
        
        if(Centrale != -1)
        {
            choixCentrale=TypeCentrale[Centrale];
            CentraleAchat fenetreCentrale = new CentraleAchat(choixCentrale);
            fenetreCentrale.setVisible(true);
        }
    }
}
