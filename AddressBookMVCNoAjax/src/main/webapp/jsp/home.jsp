<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Address Book</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css">
        <link rel="shortcut icon" href="${pageContext.request.contextPath}/img/icon.png">
    </head>
    <body>
        <div class="container">
            <h1>Address Book</h1>
            <hr />
            <div class="navbar">
                <ul class="nav nav-tabs">
                    <li role="presentation" class="active">
                        <a href="${pageContext.request.contextPath}/home">Home</a>
                    </li>
                    <li role="presentation">
                        <a href="${pageContext.request.contextPath}/search">Search</a>
                    </li>
                    <li role="presentation">
                        <a href="${pageContext.request.contextPath}/stats">Stats</a>
                    </li>
                    <li role="presentation">
                        <a href="${pageContext.request.contextPath}/displayAddressBookNoAjax">
                            Address Book (No Ajax)
                        </a>
                    </li>
                </ul>
            </div>
            <div class ="row">
                <div class ="col-md-6">
                    <h2>Address Book</h2>
                    <table id ="addressTable" class="table table-hover">
                        <tr>
                            <th width="40%">Address</th>
                            <th width="30%">Last Name</th>
                            <th width="15%"></th>
                            <th width="15%"></th>             
                        </tr>
                        <tbody id="contentRows"></tbody>
                    </table>
                </div>
                <div class="col-md-6">
                    <h2>Add New Address</h2>
                    <form class="form-horizontal" role="form">
                        <div class="form-group">
                            <label form="add-resident-last-name" class="col-md-4 control-label">Last Name:</label>
                            <div class="col-md-8">
                                <input type="text" class="form-control" id="add-resident-last-name" placeholder="Last Name">
                                <div id="residentLastName" style="color:red" class="form-error"></div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label form="add-street-address" class="col-md-4 control-label">Address:</label>
                            <div class="col-md-8">
                                <input type="text" class="form-control" id="add-street-address" placeholder="Address">
                                <div id="streetAddress" style="color:red" class="form-error"></div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label form="add-city-name" class="col-md-4 control-label">City:</label>
                            <div class="col-md-8">
                                <input type="text" class="form-control" id="add-city-name" placeholder="City">
                                <div id="cityName" style="color:red" class="form-error"></div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label form="add-state" class="col-md-4 control-label">State:</label>
                            <div class="col-md-8">
                                <input type="text" class="form-control" id="add-state" placeholder="State">
                                <div id="state" style="color:red" class="form-error"></div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label form="add-zip-code" class="col-md-4 control-label">Zip Code:</label>
                            <div class="col-md-8">
                                <input type="text" class="form-control" id="add-zip-code" placeholder="Zip">
                                <div id="zipCode" style="color:red" class="form-error"></div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-md-offset-4 col-md-8">
                                <button type="submit" id="add-button" class="btn btn-default">Create Address</button>
                            </div>                               
                        </div>
                    </form>
                    <div id="validationErrors" style="color: red"/>
                </div>
            </div>
        </div>

        <!-- Details Modal -->
        <div class="modal fade" id="detailsModal" tabindex="-1" roll="dialog" 
             aria-labelledby="detailsModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal">
                            <span aria-hidden="true">&times;</span>
                            <span class="sr-only">Close</span>
                        </button>
                        <h4 class="modal-title" id="detailsModalLabel">Address Details</h4> 
                    </div>
                    <div class="modal-body">
                        <h3 id="address-id"></h3>
                        <table class="table table-bordered">
                            <tr><th>Last Name:</th><td id="address-residentLastName"></tr>
                            <tr><th>Address:</th><td id="address-streetAddress"></tr>
                            <tr><th>City:</th><td id="address-cityName"></tr>
                            <tr><th>State:</th><td id="address-state"></tr>
                            <tr><th>Zip Code:</th><td id="address-zipCode"></tr>
                        </table>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                    </div>
                </div>
            </div>
        </div>
        <!-- Details Modal -- END -->
        
        <!-- Edit Modal -->
        <div class="modal fade" id="editModal" tabindex="-1" role="dialog"
             aria labelledby="detailsModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal">
                            <span aria-hidden="true">&times;</span>
                            <span class="sr-only">Close</span>
                        </button>
                        <h4 class="modal-title" id="detailsModalLabel">Edit Address</h4>
                    </div>
                    <div class="modal-body">
                        <h3 id="address-id"></h3>
                        <form class="form-horizontal" role="form">
                            <div class="form-group">
                                <label for="edit-resident-last-name" class="col-md-4 control-label">Last Name:</label>
                                <div class="col-md-8">
                                    <input type="text" class="form-control" id="edit-resident-last-name" placeholder="Last Name">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="edit-street-address" class="col-md-4 control-label">Address:</label>
                                <div class="col-md-8">
                                    <input type="text" class="form-control" id="edit-street-address" placeholder="Address">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="edit-city-name" class="col-md-4 control-label">City:</label>
                                <div class="col-md-8">
                                    <input type="text" class="form-control" id="edit-city-name" placeholder="City">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="edit-state" class="col-md-4 control-label">State:</label>
                                <div class="col-md-8">
                                    <input type="text" class="form-control" id="edit-state" placeholder="State">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="edit-zip-code" class="col-md-4 control-label">Zip Code:</label>
                                <div class="col-md-8">
                                    <input type="text" class="form-control" id="edit-zip-code" placeholder="Zip Code">
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-md-offset-4 col-md-8">
                                    <button type="submit" id="edit-button" class="btn btn-default" data-dismiss="modal">
                                        Edit Address
                                    </button>
                                    <button type="button" class="btn btn-default" data-dismiss="modal">
                                        Cancel
                                    </button>
                                    <input type="hidden" id="edit-address-id">
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
        <!-- Edit Modal -- END -->
        
        <script src="${pageContext.request.contextPath}/js/jquery-1.11.3.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/addressList.js"></script>
    </body>
</html>
<!--
            