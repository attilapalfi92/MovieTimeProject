/**
 * Created by Attila on 2015-05-06.
 */

function getRefreshToken(userName, pwd, refreshTokenHandler) {
    var pwdGrantUrl = '/oauth/token?client_id=MovieTime&client_secret=MovieTimeSecretKey&grant_type=password&username=' +
        userName +
        '&password=' +
        pwd;

    $.ajax({
        url: pwdGrantUrl,
        success: function(response) {
            refreshTokenHandler(response.refresh_token);
        }
    })

}

function getAccessToken(refreshToken, accessTokenHandler) {
    var refreshGrantUrl = '/oauth/token?client_id=MovieTime&client_secret=MovieTimeSecretKey&grant_type=refresh_token&refresh_token=' +
        refreshToken;

    $.ajax({
        url: refreshGrantUrl,
        success: function(response) {
            accessTokenHandler(response.access_token);
        }
    });
}