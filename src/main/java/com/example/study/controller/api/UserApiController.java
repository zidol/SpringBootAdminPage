package com.example.study.controller.api;

import com.example.study.model.entity.User;
import com.example.study.model.network.request.UserApiRequest;
import com.example.study.model.network.response.UserApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j  //롬복에서 제공하는것 log.info 사용 가능
@RestController
@RequestMapping("/api/user")                        //인터페이스를 공통적으로 사용하기 위해 제너릭 타입으로 인터페이스를 구성
public class UserApiController extends CrudController<UserApiRequest, UserApiResponse, User> {
//    @Autowired
//    private UserApiLogicService userApiLogicService;
//
//    @Override
//    @PostMapping("")    // /api/user
//    public Header<UserApiResponse> create(@RequestBody Header<UserApiRequest> request) {
//        log.info("{}",request); // request.toString();과 같음
//        return userApiLogicService.create(request);
//    }
//
//    @Override
//    @GetMapping("{id}") // /api/user{id}
//    public Header<UserApiResponse> read(@PathVariable(name = "id") Long id) {
//        log.info("read : {}", id);
//        return userApiLogicService.read(id);
//    }
//
//    @Override
//    @PutMapping("") // /api/user
//    public Header<UserApiResponse>  update(@RequestBody Header<UserApiRequest> request) {
//        return userApiLogicService.update(request);
//    }
//
//    @Override
//    @DeleteMapping("{id}")  // /api/user/{id}
//    public Header delete(@PathVariable(name = "id") Long id) {
//        log.info("delete : {}", id);
//         return userApiLogicService.delete(id);
//    }
}
