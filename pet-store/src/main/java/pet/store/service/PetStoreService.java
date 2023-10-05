package pet.store.service;

import java.util.NoSuchElementException;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pet.store.controller.model.PetStoreCustomer;
import pet.store.controller.model.PetStoreData;
import pet.store.controller.model.PetStoreEmployee;
import pet.store.dao.CustomerDao;
import pet.store.dao.EmployeeDao;
import pet.store.dao.PetStoreDao;
import pet.store.entity.Customer;
import pet.store.entity.Employee;
import pet.store.entity.PetStore;

@Service
public class PetStoreService {

	@Autowired
	private PetStoreDao petStoreDao;

	@Autowired
	private EmployeeDao employeeDao;

	@Autowired
	private CustomerDao customerDao;

	@Transactional(readOnly = false)
	public PetStoreData savePetStore(PetStoreData petStoreData) {
		Long petStoreId = petStoreData.getPetStoreId();
		PetStore petStore = findOrCreatePetStore(petStoreId);

		copyPetStoreFields(petStore, petStoreData);

		return new PetStoreData(petStoreDao.save(petStore));
	}

	private void copyPetStoreFields(PetStore petStore, PetStoreData petStoreData) {
		petStore.setPetStoreId(petStoreData.getPetStoreId());
		petStore.setPetStoreName(petStoreData.getPetStoreName());
		petStore.setPetStoreAddress(petStoreData.getPetStoreAddress());
		petStore.setPetStoreCity(petStoreData.getPetStoreCity());
		petStore.setPetStoreState(petStoreData.getPetStoreState());
		petStore.setPetStoreZip(petStoreData.getPetStoreZip());
		petStore.setPetStorePhone(petStoreData.getPetStorePhone());

	}

	private PetStore findOrCreatePetStore(Long petStoreId) {
		PetStore petStore;

		if (Objects.isNull(petStoreId)) {
			petStore = new PetStore();
		} else {
			petStore = findPetStoreById(petStoreId);
		}
		return petStore;
	}

	private PetStore findPetStoreById(Long petStoreId) {
		return petStoreDao.findById(petStoreId)
				.orElseThrow(() -> new NoSuchElementException("Pet Store with ID=" + petStoreId + " was not found."));

	}

//	PET STORE EMPLOYEE CODES BELOW

	@Transactional(readOnly = false)
	public PetStoreEmployee saveEmployee(Long petStoreId, PetStoreEmployee petStoreEmployee) {

		Long employeeId = petStoreEmployee.getEmployeeId(); //
		findPetStoreById(petStoreId);
		findOrCreatePetStore(petStoreId); // ??????? NOT SURE
//		Long petStore = petStoreEmployee.getEmployeeId();
		Employee employee = findOrCreateEmployee(petStoreId, employeeId);

		copyEmployeeFields(employee, petStoreEmployee); // this is a method call - method needs to be created

//		employee.setPetStore(petStore); // set petStore in employee
//
//		petStore.getEmployees().add(employee); // add employee to pet store list of employees
//
		return new PetStoreEmployee(employeeDao.save(employee)); // create new employee which passes in employeeDAO

	}

	private void copyEmployeeFields(Employee employee, PetStoreEmployee petStoreEmployee) {

		employee.setEmployeeId(petStoreEmployee.getEmployeeId());
		employee.setEmployeeFirstName(petStoreEmployee.getEmployeeFirstName());
		employee.setEmployeeLastName(petStoreEmployee.getEmployeeLastName());
		employee.setEmployeePhone(petStoreEmployee.getEmployeePhone());
		employee.setEmployeeJobTitle(petStoreEmployee.getEmployeeJobTitle());
		// The method should take an Employee as a parameter and a PetStoreEmployee as a
		// parameter.
		// Copy all matching PetStoreEmployee fields to the Employee object.
	}

	private Employee findEmployeeById(Long employeeId) { // create method findEmployeeById taking
															// petStoreId and EmployeeId as parameters
		return employeeDao.findById(employeeId)
				.orElseThrow(() -> new NoSuchElementException("Employee with ID=" + employeeId + " was not found."));

	}

	private Employee findOrCreateEmployee(Long petStoreId, Long employeeId) {

		// create a new method findOrCreateEmployee. This method takes employeeId and
		// petStoreId as parameter
		Employee employee; // this method returns an employee object if successful

		if (Objects.isNull(employeeId)) {
			employee = new Employee(); // if the pet store ID is null, it should return a new Employee object

		} else {

			employee = findEmployeeById(employeeId); // if the pet store ID is not null, it should call the method,
														// findEmployeeById
		}
		return employee;
	}

	// PET STORE CUSTOMER CODES BELOW ******************************

	private void copyCustomerFields(Customer customer, PetStoreCustomer petStoreCustomer) {
		customer.setCustomerId(petStoreCustomer.getCustomerId());
		customer.setCustomerFirstName(petStoreCustomer.getCustomerFirstName());
		customer.setCustomerLastName(petStoreCustomer.getCustomerLastName());
		customer.setCustomerEmail(petStoreCustomer.getCustomerEmail());
	}

	private Customer findOrCreateCustomer(Long petStoreId, Long customerId) {
		Customer customer;

		if (Objects.isNull(customerId)) {
			customer = new Customer();
		} else {
			customer = findCustomerById(customerId);
		}
		return customer;

	}

	public PetStoreCustomer saveCustomer(Long petStoreId, PetStoreCustomer petStoreCustomer) {
		findPetStoreById(petStoreId);
		// findOrCreateCustomer(petStoreId);

		return petStoreCustomer;
	}

	private Customer findCustomerById(Long customerId) {
		return customerDao.findById(customerId)
				.orElseThrow(() -> new NoSuchElementException("Customer with ID=" + customerId + " was not found."));

	}
}

//@Transactional(readOnly = false)
//public PetStoreData savePetStore(PetStoreData petStoreData) {
//	Long petStoreId = petStoreData.getPetStoreId();
//	PetStore petStore = findOrCreatePetStore(petStoreId);
//
//	copyPetStoreFields(petStore, petStoreData);

//
//	return new PetStoreData(petStoreDao.save(petStore));
//}