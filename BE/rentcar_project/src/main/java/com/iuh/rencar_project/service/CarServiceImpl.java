package com.iuh.rencar_project.service;

import com.iuh.rencar_project.dto.request.CarRequest;
import com.iuh.rencar_project.entity.Car;
import com.iuh.rencar_project.repository.CarRepository;
import com.iuh.rencar_project.service.template.ICarService;
import com.iuh.rencar_project.utils.exception.bind.EntityException;
import com.iuh.rencar_project.utils.exception.bind.NotFoundException;
import com.iuh.rencar_project.utils.mapper.ICarMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author Duy Trần Thế
 * @version 1.0
 * @date 5/9/2021 2:55 PM
 */
public class CarServiceImpl implements ICarService {
    private static final Logger logger = LogManager.getLogger(CarServiceImpl.class);
    private final CarRepository carRepository;
    private final ICarMapper carMapper;

    public CarServiceImpl(CarRepository carRepository, ICarMapper carMapper) {
        this.carRepository = carRepository;
        this.carMapper = carMapper;
    }


    @Override
    public String save(CarRequest carRequest) {
        String name = carRequest.getName();
        if (this.existsByName(name))
            throw new EntityException("Car " + name + " exists");
        try {
            carRepository.saveAndFlush(carMapper.toEntity(carRequest));
        } catch (Exception e) {
            logger.error("Car Exception: ", e);
            throw new EntityException("Car " + name + " save fail", e);
        }
        return "Car " + name + " save success";
    }

    @Override
    public String update(Long id, CarRequest carRequest) {
        Car currentCar = this.findById(id);
        String currentName = currentCar.getName();
        if (this.existsByName(carRequest.getName()) && !currentName.equals(carRequest.getName()))
            throw new EntityException("Car " + carRequest.getName() + " exists");
        try {
            carMapper.updateEntity(carRequest, currentCar);
            carRepository.saveAndFlush(currentCar);
        } catch (Exception e) {
            logger.error("Car Exception: ", e);
            throw new EntityException("Car " + currentName + " update fail");
        }
        return "Car " + currentName + " update success";
    }

    @Override
    public String delete(Long id) {
        Car car = this.findById(id);
        String name = car.getName();
        try{
            carRepository.deleteById(id);
        }catch (Exception e){
            logger.error("Car Exception: ", e);
            throw new EntityException("Car " + name + " delete fail");
        }
        return "Car " + name + " delete success";
    }

    @Override
    public Car findBySlug(String slug) {
        return carRepository.findBySlug(slug).orElseThrow(()-> new NotFoundException("Car with slug " + slug + " not found"));
    }

    @Override
    public Car findById(Long id) {
        return carRepository.findById(id).orElseThrow(()-> new NotFoundException("Car with id " + id + " not found"));
    }

    @Override
    public Boolean existsByName(String name) {
        return carRepository.existsByName(name);
    }
}
