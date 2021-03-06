# SOAP-based-web-service

APPENDIX: 
1.	XML SCHEMAS FOR DTOs 
2.	WSDL for Web Services 
3.	IMPORTANT INTERFACES 
4.	IMPORTANT APIs 
 
 
 
1. XML SCHEMAS FOR DTOs: 
 
PatientDTO.xsd 
 
<?xml version="1.0" encoding="UTF-8"?> 
<schema targetNamespace="http://cs548.stevens.edu/clinic/service/web/soap/patient"  	xmlns:jaxb="http://java.sun.com/xml/ns/jaxb" jaxb:version="2.0" 
 	elementFormDefault="unqualified" xmlns="http://www.w3.org/2001/XMLSchema" 
 	xmlns:tns="http://cs548.stevens.edu/clinic/schemas/patient" 
 	attributeFormDefault="unqualified"> 
 	<annotation> 
 	 	<appinfo> 
 	 	 	<jaxb:globalBindings> 
 	 	 	 	<jaxb:javaType name="java.util.Date" xmlType="date" 
 	 	 	 
 	parseMethod="edu.stevens.cs548.clinic.service.dto.DateAdapter.parseDate" 
 	 	 	 
 	printMethod="edu.stevens.cs548.clinic.service.dto.DateAdapter.printDate" /> 
 	 	 	</jaxb:globalBindings> 
 	 	</appinfo> 
 	</annotation> 
 
 
 	<element name="patient-dto">  	 	<complexType> 
 	 	 	<sequence> 
 	 	 	 	<element name="id" type="long" /> 
 	 	 	 	<element name="patient-id" type="long" /> 
 	 	 	 	<element name="name" type="string" /> 
	 	 	 	<element name="dob" type="date" /> 
 	 	 	<element name="treatments" type="long" maxOccurs="unbounded"  	 	 	 	nillable="true" /> 
	 	 	</sequence> 
	 	</complexType> 
 	</element>  	 </schema> 
 
 
 
ProviderDTO.xsd 
 
<?xml version="1.0" encoding="UTF-8" standalone="yes"?> 
<schema targetNamespace="http://cs548.stevens.edu/clinic/service/web/soap/provider"  	xmlns:jaxb="http://java.sun.com/xml/ns/jaxb" jaxb:version="2.0" 
 	elementFormDefault="unqualified" xmlns="http://www.w3.org/2001/XMLSchema"  	xmlns:tns="http://www.example.org/clinic/schemas/provider"  	attributeFormDefault="unqualified"> 
 
 	<annotation>  	 	<appinfo> 
 	 	 	<jaxb:globalBindings> 
 	 	 	 	<jaxb:javaType name="java.util.Date" xmlType="date" 
 	 	 	 
 	parseMethod="edu.stevens.cs548.clinic.service.dto.DateAdapter.parseDate" 
 	 	 	 
 	printMethod="edu.stevens.cs548.clinic.service.dto.DateAdapter.printDate" /> 
 	 	 	</jaxb:globalBindings> 
 	 	</appinfo>  	</annotation> 
 
 	<element name="provider-dto">  	 	<complexType> 
 	 	 	<sequence> 
 	 	 	 	<element name="id" type="long" /> 
 	 	 	 	<element name="provider-id" type="long" /> 
 	 	 	 	<element name="name" type="string" /> 
 	 	 	 	<element name="specialization" type="string" />  	 	 	 	<element name="treatments" type="long" nillable="true" 
 	 	 	 	 	minOccurs="0" maxOccurs="unbounded" /> 
 	 	 	</sequence> 
 	 	</complexType> 
 	</element> 
</schema> 
TreatmentDTO.xsd 
 
<?xml version="1.0" encoding="UTF-8"?> 
<schema targetNamespace="http://cs548.stevens.edu/clinic/schemas/treatment"  	xmlns:jaxb="http://java.sun.com/xml/ns/jaxb" jaxb:version="2.0" 
 	elementFormDefault="qualified" xmlns="http://www.w3.org/2001/XMLSchema"  	xmlns:tns="http://cs548.stevens.edu/clinic/schemas/treatment"> 
 
 	<annotation>  	 	<appinfo> 
 	 	 	<jaxb:globalBindings> 
 	 	 	 	<jaxb:javaType name="java.util.Date" xmlType="date" 
 	 	 	 
 	parseMethod="edu.stevens.cs548.clinic.service.dto.DateAdapter.parseDate" 
 	 	 	 
 	printMethod="edu.stevens.cs548.clinic.service.dto.DateAdapter.printDate" /> 
 	 	 	</jaxb:globalBindings> 
 	 	</appinfo> 
 	</annotation> 
 
 
 
 	<element name="treatment-dto"> 
 	 	<complexType> 
 	 	 	<sequence>  	 	 	 	 
 	 	 	 	<element name="diagnosis" type="string"></element> 
 	 	 	 	<choice> 
 	 	 	 	 	<element name="drug-treatment" 
type="tns:DrugTreatmentType"></element> 
 	 	 	 	 	<element name="radiology" type="tns:RadiologyType"></element> 
 	 	 	 	 	<element name="surgery" type="tns:SurgeryType"></element> 
 	 	 	 	</choice> 
 	 	 	</sequence>  	 	</complexType> 
 	</element> 
 
 	<complexType name="DrugTreatmentType"> 
 	 	<sequence> 
 	 	 	<element name="name" type="string"></element> 
	 	 	<element name="dosage" type="float"></element> 
	 	 	<element name="physician" type="string"></element> 
	 	</sequence> 
</complexType> 
 	<complexType name="RadiologyType"> 
 	 	<sequence> 
 	 	 	<element name="date" type="date" minOccurs="1" maxOccurs="unbounded"></element> 
 	 	 	<element name="radiologist" type="string"></element> 
 	 	</sequence>  	</complexType> 
 
 	<complexType name="SurgeryType"> 
 	 	<sequence> 
 	 	 	<element name="date" type="date"></element> 
 	 	 	<element name="surgeon" type="string"></element> 
 	 	</sequence>  	</complexType> 
 
 
</schema> 
 
5.	WSDL FOR WEB SERVICES 
 
5.1.	PATIENT WSDL 
 
This XML file does not appear to have any style information associated with it. The document tree is shown below. 
<!-- 
 Published by JAX-WS RI (http://jax-ws.java.net). RI's version is Metro/2.3.1-b419 (branches/2.3.1.x-7937; 
2014-08-04T08:11:03+0000) JAXWS-RI/2.2.10-b140803.1500 JAXWS-API/2.2.11 JAXB-RI/2.2.10b140802.1033 JAXB-API/2.2.12-b140109.1041 svn-revision#unknown.  
--> 
<!-- 
 Generated by JAX-WS RI (http://jax-ws.java.net). RI's version is Metro/2.3.1-b419 (branches/2.3.1.x-7937; 
2014-08-04T08:11:03+0000) JAXWS-RI/2.2.10-b140803.1500 JAXWS-API/2.2.11 JAXB-RI/2.2.10b140802.1033 JAXB-API/2.2.12-b140109.1041 svn-revision#unknown.  
--> 
<definitions xmlns:wsu="http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-utility-
1.0.xsd" xmlns:wsp="http://www.w3.org/ns/ws-policy" xmlns:wsp1_2="http://schemas.xmlsoap.org/ws/2004/09/policy" xmlns:wsam="http://www.w3.org/2007/05/addressing/metadata" xmlns:soap="http://schemas.xmlsoap.org/wsdl/soap/" 
xmlns:tns="http://cs548.stevens.edu/clinic/service/web/soap/patient" 
xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns="http://schemas.xmlsoap.org/wsdl/" targetNamespace="http://cs548.stevens.edu/clinic/service/web/soap/patient" name="PatientWebService"> 
<types> 
<xsd:schema> 
<xsd:import namespace="http://cs548.stevens.edu/clinic/service/web/soap/patient" schemaLocation="http://ec2-35-160-42-247.us-west-
2.compute.amazonaws.com:8080/ClinicWebService/PatientWebService?xsd=1"/> 
</xsd:schema> 
<xsd:schema> 
<xsd:import namespace="http://cs548.stevens.edu/clinic/schemas/treatment" schemaLocation="http://ec2-35-160-42-247.us-west-
2.compute.amazonaws.com:8080/ClinicWebService/PatientWebService?xsd=2"/> 
</xsd:schema> 
</types> 
<message name="getPatientsByNameDob"> 
<part name="parameters" element="tns:getPatientsByNameDob"/> 
</message> 
<message name="getPatientsByNameDobResponse"> 
<part name="parameters" element="tns:getPatientsByNameDobResponse"/> 
</message> 
<message name="PatientServiceExn"> 
<part name="fault" element="tns:PatientServiceExn"/> 
</message> 
<message name="getPatientByPatId"> 
<part name="parameters" element="tns:getPatientByPatId"/> 
</message> 
<message name="getPatientByPatIdResponse"> 
<part name="parameters" element="tns:getPatientByPatIdResponse"/> 
</message> 
<message name="siteInfo"> 
<part name="parameters" element="tns:siteInfo"/> 
</message> 
<message name="siteInfoResponse"> 
<part name="parameters" element="tns:siteInfoResponse"/> 
</message> 
<message name="getPatientByDbId"> 
<part name="parameters" element="tns:getPatientByDbId"/> 
</message> 
<message name="getPatientByDbIdResponse"> 
<part name="parameters" element="tns:getPatientByDbIdResponse"/> 
</message> 
<message name="create"> 
<part name="parameters" element="tns:create"/> 
</message> 
<message name="createResponse"> 
<part name="parameters" element="tns:createResponse"/> 
</message> 
<message name="addDrugTreatment"> 
<part name="parameters" element="tns:addDrugTreatment"/> 
</message> 
<message name="addDrugTreatmentResponse"> 
<part name="parameters" element="tns:addDrugTreatmentResponse"/> 
</message> 
<message name="PatientNotFoundExn"> 
<part name="fault" element="tns:PatientNotFoundExn"/> 
</message> 
<message name="addSurgery"> 
<part name="parameters" element="tns:addSurgery"/> 
</message> 
<message name="addSurgeryResponse"> 
<part name="parameters" element="tns:addSurgeryResponse"/> 
</message> 
<message name="addRadiology"> 
<part name="parameters" element="tns:addRadiology"/> 
</message> 
<message name="addRadiologyResponse"> 
<part name="parameters" element="tns:addRadiologyResponse"/> 
</message> 
<message name="getTreatments"> 
<part name="parameters" element="tns:getTreatments"/> 
</message> 
<message name="getTreatmentsResponse"> 
<part name="parameters" element="tns:getTreatmentsResponse"/> 
</message> 
<message name="TreatmentNotFoundExn"> 
<part name="fault" element="tns:TreatmentNotFoundExn"/> 
</message> 
<message name="deleteTreatment"> 
<part name="parameters" element="tns:deleteTreatment"/> 
</message> 
<message name="deleteTreatmentResponse"> 
<part name="parameters" element="tns:deleteTreatmentResponse"/> 
</message> 
<message name="TreatmentExn"> 
<part name="fault" element="tns:TreatmentExn"/> 
</message> 
<portType name="IPatientWebPort"> 
<operation name="getPatientsByNameDob"> 
<input 
wsam:Action="http://cs548.stevens.edu/clinic/service/web/soap/patient/IPatientWebPort/getPatientsBy NameDobRequest" message="tns:getPatientsByNameDob"/> 
<output 
wsam:Action="http://cs548.stevens.edu/clinic/service/web/soap/patient/IPatientWebPort/getPatientsBy
NameDobResponse" message="tns:getPatientsByNameDobResponse"/> <fault message="tns:PatientServiceExn" name="PatientServiceExn" 
wsam:Action="http://cs548.stevens.edu/clinic/service/web/soap/patient/IPatientWebPort/getPatientsBy NameDob/Fault/PatientServiceExn"/> 
</operation> 
<operation name="getPatientByPatId"> 
<input 
wsam:Action="http://cs548.stevens.edu/clinic/service/web/soap/patient/IPatientWebPort/getPatientByP atIdRequest" message="tns:getPatientByPatId"/> 
<output 
wsam:Action="http://cs548.stevens.edu/clinic/service/web/soap/patient/IPatientWebPort/getPatientByP atIdResponse" message="tns:getPatientByPatIdResponse"/> <fault message="tns:PatientServiceExn" name="PatientServiceExn" 
wsam:Action="http://cs548.stevens.edu/clinic/service/web/soap/patient/IPatientWebPort/getPatientByP atId/Fault/PatientServiceExn"/> 
</operation> 
<operation name="siteInfo"> 
<input 
wsam:Action="http://cs548.stevens.edu/clinic/service/web/soap/patient/IPatientWebPort/siteInfoReques t" message="tns:siteInfo"/> 
<output 
wsam:Action="http://cs548.stevens.edu/clinic/service/web/soap/patient/IPatientWebPort/siteInfoRespon se" message="tns:siteInfoResponse"/> 
</operation> 
<operation name="getPatientByDbId"> 
<input 
wsam:Action="http://cs548.stevens.edu/clinic/service/web/soap/patient/IPatientWebPort/getPatientByD bIdRequest" message="tns:getPatientByDbId"/> 
<output 
wsam:Action="http://cs548.stevens.edu/clinic/service/web/soap/patient/IPatientWebPort/getPatientByD bIdResponse" message="tns:getPatientByDbIdResponse"/> <fault message="tns:PatientServiceExn" name="PatientServiceExn" 
wsam:Action="http://cs548.stevens.edu/clinic/service/web/soap/patient/IPatientWebPort/getPatientByD bId/Fault/PatientServiceExn"/> 
</operation> 
<operation name="create"> 
<input 
wsam:Action="http://cs548.stevens.edu/clinic/service/web/soap/patient/IPatientWebPort/createRequest " message="tns:create"/> 
<output 
wsam:Action="http://cs548.stevens.edu/clinic/service/web/soap/patient/IPatientWebPort/createRespons e" message="tns:createResponse"/> 
<fault message="tns:PatientServiceExn" name="PatientServiceExn" 
wsam:Action="http://cs548.stevens.edu/clinic/service/web/soap/patient/IPatientWebPort/create/Fault/P atientServiceExn"/> 
</operation> 
<operation name="addDrugTreatment"> 
<input 
wsam:Action="http://cs548.stevens.edu/clinic/service/web/soap/patient/IPatientWebPort/addDrugTreat mentRequest" message="tns:addDrugTreatment"/> 
<output 
wsam:Action="http://cs548.stevens.edu/clinic/service/web/soap/patient/IPatientWebPort/addDrugTreat mentResponse" message="tns:addDrugTreatmentResponse"/> 
<fault message="tns:PatientNotFoundExn" name="PatientNotFoundExn" 
wsam:Action="http://cs548.stevens.edu/clinic/service/web/soap/patient/IPatientWebPort/addDrugTreat ment/Fault/PatientNotFoundExn"/> 
</operation> 
<operation name="addSurgery"> 
<input 
wsam:Action="http://cs548.stevens.edu/clinic/service/web/soap/patient/IPatientWebPort/addSurgeryRe quest" message="tns:addSurgery"/> 
<output 
wsam:Action="http://cs548.stevens.edu/clinic/service/web/soap/patient/IPatientWebPort/addSurgeryRes ponse" message="tns:addSurgeryResponse"/> 
<fault message="tns:PatientNotFoundExn" name="PatientNotFoundExn" 
wsam:Action="http://cs548.stevens.edu/clinic/service/web/soap/patient/IPatientWebPort/addSurgery/Fa ult/PatientNotFoundExn"/> 
</operation> 
<operation name="addRadiology"> 
<input 
wsam:Action="http://cs548.stevens.edu/clinic/service/web/soap/patient/IPatientWebPort/addRadiologyR equest" message="tns:addRadiology"/> 
<output 
wsam:Action="http://cs548.stevens.edu/clinic/service/web/soap/patient/IPatientWebPort/addRadiologyR esponse" message="tns:addRadiologyResponse"/> 
<fault message="tns:PatientNotFoundExn" name="PatientNotFoundExn" 
wsam:Action="http://cs548.stevens.edu/clinic/service/web/soap/patient/IPatientWebPort/addRadiology/ Fault/PatientNotFoundExn"/> 
</operation> 
<operation name="getTreatments"> 
<input 
wsam:Action="http://cs548.stevens.edu/clinic/service/web/soap/patient/IPatientWebPort/getTreatments Request" message="tns:getTreatments"/> 
<output 
wsam:Action="http://cs548.stevens.edu/clinic/service/web/soap/patient/IPatientWebPort/getTreatments
Response" message="tns:getTreatmentsResponse"/> 
<fault message="tns:PatientNotFoundExn" name="PatientNotFoundExn" 
wsam:Action="http://cs548.stevens.edu/clinic/service/web/soap/patient/IPatientWebPort/getTreatments /Fault/PatientNotFoundExn"/> 
<fault message="tns:TreatmentNotFoundExn" name="TreatmentNotFoundExn" 
wsam:Action="http://cs548.stevens.edu/clinic/service/web/soap/patient/IPatientWebPort/getTreatments /Fault/TreatmentNotFoundExn"/> 
<fault message="tns:PatientServiceExn" name="PatientServiceExn" 
wsam:Action="http://cs548.stevens.edu/clinic/service/web/soap/patient/IPatientWebPort/getTreatments /Fault/PatientServiceExn"/> 
</operation> 
<operation name="deleteTreatment"> 
<input 
wsam:Action="http://cs548.stevens.edu/clinic/service/web/soap/patient/IPatientWebPort/deleteTreatme ntRequest" message="tns:deleteTreatment"/> 
<output 
wsam:Action="http://cs548.stevens.edu/clinic/service/web/soap/patient/IPatientWebPort/deleteTreatme ntResponse" message="tns:deleteTreatmentResponse"/> 
<fault message="tns:PatientNotFoundExn" name="PatientNotFoundExn" 
wsam:Action="http://cs548.stevens.edu/clinic/service/web/soap/patient/IPatientWebPort/deleteTreatme nt/Fault/PatientNotFoundExn"/> 
<fault message="tns:TreatmentNotFoundExn" name="TreatmentNotFoundExn" 
wsam:Action="http://cs548.stevens.edu/clinic/service/web/soap/patient/IPatientWebPort/deleteTreatme nt/Fault/TreatmentNotFoundExn"/> 
<fault message="tns:PatientServiceExn" name="PatientServiceExn" 
wsam:Action="http://cs548.stevens.edu/clinic/service/web/soap/patient/IPatientWebPort/deleteTreatme nt/Fault/PatientServiceExn"/> 
<fault message="tns:TreatmentExn" name="TreatmentExn" 
wsam:Action="http://cs548.stevens.edu/clinic/service/web/soap/patient/IPatientWebPort/deleteTreatme nt/Fault/TreatmentExn"/> 
</operation> 
</portType> 
<binding name="PatientWebPortBinding" type="tns:IPatientWebPort"> 
<soap:binding transport="http://schemas.xmlsoap.org/soap/http" style="document"/> 
<operation name="getPatientsByNameDob"> 
<soap:operation soapAction=""/> 
<input> 
<soap:body use="literal"/> 
</input> 
<output> 
<soap:body use="literal"/> 
</output> 
<fault name="PatientServiceExn"> 
<soap:fault name="PatientServiceExn" use="literal"/> 
</fault> 
</operation> 
<operation name="getPatientByPatId"> 
<soap:operation soapAction=""/> 
<input> 
<soap:body use="literal"/> 
</input> 
<output> 
<soap:body use="literal"/> 
</output> 
<fault name="PatientServiceExn"> 
<soap:fault name="PatientServiceExn" use="literal"/> 
</fault> 
</operation> 
<operation name="siteInfo"> 
<soap:operation soapAction=""/> 
<input> 
<soap:body use="literal"/> 
</input> 
<output> 
<soap:body use="literal"/> 
</output> 
</operation> 
<operation name="getPatientByDbId"> 
<soap:operation soapAction=""/> 
<input> 
<soap:body use="literal"/> 
</input> 
<output> 
<soap:body use="literal"/> 
</output> 
<fault name="PatientServiceExn"> 
<soap:fault name="PatientServiceExn" use="literal"/> 
</fault> 
</operation> 
<operation name="create"> 
<soap:operation soapAction=""/> 
<input> 
<soap:body use="literal"/> 
</input> 
<output> 
<soap:body use="literal"/> 
</output> 
<fault name="PatientServiceExn"> 
<soap:fault name="PatientServiceExn" use="literal"/> 
</fault> 
</operation> 
<operation name="addDrugTreatment"> 
<soap:operation soapAction=""/> 
<input> 
<soap:body use="literal"/> 
</input> 
<output> 
<soap:body use="literal"/> 
</output> 
<fault name="PatientNotFoundExn"> 
<soap:fault name="PatientNotFoundExn" use="literal"/> 
</fault> 
</operation> 
<operation name="addSurgery"> 
<soap:operation soapAction=""/> 
<input> 
<soap:body use="literal"/> 
</input> 
<output> 
<soap:body use="literal"/> 
</output> 
<fault name="PatientNotFoundExn"> 
<soap:fault name="PatientNotFoundExn" use="literal"/> 
</fault> 
</operation> 
<operation name="addRadiology"> 
<soap:operation soapAction=""/> 
<input> 
<soap:body use="literal"/> 
</input> 
<output> 
<soap:body use="literal"/> 
</output> 
<fault name="PatientNotFoundExn"> 
<soap:fault name="PatientNotFoundExn" use="literal"/> 
</fault> 
</operation> 
<operation name="getTreatments"> 
<soap:operation soapAction=""/> 
<input> 
<soap:body use="literal"/> 
</input> 
<output> 
<soap:body use="literal"/> 
</output> 
<fault name="PatientNotFoundExn"> 
<soap:fault name="PatientNotFoundExn" use="literal"/> 
</fault> 
<fault name="TreatmentNotFoundExn"> 
<soap:fault name="TreatmentNotFoundExn" use="literal"/> 
</fault> 
<fault name="PatientServiceExn"> 
<soap:fault name="PatientServiceExn" use="literal"/> 
</fault> 
</operation> 
<operation name="deleteTreatment"> 
<soap:operation soapAction=""/> 
<input> 
<soap:body use="literal"/> 
</input> 
<output> 
<soap:body use="literal"/> 
</output> 
<fault name="PatientNotFoundExn"> 
<soap:fault name="PatientNotFoundExn" use="literal"/> 
</fault> 
<fault name="TreatmentNotFoundExn"> 
<soap:fault name="TreatmentNotFoundExn" use="literal"/> 
</fault> 
<fault name="PatientServiceExn"> 
<soap:fault name="PatientServiceExn" use="literal"/> 
</fault> 
<fault name="TreatmentExn"> 
<soap:fault name="TreatmentExn" use="literal"/> 
</fault> 
</operation> 
</binding> 
<service name="PatientWebService"> 
<port name="PatientWebPort" binding="tns:PatientWebPortBinding"> 
<soap:address location="http://ec2-35-160-42-247.us-west-
2.compute.amazonaws.com:8080/ClinicWebService/PatientWebService"/> </port> 
</service> 
</definitions> 
 
5.2.	PROVIDER WSDL 
This XML file does not appear to have any style information associated with it. The document tree is shown below. 
<!-- 
 Published by JAX-WS RI (http://jax-ws.java.net). RI's version is Metro/2.3.1-b419 
(branches/2.3.1.x-7937; 2014-08-04T08:11:03+0000) JAXWS-RI/2.2.10-b140803.1500 JAXWSAPI/2.2.11 JAXB-RI/2.2.10-b140802.1033 JAXB-API/2.2.12-b140109.1041 svn-revision#unknown.  
--> 
<!-- 
 Generated by JAX-WS RI (http://jax-ws.java.net). RI's version is Metro/2.3.1-b419 
(branches/2.3.1.x-7937; 2014-08-04T08:11:03+0000) JAXWS-RI/2.2.10-b140803.1500 JAXWSAPI/2.2.11 JAXB-RI/2.2.10-b140802.1033 JAXB-API/2.2.12-b140109.1041 svn-revision#unknown.  
--> 
<definitions xmlns:wsu="http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurityutility-1.0.xsd" xmlns:wsp="http://www.w3.org/ns/ws-policy" xmlns:wsp1_2="http://schemas.xmlsoap.org/ws/2004/09/policy" xmlns:wsam="http://www.w3.org/2007/05/addressing/metadata" xmlns:soap="http://schemas.xmlsoap.org/wsdl/soap/" 
xmlns:tns="http://cs548.stevens.edu/clinic/service/web/soap/provider" 
xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns="http://schemas.xmlsoap.org/wsdl/" targetNamespace="http://cs548.stevens.edu/clinic/service/web/soap/provider" name="ProviderWebService"> 
<types> 
<xsd:schema> 
<xsd:import namespace="http://cs548.stevens.edu/clinic/service/web/soap/provider" schemaLocation="http://ec2-35-160-42-247.us-west-
2.compute.amazonaws.com:8080/ClinicWebService/ProviderWebService?xsd=1"/> 
</xsd:schema> 
<xsd:schema> 
<xsd:import namespace="http://cs548.stevens.edu/clinic/service/web/soap/patient" schemaLocation="http://ec2-35-160-42-247.us-west-
2.compute.amazonaws.com:8080/ClinicWebService/ProviderWebService?xsd=2"/> 
</xsd:schema> 
<xsd:schema> 
<xsd:import namespace="http://cs548.stevens.edu/clinic/schemas/treatment" schemaLocation="http://ec2-35-160-42-247.us-west-
2.compute.amazonaws.com:8080/ClinicWebService/ProviderWebService?xsd=3"/> 
</xsd:schema> 
</types> 
<message name="getProviderByNpi"> 
<part name="parameters" element="tns:getProviderByNpi"/> 
</message> 
<message name="getProviderByNpiResponse"> 
<part name="parameters" element="tns:getProviderByNpiResponse"/> 
</message> 
<message name="ProviderServiceExn"> 
<part name="fault" element="tns:ProviderServiceExn"/> </message> 
<message name="siteInfo"> 
<part name="parameters" element="tns:siteInfo"/> 
</message> 
<message name="siteInfoResponse"> 
<part name="parameters" element="tns:siteInfoResponse"/> 
</message> 
<message name="create"> 
<part name="parameters" element="tns:create"/> 
</message> 
<message name="createResponse"> 
<part name="parameters" element="tns:createResponse"/> 
</message> 
<message name="addDrugTreatment"> 
<part name="parameters" element="tns:addDrugTreatment"/> 
</message> 
<message name="addDrugTreatmentResponse"> 
<part name="parameters" element="tns:addDrugTreatmentResponse"/> 
</message> 
<message name="ProviderNotFoundExn"> 
<part name="fault" element="tns:ProviderNotFoundExn"/> 
</message> 
<message name="PatientNotFoundExn"> 
<part name="fault" element="tns:PatientNotFoundExn"/> 
</message> 
<message name="addSurgery"> 
<part name="parameters" element="tns:addSurgery"/> 
</message> 
<message name="addSurgeryResponse"> 
<part name="parameters" element="tns:addSurgeryResponse"/> 
</message> 
<message name="addRadiology"> 
<part name="parameters" element="tns:addRadiology"/> 
</message> 
<message name="addRadiologyResponse"> 
<part name="parameters" element="tns:addRadiologyResponse"/> 
</message> 
<message name="getTreatments"> 
<part name="parameters" element="tns:getTreatments"/> 
</message> 
<message name="getTreatmentsResponse"> 
<part name="parameters" element="tns:getTreatmentsResponse"/> 
</message> 
<message name="TreatmentNotFoundExn"> 
<part name="fault" element="tns:TreatmentNotFoundExn"/> </message> 
<message name="getProvider"> 
<part name="parameters" element="tns:getProvider"/> 
</message> 
<message name="getProviderResponse"> 
<part name="parameters" element="tns:getProviderResponse"/> 
</message> 
<portType name="IProviderWebPort"> 
<operation name="getProviderByNpi"> 
<input 
wsam:Action="http://cs548.stevens.edu/clinic/service/web/soap/provider/IProviderWebPort/getP roviderByNpiRequest" message="tns:getProviderByNpi"/> 
<output 
wsam:Action="http://cs548.stevens.edu/clinic/service/web/soap/provider/IProviderWebPort/getP roviderByNpiResponse" message="tns:getProviderByNpiResponse"/> <fault message="tns:ProviderServiceExn" name="ProviderServiceExn" 
wsam:Action="http://cs548.stevens.edu/clinic/service/web/soap/provider/IProviderWebPort/getP roviderByNpi/Fault/ProviderServiceExn"/> 
</operation> 
<operation name="siteInfo"> 
<input 
wsam:Action="http://cs548.stevens.edu/clinic/service/web/soap/provider/IProviderWebPort/siteI nfoRequest" message="tns:siteInfo"/> 
<output 
wsam:Action="http://cs548.stevens.edu/clinic/service/web/soap/provider/IProviderWebPort/siteI nfoResponse" message="tns:siteInfoResponse"/> 
</operation> 
<operation name="create"> 
<input 
wsam:Action="http://cs548.stevens.edu/clinic/service/web/soap/provider/IProviderWebPort/crea teRequest" message="tns:create"/> 
<output 
wsam:Action="http://cs548.stevens.edu/clinic/service/web/soap/provider/IProviderWebPort/crea teResponse" message="tns:createResponse"/> 
<fault message="tns:ProviderServiceExn" name="ProviderServiceExn" 
wsam:Action="http://cs548.stevens.edu/clinic/service/web/soap/provider/IProviderWebPort/crea te/Fault/ProviderServiceExn"/> 
</operation> 
<operation name="addDrugTreatment"> 
<input 
wsam:Action="http://cs548.stevens.edu/clinic/service/web/soap/provider/IProviderWebPort/add DrugTreatmentRequest" message="tns:addDrugTreatment"/> 
<output 
wsam:Action="http://cs548.stevens.edu/clinic/service/web/soap/provider/IProviderWebPort/add
DrugTreatmentResponse" message="tns:addDrugTreatmentResponse"/> 
<fault message="tns:ProviderNotFoundExn" name="ProviderNotFoundExn" 
wsam:Action="http://cs548.stevens.edu/clinic/service/web/soap/provider/IProviderWebPort/add
DrugTreatment/Fault/ProviderNotFoundExn"/> 
<fault message="tns:PatientNotFoundExn" name="PatientNotFoundExn" 
wsam:Action="http://cs548.stevens.edu/clinic/service/web/soap/provider/IProviderWebPort/add DrugTreatment/Fault/PatientNotFoundExn"/> 
</operation> 
<operation name="addSurgery"> 
<input 
wsam:Action="http://cs548.stevens.edu/clinic/service/web/soap/provider/IProviderWebPort/addS urgeryRequest" message="tns:addSurgery"/> 
<output 
wsam:Action="http://cs548.stevens.edu/clinic/service/web/soap/provider/IProviderWebPort/addS urgeryResponse" message="tns:addSurgeryResponse"/> 
<fault message="tns:ProviderNotFoundExn" name="ProviderNotFoundExn" 
wsam:Action="http://cs548.stevens.edu/clinic/service/web/soap/provider/IProviderWebPort/addS urgery/Fault/ProviderNotFoundExn"/> 
<fault message="tns:PatientNotFoundExn" name="PatientNotFoundExn" 
wsam:Action="http://cs548.stevens.edu/clinic/service/web/soap/provider/IProviderWebPort/addS urgery/Fault/PatientNotFoundExn"/> 
</operation> 
<operation name="addRadiology"> 
<input 
wsam:Action="http://cs548.stevens.edu/clinic/service/web/soap/provider/IProviderWebPort/add RadiologyRequest" message="tns:addRadiology"/> 
<output 
wsam:Action="http://cs548.stevens.edu/clinic/service/web/soap/provider/IProviderWebPort/add
RadiologyResponse" message="tns:addRadiologyResponse"/> 
<fault message="tns:ProviderNotFoundExn" name="ProviderNotFoundExn" 
wsam:Action="http://cs548.stevens.edu/clinic/service/web/soap/provider/IProviderWebPort/add
Radiology/Fault/ProviderNotFoundExn"/> 
<fault message="tns:PatientNotFoundExn" name="PatientNotFoundExn" 
wsam:Action="http://cs548.stevens.edu/clinic/service/web/soap/provider/IProviderWebPort/add Radiology/Fault/PatientNotFoundExn"/> 
</operation> 
<operation name="getTreatments"> 
<input 
wsam:Action="http://cs548.stevens.edu/clinic/service/web/soap/provider/IProviderWebPort/getT reatmentsRequest" message="tns:getTreatments"/> 
<output 
wsam:Action="http://cs548.stevens.edu/clinic/service/web/soap/provider/IProviderWebPort/getT reatmentsResponse" message="tns:getTreatmentsResponse"/> 
<fault message="tns:ProviderNotFoundExn" name="ProviderNotFoundExn" 
wsam:Action="http://cs548.stevens.edu/clinic/service/web/soap/provider/IProviderWebPort/getT reatments/Fault/ProviderNotFoundExn"/> 
<fault message="tns:TreatmentNotFoundExn" name="TreatmentNotFoundExn" 
wsam:Action="http://cs548.stevens.edu/clinic/service/web/soap/provider/IProviderWebPort/getT reatments/Fault/TreatmentNotFoundExn"/> 
<fault message="tns:ProviderServiceExn" name="ProviderServiceExn" 
wsam:Action="http://cs548.stevens.edu/clinic/service/web/soap/provider/IProviderWebPort/getT reatments/Fault/ProviderServiceExn"/> 
</operation> 
<operation name="getProvider"> 
<input 
wsam:Action="http://cs548.stevens.edu/clinic/service/web/soap/provider/IProviderWebPort/getP roviderRequest" message="tns:getProvider"/> 
<output 
wsam:Action="http://cs548.stevens.edu/clinic/service/web/soap/provider/IProviderWebPort/getP roviderResponse" message="tns:getProviderResponse"/> 
<fault message="tns:ProviderServiceExn" name="ProviderServiceExn" 
wsam:Action="http://cs548.stevens.edu/clinic/service/web/soap/provider/IProviderWebPort/getP rovider/Fault/ProviderServiceExn"/> 
</operation> 
</portType> 
<binding name="ProviderWebPortBinding" type="tns:IProviderWebPort"> 
<soap:binding transport="http://schemas.xmlsoap.org/soap/http" style="document"/> 
<operation name="getProviderByNpi"> 
<soap:operation soapAction=""/> 
<input> 
<soap:body use="literal"/> 
</input> 
<output> 
<soap:body use="literal"/> 
</output> 
<fault name="ProviderServiceExn"> 
<soap:fault name="ProviderServiceExn" use="literal"/> 
</fault> 
</operation> 
<operation name="siteInfo"> 
<soap:operation soapAction=""/> 
<input> 
<soap:body use="literal"/> 
</input> 
<output> 
<soap:body use="literal"/> 
</output> 
</operation> 
<operation name="create"> 
<soap:operation soapAction=""/> 
<input> 
<soap:body use="literal"/> 
</input> 
<output> 
<soap:body use="literal"/> 
</output> 
<fault name="ProviderServiceExn"> 
<soap:fault name="ProviderServiceExn" use="literal"/> 
</fault> 
</operation> 
<operation name="addDrugTreatment"> 
<soap:operation soapAction=""/> 
<input> 
<soap:body use="literal"/> 
</input> 
<output> 
<soap:body use="literal"/> 
</output> 
<fault name="ProviderNotFoundExn"> 
<soap:fault name="ProviderNotFoundExn" use="literal"/> 
</fault> 
<fault name="PatientNotFoundExn"> 
<soap:fault name="PatientNotFoundExn" use="literal"/> 
</fault> 
</operation> 
<operation name="addSurgery"> 
<soap:operation soapAction=""/> 
<input> 
<soap:body use="literal"/> 
</input> 
<output> 
<soap:body use="literal"/> 
</output> 
<fault name="ProviderNotFoundExn"> 
<soap:fault name="ProviderNotFoundExn" use="literal"/> 
</fault> 
<fault name="PatientNotFoundExn"> 
<soap:fault name="PatientNotFoundExn" use="literal"/> 
</fault> 
</operation> 
<operation name="addRadiology"> 
<soap:operation soapAction=""/> 
<input> 
<soap:body use="literal"/> 
</input> 
<output> 
<soap:body use="literal"/> 
</output> 
<fault name="ProviderNotFoundExn"> 
<soap:fault name="ProviderNotFoundExn" use="literal"/> 
</fault> 
<fault name="PatientNotFoundExn"> 
<soap:fault name="PatientNotFoundExn" use="literal"/> 
</fault> 
</operation> 
<operation name="getTreatments"> 
<soap:operation soapAction=""/> 
<input> 
<soap:body use="literal"/> 
</input> 
<output> 
<soap:body use="literal"/> 
</output> 
<fault name="ProviderNotFoundExn"> 
<soap:fault name="ProviderNotFoundExn" use="literal"/> 
</fault> 
<fault name="TreatmentNotFoundExn"> 
<soap:fault name="TreatmentNotFoundExn" use="literal"/> 
</fault> 
<fault name="ProviderServiceExn"> 
<soap:fault name="ProviderServiceExn" use="literal"/> 
</fault> 
</operation> 
<operation name="getProvider"> 
<soap:operation soapAction=""/> 
<input> 
<soap:body use="literal"/> 
</input> 
<output> 
<soap:body use="literal"/> 
</output> 
<fault name="ProviderServiceExn"> 
<soap:fault name="ProviderServiceExn" use="literal"/> 
</fault> 
</operation> 
</binding> 
<service name="ProviderWebService"> 
<port name="ProviderWebPort" binding="tns:ProviderWebPortBinding"> 
<soap:address location="http://ec2-35-160-42-247.us-west-
2.compute.amazonaws.com:8080/ClinicWebService/ProviderWebService"/> </port> 
</service> 
</definitions> 
 
 
6.	IMPORTANT INTERFACES  
 
CLINIC SERVICE:  
1)	IPatientService : Implements the patient services.  
2)	IPatientServiceLocal: Local service interface for PatientService.  
3)	IPatientServiceRemote: Remote service interface for PatientService.  
4)	IProviderService : Implements the provider services. 
5)	IProviderServiceLocal: Local service interface for ProviderService.  
6)	IProviderServiceRemote: Remote service interface for ProviderService. 
 
CLINIC WEB SERVICE  
1)	IPatientWebService: Web service interface for patient services.  
2)	IProviderWebService: Web service interface for provider services. 
 
4.   IMPORTANT OBSERVATIONS:  
1)	Glassfish application server has many bugs. For example, the Glassfish 4.0 restricts access to all schemas and hence you get a browser error while generating the Client Tester. You have to add this property in the JVM options so as to allow access to all schemas.  
2)	You should always undeploy your previous version of the application before deploying your latest one else Glassfish will show an EJB error. 
