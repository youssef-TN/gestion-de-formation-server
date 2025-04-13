package PO_BD.gestion_de_formation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import PO_BD.gestion_de_formation.model.Participant;
import PO_BD.gestion_de_formation.service.ParticipantService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/participants")
public class ParticipantController {
	
	private final ParticipantService participantService;

	@Autowired
	public ParticipantController(ParticipantService participantService) {
		this.participantService = participantService;
	}

	@GetMapping
	public ResponseEntity<List<Participant>> getAllParticipants() {
		return ResponseEntity.ok(participantService.getAllParticipants());
	}

	@GetMapping("/{id}")
	public ResponseEntity<Participant> getParticipantById(@PathVariable String id) {
		Optional<Participant> participant = participantService.getParticipantById(id);
		return participant.map(ResponseEntity::ok)
				.orElseGet(() -> ResponseEntity.notFound().build());
	}

	@GetMapping("/structure/{structure}")
	public ResponseEntity<List<Participant>> getParticipantsByStructure(@PathVariable String structure) {
		return ResponseEntity.ok(participantService.getParticipantsByStructure(structure));
	}

	@PostMapping
	public ResponseEntity<?> createParticipant(@RequestBody Participant participant) {
		try {
			Participant createdParticipant = participantService.createParticipant(participant);
			return ResponseEntity.status(HttpStatus.CREATED).body(createdParticipant);
		} catch (IllegalArgumentException e) {
			Map<String, String> response = new HashMap<>();
			response.put("error", e.getMessage());
			return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
		}
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> updateParticipant(@PathVariable String id, @RequestBody Participant participantDetails) {
		try {
			Participant updatedParticipant = participantService.updateParticipant(id, participantDetails);
			return ResponseEntity.ok(updatedParticipant);
		} catch (IllegalArgumentException e) {
			Map<String, String> response = new HashMap<>();
			response.put("error", e.getMessage());
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteParticipant(@PathVariable String id) {
		try {
			participantService.deleteParticipant(id);
			return ResponseEntity.noContent().build();
		} catch (IllegalArgumentException e) {
			Map<String, String> response = new HashMap<>();
			response.put("error", e.getMessage());
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
		}
	}
	
}
