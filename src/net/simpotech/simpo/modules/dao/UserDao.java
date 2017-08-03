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
 * @since 2017年8月2日
 */
@Repository
public class UserDao extends BaseDao{
		
	private String namespace="net.simpotech.simpo.modules.dao.UserDao";
	
	
	/**
	 * 初始页-获得多个用户
	 * @return List
	 */
	public List<UserVo> listUserVo(){
		return this.selectList(namespace+".listUserVo");
	}
	
	
	/**
	 * 获得单个用户信息
	 * @param userId
	 * @return UserVo
	 */
	public UserVo getUserVo(String userId){
		Map<String,String> map=new HashMap<>();
		map.put("userId", userId);
		return this.selectOne(namespace+".getUserVo", map);
	}
	
	/**
	 * 删除选择的用户
	 * @param userIds 用户id
	 * @return int
	 */
	public int removeUserVo(String userId){
		Map<String,String> map=new HashMap<>();
		map.put("userId", userId);
		return this.delete(namespace+".removeUserVo", map);
	}
	
	/**
	 * 保存用户信息
	 * @param userVo 用户vo
	 * @return int
	 */
	public int saveUserVo(UserVo userVo){
		return this.insert(namespace+".saveUserVo", userVo);
	}
	
	/**
	 * 修改用户信息
	 * @param userVo 用户vo
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
