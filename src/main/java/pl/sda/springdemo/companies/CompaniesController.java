package pl.sda.springdemo.companies;


import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class CompaniesController {

    private final CompanyService companyService;

    public CompaniesController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @GetMapping("/companies")
    public String listCompanies(){
        return "companies/companies";
    }

    @PostMapping(value="/companies", consumes= MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String findCompanies(@RequestParam(required = false) String NIP,
                                @RequestParam(required = false) String nazwa,
                                Model model){

        List<Company> foundCompanies=companyService.find(NIP,nazwa);
        model.addAttribute("companies", foundCompanies);

        return "companies/companies";

    }
}
