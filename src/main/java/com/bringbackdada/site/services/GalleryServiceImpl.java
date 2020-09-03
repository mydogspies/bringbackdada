package com.bringbackdada.site.services;

import com.bringbackdada.site.model.Gallery;
import com.bringbackdada.site.repositories.GalleryRepository;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class GalleryServiceImpl implements GalleryService {

    private final GalleryRepository galleryRepository;

    public GalleryServiceImpl(GalleryRepository galleryRepository) {
        this.galleryRepository = galleryRepository;
    }

    @Override
    public Gallery getGalleryByFeatured() {
        Iterable<Gallery> result = galleryRepository.findAll();
        for (Gallery gallery : result) {
            if (gallery.getFeatured().equals(true));
            return gallery;
        }
        return null;
    }

    @Override
    public Set<Gallery> findAll() {
        Iterable<Gallery> result = galleryRepository.findAll();
        Set<Gallery> resultSet = StreamSupport.stream(result.spliterator(), false)
                .collect(Collectors.toSet());
        return resultSet;
    }

    @Override
    public Gallery findById(Long aLong) {
        return null;
    }

    @Override
    public Gallery save(Gallery object) {
        galleryRepository.save(object);
        return object;
    }

    @Override
    public void delete(Gallery object) {

    }

    @Override
    public void deleteById(Long aLong) {

    }

    @Override
    public int count(Set<Gallery> set) {
        return 0;
    }

}
