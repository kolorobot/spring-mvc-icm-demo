package com.github.kolorobot.icm.api.types;

import com.sun.istack.NotNull;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Size;
import javax.xml.bind.annotation.*;

@XmlType
@XmlAccessorType(XmlAccessType.NONE)
public class NewIncident {

    @XmlAttribute(required = true)
    @NotNull
    private Long userId;

    @XmlElement(required = true)
    @NotBlank
    @Size(max = 50)
    private String description;

    @XmlElement(required = true)
    @NotBlank
    @Size(max = 255)
    private String type;

    @XmlElement
    @NotBlank
    @Size(max = 255)
    private String addressLine;

    @XmlElement
    @NotBlank
    @Size(max = 255)
    private String cityLine;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAddressLine() {
        return addressLine;
    }

    public void setAddressLine(String addressLine) {
        this.addressLine = addressLine;
    }

    public String getCityLine() {
        return cityLine;
    }

    public void setCityLine(String cityLine) {
        this.cityLine = cityLine;
    }
}
