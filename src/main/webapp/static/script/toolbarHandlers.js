/**
 * Created by Attila on 2015-05-03.
 */
// when the user clicks on movies, that div shows up
// and the others hide
function tbMovBtnClicked() {

    $('#actors_div').hide('fast');
    $('#mustsee_div').hide('fast');
    $('#haveseen_div').hide('fast');
    $('#submitData_div').hide('fast');
    $('#movies_div').show('fast');
    $('body').css('background-color', '#779ECB');
}


// when the user clicks on movies, that div shows up
// and the others hide
function tbActBtnClicked() {
    //$('#actors_div_details').show();

    $('#movies_div').hide('fast');
    $('#mustsee_div').hide('fast');
    $('#haveseen_div').hide('fast');
    $('#submitData_div').hide('fast');
    $('#actors_div').show('fast');
    $('body').css('background-color', '#77DD77');
}


// when the user clicks on movies, that div shows up
// and the others hide
function tbMustBtnClicked() {

    $('#movies_div').hide('fast');
    $('#actors_div').hide('fast');
    $('#haveseen_div').hide('fast');
    $('#submitData_div').hide('fast');
    $('#mustsee_div').show('fast');
    $('body').css('background-color', '#BB7971');
}


// when the user clicks on movies, that div shows up
// and the others hide
function tbHaveBtnClicked() {

    $('#movies_div').hide('fast');
    $('#mustsee_div').hide('fast');
    $('#actors_div').hide('fast');
    $('#submitData_div').hide('fast');
    $('#haveseen_div').show('fast');
    $('body').css('background-color', '#FFB347');
}


// when the user clicks on Upload Movies, that div shows up
// and the others hide
function tbUploadDataBtnClicked(){

    $('#haveseen_div').hide('fast');
    $('#movies_div').hide('fast');
    $('#mustsee_div').hide('fast');
    $('#actors_div').hide('fast');
    $('#submitData_div').show('fast');
    $('body').css('background-color', '#B19CD9');
}