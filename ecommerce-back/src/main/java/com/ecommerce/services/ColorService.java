package com.ecommerce.services;

import com.ecommerce.entities.Color;
import com.ecommerce.repositories.ColorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ColorService extends BaseService<Color, Long> {

    private final ColorRepository colorRepository;

    public ColorService(ColorRepository colorRepository) {
        super(colorRepository);
        this.colorRepository = colorRepository;
    }

    public Color create(Color color) {
        color.setDeleted(false);
        return colorRepository.save(color);
    }

    public Optional<Color> getById(Long id) {
        return colorRepository.findByIdAndDeletedFalse(id);
    }

    public List<Color> getAll() {
        return colorRepository.findAllByDeletedFalse();
    }

    public Color update(Long id, Color updated) {
        Color existing = colorRepository.findByIdAndDeletedFalse(id)
                .orElseThrow(() -> new RuntimeException("Color no encontrado"));

        existing.setName(updated.getName());
        existing.setHex(updated.getHex());

        return colorRepository.save(existing);
    }

    public void delete(Long id) {
        Color existing = colorRepository.findByIdAndDeletedFalse(id)
                .orElseThrow(() -> new RuntimeException("Color no encontrado"));

        existing.setDeleted(true);
        colorRepository.save(existing);
    }
}
