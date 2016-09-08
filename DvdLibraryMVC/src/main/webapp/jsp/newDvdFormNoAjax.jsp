<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>DVD Library</title>
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css"
              rel="stylesheet">
        <link rel="shortcut icon"
              href="${pageContext.request.contextPath}/img/icon.png">
    </head>
    <body>
        <div class="container">
            <h1>DVD Library</h1>
            <hr/>
        </div>
        <div class="container">
            <h1>New Dvd Form</h1>
            <a href="displayDvdLibraryNoAjax">DVD Library (No
                Ajax)</a><br/>
            <hr/>
            <form class="form-horizontal"
                  role="form"
                  action="addNewDvdNoAjax"
                  method="POST">
                <div class="form-group">
                    <label for="add-title"
                           class="col-md-4 control-label">Title:</label>
                    <div class="col-md-8">
                        <input type="text"
                               class="form-control"
                               id="add-title"
                               name="title"
                               placeholder="Title"/>
                    </div>
                </div>
                <div class="form-group">
                    <label for="add-release-date" class="col-md-4 controllabel">Release Date:</label>
                    <div class="col-md-8">
                        <input type="text"
                               class="form-control"
                               id="add-release-date"
                               name="releaseDate"
                               placeholder="Release Date"/>
                    </div>
                </div>
                <div class="form-group">
                    <label for="add-mpaa-rating"
                           class="col-md-4 control-label">Rating:</label>
                    <div class="col-md-8">
                        <input type="text"
                               class="form-control"
                               id="add-mpaa-rating"
                               name="mpaaRating"
                               placeholder="MPAARating"/>
                    </div>
                </div>
                <div class="form-group">
                    <label for="add-director-name" class="col-md-4 controllabel">Director:</label>
                    <div class="col-md-8">
                        <input type="text"
                               class="form-control"
                               id="add-director-name"
                               name="directorName"
                               placeholder="Director"/>
                    </div>
                </div>
                <div class="form-group">
                    <label for="add-studio" class="col-md-4 controllabel">Studio</label>
                    <div class="col-md-8">
                        <input type="text"
                               class="form-control"
                               id="add-studio"
                               name="studio"
                               placeholder="Studio"/>
                    </div>
                </div>
                <div class="form-group">
                    <label for="add-user-rating" class="col-md-4 controllabel">User Rating:</label>
                    <div class="col-md-8">
                        <input type="text"
                               class="form-control"
                               id="add-user-rating"
                               name="userRating"
                               placeholder="User Rating"/>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-md-offset-4 col-md-8">
                        <button type="submit"
                                id="add-button"
                                class="btn btn-default">Add New
                            DVD</button>
                    </div>
                </div>
            </form>
        </div>
        <script src="${pageContext.request.contextPath}/js/jquery-1.11.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    </body>
</html>
