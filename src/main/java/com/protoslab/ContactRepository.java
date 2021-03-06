package com.protoslab;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactRepository extends CrudRepository<Contact, Long> {

    Page<Contact> findAll(Pageable pageable);

    Page<Contact> findByPhoneIsNotNullOrPhoneSummaryIsNotNull(Pageable pagebale);

    Optional<Contact> findByName(String name);
}