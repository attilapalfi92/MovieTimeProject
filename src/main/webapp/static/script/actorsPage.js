/**
 * Created by Attila on 2015-05-03.
 */

// this function is called when the user
// wants to know more about an actor
function loadActorDetails(event) {
    console.log(event.target.name);
    var url = event.target.name;
    // http://localhost:8090/rest/actor/byId/{id}
    var startIndex = url.indexOf('/rest');
    url = url.substring(startIndex);
    console.log(url);

    $('#movies_div').hide('fast');
    $('#loadingDiv').show('fast');
    $.ajax({
        url: url
    }).then(function(data) {

        $('#actor_name_h2').text(data.actor.name);
        $('#actor_bio').hide();
        if (data.biography) {
            $('#actor_bio').text(data.biography.biotext);
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

            var detailsBtnName = '/rest/movie/byId/' + movId;
            var detailsBtn = document.createElement('input');
            detailsBtn.setAttribute('type', 'button');
            detailsBtn.setAttribute('class', 'movie_details_button');
            detailsBtn.setAttribute('value', 'Show details');
            detailsBtn.setAttribute('name', detailsBtnName);
            $(detailsBtn).click(loadMovieDetails);
            cell_btn.appendChild(detailsBtn);
        }


        $('#loadingDiv').hide();
        $('#mustsee_div').hide();
        $('#haveseen_div').hide();
        $('#actors_div_hideable').show();
        $('#actors_div').show('fast');
        console.log(data);
    });
}

// function show/hide actor's bio
function actorBioBtnClicked() {
    $('#actor_bio').toggle('fast');
}

// searches for actors
function searchActors() {

}