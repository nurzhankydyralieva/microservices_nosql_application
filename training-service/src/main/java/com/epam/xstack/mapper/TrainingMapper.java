package com.epam.xstack.mapper;//package com.epam.xstack.mapper;

import com.epam.xstack.model.dto.TrainingDTO;
import com.epam.xstack.model.entity.Training;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TrainingMapper {
    Training toEntity(TrainingDTO trainingDTO);

    TrainingDTO toDTO(Training training);
}
