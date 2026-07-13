package org.nostea.gift.service;

import org.nostea.gift.db.entity.GiftEntity;
import org.nostea.gift.db.repository.GiftRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GiftService {

    private final GiftRepository giftRepository;

    public GiftService(GiftRepository giftRepository) {
        this.giftRepository = giftRepository;
    }

    public List<GiftEntity> getAllGifts() {
        try{
            return giftRepository.findAll();

        } catch (Exception e) {
            System.out.println("Error getting all gifts " + e.getMessage());
            return new ArrayList<>();
        }
    }

    public GiftEntity getGiftById(long id) {
        return giftRepository.findById(id).orElse(null);
    }

}
