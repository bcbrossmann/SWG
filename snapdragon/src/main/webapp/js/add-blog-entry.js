var blogTags = [];

$(document).ready(function () {
    $('#add-be-submit').click(function (event) {
        event.preventDefault();

        // JSON OBJECT
        var post = JSON.stringify({
            entryName: $('#add-be-name').val(),
            entryBody: CKEDITOR.instances.addBeBody.getData(),
            tags: blogTags
        });

        $.ajax({
            type: 'POST',
            url: '/snapdragon/blog/new',
            data: post,
            contentType: 'application/json; charset=utf-8',
            headers: {
                'Content-Type': 'application/json'
            }
        }).success(function (data, status) { 
            //alert('Success');
            window.location = ('jsp/manage-blog-entries.jsp');
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

    submitTag();
});

/* ------- CLEAR FORM ------- */
function clearForm() {
    event.preventDefault();
    $('#add-be-name').val('');
    CKEDITOR.instances.addBeBody.setData('');
    $('#add-be-tag-field').val('');
}


/* PUSH TAG,
 * APPEND TAG,
 * CLEAR TAG */
function addTag() {
    blogTags.push($('#add-be-tag-field').val());
    $('#tag-container').append($('#add-be-tag-field').val() + " ");
    $('#add-be-tag-field').val('');
}

/* ------- SUBMIT TAG ------- */
function submitTag() {
    // ADD TAG ON SUBMIT
    $('#add-be-tag-submit').click(function (event) {
        event.preventDefault();
        addTag();
    });

    // ADD TAG ON SPACE AND ENTER
    $('#add-be-tag-field').keydown(function (event) {
        if (event.keyCode === 32 || event.keyCode === 13) {
            event.preventDefault();
            if ($('#add-be-tag-field').val() !== '') {
                addTag();
            }
        }
    });
}