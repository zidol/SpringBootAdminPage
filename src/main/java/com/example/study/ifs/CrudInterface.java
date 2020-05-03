package com.example.study.ifs;

import com.example.study.model.network.Header;

//인터페이스를 공통적으로 사용하기 위해 제너릭 타입으로 인터페이스를 구성
public interface CrudInterface<Req, Res> {

    Header<Res> create(Header<Req> request);    //todo request object 추가

    Header<Res> read(Long id);

    Header<Res> update(Header<Req> request);

    Header delete(Long id);
}

