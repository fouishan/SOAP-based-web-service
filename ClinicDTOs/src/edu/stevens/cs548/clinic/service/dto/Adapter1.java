//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.12-b141219.1637 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.10.16 at 07:20:41 PM EDT 
//


package edu.stevens.cs548.clinic.service.dto;

import java.util.Date;
import javax.xml.bind.annotation.adapters.XmlAdapter;

public class Adapter1
    extends XmlAdapter<String, Date>
{


    public Date unmarshal(String value) {
        return (edu.stevens.cs548.clinic.service.dto.DateAdapter.parseDate(value));
    }

    public String marshal(Date value) {
        return (edu.stevens.cs548.clinic.service.dto.DateAdapter.printDate(value));
    }

}
