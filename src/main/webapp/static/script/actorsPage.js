/**
 * Created by Attila on 2015-05-03.
 */

// this function is called when the user
// wants to know more about an actor
function loadActorDetails(event) {
    console.log(event.target.name);
    var url = event.target.name;
    // http://localhost:8090/rest/movieTime/actor/byId/{id}
    var startIndex = url.indexOf('/rest');
    url = url.substring(startIndex);
    url = url + '?access_token=' + Cookies.get('accessToken');
    console.log(url);

    $('#movies_div').hide('fast');
    $('#loadingDiv').show();
    $.ajax({
        url: url,
        success: function(data) {
            $('#actor_name_h2').text(data.actor.name);
            $('#actor_bio').hide();
            if (data.biography) {
                $('#actor_bio').text(data.biography.bioText);
            }

            var table = document.getElementById('actors_movies_table');

            $(table).empty().append(
                "<tr> " +
                "<th>Title</th> " +
                "<th>Role</th> " +
                "<th>Link</th> " +
                "</tr>");


            var movies = Object.keys(data.roles);
            for (var i = 0; i < movies.length; i++) {
                // extracting the title and movieId
                var movie = movies[i];
                var idx = movie.indexOf('Title=');
                var titleAndId = movie.substring(idx + 7);
                var movIdIdx = titleAndId.indexOf('movieId=');
                var title = titleAndId;
                title = title.substring(0, movIdIdx);
                var movId = titleAndId.substring(movIdIdx + 9);

                // here I have the title and the movieId
                var row = table.insertRow(-1);
                var cell_movtitle = row.insertCell(-1);
                var cell_role = row.insertCell(-1);
                var cell_btn = row.insertCell(-1);

                $(cell_movtitle).text(title);
                $(cell_role).text(data.roles[movie]);

                var detailsBtnName = '/rest/movieTime/movie/byId/' + movId;
                var detailsBtn = document.createElement('input');
                detailsBtn.setAttribute('type', 'button');
                detailsBtn.setAttribute('class', 'movie_details_button');
                detailsBtn.setAttribute('value', 'Show details');
                detailsBtn.setAttribute('name', detailsBtnName);
                $(detailsBtn).click(loadMovieDetails);
                cell_btn.appendChild(detailsBtn);
            }


            $('#loadingDiv').hide();
            tbActBtnClicked();
            $('#actors_div_details_hideable').show();
            $('#actors_div_details').show('fast');
            console.log(data);
        },

        error: function(data) {
            $('#loadingDiv').hide();
            ajaxFailedHandler(data, loadActorDetails, logOut, event);
        }
    });
}

// function show/hide actor's bio
function actorBioBtnClicked() {
    $('#actor_bio').toggle('fast');
}


function searchActorsBtnClicked() {
    $('#actors_act_page_l').text('1');
    $('#actors_div_details_hideable').hide('fast');
    searchActors();
}

// searches for actors
function searchActors() {
    //actors_actor_firstName
    //actors_actor_lastName
    var firstName = $('#actors_actor_firstName').val();
    var lastName = $('#actors_actor_lastName').val();

    if(firstName || lastName) {
        if(!firstName) {
            firstName = "_";
        }
        if(!lastName) {
            lastName = "_";
        }
        var pageLabel = $('#actors_act_page_l');
        var page = parseInt(pageLabel.text());
        var url = '/rest/movieTime/actor/byName/' + firstName + '/' + lastName + '/' + page + '/' + 30;
        url = url + '?access_token=' + Cookies.get('accessToken');
        var $loading = $('#loadingDiv');
        //$loading.show();
        $.ajax({
            url: url,
            success: function(data) {
                loadActorsActorTable(data);
                //$loading.hide();
            },
            error: function(data) {
                //$loading.hide();
                ajaxFailedHandler(data, searchActors, logOut, undefined);
            }
        });

    } else if (!firstName && !lastName) {
        $('#actors_actor_t').empty();
        $('#actors_next_act_btn').prop('disabled', true);
        $('#actors_prev_act_btn').prop('disabled', true);
    }
}


function loadActorsActorTable(data) {
    var table = document.getElementById('actors_actor_t');
    $(table).empty();
    var actors = data.actors;

    if (actors.length != 0) {
        for(var i = 0; i < actors.length; i++) {
            var actor = actors[i];
            var row = table.insertRow(-1);
            var cell_actorname = row.insertCell(-1);
            var cell_actorSex = row.insertCell(-1);
            var cell_actorId = row.insertCell(-1);
            var cell_btn = row.insertCell(-1);

            $(cell_actorname).text(actor['name']);
            $(cell_actorSex).text(actor.sex);
            $(cell_actorId).text(actor.actorId);

            var detailsBtn = document.createElement('input');
            detailsBtn.setAttribute('type', 'button');
            //detailsBtn.setAttribute('class', 'btn btn-default');
            detailsBtn.setAttribute('value', 'Show details');
            detailsBtn.setAttribute('name', actor.links[0].href);
            $(detailsBtn).click(loadActorDetails);
            cell_btn.appendChild(detailsBtn);
        }

        var nextBtn = $('#actors_next_act_btn');
        var prevBtn = $('#actors_prev_act_btn');
        var pageNumLabel = $('#actors_act_page_l');
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



function actorsPrevActors() {
    var pageLabel = $('#actors_act_page_l');
    var pNum = parseInt(pageLabel.text());
    if(pNum > 1)
        pNum = pNum - 1;
    console.log(pNum);
    pageLabel.text(pNum.toString());

    searchActors();
}


function actorsNextActors() {
    var pageLabel = $('#actors_act_page_l');
    var pNum = parseInt(pageLabel.text());
    pNum = pNum + 1;
    console.log(pNum);
    pageLabel.text(pNum.toString());

    searchActors();
}

function actorDetailsBtnClicked() {
    $('#actors_div_details_hideable').toggle(400);
}