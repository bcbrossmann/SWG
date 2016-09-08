<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Address Book</title>
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css"
              rel="stylesheet">
        <link rel="shortcut icon"
              href="${pageContext.request.contextPath}/img/icon.png">
    </head>
    <body>
        <div class="container">
            <h1>Address Book</h1>
            <hr/>
        </div>
        <div class="container">
            <h1>New Address Form</h1>
            <a href="displayAddressBookNoAjax">Address Book (No
                Ajax)</a><br/>
            <hr/>
            <form class="form-horizontal" role="form" modelAttribute="address"
                     action="addNewAddressNoAjax" method="POST">
                <div class="form-group">
                    <label for="add-resident-last-name" class="col-md-4 control-label">Resident Last Name:</label>
                    <div class="col-md-8">
                        <input type="text" class="form-control" id="add-resident-last-name"
                                  name="residentLastName" placeholder="Resident Last Name"/>
                        <errors path="residentLastName" cssclass="error"></errors>
                    </div>
                </div>
                <div class="form-group">
                    <label for="add-street-address" class="col-md-4 control-label">Street Address:</label>
                    <div class="col-md-8">
                        <input type="text" class="form-control" id="add-street-address"
                                  name="streetAddress" placeholder="Street Address"/>
                        <errors path="streetAddress" cssclass="error"></errors>
                    </div>
                </div>
                <div class="form-group">
                    <label for="add-city-name" class="col-md-4 control-label">City:</label>
                    <div class="col-md-8">
                        <input type="text" class="form-control" id="add-city-name"
                                  name="cityName" placeholder="City"/>
                        <errors path="cityName" cssclass="error"></errors>
                    </div>
                </div>
                <div class="form-group">
                    <label for="add-state" class="col-md-4 control-label">State:</label>
                    <div class="col-md-8">
                        <input type="text" class="form-control" id="add-state"
                                  name="state" placeholder="State"/>
                        <errors path="state" cssclass="error"></errors>
                    </div>
                </div>
                <div class="form-group">
                    <label for="add-zip" class="col-md-4 control-label">Zip Code:</label>
                    <div class="col-md-8">
                        <input type="text" class="form-control" id="add-zip"
                                  name="zipCode" placeholder="Zip Code"/>
                        <hidden name="addressId"/>
                        <errors path="zipCode" cssclass="error"></errors>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-md-offset-4 col-md-8">
                        <button type="submit" id="add-button" class="btn btn-default">Add Address</button>
                    </div>
                </div>
            </form>
        </div>
        <!-- Placed at the end of the document so the pages load faster -->
        <script src="${pageContext.request.contextPath}/js/jquery-1.11.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    </body>
</html>
