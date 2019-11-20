package com.dell.emc.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.dell.emc.exception.ResourceNotFoundException;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.dell.emc.model.Org;
import com.dell.emc.service.OrgService;
import org.springframework.web.bind.annotation.RequestMapping;


@RestController
@RequestMapping("/api/v1")
public class OrgController {

  @Autowired
  private OrgService orgService;
  /**
   * Get all orgs list.
   *
   * @return the list
   */
  @GetMapping("/orgs")
  public List<Org> getAllOrgs() {
    return orgService.findAll();
  }

  /**
   * Gets orgs by id.
   *
   * @param orgId the org id
   * @return the orgs by id
   * @throws ResourceNotFoundException the resource not found exception
   */
  @GetMapping("/orgs/{id}")
  public ResponseEntity<Org> getOrgsById(@PathVariable(value = "id") Long orgId)
      throws ResourceNotFoundException {
        Org org = orgService.getOrgByid(orgId);
    return ResponseEntity.ok().body(org);
  }

  /**
   * Create org org.
   *
   * @param org the org
   * @return the org
   */
  @PostMapping("/orgs")
  public Org createOrg(@Valid @RequestBody Org org) {
    return orgService.createOrg(org);
  }

  /**
   * Update org response entity.
   *
   * @param orgId the org id
   * @param orgDetails the org details
   * @return the response entity
   * @throws ResourceNotFoundException the resource not found exception
   */
  @PutMapping("/orgs/{id}")
  public ResponseEntity<Org> updateOrg(
      @PathVariable(value = "id") Long orgId, @Valid @RequestBody Org orgDetails)
      throws ResourceNotFoundException {

    Org org = orgService.getOrgByid(orgId);
    org.setEmail(orgDetails.getEmail());
    org.setLastName(orgDetails.getLastName());
    org.setFirstName(orgDetails.getFirstName());
    final Org updatedOrg = orgService.createOrg(org);
    return ResponseEntity.ok(updatedOrg);
  }

  /**
   * Delete org map.
   *
   * @param orgId the org id
   * @return the map
   * @throws Exception the exception
   */

  @DeleteMapping("/orgs/{id}")
  public Map<String, Boolean> deleteOrg(@PathVariable(value = "id") Long orgId) throws Exception {
    Org org = orgService.getOrgByid(orgId);
    orgService.deleteOrg(org);
    Map<String, Boolean> response = new HashMap<>();
    response.put("deleted", Boolean.TRUE);
    return response;
  }
 

}