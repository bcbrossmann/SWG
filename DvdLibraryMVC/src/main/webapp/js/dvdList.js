/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
$(document).ready(function () {
    loadDVDs();


    $('#add-button').click(function (event) {

        event.preventDefault();

        $.ajax({
            type: 'POST',
            url: 'dvd',
            data: JSON.stringify({
                title: $('#add-title').val(),
                releaseDate: $('#add-release-date').val(),
                mpaaRating: $('#add-mpaa-rating').val(),
                directorName: $('#add-director-name').val(),
                studio: $('#add-studio').val(),
                userRating: $('#add-user-rating').val()

            }),
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            'dataType': 'json'
        }).success(function (data, status) {

            $('#add-title').val(''),
            $('#add-release-date').val(''),
            $('#add-mpaa-rating').val(''),
            $('#add-director-name').val(''),
            $('#add-studio').val(''),
            $('#add-user-rating').val('');
    
            $('validationErrors').empty();
            $('.form-error').text('');
            loadDVDs();

        }).error(function(data, status){
            $('#validationErrors').empty();
            $.each(data.responseJSON.validationErrors, function(index, validationError){
                var errorDiv = $('#validationErrors');
                errorDiv.append(validationError.message).append($('<br>'));
                $('#' + validationError.fieldName).text(validationError.message);
            });
        });
    });



    $('#search-button').click(function (event) {
        event.preventDefault();

        $.ajax({
            type: 'POST',
            url: 'search/dvds',
            data: JSON.stringify({
                title: $('#search-title').val(),
                releaseDate: $('#search-release-date').val(),
                mpaaRating: $('#search-mpaa-rating').val(),
                directorName: $('#search-director-name').val(),
                studio: $('#search-studio').val(),
                userRating: $('#search-user-rating').val()
            }),
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            'dataType': 'json'
        }).success(function (data, status) {
            $('#search-title').val('');
            $('#search-release-date').val('');
            $('#search-mpaa-rating').val('');
            $('#search-director-name').val('');
            $('#search-studio').val('');
            $('#search-user-rating').val('');

            fillDvdTable(data, status);
        });
    });

});

function fillDvdTable(dvdList, status) {

    clearDvdTable();
    var cTable = $('#contentRows');

    $.each(dvdList, function (index, dvd) {
        cTable.append($('<tr>')
                .append($('<td>')
                        .append($('<a>')
                                .attr({
                                    'data-dvd-id': dvd.dvdid,
                                    'data-toggle': 'modal',
                                    'data-target': '#detailsModal'
                                })
                                .text(dvd.title)))
                .append($('<td>').text(dvd.releaseDate))
                .append($('<td>')
                        .append($('<a>')
                                .attr({
                                    'data-dvd-id': dvd.dvdid,
                                    'data-toggle': 'modal',
                                    'data-target': '#editModal'
                                })
                                .text('Edit')))
                .append($('<td>')
                        .append($('<a>')
                                .attr({
                                    'onClick': 'deleteDvd(' +
                                            dvd.dvdid + ')'
                                })
                                .text('Delete')))
                );
    });
}

function loadDVDs() {
    $.ajax({
        url: "dvds"
    }).success(function (data, status) {
        fillDvdTable(data, status);
    });
}



function clearDvdTable() {
    $('#contentRows').empty();
}

$('#detailsModal').on('show.bs.modal', function (event) {

    var element = $(event.relatedTarget);
    var dvdId = element.data('dvd-id');
    var modal = $(this);

    $.ajax({
        type: 'GET',
        url: 'dvd/' + dvdId
    }).success(function (dvd) {
        modal.find('#dvd-id').text(dvd.dvdid);
        modal.find('#dvd-title').text(dvd.title);
        modal.find('#dvd-releaseDate').text(dvd.releaseDate);
        modal.find('#dvd-mpaaRating').text(dvd.mpaaRating);
        modal.find('#dvd-directorName').text(dvd.directorName);
        modal.find('#dvd-studio').text(dvd.studio);
        modal.find('#dvd-userRating').text(dvd.userRating);
    });
});

$('#editModal').on('show.bs.modal', function (event) {

    var element = $(event.relatedTarget);
    var dvdId = element.data('dvd-id');
    var modal = $(this);

    $.ajax({
        type: 'GET',
        url: 'dvd/' + dvdId
    }).success(function (dvd) {
        modal.find('#dvd-id').text(dvd.dvdid);
        modal.find('#edit-dvd-id').val(dvd.dvdid);
        modal.find('#edit-title').val(dvd.title);
        modal.find('#edit-release-date').val(dvd.releaseDate);
        modal.find('#edit-mpaa-rating').val(dvd.mpaaRating);
        modal.find('#edit-director-name').val(dvd.directorName);
        modal.find('#edit-studio').val(dvd.studio);
        modal.find('#edit-user-rating').val(dvd.userRating);
    });
});

    $('#edit-button').click(function (event) {

        event.preventDefault();

        $.ajax({
            type: 'PUT',
            url: 'dvd/' + $('#edit-dvd-id').val(),
            data: JSON.stringify({
                dvdId: $('#edit-dvd-id').val(),
                title: $('#edit-title').val(),
                releaseDate: $('#edit-release-date').val(),
                mpaaRating: $('#edit-mpaa-rating').val(),
                directorName: $('#edit-director-name').val(),
                studio: $('#edit-studio').val(),
                userRating: $('#edit-user-rating').val()
            }),
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            'dataType': 'json'
        }).success(function () {
            loadDVDs();
        });
    });
    
    function deleteDvd(id) {
    var answer = confirm("Do you really want to delete this DVD?");
    if (answer === true) {
        $.ajax({
            type: 'DELETE',
            url: 'dvd/' + id
        }).success(function () {
            loadDVDs();
        });
    }
}