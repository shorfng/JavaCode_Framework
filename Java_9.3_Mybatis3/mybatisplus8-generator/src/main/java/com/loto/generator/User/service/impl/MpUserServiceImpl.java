package com.loto.generator.User.service.impl;

import com.loto.generator.User.entity.MpUser;
import com.loto.generator.User.mapper.MpUserMapper;
import com.loto.generator.User.service.IMpUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * MybatisPlus的user表 服务实现类
 * </p>
 *
 * @author 蓝田_Loto
 * @since 2021-03-08
 */
@Service
public class MpUserServiceImpl extends ServiceImpl<MpUserMapper, MpUser> implements IMpUserService {

}
