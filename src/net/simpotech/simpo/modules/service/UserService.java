package net.simpotech.simpo.modules.service;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.simpotech.simpo.modules.vo.UserVo;

/**
 * UserService
 * 
 * @author chenkun
 * @version 1.0
 * @since 2017年8月2日
 */
public interface UserService {

	/**
	 * 初始页-获得多个用户
	 * 
	 * @return List
	 */
	List<UserVo> listUserVo();

	/**
	 * 获得单个用户信息
	 * 
	 * @param userId
	 * @return UserVo
	 */
	UserVo getUserVo(String userId);

	/**
	 * 删除选择的用户
	 * 
	 * @param userIds
	 *            用户id
	 * @return int
	 */
	int removeUserVo(String userId);

	/**
	 * 保存用户信息
	 * 
	 * @param userIds
	 *            用户id
	 * @return int
	 */
	int saveUserVo(UserVo userVo);
	
	/**
	 * 修改用户信息
	 * @param userVo 用户vo
	 * @return int
	 */
    int updateUserVo(UserVo userVo);
}
