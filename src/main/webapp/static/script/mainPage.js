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

    // hide buttons
    $('#next_btn').prop('disabled', true);
    $('#prev_btn').prop('disabled', true);
    $('#submit_mov_prev_act_btn').prop('disabled', true);
    $('#submit_mov_next_act_btn').prop('disabled', true);

    // hide movie details table
    $('#movie_details_div').hide();
    $('#loadingDiv').hide();
    $('#movie_list_div').hide();
    $('#actors_div').hide();
    $('#submitData_div').hide();
    $('#actors_div_hideable').hide();
    $('#submitActor_div').hide();
    $('#submitOther_div').hide();

    Cookies.get()

    console.log(pSize);
    getRefreshToken('Oppenheimer', '111111', refreshTokenHandler());

});

function refreshTokenHandler(refreshToken) {
    console.log(refreshToken);
}

function accessTokenHandler(accessToken) {
    console.log(accessToken);
}






