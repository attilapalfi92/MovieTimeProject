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

// this global variable contains the information about
// if the enter is pressed. its needed for paging.
var enterPressed = false;

// when document is ready, set the slider's label
$(document).ready(function(){
    // init pageSize paragraph
    var pSize = $('#pageSize_i').val();
    $('#pSize_label').text(pSize);

    // hide buttons
    $('#next_btn').prop('disabled', true);
    $('#prev_btn').prop('disabled', true);

    // hide movie details table
    $('#movie_details').hide();
    $('#loadingDiv').hide();
    $('#movie_list').hide();
    $('#actors_movies').hide();

    console.log(pSize);
});



// when the user clicks on movies, that div shows up
// and the others hide
function tbMovBtnClicked() {
    $('#movies_div').show('slow');
    $('#actors_div').hide('slow');
    $('#mustsee_div').hide('slow');
    $('#haveseen_div').hide('slow');
}


// when the user clicks on movies, that div shows up
// and the others hide
function tbActBtnClicked() {
    $('#movies_div').hide('slow');
    $('#actors_div').show('slow');
    $('#mustsee_div').hide('slow');
    $('#haveseen_div').hide('slow');
}


// when the user clicks on movies, that div shows up
// and the others hide
function tbMustBtnClicked() {
    $('#movies_div').hide('slow');
    $('#actors_div').show('slow');
    $('#mustsee_div').hide('slow');
    $('#haveseen_div').hide('slow');
}


// when the user clicks on movies, that div shows up
// and the others hide
function tbHaveBtnClicked() {
    $('#movies_div').hide('slow');
    $('#actors_div').show('slow');
    $('#mustsee_div').hide('slow');
    $('#haveseen_div').hide('slow');
}


// if the slider changes, we update the slider
function sliderChanged(value) {
    $('#pSize_label').text(value);
    $('#pageNum_label').text('1');

    titleChanged($('#mTitle_input').val());
};

// event handler for the click on next button
function nextClicked() {
    var pageNumLabel = $('#pageNum_label');
    var pNum = parseInt(pageNumLabel.text());
    pNum = pNum + 1;
    console.log(pNum);
    pageNumLabel.text(pNum.toString());

    initMoviesTable();
    if(!enterPressed) {
        searchMoviesFast($('#mTitle_input').val());
    } else {
        searchMoviesMore($('#mTitle_input').val());
    }
}

// event handler for the click on previous button
function prevClicked() {
    var pageNumLabel = $('#pageNum_label');
    var pNum = parseInt(pageNumLabel.text());
    if(pNum > 1)
        pNum = pNum - 1;
    console.log(pNum);
    pageNumLabel.text(pNum.toString());

    initMoviesTable();
    if(!enterPressed) {
        searchMoviesFast($('#mTitle_input').val());
    } else {
        searchMoviesMore($('#mTitle_input').val());
    }
}


// this function is called when the user
// wants to know more about an actor
function loadActorDetails(event) {
    console.log(event.target.name);
    var url = event.target.name;
    // http://localhost:8090/rest/actor/byId/{id}
    var startIndex = url.indexOf('/rest');
    url = url.substring(startIndex);
    console.log(url);

    $.ajax({
        url: url
    }).then(function(data) {
        console.log(data);
    });
}

// this function fills the table containing the actors
// when the user wants to know more about a movie
function loadActorsTable(actors) {
    var table = document.getElementById('actors_table');

    $(table).empty().append(
        "<tr> " +
        "<th>Name</th> " +
        "<th>Role</th> " +
        "<th>Sex</th> " +
        "<th>actorId</th> " +
        "<th>link</th> " +
        "</tr>");

    for(var i = 0; i < actors.length; i++) {
        var actor = actors[i];
        var row = table.insertRow(-1);
        var cell_actorname = row.insertCell(-1);
        var cell_role = row.insertCell(-1);
        var cell_sex = row.insertCell(-1);
        var cell_actorid = row.insertCell(-1);
        var cell_btn = row.insertCell(-1);

        $(cell_actorname).text(actor['name']);
        $(cell_role).text(actor.role);
        $(cell_sex).text(actor.sex);
        $(cell_actorid).text(actor.actorid);

        var detailsBtn = document.createElement('input');
        detailsBtn.setAttribute('type', 'button');
        detailsBtn.setAttribute('class', 'actor_details_button');
        detailsBtn.setAttribute('value', 'Show details');
        detailsBtn.setAttribute('name', actor.links[0].href);
        $(detailsBtn).click(loadActorDetails);
        cell_btn.appendChild(detailsBtn);
    }

}

// this function fills the div containing
// every other data about the movie
function loadMovieDetailsDiv(movie) {
    //<h2 id="movie_title_h2"></h2>
    //<div>
    //<h3>Details</h3>
    //<h4 id="directors_h">Directors: </h4>      <p id="directors_p"></p>
    //<h4 id="writers_h">Writers: </h4>          <p id="writers_p"></p>
    //<h4 id="producers_h">Producers: </h4>      <p id="producers_p"></p>
    //<h4 id="editors_h">Editors: </h4>          <p id="editors_p"></p>
    //<h4 id="genres_h">Genres: </h4>            <p id="genres_p"></p>
    //<h4 id="languages_h">Languages: </h4>      <p id="languages_p"></p>
    //<h4 id="locations_h">Locations: </h4>      <p id="locations_p"></p>
    //</div>

    $('#movie_title_h2').text(movie.movie.title);

    var dirsP = $('#directors_p');
    for (var i = 0; i < movie.directors.length; i++) {
        dirsP.append(movie.directors[i]['name'] + ';   ');
    }

    var writsP = $('#writers_p');
    for (var i = 0; i < movie.writers.length; i++) {
        writsP.append(movie.writers[i]['name'] + ';   ');
    }

    var prodsP = $('#producers_p');
    for (var i = 0; i < movie.producers.length; i++) {
        prodsP.append(movie.producers[i]['name'] + ';   ');
    }

    var editsP = $('#editors_p');
    for (var i = 0; i < movie.editors.length; i++) {
        editsP.append(movie.editors[i]['name'] + ';   ');
    }

    var genresP = $('#genres_p');
    for (var i = 0; i < movie.genres.length; i++) {
        genresP.append(movie.genres[i]['genre'] + ';   ');
    }

    var langP = $('#languages_p');
    for (var i = 0; i < movie.languages.length; i++) {
        langP.append(movie.languages[i]['language'] + ';   ');
    }

    var locP = $('#locations_p');
    for (var i = 0; i < movie.locations.length; i++) {
        if(!movie.locations[i].addition) {
            locP.append('<b>' + movie.locations[i].location + '</b>' + ';   ');
        } else {
            locP.append('<b>' + movie.locations[i].location + '</b> <i>' + movie.locations[i].addition + '</i>;   ');
        }
    }
}

// if user clicks on the movie details button
// this function shows every information about
// the movie
function loadMovieDetails(event) {
    console.log('button pressed');

    var url = event.target.name;
    // http://localhost:8090/rest/movie/byId/2122594
    var startIndex = url.indexOf('/rest');
    url = url.substring(startIndex);
    console.log(url);

    var $loading = $('#loadingDiv');
    $loading.show();
    $.ajax({
        url: url
    }).then(function(data) {
        $loading.hide();
        console.log(data);

        // this function fills the table containing the actors
        // #actors_table
        loadActorsTable(data.actors);

        // this function fills the div containing
        // every other data about the movie
        loadMovieDetailsDiv(data);

        $('#movie_details').show();
    });
}

// this function is called when the user presses enter
// in the search text of moves section
$('#mTitle_input').keypress(function (e){
   if(e.which == 13 || e.keyCode == 13) {
       enterPressed = true;
       $('#pageNum_label').text('1');

       var mTitle = $('#mTitle_input').text();
       console.log(mTitle);
   }

});

// this function initializes the movies table
// in the movies section
function initMoviesTable() {
    $('#movie_details').hide();

    $('#moviesTable').empty().append(
        "<tr> " +
        "<th>Title</th> " +
        "<th>Tagline</th> " +
        "<th>movieId</th> " +
        "<th>link</th> " +
        "</tr>");
}

// when the user presses enter, ot taps on the button
// this function is called in order to search for movies
// with more results
function titleEnterPressed() {
    enterPressed = true;
    $('#pageNum_label').text('1');
    var value = $('#mTitle_input').val();

    // if the input is not empty
    if(value) {
        initMoviesTable();
        searchMoviesMore(value);
        $('#movie_list').show();

    } else {
        $('#movie_list').hide();
    }
}

// event handler for the title input
function titleChanged(value) {
    enterPressed = false;
    $('#pageNum_label').text('1');

    // if the input is not empty
    if(value) {
        initMoviesTable();
        searchMoviesFast(value);
        $('#movie_list').show();

    } else {
        $('#movie_list').hide();
    }
}

// this function performs a fast search
// in movies. its called after each keypress
function searchMoviesFast(mTitle) {
    // creating the url: title
    var url = "/rest/movie/byTitle/" + mTitle + '/';
    // creating the url: page number
    var pNum = $('#pageNum_label').text();
    url = url + pNum + '/';
    // creating the url: page size
    var pSize = $('#pageSize_i').val();
    url = url + pSize;

    console.log(url);

    $.ajax({
        url: url
    }).then(function(data){
        updateMovieListTable(data)
    });
}

// this function searches for movies with
// much more results.
function searchMoviesMore(mTitle) {
    // creating the url: title
    var url = "/rest/movie/byPartTitle/" + mTitle + '/';
    // creating the url: page number
    var pNum = $('#pageNum_label').text();
    url = url + pNum + '/';
    // creating the url: page size
    var pSize = $('#pageSize_i').val();
    url = url + pSize;

    console.log(url);
    var $loading = $('#loadingDiv');
    $loading.show();
    $.ajax({
        url: url
    }).then(function(data){
        $loading.hide();
        updateMovieListTable(data)
    });
}


// if the input text changes, update the table
function updateMovieListTable(data) {
    var nextBtn = $('#next_btn');
    var prevBtn = $('#prev_btn');
    var pageNumLabel = $('#pageNum_label');
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


    // uploading the table with data
    var table = document.getElementById('moviesTable');
    var movies = data.movies;
    for(var i = 0; i < movies.length; i++) {
        var link1 = movies[i].links[0].href;

        var movie = movies[i];
        var row = table.insertRow(-1);
        var cell_title = row.insertCell(-1);
        var cell_tagline = row.insertCell(-1);
        var cell_movieid = row.insertCell(-1);
        var cell_btn = row.insertCell(-1);

        $(cell_title).text(movie.title);
        if (movies[i].tagline) {
            $(cell_tagline).text(movie.tagline.taglinetext);

        } else {
            $(cell_tagline).text('');
        }
        $(cell_movieid).text(movie.movieid);

        var detailsBtn = document.createElement('input');
        detailsBtn.setAttribute('type', 'button');
        detailsBtn.setAttribute('class', 'movie_details_button');
        detailsBtn.setAttribute('value', 'Show details');
        detailsBtn.setAttribute('name', link1);
        $(detailsBtn).click(loadMovieDetails);
        cell_btn.appendChild(detailsBtn);

    }

};