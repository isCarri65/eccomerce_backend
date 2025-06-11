package com.ecommerce.services;

import com.ecommerce.entities.Size;
import com.ecommerce.repositories.SizeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SizeService extends BaseService<Size, Long> {

    private final SizeRepository sizeRepository;

    public SizeService(SizeRepository sizeRepository) {
        super(sizeRepository);
        this.sizeRepository = sizeRepository;
    }

    public Size create(Size size) {
        size.setDeleted(false); // por defecto
        return sizeRepository.save(size);
    }

    public Optional<Size> getById(Long id) {
        return sizeRepository.findByIdAndDeletedFalse(id);
    }

    public List<Size> getAll() {
        return sizeRepository.findAllByDeletedFalse();
    }

    public Size update(Long id, Size updated) {
        Size existing = sizeRepository.findByIdAndDeletedFalse(id)
                .orElseThrow(() -> new RuntimeException("Tamaño no encontrado"));

        existing.setName(updated.getName());
        existing.setType(updated.getType());
        return sizeRepository.save(existing);
    }

    public void delete(Long id) {
        Size existing = sizeRepository.findByIdAndDeletedFalse(id)
                .orElseThrow(() -> new RuntimeException("Tamaño no encontrado"));
        existing.setDeleted(true);
        sizeRepository.save(existing);
    }
}
