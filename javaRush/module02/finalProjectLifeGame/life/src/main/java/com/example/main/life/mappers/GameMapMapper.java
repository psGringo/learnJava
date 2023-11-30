package com.example.main.life.mappers;

import com.example.main.life.Statistics;
import com.example.main.life.models.Map;
import com.example.main.life.models.animals.Animal;
import com.example.main.life.models.plants.Grass;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.openapi.life.model.GameMapUi;
import org.openapi.life.model.AnimalUi;
import org.openapi.life.model.GrassUi;
import org.openapi.life.model.StatisticsUi;

@Mapper
public interface GameMapMapper {

    GameMapMapper INSTANCE = Mappers.getMapper(GameMapMapper.class);

//    @Mapping(target = "maxX", source = "maxX")
//    @Mapping(target = "maxY", source = "maxY")
    GameMapUi toMapDto(Map gameMap);
    GrassUi toGrassDto(Grass grass);
    AnimalUi toAnimalsDto(Animal animal);
    StatisticsUi toStatistcsDto(Statistics statistics);

}
