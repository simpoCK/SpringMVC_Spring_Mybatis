package net.simpotech.simpo.modules.action;


import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import net.simpotech.simpo.common.BaseAction;
import net.simpotech.simpo.modules.service.UserService;
import net.simpotech.simpo.modules.vo.UserVo;

/**
 * UserAction
 * @author chenkun
 * @version 1.0
 * @since 2017年8月2日
 */
@Controller
@RequestMapping("/userAction")
public class UserAction extends BaseAction{
	
	@Resource
	private UserService userService;
	
	/**
	 * 初始页
	 * @param model
	 * @return String
	 */
	@RequestMapping("/index")
	public String index(ModelMap model){
		return "user/user_index";
	}
	
	/**
	 * 初始页-获得多个用户
	 * @return List
	 */
	@RequestMapping("/listUserVo")
	public String listUserVo(ModelMap model){
		List<UserVo> userVoList= userService.listUserVo();
		model.addAttribute("userVoList",userVoList);
		return "user/user_list";
	}
	
	
	/**
	 * 删除选择的用户
	 * @param userIds 用户id
	 * @return int
	 */
	@RequestMapping("/removeUserVo")
	public int removeUserVo(String userId,ModelMap model,HttpServletRequest request){
		return userService.removeUserVo(userId);
	}
	
	/**
	 * 保存用户信息
	 * @param userIds 用户id
	 * @return int
	 */
	@RequestMapping("/saveUserVo")
	public String saveUserVo(UserVo userVo,ModelMap model,HttpServletRequest request){
		if(StringUtils.isEmpty(userVo.getId())){
			//插入
			userService.saveUserVo(userVo);
		}else{
			//修改
			userService.updateUserVo(userVo);
		}
		return this.index(model);
	}
	
	/**
	 * 页面跳转到表单页
	 * @param userId
	 * @return String
	 */
	@RequestMapping("/toFormPage")
	public String toFormPage(String userId,ModelMap model){
		if(!StringUtils.isEmpty(userId)){
			//userId非空,则为修改
			UserVo userVo= userService.getUserVo(userId);
			model.addAttribute("userVo", userVo);
		}
		return "user/user_form";
	}
	
	
	
}
