package com.tenpo.challenge.mapper;

import com.tenpo.api.model.PaginatedTransactionResponse;
import com.tenpo.api.model.Transaction;
import com.tenpo.challenge.dto.PaginatedTransactionHistory;
import com.tenpo.challenge.dto.TransactionHistory;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.util.List;

@Mapper(componentModel = "spring")
public interface TransactionMapper {

    @Mapping(target = "creationDate",source = "creationDate", qualifiedByName = "toSystemOffset")
    Transaction toResponseTransactionDto(final TransactionHistory dto);

    List<Transaction> toResponseDtoList(final List<TransactionHistory> dtoList);

    PaginatedTransactionResponse toResponseDto(final PaginatedTransactionHistory dto);

    @Named("toSystemOffset")
    default OffsetDateTime toSystemOffset(LocalDateTime localDateTime) {
        if (localDateTime == null) return null;
        return localDateTime.atZone(ZoneId.systemDefault()).toOffsetDateTime();
    }
}
