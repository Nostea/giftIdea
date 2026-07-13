package org.nostea.gift.db.repository;

import org.nostea.gift.db.entity.GiftEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GiftRepository extends JpaRepository<GiftEntity, Long> {
}
