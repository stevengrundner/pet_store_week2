package pet.store.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import pet.store.controller.model.PetStoreData;
import pet.store.controller.model.PetStoreEmployee;
import pet.store.service.PetStoreService;

@RestController
@RequestMapping("/pet_store")
@Slf4j
public class PetStoreController {
	@Autowired
	private PetStoreService petStoreService;

	@PostMapping("/pet_store")
	@ResponseStatus(code = HttpStatus.CREATED)
	public PetStoreData insertPetStoreData(@RequestBody PetStoreData petStoreData) {
		log.info("Creating Pet Store {}", petStoreData);
		return petStoreService.savePetStore(petStoreData);
	}

	@PutMapping("/pet_store/{petStoreId}")
	public PetStoreData updatePetStoreData(@PathVariable Long petStoreId, @RequestBody PetStoreData petStoreData) {
		petStoreData.setPetStoreId(petStoreId);
		log.info("Updating Pet Store Id {}", petStoreData);
		return petStoreService.savePetStore(petStoreData);
	}

	@PostMapping("/pet_store/{petStoreId}/employee")
	@ResponseStatus(code = HttpStatus.CREATED)
	public PetStoreEmployee inSertPetStoreEmployee(@PathVariable Long petStoreId,
			@RequestBody PetStoreEmployee petStoreEmployee) {
		log.info("Creating employee {} for Pet Store with ID={}", petStoreId, petStoreEmployee);
		return petStoreService.saveEmployee(petStoreId, petStoreEmployee);
	} // LINES 39-45 REVIEW

}