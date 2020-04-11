package com.itcast.englis_news.service.impl;

import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import com.itcast.englis_news.common.Role;
import com.itcast.englis_news.common.User;
import com.itcast.englis_news.common.UserRole;
import com.itcast.englis_news.common.exception.BaseException;
import com.itcast.englis_news.common.result.ResultCodeEnum;
import com.itcast.englis_news.dao.RoleMapper;
import com.itcast.englis_news.dao.UserMapper;
import com.itcast.englis_news.dao.UserRoleMapper;
import com.itcast.englis_news.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import java.util.Date;
import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    @Resource
    private RoleMapper roleMapper;

    @Resource
    private UserRoleMapper userRoleMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    //注入发送邮件服务
    private final MailService mailService;

    //注入redis操作对象
    private RedisTemplate<String,String> redisTemplate;

    //注入邮件模板,注意#{}注入容器内bean方法执行后的内容
    @Value("#{templateConfig.templateContentAsString}")
    private String email_template;

    public UserServiceImpl(MailService mailService,RedisTemplate<String,String> template) {
        this.mailService = mailService;
        this.redisTemplate =template;
    }

    @Override
    public int deleteByPrimaryKey(Integer uid) {
        return userMapper.deleteByPrimaryKey(uid);
    }

    /**
     * 注册新用户
     *
     * @param record 用户
     * @return 执行插入后的改变的行数
     */
    @Transactional
    @Override
    public void insert(User record, String code) throws BaseException {
        //1.校验用户信息完整性
        boolean isCorrect = validateUser(record);
        boolean isMatch = validateVerifyCode(code,record.getEmail());
        log.info("输入一致吗:"+isMatch);
        if (isCorrect&&isMatch) {
            //2.0验证当前邮箱是否已本人注册过,如果注册,则返回错误提示
            if (checkEmailIsRegisted(record.getEmail())){
                BaseException baseException = new BaseException(ResultCodeEnum.PARAM_ERROR);
                baseException.setMessage("邮箱已被注册,请更换注册邮箱!");
                throw baseException;
            }
            //2.1校验无误后,设置当前注册时间
            record.setRegisterDate(new Date());
            //2.2密码加密
            record.setPassword(passwordEncoder.encode(record.getPassword()));
            try {
                //2.3 插入用户
                userMapper.insert(record);
                //2.4为新用户设置普通角色
                Role role = new Role();
                role.setRname("USER");
                roleMapper.insert(role);
                //2.5插入对应关系
                UserRole userRole = UserRole.builder().uid(record.getUid()).rid(role.getRid()).build();
                userRoleMapper.insert(userRole);
                return;
            } catch (Exception e) {
                //如果操作数据库
                BaseException exception = new BaseException(ResultCodeEnum.DB_OPERATION_ERROR);
                exception.setMessage(e.getMessage());
                throw exception;
            }
        }
        throw new BaseException(ResultCodeEnum.CODE_INVALID_ERROR);
    }

    /**
     * 判断邮箱是否已被注册
     * @param email 注册者邮箱
     * @return true(已注册)/false(未被注册)
     */
    private boolean checkEmailIsRegisted(String email) {
        return userMapper.selectByEmail(email)>0;
    }

    /**
     * 校验用户输入的验证码是否与生成的验证码一致
     * @param code 输入的验证码
     * @return 校验结果
     */
    private boolean validateVerifyCode(String code,String email) {
        //todo:1.从redis中获取已发送的验证码;
        String code_generate = redisTemplate.opsForValue().get(email);
        // 2.判断验证码状态(是否存在,是否过时);
        // 3.判断验证码是否匹配
        return code_generate != null && code_generate.equalsIgnoreCase(code);
    }

    /**
     * 校验用户数据是否合规
     *
     * @param record 用户
     * @return 校验结果
     */
    private boolean validateUser(User record) {
        return record != null //用户信息非空
                && record.getUname() != null && !record.getUname().equals("")//用户名非空
                && record.getEmail() != null && !record.getEmail().equals("") //邮箱非空
                && record.getPassword() != null && record.getPassword().length()>4//密码非空且长度至少5位
                &&record.getPhoneNum()!=null&&record.getPhoneNum().length()==11;//电话号码非空且长度11为
    }

    @Override
    public int insertSelective(User record) {
        return userMapper.insertSelective(record);
    }

    @Override
    public User selectByPrimaryKey(Integer uid) {
        return userMapper.selectByPrimaryKey(uid);
    }

    @Override
    public int updateByPrimaryKeySelective(User record) {
        return userMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(User record) {
        return userMapper.updateByPrimaryKey(record);
    }

    /**
     * 发送验证码邮件
     * @param email 邮箱地址
     * @return code 验证码
     * @throws BaseException 异常
     */
    @Override
    public String sendVerifyCode(String email) throws BaseException{
        String code = RandomUtil.randomString(6);
        try {
            //需要构造一个HTML模板,唯一变量为code
            mailService.sendHtmlMail(email, "会员注册验证码", StrUtil.format(email_template, code));
            //需要将验证码存入redis,有效时间设为5分钟
            redisTemplate.opsForValue().set(email, code);
            redisTemplate.expire(email, 5, TimeUnit.MINUTES);
            log.info(email+"->:"+code);
            System.out.println("---------");
            System.out.println(redisTemplate.opsForValue().get(email));
            System.out.println("---------");
        } catch (MessagingException e) {
            throw new BaseException(ResultCodeEnum.MESSAGING_ERROR);
        }
        return code;
    }

}

