package com.chainhaus.mtg.repository;

import com.chainhaus.mtg.entity.Minted;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by Jamali on 9/13/2021.
 */
public interface MintedRepository extends CrudRepository<Minted, Long> {
    List<Minted> findAllByCreatedByOrderByCreatedDate(Long createdBy);
    @Override
    List<Minted> findAll();
}
