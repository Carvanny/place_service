package br.com.place.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.HttpClientErrorException;

import br.com.place.PlaceServiceApplication;
import br.com.place.model.Place;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = PlaceServiceApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PlaceControllerIntegrationTest {
	@Autowired
	private TestRestTemplate restTemplate;

	@LocalServerPort
	private int port;

	private String getRootUrl() {
		return "http://localhost:8080" + "/api/v1/place";
	}

	@Test
	public void contextLoads() {

	}

	@Test
	public void testGetAllPlace() {
		HttpHeaders headers = new HttpHeaders();
		HttpEntity<String> entity = new HttpEntity<String>(null, headers);
		ResponseEntity<String> response = restTemplate.exchange(getRootUrl() + "/", HttpMethod.GET, entity,
				String.class);
		assertNotNull(response.getBody());
	}

	@Test
	public void testGetPlaceById() {
		Place place = restTemplate.getForObject(getRootUrl() + "/5d89168f56df0c34f943703c", Place.class);
		System.out.println(place.getName());
		assertNotNull(place);
	}

	@Test
	public void testCreatePlace() {
		Place place = new Place();
		place.setCity("Rio de Janeiro");
		place.setName("Realengo");
		place.setSlug("Marimbondo");
		place.setState("RJ");
		ResponseEntity<Place> postResponse = restTemplate.postForEntity(getRootUrl() + "/", place, Place.class);
		assertNotNull(postResponse);
		assertNotNull(postResponse.getBody());
	}

	@Test
	public void testUpdatePlace() {
		String id = "5d89177056df0c37e7e8e248";
		Place place = restTemplate.getForObject(getRootUrl() + "/" + id, Place.class);
		place.setCity("Rio de Janeiro");
		place.setName("Centro");
		place.setSlug("borboleta");
		place.setState("RJ");
		restTemplate.put(getRootUrl() + "/" + id, place);
		Place updatedPlace = restTemplate.getForObject(getRootUrl() + "/" + id, Place.class);
		assertNotNull(updatedPlace);
	}

	@Test
	public void testDeletePlace() {
		String id = "5d890d3656df0c1a5673e392";
		Place place = restTemplate.getForObject("http://localhost:8080/api/v1/place/" + id, Place.class);
		assertNotNull(place);
		restTemplate.delete(getRootUrl() + "/" + id);
		try {
			place = restTemplate.getForObject(getRootUrl() + "/" + id, Place.class);
		} catch (final HttpClientErrorException e) {
			assertEquals(e.getStatusCode(), HttpStatus.NOT_FOUND);
		}
	}
}