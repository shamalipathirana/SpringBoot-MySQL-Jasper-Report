package com.example.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.springboot.entity.Employee;


@Repository
public interface ReportRepository extends JpaRepository<Employee, Long> {

}