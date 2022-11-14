package com.dipol.pdfconvert.data.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Embeddable
@AllArgsConstructor
@NoArgsConstructor
public class Ba02PK implements Serializable {

    private String toolVersion;
    private String reportVersion;
    private String code;

}
