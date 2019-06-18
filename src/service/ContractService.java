package service;

import java.util.Calendar;
import java.util.Date;

import entities.Contract;
import entities.Installment;

public class ContractService {

	private OnlinePaymentService onlinePaymentService;

	public ContractService(OnlinePaymentService onlinePaymentService) {
		this.onlinePaymentService = onlinePaymentService;
	}

	public void processContract(Contract contract, Integer months) {
		double paymentQuota = contract.getTotalValue() / months;

		for (int i = 1; i <= months; i++) {
			Date date = addMonths(contract.getDate(), i);
			double updateQuota = paymentQuota + onlinePaymentService.interest(paymentQuota, i);
			double fullQuota = updateQuota + onlinePaymentService.paymentFee(updateQuota);
			contract.addInstallment(new Installment(date, fullQuota));
		}
	}

	private Date addMonths(Date date, int n) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.MONTH, n);
		return cal.getTime();
	}

}
