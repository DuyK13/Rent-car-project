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
@Mapper(componentModel = "spring", uses = {ICourseMapper.class, ICarMapper.class, HelperMapper.class})
public interface IBillMapper {
    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "slug", source = "fullname", qualifiedBy = BillSlugMapping.class),
            @Mapping(target = "createdBy", ignore = true),
            @Mapping(target = "createdDate", ignore = true),
            @Mapping(target = "modifiedBy", ignore = true),
            @Mapping(target = "modifiedDate", ignore = true),
            @Mapping(target = "type", ignore = true),
            @Mapping(target = "startTime", ignore = true),
            @Mapping(target = "rentTime", ignore = true),
            @Mapping(target = "course", ignore = true),
            @Mapping(target = "car", ignore = true),
            @Mapping(target = "state", expression = "java(com.iuh.rencar_project.utils.enums.BillState.PENDING)"),
            @Mapping(target = "billAmount", ignore = true),
            @Mapping(target = "lateCharge", ignore = true),
    })
    Bill toEntityByGuest(BillRequest billRequest);

    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "slug", source = "fullname", qualifiedBy = BillSlugMapping.class),
            @Mapping(target = "createdBy", ignore = true),
            @Mapping(target = "createdDate", ignore = true),
            @Mapping(target = "modifiedBy", ignore = true),
            @Mapping(target = "modifiedDate", ignore = true),
            @Mapping(target = "type", source = "type", qualifiedBy = StringToTypeNameMapping.class),
            @Mapping(target = "course", source = "course", qualifiedBy = StringToCourseMapping.class),
            @Mapping(target = "car", source = "car", qualifiedBy = StringToCarMapping.class),
            @Mapping(target = "state", expression = "java(java.util.Objects.isNull(billRequest.getCourse())?com.iuh.rencar_project.utils.enums.BillState.RENTED:com.iuh.rencar_project.utils.enums.BillState.PAID)"),
            @Mapping(target = "billAmount", ignore = true),
            @Mapping(target = "lateCharge", ignore = true),
    })
    Bill toEntityByStaff(BillRequest billRequest);

    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "slug", ignore = true),
            @Mapping(target = "fullname", ignore = true),
            @Mapping(target = "phoneNumber", ignore = true),
            @Mapping(target = "email", ignore = true),
            @Mapping(target = "createdBy", ignore = true),
            @Mapping(target = "createdDate", ignore = true),
            @Mapping(target = "modifiedBy", ignore = true),
            @Mapping(target = "modifiedDate", ignore = true),
            @Mapping(target = "type", source = "type", qualifiedBy = StringToTypeNameMapping.class),
            @Mapping(target = "course", source = "course", qualifiedBy = StringToCourseMapping.class),
            @Mapping(target = "car", source = "car", qualifiedBy = StringToCarMapping.class),
            @Mapping(target = "state", expression = "java(java.util.Objects.isNull(billRequest.getCourse())?com.iuh.rencar_project.utils.enums.BillState.RENTED:com.iuh.rencar_project.utils.enums.BillState.PAID)"),
            @Mapping(target = "billAmount", ignore = true),
            @Mapping(target = "lateCharge", ignore = true),
    })
    void updateEntityToRentedOrPaid(BillRequest billRequest, @MappingTarget Bill bill);

    @Mappings({
            @Mapping(target = "createdBy", source = "createdBy", qualifiedBy = UserToStringMapping.class),
            @Mapping(target = "modifiedBy", source = "modifiedBy", qualifiedBy = UserToStringMapping.class),
            @Mapping(target = "type", source = "type", qualifiedBy = BillTypeToStringMapping.class),
            @Mapping(target = "billAmount", source = "billAmount", defaultValue = "0L"),
            @Mapping(target = "lateCharge", source = "lateCharge", defaultValue = "0L"),
    })
    BillResponse toResponse(Bill bill);
}
