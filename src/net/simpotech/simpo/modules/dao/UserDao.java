package net.simpotech.simpo.modules.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import net.simpotech.simpo.common.BaseDao;
import net.simpotech.simpo.modules.vo.UserVo;

/**
 * UserDao
 * @author chenkun
 * @version 1.0
 * @since 2017��8��2��
 */
@Repository
public class UserDao extends BaseDao{
		
	private String namespace="net.simpotech.simpo.modules.dao.UserDao";
	
	
	/**
	 * ��ʼҳ-��ö���û�
	 * @return List
	 */
	public List<UserVo> listUserVo(){
		return this.selectList(namespace+".listUserVo");
	}
	
	
	/**
	 * ��õ����û���Ϣ
	 * @param userId
	 * @return UserVo
	 */
	public UserVo getUserVo(String userId){
		Map<String,String> map=new HashMap<>();
		map.put("userId", userId);
		return this.selectOne(namespace+".getUserVo", map);
	}
	
	/**
	 * ɾ��ѡ����û�
	 * @param userIds �û�id
	 * @return int
	 */
	public int removeUserVo(String userId){
		Map<String,String> map=new HashMap<>();
		map.put("userId", userId);
		return this.delete(namespace+".removeUserVo", map);
	}
	
	/**
	 * �����û���Ϣ
	 * @param userVo �û�vo
	 * @return int
	 */
	public int saveUserVo(UserVo userVo){
		return this.insert(namespace+".saveUserVo", userVo);
	}
	
	/**
	 * �޸��û���Ϣ
	 * @param userVo �û�vo
	 * @return int
	 */
	public int updateUserVo(UserVo userVo){
		Map<Object,Object> map=new HashMap<>();
		map.put("code", userVo.getCode());
		map.put("name", userVo.getName());
		map.put("agent", userVo.getAgent());
		map.put("depart", userVo.getDepart());
		map.put("id", userVo.getId());
		return this.insert(namespace+".updateUserVo", map);
	}
	
	
	
	
	
	
}
