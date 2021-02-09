package com.bringbackdada.site.services;

import com.bringbackdada.site.model.Gallery;
import com.bringbackdada.site.model.GalleryItem;
import com.bringbackdada.site.repositories.GalleryItemRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class GalleryItemServiceImpl implements GalleryItemService {

    private final Logger logger = LoggerFactory.getLogger(GalleryItemServiceImpl.class);

    private final GalleryItemRepository galleryItemRepository;

    public GalleryItemServiceImpl(GalleryItemRepository galleryItemRepository) {
        this.galleryItemRepository = galleryItemRepository;
    }

    @Override
    public List<GalleryItem> findAll() {
        Iterable<GalleryItem> result = galleryItemRepository.findAll();
        List<GalleryItem> resultSet = StreamSupport.stream(result.spliterator(), false)
                .collect(Collectors.toList());
        return resultSet;
    }

    @Override
    public GalleryItem findById(Long aLong) {
        Optional<GalleryItem> galleryItemOpt = galleryItemRepository.findById(aLong);
        if (galleryItemOpt.isEmpty()) {
            logger.error("findById(): No such GalleryItem with id " + aLong);
            throw new RuntimeException("GalleryItem not found");
        }
        return galleryItemOpt.get();
    }

    @Override
    public GalleryItem save(GalleryItem object) {
        galleryItemRepository.save(object);
        logger.debug("save(): GalleryItem saved: " + object);
        return object;
    }

    @Override
    public void delete(GalleryItem object) {
        galleryItemRepository.delete(object);
        logger.debug("delete(): GalleryItem " + object + " was permanently deleted");
    }

    @Override
    public void deleteById(Long aLong) {
        galleryItemRepository.deleteById(aLong);
        logger.debug("delete(): GalleryItem with id " + aLong + " was permanently deleted");
    }

    @Override
    public int count(List<GalleryItem> set) {
        return 0;
    }

    @Override
    public List<GalleryItem> sortGalleryItemByGalleryItemOrder(List<GalleryItem> unsortedList) {
        List<GalleryItem> sortedList = unsortedList.stream()
                .sorted(Comparator.comparing(GalleryItem::getItemOrder))
                .collect(Collectors.toList());
        return sortedList;
    }
}
