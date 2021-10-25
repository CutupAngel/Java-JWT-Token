/**
 * Created by Asad Sarwar on 9/28/2021.
 */

$('#pin').on('keyup', function (event) {
    if (event.keyCode === 13) {
        login();
    }
});
function login() {
    removeLocalStorage(MTG_CONSTANTS.MTG_USER_TOKEN);
    // removeLocalStorage(MTG_CONSTANTS.MTG_USER_AUTHORITIES);

    $.ajax({
        url: contextRoot+'api/auth/login',
        headers: {
            'Authorization': null
        },
        method: 'POST',
        // data: {username: $('#email').val(), password: $('#password').val()},\
        contentType: 'application/json',
        data: JSON.stringify({userName: "admin", password: $('#pin').val()}),
        success: function (data) {
            addLocalStorage(MTG_CONSTANTS.MTG_USER_TOKEN, data.token);
            setCookie(MTG_CONSTANTS.MTG_USER_TOKEN, data.token);
//                 addLocalStorage(MTG_CONSTANTS.MTG_USER_AUTHORITIES, loginObject.token);
            $(location).attr('href', contextRoot);
        },
        error: function (err) {
            console.log(err);
            alert(err.responseJSON.message);
        }
    });
}