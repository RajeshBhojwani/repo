package com.dell.emc.service;

import java.util.List;

import javax.validation.Valid;

import com.dell.emc.exception.ResourceNotFoundException;
import com.dell.emc.model.Org;

public interface OrgService {


	Org getOrgByid(Long orgId) throws ResourceNotFoundException;

	Org createOrg(@Valid Org org);

	void deleteOrg(Org org);

	List<Org> findAll();

}