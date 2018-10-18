package com.tft.framework.auth.service.impl;

import com.tft.framework.auth.dao.AuthRepository;
import com.tft.framework.auth.service.AuthService;
import com.tft.framework.common.service.TftCacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * <br>类描述：
 * <br>author： lwl liuwanli_eamil@163.com	2018/10/11 23:33
 *
 * @ClassName AuthServiceImpl
 * @see #
 * @since {修改人、修改时间、修改事由}
 */
@Service("authService")
public class AuthServiceImpl implements AuthService {

    @Autowired
    private AuthRepository authRepository;

    @Autowired
    @Qualifier("redisCacheService")
    private TftCacheService tftCacheService;

    /**
     * <br>功能描述:  系统启动时加载菜单和action的请求权限配置信息，缓存到Redis中
     * <br>处理逻辑:
     * <br>作者: lwl liuwanli_eamil@163.com 2018/10/11 23:30
     * <br>修改记录: {修改人 修改原因 修改时间}
     *
     * @return void
     * @throws
     * @see #
     */
    @Override
    public Map<String,String> loadUrlAuthInfo() throws Exception {
        Map<String,String> authMap =  (HashMap)tftCacheService.getChae("tftUrlAuthMap",HashMap.class);
        if(authMap==null){
            authMap = this.reLoadUrlAuthInfo();
        }
        return authMap;
    }

    /**
     * <br>功能描述:  重新加载url授权信息
     * <br>处理逻辑:
     * <br>作者: lwl liuwanli_eamil@163.com 2018/10/12 14:37
     * <br>修改记录: {修改人 修改原因 修改时间}
     *
     * @return java.util.Map<java.lang.String , java.lang.String>
     * @throws
     * @see #
     */
    @Override
    public Map<String, String> reLoadUrlAuthInfo() throws Exception {
        Map<String,String> authMap =  authMap = new HashMap<>();
        //查询授权信息
        String[][] actions = authRepository.searchAction();
        //将授权信息拼装成map<url，usercodes>
        for(int i=0;i<actions.length;i++){
            String key = actions[i][0];
            String value = actions[i][1];
            if(authMap.get(key)==null){
                authMap.put(key,value);
            }else{
                authMap.put(key,authMap.get(key)+","+value);
            }
        }
        //将授权信息缓存到Redis中
        tftCacheService.saveCache("tftUrlAuthMap",authMap);
        return authMap;
    }
}
