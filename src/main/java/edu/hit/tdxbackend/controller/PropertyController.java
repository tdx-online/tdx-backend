package edu.hit.tdxbackend.controller;

import edu.hit.tdxbackend.entity.ProductProperties;
import edu.hit.tdxbackend.entity.Property;
import edu.hit.tdxbackend.entity.ResultInfo;
import edu.hit.tdxbackend.service.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/property")
public class PropertyController {
    private final PropertyService propertyService;

    @Autowired
    public PropertyController(PropertyService propertyService) {
        this.propertyService = propertyService;
    }

    @PostMapping("/add")
    public ResultInfo addProperty(@RequestBody Property property) {
        ResultInfo info = new ResultInfo();
        try {
            boolean addFlag = propertyService.addProperty(property.getCid(), property.getName());
            if (addFlag) {
                info.setFlag(true);
                info.setErrorMsg(null);
            } else {
                info.setFlag(false);
                info.setErrorMsg("添加失败");
            }
        } catch (Exception e) {
            info.setFlag(false);
            info.setErrorMsg("添加失败");
        }
        return info;
    }

    @PostMapping("/change")
    public ResultInfo changeProperty(@RequestBody ProductProperties property) {
        ResultInfo info = new ResultInfo();
        try {
            boolean changeFlag = propertyService.changeProperty(property.getId(), property.getValue());
            if (changeFlag) {
                info.setFlag(true);
                info.setErrorMsg(null);
            } else {
                info.setFlag(false);
                info.setErrorMsg("修改失败");
            }
        } catch (Exception e) {
            info.setFlag(false);
            info.setErrorMsg("修改失败");
        }
        return info;
    }

    @PostMapping("/addProductProperty")
    public ResultInfo addProductProperty(@RequestBody ProductProperties property) {
        ResultInfo info = new ResultInfo();
        try {
            boolean addFlag = propertyService.addProductProperty(property.getId(), property.getPtid(), property.getValue());
            if (addFlag) {
                info.setFlag(true);
                info.setErrorMsg(null);
            } else {
                info.setFlag(false);
                info.setErrorMsg("添加失败");
            }
        } catch (Exception e) {
            info.setFlag(false);
            info.setErrorMsg("添加失败");
        }
        return info;
    }
}
