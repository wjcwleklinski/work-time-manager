package com.wjcwleklinski.worktimemanager.converter;


import com.wjcwleklinski.worktimemanager.entity.EmployeeProjectId;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;


@Component
public class EmployeeProjectIdConverter implements Converter<String, EmployeeProjectId> {

    @Override
    public EmployeeProjectId convert(String s) {

        String[] parts = s.split("_");
        return new EmployeeProjectId(Long.parseLong(parts[0]), Long.parseLong(parts[1]));
    }
}
