/**
 * Created by Asad Sarwar on 9/9/2021.
 */

const feed = $('#feed');
let imageUrl = null;
let caption = null;
$(function () {
    let accessToken = $('#access_token').attr('value');
    let userId = $('#user_id').attr('value');
    let mediaId = $('#media_id').attr('value');

    console.log(accessToken);
    console.log(userId);
    console.log(mediaId);

    $.ajax({
        url: getUserMediaDetailUrl(mediaId, accessToken),
        type: 'GET',
        success: function (rsp) {
            feed.append(getMediaCard(rsp.id, rsp.media_url, rsp.caption || '', moment(rsp.timestamp).fromNow()));
            imageUrl = rsp.media_url;
        },
        error: function(error) {
            console.log('error:', error);
        }
    });

    // imageUrl = 'https://cdn.pixabay.com/photo/2015/04/23/22/00/tree-736885_960_720.jpg';
    // imageUrl = 'https://media.istockphoto.com/photos/row-of-books-on-a-shelf-multicolored-book-spines-stack-in-the-picture-id1222550815';
    // caption = 'Test Image';
    // feed.append(getMediaCard(1, imageUrl, caption, moment(new Date() - 1000 * 60 * 60 *5).fromNow()));

});








function getMediaCard(_mediaId, _imageUrl, _caption, _timestamp) {
    return '' +
        '<div class="col-md-12">' +
            '<div class="card mb-12 box-shadow">' +
                '<img class="card-img-top" src="'+_imageUrl+'" data-holder-rendered="true" style="height: 600px; width: 100%; display: block;">' +
                '<div class="card-body">' +
                    '<h6 class="card-text">Please Select Account</h6>' +
                    '<select id="address">' +
                        '<option value="0x3d2916a46115d5bec8f61254368111ce12879181">0x3d2916a46115d5bec8f61254368111ce12879181</option>'+
                        '<option value="0x7408322d8114c1be2673b2838e372cf7713ce3e7">0x7408322d8114c1be2673b2838e372cf7713ce3e7</option>'+
                        '<option value="0xe7cd814770796bceaec6f49232b186c02cf85158">0xe7cd814770796bceaec6f49232b186c02cf85158</option>'+
                        '<option value="0x8e6b3307fe373a046319404f9e08a0f887147f08">0x8e6b3307fe373a046319404f9e08a0f887147f08</option>'+
                        '<option value="0x137ec4a38e30513351e2c026a3e8fcebbbe8d462">0x137ec4a38e30513351e2c026a3e8fcebbbe8d462</option>'+
                    '</select>'+
                    '<h6 class="card-text">Please Enter Description</h6>' +
                    '<input type="text" style="width: 100%" id="description" maxlength="300" />' +
                    '<p class="card-text">'+_caption+'</p>' +
                    '<div class="d-flex justify-content-between align-items-center">' +
                        '<div class="btn-group">' +
                            '<button type="button" onclick="mint()" class="btn btn-sm btn-outline-secondary">Mint</button>' +
                        '</div>' +
                    '<small class="text-muted">'+_timestamp+'</small>' +
                    '</div>' +
                '</div>' +
            '</div>' +
        '</div>' +
        '';
}

function mint(){

    $.ajax({
        url: '/insta/mint',
        type: 'POST',
        data: {imageUrl : imageUrl, address : $('#address').val(), description: $('#description').val(), caption: caption},
        success: function (rsp) {
            console.log(rsp);
            alert("Image minted");
            window.open("/minted/" + rsp);
        },
        error: function(err) {
            alert(err.responseText);
            console.log('error:', err);
        }
    });

}