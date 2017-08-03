package net.simpotech.simpo.modules.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import net.simpotech.simpo.modules.dao.UserDao;
import net.simpotech.simpo.modules.service.UserService;
import net.simpotech.simpo.modules.vo.UserVo;
import net.simpotech.simpo.utils.StringHelperTools;

/**
 * UserServiceImpl
 * 
 * @author chenkun
 * @version 1.0
 * @since 2017Äê8ÔÂ2ÈÕ
 */
@Service
public class UserServiceImpl implements UserService {

	@Resource
	private UserDao userDao;

	@Override
	public List<UserVo> listUserVo() {
		return userDao.listUserVo();
	}

	@Override
	public UserVo getUserVo(String userId) {
		return userDao.getUserVo(userId);
	}

	@Override
	public int removeUserVo(String userId) {
		return userDao.removeUserVo(userId);
	}

	@Override
	public int saveUserVo(UserVo userVo) {
		userVo.setId(StringHelperTools.getUUID());
		return userDao.saveUserVo(userVo);
	}

	@Override
	public int updateUserVo(UserVo userVo) {
		return userDao.updateUserVo(userVo);
	}
	
	

}
