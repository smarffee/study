package com.lin.docker.service;

import com.lin.docker.model.User;
import com.lin.docker.model.UserAddress;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserAddressService {

    public UserAddress queryByUser(User user) {

        log.info("UserAddressService.queryByUser: 根据用户查找用户地址. 用户信息为: user is {}", user);

        UserAddress userAddress = new UserAddress();

        if (user.getId() == 1) {
            userAddress.setId(1);
            userAddress.setDesc("北京东城区");
        } else if (user.getId() == 2) {
            userAddress.setId(2);
            userAddress.setDesc("北京西城区");
        } else if (user.getId() == 3) {
            userAddress.setId(3);
            userAddress.setDesc("北京海淀区");
        } else {
            userAddress.setId(user.getId());
            userAddress.setDesc("未知");
        }

        return userAddress;
    }

}
