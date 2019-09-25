package br.com.place.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.MediaType;

import br.com.place.model.Place;
import br.com.place.repository.PlaceRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(value = "callback", description = "This is a simple challenge to test your skills on building APIs.", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, basePath = "/api/callback")
@RestController
@RequestMapping("/api/v1/place")
public class PlaceController {

	@Autowired
	private PlaceRepository repository;

	@ApiOperation(value = "Search Place for all", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, response = Place.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Sucess"), @ApiResponse(code = 404, message = "Service not found")})
	@GetMapping
	public ResponseEntity<?> list() {
		List<Place> list = repository.findAll();
		return ResponseEntity.ok(list);
	}

	@ApiOperation(value = "Search Place for Id", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, response = Place.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Sucess"), @ApiResponse(code = 404, message = "Place not found for this id ::")})	
	@GetMapping(path = "/{id}")
	public ResponseEntity<Place> findById(@PathVariable String id) {
		
		return repository.findById(id)
		           .map(record -> ResponseEntity.ok().body(record))
		           .orElse(ResponseEntity.notFound().build());
	}

	@ApiOperation(value = "Create a Place ", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, response = Place.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully created"), @ApiResponse(code = 404, message = "Service not found")})			
	@PostMapping
	public Place create(@RequestBody Place place){
		place.setCreated(new Date());
		place.setUpdated(new Date());
		return repository.save(place);
	}

	@ApiOperation(value = "Update Place ", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, response = Place.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully updated"), @ApiResponse(code = 404, message = "Service not found")})		
	@PutMapping(value="/{id}")
	public ResponseEntity<Place> update(@PathVariable("id") String id, @RequestBody Place place) {
		return repository.findById(id)
	           .map(record -> {
	               record.setName(place.getName());
	               record.setSlug(place.getSlug());
	               record.setCity(place.getCity());
	               record.setState(place.getState());
	               record.setUpdated(new Date());
	               Place updated = repository.save(record);
	               return ResponseEntity.ok().body(updated);
	           }).orElse(ResponseEntity.notFound().build());
	}
	
	/**
	 * This method was not requested, but given that the requested is a CRUD. So I decided to create
	 * @param id
	 */
	@ApiOperation(value = "Delete Place ", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, response = Place.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully deleted"), @ApiResponse(code = 404, message = "Service not found")})		
	@DeleteMapping(value="/{id}")
	public void delete(@PathVariable("id") String id) {
		 repository.deleteById(id);	           
	}
}