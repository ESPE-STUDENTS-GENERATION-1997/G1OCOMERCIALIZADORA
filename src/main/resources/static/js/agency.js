let itemsSellTicket = []



function handleFindFlights() {

	const cityOrigin = document.getElementById("cityOrigin").value
	const cityDestiny = document.getElementById("cityDestiny").value
	let dateSelected = document.getElementById("dateFlight").value

	if (cityOrigin === '0') {
		alert("Debe seleccionar una Ciudad de Origen")
		return;
	}

	if (cityDestiny === '0') {
		alert("Debe seleccionar una Ciudad de Destino")
		return;
	}

	if (dateSelected === null || dateSelected === undefined || dateSelected === '') {
		alert("Debe seleccionar una fecha.")
		return;
	}

	dateSelected = parseDate(dateSelected);

	fetch(`https://aerolinea-condor-espe-2021.herokuapp.com/v1/flights/${cityOrigin}/${cityDestiny}/${dateSelected}/find-all`)
		.then((response) => {
			return response.json();
		}).then(function(responseData) {
			$("#modal_buscar_vuelos").modal('show');
			if (Array.isArray(responseData)) {
				const elementContentModal = document.getElementById("content_modal_buscar_vuelos")
				if (responseData.length >= 1) {
					buildFlightsOptions(responseData, elementContentModal)
				} else {
					elementContentModal.innerHTML = `
					<div class="alert alert-info" role="alert">
					  No exiten vuelos con los parámetros seleccionados.
					</div>
					`
					const buttonAccept = document.querySelector('#btn_success_flight')
					buttonAccept.disabled = true
				}
			} else {
				$("#modal_buscar_vuelos").modal('hide');
				alert("Error al consumir los web services")
			}
		}).catch(function(error) {
			console.log(error)
			alert("Error al consumir los web services")
		});

}

const parseDate = (dateSelected = "") => {
	if (typeof dateSelected === "string") {
		return dateSelected.substring(8, 10) + dateSelected.substring(4, 8) + dateSelected.substring(0, 4);
	}
}

const buildFlightsOptions = (flightsAviable = [], elementContentModal) => {
	let carouselItem = `<div id="carouselExampleControls" class="carousel slide" data-ride="carousel" data-interval="false">  <div class="carousel-inner">`

	flightsAviable.forEach((item, index) => {
		const cityOrigin = document.getElementById("cityOrigin").value
		const cityDestiny = document.getElementById("cityDestiny").value
		let dateSelected = parseDate(document.getElementById("dateFlight").value)

		if (index === 0) {
			carouselItem = carouselItem + `<div class="container carousel-item active ">
			<div class="row alert alert-info">
				<div class="col-sm-3">
			      <figure class="figure card">
					  <img src="https://iair.mx/documentos/aerolineas_logo/condor-cargo.png" class="figure-img img-fluid rounded" alt="Aerolinea Condor">
					  <figcaption class="figure-caption">Aerolínea Condor</figcaption>
				  </figure>
			    </div>
			    <div class="col-sm-9">
					<div class="card" style="width: 95%">
					    <div class="card-body">
	 					<h5 class="card-title">Número de Vuelo: ${item.id}</h5>
					 	<h6 class="card-subtitle mb-2 text-muted">Costo: ${item.costTicket}</h6>
					    <p class="card-text"> Boletos Disponibles: ${item.numTicketTotal}. </p>
			    		<p class="card-text"> Boletos Totales: ${item.numTicketAviable}. </p>
						<div class="row">
							<div class="col-lg-7">Cantidad de Boletos a Comprar:</div>
							<div class="col-lg-5">
							<input class="form-control" type="number" id="amount_tickets_${item.id}">
							</div>	
						</div>
						<div class="row justify-content-md-center mb-2">
							<div class="col-lg-5">
								<button type="button" class="btn btn-info"
								onclick="handleSelectedFlight(${item.id},  ${item.costTicket}, ${item.numTicketTotal},  ${item.numTicketAviable},${cityOrigin}, ${cityDestiny}, '${dateSelected}' )">Selecionar</button>
							</div>
						</div>
						</div>
  			        </div>
			    </div>
				
			</div>
			</div>`
		} else {
			carouselItem = carouselItem + `<div class="carousel-item ">
			<div class="row alert alert-info">
				<div class="col-sm-3">
			      <figure class="figure card">
					  <img src="https://iair.mx/documentos/aerolineas_logo/condor-cargo.png" class="figure-img img-fluid rounded" alt="Aerolinea Condor">
					  <figcaption class="figure-caption">Aerolínea Condor</figcaption>
				  </figure>
			    </div>
			    <div class="col-sm-9">
					<div class="card" style="width: 95%">
					    <div class="card-body">
	 					<h5 class="card-title">Número de Vuelo: ${item.id}</h5>
					 	<h6 class="card-subtitle mb-2 text-muted">Costo: ${item.costTicket}</h6>
					    <p class="card-text"> Boletos Disponibles: ${item.numTicketTotal}. </p>
			    		<p class="card-text"> Boletos Totales: ${item.numTicketAviable}. </p>
						<div class="row">
							<div class="col-lg-7">Cantidad de Boletos a Comprar:</div>
							<div class="col-lg-5">
							<input class="form-control" type="number" id="amount_tickets_${item.id}">
							</div>	
						</div>
						<div class="row justify-content-md-center mb-2">
							<div class="col-lg-5">
								<button type="button" class="btn btn-info"  
								onclick="handleSelectedFlight(${item.id},  ${item.costTicket}, ${item.numTicketTotal},  ${item.numTicketAviable},${cityOrigin}, ${cityDestiny}, '${dateSelected}'  )" >Selecionar</button>
							</div>
						</div>
						</div>
  			        </div>
			    </div>
				
			</div>
			</div>`
		}


	})

	carouselItem = carouselItem + ` </div>`

	carouselItem = carouselItem
		+ `<a class="carousel-control-prev" href="#carouselExampleControls" role="button" data-slide="prev">
			    <span class="carousel-control-prev-icon" aria-hidden="true"></span>
			    <span class="sr-only">Anterior</span>
			  </a>
			  <a class="carousel-control-next" href="#carouselExampleControls" role="button" data-slide="next">
			    <span class="carousel-control-next-icon" aria-hidden="true"></span>
			    <span class="sr-only">Siguiente</span>
			  </a>
			</div> </div>`
	elementContentModal.innerHTML = carouselItem
}

function handleSelectedFlight(id, costTicket, numTicketTotal, numTicketAviable, ctOrigin, ctDestiny, dateFl) {
	const amountTickets = Number(document.getElementById("amount_tickets_" + id).value)

	itemsSellTicket.push({
		id,
		costTicket,
		numTicketTotal,
		numTicketAviable,
		amountTickets,
		ctOrigin,
		ctDestiny,
		dateFl
	})

	document.getElementById("new_item_agency").value = "Aerolinea Condor S.A."
	document.getElementById("new_item_dateFlight").value = dateFl
	document.getElementById("new_item_description").value = "Número de Vuelo: " + id 
	document.getElementById("new_item_flight").value = id
	document.getElementById("new_item_idCityOrigin").value = ctOrigin 
	document.getElementById("new_item_idCityDestiny").value = ctDestiny
	document.getElementById("new_item_price").value = costTicket 
	document.getElementById("new_item_numTickets").value = amountTickets
	
	document.getElementById("new_id_customer").value = document.getElementById("customerId").value 
	document.getElementById("new_names_customer").value = document.getElementById("names_costumer").value 
	document.getElementById("new_document_customer").value = document.getElementById("document_costumer").value 
	document.getElementById("new_email_customer").value = document.getElementById("email_costumer").value 
	document.getElementById("new_phone_customer").value = document.getElementById("phone_costumer").value  
	document.getElementById("new_address_customer").value = document.getElementById("address_costumer").value 
		
	let totalInvoice = 0.00
	
	itemsSellTicket.forEach((ticket, index) => {
		const totalItem = Math.round(100 * (ticket.amountTickets * ticket.costTicket)) / 100
		totalInvoice = totalInvoice + totalItem
		
	})
	
	totalInvoice = Math.round(100 * (totalInvoice)) / 100
	document.getElementById("invoice_total_ticket").value = totalInvoice
	//$("#modal_buscar_vuelos").modal('hide');

}

function handleSaveInvoice() {
	

	const data = {
		id_customer: Number(document.getElementById("customerId").value),
		items: itemsSellTicket
	}

	const config = {
		method: 'POST',
		headers: {
			'Content-Type': 'application/json'
		},
		body: JSON.stringify(data)
	}
	const elementContentModalResult = document.getElementById("content_modal_result_save")
	$("#modal_result_save").modal('show');

		
	fetch("/api/v1/invoices-agency/save", config)
		.then((response) => {
			return response.json();
		}).then(function(responseData) {
			console.log(responseData)
			//responseData.status = 404
			elementContentModalResult.innerHTML = `
			<div class="alert alert-info" role="alert">
			  Venta Registrada con exito. <br/>
			  <a href="#" class="alert-link">Ver Recibo</a>.
			</div>
			`
			
		}).catch(function(error) {
			console.log(error)
			elementContentModalResult.innerHTML = `
			<div ctbodylass="alert alert-info" role="alert">
			  Venta Registrada con exito.
			</div>
			`
			
		})
}

function handleEditRow(flight , dateFlight, price, numTickets){
	document.getElementById("modal_mostrar_boletos_title").append(flight)
	const htmlTBody =  document.getElementById("modal_table_tickets_tbody")
	
	let rows = ""
	for(let i = 0; i < numTickets ; i++){
		rows = rows + `
		<tr >
			<td>${i + 1}</td>
			<td>00${flight} - 00${i + 1}</td>
			<td>${dateFlight}</td>
			<td>${price}</td>
			<td>  
			<a href="/agencia/ventas/${flight}/idVuelo/${dateFlight}/fecha-vuelo/${price}/precio/00${flight}-00${i + 1}/asiento" class="editar btn btn-outline-info" >
				<i class="fas fa-print"></i>
			</button>
			</td>
		</tr>
		`
	}
	htmlTBody.innerHTML = rows;
	$("#modal_mostrar_boletos").modal('show');
	
}
	
