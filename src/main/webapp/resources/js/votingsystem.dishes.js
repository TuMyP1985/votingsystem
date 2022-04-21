const dishAjaxUrl = "votingsys/dish/";

// https://stackoverflow.com/a/5064235/548473
const ctx = {
    ajaxUrl: dishAjaxUrl,
    updateTable: function () {
        location.reload();
        // window.location = "dish"; //переход вместо обновления опять формируем (переходим) на страницу dish
    }
};

// $(document).ready(function () {
$(function () {
    makeEditable(
        $("#datatable").DataTable({
            "paging": false,
            "info": true,
            "columns": [
                {"data": "name"},
                {"data": "price"},
                {"defaultContent": "Edit",
                    "orderable": false},
                {
                    "defaultContent": "Delete",
                    "orderable": false
                }
            ],
            "order": [
                [
                    0,
                    "asc"
                ]
            ]
        })
    );
});