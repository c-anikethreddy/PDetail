package com.cognizant.pensionmanagementportal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cognizant.pensionmanagementportal.model.ProcessPensionInput;



/**
 * @author Nayan, Akshita, Akhil
 * PensionManagementRepository extends JPA Repository
 * Annotated with @Repository to communicate with database
 */

@Repository
public interface PensionManagementRepository extends JpaRepository<ProcessPensionInput, Long> {

}
