package com.iuh.rencar_project.utils.mapper;

import com.iuh.rencar_project.dto.request.ReservationRequest;
import com.iuh.rencar_project.dto.response.ReservationResponse;
import com.iuh.rencar_project.entity.Reservation;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

/**
 * @author Duy Trần Thế
 * @version 1.0
 * @datetime 6/14/2021 6:27 PM
 */
@Mapper(componentModel = "spring")
public interface IReservationMapper {

    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "state", expression = "java(com.iuh.rencar_project.utils.enums.ReservationState.PENDING)")
    })
    Reservation toEntity(ReservationRequest reservationRequest);

    ReservationResponse toResponse(Reservation reservation);
}
