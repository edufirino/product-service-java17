package com.bnp.manualtransaction.mapper;
import com.bnp.manualtransaction.domain.entity.ManualTransactionEntity;
import com.bnp.manualtransaction.domain.entity.ManualTransactionId;
import com.bnp.manualtransaction.domain.dto.ManualTransactionDTO;
import com.bnp.manualtransaction.domain.dto.ManualTransactionResponse;

import java.math.BigDecimal;
public final class ManualTransactionMapper {
    private ManualTransactionMapper() {}
    public static ManualTransactionDTO toDTO(ManualTransactionEntity entity) {
        if (entity == null) return null;
        BigDecimal amount = entity.getAmount() != null ? entity.getAmount() : null;

        return new ManualTransactionDTO(
                entity.getId().getMonth(),
                entity.getId().getYear(),
                entity.getId().getEntryNumber(),
                entity.getProductCode(),
                entity.getCosifCode(),
                entity.getDescription(),
                entity.getTransactionDate(),
                entity.getUserCode(),
                amount
        );
    }

    public static ManualTransactionEntity toEntity(ManualTransactionDTO dto) {
        if (dto == null)
            return null;
        ManualTransactionId id = new ManualTransactionId(
                dto.getMonth(),
                dto.getYear(),
                dto.getEntryNumber());

        BigDecimal amount = dto.getAmount() != null ? dto.getAmount() : null;
        return new ManualTransactionEntity(
                id,
                dto.getProductCode(),
                dto.getCosifCode(),
                dto.getDescription(),
                dto.getTransactionDate(),
                dto.getUserCode(),
                amount);
    }

    public static ManualTransactionResponse toResponseDTO(ManualTransactionEntity entity, String productDescription) {
        if (entity == null)
            return null;
        BigDecimal amount = entity.getAmount() != null ? entity.getAmount() : null;

        return new ManualTransactionResponse(
                entity.getId().getMonth(),
                entity.getId().getYear(),
                entity.getId().getEntryNumber(),
                entity.getProductCode(),
                productDescription,
                entity.getDescription(),
                amount);
    }
    

}
