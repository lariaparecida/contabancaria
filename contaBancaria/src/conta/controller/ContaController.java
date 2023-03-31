package conta.controller;

import java.util.ArrayList;

import conta.model.Conta;
import conta.repository.ContaRepository;

public class ContaController implements ContaRepository {
	int numero = 0;
	private ArrayList<Conta> listaContas = new ArrayList<Conta>();

	@Override

	public void listarTodas() {
		for (var conta : listaContas) {
			conta.visualizar();
		}
	}

	@Override
	public void procurarPorNumero(int numero) {
		var Conta = buscarNaCollection(numero);

		if (Conta != null)
			Conta.visualizar();
		else
			System.out.println("\n A conta número: " + numero + " não foi criada");

	}

	@Override
	public void cadastrar(Conta conta) {
		listaContas.add(conta);
		System.out.println("\n A Conta número: " + conta.getNumero() + " foi criada com sucesso!");
	}

	@Override
	public void atualizar(Conta conta) {
		var buscaConta = buscarNaCollection(conta.getNumero());

		if (buscaConta != null) {
			listaContas.set(listaContas.indexOf(buscaConta), conta);
			System.out.println("n A conta número: " + conta.getNumero() + " foi atualizada com sucesso!");
		} else
			System.out.println("\n A Conta número: " + conta.getNumero() + " não foi encontrada!");
	}

	@Override
	public void deletar(int numero) {
		var conta = buscarNaCollection(numero);

		if (conta != null) {
			if (listaContas.remove(conta) == true)
				System.out.println("\n A conta numero: " + numero + " foi deletada com suesso! ");
		} else
			System.out.println("\n A conta numero: " + numero + " não foi encontrada! ");
	}

	@Override
	public void sacar(int numero, float valor) {
		var conta = buscarNaCollection(numero);

		if (conta != null) {
			if (conta.sacar(valor) == true)
				System.out.println("\n O saque na Conta numero: " + numero + " foi efetuado com sucesso!");
		} else
			System.out.println("\n A Conta numero: " + numero + " não foi encontrada! ");

	}

	@Override
	public void depositar(int numero, float valor) {
		var conta = buscarNaCollection(numero);

		if (conta != null) {
			conta.depositar(valor);
			System.out.println("\n O deposito na conta número: " + numero + "foi efetuado com sucesso!");
		} else
			System.out.println(
					"\n A conta numero: " + numero + "não foi encontrada pi a Conta destino não é uma Conta Corrente!");

	}

	@Override
	public void transferir(int numeroOrigem, int numeroDestino, float valor) {
		var contaOrigem = buscarNaCollection(numeroOrigem);
		var contaDestino = buscarNaCollection(numeroDestino);

		if (contaOrigem != null && contaDestino != null) {
			contaDestino.depositar(valor);
			System.out.println("\n A transferência foi efetuada com sucesso! ");
		} else
			System.out.println("\n A conta de origem e/ou destino não foram encontradas! ");

	}

	public int gerarNumero() {
		return ++numero;
	}

	public Conta buscarNaCollection(int numro) {
		for (var Conta : listaContas) {
			if (Conta.getNumero() == numero) {
				return Conta;
			}
		}
		return null;
	}

	public int retornaTipo(int numero) {
		for (var conta : listaContas) {
			if (conta.getNumero() == numero) {
				return conta.getTipo();
			}
		}
		return 0;
	}

}