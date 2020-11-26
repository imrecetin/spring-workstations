package com.outboxpattern.order.outbox.dao;

import com.outboxpattern.order.outbox.models.OutBox;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface OutBoxRepository extends JpaRepository<OutBox, UUID> {

}
