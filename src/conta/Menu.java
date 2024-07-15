package conta;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;
import conta.util.Cores;
import conta.controller.ContaController;
import conta.model.ContaCorrente;
import conta.model.ContaPoupanca;

public class Menu {

	public static void main(String[] args) {
		
		ContaController contas = new ContaController();
		Scanner teclado = new Scanner(System.in);
		
		int opcao,numero,agencia,tipo,aniversario;
		String titular;
		float saldo,limite;
		
		while (true) {
		
		System.out.println(Cores.TEXT_YELLOW + Cores.ANSI_BLACK_BACKGROUND
				         + "*********************************************");
		System.out.println("                                             ");
		System.out.println("            BANCO DO BRAZIL COM Z            ");
		System.out.println("                                             ");
		System.out.println("     1 - Criar Conta                         ");
		System.out.println("     2 - Listar todas as Contas              ");
		System.out.println("     3 - Buscar Conta por Numero             ");
		System.out.println("     4 - Atualizar Dados da Conta            ");
		System.out.println("     5 - Apagar Conta                        ");
		System.out.println("     6 - Sacar                               ");
		System.out.println("     7 - Depositar                           ");
		System.out.println("     8 - Transferir valores entre Contas     ");
		System.out.println("     9 - Sair                                ");
		System.out.println("                                             ");
		System.out.println("*********************************************");
		System.out.println("Entre com a opção desejada:                  ");
		System.out.println("                                             " + Cores.TEXT_RESET);
		
		try {
			opcao = teclado.nextInt();
		} catch (InputMismatchException e) {
			System.out.println("\nDigite valores inteiros!");
			teclado.nextLine();
			opcao = 0;
		}
		
		
		if (opcao == 9) {
			System.out.println(Cores.TEXT_WHITE_BOLD + "\nBanco do Brazil com Z - O seu futuro começa aqui!");
			sobre();
			teclado.close();
			System.exit(0);
		}
		
		switch (opcao) {
		case 1:
			System.out.println(Cores.TEXT_WHITE_BOLD + "Criar Conta\n\n");
			
			System.out.println("Digite o número da Agência: ");
			agencia = teclado.nextInt();
			System.out.println("Digite o nome do titular: ");
			teclado.skip("\\R?");
			titular = teclado.nextLine();
			
			do {
				System.out.println("Digite o tipo de conta (1 - CC ou 2 - CP): ");
				tipo = teclado.nextInt();
				
			} while (tipo < 1 || tipo > 2);
			
			System.out.println("Digite o saldo da conta (R$): ");
			saldo = teclado.nextFloat();
			
			switch (tipo) {
			case 1:
				System.out.println("Digite o limite da conta (R$): ");
				limite = teclado.nextFloat();
				
				contas.cadastrar(new ContaCorrente(contas.gerarNumero(),agencia,tipo,titular,saldo,limite));
				break;
			case 2:
				System.out.println("Digite o aniversário da conta: ");
				aniversario = teclado.nextInt();
				
				contas.cadastrar(new ContaPoupanca(contas.gerarNumero(),agencia,tipo,titular,saldo,aniversario));
				break;
			default: 
				System.out.println("Opção inválida!");
				break;
			}
			
			keyPress();
			break;
		case 2:
			System.out.println(Cores.TEXT_WHITE_BOLD + "Listar todas as Contas\n\n");
			
			contas.listarTodas();
			keyPress();
			break;
		case 3: 
			System.out.println(Cores.TEXT_WHITE_BOLD + "Consultar dados da Conta por número\n\n");
			
			System.out.println("Digite o número da conta: ");
			numero = teclado.nextInt();
			
			contas.procurarPorNumero(numero);
			
			keyPress();
			break;
		case 4:
			System.out.println(Cores.TEXT_WHITE_BOLD + "Atualizar dados da Conta\n\n");
			
			System.out.println("Digite o número da conta: ");
			numero = teclado.nextInt();
			
			var buscaConta = contas.buscarNaCollection(numero);
			
			if (buscaConta != null) {
				
				tipo = buscaConta.getTipo();
				
				System.out.println("Digite o número da Agência: ");
				agencia = teclado.nextInt();
				System.out.println("Digite o nome do Titular: ");
				titular = teclado.nextLine();
				System.out.println("Digite o saldo da Conta (R$): ");
				saldo = teclado.nextFloat();
				
				switch(tipo) {
				case 1 -> {
					System.out.println("Digite o limite de Crédito (R$): ");
					limite = teclado.nextFloat();
					
					
					contas.atualizar(new ContaCorrente(numero, agencia, tipo, titular, saldo, limite));
				}
				}
				
			}
			
			keyPress();
			break;
		case 5:
			System.out.println(Cores.TEXT_WHITE_BOLD + "Apagar a Conta\n\n");
			
			System.out.println("Digite o número da conta a deletar: ");
			contas.deletar(teclado.nextInt());
			
			keyPress();
			break;
		case 6:
			System.out.println(Cores.TEXT_WHITE_BOLD + "Saque\n\n");
			
			System.out.println("Digite o número da conta: ");
			int contaSaque = teclado.nextInt();
			
			System.out.println("Digite o valor a sacar: ");
			float aSacar = teclado.nextFloat();
			
			contas.sacar(contaSaque, aSacar);
			
			keyPress();
			break;
		case 7:
			System.out.println(Cores.TEXT_WHITE_BOLD + "Depósito\n\n");
			
			System.out.println("Digite o número da conta: ");
			int contaDeposito = teclado.nextInt();
			
			System.out.println("Digite o valor a sacar: ");
			float aDepositar = teclado.nextFloat();
			
			contas.depositar(contaDeposito, aDepositar);
			
			keyPress();
			break;
		case 8:
			System.out.println(Cores.TEXT_WHITE_BOLD + "Transferência entre Contas\n\n");
			
			System.out.println("Digite o número da conta de origem: ");
			int numOrigem = teclado.nextInt();
			
			System.out.println("Digite o número da conta de destino: ");
			int numDestino = teclado.nextInt();
			
			System.out.println("Digite o valor a transferir: ");
			float valor = teclado.nextFloat();
			
			contas.transferir(numOrigem, numDestino, valor);
			
			keyPress();
			break;
		default:
			System.out.println(Cores.TEXT_RED_BOLD + "\nOpção Inválida!\n");
			keyPress();
			break;
				
		}
		
		}
		
	}
	
	public static void sobre() {
		System.out.println("\n*****************************************************");
		System.out.println("Projeto Desenvolvido por: ");
		System.out.println("Bianka Bonete Staianof - biankabonete@gmail.com");
		System.out.println("github.com/bonninho");
		System.out.println("*******************************************************");
	}
	
	public static void keyPress() {

		try {

			System.out.println(Cores.TEXT_RESET + "\n\nPressione Enter para Continuar...");
			System.in.read();

		} catch (IOException e) {

			System.out.println("Você pressionou uma tecla diferente de enter!");

		}
	
	}
}
	
