package com.iuh.rencar_project.service;

import com.iuh.rencar_project.dto.request.CarRequest;
import com.iuh.rencar_project.entity.Car;
import com.iuh.rencar_project.repository.CarRepository;
import com.iuh.rencar_project.service.template.ICarService;
import com.iuh.rencar_project.service.template.ICategoryService;
import com.iuh.rencar_project.service.template.IFileService;
import com.iuh.rencar_project.utils.enums.Status;
import com.iuh.rencar_project.utils.exception.bind.EntityException;
import com.iuh.rencar_project.utils.exception.bind.NotFoundException;
import com.iuh.rencar_project.utils.mapper.IBillMapper;
import com.iuh.rencar_project.utils.mapper.ICarMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author Duy Trần Thế
 * @version 1.0
 * @date 5/9/2021 2:55 PM
 */
@Service
public class CarServiceImpl implements ICarService {
    private static final Logger logger = LogManager.getLogger(CarServiceImpl.class);
    private final CarRepository carRepository;
    private final ICarMapper carMapper;
    private final ICategoryService categoryService;
    private final IFileService fileService;

    @Autowired
    public CarServiceImpl(CarRepository carRepository, ICarMapper carMapper, ICategoryService categoryService, IFileService fileService, IBillMapper billMapper) {
        this.carRepository = carRepository;
        this.carMapper = carMapper;
        this.categoryService = categoryService;
        this.fileService = fileService;
    }

    @Override
    public String save(CarRequest carRequest, MultipartFile multipartFile) {
        String name = carRequest.getName();
        if (this.existsByName(name))
            throw new EntityException("Car Exists");
        String fileUrl = fileService.uploadCarImage(multipartFile, name);
        Car car = carMapper.toEntity(carRequest);
        car.setImage(fileUrl);
        return categoryService.addCarToCategory(carRequest.getCategoryName(), car);
    }

    @Override
    public String update(Long id, CarRequest carRequest, MultipartFile multipartFile) {
        Car currentCar = this.findById(id);
        if (this.existsByName(carRequest.getName()) && !currentCar.getName().equals(carRequest.getName()))
            throw new EntityException("Car Exists");
        carMapper.updateEntity(carRequest, currentCar);
        currentCar.setImage(fileService.updateCarImage(multipartFile, currentCar));
        return categoryService.updateCarToCategory(carRequest.getCategoryName(), currentCar, currentCar);
    }

//    @Override
//    public Boolean saveBillToCar(Bill bill, String carName) {
//        Car car = this.findByName(carName);
//        Set<Bill> bills = car.getBills();
//        if(bills.add(bill))
//            return false;
//        car.setBills(bills);
//        try{
//            carRepository.saveAndFlush(car);
//        }catch(Exception e){
//            logger.error("Bill Exception: ", e);
//            return false;
//        }
//        return true;
//    }

    @Override
    public String setAvailability(Long id) {
        Car currentCar = this.findById(id);
        String message;
        if (currentCar.getStatus() == Status.ENABLE) {
            currentCar.setStatus(Status.DISABLE);
            message = "Disable car successful";
        } else {
            currentCar.setStatus(Status.ENABLE);
            message = "Enable car successful";
        }
        try {
            carRepository.saveAndFlush(currentCar);
        } catch (Exception e) {
            logger.error("Car Exception: ", e);
            throw new EntityException("Car status change failed");
        }
        return message;
    }

    @Override
    public String delete(Long id) {
        try {
            carRepository.deleteById(id);
        } catch (Exception e) {
            logger.error("Car Exception: ", e);
            throw new EntityException("Car delete failed");
        }
        return "Car delete successful";
    }

    @Override
    public Car findBySlug(String slug) {
        return carRepository.findBySlug(slug).orElseThrow(() -> new NotFoundException("Car not found"));
    }

    @Override
    public Car findBySlugForGuest(String slug) {
        return carRepository.findBySlugAndStatus(slug, Status.ENABLE).orElseThrow(() -> new NotFoundException("Car not found"));
    }

    @Override
    public Car findById(Long id) {
        return carRepository.findById(id).orElseThrow(() -> new NotFoundException("Car not Found"));
    }

    @Override
    public Boolean existsByName(String name) {
        return carRepository.existsByName(name);
    }

    @Override
    public Car findByName(String name) {
        return carRepository.findByName(name).orElseThrow(() -> new NotFoundException("Car not found"));
    }

    @Override
    public Page<Car> findAllPaginated(int pageNo) {
        Pageable pageable = PageRequest.of(pageNo - 1, 5, Sort.by(Sort.Order.asc("id")));
        return carRepository.findAll(pageable);
    }
}
