package ec.edu.espe.monster.GR10COMERCIALIZADORA.models.DTOs;

import java.util.Date;


import lombok.Data;

@Data
public class FlightsResponseApi {

	private Long id;
	
	private Date timestampTakeOff;
	
	private Double costTicket;
	
	private Integer numTicketAviable;
	
	private Integer numTicketTotal;
}
