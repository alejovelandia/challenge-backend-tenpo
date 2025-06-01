package com.tenpo.challenge.repository.mapper;

import com.tenpo.challenge.dto.TransactionHistory;
import com.tenpo.challenge.repository.entity.TransactionHistoryEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TransactionHistoryMapper {

    TransactionHistory toDomain(final TransactionHistoryEntity entity);

    List<TransactionHistory> toDomainList(final List<TransactionHistoryEntity> entityList);

    TransactionHistoryEntity toEntity(final TransactionHistory dto);
}
