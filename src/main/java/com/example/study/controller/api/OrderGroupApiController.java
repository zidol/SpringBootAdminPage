package com.example.study.controller.api;

import com.example.study.model.entity.OrderGroup;
import com.example.study.model.network.request.OrderGroupApiRequest;
import com.example.study.model.network.response.OrderGroupApiResponse;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/orderGroup")
public class OrderGroupApiController extends CrudController<OrderGroupApiRequest, OrderGroupApiResponse, OrderGroup> {

//    @Autowired
//    private OrderGroupApiLogicService orderGroupApiLogicService;
//
//   @PostConstruct
//    public void init() {
//       this.baseService = orderGroupApiLogicService;
//   }
}
