package edu.hit.tdxbackend.service.impl;

import edu.hit.tdxbackend.mapper.PropertyMapper;
import edu.hit.tdxbackend.service.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PropertyServiceImpl implements PropertyService {
    private final PropertyMapper propertyMapper;

    @Autowired
    public PropertyServiceImpl(PropertyMapper propertyMapper) {
        this.propertyMapper = propertyMapper;
    }

    @Override
    public boolean addProperty(int cid, String name) {
        return propertyMapper.addProperty(cid, name);
    }

    @Override
    public boolean changeProperty(int id, String value) {
        return propertyMapper.changeProperty(id, value);
    }

    @Override
    public boolean addProductProperty(int id, int ptid, String value) {
        return propertyMapper.addProductProperty(id, ptid, value);
    }
}
