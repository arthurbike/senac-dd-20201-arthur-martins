package model.bo.exercicio1;

import model.dao.exercicio1.EnderecoDAO;

public class EnderecoBO {

	private EnderecoDAO dao = new EnderecoDAO();

	public String excluir(int idSelecionado) {
		String mensagem = "";
		if (dao.temEnderecoCadastradoComId(idSelecionado)) {
			if (dao.excluir(idSelecionado)) {
				mensagem = "Exclu�do com sucesso";
			} else {
				mensagem = "Erro ao excluir";
			}
		} else {
			mensagem = "Id informado (" + idSelecionado + ") n�o existe no banco.";
		}

		return mensagem;
	}
}