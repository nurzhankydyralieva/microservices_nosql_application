package com.epam.xstack.mapper;

import com.epam.xstack.model.dto.TrainerDTO;
import com.epam.xstack.model.entity.Trainer;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TrainerMapper {
    Trainer toEntity(TrainerDTO trainerDTO);

    TrainerDTO toDto(Trainer trainer);
}
