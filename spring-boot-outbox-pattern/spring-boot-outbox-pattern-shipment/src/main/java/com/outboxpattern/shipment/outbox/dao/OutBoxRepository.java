package com.outboxpattern.shipment.outbox.dao;

import com.outboxpattern.shipment.outbox.models.OutBox;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface OutBoxRepository extends JpaRepository<OutBox, UUID> {

}
