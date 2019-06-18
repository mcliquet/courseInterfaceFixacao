package application;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

import entities.Contract;
import entities.Installment;
import service.ContractService;
import service.PaypalService;

public class Program {

	public static void main(String[] args) throws ParseException {
		// TODO Auto-generated method stub
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		
		System.out.println("Enter contract data:");
		
		System.out.print("Number: ");
		int number = sc.nextInt();
		System.out.print("Date (dd/MM/yyyy): ");
		Date date = sdf.parse(sc.nextLine());
		System.out.print("Contract value: ");
		double totalValue = sc.nextDouble();
		
		//instancia um novo contrato
		Contract contract = new Contract(number, date, totalValue);
		
		System.out.print("Enter number of installments: ");
		int installments = sc.nextInt();
		
		//instanciar um serviço com o método de calculo usando o paypal
		ContractService contractService = new ContractService(new PaypalService());
		
		//processar o contrato com os calculos usando o método processContract
		contractService.processContract(contract, installments);
		
		//imprimir as parcelas
		System.out.println("Installments: ");
		for(Installment x : contract.getInstallments()) {
			System.out.println(x);
		}
		
		sc.close();
	}

}
