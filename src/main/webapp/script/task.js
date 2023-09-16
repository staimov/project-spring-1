function deleteTask(id) {
    if (id == null) {
        console.error("task id is null");
        return;
    }

    if (!(confirm('Are you sure you want to delete the task (id = ' + id + ')?')))
        return;

    let url = "/" + id;
    $.ajax({
        url: url,
        type: 'DELETE',
        success: function () {
            window.location.reload();
        }
    });
}

function editTask(id) {
    if (id == null) {
        console.error("task id is null");
        return;
    }

    let deleteButtonId = "#delete_button_" + id;
    $(deleteButtonId).remove();

    let editButtonId = "#edit_button_" + id;

    $(editButtonId)
        .removeClass('btn-info')
        .addClass('btn-success')
        .attr('onclick', 'saveTask(' + id + ')')
        .html('<i class="fas fa-save"></i> Save');

    let row = $(editButtonId).parent().parent().parent();
    let cells = row.children();

    let descriptionCell = cells[1];
    descriptionCell.innerHTML =
        "<input id='description_input_" + id + "' type='text' value='" + descriptionCell.innerHTML + "'>";

    let statusCell = cells[2];
    let statusDropdownId = "#status_dropdown_" + id;
    let statusCurrentValue = statusCell.innerHTML;
    statusCell.innerHTML = getStatusDropdownHtml(id);
    $(statusDropdownId).val(statusCurrentValue).change();
}

function getStatusDropdownHtml(id) {
    if (id == null) {
        console.error("task id is null");
        return;
    }

    let statusDropdownId = "status_dropdown_" + id;
    return "<label for='status'><label>"
        + "<select id=" + statusDropdownId + " name='status'/>"
        + "<option value='IN_PROGRESS'>IN_PROGRESS</option>"
        + "<option value='DONE'>DONE</option>"
        + "<option value='PAUSED'>PAUSED</option>"
        + "</select>"
}

function saveTask(id){
    if (id == null) {
        console.error("task id is null");
        return;
    }

    let url = "/" + id;
    let description_value = $("#description_input_" + id).val();
    let status_value = $("#status_dropdown_" + id).val();

    $.ajax({
        url: url,
        type: 'POST',
        datatype: 'json',
        contentType: 'application/json;charset=UTF-8',
        data: JSON.stringify({"description": description_value, "status": status_value})
    })

    setTimeout(() => {
        document.location.reload();}, 300)
}
