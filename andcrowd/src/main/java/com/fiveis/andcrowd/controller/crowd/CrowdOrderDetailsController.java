package com.fiveis.andcrowd.controller.crowd;

import com.fiveis.andcrowd.dto.crowd.CrowdOrderDetailsDTO;
import com.fiveis.andcrowd.entity.crowd.CrowdOrderDetails;
import com.fiveis.andcrowd.service.crowd.CrowdOrderDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
@RequestMapping("/crowd_orderdetails{crowdId}")
public class CrowdOrderDetailsController {

    private final CrowdOrderDetailsService crowdOrderDetailsService;

    @Autowired
    public CrowdOrderDetailsController(CrowdOrderDetailsService orderDetailsService) {
        this.crowdOrderDetailsService = orderDetailsService;
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public List<CrowdOrderDetailsDTO.FindById> getlist() {
        return crowdOrderDetailsService.findAll();
    }

    @RequestMapping(value = "/{purchaseId}", method = RequestMethod.GET)
    public CrowdOrderDetailsDTO.FindById getOrder(@PathVariable("purchaseId") int purchaseId) {
        return  crowdOrderDetailsService.findById(purchaseId);
    }

    @RequestMapping(value = "/{purchaseId}/update", method = RequestMethod.PATCH)
    public String updateOrder(CrowdOrderDetails crowdOrderDetails) {
        crowdOrderDetailsService.update(crowdOrderDetails);
        return "redirect:/crowd_orderdetails{crowdId}/" + crowdOrderDetails.getPurchaseId();
    }

    @RequestMapping(value = "/{purchaseId}/delete", method = RequestMethod.DELETE)
    public void deleteOrder(@PathVariable("purchaseId") int purchaseId) {
        crowdOrderDetailsService.deleteById(purchaseId);
    }

}
