package ec.edu.espe.monster.GR10COMERCIALIZADORA.services.imp;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import ec.edu.espe.monster.GR10COMERCIALIZADORA.models.DTOs.TableAmortizationResponse;
import ec.edu.espe.monster.GR10COMERCIALIZADORA.models.entitys.Factura;
import ec.edu.espe.monster.GR10COMERCIALIZADORA.services.IGenerateTableAmortization;

@Service
@Primary
public class GenerateTableAmortizationImp implements IGenerateTableAmortization{

	@Override
	public Double calculateFee(Double amount, Integer numFee, Double interestRateYear) {
		interestRateYear = (interestRateYear/ 100D) / 12D; 
		
		//( 1 + interes_mensual)
		Double interest = 1D + interestRateYear;
		
		//( 1 + interes_mensual)^numero_cuotas
		interest = Math.pow(interest, numFee);

		Double fee = (interest * interestRateYear)/(interest - 1D);

		fee = amount * fee;
		
		return fee;
	}

	@Override
	public List<TableAmortizationResponse> generate(Factura factura) {
		List<TableAmortizationResponse>  table = new ArrayList<TableAmortizationResponse>();
		
		Double balance = factura.getTotal();
		Double interestRateYear = (16D / 100D) / 12D;
		Double interestPaid = 0D;
		Double capitalPaid = 0D;
		DecimalFormat df = new DecimalFormat("0.00");
		Double fee = this.calculateFee(balance, factura.getNumero_cuotas(), 16D);
		
		table.add(new TableAmortizationResponse(0, "0.00", "0.00", "0.00", df.format(balance)));
		
		for (int i = 1; i <= factura.getNumero_cuotas(); i++) {
			TableAmortizationResponse row = new TableAmortizationResponse();
			row.setIndex(i);
			row.setValueFee(df.format(fee));
			
			interestPaid =  interestRateYear * balance;
			row.setInterestPaid(df.format(interestPaid));
			capitalPaid =  fee - interestPaid; 
			row.setCapitalPaid(df.format(capitalPaid));
			
			
			balance = balance - capitalPaid;
			row.setBalance(df.format(balance));
			
			table.add(row);
		}
		
		return table;
	}

}
