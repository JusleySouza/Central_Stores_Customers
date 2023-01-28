package com.central.stores.customers.pagination;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PaginationDTO implements Serializable{
	
    private static final long serialVersionUID = 1L;
    
    private long offset;
    private int pageSize;
    private int pageNumber;
    private int totalPages;
    private long totalElements;
    private Boolean moreElements;
}
