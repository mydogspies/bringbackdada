package com.bringbackdada.site.services;

import com.bringbackdada.site.commands.GalleryCommand;
import com.bringbackdada.site.commands.converters.GalleryCmdToGallery;
import com.bringbackdada.site.commands.converters.GalleryToGalleryCmd;
import com.bringbackdada.site.model.Gallery;
import com.bringbackdada.site.repositories.GalleryRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class GalleryServiceImpl implements GalleryService {

    private final Logger logger = LoggerFactory.getLogger(GalleryServiceImpl.class);

    private final GalleryRepository galleryRepository;
    private final GalleryCmdToGallery galleryCmdToGallery;
    private final GalleryToGalleryCmd galleryToGalleryCmd;

    public GalleryServiceImpl(GalleryRepository galleryRepository, GalleryCmdToGallery galleryCmdToGallery, GalleryToGalleryCmd galleryToGalleryCmd) {
        this.galleryRepository = galleryRepository;
        this.galleryCmdToGallery = galleryCmdToGallery;
        this.galleryToGalleryCmd = galleryToGalleryCmd;
    }

    @Override
    public List<Gallery> getGalleryByFeatured() {
        Iterable<Gallery> result = galleryRepository.findAll();
        List<Gallery> galleryList = new ArrayList<>();
        for (Gallery gallery : result) {
            if (gallery.getFrontPageFeatured().equals(true)) {
                galleryList.add(gallery);
            }

        }
        return galleryList;
    }

    @Override
    public List<Gallery> findAll() {
        Iterable<Gallery> result = galleryRepository.findAll();
        List<Gallery> resultSet = StreamSupport.stream(result.spliterator(), false)
                .collect(Collectors.toList());
        return resultSet;
    }

    @Override
    public List<GalleryCommand> findAllAsCommands() {
        List<GalleryCommand> galleryCmdList = new ArrayList<>();
        Iterable<Gallery> result = galleryRepository.findAll();
        List<Gallery> resultSet = StreamSupport.stream(result.spliterator(), false)
                .collect(Collectors.toList());
        for (Gallery gallery : resultSet) {
            galleryCmdList.add(galleryToGalleryCmd.convert(gallery));
        }
        return galleryCmdList;
    }

    @Override
    public Gallery findById(Long aLong) {
        Optional<Gallery> galleryOpt = galleryRepository.findById(aLong);

        if (galleryOpt.isEmpty()) {
            logger.error("findById(): No such gallery with id " + aLong);
            throw new RuntimeException("GalleryOld not found");
        }

        return galleryOpt.get();
    }

    @Override
    public Gallery save(Gallery object) {
        galleryRepository.save(object);
        return object;
    }

    @Override
    public GalleryCommand saveGalleryCommand(GalleryCommand command) {
        Gallery detachedGallery = galleryCmdToGallery.convert(command);
        Gallery savedGallery = galleryRepository.save(detachedGallery);
        logger.debug("Gallery detached from cmd object and saved as id: " + savedGallery.getId());
        return galleryToGalleryCmd.convert(savedGallery);
    }

    @Override
    public void delete(Gallery object) {

    }

    @Override
    public void deleteById(Long aLong) {
        galleryRepository.deleteById(aLong);
    }

    @Override
    public List<Gallery> sortGalleryByGalleryOrder(List<Gallery> unsortedGalleryList) {
        List<Gallery> sortedList = unsortedGalleryList.stream()
                .sorted(Comparator.comparing(Gallery::getGalleryOrder))
                .collect(Collectors.toList());
        return sortedList;
    }

    @Override
    public int count(List<Gallery> set) {
        return (int) galleryRepository.count();
    }

}
