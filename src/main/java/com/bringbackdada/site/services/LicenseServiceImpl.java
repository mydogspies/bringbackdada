package com.bringbackdada.site.services;

import com.bringbackdada.site.model.License;
import com.bringbackdada.site.repositories.LicenseRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
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
    public List<License> findAll() {
        Iterable<License> result = licenseRepository.findAll();
        List<License> resultSet = StreamSupport.stream(result.spliterator(), false)
                .collect(Collectors.toList());
        return resultSet;
    }

    @Override
    public License findById(Long aLong) {
        Optional<License> license = licenseRepository.findById(aLong);
        // TODO add isPresent() exception
        return license.get();
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
    public int count(List<License> set) {
        return 0;
    }
}
