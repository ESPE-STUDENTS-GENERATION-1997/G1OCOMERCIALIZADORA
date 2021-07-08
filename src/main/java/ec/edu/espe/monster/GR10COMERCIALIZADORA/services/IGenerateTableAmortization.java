package ec.edu.espe.monster.GR10COMERCIALIZADORA.services;

import java.util.List;

import ec.edu.espe.monster.GR10COMERCIALIZADORA.models.DTOs.TableAmortizationResponse;
import ec.edu.espe.monster.GR10COMERCIALIZADORA.models.entitys.Factura;


public interface IGenerateTableAmortization {

	public Double calculateFee(Double amount, Integer numFee, Double interestRateYear);
	
	public List<TableAmortizationResponse> generate(Factura factura);
	
}