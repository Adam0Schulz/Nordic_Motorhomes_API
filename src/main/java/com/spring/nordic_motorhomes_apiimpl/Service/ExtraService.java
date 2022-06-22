package com.spring.nordic_motorhomes_apiimpl.Service;

import com.spring.nordicmotorhomes.Entity.Extra;
import com.spring.nordicmotorhomes.repository.ExtraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

// Adam
@Service
public class ExtraService {

    @Autowired
    private ExtraRepository extraRepository;

    // Get all extras
    public List<Extra> getAllExtras() {
        return extraRepository.findAll();
    }

    // Get extra by id
    public Extra getExtraById(long id) {
        return extraRepository.findById((long) id).orElse(null);
    }

    // Get extras by id-s
    public Set<Extra> getExtrasByIds(Set<Integer> extraIDs) {
        Set<Extra> extras = new HashSet<>();
        for (int id : extraIDs) {
            Extra extra = getExtraById((long) id);
            if (extra == null) {
                return null;
            }
            extras.add(extra);
        }
        return extras;
    }

    // Get extra's total price
    public double getExtrasTotalPrice(Set<Extra> extras) {
        double total = 0;
        for (Extra extra : extras) {
            total += extra.getPrice();
        }
        return total;
    }

    // Create extra
    public Extra createExtra(double price, String type) {
        Extra newExtra = Extra.builder()
                .price(price)
                .type(type)
                .build();
        extraRepository.save(newExtra);
        return newExtra;
    }
}
