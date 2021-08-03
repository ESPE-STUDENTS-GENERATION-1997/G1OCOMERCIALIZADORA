package ec.edu.espe.monster.GR10COMERCIALIZADORA.RestControllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import ec.edu.espe.monster.GR10COMERCIALIZADORA.models.DTOs.InvoicesAgencySaveRequest;
import ec.edu.espe.monster.GR10COMERCIALIZADORA.models.entitys.InvoicesAgency;
import ec.edu.espe.monster.GR10COMERCIALIZADORA.services.InvoicesAgencyServices;

@RestController
@CrossOrigin("*")
public class InvoicesAgencyRestController {

	@Autowired
	private InvoicesAgencyServices invoicesAgencyServices;

	@RequestMapping(value = "/api/v1/invoices-agency/save", 
			method = RequestMethod.POST, 
			consumes = MediaType.APPLICATION_JSON_VALUE, 
			produces = MediaType.APPLICATION_JSON_VALUE,
			headers ={ "Accept=*/*", "Content-Type=*/*" })
	@ResponseBody
	public ResponseEntity<Long> save(@RequestBody InvoicesAgencySaveRequest request) {
		InvoicesAgency invoive = invoicesAgencyServices.saveInvoice(request);

		return new ResponseEntity<Long>(invoive.getId_invoice_ag(), HttpStatus.CREATED);
	}

	@GetMapping("/api/test")
	public String test() {
		return "test";
	}
}
