/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
$(document).ready(function () {
    loadAddresses();

    $('#add-button').click(function (event) {

        event.preventDefault();//allows us to bypass the default behavior of a button reloading the
        //web page

        $.ajax({
            type: 'POST',
            url: 'address',
            data: JSON.stringify({
                residentLastName: $('#add-resident-last-name').val(),
                streetAddress: $('#add-street-address').val(),
                cityName: $('#add-city-name').val(),
                state: $('#add-state').val(),
                zipCode: $('#add-zip-code').val()
            }),
            headers:
                    {
                        'Accept': 'application/json',
                        'Content-Type': 'application/json'
                    },
            'dataType': 'json'

        }).success(function (data, status) {
            $('#add-resident-last-name').val('');
            $('#add-street-address').val('');
            $('#add-city-name').val('');
            $('#add-state').val('');
            $('#add-zip-code').val('');

            $('#validationErrors').empty();
            $('.form-error').text('');
            loadAddresses();

        }).error(function (data, status) {
            $('#validationErrors').empty();
            $.each(data.responseJSON.validationErrors, function (index, validationError) {
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
            url: 'search/addresses',
            data: JSON.stringify({
                streetAddress: $('#search-street-address').val(),
                residentLastName: $('#search-resident-last-name').val(),
                cityName: $('#search-city-name').val(),
                state: $('#search-state').val(),
                zipCode: $('#search-zip-code').val()
            }),
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            'dataType': 'json'
        }).success(function (data, status) {
            $('#search-street-address').val('');
            $('#search-resident-last-name').val('');
            $('#search-city-name').val('');
            $('#search-state').val('');
            $('#search-zip-code').val('');
            fillAddressTable(data, status);
        });
    });

});

function fillAddressTable(addressList, status) {

    clearAddressTable();
    var cTable = $('#contentRows');

    $.each(addressList, function (index, address) {
        cTable.append($('<tr>')
                .append($('<td>').append(
                        $('<a>')
                        .attr(
                                {
                                    'data-address-id': address.addressId,
                                    'data-toggle': 'modal',
                                    'data-target': '#detailsModal'
                                }
                        )
                        .text(address.streetAddress))
                        ) 
                .append($('<td>').text(address.residentLastName))
                .append($('<td>').append($('<a>')
                        .attr({
                            'data-address-id': address.addressId,
                            'data-toggle': 'modal',
                            'data-target': '#editModal'
                        })
                        .text('Edit')))
                .append($('<td>').append($('<a>')
                                .attr({'onClick': 'deleteAddress(' + address.addressId + ')'})
                                .text('Delete'))) 
                ); 
    }); 
}

function loadAddresses() {

    $.ajax({
        url: "addresses"
    }).success(function (data, status) {
        fillAddressTable(data, status);
    });
}

function clearAddressTable() {
    $('#contentRows').empty();
}

$('#detailsModal').on('show.bs.modal', function (event) {

    var element = $(event.relatedTarget);
    var addressId = element.data('address-id');
    var modal = $(this);

    $.ajax({
        type: 'GET',
        url: 'address/' + addressId
    }).success(function (address) {
        modal.find('#address-id').text(address.addressId);
        modal.find('#address-residentLastName').text(address.residentLastName);
        modal.find('#address-streetAddress').text(address.streetAddress);
        modal.find('#address-cityName').text(address.cityName);
        modal.find('#address-state').text(address.state);
        modal.find('#address-zipCode').text(address.zipCode);
    });
});

$('#editModal').on('show.bs.modal', function (event) {
    var element = $(event.relatedTarget);
    var addressId = element.data('address-id');
    var modal = $(this);
    $.ajax({
        type: 'GET',
        url: 'address/' + addressId
    }).success(function (address) {
        modal.find('#address-id').text(address.addressId);
        modal.find('#edit-address-id').val(address.addressId);
        modal.find('#edit-resident-last-name').val(address.residentLastName);
        modal.find('#edit-street-address').val(address.streetAddress);
        modal.find('#edit-city-name').val(address.cityName);
        modal.find('#edit-state').val(address.state);
        modal.find('#edit-zip-code').val(address.zipCode);
    });
});

$('edit-button').click(function(event){
    
    event.preventDefault();
    
    $.ajax({
        type:'PUT',
        url:'/address' + $('edit-address-id').val(),
        data: JSON.stringify({
            addressId: $('edit-address-id').val(),
            residentLastName: $('edit-resident-last-name').val(),
            streetAddress: $('edit-street-address').val(),
            cityName: $('edit-city-name').val(),
            state: $('edit-state').val(),
            zipCode: $('edit-zip-code').val(),          
        }),
        headers:
                {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json'
                },
        'dataType': 'json'
    }).success(function () {
        loadAddresses();
    });
});

function deleteAddress(id) {
    var answer = confirm("Do you really want to delete this address?");
    if (answer === true) {
        $.ajax({
            type: 'DELETE',
            url: 'address/' + id
        }).success(function () {
            loadAddresses();
        });
    }
}