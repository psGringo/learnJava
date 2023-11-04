package com.example.main.petstore;

import com.example.main.greetings.GreetingsService;
import java.util.List;
import lombok.AllArgsConstructor;
import org.openapi.petstore.model.PetUI;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class PetStoreController implements org.openapi.petstore.api.PetsApi {

    private GreetingsService greetingsService;

    @GetMapping("/pets")
    public String index() {
        return greetingsService.sayHello();
    }

    @Override
    public ResponseEntity<Void> createPets() {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<PetUI>> listPets(Integer limit) {
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<PetUI> showPetById(String petId) {
        return new ResponseEntity<>(null, HttpStatus.OK);
    }
}
