package com.ssafy.findme.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ssafy.findme.domain.User;

@Repository
public interface AccountRepository extends JpaRepository<User, Long> {

	public Optional<User> findByEmail(String email);

	public Optional<User> findByEmailAndAuthKey(String email, String key);

	public List<User> findByNameLike(String keyword);

	public int countByEmail(String email);

	public int countByEmailAndPassword(String email, String password);

	public Optional<User> findById(Long user_id);
}
