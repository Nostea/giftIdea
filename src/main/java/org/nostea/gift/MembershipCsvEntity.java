package org.nostea.gift;

import java.time.LocalDateTime;

public record MembershipCsvEntity(long userId, long groupId, LocalDateTime joinedAt) {

}
