package org.glamgaze.library.service;

import org.glamgaze.library.model.Image;

import java.util.List;

public interface ImageService
{
    List<Image> findProductImages(long id);
    List<Image> findAll();
}
