/**
 * Created by Asad Sarwar on 9/29/2021.
 */
/**
 * Created by Jamali on 9/9/2021.
 */

const feed = $('#feed');

$(function () {
    let mintedId = $('#minted_id').attr('value');
    $.ajax({
        url: contextRoot + "api/query/minted/" + mintedId,
        type: 'GET',
        success: function(img){
            console.log('response:',img);
            feed.append(getMediaCard(img.ipfsUrl, img.caption || '', moment(img.createdDate).fromNow()));
        },
        error: function(error) {
            console.log('error:', error);
        }
    });

});


function getMediaCard(_imageUrl, _caption, _timestamp) {
    return '' +
        '            <div class="col-md-6 col-centered">' +
        '              <div class="card mb-6" style="width: 615px !important;">' +
        '                <img class="card-img-top" src="'+_imageUrl+'" data-holder-rendered="true" style="height: 614px; width: 614px; display: block;">' +
        '                <div class="card-body">' +
        '                  <p class="card-text">'+_caption+'</p>' +
        '                  <div class="d-flex justify-content-between align-items-center">' +
        // '                    <div class="btn-group">' +
        // '                      <button type="button" onclick="openMedia(\''+ _mediaId +'\')" class="btn btn-sm btn-outline-secondary">View</button>' +
        // '                    </div>' +
        '                    <small class="text-muted">'+_timestamp+'</small>' +
        '                  </div>' +
        '                </div>' +
        '              </div>' +
        '            </div>' +
        '            <div class="col-md-12" style="margin-bottom: 20px"></div>';
}