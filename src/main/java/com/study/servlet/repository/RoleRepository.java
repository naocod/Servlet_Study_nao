package com.study.servlet.repository;

import com.study.servlet.entity.Role;

public interface RoleRepository {

	public Role findRoleByRolename(String roleName);

}
