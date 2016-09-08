<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Address Book</title>
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
        <link rel="shortcut icon" href="${pageContext.request.contextPath}/img/icon.png">
    </head>
    <body>
        <div class="container">
            <h1>Address Book</h1>
            <hr/>
        </div>
        <div class="container">
            <h1>New Address Form</h1>
            <a href="displayAddressBookNoAjax">Address Book (No Ajax)</a><br/>
            <hr/>
            <sf:form class="form-horizontal" role="form" modelAttribute="address"
                     action="editAddressNoAjax" method="POST">
                <div class="form-group">
                    <label for="add-resident-last-name" class="col-md-4 control-label">Resident Last Name:</label>
                    <div class="col-md-8">
                        <sf:input type="text" class="form-control" id="add-resident-last-name"
                                  path="residentLastName" placeholder="Resident Last Name"/>
                        <sf:errors path="residentLastName" cssclass="error"></sf:errors>
                    </div>
                </div>
                <div class="form-group">
                    <label for="add-street-address" class="col-md-4 control-label">Street Address:</label>
                    <div class="col-md-8">
                        <sf:input type="text" class="form-control" id="add-street-address"
                                  path="streetAddress" placeholder="Street Address"/>
                        <sf:errors path="streetAddress" cssclass="error"></sf:errors>
                    </div>
                </div>
                <div class="form-group">
                    <label for="add-city-name" class="col-md-4 control-label">City:</label>
                    <div class="col-md-8">
                        <sf:input type="text" class="form-control" id="add-city-name"
                                  path="cityName" placeholder="City"/>
                        <sf:errors path="cityName" cssclass="error"></sf:errors>
                    </div>
                </div>
                <div class="form-group">
                    <label for="add-state" class="col-md-4 control-label">State:</label>
                    <div class="col-md-8">
                        <sf:input type="text" class="form-control" id="add-state"
                                  path="state" placeholder="State"/>
                        <sf:errors path="state" cssclass="error"></sf:errors>
                    </div>
                </div>
                <div class="form-group">
                    <label for="add-zip" class="col-md-4 control-label">Zip Code:</label>
                    <div class="col-md-8">
                        <sf:input type="tel" class="form-control" id="add-zip"
                                  path="zipCode" placeholder="Zip Code"/>
                        <sf:hidden path="addressId"/>
                        <sf:errors path="zipCode" cssclass="error"></sf:errors>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-md-offset-4 col-md-8">
                        <button type="submit" id="add-button" class="btn btn-default">Edit Address</button>
                    </div>
                </div>
            </sf:form>
        </div>
        <script src="${pageContext.request.contextPath}/js/jquery-1.11.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    </body>
</html>
