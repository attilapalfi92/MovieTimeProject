/**
 * Created by Attila on 2015-05-03.
 */
// when the user clicks on movies, that div shows up
// and the others hide
function tbMovBtnClicked() {
    $('#movies_div').show('fast');
    $('#actors_div').hide();
    $('#mustsee_div').hide();
    $('#haveseen_div').hide();
    $('#submitData_div').hide();
    $('body').css('background-color', '#779ECB');
}


// when the user clicks on movies, that div shows up
// and the others hide
function tbActBtnClicked() {
    //$('#actors_div_details').show();
    $('#actors_div').show('fast');
    $('#movies_div').hide();
    $('#mustsee_div').hide();
    $('#haveseen_div').hide();
    $('#submitData_div').hide();
    $('body').css('background-color', '#77DD77');
}


// when the user clicks on movies, that div shows up
// and the others hide
function tbMustBtnClicked() {
    $('#mustsee_div').show('fast');
    $('#movies_div').hide();
    $('#actors_div').hide();
    $('#haveseen_div').hide();
    $('#submitData_div').hide();
    $('body').css('background-color', '#FF6961');
}


// when the user clicks on movies, that div shows up
// and the others hide
function tbHaveBtnClicked() {
    $('#haveseen_div').show('fast');
    $('#movies_div').hide();
    $('#mustsee_div').hide();
    $('#actors_div').hide();
    $('#submitData_div').hide();
    $('body').css('background-color', '#FFB347');
}


// when the user clicks on Upload Movies, that div shows up
// and the others hide
function tbUploadDataBtnClicked(){
    $('#submitData_div').show('fast');
    $('#haveseen_div').hide();
    $('#movies_div').hide();
    $('#mustsee_div').hide();
    $('#actors_div').hide();
    $('body').css('background-color', '#B19CD9');
}