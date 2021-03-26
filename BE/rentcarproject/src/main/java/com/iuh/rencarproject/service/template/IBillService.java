/**
 * @author DuyTT10
 * @date Mar 26, 2021
 * @version 1.0
 */
package com.iuh.rencarproject.service.template;

import java.util.List;

import com.iuh.rencarproject.dto.request.BillRequestDTO;
import com.iuh.rencarproject.dto.response.BillResponseDTO;
import com.iuh.rencarproject.entity.Bill;

public interface IBillService {

	Bill createBill(BillRequestDTO billRequestDTO);
	
	Bill findById(Long id);
	
	List<BillResponseDTO> findAll(); 
}

