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
        url: getUserMediaListUrl(accessToken),
        type: 'GET',
        success: function(response){
            console.log('response:',response.data);
            //load images
            for(let d of response.data){
                $.ajax({
                    url: getUserMediaDetailUrl(d.id,accessToken),
                    type: 'GET',
                    // async: false,
                    success: function (rsp) {
                        if(rsp.media_type === 'IMAGE'){
                            feed.append(getMediaCard(rsp.id, rsp.media_url, rsp.caption || '', moment(rsp.timestamp).fromNow()));
                        }
                    },
                    error: function(error) {
                        console.log('error:', error);
                    }
                });
            }

        },
        error: function(error) {
            console.log('error:', error);
        }
    });

});


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
    window.open('/insta/view?mediaId='+ _mediaId +'&userId='+userId+'&accessToken='+accessToken,"_self");
}