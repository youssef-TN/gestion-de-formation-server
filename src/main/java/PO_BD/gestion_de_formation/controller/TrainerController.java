package PO_BD.gestion_de_formation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import PO_BD.gestion_de_formation.model.Trainer;
import PO_BD.gestion_de_formation.service.TrainerService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/trainers")
@CrossOrigin(origins = "*")
public class TrainerController {

	private final TrainerService trainerService;

	@Autowired
	public TrainerController(TrainerService trainerService) {
		this.trainerService = trainerService;
	}

	@GetMapping
	public ResponseEntity<List<Trainer>> getAllTrainers() {
		return ResponseEntity.ok(trainerService.getAllTrainers());
	}

	@GetMapping("/{id}")
	public ResponseEntity<Trainer> getTrainerById(@PathVariable String id) {
		Optional<Trainer> trainer = trainerService.getTrainerById(id);
		return trainer.map(ResponseEntity::ok)
				.orElseGet(() -> ResponseEntity.notFound().build());
	}

	@PostMapping
	public ResponseEntity<?> createTrainer(@RequestBody Trainer trainer) {
		try {
			Trainer createdTrainer = trainerService.createTrainer(trainer);
			return ResponseEntity.status(HttpStatus.CREATED).body(createdTrainer);
		} catch (IllegalArgumentException e) {
			Map<String, String> response = new HashMap<>();
			response.put("error", e.getMessage());
			return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
		}
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> updateTrainer(@PathVariable String id, @RequestBody Trainer trainerDetails) {
		try {
			Trainer updatedTrainer = trainerService.updateTrainer(id, trainerDetails);
			return ResponseEntity.ok(updatedTrainer);
		} catch (IllegalArgumentException e) {
			Map<String, String> response = new HashMap<>();
			response.put("error", e.getMessage());
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteTrainer(@PathVariable String id) {
		try {
			trainerService.deleteTrainer(id);
			return ResponseEntity.noContent().build();
		} catch (IllegalArgumentException e) {
			Map<String, String> response = new HashMap<>();
			response.put("error", e.getMessage());
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
		}
	}

	@GetMapping("/recentActivities")
	public ResponseEntity<List<Trainer>> getRecentActivities() {
		return ResponseEntity.ok(trainerService.getRecentActivities());
	}
}
