package com.apapedia.webapp.security.xml;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@XmlAccessorType(XmlAccessType.FIELD)
public class Attributes {
    
    @XmlElement(namespace = "http://www.yale.edu/tp/cas")
    private String ldap_cn;

    @XmlElement(namespace = "http://www.yale.edu/tp/cas")
    private String kd_org;

    @XmlElement(namespace = "http://www.yale.edu/tp/cas")
    private String peran_user;

    @XmlElement(namespace = "http://www.yale.edu/tp/cas")
    private String nama;

    @XmlElement(namespace = "http://www.yale.edu/tp/cas")
    private String npm;
}
