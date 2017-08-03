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
 * @since 2017��8��2��
 */
@Controller
@RequestMapping("/userAction")
public class UserAction extends BaseAction{
	
	@Resource
	private UserService userService;
	
	/**
	 * ��ʼҳ
	 * @param model
	 * @return String
	 */
	@RequestMapping("/index")
	public String index(ModelMap model){
		return "user/user_index";
	}
	
	/**
	 * ��ʼҳ-��ö���û�
	 * @return List
	 */
	@RequestMapping("/listUserVo")
	public String listUserVo(ModelMap model){
		List<UserVo> userVoList= userService.listUserVo();
		model.addAttribute("userVoList",userVoList);
		return "user/user_list";
	}
	
	
	/**
	 * ɾ��ѡ����û�
	 * @param userIds �û�id
	 * @return int
	 */
	@RequestMapping("/removeUserVo")
	public int removeUserVo(String userId,ModelMap model,HttpServletRequest request){
		return userService.removeUserVo(userId);
	}
	
	/**
	 * �����û���Ϣ
	 * @param userIds �û�id
	 * @return int
	 */
	@RequestMapping("/saveUserVo")
	public String saveUserVo(UserVo userVo,ModelMap model,HttpServletRequest request){
		if(StringUtils.isEmpty(userVo.getId())){
			//����
			userService.saveUserVo(userVo);
		}else{
			//�޸�
			userService.updateUserVo(userVo);
		}
		return this.index(model);
	}
	
	/**
	 * ҳ����ת����ҳ
	 * @param userId
	 * @return String
	 */
	@RequestMapping("/toFormPage")
	public String toFormPage(String userId,ModelMap model){
		if(!StringUtils.isEmpty(userId)){
			//userId�ǿ�,��Ϊ�޸�
			UserVo userVo= userService.getUserVo(userId);
			model.addAttribute("userVo", userVo);
		}
		return "user/user_form";
	}
	
	
	
}
