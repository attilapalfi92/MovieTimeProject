/**
 * Created by Attila on 2015-04-30.
 */

// when ajax is loading
$(document)
    .ajaxStart(function () {
        //var $loading = $('#loadingDiv').hide();
        //$loading.show();
    })
    .ajaxStop(function () {
        //var $loading = $('#loadingDiv').hide();
        //$loading.hide();
    });


// when document is ready, set the slider's label
$(document).ready(function(){
    // init pageSize paragraph
    var pSize = $('#pageSize_i').val();
    $('#pSize_label').text(pSize);

    // disable buttons
    $('#next_btn').prop('disabled', true);
    $('#prev_btn').prop('disabled', true);
    $('#submit_mov_prev_act_btn').prop('disabled', true);
    $('#submit_mov_next_act_btn').prop('disabled', true);
    $('#actors_prev_act_btn').prop('disabled', true);
    $('#actors_next_act_btn').prop('disabled', true);

    // hide movie details table
    $('#movie_details_div').hide();
    $('#loadingDiv').hide();
    $('#movie_list_div').hide();
    $('#actors_div').hide();
    $('#submitData_div').hide();
    $('#actors_div_details').hide();
    $('#submitActor_div').hide();
    $('#submitOther_div').hide();

    // hide the protected div by default
    $('#protectedDiv').hide();
    // hide loginDiv by default too, and show when it's needed
    $('#loginDiv').hide();

    userName = Cookies.get('userName');
    // if userName is undefined, user has to log in
    if (typeof userName === 'undefined') {
        $('#loginDiv').show('fast');

    } else { // else check his/her tokens
        password = Cookies.get('password');
        var accessToken = Cookies.get('accessToken');
        var refreshToken = Cookies.get('refreshToken');

        // if accessToken is undefined, we have to get one and save it to cookies
        if (typeof accessToken === 'undefined') {

            // if refreshToken is undefined too, the user is logged out
            // and she/he has to log in again
            if (typeof refreshToken === 'undefined') {
                $('#loginDiv').show('fast');
                return;
            }

            // else we can get access token and the user is logged in
            getAccessToken(refreshToken.refresh_token, saveAccessToken, logOut);
            logIn();
        }

        // else the user is logged in
        logIn();
    }
});


function logOut(reason) {
    $('#protectedDiv').hide('fast');
    $('#loginDiv').show('fast');
    Cookies.remove('userName');
    Cookies.remove('password');
    Cookies.remove('accessToken');
    Cookies.remove('refreshToken');
}

function logIn() {
    $('#welcomeHeader').text('Welcome ' + userName + '!');
    $('#protectedDiv').show('fast');
    $('#loginDiv').hide('fast');
}


function loginClicked() {
    userName = $('#username_i').val();
    password = $('#password_i').val();
    getRefreshToken(userName, password, userLoggedIn, logOut);
    Cookies.set('userName', userName, { expires: 31 });
    Cookies.set('password', password, { expires: 31 });
}


var userName;
var password;


function userLoggedIn(refreshToken) {
    Cookies.set('refreshToken', refreshToken.refresh_token, { expires: 30 });

    var tokenLife = refreshToken.expires_in / 60 / 60 / 24;
    Cookies.set('accessToken', refreshToken.access_token, { expires: tokenLife });

    console.log('Token: ');
    console.log(refreshToken);

    logIn();
}


function saveAccessToken(accessToken) {
    var tokenLife = accessToken.expires_in / 60 / 60 / 24;
    Cookies.set('accessToken', accessToken.access_token, { expires: tokenLife });
    console.log('accessToken: ');
    console.log(accessToken);
}