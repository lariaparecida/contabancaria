package conta;

import java.io.IOException; 

import java.util.InputMismatchException;
import java.util.Scanner;

import conta.model.Conta;
import conta.model.ContaCorrente;
import conta.model.ContaPoupanca;
import conta.util.Cores;
import conta.controller.ContaController;

@SuppressWarnings("unused")
public class Menu {

	public static Scanner leia = new Scanner(System.in);

	public static void main(String[] args) {
		ContaController contas = new ContaController();
		
		int  opcao, numero, agencia, tipo, aniversario ,numeroDestino;
		String titular;
		float saldo, limite, valor;
	

		while (true) {

			System.out.println(Cores.TEXT_YELLOW + Cores.ANSI_BLACK_BACKGROUND
					+ "*****************************************************");
			System.out.println("                                                     ");
			System.out.println("                BANCO DO BRAZIL COM Z                ");
			System.out.println("                                                     ");
			System.out.println("*****************************************************");
			System.out.println("                                                     ");
			System.out.println("            1 - Criar Conta                          ");
			System.out.println("            2 - Listar todas as Contas               ");
			System.out.println("            3 - Buscar Conta por Numero              ");
			System.out.println("            4 - Atualizar Dados da Conta             ");
			System.out.println("            5 - Apagar Conta                         ");
			System.out.println("            6 - Sacar                                ");
			System.out.println("            7 - Depositar                            ");
			System.out.println("            8 - Transferir valores entre Contas      ");
			System.out.println("            9 - Sair                                 ");
			System.out.println("                                                     ");
			System.out.println("*****************************************************");
			System.out.println("Entre com a opção desejada:                          ");
			System.out.println("                                                     " + Cores.TEXT_RESET);
			
			try {
				opcao = leia.nextInt();
			}catch(InputMismatchException e){
				System.out.println("\nDigite valores inteiros!");
				leia.nextLine();
				opcao=0;
			}

			if (opcao == 9) {
				System.out.println("\nBanco do Brazil com Z - O seu futuro começa aqui!");
				leia.close();
				System.exit(0);
			}

			switch (opcao) {
			case 1:
				System.out.println(Cores.TEXT_BLUE+"\n Criar Conta");

				System.out.println(Cores.TEXT_WHITE+"\n Digite o numero da Agência: ");
				agencia = leia.nextInt();
				System.out.println(Cores.TEXT_WHITE+"\n Digite o Nome do titular: ");
				leia.skip("\\R?");
				titular = leia.nextLine();
				
				do {
					System.out.println(Cores.TEXT_WHITE+"Digite o tipo de conta (1-CC ou 2-CP): ");
					tipo = leia.nextInt();
				} while (tipo != 1 && tipo != 2);
				
				System.out.println(Cores.TEXT_GREEN+"Digite o Saldo da Conta (R$): ");
				saldo = leia.nextFloat();
				
				switch(tipo) {
				case 1 -> {
					System.out.println(Cores.TEXT_WHITE+"Digite o Limite de Crédito (R$)");
					limite = leia.nextFloat();
					contas.cadastrar(new ContaCorrente(contas.gerarNumero(), agencia, tipo, titular, saldo, limite));
				}
				case 2 -> {
					System.out.println("Digite o dia do Aniversario da Conta: ");
					aniversario = leia.nextInt();
					contas.cadastrar(new ContaPoupanca(contas.gerarNumero(), agencia, tipo, titular, saldo, aniversario));
				}
				
				}
				
				
			case 2:
				System.out.println(Cores.TEXT_WHITE+"\n Listar todas as Contas");
				contas.listarTodas();
				keyPress();
				break;
			case 3:
				System.out.println(Cores.TEXT_WHITE+"\n Consultar dados da Conta - Por número\n\n");
				System.out.println("Digite o número da conta: ");
				numero = leia.nextInt();
				
				contas.procurarPorNumero(numero);

				keyPress();
				break;
			case 4:
				System.out.println(Cores.TEXT_WHITE+"\n Atualizar dados da Conta");
				
				System.out.println("Digite o número da conta: ");
				numero = leia.nextInt();
				
				if(contas.buscarNaCollection(numero)!= null) {
					System.out.println("Digite o numero da Agência: ");
					agencia = leia.nextInt();
					System.out.println("Difite o nome do Titular: ");
					leia.skip("\\R?");
					titular = leia.nextLine();
					
					System.out.println("Digite o saldo da Conta (R$): ");
					saldo = leia.nextFloat();
					
					tipo = contas.retornaTipo(numero);
					
					switch (tipo) {
					case 1 -> {
						System.out.println("Digite o Limite de Crétido (R$): ");
						limite = leia.nextFloat();
						contas.atualizar(new ContaCorrente(numero,agencia,tipo,titular,saldo,limite));
					}
					case 2 -> {
						System.out.println("Digite o dia do Aniversário da conta: ");
						aniversario = leia.nextInt();
						contas.atualizar(new ContaPoupanca(numero,agencia,tipo,titular,saldo,aniversario));
					} default -> {
						System.out.println("! Tipo de conta inválida !");
					}
					} 
				} else
					System.out.println("\n! Conta não encontrada !");
			
				
				keyPress();
				break;
				
				
			case 5:
				System.out.println(Cores.TEXT_RED+" Apagar Conta");
				
				System.out.println(Cores.TEXT_WHITE+"\nDigite o número da conta ");
				numero = leia.nextInt();
				
				contas.deletar(numero);
				
				keyPress();
				break;
				
			case 6:
				System.out.println("Saque\n\n");
				
				System.out.println("Digite o número da conta: ");
				numero = leia.nextInt();
				
				do {
					System.out.println("Digite o valor do Saque (R$)");
					valor = leia.nextFloat();
				} while(valor <=0);
				
				contas.sacar(numero, valor);
				keyPress();
				break;
				
			case 7:
				System.out.println(Cores.TEXT_GREEN+"Depositar\n\n");
				
				System.out.println("Digite o Numero da conta: ");
				numero = leia.nextInt();
				
				do {
					System.out.println("Digite o valor do depósiti (R$): ");
					valor = leia.nextFloat();
				}while (valor <= 0);
				
				contas.depositar(numero, valor);
				keyPress();
				
				break;
			case 8:
				System.out.println(Cores.TEXT_WHITE+"Transferir\n\n");
				
				System.out.println("Digite o Numero da conta de origem: ");
				numero = leia.nextInt();
				System.out.println("Digite o Numero da conta de destino: ");
				numeroDestino = leia.nextInt();
				
				do {
					System.out.println("Digite o valor da tranferência (R$): ");
					valor = leia.nextFloat();
					
				}while(valor <= 0);
				
				contas.transferir(numero, numeroDestino, valor);
				keyPress();
				break;
				
			default:
				System.out.println(Cores.TEXT_RED+"\nOpção Inválida" + Cores.TEXT_RESET);
				
				keyPress();
				break;
			}
		}
	}

	public static void keyPress() {

		try {

			System.out.println(Cores.TEXT_RESET + "\n\nPressione Enter para Continuar...");
			System.in.read();

		} catch (IOException e) {

			System.out.println("Você pressionou uma tecla diferente de enter!");

		} catch(Exception e2) {
			System.out.println("Opção invalida");
		}
	}
}