/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$(document).ready(function () {
    populateCommentDiv();

    $('#comment-submit').on('click', function () {
        event.preventDefault();

        $.ajax({
            type: 'POST',
            url: '/snapdragon/comment/new',
            data: JSON.stringify({
                blogEntryId: $('#blog-entry-id').text(),
                commentorName: $('#commentor-name').val(),
                commentBody: $('#comment-body').val()
            }),
            contentType: 'application/json; charset=utf-8',
            headers: {
                'Content-Type': 'application/json'
            }
        }).success(function (data, status) {
            populateCommentDiv();
        }).error(function (data, status) {
            //Show error message
        });
    });
});

/* ---------- LOAD COMMENTS ---------- */
function populateCommentDiv() {
    var commentDiv = $('#comments');
    commentDiv.empty();

    $.ajax({
        url: '/snapdragon/comment/blogcomments/' + $('#blog-entry-id').text()
    }).success(function (data, status) {
        for (var i = 0; i < data.length; i++) {
            commentDiv.append($('<p class="c-name">').text(data[i].commentorName))
                    .append($('<p class="c-body">').text(data[i].commentBody));
        }
    });


}
