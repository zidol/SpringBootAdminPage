package com.example.study.service;

import com.example.study.model.entity.User;
import com.example.study.model.enumclass.UserStatus;
import com.example.study.model.network.Header;
import com.example.study.model.network.request.UserApiRequest;
import com.example.study.model.network.response.UserApiResponse;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class UserApiLogicService extends BaseService<UserApiRequest, UserApiResponse, User> {

//    @Autowired
//    private UserRepository userRepository;

    // 1. request data
    // 2. user 생성
    // 3. 생서된 데이터 -> UserApiResponse return
    @Override
    public Header<UserApiResponse> create(Header<UserApiRequest> request) {

        //1. request data
        UserApiRequest userApiRequest = request.getData();

        //2. user 생성
        User user = User.builder()
                .account(userApiRequest.getAccount())
                .password(userApiRequest.getPassword())
                .status(UserStatus.REGISTERED)
                .phoneNumber(userApiRequest.getPhoneNumber())
                .email(userApiRequest.getEmail())
                .registeredAt(LocalDateTime.now())
                .build();
        User newUser = baseRepository.save(user);

        //3. 생성된 데이터 -> userApiResponse return
        return response(newUser);
    }

    @Override
    public Header<UserApiResponse> read(Long id) {
        // id -> repository getOne, getById
//        Optional<User> optional = userRepository.findById(id);
//
//        // user -> userApiResponse return
//        return optional
//                .map(user -> response(user))
//                .orElseGet(() -> Header.ERROR("데이터 없음")
//                );
        return baseRepository.findById(id)
                .map(user -> response(user)) //map 데이터 형태 변형할때 사용
                .orElseGet(() -> Header.ERROR("데이터 없음")
                );
    }

    @Override
    public Header<UserApiResponse> update(Header<UserApiRequest> request) {

        //1. data
        UserApiRequest userApiRequest = request.getData();
        //2. id -> user 데이터 찾고
        Optional<User> optional = baseRepository.findById(userApiRequest.getId());

        return  optional.map(user -> {
            //3. data -> update
            //id
            user.setAccount(userApiRequest.getAccount())
                    .setPassword(userApiRequest.getPassword())
                    .setStatus(userApiRequest.getStatus())
                    .setPhoneNumber(userApiRequest.getPhoneNumber())
                    .setEmail(userApiRequest.getEmail())
                    .setRegisteredAt(userApiRequest.getRegisteredAt())
                    .setUnregisteredAt(userApiRequest.getUnregisteredAt())
            ;
                return  user;
            //4. userApiresponse
        })
                .map(user -> baseRepository.save(user))     //update    -> 새로운 유저 객체 반환 (newUser)
                .map(updateUser -> response(updateUser))                //userApiResponse
                .orElseGet(() -> Header.ERROR("데이터 없음"));




    }

    @Override
    public Header delete(Long id) {
        //1. id -> repository ->user
        Optional<User> optional = baseRepository.findById(id);

        //2. repository -> delete
       return optional.map(user -> {
           baseRepository.delete(user);
            return Header.OK();
        }).orElseGet(() -> Header.ERROR("데이터 없음"));

        //3. response return
//        return null;
    }

    private Header<UserApiResponse> response(User user) {
        // user -> userApiresponse
        UserApiResponse userApiResponse = UserApiResponse.builder()
                .id(user.getId())
                .account(user.getAccount())    //todo 암호화, 길이
                .password(user.getPassword())
                .phoneNumber(user.getPhoneNumber())
                .status(user.getStatus())
                .registeredAt(user.getRegisteredAt())
                .unregisteredAt(user.getUnregisteredAt())
                .build();
        return Header.OK(userApiResponse);
    }
}
