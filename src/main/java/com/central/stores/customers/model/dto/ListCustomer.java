package com.central.stores.customers.model.dto;

import java.io.Serializable;
import java.util.List;

import com.central.stores.customers.pagination.PaginationDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ListCustomer implements Serializable {

    private static final long serialVersionUID = 1L;
    private PaginationDTO pageable;
    private List<ResponseCustomerDTO> content;
}
