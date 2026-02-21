package com.example.blackrock.responseDto;

import com.example.blackrock.dto.FilteredInvalidTransaction;
import com.example.blackrock.dto.FilteredValidTransaction;

import java.util.List;

public class FilteredtransactionResponse {
    private List<FilteredValidTransaction> valid;

    private List<FilteredInvalidTransaction> invalid;

    public FilteredtransactionResponse(List<FilteredValidTransaction> valid, List<FilteredInvalidTransaction> invalid) {
        this.valid = valid;
        this.invalid = invalid;
    }

    public List<FilteredValidTransaction> getValid() {
        return valid;
    }

    public void setValid(List<FilteredValidTransaction> valid) {
        this.valid = valid;
    }

    public List<FilteredInvalidTransaction> getInvalid() {
        return invalid;
    }

    public void setInvalid(List<FilteredInvalidTransaction> invalid) {
        this.invalid = invalid;
    }
}
