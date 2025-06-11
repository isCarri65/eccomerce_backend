package com.ecommerce.services;

import com.ecommerce.entities.Type;
import com.ecommerce.repositories.TypeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TypeService extends BaseService<Type, Long> {

    private final TypeRepository typeRepository;

    public TypeService(TypeRepository typeRepository) {
        super(typeRepository);
        this.typeRepository = typeRepository;
    }

    public Type create(Type type) {
        type.setDeleted(false);
        return typeRepository.save(type);
    }

    public Optional<Type> getById(Long id) {
        return typeRepository.findByIdAndDeletedFalse(id);
    }

    public List<Type> getAll() {
        return typeRepository.findAllByDeletedFalse();
    }

    public Type update(Long id, Type updated) {
        Type existing = typeRepository.findByIdAndDeletedFalse(id)
                .orElseThrow(() -> new RuntimeException("Tipo no encontrado"));

        existing.setName(updated.getName());
        return typeRepository.save(existing);
    }

    public void delete(Long id) {
        Type existing = typeRepository.findByIdAndDeletedFalse(id)
                .orElseThrow(() -> new RuntimeException("Tipo no encontrado"));
        existing.setDeleted(true);
        typeRepository.save(existing);
    }
}
