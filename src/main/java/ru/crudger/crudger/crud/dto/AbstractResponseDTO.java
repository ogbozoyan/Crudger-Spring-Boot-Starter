package ru.crudger.crudger.crud.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A data transfer object representing the response of an abstract operation.
 * It contains the content, total number of elements, and total number of pages.
 *
 * @author ogbozoyan
 * @date 13.02.2023
 */
public class AbstractResponseDTO implements Serializable {
    private Object content;
    private Long totalElements;
    private Integer totalPages;

    /**
     * Constructs an AbstractResponseDTO object with the specified content, total number of elements,
     * and total number of pages.
     *
     * @param content       The content of the response.
     * @param totalElements The total number of elements in the response.
     * @param totalPages    The total number of pages in the response.
     */
    public AbstractResponseDTO(Object content, Long totalElements, Integer totalPages) {
        this.content = content;
        this.totalElements = totalElements;
        this.totalPages = totalPages;
    }

    public Object getContent() {
        return this.content;
    }

    public Long getTotalElements() {
        return this.totalElements;
    }

    public Integer getTotalPages() {
        return this.totalPages;
    }

    public void setContent(Object content) {
        this.content = content;
    }

    public void setTotalElements(Long totalElements) {
        this.totalElements = totalElements;
    }

    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AbstractResponseDTO that)) return false;
        return Objects.equals(getContent(), that.getContent()) && Objects.equals(getTotalElements(), that.getTotalElements()) && Objects.equals(getTotalPages(), that.getTotalPages());
    }

    protected boolean canEqual(final Object other) {
        return other instanceof AbstractResponseDTO;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $content = this.getContent();
        result = result * PRIME + ($content == null ? 43 : $content.hashCode());
        final Object $totalElements = this.getTotalElements();
        result = result * PRIME + ($totalElements == null ? 43 : $totalElements.hashCode());
        final Object $totalPages = this.getTotalPages();
        result = result * PRIME + ($totalPages == null ? 43 : $totalPages.hashCode());
        return result;
    }

    public String toString() {
        return "AbstractResponseDTO(content=" + this.getContent() + ", totalElements=" + this.getTotalElements() + ", totalPages=" + this.getTotalPages() + ")";
    }
}
