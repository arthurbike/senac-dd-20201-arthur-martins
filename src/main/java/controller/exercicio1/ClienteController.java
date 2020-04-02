package controller.exercicio1;

import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;

import model.bo.exercicio1.ClienteBO;
import model.bo.exercicio1.EnderecoBO;
import model.bo.exercicio1.TelefoneBO;
import model.dao.exercicio1.ClienteDAO;
import model.vo.exercicio1.Cliente;
import model.vo.exercicio1.Endereco;
import model.vo.exercicio1.Telefone;

public class ClienteController {

	private ClienteDAO dao = new ClienteDAO();

	private ClienteBO bo = new ClienteBO();

	public ArrayList<Cliente> listarTodosOsClientes() {
		return bo.consultarClientes();
	}

	public String validarCampos(String txtCpf, String txtNome, String txtSobrenome, Object txtTelefone) {
		String mensagem = "";
		
		mensagem += verificarTelefone(txtTelefone);
		
		mensagem += validarNome(txtNome);

		mensagem += validarCpf(txtCpf);

		mensagem += validarSobrenome(txtSobrenome);
		return mensagem;
	}

	public String excluirPorComboBox(Object comboBox) {
		String mensagem = "";

		Cliente cliente = new Cliente();
		cliente = (Cliente) comboBox;
		int id = cliente.getId();
		ArrayList<Telefone> telefones = cliente.getTelefones();
		if (!telefones.isEmpty()) {
			mensagem += "N�o � poss�vel excluir o cliente, pois ele possui telefone.";
		} else {
			ClienteBO bo = new ClienteBO();
			if (bo.excluirPorId(id)) {
				mensagem = "";
			} else {
				mensagem += "N�o foi poss�vel excluir o cliente.";
			}
		}

		return mensagem;
	}

	public String excluirPorCpf(String txtCpf) {
		String mensagem = validarCpf(txtCpf);
		if (mensagem.isEmpty()) {
			ClienteBO bo = new ClienteBO();
			bo.excluir(txtCpf);
		}

		return mensagem;
	}

	public String validarNome(String txtNome) {
		String mensagem = "";
		if (txtNome.length() < 3) {
			mensagem += "O nome deve conter pelo menos 3 caracteres.\n";
		} else {
			for (int i = 0; i < txtNome.length(); i++) {
				char a = txtNome.charAt(i);
				if (!Character.isLetter(a)) {
					mensagem += "O nome n�o pode conter n�meros.\n";
					break;
				}
			}
		}

		return mensagem;
	}

	public String validarSobrenome (String txtSobrenome) {
		String mensagem = "";
		
		if (txtSobrenome.isEmpty()) {
			mensagem += "O campo de sobrenome deve ser preenchido.";
		} else {
		for (int i = 0; i < txtSobrenome.length(); i++) {
			char a = txtSobrenome.charAt(i);
			if (!Character.isLetter(a)) {
				mensagem += "O sobrenome n�o pode conter n�meros.\n";
				break;
			}
		}
		}
		return mensagem;
	}

	public String cpfExistente(String txtCpf) {
		String mensagem = "";

		ClienteBO cliente = new ClienteBO();
		if (cliente.existeCpf(txtCpf)) {
			mensagem += "Este cpf j� est� sendo utilizado.\n";
		}
		return mensagem;
	}

	public String validarCpf(String txtCpf) {
		String mensagem = "";

		if (txtCpf.length() != 11) {
			mensagem += "O cpf deve possuir 11 d�gitos.\n";
		}
		if (txtCpf.isEmpty()) {
			mensagem += "O campo do cpf n�o foi preenchido.\n";
		}
		mensagem += cpfExistente(txtCpf);

		return mensagem;
	}

	// clientes que possuem telefones n�o podem ser exclu�dos
	public String possuiTelefone(String txtTelefone) {
		String mensagem = "";
		ClienteBO clienteBO = new ClienteBO();
		if (clienteBO.telefoneExistente(txtTelefone)) {
			mensagem += "Este telefone j� existe.";
		}
		return mensagem;
	}

	public ArrayList<Endereco> preencherEndereco() {
		EnderecoBO enderecoBO = new EnderecoBO();
		ArrayList<Endereco> enderecos = enderecoBO.consultarTodosEnderecos();

		return enderecos;
	}
	
	public ArrayList<Telefone> preencherTelefone() {
		TelefoneBO telefoneBO = new TelefoneBO();
		ArrayList<Telefone> telefones = telefoneBO.listarTelefones();
		
		return telefones;
	}

	public int selecionarIdEndereco(Object endereco) {
		Endereco idEndereco = (Endereco) endereco;
		return idEndereco.getId();
	}

	public String validarExclusao(String txtCpf) {
		String mensagem = "";

		mensagem += validarCpf(txtCpf);
		if (mensagem.isEmpty()) {
			ClienteBO bo = new ClienteBO();

		}

		return mensagem;
	}

	public String salvarCliente(String txtNome, String txtSobrenome, String txtCpf, Object txtEndereco, Object txtTelefone) {
		String mensagem = "";
		
		mensagem += validarCampos(txtCpf, txtNome, txtSobrenome, txtTelefone);
		if (!mensagem.isEmpty()) {
			JOptionPane.showMessageDialog(null, mensagem);
		} else {
			
			

			ClienteBO bo = new ClienteBO();
			Cliente cliente = criarCliente(txtNome, txtSobrenome, txtCpf, txtEndereco, txtTelefone);
			bo.salvar(cliente);

		}
		return mensagem;
	}

	public Cliente criarCliente(String txtNome, String txtSobrenome, String txtCpf, Object txtEndereco, Object txtTelefone) {
		Cliente cliente = new Cliente();
		cliente.setNome(txtNome);
		cliente.setSobrenome(txtSobrenome);
		cliente.setCpf(txtCpf);

		Endereco endereco = (Endereco) txtEndereco;
		cliente.setEndereco(endereco);
		
		Telefone telefone = (Telefone) txtTelefone;
		ArrayList<Telefone> telefones = new ArrayList<Telefone>();
		telefones.add(telefone);
		cliente.setTelefones(telefones);

		return cliente;
	}
	
	public void atualizarComboBox(JComboBox comboBox, Object item) {
		comboBox.removeItem(item);
	}
	
	public String verificarTelefone(Object txtTelefone) {
		String mensagem = "";
		
		Telefone telefone = (Telefone)txtTelefone;
		String txtNumero = telefone.getNumero();
		TelefoneController telefoneController = new TelefoneController();
		if ( !telefoneController.validarTelefone(txtNumero).isEmpty() ) {
			mensagem += telefoneController.validarTelefone(txtNumero);
		}
		return mensagem;
	}

}