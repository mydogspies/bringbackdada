package com.bringbackdada.site.repositories;

import com.bringbackdada.site.model.License;
import com.bringbackdada.site.model.LicenseCategory;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface LicenseRepository extends CrudRepository<License, Long> {

    Optional<License> findByCategory(Enum<LicenseCategory> category);
}
