package org.nostea.gift.controller;

import org.nostea.gift.db.entity.GiftEntity;
import org.nostea.gift.model.Gift;
import org.nostea.gift.service.GiftService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/gifts")
public class GiftController {
    private final GiftService giftService;

    public GiftController(GiftService giftService) {
        this.giftService = giftService;
    }

    @GetMapping
    public ResponseEntity<List<GiftEntity>> getAllGiftsResponse() {
        List<GiftEntity> gifts = giftService.getAllGifts();
        if(gifts.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(gifts);
    }

    @GetMapping("{id}")
    public ResponseEntity<GiftEntity> getGiftByIdResponse(@PathVariable long id){
        if (id <= 0) {
            return ResponseEntity.badRequest().build();
        }

        GiftEntity gift = giftService.getGiftById(id);

        if (gift == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(gift);
        }
    }

}
