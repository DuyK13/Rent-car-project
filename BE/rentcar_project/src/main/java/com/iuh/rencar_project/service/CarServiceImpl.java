package com.iuh.rencar_project.service;

import com.iuh.rencar_project.dto.request.CarRequest;
import com.iuh.rencar_project.entity.Car;
import com.iuh.rencar_project.repository.CarRepository;
import com.iuh.rencar_project.service.template.ICarService;
import com.iuh.rencar_project.service.template.ICategoryService;
import com.iuh.rencar_project.service.template.IFileService;
import com.iuh.rencar_project.utils.enums.CarState;
import com.iuh.rencar_project.utils.enums.Status;
import com.iuh.rencar_project.utils.exception.bind.EntityException;
import com.iuh.rencar_project.utils.exception.bind.NotFoundException;
import com.iuh.rencar_project.utils.mapper.ICarMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author Duy Trần Thế
 * @version 1.0
 * @date 5/9/2021 2:55 PM
 */
@Service
public class CarServiceImpl implements ICarService {
    private static final Logger logger = LoggerFactory.getLogger(CarServiceImpl.class);
    private final CarRepository carRepository;
    private final ICarMapper carMapper;
    private final ICategoryService categoryService;
    private final IFileService fileService;

    @Autowired
    public CarServiceImpl(CarRepository carRepository, ICarMapper carMapper, ICategoryService categoryService, IFileService fileService) {
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
        Car newCar = this.findById(id);
        Car oldCar = this.findById(id);
        if (this.existsByName(carRequest.getName()) && !oldCar.getName().equals(carRequest.getName()))
            throw new EntityException("Car Exists");
        carMapper.updateEntity(carRequest, newCar);
        newCar.setImage(fileService.updateCarImage(multipartFile, newCar));
        return categoryService.updateCarToCategory(carRequest.getCategoryName(), oldCar, newCar);
    }

    @Override
    public String update(Long id, CarRequest carRequest) {
        Car newCar = this.findById(id);
        Car oldCar = this.findById(id);
        System.out.println(oldCar.getName());
        System.out.println(carRequest.getName());
        if (this.existsByName(carRequest.getName()) && !oldCar.getName().equals(carRequest.getName()))
            throw new EntityException("Car Exists");
        carMapper.updateEntity(carRequest, newCar);
        return categoryService.updateCarToCategory(carRequest.getCategoryName(), oldCar, newCar);
    }

    @Override
    public Car updateCarForBillRented(Car car) {
        if (car.getState() == CarState.AVAILABLE && car.getStatus() == Status.ENABLE) {
            car.setState(CarState.UNAVAILABLE);
            try {
                car = carRepository.saveAndFlush(car);
            } catch (Exception e) {
                logger.error("Car Exception: ", e);
                throw new EntityException("Car can't rent");
            }
        } else {
            throw new EntityException("Car not available for rent");
        }
        return car;
    }

    @Override
    public Car updateCarForBillPaid(Car car) {
        car.setState(CarState.AVAILABLE);
        try {
            car = carRepository.saveAndFlush(car);
        } catch (Exception e) {
            logger.error("Car Exception: ", e);
            throw new EntityException("Car not returned");
        }
        return car;
    }

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
    public Page<Car> findAllPaginated(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize, Sort.by(Sort.Order.asc("id")));
        return carRepository.findAll(pageable);
    }

    @Override
    public List<Car> findAllEnable() {
        return carRepository.findAllByStatus(Status.ENABLE);
    }

    @Override
    public Page<Car> search(int pageNo, int pageSize, String s) {
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize, Sort.by(Sort.Order.asc("id")));
        return carRepository.search(s, pageable);
    }
}
