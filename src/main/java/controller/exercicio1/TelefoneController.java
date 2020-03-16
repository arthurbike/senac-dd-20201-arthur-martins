package controller.exercicio1;

import model.bo.exercicio1.TelefoneBO;
import model.vo.exercicio1.Telefone;

public class TelefoneController {

	private TelefoneBO bo = new TelefoneBO();

	/**
	 * Salva um novo telefone, validando os valores informados
	 * 
	 * @param novoTelefone o telefone a ser salvo;
	 * @return uma mensagem informando uma das op��es a seguir:
	 * 
	 *         (1) h� campos para ajustar
	 * 
	 *         (2) salvo com sucesso
	 * 
	 *         (3) erro ao salvar
	 */
	public String salvar(Telefone novoTelefone) {
		String mensagemValidacao = validarCampos(novoTelefone);

		if (mensagemValidacao.isEmpty()) {
			mensagemValidacao = bo.salvar(novoTelefone);
		}
		return mensagemValidacao;
	}

	private String validarCampos(Telefone novoTelefone) {
		String mensagem = "";

		if (novoTelefone == null) {
			mensagem = "Telefone n�o foi criado";
		} else {
			if (novoTelefone.getDdd().trim().length() != 2) {
				mensagem += "Informe o DDD com 2 d�gitos \n";
			}

			try {
				Integer.parseInt(novoTelefone.getDdd());
			} catch (NumberFormatException ex) {
				mensagem += "O DDD deve ser um N�MERO";
			}
		}

		// TODO fazer mais valida��es
		// Numero

		// Codigo pais
		return mensagem;
	}

	private String validarCampoNumerico(String valorDoCampo, String nomeDoCampo, int tamanhoMinimo, int tamanhoMaximo) {
		// TODO desenvolver

		return "";
	}

}