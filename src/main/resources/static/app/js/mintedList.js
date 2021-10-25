/**
 * Created by Asad Sarwar on 9/29/2021.
 */
/**
 * Created by Jamali on 9/9/2021.
 */

const feed = $('#feed');
let accessToken = $('#access_token').attr('value');
let userId = $('#user_id').attr('value');

$(function () {
    accessToken = $('#access_token').attr('value');
    userId = $('#user_id').attr('value');

    console.log(accessToken);
    console.log(userId);

    $.ajax({
        url: contextRoot + "api/query/minted",
        type: 'GET',
        success: function(response){
            console.log('response:',response);
            //load images
            for(let img of response){
                feed.append(getMediaCard(img.id, img.ipfsUrl, img.caption || '', moment(img.createdDate).fromNow()));
            }
        },
        error: function(error) {
            console.log('error:', error);
        }
    });

});

// let myDiv = document.getElementById('feed');
//
// $(window).scroll(
//     function scrolled(e) {
//         if (myDiv.offsetHeight + myDiv.scrollTop >= myDiv.scrollHeight) {
//             console.log('scrolledToBottom(e);');
//         }
//     }
// );


function getMediaCard(_mediaId, _imageUrl, _caption, _timestamp) {
    return '' +
        '            <div class="col-md-6 col-centered">' +
        '              <div class="card mb-6" style="width: 615px !important;">' +
        '                <img class="card-img-top" src="'+_imageUrl+'" data-holder-rendered="true" style="height: 614px; width: 614px; display: block;">' +
        '                <div class="card-body">' +
        '                  <p class="card-text">'+_caption+'</p>' +
        '                  <div class="d-flex justify-content-between align-items-center">' +
        '                    <div class="btn-group">' +
        '                      <button type="button" onclick="openMedia(\''+ _mediaId +'\')" class="btn btn-sm btn-outline-secondary">View</button>' +
        '                    </div>' +
        '                    <small class="text-muted">'+_timestamp+'</small>' +
        '                  </div>' +
        '                </div>' +
        '              </div>' +
        '            </div>' +
        '            <div class="col-md-12" style="margin-bottom: 20px"></div>';
}

function openMedia(_mediaId) {
    window.open('/view/minted/' + _mediaId,'_self');
}