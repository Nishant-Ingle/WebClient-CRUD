package com.example.consumer;

import java.util.HashMap;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Mono;

@RestController
public class ConsumerController {
	String baseUrl = "http://localhost:9001";
	WebClient webClient = WebClient.builder().baseUrl(baseUrl).build();

	@GetMapping(value = "/avengers") 
	@ResponseBody
	public Object readAvengers() {
		return webClient
				.get()
				.uri("/avengers")
				.retrieve()
				.bodyToMono(HashMap.class)
				.block();
	}

	@GetMapping(value = "/avengers/{id}")
	@ResponseBody
	public Object readAvenger(@PathVariable String id) {
		return webClient
				.get()
				.uri("/avengers/" + id)
				.retrieve()
				.bodyToMono(String.class)
				.block();
	}

	@PutMapping(value = "/avengers/{id}")
	public void updateAvenger(@PathVariable String id, @RequestParam(value = "name") String name) {
		webClient
		.put()
		.uri(
				uriBuilder -> uriBuilder
				.path("/avengers/" + id)
				.queryParam("name", name)
				.build())
		.exchange()
		.block();
	}

	@DeleteMapping(value = "/avengers/{id}")
	public void deleteAvenger(@PathVariable String id) {
		webClient
		.delete()
		.uri("/avengers/" + id)
		.exchange()
		.block();
	}

	@PostMapping(value = "/avengers")
	public void createAvengers(@RequestBody HashMap<String, String> requestMap) {
		webClient
		.post()
		.uri("/avengers/")
		.body(BodyInserters.fromProducer(Mono.just(requestMap), HashMap.class))
		.exchange()
		.block();
	}
}
