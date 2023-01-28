package com.central.stores.customers.pagination;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.central.stores.customers.model.Customer;
import com.central.stores.customers.model.dto.ListCustomer;
import com.central.stores.customers.model.dto.ResponseCustomerDTO;

public final class Pagination {
	
	public static ListCustomer paginationCustomer(Page<Customer> pageListCustomer, List<ResponseCustomerDTO> customer) {
		
		PaginationDTO paginationDTO = PaginationDTO.builder()
				.moreElements(!pageListCustomer.isLast())
				.offset(pageListCustomer.getPageable().getOffset())
				.pageNumber(pageListCustomer.getPageable().getPageNumber())
				.pageSize(pageListCustomer.getPageable().getPageSize())
				.totalElements(pageListCustomer.getTotalElements())
				.totalPages(pageListCustomer.getTotalPages())
				.build();
		
		return ListCustomer.builder()
				.pageable(paginationDTO)
				.content(customer)
				.build();
		
	}

	public static Pageable createPagination(Integer pageSize, Integer page, String sortBy) {
		String[] sort = sortBy.split(",");
		String evalSort = sort[0];
		String sortDirection = sort[1];
		Sort.Direction evalDirection = sortDirection(sortDirection);
		Sort sortOrderIgnoreCase = Sort.by(new Sort.Order(evalDirection, evalSort));
		return PageRequest.of(page, pageSize,  sortOrderIgnoreCase);
	}

	private static Sort.Direction sortDirection(String sortDirection){
		if(sortDirection.equalsIgnoreCase("DESC")) {
			return Sort.Direction.DESC;
		} else {
			return Sort.Direction.ASC;
		}
	}

}
