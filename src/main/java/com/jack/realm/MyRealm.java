package com.jack.realm;

import com.jack.dao.AdminMapper;
import com.jack.dao.ResourceMapper;
import com.jack.dao.RoleMapper;
import com.jack.pojo.entity.Admin;
import com.jack.pojo.entity.Resource;
import com.jack.pojo.entity.Role;
import com.jack.service.AdminService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class MyRealm extends AuthorizingRealm {

	@Autowired
	private AdminService adminService;

	/**
	 * 登录之后用于授权
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(
			PrincipalCollection principals) {
		String username = (String) principals.getPrimaryPrincipal();
		SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();

		Set<String> roles = getRoles(username);
		Set<String> permissions = getPermissions(username);

		authorizationInfo.setRoles(roles);
		authorizationInfo.setStringPermissions(permissions);
		return authorizationInfo;
	}


	/**
	 * 用于验证身份
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken token) throws AuthenticationException {
		String username = (String) token.getPrincipal();

		Admin admin = getUserByUsername(username);
		if (admin == null) {
			throw new AuthenticationException();
		}

		if (null != admin) {
			AuthenticationInfo info = new SimpleAuthenticationInfo(
					admin.getUsername(), admin.getPassword(), "MyRealm");
			return info;
		}
		return null;
	}

	/**
	 * 根据用户名查询用户
	 * @param username
	 * @return
	 */
	private Admin getUserByUsername(String username) {
		Admin admin = adminService.findAdminByUsername(username);
		return admin;
	}

	/**
	 * 根据用户名查询用户的所有权限（资源）
	 * @param username
	 * @return
	 */
	private Set<String> getPermissions(String username) {
		List<Resource> resources = adminService.findResourcesByUsername(username);
		Set<String> permissions = new HashSet<>();
		resources.stream().forEach(res -> {
			permissions.add(res.getResName());
		});
		return permissions;
	}

	/**
	 * 根据用户名查询用户的所有角色
	 * @param username
	 * @return
	 */
	private Set<String> getRoles(String username) {
		List<Role> roleList = adminService.findRolesByUsername(username);
		Set<String> roles = new HashSet<>(roleList.size());
		roleList.stream().forEach(role -> {
			roles.add(role.getRoleName());
		});
		return roles;
	}

}