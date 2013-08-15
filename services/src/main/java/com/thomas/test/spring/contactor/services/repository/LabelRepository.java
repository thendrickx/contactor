package com.thomas.test.spring.contactor.services.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.thomas.test.spring.contactor.domain.Label;
import com.thomas.test.spring.contactor.domain.User;

public interface LabelRepository extends PagingAndSortingRepository<Label, Long> {

	public List<Label> findByUser(User user);

	public Page<Label> findByUser(User user, Pageable pageable);

	public Label findByUserAndLabelLike(User user, String label);
}
