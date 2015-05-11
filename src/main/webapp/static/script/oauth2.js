/**
 * Created by Attila on 2015-05-06.
 */

function getRefreshToken(userName, pwd, refreshTokenHandler, errorHandler) {
    var pwdGrantUrl = '/oauth/token?client_id=MovieTime&client_secret=' +
        'MovieTimeSecretKey&grant_type=password&username=' +
        userName +
        '&password=' +
        pwd;

    $.ajax({
        url: pwdGrantUrl,
        type: 'POST',
        contentType: 'application/json; charset=utf-8',
        dataType: 'json',
        success: function(response) {
            refreshTokenHandler(response);
        },
        error: function(response) {
            console.log('Error happened during getRefreshToken');
            console.log(response);
            errorHandler('refresh');
        }
    })

}

function getAccessToken(refreshToken, accessTokenHandler, errorHandler) {
    var refreshGrantUrl = '/oauth/token?client_id=MovieTime&client_secret=' +
        'MovieTimeSecretKey&grant_type=refresh_token&refresh_token=' +
        refreshToken;

    $.ajax({
        type: 'POST',
        contentType: 'application/json; charset=utf-8',
        dataType: 'json',
        url: refreshGrantUrl,
        success: function(response) {
            accessTokenHandler(response);
        },
        error: function(response) {
            console.log('Error happened during getAccessToken');
            console.log(response);
            errorHandler('access');
        }
    });
}