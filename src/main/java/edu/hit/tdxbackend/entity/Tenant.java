package edu.hit.tdxbackend.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Tenant {
    private Integer id;
    private String username;
    private String password;
    private String email;
    private Integer status; // 0: 审核中 1: 审核通过 2: 审核未通过
}
