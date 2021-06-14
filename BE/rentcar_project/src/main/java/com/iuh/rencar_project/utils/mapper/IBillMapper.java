package com.iuh.rencar_project.utils.mapper;

import com.iuh.rencar_project.dto.request.BillRequest;
import com.iuh.rencar_project.dto.response.BillResponse;
import com.iuh.rencar_project.entity.Bill;
import com.iuh.rencar_project.utils.mapper.annotation.*;
import com.iuh.rencar_project.utils.mapper.helper.HelperMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;

/**
 * @author Duy Trần Thế
 * @version 1.0
 * @date 5/15/2021 10:09 AM
 */
@Mapper(componentModel = "spring", uses = {ICarMapper.class, HelperMapper.class})
public interface IBillMapper {

    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "createdBy", ignore = true),
            @Mapping(target = "createdDate", ignore = true),
            @Mapping(target = "modifiedBy", ignore = true),
            @Mapping(target = "modifiedDate", ignore = true),
            @Mapping(target = "type", source = "type", qualifiedBy = StringToTypeNameMapping.class),
            @Mapping(target = "car", source = "car", qualifiedBy = StringToCarMapping.class),
            @Mapping(target = "state", expression = "java(com.iuh.rencar_project.utils.enums.BillState.RENTED)"),
            @Mapping(target = "billAmount", ignore = true),
            @Mapping(target = "charges", ignore = true),
            @Mapping(target = "note", ignore = true)
    })
    Bill toEntityByStaff(BillRequest billRequest);

    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "fullname", ignore = true),
            @Mapping(target = "phoneNumber", ignore = true),
            @Mapping(target = "email", ignore = true),
            @Mapping(target = "createdBy", ignore = true),
            @Mapping(target = "createdDate", ignore = true),
            @Mapping(target = "modifiedBy", ignore = true),
            @Mapping(target = "modifiedDate", ignore = true),
            @Mapping(target = "type", source = "type", qualifiedBy = StringToTypeNameMapping.class),
            @Mapping(target = "car", source = "car", qualifiedBy = StringToCarMapping.class),
            @Mapping(target = "state", expression = "java(com.iuh.rencar_project.utils.enums.BillState.RENTED)"),
            @Mapping(target = "billAmount", ignore = true),
            @Mapping(target = "charges", ignore = true),
            @Mapping(target = "note", ignore = true)
    })
    void paidBill(BillRequest billRequest, @MappingTarget Bill bill);

    @Mappings({
            @Mapping(target = "createdBy", source = "createdBy", qualifiedBy = UserToStringMapping.class),
            @Mapping(target = "modifiedBy", source = "modifiedBy", qualifiedBy = UserToStringMapping.class),
            @Mapping(target = "type", source = "type", qualifiedBy = BillTypeToStringMapping.class),
            @Mapping(target = "billAmount", source = "billAmount", defaultValue = "0L"),
            @Mapping(target = "charges", source = "charges", defaultValue = "0L"),
    })
    BillResponse toResponse(Bill bill);
}
