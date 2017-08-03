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
 * @since 2017��8��2��
 */
public interface UserService {

	/**
	 * ��ʼҳ-��ö���û�
	 * 
	 * @return List
	 */
	List<UserVo> listUserVo();

	/**
	 * ��õ����û���Ϣ
	 * 
	 * @param userId
	 * @return UserVo
	 */
	UserVo getUserVo(String userId);

	/**
	 * ɾ��ѡ����û�
	 * 
	 * @param userIds
	 *            �û�id
	 * @return int
	 */
	int removeUserVo(String userId);

	/**
	 * �����û���Ϣ
	 * 
	 * @param userIds
	 *            �û�id
	 * @return int
	 */
	int saveUserVo(UserVo userVo);
	
	/**
	 * �޸��û���Ϣ
	 * @param userVo �û�vo
	 * @return int
	 */
    int updateUserVo(UserVo userVo);
}
