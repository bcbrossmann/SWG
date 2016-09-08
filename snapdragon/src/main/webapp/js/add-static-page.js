/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var widget = uploadcare.Widget('[role=uploadcare-uploader]');

$(document).ready(function () {
    var imageUrl;
    var imageWidth;
    var imageHeight;

    widget.onChange(function (file) {
        if (file) {
            file.done(function (info) {
                imageUrl = info.cdnUrl;
                imageWidth = info.originalImageInfo.width;
                imageHeight = info.originalImageInfo.height;
            });
        }
    });

    $('#add-sp-preview').on('click', function () {
        event.preventDefault();

        var preview = window.open('static-page-preview.jsp');
        
        preview.previewTitle = $('#add-sp-title').val();
        preview.previewBody = CKEDITOR.instances.addSpBody.getData();
        preview.previewImageUrl = $('#add-sp-image').val();
        preview.previewImageAltText = $('#add-sp-alttext').val();
    });

    $('#add-sp-cancel').on('click', function () {
        event.preventDefault();
        clearAddStaticPageForm();
    });

    $('#add-sp-submit').on('click', function () {
        event.preventDefault();

        $.ajax({
            type: 'POST',
            url: '/snapdragon/page/new',
            data: JSON.stringify({
                spTitle: $('#add-sp-title').val(),
                spBody: CKEDITOR.instances.addSpBody.getData(),
                picture: {
                    spImageUrl: imageUrl,
                    spImageAltText: $('#add-sp-alttext').val(),
                    imageHeight: imageHeight,
                    imageWidth: imageWidth
                }
            }),
            contentType: 'application/json; charset=utf-8',
            headers: {
                'Content-Type': 'application/json'
            }
        }).success(function (data, status) { 
            //alert('Success');
            window.location = ('jsp/manage-static-page.jsp');
        }).error(function (data, status) {
//            alert('Error');
            $('#validationErrors').empty();
            // #2 - Go through each of the fieldErrors and display the associated error
            // message in the validationErrors div
            $.each(data.responseJSON.fieldErrors, function (index, validationError) {
            var errorDiv = $('#validationErrors');
                errorDiv.append(validationError.message).append($('<br>'));
            });
        });
    });
});

function clearAddStaticPageForm() {
    event.preventDefault();
    $('#add-sp-title').val('');
    widget.value(null);
    $('#add-sp-alttext').val('');
    CKEDITOR.instances.addSpBody.setData('');
}

