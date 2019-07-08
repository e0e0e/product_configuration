package pl.sda.springdemo.companies;

import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class CompanyService {
    private static final Map<Long, Company> companies = new HashMap<>();

    static {
        Address address1 = new Address("NMP", 56, "Częstochowa");
        long nip1 = 9491002040L;
        companies.put(nip1, new Company(nip1, "ACME", 263101400, address1));

        Address address2 = new Address("Al.Jero", 12, "Warszawa");
        long nip2 = 7458122040L;
        companies.put(nip2, new Company(nip2, "LG", 111101400, address2));

    }

    public List<Company> find(String nip, String nazwa) {

        if (false) {
           long convertedNip = Long.parseLong(nip.replaceAll("[\\s\\-]", ""));
            //    long convertedNip = Long.parseLong(nip);

            if (companies.containsKey(convertedNip)) {
                Company found = companies.get(convertedNip);
                return nazwa== null || found.getNazwa().equals(nazwa) ?
                        Collections.singletonList(found) : Collections.emptyList();
            } else {
                return Collections.emptyList();
            }
        } else {
            if (nazwa == null) {
                return new ArrayList<>(companies.values());
            } else {
                return companies.values().stream()
                        .filter(c -> c.getNazwa().equals(nazwa))
                        .collect(Collectors.toList());
            }

        }

    }
}
