package org.glamgaze.library.repository;

import org.glamgaze.library.model.Offer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OfferRepository extends JpaRepository<Offer,Long>
{
    Offer findById(long id);
}