package executavel.exercicio1;

import javax.swing.JOptionPane;

import model.dao.exercicio1.TelefoneDAO;
import model.vo.exercicio1.Cliente;
import model.vo.exercicio1.Telefone;

public class AdicionaJOptionPane {

	public static void adicionar() {
		String codigoPais = JOptionPane.showInputDialog(null,"Digite o c�digo do pa�s do telefone: ", "Telefone", JOptionPane.INFORMATION_MESSAGE);
        String ddd = JOptionPane.showInputDialog(null,"Digite o ddd do telefone: ", "Telefone", JOptionPane.INFORMATION_MESSAGE);
        String numero = JOptionPane.showInputDialog(null,"Digite o n�mero do telefone: ", "Telefone", JOptionPane.INFORMATION_MESSAGE);
        int isMovel = JOptionPane.showConfirmDialog(null, "Seu telefone � m�vel?", "Telefone", JOptionPane.INFORMATION_MESSAGE);
        Boolean foiCancelado = false;
        boolean movel = false;
        //  0 = sim /  1 = n�o / 2 = cancelar;
        if(isMovel == 0) {
            System.out.println("O telefone � m�vel");
            movel = true;
        } else if(isMovel == 1) {
            System.out.println("O telefone � fixo");
            movel = false;
        } else {
        	foiCancelado = true;
            System.out.println("Erro, processo cancelado.");
            JOptionPane.showMessageDialog(null, "Erro, processo cancelado.");
        }
       // passar id do cliente como null
        int isAtivo = JOptionPane.showConfirmDialog(null, "Seu telefone � ativo?", "Telefone", JOptionPane.INFORMATION_MESSAGE);
        boolean ativo = true;
        if(isAtivo == 0) {
            System.out.println("O telefone � ativo");
            ativo = true;
        } else if(isAtivo == 1) {
            System.out.println("O telefone � ativo");
            ativo = true;
        } else {
        	foiCancelado = true;
            System.out.println("Erro, processo cancelado.");
            JOptionPane.showMessageDialog(null, "Erro, processo cancelado.");
        }
        
        Cliente cliente = new Cliente();
        Telefone novoTelefone = new Telefone(0, cliente, codigoPais, ddd, numero, movel, ativo);
        
        TelefoneDAO telefoneDAO = new TelefoneDAO();
        if(foiCancelado != true) {
        	telefoneDAO.salvar(novoTelefone);
        }
	}
	
}
