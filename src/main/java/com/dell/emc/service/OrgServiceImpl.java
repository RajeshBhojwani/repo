package com.dell.emc.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.dell.emc.exception.ResourceNotFoundException;
import com.dell.emc.model.Org;
import com.dell.emc.repository.OrgRepository;
 import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand; 



@Service
public class OrgServiceImpl implements OrgService {
	

	@Autowired
	private OrgRepository orgRepository;
	
	public List<Org> findAll() {
		return orgRepository.findAll();
	}
	
	
	@HystrixCommand(fallbackMethod = "reliable")
	
	public Org getOrgByid(Long orgId) throws ResourceNotFoundException {
		return orgRepository
        .findById(orgId)
        .orElseThrow(() -> new ResourceNotFoundException("Org not found on :: " + orgId));
	}
	
	
	public Org createOrg(Org org) {
		 return orgRepository.save(org);
	}
	
	
	public void deleteOrg(Org org) {
		 orgRepository.delete(org);
	}

	
	public Org reliable(Long orgId) {
		return new Org();
	}
	

}
