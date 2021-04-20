package org.study.pagination.contollers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.study.pagination.models.Employee;
import org.study.pagination.services.EmployeeService;

@Controller
public class EmployeeController {
	
	@Autowired
	private EmployeeService empServ;
	
	//list all emps
	@GetMapping("/")
	public String homepage(Model model) {

 
		return findPagination(1, "firstName", "asc", model);
	}

	@GetMapping("/newEmployee")
	public String viewEmpForm(Model model) {
		Employee employee = new Employee ();
		model.addAttribute("employee", employee);
		return "new_employee";
	}

	@PostMapping("/saveEmployee")
	public String saveEmployee(@ModelAttribute("employee") Employee employee) {
		// save employee to database
		empServ.saveEmployee(employee);
		return "redirect:/";
	}
	
	@GetMapping("/updateForm/{id}")
	public String updateForm(@PathVariable ( value = "id") long id, Model model) {
		
		Employee employee = empServ.getEmployeeById(id);
		
		model.addAttribute("employee", employee);
		return "update_employee";
	}
	
	@GetMapping("/deleteEmployee/{id}")
	public String deleteEmployee(@PathVariable (value = "id") long id) {
		
		this.empServ.deleteEmpById(id);
		return "redirect:/";
	}
	
	@GetMapping("/page/{pageNo}")
	public String findPagination(@PathVariable (value = "pageNo") int pageNo, 
			@RequestParam("sortField") String sortField,
			@RequestParam("sortDir") String sortDir,
			Model model) {
		int pageSize = 5;
		
		Page<Employee> page = empServ.findPangination(pageNo, pageSize, sortField, sortDir);
		List<Employee> listEmployees = page.getContent();
		
		model.addAttribute("currentPage", pageNo);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("totalItems", page.getTotalElements());
		
		model.addAttribute("sortField", sortField);
		model.addAttribute("sortDir", sortDir);
		model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
		
		model.addAttribute("listEmployees", listEmployees);
		return "index";
	}

}
