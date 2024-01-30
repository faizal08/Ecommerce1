package org.glamgaze.library.repository;

import org.glamgaze.library.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository  extends JpaRepository<Role,Long>
{
  Role findByName(String name);
}
