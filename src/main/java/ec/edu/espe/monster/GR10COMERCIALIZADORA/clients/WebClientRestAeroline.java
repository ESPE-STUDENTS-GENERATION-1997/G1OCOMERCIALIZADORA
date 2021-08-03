package ec.edu.espe.monster.GR10COMERCIALIZADORA.clients;

import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import ec.edu.espe.monster.GR10COMERCIALIZADORA.models.DTOs.CityResponseApi;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

@Service
@Getter
@Slf4j
public class WebClientRestAeroline {

	@Value("${ws.rest.aeroline.base-url}")
	private String baseUrl;
	
	private WebClient webClient;
	
	@PostConstruct
	public void build() {
		webClient = WebClient.builder()
				    .baseUrl(baseUrl)
				    .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
				    .build();
	}
	
	public List<CityResponseApi> getAllCitys(){
		try {
			Flux<CityResponseApi> citiesFlux = webClient.get().uri("/v1/geographic-location/-/TG004/by-type").retrieve().bodyToFlux(CityResponseApi.class);
			return citiesFlux.collectList().block();
		} catch (WebClientResponseException e) {
			log.error("FAILED API GET CITIES \nexpection: " + e.getMessage()  + " \n status-code" + e.getStatusCode());
			CityResponseApi cityDefault = new CityResponseApi();
			cityDefault.setId(1L);
			cityDefault.setName("Quito");
			return Arrays.asList(cityDefault);
		}
		
	}
}
