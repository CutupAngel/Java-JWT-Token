/**
 * Created by Jamali on 9/9/2021.
 */

let success_url = $('#success_url').attr('value');

$('#createBtn').on('click',function(){

    window.open('https://api.instagram.com/oauth/authorize' +
        '?client_id=' + INSTA_APP_ID +
        '&redirect_uri=' + success_url +
        '&scope=user_profile,user_media' +
        '&response_type=code', "_self"
    );
});

let tableBody = $('#tableBody');

$(function () {

    $.ajax({
        url: '/insta/latestMinted',
        type: 'GET',
        success: function (rsp) {
            try{
                for(let i = rsp.length-1 ; i > -1 ; i--){
                    r = rsp[i];
                    tableBody.append(getRow(r.imageUrl, r.ipfsUrl, moment(r.createdDate).fromNow(), "https://ipfs.io/ipfs/" + r.metaDataIpfsHash ));
                }
            }catch (err){
                console.log(err)
            }
        },
        error: function(err) {
            // alert(err.responseText);
            // console.log('error:', err);
        }
    });

});

function getRow(_img, _ipfsUrl, _mintedOn, _metaDataIpfsUrl){
    return '' +
        '<tr>' +
        '    <th> <img src="'+_ipfsUrl+'" style="width: 50px; height: 50px" ></th>' +
        '    <td> <a href="'+_ipfsUrl+'">'+_ipfsUrl+'</a><br><a href='+_metaDataIpfsUrl+'>'+_metaDataIpfsUrl+'</a></td>' +
        '    <td>'+_mintedOn+'</td>' +
        '</tr>' +
        '';
}