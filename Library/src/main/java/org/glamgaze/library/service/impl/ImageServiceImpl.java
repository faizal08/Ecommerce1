package org.glamgaze.library.service.impl;

import org.glamgaze.library.model.Image;
import org.glamgaze.library.repository.ImageRepository;
import org.glamgaze.library.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImageServiceImpl implements ImageService
{
    @Autowired
    private ImageRepository imageRepository;
    @Override
    public List<Image> findProductImages(long id) {
        return imageRepository.findImageBy(id);
    }
    @Override
    public List<Image> findAll() {
        return imageRepository.findAll();
    }
}
