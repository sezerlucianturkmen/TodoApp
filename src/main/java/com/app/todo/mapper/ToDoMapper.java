package com.app.todo.mapper;

import com.app.todo.dto.request.CreateToDoRequestDto;
import com.app.todo.dto.request.UpdateToDoRequestDto;
import com.app.todo.dto.response.ToDoResponseDto;
import com.app.todo.entity.ToDo;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ToDoMapper {
    ToDoMapper INSTANCE = Mappers.getMapper(ToDoMapper.class);

    ToDo toToDo(final CreateToDoRequestDto dto);
    ToDoResponseDto toToDoResponseDto(final ToDo todo);

    void update(@MappingTarget ToDo toDo, UpdateToDoRequestDto updateToDoRequestDto);


}
