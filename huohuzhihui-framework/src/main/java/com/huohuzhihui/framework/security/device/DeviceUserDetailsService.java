package com.huohuzhihui.framework.security.device;

import com.huohuzhihui.common.core.domain.entity.LoginMerDevice;
import com.huohuzhihui.common.core.domain.model.LoginUser;
import com.huohuzhihui.common.enums.LoginType;
import com.huohuzhihui.merchant.domain.MerDevice;
import com.huohuzhihui.merchant.service.IMerDeviceService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * blog
 *
 * @author : lizhihe
 * @description : 基于设备验证的userdetails
 * @date : 2020-08-17 09:24
 **/
@Service("deviceUserDetailsService")
@Slf4j
public class DeviceUserDetailsService implements UserDetailsService {
    @Autowired
    private IMerDeviceService deviceService;

    @Override
    public UserDetails loadUserByUsername(String deviceNo) {
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        // 从数据库中取出用户信息
        MerDevice param = new MerDevice();
        param.setNo(deviceNo);
        List<MerDevice> devices = deviceService.selectMerDeviceList(param);
        // 判断用户是否存在
        if (CollectionUtils.isEmpty(devices)) {

            log.info("设备号{}不存在",deviceNo);
            throw new UsernameNotFoundException("设备号不存在");
        }
        if (devices.size() > 1) {
            throw new UsernameNotFoundException("设备号存在多个设备!");
        }
        LoginMerDevice device = new LoginMerDevice();
        MerDevice source = devices.get(0);
        BeanUtils.copyProperties(source,device);
        // 返回UserDetails实现类
        LoginUser loginUser = new LoginUser(device, null);
        loginUser.setLoginType(LoginType.DEVICE.getCode());
        return loginUser;
    }
}
