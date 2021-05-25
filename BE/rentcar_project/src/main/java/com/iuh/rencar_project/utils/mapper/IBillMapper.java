package com.iuh.rencar_project.utils.mapper;

import com.iuh.rencar_project.dto.request.BillRequest;
import com.iuh.rencar_project.dto.response.BillResponse;
import com.iuh.rencar_project.entity.Bill;
import com.iuh.rencar_project.entity.Course;
import com.iuh.rencar_project.utils.mapper.annotation.StringToCarMapping;
import com.iuh.rencar_project.utils.mapper.annotation.StringToCourseMapping;
import com.iuh.rencar_project.utils.mapper.annotation.UserToStringMapping;
import com.iuh.rencar_project.utils.mapper.helper.HelperMapper;
import org.mapstruct.*;

/**
 * @author Duy Trần Thế
 * @version 1.0
 * @date 5/15/2021 10:09 AM
 */
@Mapper(componentModel = "spring", uses = {ICourseMapper.class, ICarMapper.class, HelperMapper.class})
public interface IBillMapper {
    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "slug", ignore = true),
            @Mapping(target = "staff", ignore = true),
            @Mapping(target = "state", ignore = true),
            @Mapping(target = "createdDate", ignore = true),
            @Mapping(target = "courses", source = "courses", qualifiedBy = StringToCourseMapping.class),
            @Mapping(target = "car", source = "car", qualifiedBy = StringToCarMapping.class)
    })
    Bill toEntity(BillRequest billRequest);

    @InheritConfiguration(name = "toEntity")
    void updateEntity(BillRequest billRequest, @MappingTarget Bill bill);

    @Mappings({
            @Mapping(source = "staff", target = "staff", qualifiedBy = UserToStringMapping.class),
            @Mapping(target = "totalMoney", ignore = true)
    })
    BillResponse toResponse(Bill bill);

    @AfterMapping
    public default void calculateTotalPrice(Bill bill, @MappingTarget BillResponse billResponse) {
        Long totalMoney = 0L;
        for (Course course : bill.getCourses()) {
            totalMoney += course.getPrice();
        }
        totalMoney += bill.getCar().getPrice();

        totalMoney += bill.getExtraTime() * bill.getCar().getPrice();

        billResponse.setTotalMoney(totalMoney);
    }
}
