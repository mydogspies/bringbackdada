package com.bringbackdada.site.services;

import com.bringbackdada.site.model.License;
import com.bringbackdada.site.repositories.LicenseRepository;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class LicenseServiceImpl implements LicenseService {

    private final LicenseRepository licenseRepository;

    public LicenseServiceImpl(LicenseRepository licenseRepository) {
        this.licenseRepository = licenseRepository;
    }

    @Override
    public Set<License> findAll() {
        Iterable<License> result = licenseRepository.findAll();
        Set<License> resultSet = StreamSupport.stream(result.spliterator(), false)
                .collect(Collectors.toSet());
        return resultSet;
    }

    @Override
    public License findById(Long aLong) {
        return null;
    }

    @Override
    public License save(License object) {
        licenseRepository.save(object);
        return object;
    }

    @Override
    public void delete(License object) {

    }

    @Override
    public void deleteById(Long aLong) {

    }

    @Override
    public int count(Set<License> set) {
        return 0;
    }
}
