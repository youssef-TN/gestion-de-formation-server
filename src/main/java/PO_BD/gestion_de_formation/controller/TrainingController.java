package PO_BD.gestion_de_formation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import PO_BD.gestion_de_formation.model.Training;
import PO_BD.gestion_de_formation.model.User;
import PO_BD.gestion_de_formation.service.TrainingService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/trainings")
public class TrainingController {

	private final TrainingService trainingService;

	@Autowired
	public TrainingController(TrainingService trainingService) {
		this.trainingService = trainingService;
	}

	@GetMapping
	public ResponseEntity<List<Training>> getAllTrainings() {
		return ResponseEntity.ok(trainingService.getAllTrainings());
	}

	@GetMapping("/{id}")
	public ResponseEntity<Training> getTrainingById(@PathVariable String id) {
		Optional<Training> training = trainingService.getTrainingById(id);
		return training.map(ResponseEntity::ok)
				.orElseGet(() -> ResponseEntity.notFound().build());
	}

	@PostMapping
	public ResponseEntity<?> createTraining(@RequestBody Training training) {
		try {
			Training createdTraining = trainingService.createTraining(training);
			return ResponseEntity.status(HttpStatus.CREATED).body(createdTraining);
		} catch (IllegalArgumentException e) {
			Map<String, String> response = new HashMap<>();
			response.put("error", e.getMessage());
			return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
		}
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> updateTraining(@PathVariable String id, @RequestBody Training trainingDetails) {
		try {
			Training updatedTraining = trainingService.updateTraining(id, trainingDetails);
			return ResponseEntity.ok(updatedTraining);
		} catch (IllegalArgumentException e) {
			Map<String, String> response = new HashMap<>();
			response.put("error", e.getMessage());
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteTraining(@PathVariable String id) {
		try {
			trainingService.deleteTraining(id);
			return ResponseEntity.noContent().build();
		} catch (IllegalArgumentException e) {
			Map<String, String> response = new HashMap<>();
			response.put("error", e.getMessage());
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
		}
	}
	
}
