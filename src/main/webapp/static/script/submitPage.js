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
    var firstName = $('#submit_mov_actor_firstName').val();
    var lastName = $('#submit_mov_actor_lastName').val();

    if(firstName && lastName) {
        var pageLabel = $('#submit_mov_act_page_l');
        var page = parseInt(pageLabel.text());
        var url = '/rest/actor/byName/' + firstName + '/' + lastName + '/' + page + '/' + 30;
        var $loading = $('#loadingDiv');
        $loading.show('fast');
        $.ajax({
            url: url
        }).then(function(data){
            loadSubmitMovActorTable(data)>
            $loading.hide('fast');
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
            $(cell_actorId).text(actor.actorid);

            var addBtn = document.createElement('input');
            addBtn.setAttribute('type', 'button');
            addBtn.setAttribute('class', 'add_actor_to_movie_btn');
            addBtn.setAttribute('value', 'Add');
            var btnName = 'actorId= ' + actor.actorid + '; name= ' + actor.name;
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

    submitMovActSrchBtnClicked();
}


function submitMovNextActors() {
    var pageLabel = $('#submit_mov_act_page_l');
    var pNum = parseInt(pageLabel.text());
    pNum = pNum + 1;
    console.log(pNum);
    pageLabel.text(pNum.toString());

    submitMovActSrchBtnClicked();
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
    cancelBtn.setAttribute('class', 'add_actor_to_movie_btn');
    cancelBtn.setAttribute('value', 'Cancel');
    cancelBtn.setAttribute('name', actorId);
    $(cancelBtn).click(cancelActorFromMovieSubmit);
    cancel_cell.appendChild(cancelBtn);

}


function cancelActorFromMovieSubmit(event) {
    var row = document.getElementById(event.target.name);
    row.parentNode.removeChild(row);
}