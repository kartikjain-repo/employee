package com.example.employee.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmployeePatchDTO {
    @ApiModelProperty(notes="The state for the employee", example="ADDED", required = true)
    @NotNull
    private String state;
}
