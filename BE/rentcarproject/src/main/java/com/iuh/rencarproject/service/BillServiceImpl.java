/**
 * @author DuyTT10
 * @date Mar 26, 2021
 * @version 1.0
 */
package com.iuh.rencarproject.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Service;

import com.iuh.rencarproject.dto.request.BillRequestDTO;
import com.iuh.rencarproject.dto.response.BillResponseDTO;
import com.iuh.rencarproject.entity.Bill;
import com.iuh.rencarproject.entity.Course;
import com.iuh.rencarproject.repotitory.BillRepository;
import com.iuh.rencarproject.repotitory.CourseRepository;
import com.iuh.rencarproject.service.template.IBillService;

@Service
public class BillServiceImpl implements IBillService {

	final static Logger logger = Logger.getLogger(BillServiceImpl.class);

	@Autowired
	CourseRepository courseRepository;

	@Autowired
	BillRepository billRepository;

	@Override
	public Bill createBill(BillRequestDTO billRequestDTO) {
		Bill bill = null;
		try {
			Course course = courseRepository.findById(billRequestDTO.getCourseId()).get();
			bill = billRequestDTO.convertBillCreationToEntity(course);
			bill = billRepository.saveAndFlush(bill);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return bill;
	}

	@Override
	public Bill findById(Long id) {
		Bill bill = null;
		try {
			bill = billRepository.findById(id).get();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return bill;
	}

	@Override
	public List<BillResponseDTO> findAll() {
		List<BillResponseDTO> bills = new ArrayList<BillResponseDTO>();
		try {
			bills = billRepository.findAll(Sort.by(Order.asc("id"))).stream().map(x -> {
				return new BillResponseDTO(x);
			}).collect(Collectors.toList());
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return bills;
	}

}
