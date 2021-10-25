const INSTA_APP_ID = 1148266559032113;
// const INSTA_SUCCESS_AUTH_URL = 'https://staging.binarybitz.com/insta/valid';
const INSTA_FAILURE_AUTH_URL = 'https://staging.binarybitz.com/insta/invalid';

const INSTA_USER_MEDIA_URL = 'https://graph.instagram.com/me/media';
const INSTA_USER_MEDIA_DETAIL_URL = 'https://graph.instagram.com/';
const MEDIA_DETAIL_FIELDS = "fields=id,caption,media_type,media_url,username,timestamp";

const MTG_CONSTANTS = {
    MTG_USER_TOKEN: "MTG_USER_TOKEN",
    MTG_USER_AUTHORITIES: "MTG_USER_AUTHORITIES"
}

function getUserMediaListUrl(_accessToken){
    return INSTA_USER_MEDIA_URL + "?access_token=" + _accessToken;
}

function getUserMediaDetailUrl(_mediaId,_accessToken){
    return INSTA_USER_MEDIA_DETAIL_URL + _mediaId + "?" + MEDIA_DETAIL_FIELDS + "&access_token=" + _accessToken;
}



////////////////////////////////////
////////// UTIL FUNCTIONS //////////
////////////////////////////////////
function isNull(cmp) {
    if (cmp) {
        if (typeof (cmp) === "boolean") {
            return false;
        } else if (cmp === null) {
            return true;
        } else {
            return false;
        }
    } else {
        return true;
    }
}

function isNotNull(cmp) {
    return !isNull(cmp);
}

function isEmpty(cmp) {
    return cmp.trim() === '';
}

function isNotNullNorEmpty(cmp) {
    return isNotNull(cmp) && !isEmpty(cmp);
}

/////////////////////////////////////////////////////////
////////// Export Buttons Export Functionality //////////
/////////////////////////////////////////////////////////
function initUtilFunction() {

    initMap();

    enableExpandFunctionality();
    enableExportButtonsFunctionality();
}

function enableExportButtonsFunctionality() {
    // Click event bind to all export buttons (i.e. csv, excel, pdf)
    $('.app-xbtn').click(function () {
        console.log('Export Button clicked');
        let button = $(this).attr('export-btn');
        let tableId = $(this).attr('table-id');

        $(tableId).DataTable().buttons(button).trigger();
        // $(tableId).find(button).click();
    });

    $('.download-app-excel').click(function(){
        console.log('Working . . . download-app-excel');
        downloadExcelFile();
    });
}

function enableExpandFunctionality() {
    $('.fa-expand').unbind();
    $('.fa-expand').click(function () {
        console.log('Working:', this);
        $($(this).attr('master-div')).toggleClass('app_popup');
        let cid = $(this).attr('chart-id');
        if(typeof cid != 'undefined'){
            reflowChart(cid);
        }
    });
}

function reflowChart(_id) {
    $('#' + _id).highcharts().reflow()
}
// //////////////////////////////
// //////// AJAX CALLS //////////
// //////////////////////////////
function ajaxCall(url, type, data, successCallBack, errorCallBack) {
    $.ajax({
        url: url,
        type: type,
        data: data,
        dataType: DATA_TYPE,
        success: successCallBack,
        error: function(error) {
            console.log('error:', error);
        }
    });
}

function ajaxUpload(url, type, data, successCallBack, errorCallBack) {
    $.ajax({
        url: url,
        type: type,
        data: data,
        contentType: false,
        processData: false,
        dataType: DATA_TYPE,
        success: successCallBack,
        error: function(error) {
            showMessage(error);
        }
    });
}

function ajaxDownload(_file, _name) {
    if(typeof _name == 'undefined' || _name == null){
        _name = _file;
    }

    let x = new XMLHttpRequest();
    x.open("GET", contextRoot+"downloadFiles/" + _file, true);
    x.responseType = 'blob';
    x.onload=function(e){download(x.response, _name, "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet" ); };
    x.send();
}


function getLocalStorage(key) {
    return localStorage.getItem(key);
}

function addLocalStorage(key, value) {
    localStorage.setItem(key, value);
}

function removeLocalStorage(key) {
    localStorage.removeItem(key);
}

$.ajaxSetup({
    headers: {
        'Authorization': 'Bearer ' + getLocalStorage(MTG_CONSTANTS.MTG_USER_TOKEN)
    },

    beforeSend: function() {
        $('#ajaxLoader').fadeIn(300);
    },
    complete: function() {
        $('#ajaxLoader').fadeOut(500);
    },
    success: function() {
        $('#ajaxLoader').fadeOut(500);
    }
});

$(function () {
    $('#ajaxLoader').fadeOut(200);
});


function setCookie(cname, cvalue, exdays) {
    document.cookie = cname + "=" + cvalue;
}

function getCookie(cname) {
    let name = cname + "=";
    let ca = document.cookie.split(';');
    for(let i = 0; i < ca.length; i++) {
        let c = ca[i];
        while (c.charAt(0) == ' ') {
            c = c.substring(1);
        }
        if (c.indexOf(name) == 0) {
            return c.substring(name.length, c.length);
        }
    }
    return "";
}