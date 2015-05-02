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
    $('#movie_details_div').hide();
    $('#loadingDiv').hide();
    $('#movie_list_div').hide();
    $('#actors_div').hide();

    console.log(pSize);
});



// when the user clicks on movies, that div shows up
// and the others hide
function tbMovBtnClicked() {
    $('#movies_div').show('fast');
    $('#actors_div').hide('fast');
    $('#mustsee_div').hide('fast');
    $('#haveseen_div').hide('fast');
    $('#submitData_div').hide('fast');
}


// when the user clicks on movies, that div shows up
// and the others hide
function tbActBtnClicked() {
    $('#actors_div').show('fast');
    $('#movies_div').hide('fast');
    $('#mustsee_div').hide('fast');
    $('#haveseen_div').hide('fast');
    $('#submitData_div').hide('fast');
}


// when the user clicks on movies, that div shows up
// and the others hide
function tbMustBtnClicked() {
    $('#mustsee_div').show('fast');
    $('#movies_div').hide('fast');
    $('#actors_div').hide('fast');
    $('#haveseen_div').hide('fast');
    $('#submitData_div').hide('fast');
}


// when the user clicks on movies, that div shows up
// and the others hide
function tbHaveBtnClicked() {
    $('#haveseen_div').show('fast');
    $('#movies_div').hide('fast');
    $('#mustsee_div').hide('fast');
    $('#actors_div').hide('fast');
    $('#submitData_div').hide('fast');
}


// when the user clicks on Upload Movies, that div shows up
// and the others hide
function tbUploadMovBtnClicked(){
    $('#submitData_div').show('fast');
    $('#haveseen_div').hide('fast');
    $('#movies_div').hide('fast');
    $('#mustsee_div').hide('fast');
    $('#actors_div').hide('fast');
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
        $('#movie_list_div').hide(50);
        searchMoviesFast($('#mTitle_input').val());
        $('#movie_list_div').show(50);
    } else {
        $('#movie_list_div').hide(300);
        searchMoviesMore($('#mTitle_input').val());
        $('#movie_list_div').show(300);
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
        $('#movie_list_div').hide(50);
        searchMoviesFast($('#mTitle_input').val());
        $('#movie_list_div').show(50);
    } else {
        $('#movie_list_div').hide(300);
        searchMoviesMore($('#mTitle_input').val());
        $('#movie_list_div').show(300);
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

    $('#movies_div').hide('fast');
    $('#loadingDiv').show('fast');
    $.ajax({
        url: url
    }).then(function(data) {
        //<div id="actors_div">
            //<div id="actors_movies">
            //<h2 id="actor_name_h2"></h2>
            //<p id="actor_bio"></p>
                //<table id="actors_movies_table">
                    //<tr>
                        //<th>Title</th>
                        //<th>Role</th>
                        //<th>link</th>
                    //</tr>
                //</table>
            //</div>
        //</div>

        $('#actor_name_h2').text(data.actor.name);
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


        $('#loadingDiv').hide('fast');
        $('#mustsee_div').hide('fast');
        $('#haveseen_div').hide('fast');
        $('#actors_div').show('fast');
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

    $('#movie_title_h2').empty().text(movie.movie.title);

    var dirsP = $('#directors_p');
    dirsP.empty();
    for (var i = 0; i < movie.directors.length; i++) {
        dirsP.append(movie.directors[i]['name'] + ';   ');
    }

    var writsP = $('#writers_p');
    writsP.empty();
    for (var i = 0; i < movie.writers.length; i++) {
        writsP.append(movie.writers[i]['name'] + ';   ');
    }

    var prodsP = $('#producers_p');
    prodsP.empty();
    for (var i = 0; i < movie.producers.length; i++) {
        prodsP.append(movie.producers[i]['name'] + ';   ');
    }

    var editsP = $('#editors_p');
    editsP.empty();
    for (var i = 0; i < movie.editors.length; i++) {
        editsP.append(movie.editors[i]['name'] + ';   ');
    }

    var genresP = $('#genres_p');
    genresP.empty();
    for (var i = 0; i < movie.genres.length; i++) {
        genresP.append(movie.genres[i]['genre'] + ';   ');
    }

    var langP = $('#languages_p');
    langP.empty();
    for (var i = 0; i < movie.languages.length; i++) {
        langP.append(movie.languages[i]['language'] + ';   ');
    }

    var locP = $('#locations_p');
    locP.empty();
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

    $('#mustsee_div').hide('fast');
    $('#haveseen_div').hide('fast');
    $('#actors_div').hide('fast');

    var url = event.target.name;
    // http://localhost:8090/rest/movie/byId/2122594
    var startIndex = url.indexOf('/rest');
    url = url.substring(startIndex);
    console.log(url);

    var $loading = $('#loadingDiv');
    $loading.show('fast');
    $.ajax({
        url: url
    }).then(function(data) {
        $loading.hide('fast');
        console.log(data);

        // this function fills the table containing the actors
        // #actors_table
        loadActorsTable(data.actors);

        // this function fills the div containing
        // every other data about the movie
        loadMovieDetailsDiv(data);

        // creating the "Show Plot" button
        startIndex = url.indexOf('/byId/');
        var movId = url.substring(startIndex + 6);
        var plotBtn = document.createElement('input');
        plotBtn.setAttribute('type', 'button');
        plotBtn.setAttribute('class', 'movie_toggle_plot_btn');
        plotBtn.setAttribute('value', 'Plot');
        plotBtn.setAttribute('name', movId);
        $(plotBtn).click(movieShowPlotBtnClicked);
        $('#movie_plot_btn_div').empty();
        $('#movie_plot_btn_div').append(plotBtn);
        firstPlotClick = true;

        $('#movies_div').show('fast');
        $('#movie_details_hideable').show();
        $('#movie_plot_p').hide();
        $('#movie_details_div').show('fast');

    });
}

// this function called when the user wants to read the plot of the movie.
// so the user clicks on the "Plot" button.
var firstPlotClick = true;
function movieShowPlotBtnClicked(event) {
    var plotPar = $('#movie_plot_p');
    if (firstPlotClick) {
        plotPar.empty();
        var movieId = event.target.name;
        var url = '/rest/plot/byMovieId/' + movieId;
        plotPar.hide();
        $.ajax({
            url: url
        }).then(function(data){
            if(data) {
                plotPar.text(data.plottext);
                firstPlotClick = false;
                plotPar.show(500);
            }
        });

    } else {
        plotPar.toggle(500);
    }
}


// this is the event handler of the "Details" button
function toggleMovieDetails() {
    $('#movie_details_hideable').toggle(400);
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
    $('#movie_details_div').hide('fast');

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

    $('#movie_list_div').hide(50);
    // if the input is not empty
    if(value) {
        initMoviesTable();
        searchMoviesMore(value);
        $('#movie_list_div').show(50);

    } else {
        $('#movie_list_div').hide(50);
    }
}

// event handler for the title input
function titleChanged(value) {
    enterPressed = false;
    $('#pageNum_label').text('1');

    $('#movie_list_div').hide(50);
    // if the input is not empty
    if(value) {
        initMoviesTable();
        searchMoviesFast(value);
        $('#movie_list_div').show(50);

    } else {
        $('#movie_list_div').hide(50);
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
        console.log(data);
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
    $loading.show('fast');
    $.ajax({
        url: url
    }).then(function(data){
        $loading.hide('fast');
        updateMovieListTable(data)
    });
}


// if the input text changes, update the table of movies
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