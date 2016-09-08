<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Address Book (No Ajax)</title>
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css"
              rel="stylesheet">
        <link rel="shortcut icon"
              href="${pageContext.request.contextPath}/img/icon.png">
    </head>
    <body>
        <div class="container">
            <h1>Address Book (No Ajax)</h1>
            <hr/>
            <div class="navbar">
                <ul class="nav nav-tabs">
                    <li role="presentation">
                        <a
                            href="${pageContext.request.contextPath}/home">Home</a>
                    </li>
                    <li role="presentation">
                        <a
                            href="${pageContext.request.contextPath}/search">Search</a>
                    </li>
                    <li role="presentation">
                        <a
                            href="${pageContext.request.contextPath}/stats">Stats</a>
                    </li>
                    <li role="presentation" class="active">
                        <a href="${pageContext.request.contextPath}/displayAddressBookNoAjax">
                            Address Book (No Ajax)
                        </a>
                    </li>
                </ul>
            </div>
        </div>
        <div class="container">
            <h1>Address Book</h1>
            <a href="displayNewAddressFormNoAjax">Add an Address</a><br/>
            <hr/>

            <c:forEach var="address" items="${addressList}">
                <s:url value="deleteAddressNoAjax"
                       var="deleteAddress_url">
                    <s:param name="addressId" value="${address.addressId}" />
                </s:url>
                <s:url value="displayEditAddressFormNoAjax"
                       var="editAddress_url">
                    <s:param name="addressId" value="${address.addressId}" />
                </s:url>               
                Resident Last Name: ${address.residentLastName}<br/>
                Address: ${address.streetAddress}<br/>
                City: ${address.cityName}<br/>
                <a href="${deleteAddress_url}">Delete</a> | 
                <a href="${editAddress_url}">Edit</a><br/>
                <hr>               
            </c:forEach>
            <script src="${pageContext.request.contextPath}/js/jquery-1.11.1.min.js"></script>
            <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    </body>
</html>
