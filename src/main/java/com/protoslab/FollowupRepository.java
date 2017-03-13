package com.protoslab;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FollowupRepository extends CrudRepository<Followup, Long> {

    Page<Followup> findAll(Pageable pageable);

    Page<Followup> findByContactId(long contactId, Pageable pageable);
}