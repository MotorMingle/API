package fr.motormingle.api.service;

import fr.motormingle.api.exception.NotFoundException;

import java.util.List;

/**
 * Interface for the service layer, used to find entities
 *
 * @param <T> the entity type
 */
public interface FindService<T, I> {

    List<T> findAll();

    T findById(I id) throws NotFoundException;
}
