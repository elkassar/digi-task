package com.digi.task.externalservice;

import com.digi.task.exception.InvalidInputException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentVerificationMockServiceImpl implements DepartmentVerificationService {

    @Override
    public void verifyDepartment(String department) {
        if (!"dept1".equals(department) && !"dept2".equals(department)) {
            throw new InvalidInputException(List.of("department"));
        }
    }
}
