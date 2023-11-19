package com.emtech.employeemanagement;

import com.emtech.employeemanagement.data.EntityResponse;
import com.emtech.employeemanagement.data.departmentdata.DepartmentCreateRequest;
import com.emtech.employeemanagement.data.employeedata.AddEmployeeRequest;
import com.emtech.employeemanagement.model.department.Department;
import com.emtech.employeemanagement.repos.DepartmentRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class EmployeeManagementApplicationTests {

	@LocalServerPort
	private int port;
	private String baseUrl = "http://localhost:";
	private String baseUrl2 = "http://localhost:";
	private static RestTemplate restTemplate;
	@Autowired
	private H2Repo h2Repo;

	@BeforeAll
	public static void init(){
		restTemplate = new RestTemplate();
	}

	@BeforeEach
	public void setUp() {
		baseUrl = baseUrl + port + "/api/v1/employees";
		baseUrl2 = baseUrl2 + port + "/api/v1/departments";
	}

	@Test
	public void testEmployeeAddition() {
		//Given
		AddEmployeeRequest requestBody = new AddEmployeeRequest("Newts Kags", "nkags@gmail.com", "Software Dev");
		DepartmentCreateRequest requestBody2 = new DepartmentCreateRequest("Software Dev", 1);
		h2Repo.deleteAll();

		//When
		ResponseEntity<EntityResponse> deptResponseEntity = restTemplate.postForEntity(baseUrl2 + "/new-department", requestBody2, EntityResponse.class);
		ResponseEntity<EntityResponse> empResponseEntity = restTemplate.postForEntity(baseUrl + "/add", requestBody, EntityResponse.class);

		//Then
		assertThat(empResponseEntity.getStatusCodeValue()).isEqualTo(200);
		EntityResponse entityResponse = empResponseEntity.getBody();
		assertThat(entityResponse).isNotNull();
		assertThat(entityResponse.getMessage()).isEqualTo("Employee added successfully");
		assertThat(entityResponse.getStatusCode()).isEqualTo(201);
		assertThat(h2Repo.findAll().size()).isEqualTo(1);
	}

}
