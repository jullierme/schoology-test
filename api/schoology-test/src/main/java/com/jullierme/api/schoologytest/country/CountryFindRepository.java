package com.jullierme.api.schoologytest.country;

import com.jullierme.api.schoologytest.domain.Country;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import java.util.List;
import java.util.Optional;

public interface CountryFindRepository extends Repository<Country, Long> {
    @Query("select c from Country c where \n" +
            "   lower(name) like lower(CONCAT('%',:name,'%')) \n" +
            "   and :name is not null \n" +
            "   and LENGTH(trim(:name)) > 0 \n" +
            "order by \n" +
            "   name\n")
    Optional<List<Country>> findByName(String name, Pageable pageable);

    default Optional<List<Country>> findByName(String name) {
        return findByName(name, null);
    }
}
