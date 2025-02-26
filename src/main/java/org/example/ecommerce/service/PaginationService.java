package org.example.ecommerce.service;

import org.example.ecommerce.dto.PaginationDTO;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class PaginationService {
    public Pageable getPageable(PaginationDTO paginationDTO) {
        return PageRequest.of(paginationDTO.getPage(), paginationDTO.getSize());
    }
}
