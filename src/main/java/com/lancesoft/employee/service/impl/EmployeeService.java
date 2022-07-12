package com.lancesoft.employee.service.impl;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.lancesoft.employee.exception.DateMissMatchException;
import com.lancesoft.employee.exception.EmployeeAreadyExistException;
import com.lancesoft.employee.model.BasicDetails;
import com.lancesoft.employee.model.Bill;
import com.lancesoft.employee.model.ClientDetailsOfEmployee;
import com.lancesoft.employee.repository.BasicDetailsRepository;
import com.lancesoft.employee.repository.BillingRepository;


@Service
public class EmployeeService {
	
	@Autowired
	BillingRepository billRepo;

	@Autowired
	BasicDetailsRepository basicDetailsRepo;

	public BasicDetails EMSHandler(BasicDetails dates) {

		System.out.println("Service "+dates.getId());
		LocalDate dt = dates.getJoiningDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

		LocalDate Sysdate = LocalDate.now();

		Period tenure = Period.between(dt, Sysdate);

		int total = tenure.getYears() * 12 + tenure.getMonths();

		dates.setTenure(total);

		LocalDate poSdate = dates.getClientDetailsOfEmployee().getPoSdate().toInstant().atZone(ZoneId.systemDefault())
				.toLocalDate();

		LocalDate PoEdate = dates.getClientDetailsOfEmployee().getPoEdate().toInstant().atZone(ZoneId.systemDefault())
				.toLocalDate();

		Period monthsAtClient = Period.between(poSdate, PoEdate);

		int workingAtclientMonths = monthsAtClient.getYears() * 12 + monthsAtClient.getMonths();

		System.out.println("wc" + workingAtclientMonths + " tenure" + total);

		int bench_tenure = total - workingAtclientMonths;

		dates.setBenchTen(bench_tenure);

		int paidTillNow = total * dates.getSalary();

		long BeanchExp = paidTillNow + bench_tenure*(dates.getBill().getCubicalCost() + dates.getBill().getTransportCost()
				+ dates.getBill().getFoodCost());

		Optional<Bill> billing = billRepo.findById(dates.getId());

		Bill bill = null;
		if (billing.isPresent()) {
			bill = billing.get();
		}

		long paid_till_now = total * dates.getSalary();

		long clientBilling = dates.getClientDetailsOfEmployee().getClientBilling();

		long profit = workingAtclientMonths * clientBilling - BeanchExp;

		bill.setBenchExp(BeanchExp);
		bill.setProfit(profit);
		bill.setPaidTillNow(paidTillNow);
		billRepo.save(bill);

		basicDetailsRepo.save(dates);

		return  dates;}
	
	public Page<BasicDetails> gettingAll(PageRequest pageReuquest)
	{
		Page<BasicDetails> list = (Page<BasicDetails>) basicDetailsRepo.findAll(pageReuquest);
		return list;
	}
	
	
	public BasicDetails createEmployee(BasicDetails basicDetail) {
		BasicDetails local=basicDetailsRepo.findByEmployeeId(basicDetail.getEmployeeId());
		if(local!=null)
			throw new EmployeeAreadyExistException("420","Employee with this id already present");
		else
		basicDetail.setEmployeeId(basicDetail.getEmployeeId());
		ClientDetailsOfEmployee cDOE=new ClientDetailsOfEmployee();
		
		
		if(basicDetail.getJoiningDate().after(basicDetail.getClientDetailsOfEmployee().getPoEdate())
				&& basicDetail.getJoiningDate().after(basicDetail.getClientDetailsOfEmployee().getPoSdate())) {
			throw new DateMissMatchException("421","Please check joining Date, PoEdate & PoSdate are they correctly entered or not");
		}
		if(basicDetail.getClientDetailsOfEmployee().getPoSdate().after(basicDetail.getClientDetailsOfEmployee().getPoEdate())) {
			throw new DateMissMatchException("422","Please check PoEdate & PoSdate are they correctly entered or not");
		}
		else {
			basicDetail.setJoiningDate(basicDetail.getJoiningDate());
			cDOE.setPoEdate(cDOE.getPoEdate());
			cDOE.setPoSdate(cDOE.getPoSdate());
		}
		return basicDetailsRepo.save(basicDetail);
	}

}
