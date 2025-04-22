package PO_BD.gestion_de_formation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import PO_BD.gestion_de_formation.model.User;
import PO_BD.gestion_de_formation.service.UserService;

import java.util.HashMap; 
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "*")
public class UserController {

	private final UserService userService;

	@Autowired
	public UserController(UserService userService) {
		this.userService = userService;
	}

	@GetMapping
	public ResponseEntity<List<User>> getAllUsers() {
		return ResponseEntity.ok(userService.getAllUsers());
	}

	@GetMapping("/{id}")
	public ResponseEntity<User> getUserById(@PathVariable String id) {
		Optional<User> user = userService.getUserById(id);
		return user.map(ResponseEntity::ok)
				.orElseGet(() -> ResponseEntity.notFound().build());
	}

	@GetMapping("/login/{login}")
	public ResponseEntity<User> getUserByLogin(@PathVariable String login) {
		Optional<User> user = userService.getUserByLogin(login);
		return user.map(ResponseEntity::ok)
				.orElseGet(() -> ResponseEntity.notFound().build());
	}

	@GetMapping("/role/{role}")
	public ResponseEntity<List<User>> getUsersByRole(@PathVariable String role) {
		return ResponseEntity.ok(userService.getUsersByRole(role));
	}

	@PostMapping
	public ResponseEntity<?> createUser(@RequestBody User user) {
		try {
			User createdUser = userService.createUser(user);
			return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
		} catch (IllegalArgumentException e) {
			Map<String, String> response = new HashMap<>();
			response.put("error", e.getMessage());
			return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
		}
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> updateUser(@PathVariable String id, @RequestBody User userDetails) {
		try {
			User updatedUser = userService.updateUser(id, userDetails);
			return ResponseEntity.ok(updatedUser);
		} catch (IllegalArgumentException e) {
			Map<String, String> response = new HashMap<>();
			response.put("error", e.getMessage());
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteUser(@PathVariable String id) {
		try {
			userService.deleteUser(id);
			return ResponseEntity.noContent().build();
		} catch (IllegalArgumentException e) {
			Map<String, String> response = new HashMap<>();
			response.put("error", e.getMessage());
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
		}
	}

	@PostMapping("/auth")
    public ResponseEntity<?> authenticateUser(@RequestBody Map<String, String> credentials) {
        String login = credentials.get("login");
        String password = credentials.get("password");
        if (login == null || password == null) {
            Map<String, String> response = new HashMap<>();
			response.put("error", "Login and password are required");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
        
        Optional<User> user = userService.authenticate(login, password);
		return user.map(ResponseEntity::ok)
            .orElseGet(() -> ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
    }
	
	@GetMapping("/recentActivities")
public ResponseEntity<List<User>> getRecentActivities() {
    return ResponseEntity.ok(userService.getRecentActivities());
}

}