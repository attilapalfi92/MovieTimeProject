/**
 * Created by Attila on 2015-05-02.
 */

function submitMovBtnClicked() {
    $('#submitMovie_div').show('fast');
    $('#submitActor_div').hide();
    $('#submitOther_div').hide();
}

function submitActBtnClicked() {
    $('#submitActor_div').show('fast');
    $('#submitMovie_div').hide();
    $('#submitOther_div').hide();
}

function submitOtherBtnClicked() {
    $('#submitOther_div').show('fast');
    $('#submitActor_div').hide();
    $('#submitMovie_div').hide();
}

function submitMovActSrchBtnClicked() {
    $('#submit_mov_act_page_l').text('1');
    submitMovActorsSearch();
}


function submitMovActorsSearch() {
    var firstName = $('#submit_mov_actor_firstName').val();
    var lastName = $('#submit_mov_actor_lastName').val();

    if(firstName || lastName) {
        if(!firstName) {
            firstName = "_";
        }
        if(!lastName) {
            lastName = "_";
        }
        var pageLabel = $('#submit_mov_act_page_l');
        var page = parseInt(pageLabel.text());
        var url = '/rest/movieTime/actor/byName/' + firstName + '/' + lastName + '/' + page + '/' + 30;
        url = url + '?access_token=' + Cookies.get('accessToken');
        var $loading = $('#loadingDiv');
        //$loading.show();
        $.ajax({
            url: url,
            success: function(data) {
                loadSubmitMovActorTable(data);
                //$loading.hide();
            },
            error: function(data) {
                //$loading.hide();
                ajaxFailedHandler(data, submitMovActorsSearch, logOut, undefined);
            }
        });

    } else if (!firstName && !lastName) {
        $('#submit_mov_actor_t').empty();
        $('#submit_mov_next_act_btn').prop('disabled', true);
        $('#submit_mov_prev_act_btn').prop('disabled', true);
    }
}



function loadSubmitMovActorTable(data) {
    var table = document.getElementById('submit_mov_actor_t');
    $(table).empty();
    var actors = data.actors;

    if (actors.length != 0) {
        for(var i = 0; i < actors.length; i++) {
            var actor = actors[i];
            var row = table.insertRow(-1);
            var cell_actorname = row.insertCell(-1);
            var cell_actorId = row.insertCell(-1);
            var cell_btn = row.insertCell(-1);

            $(cell_actorname).text(actor['name']);
            $(cell_actorId).text(actor.actorId);

            var addBtn = document.createElement('input');
            addBtn.setAttribute('type', 'button');
            //addBtn.setAttribute('class', 'btn btn-default');
            addBtn.setAttribute('value', 'Add');
            var btnName = 'actorId= ' + actor.actorId + '; name= ' + actor.name;
            addBtn.setAttribute('name', btnName);
            $(addBtn).click(addActorToMovie);
            cell_btn.appendChild(addBtn);
        }

        var nextBtn = $('#submit_mov_next_act_btn');
        var prevBtn = $('#submit_mov_prev_act_btn');
        var pageNumLabel = $('#submit_mov_act_page_l');
        nextBtn.prop('disabled', true);
        prevBtn.prop('disabled', true);

        // setting the previous and next buttons
        if(data.links.length == 0) {
            nextBtn.prop('disabled', true);
            prevBtn.prop('disabled', true);
            pageNumLabel.text('1');

        } else if(data.links.length == 1) {
            if (data.links[0].rel == "nextPage") {
                nextBtn.prop('disabled', false);
                prevBtn.prop('disabled', true);
                pageNumLabel.text('1');

            } else {
                nextBtn.prop('disabled', true);
                prevBtn.prop('disabled', false);
            }

        } else {
            nextBtn.prop('disabled', false);
            prevBtn.prop('disabled', false);
        }

    } else {
        $(table).empty();
        $('#submit_mov_next_act_btn').prop('disabled', true);
        $('#submit_mov_prev_act_btn').prop('disabled', true);
    }

}



function submitMovPrevActors() {
    var pageLabel = $('#submit_mov_act_page_l');
    var pNum = parseInt(pageLabel.text());
    if(pNum > 1)
        pNum = pNum - 1;
    console.log(pNum);
    pageLabel.text(pNum.toString());

    submitMovActorsSearch();
}


function submitMovNextActors() {
    var pageLabel = $('#submit_mov_act_page_l');
    var pNum = parseInt(pageLabel.text());
    pNum = pNum + 1;
    console.log(pNum);
    pageLabel.text(pNum.toString());

    submitMovActorsSearch();
}



function addActorToMovie(event) {
    var idAndName = event.target.name;
    console.log(idAndName);
    var idIdx = idAndName.indexOf('actorId=') + 9;
    var idEndIdx = idAndName.indexOf(';');
    var actorId = idAndName.substring(idIdx, idEndIdx);
    var actorName = idAndName.substring(idEndIdx + 8);
    console.log(actorId + ' ' + actorName);

    var table = document.getElementById('submit_mov_added_actors_t');
    var row = table.insertRow(-1);
    row.setAttribute('id', actorId);
    var name_cell = row.insertCell(-1);
    var id_cell = row.insertCell(-1);
    var role_cell = row.insertCell(-1);
    var cancel_cell = row.insertCell(-1);


    $(name_cell).text(actorName);
    $(id_cell).text(actorId);

    var roleInput = document.createElement('input');
    roleInput.setAttribute('type', 'text');
    roleInput.setAttribute('class', 'submit_movie_actors_role');
    roleInput.setAttribute('placeholder', 'role');
    role_cell.appendChild(roleInput);

    var cancelBtn = document.createElement('input');
    cancelBtn.setAttribute('type', 'button');
    //cancelBtn.setAttribute('class', 'btn btn-default');
    cancelBtn.setAttribute('value', 'Cancel');
    cancelBtn.setAttribute('name', actorId);
    $(cancelBtn).click(cancelActorFromMovieSubmit);
    cancel_cell.appendChild(cancelBtn);

}


function cancelActorFromMovieSubmit(event) {
    var row = document.getElementById(event.target.name);
    row.parentNode.removeChild(row);
}


function submitMovieClicked() {
    var table = document.getElementById('submit_mov_added_actors_t');
    var rows = table.rows;

    var movie = new Object();
    movie.title = $('#submit_mov_title').val();

    // if contains only whitespaces
    if (!movie.title.replace(/\s/g, '').length) {
        alert("Title cannot be empty.");
        return;
    }

    var roles = new Array();
    for(var i = 1; i < rows.length; i++) {
        var row = table.getElementsByTagName('tr')[i];
        var idCell = row.cells[1];
        var actorId = $(idCell).text();

        var roleCell = row.cells[2];
        var role = $(roleCell.childNodes[0]).val();
        if (role == '') {
            alert('Role cannot be empty.');
            return;
        }
        var role = {
            actorId: actorId,
            asCharacter: role
        };
        roles.push(role);
    }

    movie.roles = roles;

    var release = new Object();
    release.releaseDate = $('#submit_mov_releaseDate').val();
    if (release.releaseDate == '') {
        alert('Release date cannot be empty.');
        return;
    }
    release.country = $('#submit_mov_country').val();
    // if contains only whitespaces
    if (!release.country.replace(/\s/g, '').length) {
        alert("Country cannot be empty.");
        return;
    }
    movie.release = release;

    var genres = $('#submit_mov_genres').val();
    // if contains only whitespaces
    if (!genres.replace(/\s/g, '').length) {
        alert("Genres cannot be empty.");
        return;
    }
    movie.genres = genres.split(';');

    var taglines = $('#submit_mov_taglines').val();
    // if contains only whitespaces
    if (!taglines.replace(/\s/g, '').length) {
        alert("Taglines cannot be empty.");
        return;
    }
    movie.taglines = taglines.split(';');
    console.log(JSON.stringify(movie));
    var url = '/rest/movieTime/movie';
    url = url + '?access_token=' + Cookies.get('accessToken');

    $.ajax({
        type: 'POST',
        url: url,
        data: JSON.stringify(movie),
        contentType: 'application/json; charset=utf-8',
        dataType: 'json',
        success: function(msg) {
            alert('Movie added!');
            clearSubmitInputs();
            console.log(msg);
        },
        error: function(msg) {
            ajaxFailedHandler(msg, submitMovieClicked, logOut, undefined);
        }
    });
}



function clearSubmitInputs() {
    //"submit_mov_title"
    $('#submit_mov_title').val('');
    //"submit_mov_releaseDate"
    $('#submit_mov_releaseDate').val('');
    //"submit_mov_country"
    $('#submit_mov_country').val('');
    //"submit_mov_genres"
    $('#submit_mov_genres').val('');
    //"submit_mov_taglines"
    $('#submit_mov_taglines').val('');
    //"submit_mov_added_actors_t"
    $('#submit_mov_added_actors_t').empty();
    //"submit_mov_act_page_l"
    $('#submit_mov_act_page_l').text('1');
    //"submit_mov_prev_act_btn"
    $('#submit_mov_prev_act_btn').prop('disabled', true);
    //"submit_mov_next_act_btn"
    $('#submit_mov_next_act_btn').prop('disabled', true);
    //"submit_mov_actor_t"
    $('#submit_mov_actor_t').empty();
}